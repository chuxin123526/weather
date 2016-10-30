package com.somebody.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somebody.mapper.PackageMapper;
import com.somebody.po.Package;
import com.somebody.service.IPackageService;
import com.somebody.vo.PackageQueryCondition;

@Service
@Transactional
public class PackageServiceImpl implements IPackageService
{
	@Resource
	private PackageMapper packageMapper ; 
	
	public com.somebody.po.Package selectByPrimaryKey(long packageID) throws Exception
	{
		return packageMapper.selectByPrimaryKey(packageID) ; 
	}

	public List<Package> list() throws Exception
	{
		return this.packageMapper.list() ; 
	}

	public int add(Package package1) throws Exception
	{
		return this.packageMapper.insert(package1) ; 
	}

	public int delete(long packageID) throws Exception
	{
		return this.packageMapper.deleteByPrimaryKey(packageID) ; 
	}

	public int update(Package package1) throws Exception
	{
		return this.packageMapper.updateByPrimaryKey(package1) ; 
	}

	public List<Package> query(PackageQueryCondition packageQueryCondition) throws Exception
	{
		int count = this.packageMapper.count(packageQueryCondition) ; 
		if(count == 0 )
		{
			packageQueryCondition.setPageCount(1);
		}
		else
			if(count%10 == 0)
			{
				packageQueryCondition.setPageCount(count/10);
			}
			else
			{
				packageQueryCondition.setPageCount(count/10 + 1);
			}
			
		return this.packageMapper.query(packageQueryCondition) ; 
	}

	public List<Package> selectPackageIDAndName() throws Exception
	{
		return this.packageMapper.selectPackageIDAndName() ;
	}

}
