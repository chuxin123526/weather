package com.somebody.web.interfaces.android;

import java.net.URLDecoder;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.somebody.po.Orders;
import com.somebody.service.IOrdersService;
import com.somebody.service.IUserService;

@Controller
@RequestMapping(path = "/orders")
public class OrdersAction
{
	Logger logger = Logger.getLogger(OrdersAction.class) ; 
	
	@Resource
	private IOrdersService ordersServiceImpl ; 
	@Resource
	private IUserService userServiceImpl ;
	
	@RequestMapping(path = "/isBuy", method = RequestMethod.POST)
	@ResponseBody
	public String isBuy(@RequestBody String requestBody)
	{
		int state = 0 ; 
		try
		{
			requestBody = URLDecoder.decode(requestBody , "utf-8") ; 
			
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			String userName = jsonObject.getString("userName") ; 
			String packageID =jsonObject.getString("packageID") ; 
			if(this.userServiceImpl.selectCountByUserName(userName) == 0)
			{
				//用户不存在
				state = 3 ; 
			}
			else
			{
				state = this.ordersServiceImpl.isBuy(userName, Long.parseLong(packageID)) ; 
				if(state != 0)
				{
					//check whether deadline
					Orders orders = new Orders() ; 
					orders.setUserName(userName);
					orders.setPackageID( Long.parseLong(packageID));
					Date deadline = this.ordersServiceImpl.selectDeadlineByUserNameAndPackageID(userName , Long.parseLong(packageID)) ; 
					Date nowDate = new Date() ; 
					if(deadline.getTime() > nowDate.getTime())
					{
						//用户已经购买过该套餐且未过期
						state = 1 ; 
					}
					else
					{
						//用户购买了该套餐且过期
						state = 4 ; 
					}
				}
				else
				{
					state = 0 ; 
				}
			}
		}
		catch(Exception e)
		{
			state = 2 ; 
			this.logger.error(e);
		}	
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ;
	}
	
	@RequestMapping(path = "/buyPackage", method = RequestMethod.POST)
	@ResponseBody
	public String buyPackage(@RequestBody String requestBody) 
	{
		int state = 0 ; 
		try
		{

			requestBody = URLDecoder.decode(requestBody , "utf-8") ; 
			
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			String userName = jsonObject.getString("userName") ; 
			if(this.userServiceImpl.selectCountByUserName(userName) == 0)
			{
				//用户不存在
				state = 3 ; 
			}
			else
			{
				long packageID = Long.parseLong(jsonObject.getString("packageID")) ; 
			
				//check whether already buy
				if(this.ordersServiceImpl.isBuy(userName, packageID ) != 0)
				{
					//check whether deadline
					Orders orders = new Orders() ; 
					orders.setUserName(userName);
					orders.setPackageID(packageID);
					Date deadline = this.ordersServiceImpl.selectDeadlineByUserNameAndPackageID(userName , packageID) ; 
					Date nowDate = new Date() ; 
					if(deadline.getTime() > nowDate.getTime())
					{
						//用户已经购买过该套餐且未过期
						state = 0 ; 
					}
					else
					{
						//用户已经购买过该套餐且已经过期,可以购买
						int duration = Integer.parseInt(jsonObject.getString("duration")) ;
						this.ordersServiceImpl.buyPackage(userName, packageID, duration) ; 
						state = 1 ; 
					}
				}
				else
				{
					//用户没有购买过该套餐，可以购买
					int duration = Integer.parseInt(jsonObject.getString("duration")) ;
					this.ordersServiceImpl.buyPackage(userName, packageID, duration) ; 
					state = 1 ; 
				}
			}
		}
		catch(Exception e)
		{
			//异常
			state = 2 ; 
			this.logger.error(e);
		}
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ;  
	}
}
