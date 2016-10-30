package com.somebody.mapper;


import java.util.List;

import com.somebody.po.Package;
import com.somebody.vo.PackageQueryCondition;

public interface PackageMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(Package record);

    int insertSelective(Package record);

    Package selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Package record);

    int updateByPrimaryKey(Package record);
    
    public List<Package> list() ; 
    
    public List<Package> query(PackageQueryCondition packageQueryCondition) ;
    
    public int count(PackageQueryCondition packageQueryCondition) ;
    
    public List<Package> selectPackageIDAndName() ; 
}