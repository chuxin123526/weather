package com.somebody.web.interfaces.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.somebody.po.User;
import com.somebody.service.IUserService;
import com.somebody.utils.CharacterCheck;

@Controller
@RequestMapping("/user")
public class UserAction
{
	Logger logger = Logger.getLogger(UserAction.class) ; 
	
	@Resource
	private IUserService userServiceImpl ; 
	
	@RequestMapping(path = "/commonUserRegister", method = RequestMethod.POST)
	@ResponseBody
	public String commonUserRegister(@RequestBody String requestBody) 
	{
		
		int state = 0 ; 
		try
		{
			requestBody = URLDecoder.decode(requestBody , "utf-8") ; 
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			String userName = jsonObject.getString("userName") ; 
			
			String password = jsonObject.getString("password") ; 
			
			//参数合法性检查
			if(CharacterCheck.isEnglish(password) && CharacterCheck.checkUserName(userName) && CharacterCheck.checkPassword(password)&&
					userName.length() <= 8 && userName != null && !userName.equals("") &&
					password.length() <= 16 && password != null && !password.equals(""))
			{
				if(this.userServiceImpl.selectCountByUserName(userName)!=0)
				{
					//该用户名已被注册
					state = 0 ; 
				}
				else
				{
					password = DigestUtils.md5DigestAsHex(password.getBytes()) ; 
					User user = new User() ; 
					user.setName(userName);
					user.setPassword(password);
					state = this.userServiceImpl.commonUserRegister(user) ;
				}
			}
			else
			{
				//参数不合法
				state = 3 ; 
			}
			
		}
		catch(Exception e)
		{
			//服务器发生异常
			state = 2 ; 
			this.logger.error(e);
		}
		 
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		return result.toString() ; 
		
	}
	
	@RequestMapping(path = "/enterpriseUserRegister" , method = RequestMethod.POST)
	@ResponseBody
	public String enterpriseUserRegister(@RequestBody String requestBody) 
	{
		int state = 0 ; 
		try
		{
			requestBody = URLDecoder.decode(requestBody , "utf-8") ; 
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			String userName = jsonObject.getString("userName") ; 
			String password = jsonObject.getString("password") ; 
			
			//参数合法性检查
			if(CharacterCheck.isEnglish(password) && CharacterCheck.checkUserName(userName) && CharacterCheck.checkPassword(password)&&
					userName.length() <= 8 && userName != null && !userName.equals("") &&
					password.length() <= 16 && password != null && !password.equals(""))
			{
				if(this.userServiceImpl.selectCountByUserName(userName)!=0)
				{
					//该用户名已被注册
					state = 0 ; 
				}
				else
				{
					password = DigestUtils.md5DigestAsHex(password.getBytes()) ; 
					User user = new User() ; 
					user.setName(userName);
					user.setPassword(password);
					state = this.userServiceImpl.enterpriseUserRegister(user) ;
				}
			}
			else
			{
				//参数不合法
				state = 3 ; 
			}
			
		}
		catch(Exception e)
		{
			//服务器发生异常
			state = 2 ; 
			this.logger.error(e);
		}
		 
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		return result.toString() ; 
	}
	
	@RequestMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public void login(@RequestBody String requestBody , HttpServletResponse response ,
			HttpServletRequest request)
	{
		int state = 0 ; 
		User user = new User() ; 
		try
		{
			requestBody = URLDecoder.decode(requestBody , "utf-8") ; 
			
			JSONObject jsonObject = new JSONObject(requestBody) ;
			String userName = jsonObject.getString("userName") ; 
			String password = jsonObject.getString("password") ; 
			if(CharacterCheck.isEnglish(password) && CharacterCheck.checkUserName(userName) && CharacterCheck.checkPassword(password)&&
					userName.length() <= 8 && userName != null && !userName.equals("") &&
					password.length() <= 16 && password != null && !password.equals(""))
			{
				user = new User() ; 
				user.setName(userName);
				user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
				user = this.userServiceImpl.selectByUserNameAndPassword(user) ; 
				if(user == null)
				{
					//用户名或密码错误
					state = 0 ; 
				}
				else
				{
					request.getSession().putValue("user", user); 
					state = 1 ; 
				}
			}
			else
			{
				//参数不合法
				state = 3 ; 
			}
		}
		catch(Exception e)
		{
			//服务器异常
			state = 2 ; 
			this.logger.error(e);
		}
		
		JSONObject result = new JSONObject() ;
		result.put("state", state + "") ; 
		if(state == 0)
		{
			user = new User() ; 
		}
		JSONObject result1 = new JSONObject(user) ; 
		result.put("state", state + "") ; 
		result.put("user", result1) ; 
		
		//return 
		response.setHeader("Cache-Control", "no-cache");   
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
	
	@RequestMapping(path = "/isEnterpriseUser", method = RequestMethod.POST)
	@ResponseBody
	public String isEnterpriseUser(@RequestBody String requestBody)
	{
		int state = 0 ; 
		
		try
		{
			requestBody = URLDecoder.decode(requestBody , "utf-8") ; 
			
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			String userName = jsonObject.getString("userName") ;
			if(this.userServiceImpl.selectCountByUserName(userName) == 0)
			{
				state = 3 ; 
			}
			else
			{
				state = this.userServiceImpl.isEnterpriseUser(userName) ; 
			}
			
		}
		catch(Exception e)
		{
			//服务器异常
			this.logger.error(e);
			state = 2 ; 
		}
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ; 
		
	}
	
	@RequestMapping(path = "/isTrial", method = RequestMethod.POST)
	@ResponseBody
	public String isTrial(@RequestBody String requestBody)
	{
		int state = 0 ;
		
		try
		{
			requestBody = URLDecoder.decode(requestBody , "utf-8") ; 
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			String userName = jsonObject.getString("userName") ;
			if(this.userServiceImpl.selectCountByUserName(userName) == 0)
			{
				//用户不存在
				state = 3 ; 
			}
			else
			{
				state = this.userServiceImpl.isTrial(userName) ; 
			}
			
			
		}
		catch(Exception e)
		{
			this.logger.error(e);
			state = 2 ; 
		}
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
