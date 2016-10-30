package com.somebody.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.junit.Test;

public class CharacterCheck
{
	/**
	 * 检查字符是否含有中文
	 * @param s
	 * @return
	 */
	public static boolean isEnglish(String s)
	{
		for(int i = 0 ; i < s.length() ; i++)
		{
			char c = s.charAt(i) ; 
			if((c+"").getBytes().length != 1)
			{
				return false ; 
			}
		}
		
		return true ; 
	}
	
	public static boolean checkUserName(String userName)
	{
		String regex = "^[a-z0-9A-Z_]{1,8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(userName);
		return m.matches();
	}
	
	public static boolean checkPassword(String userName)
	{
		String regex = "^[a-z0-9A-Z]{1,16}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(userName);
		return m.matches();
	}

}
