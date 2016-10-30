package com.somebody.vo;

import java.util.Date;

public class Orders
{
	private Long id;

	private Integer price;

	private Date buyTime;

	private int duration;

	private Date deadline;

	private String userName;

	private String packageName;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Integer getPrice()
	{
		return price;
	}

	public void setPrice(Integer price)
	{
		this.price = price;
	}

	public Date getBuyTime()
	{
		return buyTime;
	}

	public void setBuyTime(Date buyTime)
	{
		this.buyTime = buyTime;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public Date getDeadline()
	{
		return deadline;
	}

	public void setDeadline(Date deadline)
	{
		this.deadline = deadline;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPackageName()
	{
		return packageName;
	}

	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}
	
	
}
