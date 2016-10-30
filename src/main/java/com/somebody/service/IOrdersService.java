package com.somebody.service;

import java.util.Date;
import java.util.List;

import com.somebody.po.Orders;
import com.somebody.vo.OrdersQueryCondition;

public interface IOrdersService
{
	public int buyPackage(String userName, long packageID, int duration) throws Exception;

	public int isBuy(String userName, long packageID) throws Exception;

	public Date selectDeadlineByUserNameAndPackageID(String userName, long packageID) throws Exception;

	public List<Orders> list() throws Exception; 
	
	public List<com.somebody.vo.Orders> query(OrdersQueryCondition ordersQueryCondition) throws Exception ; 
	
	public int delete(long ordersID) throws Exception; 
}
