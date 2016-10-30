package com.somebody.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.somebody.po.Orders;
import com.somebody.po.Package;
import com.somebody.service.IOrdersService;
import com.somebody.service.IPackageService;
import com.somebody.vo.OrdersQueryCondition;

@Controller
@RequestMapping(path = "/ordersController")
public class OrdersController
{
	Logger logger = Logger.getLogger(OrdersController.class) ; 
	
	@Resource
	private IOrdersService orderServiceImpl ; 
	@Resource
	private IPackageService packageServiceImpl ; 
	
	@RequestMapping(path = "/list")
	public ModelAndView list(@RequestParam(value = "page" , required = false) String page ,
			@RequestParam(value = "packageID" , required = false) String packageID, 
			@RequestParam(value = "beginTime" ,required = false) String beginTime, 
			@RequestParam(value = "endTime" , required = false) String endTime )
	{
		ModelAndView modelAndView = new ModelAndView("orders/list") ; 
		try
		{
			if(page == null || page.equals(""))
			{
				page = "1" ; 
			}
			OrdersQueryCondition ordersQueryCondition = new OrdersQueryCondition() ; 
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd") ; 
			Date beginDate = null ; 
			if(beginTime != null && !beginTime.equals(""))
			{
				beginDate = simpleDateFormat.parse(beginTime) ; 
			}
			Date endDate = null ; 
			if(endTime != null && !endTime.equals(""))
			{
				endDate = simpleDateFormat.parse(endTime) ; 
			}
			ordersQueryCondition.setBeginTime(beginDate);
			ordersQueryCondition.setEndTime(endDate);
			long packageId = 0 ; 
			if(packageID != null && !packageID.equals(""))
			{
				packageId = Long.parseLong(packageID) ; 
			}
			ordersQueryCondition.setPackageID(packageId);
			ordersQueryCondition.setPage(Integer.parseInt(page));
			List<com.somebody.vo.Orders> list = this.orderServiceImpl.query(ordersQueryCondition) ; 
			modelAndView.addObject("ordersList" , list) ; 
			modelAndView.addObject("page" , ordersQueryCondition.getPage()) ; 
			modelAndView.addObject("pageCount" , ordersQueryCondition.getPageCount()) ;
			modelAndView.addObject("packageID" , packageID) ; 
			modelAndView.addObject("beginTime" , beginTime) ; 
			modelAndView.addObject("endTime" , endTime) ; 
			List<Package> packageList = this.packageServiceImpl.selectPackageIDAndName() ;  
			modelAndView.addObject("packageList" , packageList) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e);
			modelAndView.setViewName("pageNotFound");
		}
		
		return modelAndView ;
	}
	
	@RequestMapping(path = "delete")
	@ResponseBody
	public String delete(@RequestBody String requestBody)
	{
		int state = 0 ; 
		
		try
		{
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			long ordersID = Long.parseLong(jsonObject.getString("ordersID")) ;
			state = this.orderServiceImpl.delete(ordersID) ; 
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
			logger.error(e);
		}
		
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ; 
	}
}
