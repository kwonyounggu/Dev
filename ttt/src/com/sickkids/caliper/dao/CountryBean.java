package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CountryBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7015953661855061123L;
	private int countryId=0;
	private String country="";
	
	public int getCountryId()
	{
		return countryId;
	}
	public void setCountryId(int countryId)
	{
		this.countryId = countryId;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	
}
