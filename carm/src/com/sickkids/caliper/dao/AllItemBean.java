package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class AllItemBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5772833568926496609L;
	private int app_id=-1;
	private int item_id=-1;
	private String medical_case_link="";
	private String publication_link="";
	private String photograph_link="";
	private String other_link="";
	private Timestamp creation_time=null;
	private String creator_id="";
	
	public int getApp_id()
	{
		return app_id;
	}
	public void setApp_id(int app_id)
	{
		this.app_id = app_id;
	}
	public int getItem_id()
	{
		return item_id;
	}
	public void setItem_id(int item_id)
	{
		this.item_id = item_id;
	}
	public String getMedical_case_link()
	{
		return medical_case_link;
	}
	public void setMedical_case_link(String medical_case_link)
	{
		this.medical_case_link = medical_case_link;
	}
	public String getPublication_link()
	{
		return publication_link;
	}
	public void setPublication_link(String publication_link)
	{
		this.publication_link = publication_link;
	}
	public String getPhotograph_link()
	{
		return photograph_link;
	}
	public void setPhotograph_link(String photograph_link)
	{
		this.photograph_link = photograph_link;
	}
	public String getOther_link()
	{
		return other_link;
	}
	public void setOther_link(String other_link)
	{
		this.other_link = other_link;
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
	public String getInsertStmt()
	{
		return "insert into all_item values("+
				   " "+ app_id+", "+
				   " "+item_id+", "+
				   "'"+medical_case_link+"', "+
				   "'"+publication_link+"', "+
				   "'"+photograph_link+"', "+
				   "'"+other_link+"', "+
				   "'"+creation_time+"', "+
				   "'"+creator_id+"')";
	}
	public String getUpdateStmt()
	{
		return "update all_item set "+
					"medical_case_link='"+medical_case_link+"', "+
					"publication_link='"+publication_link+"', "+
					"photograph_link='"+photograph_link+"', "+
					"other_link='"+other_link+"' "+
					"where app_id="+app_id+" and item_id="+item_id;
					
	}
	
}
