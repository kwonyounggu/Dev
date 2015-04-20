package com.sickkids.caliper.dao;

import java.io.Serializable;

public class AnalyteNameBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2163375666080040725L;
	
	private int analyte_name_id=0;
	private String analyte_name="";
	private int instrument_name_id=0;
	private int metric_system=0;
	private String value_unit="";//(mg)
	private String v2_chart_path="";
	private String v3_chart_path="";
	private String v4_chart_path="";
	private String v5_chart_path="";
	public int getAnalyte_name_id()
	{
		return analyte_name_id;
	}
	public void setAnalyte_name_id(int analyte_name_id)
	{
		this.analyte_name_id = analyte_name_id;
	}
	public String getAnalyte_name()
	{
		return analyte_name;
	}
	public void setAnalyte_name(String analyte_name)
	{
		this.analyte_name = analyte_name;
	}
	public int getInstrument_name_id()
	{
		return instrument_name_id;
	}
	public void setInstrument_name_id(int instrument_name_id)
	{
		this.instrument_name_id = instrument_name_id;
	}
	public int getMetric_system()
	{
		return metric_system;
	}
	public void setMetric_system(int metric_system)
	{
		this.metric_system = metric_system;
	}
	public String getValue_unit()
	{
		return value_unit;
	}
	public void setValue_unit(String value_unit)
	{
		this.value_unit = value_unit;
	}
	public String getV2_chart_path()
	{
		return v2_chart_path;
	}
	public void setV2_chart_path(String v2_chart_path)
	{
		this.v2_chart_path = v2_chart_path;
	}
	public String getV3_chart_path()
	{
		return v3_chart_path;
	}
	public void setV3_chart_path(String v3_chart_path)
	{
		this.v3_chart_path = v3_chart_path;
	}
	public String getV4_chart_path()
	{
		return v4_chart_path;
	}
	public void setV4_chart_path(String v4_chart_path)
	{
		this.v4_chart_path = v4_chart_path;
	}
	public String getV5_chart_path()
	{
		return v5_chart_path;
	}
	public void setV5_chart_path(String v5_chart_path)
	{
		this.v5_chart_path = v5_chart_path;
	}
}
