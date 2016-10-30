package com.somebody.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somebody.mapper.AdministratorMapper;
import com.somebody.po.Administrator;
import com.somebody.service.IAdministratorService;

@Service
@Transactional
public class AdministratorServiceImpl implements IAdministratorService
{
	@Resource
	private AdministratorMapper administratorMapper ;

	public int add(Administrator administrator) throws Exception
	{
		return this.administratorMapper.add(administrator) ; 
	}

	public Administrator selectByNameAndPassword(Administrator administrator) throws Exception
	{
		return this.administratorMapper.selectByNameAndPassword(administrator) ; 
	}

	public int updatePassword(Administrator administrator) throws Exception
	{
		return this.administratorMapper.updatePassword(administrator) ; 
	}

	public String selectPasswordByName(String name) throws Exception
	{
		return this.administratorMapper.selectPasswordByName(name) ; 
	} 
	
}
