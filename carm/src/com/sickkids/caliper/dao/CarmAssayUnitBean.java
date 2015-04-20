package com.sickkids.caliper.dao;

import java.io.Serializable;

public class CarmAssayUnitBean implements Serializable
{
	private static final long serialVersionUID = 2334684847738313357L;
	private String site_id="";
	private String assay_name="";
	private String assay_unit="";
	
	public String getSite_id()
	{
		return site_id;
	}
	public void setSite_id(String site_id)
	{
		this.site_id = site_id;
	}
	public String getAssay_name()
	{
		return assay_name;
	}
	public void setAssay_name(String assay_name)
	{
		this.assay_name = assay_name;
	}
	public String getAssay_unit()
	{
		return assay_unit;
	}
	public void setAssay_unit(String assay_unit)
	{
		this.assay_unit = assay_unit;
	}
}
