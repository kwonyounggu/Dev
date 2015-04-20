package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class LoginHistoryBean implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4072282998564619133L;
	private long login_index=0L;
	private String person_id="";
	private Timestamp login_time=null;
	private String remarks="";
	
	public LoginHistoryBean()
	{
		
	}
	public LoginHistoryBean(long login_index,String person_id,Timestamp login_time,String remarks)
	{
		this.login_index=login_index;
		this.person_id=person_id;
		this.login_time=login_time;
		this.remarks=remarks;
	}
	public long getLogin_index() 
	{
		return login_index;
	}
	public void setLogin_index(long login_index) 
	{
		this.login_index = login_index;
	}
	public String getPerson_id() 
	{
		return person_id;
	}
	public void setPerson_id(String person_id) 
	{
		this.person_id = person_id;
	}
	public Timestamp getLogin_time() 
	{
		return login_time;
	}
	public void setLogin_time(Timestamp login_time) 
	{
		this.login_time = login_time;
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
