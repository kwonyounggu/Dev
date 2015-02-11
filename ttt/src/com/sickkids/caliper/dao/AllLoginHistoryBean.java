package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class AllLoginHistoryBean implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4599311274887683320L;
	private long login_hist_index=0L;
	private int app_id=0;
	private String user_id="";
	private Timestamp login_time=null;
	private String login_ip="";
	private String remarks="";
	private Timestamp logout_time=null;
	
	public AllLoginHistoryBean()
	{
		
	}
	public AllLoginHistoryBean(long login_index, int app_id, String user_id,Timestamp login_time,String login_ip)
	{
		this.login_hist_index=login_index;
		this.app_id=app_id;
		this.user_id=user_id;
		this.login_time=login_time;
		this.login_ip=login_ip;
	}
	public long getLogin_hist_index()
	{
		return login_hist_index;
	}
	public void setLogin_hist_index(long login_hist_index)
	{
		this.login_hist_index = login_hist_index;
	}
	public int getApp_id()
	{
		return app_id;
	}
	public void setApp_id(int app_id)
	{
		this.app_id = app_id;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	public Timestamp getLogin_time()
	{
		return login_time;
	}
	public void setLogin_time(Timestamp login_time)
	{
		this.login_time = login_time;
	}
	public String getLogin_ip()
	{
		return login_ip;
	}
	public void setLogin_ip(String login_ip)
	{
		this.login_ip = login_ip;
	}
	public Timestamp getLogout_time()
	{
		return logout_time;
	}
	public void setLogout_time(Timestamp logout_time)
	{
		this.logout_time = logout_time;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
}
