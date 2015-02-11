package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringEscapeUtils;

public class HospitalBean implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5560819731919898514L;
	private String hospitalId="00";
	private String hospitalName="";
	private String country="";
	private String provState="";
	private String city="";
	private String address="";
	private String postalCode="";
	private String phone="";
	private String description="";
	private String  creatorId=null;
	private Timestamp creationTime=null;
	private String remarks="";
	private boolean valid=true;
	
	
	public String getHospitalId()
	{
		return hospitalId;
	}
	public void setHospitalId(String hospitalId)
	{
		this.hospitalId = hospitalId;
	}
	public String getHospitalName()
	{
		return hospitalName;
	}
	public void setHospitalName(String hospitalName)
	{
		this.hospitalName = hospitalName;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	public String getProvState()
	{
		return provState;
	}
	public void setProvState(String provState)
	{
		this.provState = provState;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getPostalCode()
	{
		return postalCode;
	}
	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getCreatorId()
	{
		return creatorId;
	}
	public void setCreatorId(String creatorId)
	{
		this.creatorId = creatorId;
	}
	public Timestamp getCreationTime()
	{
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime)
	{
		this.creationTime = creationTime;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public boolean isValid()
	{
		return valid;
	}
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}
	public String getInsertStmt()
	{
		return "insert into hospital values("+
				   "'"+hospitalId+"', "+
				   "'"+StringEscapeUtils.escapeSql(hospitalName)+"', "+
				   "'"+country+"', "+
				   "'"+provState+"', "+
				   "'"+city+"', "+
				   "'"+address+"', "+
				   "'"+postalCode+"', "+
				   "'"+phone+"', "+
				   "'"+description+"', "+
				   "'"+creatorId+"', "+
				   "'"+creationTime+"', "+
				   "'"+remarks+"', "+
				   ""+valid+")";
	}
	public String getUpdateStmt()
	{
		return "update hospital set "+
					"hospital_name='"+StringEscapeUtils.escapeSql(hospitalName)+"', "+
					"country='"+country+"', "+
					"phone='"+phone+"', "+
					"remarks='"+remarks+"', "+
					"valid="+valid+" "+
					"where hospital_id='"+hospitalId+"'";
					
	}
}
