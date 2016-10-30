package com.somebody.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somebody.mapper.OrdersMapper;
import com.somebody.po.Orders;
import com.somebody.service.IOrdersService;
import com.somebody.service.IPackageService;
import com.somebody.vo.OrdersQueryCondition;

@Service
@Transactional
public class OrdersServiceImpl  implements IOrdersService
{
	@Resource
	private OrdersMapper ordersMapper ; 
	@Resource
	private IPackageService packageServiceImpl ; 
	
	public int buyPackage(String userName, long packageID , int duration) 
			throws Exception
	{	
		Orders orders = new Orders() ; 
		orders.setBuyTime(new Date());
		orders.setDuration(duration);
		Calendar calendar = Calendar.getInstance() ; 
		calendar.setTime(orders.getBuyTime());
		calendar.add(Calendar.YEAR, duration);
		orders.setDeadline(calendar.getTime());
		orders.setPackageID(packageID);
		com.somebody.po.Package package1 = this.packageServiceImpl.selectByPrimaryKey(packageID) ; 
		int price = package1.getPrice() * duration ; 
		orders.setPrice(price);
		orders.setUserName(userName);
		
		int result = this.ordersMapper.insert(orders) ; 
		
		return result ; 
	}

	public int isBuy(String userName , long packageID) throws Exception
	{
		Orders orders = new Orders() ; 
		orders.setUserName(userName);
		orders.setPackageID(packageID);
		
		int result = this.ordersMapper.isBuy(orders) ; 
		return result ;
	}

	public Date selectDeadlineByUserNameAndPackageID(String userName , long packageID) throws Exception
	{
		Orders orders = new Orders() ; 
		orders.setUserName(userName);
		orders.setPackageID(packageID);
		
		return this.ordersMapper.selectDeadlineByUserNameAndPackageID(orders) ; 
	}

	public List<Orders> list() throws Exception
	{
		return this.ordersMapper.list() ; 
	}

	public List<com.somebody.vo.Orders> query(OrdersQueryCondition ordersQueryCondition) throws Exception
	{
		int count = this.ordersMapper.count(ordersQueryCondition) ; 
		if(count == 0)
		{
			ordersQueryCondition.setPageCount(1);
		}
		else
			if(count%10 == 0)
			{
				ordersQueryCondition.setPageCount(count/10);
			}
			else
			{
				ordersQueryCondition.setPageCount(count/10 +1);
			}
		
		return this.ordersMapper.query(ordersQueryCondition) ; 
	}
	
	public int delete(long ordersID) throws Exception
	{
		return this.ordersMapper.deleteByPrimaryKey(ordersID) ;  
	}

}
