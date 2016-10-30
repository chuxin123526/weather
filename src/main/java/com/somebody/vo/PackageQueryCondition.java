package com.somebody.vo;

public class PackageQueryCondition
{
	private String keyword ;
	private int page ; 
	private int pageCount ;
	
	public String getKeyword()
	{
		return keyword;
	}
	public void setKeyword(String keyword)
	{
		if(keyword != null && !keyword.equals(""))
		{
			keyword = "%" + keyword +  "%" ;
		}
		this.keyword = keyword;
	}
	
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

	
}
