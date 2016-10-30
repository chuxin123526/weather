package com.somebody.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring
{
	@Test
	public void test()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext() ; 
		System.out.println(applicationContext);
	}
}
