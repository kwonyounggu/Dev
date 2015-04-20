package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmO_DE6Bean implements Serializable
{		
	
	private static final long serialVersionUID = -6883603471173196260L;
	
	private int DE6_ID=0;
	private String SITE="";
	private String PAT_NO="";
	private int EVENT_NO=0;
	private int WEEK_NO=0;
	

	private String chestImaging="";
	private String cnsImaging="";
	private String abdominalImaging="";
	private String otherImaging="";

	private String enteredBy="";
	private Timestamp updatedOn=null;
	public int getDE6_ID()
	{
		return DE6_ID;
	}
	public void setDE6_ID(int dE6_ID)
	{
		DE6_ID = dE6_ID;
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
	public String getChestImaging()
	{
		return chestImaging;
	}
	public void setChestImaging(String chestImaging)
	{
		this.chestImaging = chestImaging;
	}
	public String getCnsImaging()
	{
		return cnsImaging;
	}
	public void setCnsImaging(String cnsImaging)
	{
		this.cnsImaging = cnsImaging;
	}
	public String getAbdominalImaging()
	{
		return abdominalImaging;
	}
	public void setAbdominalImaging(String abdominalImaging)
	{
		this.abdominalImaging = abdominalImaging;
	}
	public String getOtherImaging()
	{
		return otherImaging;
	}
	public void setOtherImaging(String otherImaging)
	{
		this.otherImaging = otherImaging;
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
