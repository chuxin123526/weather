package com.somebody.web.controller;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.somebody.po.Administrator;
import com.somebody.service.IAdministratorService;

@Controller
@RequestMapping(path = "/administratorController")
public class AdministratorController
{
	Logger logger = Logger.getLogger(AdministratorController.class) ; 
	
	@Resource 
    private Producer captchaProducer = null; 
	
	@Resource
	private IAdministratorService administratorServiceImpl ; 
	
	@RequestMapping(path = "/loginUI")
	public ModelAndView loginUI()
	{
		return new ModelAndView("administrator/loginUI") ; 
	}
	
	@RequestMapping(path = "/code")
	public void code(HttpServletRequest request , HttpServletResponse response) 
	{
		HttpSession session = request.getSession();  
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        
        String capText = captchaProducer.createText();  
        
        session.setAttribute(session.getId(), capText);  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = null ; 
        try
        {
        	out = response.getOutputStream();  
        	ImageIO.write(bi, "jpg", out);  
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	logger.error(e);
        }
        finally
        {
        	try
        	{
        		out.flush();  
        		out.close();  
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
            	logger.error(e);
        	}
        	 
        }
	}
	
	@RequestMapping(path = "/login")
	@ResponseBody
	public String login(@RequestParam("name") String name , 
				@RequestParam("password") String password ,
				@RequestParam("code") String code , 
				HttpServletRequest request) 
	{
		int state = 0 ;
		
		String sessionCode = (String)request.getSession().getAttribute(request.getSession().getId()) ; 
		if(!sessionCode.equals(code))
		{
			state = 3 ; 
		}
		else
		{
			Administrator administrator = new Administrator() ; 
			administrator.setName(name);
			administrator.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			Administrator administrator1 = null ; 
			try
			{
				administrator1 = this.administratorServiceImpl.selectByNameAndPassword(administrator) ; 
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error(e);
				state = 2 ; 
			}
			if(state != 2)
			{
				if(administrator1 == null)
				{
					state = 4 ; 
				}
				else
				{
					request.getSession().setAttribute("administrator", administrator1) ;
					state = 1 ; 
				}
			}
			
		}
		
		JSONObject result = new JSONObject() ; 
		result.put("state", state + "") ; 
		
		return result.toString() ; 
	}
	
	@RequestMapping(path = "/updatePasswordUI")
	public ModelAndView updatePasswordUI()
	{
		return new ModelAndView("/administrator/updatePasswordUI") ; 
	}
	
	@RequestMapping(path = "/updatePassword")
	@ResponseBody
	public String updatePassword(@RequestBody String requestBody , HttpServletRequest request)
	{
		int state = 0 ; 
		
		try
		{
			JSONObject jsonObject = new JSONObject(requestBody) ; 
			String name = ((Administrator)request.getSession().getAttribute("administrator")).getName() ; 
			String dataBaseOriginPassword = this.administratorServiceImpl.selectPasswordByName(name) ; 
			String originPassword = DigestUtils.md5DigestAsHex(jsonObject.getString("originPassword").getBytes()) ;
			if(!dataBaseOriginPassword.equals(originPassword))
			{
				state = 3 ; 
			}
			else
			{
				String newPassword = jsonObject.getString("newPassword") ; 
				newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes()) ;
				Administrator administrator = new Administrator() ; 
				administrator.setName(name);
				administrator.setPassword(newPassword);
				state = this.administratorServiceImpl.updatePassword(administrator) ; 
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
	
	@RequestMapping(path = "exit")
	public ModelAndView exit(HttpServletRequest request)
	{
		Administrator administrator = (Administrator)request.getSession().getAttribute("administrator") ; 
		if(administrator != null)
		{
			request.getSession().removeAttribute("administrator");
		}
		
		return new ModelAndView("administrator/loginUI") ; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
