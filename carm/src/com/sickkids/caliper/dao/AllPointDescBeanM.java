package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class AllPointDescBeanM implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8490543467748833862L;
	private int appId=0;
	private String itemHierachy="";
	private int pointId=0;
	private String pointName="";
	private String pointExplanation="";
	private String pointValueString="";
	private double pointValueNumber=0;
	private double pointValueMin=0;
	private double pointValueMax=0;
	private String pointValueUnits="";
	private String pointValueOptions="";
	private int confidenceLevel=0;
	private Timestamp creationTime=null;
	private String creatorId="";
	private String remarks="";
	public int getAppId()
	{
		return appId;
	}
	public void setAppId(int appId)
	{
		this.appId = appId;
	}
	public String getItemHierachy()
	{
		return itemHierachy;
	}
	public void setItemHierachy(String itemHierachy)
	{
		this.itemHierachy = itemHierachy;
	}
	public int getPointId()
	{
		return pointId;
	}
	public void setPointId(int pointId)
	{
		this.pointId = pointId;
	}
	public String getPointName()
	{
		return pointName;
	}
	public void setPointName(String pointName)
	{
		this.pointName = pointName;
	}
	public String getPointExplanation()
	{
		return pointExplanation;
	}
	public void setPointExplanation(String pointExplanation)
	{
		this.pointExplanation = pointExplanation;
	}
	public String getPointValueString()
	{
		return pointValueString;
	}
	public void setPointValueString(String pointValueString)
	{
		this.pointValueString = pointValueString;
	}
	public double getPointValueNumber()
	{
		return pointValueNumber;
	}
	public void setPointValueNumber(double pointValueNumber)
	{
		this.pointValueNumber = pointValueNumber;
	}
	public double getPointValueMin()
	{
		return pointValueMin;
	}
	public void setPointValueMin(double pointValueMin)
	{
		this.pointValueMin = pointValueMin;
	}
	public double getPointValueMax()
	{
		return pointValueMax;
	}
	public void setPointValueMax(double pointValueMax)
	{
		this.pointValueMax = pointValueMax;
	}
	public String getPointValueUnits()
	{
		return pointValueUnits;
	}
	public void setPointValueUnits(String pointValueUnits)
	{
		this.pointValueUnits = pointValueUnits;
	}
	public String getPointValueOptions()
	{
		return pointValueOptions;
	}
	public void setPointValueOptions(String pointValueOptions)
	{
		this.pointValueOptions = pointValueOptions;
	}
	public int getConfidenceLevel()
	{
		return confidenceLevel;
	}
	public void setConfidenceLevel(int confidenceLevel)
	{
		this.confidenceLevel = confidenceLevel;
	}
	public Timestamp getCreationTime()
	{
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime)
	{
		this.creationTime = creationTime;
	}
	public String getCreatorId()
	{
		return creatorId;
	}
	public void setCreatorId(String creatorId)
	{
		this.creatorId = creatorId;
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
