package com.sickkids.caliper.dao;

import java.io.Serializable;

public class AllLoginLevelBean implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7667481499724920250L;
	private int loginLevel=0;//1: Central Admin, 2: Data Manager (only Data Management and Sites) 3. Central View 4. Local Admin 5. Local View
	private String loginLevelDescription="";
	
	public int getLoginLevel()
	{
		return loginLevel;
	}
	public void setLoginLevel(int loginLevel)
	{
		this.loginLevel = loginLevel;
	}
	public String getLoginLevelDescription()
	{
		return loginLevelDescription;
	}
	public void setLoginLevelDescription(String loginLevelDescription)
	{
		this.loginLevelDescription = loginLevelDescription;
	}
}
