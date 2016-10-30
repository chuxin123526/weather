package com.somebody.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMybatisSpring
{
	@Test
	public void test() throws Exception
	{
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)applicationContext.getBean("sqlSessionFactory") ; 
		System.out.println(sqlSessionFactory);
	}
}
