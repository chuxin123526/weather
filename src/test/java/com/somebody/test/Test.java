package com.somebody.test;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import com.somebody.po.User;

public class Test
{
	@org.junit.Test
	public void t() throws Exception
	{
		User user = new User() ; 
		user.setName("userName");
		user.setPassword("testPassword");
		JSONObject jsonObject = new JSONObject(user) ;
		System.out.println(jsonObject);
	}
}
