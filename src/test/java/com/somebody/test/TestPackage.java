package com.somebody.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.somebody.mapper.PackageMapper;
import com.somebody.po.Package;
import com.somebody.service.IPackageService;
import com.somebody.service.IUserService;
import com.somebody.service.impl.UserServiceImpl;

public class TestPackage
{
	@Test
	public void test() throws Exception
	{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession() ; 
		
		PackageMapper packageMapper = sqlSession.getMapper(PackageMapper.class) ; 
		Package package1 = packageMapper.selectByPrimaryKey((long)1) ; 
		System.out.println(package1.getName());
		
	}
	
	@Test
	public void list() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		IPackageService packageService = (IPackageService)applicationContext.getBean("packageServiceImpl") ; 
		List<Package> list = packageService.list() ; 
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void add() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		IPackageService packageService = (IPackageService)applicationContext.getBean("packageServiceImpl") ; 
		Package package1 = new Package() ; 
		package1.setName("test");
		package1.setPrice(100);
		package1.setDetailDescribe("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		package1.setIsSale("1");
		
		for(int i = 0 ; i < 100 ; i++)
		{
			packageService.add(package1) ; 
		}
	}
	
}
