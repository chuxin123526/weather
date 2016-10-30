package com.somebody.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.somebody.po.Administrator;

public class LoginIntercepter extends HandlerInterceptorAdapter  
{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		Administrator administrator = (Administrator)request.getSession().getAttribute("administrator") ; 
		if(administrator != null)
		{
			return true ; 
		}
		else
		{
			response.sendRedirect("../administratorController/loginUI");
			return false ; 
		}
	}
	
}
