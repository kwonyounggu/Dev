package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class UsersBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7015953661855061123L;
	private long user_id=0L;
	private long room_id=0L;
	private String first_name="";
	private String last_name="";
	private String email="";
	private boolean is_owner=false;
	private String owner_password="";
	private Timestamp creation_date=null;
	private boolean is_active=false;
	private boolean is_login=false;
	
	//private Object video=null;
	//private Object ns=null;
	public Timestamp getCreation_date()
	{
		return creation_date;
	}
	public void setCreation_date(Timestamp creation_date)
	{
		this.creation_date = creation_date;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getFirst_name()
	{
		return first_name;
	}
	public void setFirst_name(String first_name)
	{
		this.first_name = first_name;
	}
	public boolean isIs_active()
	{
		return is_active;
	}
	public void setIs_active(boolean is_active)
	{
		this.is_active = is_active;
	}
	public boolean isIs_login()
	{
		return is_login;
	}
	public void setIs_login(boolean is_login)
	{
		this.is_login = is_login;
	}
	public boolean isIs_owner()
	{
		return is_owner;
	}
	public void setIs_owner(boolean is_owner)
	{
		this.is_owner = is_owner;
	}
	public String getLast_name()
	{
		return last_name;
	}
	public void setLast_name(String last_name)
	{
		this.last_name = last_name;
	}
	public String getOwner_password()
	{
		return owner_password;
	}
	public void setOwner_password(String owner_password)
	{
		this.owner_password = owner_password;
	}
	public long getRoom_id()
	{
		return room_id;
	}
	public void setRoom_id(long room_id)
	{
		this.room_id = room_id;
	}
	public long getUser_id()
	{
		return user_id;
	}
	public void setUser_id(long user_id)
	{
		this.user_id = user_id;
	}
}
