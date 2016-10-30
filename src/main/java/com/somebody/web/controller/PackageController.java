package com.somebody.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.somebody.po.Package;
import com.somebody.service.IPackageService;
import com.somebody.vo.PackageQueryCondition;

@Controller
@RequestMapping(path = "/packageController")
public class PackageController
{
	Logger logger = Logger.getLogger(PackageController.class) ; 
	
	@Resource
	private IPackageService packageServiceImpl ; 
	
	@RequestMapping(path = "/list")
	public ModelAndView list(@RequestParam(value = "page" , required = false) String page ,
			@RequestParam(value = "keyword" , required = false) String keyword )
	{
		ModelAndView modelAndView = new ModelAndView("package/list") ; 
		try
		{
			if(page == null || page.equals(""))
			{
				page = "1" ; 
			}
			PackageQueryCondition packageQueryCondition = new PackageQueryCondition() ; 
			packageQueryCondition.setKeyword(keyword);
			packageQueryCondition.setPage(Integer.parseInt(page));
			List<Package> packageList = this.packageServiceImpl.query(packageQueryCondition) ;
			//set view parameter
			modelAndView.addObject("page" , packageQueryCondition.getPage()) ; 
			modelAndView.addObject("pageCount" , packageQueryCondition.getPageCount()) ; 
			modelAndView.addObject("keyword" , keyword) ; 
			modelAndView.addObject("packageList", packageList) ; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		
		return modelAndView ;
	}
	
	@RequestMapping(path = "addUI")
	public ModelAndView addUI()
	{
		ModelAndView modelAndView = new ModelAndView("package/addUI") ; 
		
		return modelAndView ;
	}

	@RequestMapping(path = "add")
	@ResponseBody
	public String add(@RequestBody String requestBody)
	{
		int state = 0 ; 
		
		try
		{
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			String name = jsonObject.getString("name") ; 
			int price = 0 ; 
			try
			{
				price = Integer.parseInt(jsonObject.getString("price")) ; 
			}
			catch(Exception e)
			{
				state = 3 ; 
				logger.error(e);
			}
			
			String detailDescribe = jsonObject.getString("detailDescribe") ; 
			if(name == null || name.equals("") ||name.length() > 10 || detailDescribe == null || detailDescribe.equals("") || detailDescribe.length() > 100)
			{
				state = 3 ; 
			}
			else
			{
				Package package1 = new Package() ; 
				package1.setName(name);
				package1.setPrice(price);
				package1.setDetailDescribe(detailDescribe);
				package1.setIsSale("1");
				state = this.packageServiceImpl.add(package1) ; 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ; 
	}
	
	@RequestMapping(path = "delete")
	@ResponseBody
	public String delete(@RequestBody String requestBody)
	{
		int state = 0 ; 
		long packageID = 0 ;
		try
		{
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			try
			{
				packageID = Long.parseLong(jsonObject.getString("packageID")) ; 
			}
			catch(Exception e)
			{
				state = 3 ; 
				logger.error(e);
				e.printStackTrace();
			}
			if(state != 3)
			{
				state = this.packageServiceImpl.delete(packageID) ;
			}
		}
		catch(Exception e)
		{
			state = 2 ; 
			e.printStackTrace(); 
			logger.error(e);
		}
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ;
	}
	
	
	@RequestMapping(path = "updateUI")
	public ModelAndView updateUI(@RequestParam("packageID") String packageID)
	{
		ModelAndView modelAndView = new ModelAndView("package/updateUI") ;
		ModelAndView pageNotFound = new ModelAndView("pageNotFound") ; 
		long packageid = 0 ; 
		try
		{
			try
			{
				packageid = Long.parseLong(packageID) ; 
			}
			catch(Exception e)
			{
				return pageNotFound ; 
			}
			Package package1 = this.packageServiceImpl.selectByPrimaryKey(packageid) ; 
			if(package1 == null)
			{
				return pageNotFound ; 
			}
			modelAndView.addObject("packageObject" , package1) ;
			modelAndView.addObject("packageID", packageID) ; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e) ; 
			return new ModelAndView("pageNotFound") ; 
		}
		
		return modelAndView ; 
	}
	
	@RequestMapping(path = "/update" , method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestBody String requestBody)
	{
		int state = 0 ; 
		Package package1 = new Package() ; 
		long packageID = 0 ; 
		String name = null ;
		int price = 0 ; 
		String detailDescribe = null ; 
		try
		{
			try
			{
				JSONObject jsonObject = new JSONObject(requestBody) ; 
				packageID = jsonObject.getLong("packageID") ; 
				name = jsonObject.getString("name") ; 
				price = jsonObject.getInt("price") ; 
				detailDescribe = jsonObject.getString("detailDescribe") ;
				if(name == null || name.equals("") ||name.length() > 10 || detailDescribe == null || detailDescribe.equals("") || detailDescribe.length() > 100)
				{
					state = 3 ; 
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error(e);
				state = 3 ; 
			}
			if(state != 3)
			{
				package1.setId(packageID);
				package1.setName(name);
				package1.setPrice(price);
				package1.setDetailDescribe(detailDescribe);
				package1.setIsSale("1");
				state = this.packageServiceImpl.update(package1) ; 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e);
			state = 2 ; 
		}
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
