package com.somebody.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.somebody.po.User;
import com.somebody.service.IUserService;
import com.somebody.utils.CharacterCheck;
import com.somebody.vo.UserQueryCondition;

@Controller
@RequestMapping(path = "/userController")
public class UserController
{
	@Resource
	private IUserService userServiceImpl ; 
	
	Logger logger = Logger.getLogger(UserController.class) ;
	
	@RequestMapping(path = "/list")
	public ModelAndView list(@RequestParam(value = "keyword" , required = false) String keyword ,
			@RequestParam(value = "isEnterpriseUser" , required = false ) String isEnterpriseUser , 
			@RequestParam(value = "page" ,required = false) String page)
	{
		ModelAndView modelAndView = new ModelAndView("user/list") ; 
		try
		{
			UserQueryCondition userQueryCondition = new UserQueryCondition() ; 
			userQueryCondition.setKeyword(keyword);
			userQueryCondition.setIsEnterpriseUser(isEnterpriseUser);
			if(page == null || page.equals("")){page = "1";}
			userQueryCondition.setPage(Integer.parseInt(page));
			List<User> list = this.userServiceImpl.query(userQueryCondition) ; 
			modelAndView.addObject("userList", list) ; 
			modelAndView.addObject("keyword" , keyword) ; 
			modelAndView.addObject("isEnterpriseUser", isEnterpriseUser) ;
			modelAndView.addObject("page", page) ; 
			modelAndView.addObject("pageCount", userQueryCondition.getPageCount()) ; 

		}
		catch(Exception e)
		{
			logger.error(e);
		}
		
		return modelAndView ;
	}
	
	@RequestMapping(path = "/delete" , method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody String requestBody)
	{
		int state = 0 ; 
		try
		{
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			String userName = jsonObject.getString("userName") ;
			state = this.userServiceImpl.delete(userName) ; 
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
	
	@RequestMapping(path = "/addUI")
	public ModelAndView addUI()
	{
		return new ModelAndView("user/addUI") ; 
	}
	
	@RequestMapping(path = "add")
	@ResponseBody
	public String add(@RequestBody String requestBody)
	{
		int state = 0 ; 
		
		try
		{
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			//参数合法性检查
			String userName = jsonObject.getString("userName") ; 
			String password = jsonObject.getString("password") ; 
			if(CharacterCheck.checkUserName(userName) && CharacterCheck.checkPassword(password)&&
					userName.length() <= 8 && userName != null && !userName.equals("") &&
					password.length() <= 16 && password != null && !password.equals(""))
			{
				if(this.userServiceImpl.selectCountByUserName(userName) != 0)
				{
					state = 5 ; 
				}
				else
				{
					User user = new User() ; 
					user.setName(jsonObject.getString("userName"));
					user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
					user.setIsEnterpriseUser(jsonObject.getString("isEnterpriseUser"));
					state = this.userServiceImpl.insert(user) ; 
				}
			}
			else
			{
				state = 3 ; 
			}
			
		}
		catch(Exception e)
		{
			logger.error(e);
			e.printStackTrace();
			state = 2 ; 
		}
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ; 
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
