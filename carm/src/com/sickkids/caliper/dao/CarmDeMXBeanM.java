package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmDeMXBeanM implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5528310681183871295L;
	private int carmId=0;
	private String site="00";
	private String patNo="0000";
	private int eventNo=0;
	private String deIds="";
	private int de1Id=0;
	private int de2Id=0;
	private String de3Ids="";
	private int de4Id=0;
	private String de5Ids="";
	private int de6Id=0;
	private String remarks="";
	private Timestamp createdTime=null;
	private boolean flag=false;
	private String reviewStatus="not used";//This field is not used
	public int getCarmId()
	{
		return carmId;
	}
	public void setCarmId(int carmId)
	{
		this.carmId = carmId;
	}
	public String getSite()
	{
		return site;
	}
	public void setSite(String site)
	{
		this.site = site;
	}
	public String getPatNo()
	{
		return patNo;
	}
	public void setPatNo(String patNo)
	{
		this.patNo = patNo;
	}
	public int getEventNo()
	{
		return eventNo;
	}
	public void setEventNo(int eventNo)
	{
		this.eventNo = eventNo;
	}
	public String getDeIds()
	{
		return deIds;
	}
	public void setDeIds(String deIds)
	{
		this.deIds = deIds;
	}
	public int getDe1Id()
	{
		return de1Id;
	}
	public void setDe1Id(int de1Id)
	{
		this.de1Id = de1Id;
	}
	public int getDe2Id()
	{
		return de2Id;
	}
	public void setDe2Id(int de2Id)
	{
		this.de2Id = de2Id;
	}
	public String getDe3Ids()
	{
		return de3Ids;
	}
	public void setDe3Ids(String de3Ids)
	{
		this.de3Ids = de3Ids;
	}
	public int getDe4Id()
	{
		return de4Id;
	}
	public void setDe4Id(int de4Id)
	{
		this.de4Id = de4Id;
	}
	public String getDe5Ids()
	{
		return de5Ids;
	}
	public void setDe5Ids(String de5Ids)
	{
		this.de5Ids = de5Ids;
	}
	public int getDe6Id()
	{
		return de6Id;
	}
	public void setDe6Id(int de6Id)
	{
		this.de6Id = de6Id;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public Timestamp getCreatedTime()
	{
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime)
	{
		this.createdTime = createdTime;
	}
	public boolean isFlag()
	{
		return flag;
	}
	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}
	public String getReviewStatus()
	{
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus)
	{
		this.reviewStatus = reviewStatus;
	}
}
