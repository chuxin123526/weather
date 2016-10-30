package com.somebody.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.somebody.service.IOrdersService;
import com.somebody.service.impl.OrdersServiceImpl;

public class TestOrders
{
	@Test
	public void buyPackage() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ;
		IOrdersService ordersService = (IOrdersService) 
				applicationContext.getBean("ordersServiceImpl") ; 
		String userName = "testIn75" ; 
		int duration = 3 ; 
		long packageID = 3 ; 
		for(int i = 0 ; i < 100 ; i++)
		{
			System.out.println( ordersService.buyPackage(userName, packageID, duration) ); 
			
		}
	}
	
	@Test
	public void isBuy() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ;
		IOrdersService ordersService = (IOrdersService) 
				applicationContext.getBean("ordersServiceImpl") ; 
		String userName = "test_3" ; 
		int packageID = 1 ; 
		int result = ordersService.isBuy(userName, packageID) ; 
		System.out.println(result);
	}
}
