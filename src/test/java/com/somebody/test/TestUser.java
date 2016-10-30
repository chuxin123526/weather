package com.somebody.test;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import com.somebody.mapper.PackageMapper;
import com.somebody.mapper.UserMapper;
import com.somebody.po.Package;
import com.somebody.po.User;
import com.somebody.service.IUserService;
import com.somebody.service.impl.UserServiceImpl;

public class TestUser
{
	@Test
	public void test() throws Exception
	{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession() ; 
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class) ; 
		User user = userMapper.selectByPrimaryKey("test1") ; 
		System.out.println(user);
		System.out.println(user.getRegisterTime());
	}
	
	@Test
	public void insert() throws Exception
	{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession() ; 
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class) ; 
		User user = new User() ; 
		user.setName("testfef");
		System.out.println(userMapper.insert(user)) ; 
		
		
		sqlSession.commit(); 
	}
	
	@Test
	public void testInsert() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		IUserService userServiceImpl =(UserServiceImpl) 
				applicationContext.getBean("userServiceImpl") ; 
		for(int i = 0 ; i < 116 ; i++)
		{
			User user = new User() ;
			user.setName("testn" + i);
			user.setPassword(DigestUtils.md5DigestAsHex(new String("test").getBytes()));
			int result = userServiceImpl.commonUserRegister(user) ; 
		}
		
	}
	
	@Test
	public void testIsEnterpriseUser() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		IUserService userServiceImpl =(UserServiceImpl) 
				applicationContext.getBean("userServiceImpl") ; 
		int result = userServiceImpl.isEnterpriseUser("test1") ; 
		System.out.println(result);
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testIsTrail() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		IUserService userServiceImpl =(UserServiceImpl) 
				applicationContext.getBean("userServiceImpl") ; 
		int result = userServiceImpl.isTrial("test1") ; 
		System.out.println(result);
	}
	
	@Test
	public void testLogin() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		IUserService userServiceImpl =(UserServiceImpl) 
				applicationContext.getBean("userServiceImpl") ; 
		User user = new User() ;
		user.setName("中文");
		user.setPassword("testPassword");
		User result = userServiceImpl.selectByUserNameAndPassword(user); 
		System.out.println(result);
	}
	
	@Test
	public void testList() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		IUserService userServiceImpl =(UserServiceImpl) 
				applicationContext.getBean("userServiceImpl") ; 
		List<User> list = userServiceImpl.list() ; 
		System.out.println(list.size());
		for(User user : list)
		{
			System.out.println("---------");
			System.out.println(user.getName());
			System.out.println(user.getIsEnterpriseUser());
			System.out.println(user.getRegisterTime());
		}
	}
			
}
