package com.somebody.mapper;

import com.somebody.po.Administrator;

public interface AdministratorMapper
{
	public int add(Administrator administrator) ;
	
	public Administrator selectByNameAndPassword(Administrator administrator) ; 
	
	public int updatePassword(Administrator administrator) ; 
	
	public String selectPasswordByName(String name) ; 
}
