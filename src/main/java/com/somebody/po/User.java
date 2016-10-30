package com.somebody.po;

import java.util.Date;

public class User
{
	private String name;

	private String password;

	private String isEnterpriseUser;

	private Date registerTime;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name == null ? null : name.trim();
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password == null ? null : password.trim();
	}

	public String getIsEnterpriseUser()
	{
		return isEnterpriseUser;
	}

	public void setIsEnterpriseUser(String isEnterpriseUser)
	{
		this.isEnterpriseUser = isEnterpriseUser;
	}

	public Date getRegisterTime()
	{
		return registerTime;
	}

	public void setRegisterTime(Date registerTime)
	{
		this.registerTime = registerTime;
	}

	
}