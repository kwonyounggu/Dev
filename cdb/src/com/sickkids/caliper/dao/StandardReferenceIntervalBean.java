package com.sickkids.caliper.dao;

import java.io.Serializable;

public class StandardReferenceIntervalBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int analyte_index=-1;
	private String analyte="";
	private String age_range="";
	private String female_reference_interval="";
	private int female_number=0;
	private String male_reference_interval="";
	private int male_number=0;
	private String category="";
	private String munit="";
	public int getAnalyte_index()
	{
		return analyte_index;
	}
	public void setAnalyte_index(int analyte_index)
	{
		this.analyte_index = analyte_index;
	}
	public String getAnalyte()
	{
		return analyte;
	}
	public void setAnalyte(String analyte)
	{
		this.analyte = analyte;
	}
	public String getAge_range()
	{
		return age_range;
	}
	public void setAge_range(String age_range)
	{
		this.age_range = age_range;
	}
	public String getFemale_reference_interval()
	{
		return female_reference_interval;
	}
	public void setFemale_reference_interval(String female_reference_interval)
	{
		this.female_reference_interval = female_reference_interval;
	}
	public int getFemale_number()
	{
		return female_number;
	}
	public void setFemale_number(int female_number)
	{
		this.female_number = female_number;
	}
	public String getMale_reference_interval()
	{
		return male_reference_interval;
	}
	public void setMale_reference_interval(String male_reference_interval)
	{
		this.male_reference_interval = male_reference_interval;
	}
	public int getMale_number()
	{
		return male_number;
	}
	public void setMale_number(int male_number)
	{
		this.male_number = male_number;
	}
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public String getMunit()
	{
		return munit;
	}
	public void setMunit(String munit)
	{
		this.munit = munit;
	}

}
