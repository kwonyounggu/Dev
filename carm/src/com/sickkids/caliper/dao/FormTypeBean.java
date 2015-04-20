package com.sickkids.caliper.dao;

import java.io.Serializable;

public class FormTypeBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8101931744802049333L;

	private String formType="";
	private String formName="";
	private String category="";
	public String getFormType()
	{
		return formType;
	}
	public void setFormType(String formType)
	{
		this.formType = formType;
	}
	public String getFormName()
	{
		return formName;
	}
	public void setFormName(String formName)
	{
		this.formName = formName;
	}
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	
}
