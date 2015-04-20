package com.sickkids.caliper.dao;

import java.io.Serializable;

public class StdReferenceIntervalBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -357448526864779404L;
	private long whole_record_index = 0L;
	private int analyte_name_id = 0;
	private short instrument_name_id = 0;
	private short gender = 0;
	private String age_range = "";
	private String ri_lower_limit = "";
	private String ri_upper_limit = "";
	private int samples = 0;
	private String ci_lower_limit = "";
	private String ci_upper_limit = "";
	private int metric_system = 0;
	private String unit_value = "";
	private short version = 0;
	
	public long getWhole_record_index()
	{
		return whole_record_index;
	}
	public void setWhole_record_index(long whole_record_index)
	{
		this.whole_record_index = whole_record_index;
	}
	public int getAnalyte_name_id()
	{
		return analyte_name_id;
	}
	public void setAnalyte_name_id(int analyte_name_id)
	{
		this.analyte_name_id = analyte_name_id;
	}
	public short getInstrument_name_id()
	{
		return instrument_name_id;
	}
	public void setInstrument_name_id(short instrument_name_id)
	{
		this.instrument_name_id = instrument_name_id;
	}
	public short getGender()
	{
		return gender;
	}
	public void setGender(short gender)
	{
		this.gender = gender;
	}
	public String getAge_range()
	{
		return age_range;
	}
	public void setAge_range(String age_range)
	{
		this.age_range = age_range;
	}
	public String getRi_lower_limit()
	{
		return ri_lower_limit;
	}
	public void setRi_lower_limit(String ri_lower_limit)
	{
		this.ri_lower_limit = ri_lower_limit;
	}
	public String getRi_upper_limit()
	{
		return ri_upper_limit;
	}
	public void setRi_upper_limit(String ri_upper_limit)
	{
		this.ri_upper_limit = ri_upper_limit;
	}
	public int getSamples()
	{
		return samples;
	}
	public void setSamples(int samples)
	{
		this.samples = samples;
	}
	public String getCi_lower_limit()
	{
		return ci_lower_limit;
	}
	public void setCi_lower_limit(String ci_lower_limit)
	{
		this.ci_lower_limit = ci_lower_limit;
	}
	public String getCi_upper_limit()
	{
		return ci_upper_limit;
	}
	public void setCi_upper_limit(String ci_upper_limit)
	{
		this.ci_upper_limit = ci_upper_limit;
	}
	public int getMetric_system()
	{
		return metric_system;
	}
	public void setMetric_system(int metric_system)
	{
		this.metric_system = metric_system;
	}
	public String getUnit_value()
	{
		return unit_value;
	}
	public void setUnit_value(String unit_value)
	{
		this.unit_value = unit_value;
	}
	public short getVersion()
	{
		return version;
	}
	public void setVersion(short version)
	{
		this.version = version;
	}

}
