package com.somebody.mapper;

import java.util.Date;
import java.util.List;

import com.somebody.po.User;
import com.somebody.vo.UserQueryCondition;

public interface UserMapper 
{
    int deleteByPrimaryKey(String name);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    public Date selectRegisterByUserName(String userName) ; 
    
    public int selectCountByUserName(String userName) ; 
    
    public User selectByUserNameAndPassword(User user) ;
    
    public List<User> list() ; 
    
    public List<User> query(UserQueryCondition userQueryCondition) ; 
    
    public int count(UserQueryCondition userQueryCondition) ; 
}