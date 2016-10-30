package com.somebody.service;

import java.util.List;

import com.somebody.po.User;
import com.somebody.vo.UserQueryCondition;

public interface IUserService
{
	public int commonUserRegister(User user) throws Exception;

	public int enterpriseUserRegister(User user) throws Exception;

	public int isEnterpriseUser(String name) throws Exception;

	public User selectByPrimaryKey(String name) throws Exception;

	public int isTrial(String userName) throws Exception;

	public int selectCountByUserName(String userName) throws Exception;

	public User selectByUserNameAndPassword(User user) throws Exception;

	public List<User> list() throws Exception;

	public int delete(String userName) throws Exception;

	public int update(User user) throws Exception;

	public int insert(User user) throws Exception;

	public List<User> query(UserQueryCondition userQueryCondition) throws Exception ; 
}
