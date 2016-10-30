package com.somebody.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somebody.mapper.UserMapper;
import com.somebody.po.User;
import com.somebody.service.IUserService;
import com.somebody.vo.UserQueryCondition;

@Service
@Transactional
public class UserServiceImpl implements IUserService
{
	@Resource
	private UserMapper userMapper ; 
	
	public int commonUserRegister(User user) throws Exception
	{
		user.setIsEnterpriseUser("0");
		user.setRegisterTime(new Date());
		int result = this.userMapper.insert(user) ; 
		return result ; 
	}

	public int enterpriseUserRegister(User user) throws Exception
	{
		user.setIsEnterpriseUser("1");
		user.setRegisterTime(new Date());
		int result = this.userMapper.insert(user) ; 
		return result ; 
	}

	public int isEnterpriseUser(String name) throws Exception
	{
		User user = this.userMapper.selectByPrimaryKey(name) ; 
		
		if(user.getIsEnterpriseUser().equals("1"))
		{
			return 1 ; 
		}
		else
		{
			return 0 ; 
		}
	}

	public User selectByPrimaryKey(String name) throws Exception
	{
		return this.userMapper.selectByPrimaryKey(name) ; 
	}

	public int isTrial(String userName) throws Exception
	{		
		
		Date date = this.userMapper.selectRegisterByUserName(userName) ; 
		Calendar calendar = Calendar.getInstance() ; 
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		date = calendar.getTime() ; 
		Date nowDate = new Date() ; 
		if(nowDate.getTime() > date.getTime())
		{
			return 0 ; 
		}
		else
		{
			return 1 ; 
		}
	}

	public int selectCountByUserName(String userName) throws Exception
	{
		return this.userMapper.selectCountByUserName(userName) ; 
	}

	public User selectByUserNameAndPassword(User user) throws Exception
	{
		return this.userMapper.selectByUserNameAndPassword(user) ; 
	}

	public List<User> list() throws Exception
	{
		return this.userMapper.list() ; 
	}

	public int delete(String userName) throws Exception
	{
		return this.userMapper.deleteByPrimaryKey(userName) ;
	}

	public int update(User user) throws Exception
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(User user) throws Exception
	{
		return this.userMapper.insert(user) ; 
	}

	public List<User> query(UserQueryCondition userQueryCondition) throws Exception
	{
		int count = this.userMapper.count(userQueryCondition) ; 
		if(count == 0)
		{
			userQueryCondition.setPageCount(1);
		}
		else
			if(count%10 == 0)
			{
				userQueryCondition.setPageCount(count/10);
			}
			else
			{
				userQueryCondition.setPageCount(count/10 +1) ;
			}
		return this.userMapper.query(userQueryCondition) ; 
	}
	

}
