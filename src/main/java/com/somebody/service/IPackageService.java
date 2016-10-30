package com.somebody.service;

import java.util.List;

import com.somebody.po.Package;
import com.somebody.vo.PackageQueryCondition;

public interface IPackageService
{
	public Package selectByPrimaryKey(long packageID) throws Exception;

	public List<Package> list() throws Exception;

	public int add(Package package1) throws Exception;

	public int delete(long packageID) throws Exception;

	public int update(Package package1) throws Exception;

	public List<Package> query(PackageQueryCondition packageQueryCondition) throws Exception;
	
	public List<Package> selectPackageIDAndName() throws Exception; 
}
