package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class AllPointDescBean implements Serializable
{	
	private static final long serialVersionUID = -3145787700216533815L;
	
	private int app_id=0;
	private String item_hierachy="";
	private int point_id=0;
	private String point_name="";
	private String point_explanation="";
	private String point_value_string="";
	private double point_value_number=0;
	private double point_value_min=0;
	private double point_value_max=0;
	private String point_value_units="";
	private String point_value_options="";
	private int confidence_level=0;
	private Timestamp creation_time=null;
	private String creator_id="";
	private String remarks="";
	
	public int getApp_id()
	{
		return app_id;
	}
	public void setApp_id(int app_id)
	{
		this.app_id = app_id;
	}
	public String getItem_hierachy()
	{
		return item_hierachy;
	}
	public void setItem_hierachy(String item_hierachy)
	{
		this.item_hierachy = item_hierachy;
	}
	public int getPoint_id()
	{
		return point_id;
	}
	public void setPoint_id(int point_id)
	{
		this.point_id = point_id;
	}
	public String getPoint_name()
	{
		return point_name;
	}
	public void setPoint_name(String point_name)
	{
		this.point_name = point_name;
	}
	public String getPoint_explanation()
	{
		return point_explanation;
	}
	public void setPoint_explanation(String point_explanation)
	{
		this.point_explanation = point_explanation;
	}
	public String getPoint_value_string()
	{
		return point_value_string;
	}
	public void setPoint_value_string(String point_value_string)
	{
		this.point_value_string = point_value_string;
	}
	public double getPoint_value_number()
	{
		return point_value_number;
	}
	public void setPoint_value_number(double point_value_number)
	{
		this.point_value_number = point_value_number;
	}
	public double getPoint_value_min()
	{
		return point_value_min;
	}
	public void setPoint_value_min(double point_value_min)
	{
		this.point_value_min = point_value_min;
	}
	public double getPoint_value_max()
	{
		return point_value_max;
	}
	public void setPoint_value_max(double point_value_max)
	{
		this.point_value_max = point_value_max;
	}
	public String getPoint_value_units()
	{
		return point_value_units;
	}
	public void setPoint_value_units(String point_value_units)
	{
		this.point_value_units = point_value_units;
	}
	public String getPoint_value_options()
	{
		return point_value_options;
	}
	public void setPoint_value_options(String point_value_options)
	{
		this.point_value_options = point_value_options;
	}
	public int getConfidence_level()
	{
		return confidence_level;
	}
	public void setConfidence_level(int confidence_level)
	{
		this.confidence_level = confidence_level;
	}
	public Timestamp getCreation_time()
	{
		return creation_time;
	}
	public void setCreation_time(Timestamp creation_time)
	{
		this.creation_time = creation_time;
	}
	public String getCreator_id()
	{
		return creator_id;
	}
	public void setCreator_id(String creator_id)
	{
		this.creator_id = creator_id;
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
