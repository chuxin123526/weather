package com.somebody.web.interfaces.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.somebody.po.Package;
import com.somebody.service.IPackageService;
import com.somebody.service.IUserService;

@Controller
@RequestMapping(path = "/package")
public class PackageAction
{
	Logger logger = Logger.getLogger(PackageAction.class) ; 
	
	@Resource
	private IPackageService packageServiceImpl ; 
	
	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public void list(HttpServletResponse response)
	{
		int state = 0 ; 
		List<Package> packageList = new ArrayList<Package>() ; 
		try
		{
			packageList = this.packageServiceImpl.list() ; 
			state = 1 ; 
		}
		catch(Exception e)
		{
			//服务器异常
			state = 2 ; 
			this.logger.error(e);
		}
		
		JSONObject stateJson = new JSONObject() ; 
		stateJson.put("state", state + "") ;
		JSONArray jsonArray2 = new JSONArray(packageList) ; 

		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		result.put("packageList" , jsonArray2) ; 
		
		
		//return 
		response.setHeader("Cache-Control", "no-cache");   
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//		response.setHeader("Access-Control-Allow-Headers", "*");
//		response.setHeader("Access-Control-Allow-Origin", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
        response.setContentType("text/json;charset=UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out = null;
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			this.logger.error(e);
		}  
        out.write(result.toString());
	}	
	
}
