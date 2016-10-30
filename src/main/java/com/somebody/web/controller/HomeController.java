package com.somebody.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/homeController")
public class HomeController
{
	private Logger logger = Logger.getLogger(HomeController.class) ;
	
	@RequestMapping(path = "/index")
	public ModelAndView index()
	{
		ModelAndView modelAndView = new ModelAndView("home/index") ; 
		modelAndView.addObject("test" , "name") ; 
		return modelAndView ; 
	}
	
}
