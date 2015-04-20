package com.sickkids.caliper.dao;

import java.io.Serializable;

public class LodBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String assay_name="";
	private double lod=0.0d;
	public String getAssay_name()
	{
		return assay_name;
	}
	public void setAssay_name(String assay_name)
	{
		this.assay_name = assay_name;
	}
	public double getLod()
	{
		return lod;
	}
	public void setLod(double lod)
	{
		this.lod = lod;
	}
	
}
