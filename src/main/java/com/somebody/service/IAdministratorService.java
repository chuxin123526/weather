package com.somebody.service;

import com.somebody.po.Administrator;

public interface IAdministratorService
{
	public int add(Administrator administrator) throws Exception ; 
	
	public Administrator selectByNameAndPassword(Administrator administrator) throws Exception; 
	
	public int updatePassword(Administrator administrator) throws Exception ; 
	
	public String selectPasswordByName(String name) throws Exception;
}
