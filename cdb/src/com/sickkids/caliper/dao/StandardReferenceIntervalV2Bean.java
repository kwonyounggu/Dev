package com.sickkids.caliper.dao;

import java.io.Serializable;

public class StandardReferenceIntervalV2Bean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int analyte_index=-1;
	private String analyte="";
	private String age_range="";
	private String female_ri_lower_limit="";
	private String female_ri_upper_limit="";
	private int female_samples=0;
	private String female_ci_lower_limit="";
	private String female_ci_uppper_limit="";
	private String male_ri_lower_limit="";
	private String male_ri_upper_limit="";
	private int male_samples=0;
	private String male_ci_lower_limit="";
	private String male_ci_uppper_limit="";
	private String matric_system="";
	private String unit="";
	
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
	public String getFemale_ri_lower_limit()
	{
		return female_ri_lower_limit;
	}
	public void setFemale_ri_lower_limit(String female_ri_lower_limit)
	{
		this.female_ri_lower_limit = female_ri_lower_limit;
	}

	public int getFemale_samples()
	{
		return female_samples;
	}
	public void setFemale_samples(int female_samples)
	{
		this.female_samples = female_samples;
	}
	public String getFemale_ci_lower_limit()
	{
		return female_ci_lower_limit;
	}
	public void setFemale_ci_lower_limit(String female_ci_lower_limit)
	{
		this.female_ci_lower_limit = female_ci_lower_limit;
	}
	public String getFemale_ci_uppper_limit()
	{
		return female_ci_uppper_limit;
	}
	public void setFemale_ci_uppper_limit(String female_ci_uppper_limit)
	{
		this.female_ci_uppper_limit = female_ci_uppper_limit;
	}
	public String getMale_ri_lower_limit()
	{
		return male_ri_lower_limit;
	}
	public void setMale_ri_lower_limit(String male_ri_lower_limit)
	{
		this.male_ri_lower_limit = male_ri_lower_limit;
	}
	public String getFemale_ri_upper_limit()
	{
		return female_ri_upper_limit;
	}
	public void setFemale_ri_upper_limit(String female_ri_upper_limit)
	{
		this.female_ri_upper_limit = female_ri_upper_limit;
	}
	public String getMale_ri_upper_limit()
	{
		return male_ri_upper_limit;
	}
	public void setMale_ri_upper_limit(String male_ri_upper_limit)
	{
		this.male_ri_upper_limit = male_ri_upper_limit;
	}
	public int getMale_samples()
	{
		return male_samples;
	}
	public void setMale_samples(int male_samples)
	{
		this.male_samples = male_samples;
	}
	public String getMale_ci_lower_limit()
	{
		return male_ci_lower_limit;
	}
	public void setMale_ci_lower_limit(String male_ci_lower_limit)
	{
		this.male_ci_lower_limit = male_ci_lower_limit;
	}
	public String getMale_ci_uppper_limit()
	{
		return male_ci_uppper_limit;
	}
	public void setMale_ci_uppper_limit(String male_ci_uppper_limit)
	{
		this.male_ci_uppper_limit = male_ci_uppper_limit;
	}
	public String getMatric_system()
	{
		return matric_system;
	}
	public void setMatric_system(String matric_system)
	{
		this.matric_system = matric_system;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
}
