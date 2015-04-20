package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class OnlineRegistrationBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long registration_id=0L;
	private String name="";//255 length
	private int sex=0;//1:male, 2:female
	private String email="";//255 length
	private String phone="123-123-1234";//15 length
	private String school="";//255 length
	private Timestamp registration_date=null;
	private String remarks="";
	
	public OnlineRegistrationBean(){}
	public OnlineRegistrationBean(long registration_id, String name, int sex, String email, String phone, String school, Timestamp registration_date, String remarks)
	{
		this.registration_id=registration_id;
		this.name=name;
		this.sex=sex;
		this.email=email;
		this.phone=phone;
		this.school=school;
		this.registration_date=registration_date;
		this.remarks=remarks;
	}
	public Timestamp getRegistration_date()
	{
		return registration_date;
	}
	public void setRegistration_date(Timestamp registration_date)
	{
		this.registration_date = registration_date;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public long getRegistration_id()
	{
		return registration_id;
	}
	public void setRegistration_id(long registration_id)
	{
		this.registration_id = registration_id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getSex()
	{
		return sex;
	}
	public void setSex(int sex)
	{
		this.sex = sex;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getSchool()
	{
		return school;
	}
	public void setSchool(String school)
	{
		this.school = school;
	}
	
	
	
}
