package com.somebody.vo;

import java.util.Date;

public class OrdersQueryCondition
{
	private int page ; 
	private int pageCount ;
	private Date beginTime ; 
	private Date endTime ; 
	private long packageID ; 
	
	public int getPage()
	{
		return page;
	}
	public void setPage(int page)
	{
		this.page = page;
	}
	public int getPageCount()
	{
		return pageCount;
	}
	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}
	public Date getBeginTime()
	{
		return beginTime;
	}
	public void setBeginTime(Date beginTime)
	{
		this.beginTime = beginTime;
	}
	public Date getEndTime()
	{
		return endTime;
	}
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}
	public long getPackageID()
	{
		return packageID;
	}
	public void setPackageID(long packageID)
	{
		this.packageID = packageID;
	} 
	
	
}
