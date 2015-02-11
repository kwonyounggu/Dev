package org.red5.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7015953661855061123L;
	private String user_id="";
	private String hospital_id="";
	private String hospital_name="";
	private String room_id="";
	private String room_name="";
	private String first_name="";
	private String last_name="";
	private String email="";
	private boolean is_owner=false;
	private String owner_password="";
	private Timestamp creation_date=null;
	private boolean is_active=false;
	private boolean is_login=false;
	private int login_level = 2; //2: Dr, 3: RN, 4: User
	private String ip_address = "0.0.0.0";
	
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
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	public String getRoom_id()
	{
		return room_id;
	}
	public void setRoom_id(String room_id)
	{
		this.room_id = room_id;
	}
	public String getRoom_name()
	{
		return room_name;
	}
	public void setRoom_name(String room_name)
	{
		this.room_name = room_name;
	}
	public String getHospital_id()
	{
		return hospital_id;
	}
	public void setHospital_id(String hospital_id)
	{
		this.hospital_id = hospital_id;
	}
	public String getHospital_name()
	{
		return hospital_name;
	}
	public void setHospital_name(String hospital_name)
	{
		this.hospital_name = hospital_name;
	}
	public int getLogin_level()
	{
		return login_level;
	}
	public void setLogin_level(int login_level)
	{
		this.login_level = login_level;
	}
	public String getIp_address()
	{
		return ip_address;
	}
	public void setIp_address(String ip_address)
	{
		this.ip_address = ip_address;
	}
}
