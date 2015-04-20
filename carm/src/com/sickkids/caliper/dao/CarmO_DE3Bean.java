package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmO_DE3Bean implements Serializable
{		
	private static final long serialVersionUID = 7513189941307466632L;
	
	private int DE3_ID=0;
	private String SITE="";
	private String PAT_NO="";
	private int EVENT_NO=0;
	private int WEEK_NO=0;
	

	private int hour=-10000;
	private String route="";
	private String medication="";

	private String enteredBy="";
	private Timestamp updatedOn=null;
	public int getDE3_ID()
	{
		return DE3_ID;
	}
	public void setDE3_ID(int dE3_ID)
	{
		DE3_ID = dE3_ID;
	}
	public String getSITE()
	{
		return SITE;
	}
	public void setSITE(String sITE)
	{
		SITE = sITE;
	}
	public String getPAT_NO()
	{
		return PAT_NO;
	}
	public void setPAT_NO(String pAT_NO)
	{
		PAT_NO = pAT_NO;
	}
	public int getEVENT_NO()
	{
		return EVENT_NO;
	}
	public void setEVENT_NO(int eVENT_NO)
	{
		EVENT_NO = eVENT_NO;
	}
	public int getWEEK_NO()
	{
		return WEEK_NO;
	}
	public void setWEEK_NO(int wEEK_NO)
	{
		WEEK_NO = wEEK_NO;
	}
	public int getHour()
	{
		return hour;
	}
	public void setHour(int hour)
	{
		this.hour = hour;
	}
	public String getRoute()
	{
		return route;
	}
	public void setRoute(String route)
	{
		this.route = route;
	}
	public String getMedication()
	{
		return medication;
	}
	public void setMedication(String medication)
	{
		this.medication = medication;
	}
	public String getEnteredBy()
	{
		return enteredBy;
	}
	public void setEnteredBy(String enteredBy)
	{
		this.enteredBy = enteredBy;
	}
	public Timestamp getUpdatedOn()
	{
		return updatedOn;
	}
	public void setUpdatedOn(Timestamp updatedOn)
	{
		this.updatedOn = updatedOn;
	}
}
