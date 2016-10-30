package com.somebody.po;

public class Package
{
	private Long id;

	private String name;

	private Integer price;

	private String detailDescribe;

	private String isSale;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name == null ? null : name.trim();
	}

	public Integer getPrice()
	{
		return price;
	}

	public void setPrice(Integer price)
	{
		this.price = price;
	}

	public String getDetailDescribe()
	{
		return detailDescribe;
	}

	public void setDetailDescribe(String detailDescribe)
	{
		this.detailDescribe = detailDescribe;
	}

	public String getIsSale()
	{
		return isSale;
	}

	public void setIsSale(String isSale)
	{
		this.isSale = isSale;
	}

	

	
}