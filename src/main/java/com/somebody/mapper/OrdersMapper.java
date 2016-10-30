package com.somebody.mapper;

import java.util.Date;
import java.util.List;

import com.somebody.po.Orders;
import com.somebody.vo.OrdersQueryCondition;

public interface OrdersMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
    public int isBuy(Orders orders) ; 
    
    public Date selectDeadlineByUserNameAndPackageID(Orders orders) ; 
    
    public List<Orders> list() ; 
    
    public int count(OrdersQueryCondition ordersQueryCondition) ; 
    
    public List<com.somebody.vo.Orders> query(OrdersQueryCondition ordersQueryCondition) ; 
    
}