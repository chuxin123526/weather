package com.somebody.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.somebody.mapper.UserMapper;
import com.somebody.po.User;

public class TestMybatis
{
	@Test
	public void test() throws Exception
	{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try
		{
			UserMapper userMapper = session.getMapper(UserMapper.class) ; 
			User user = new User() ; 
			user.setName("test");
			userMapper.insert(user);
			session.commit();
		}
		finally
		{
			session.close();
		}
		
	}
	
}
