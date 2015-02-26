package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CourseTimeTableBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4824252711860121888L;
	
	private int timeTableId=-1;
	private int courseNumber=-1;
	private Timestamp startTime=null;
	private Timestamp endTime=null;
	private String duration="";//0.5, 1, 1.5
	private String emailAlertTo="";//younggu.kwon@sickkids.ca at 2012-12-12 00:00:00
	private String histRecordPath="";
	private String sessionStatus="";
	private String sessionDescription="";
	private String submitterId="";
	private Timestamp submissionTime=null;
	private String remarks="";
	private boolean valid=true;
	private String fileIds="";//multiple file ids, commma by comma...
	
	private String courseName="";
	
	public int getTimeTableId()
	{
		return timeTableId;
	}
	public void setTimeTableId(int timeTableId)
	{
		this.timeTableId = timeTableId;
	}
	public int getCourseNumber()
	{
		return courseNumber;
	}
	public void setCourseNumber(int courseNumber)
	{
		this.courseNumber = courseNumber;
	}
	public Timestamp getStartTime()
	{
		return startTime;
	}
	public void setStartTime(Timestamp startTime)
	{
		this.startTime = startTime;
	}
	public Timestamp getEndTime()
	{
		return endTime;
	}
	public void setEndTime(Timestamp endTime)
	{
		this.endTime = endTime;
	}
	public String getDuration()
	{
		return duration;
	}
	public void setDuration(String duration)
	{
		this.duration = duration;
	}
	public String getEmailAlertTo()
	{
		return emailAlertTo;
	}
	public void setEmailAlertTo(String emailAlertTo)
	{
		this.emailAlertTo = emailAlertTo;
	}
	public String getHistRecordPath()
	{
		return histRecordPath;
	}
	public void setHistRecordPath(String histRecordPath)
	{
		this.histRecordPath = histRecordPath;
	}
	public String getSessionStatus()
	{
		return sessionStatus;
	}
	public void setSessionStatus(String sessionStatus)
	{
		this.sessionStatus = sessionStatus;
	}
	public String getSessionDescription()
	{
		return sessionDescription;
	}
	public void setSessionDescription(String sessionDescription)
	{
		this.sessionDescription = sessionDescription;
	}
	public String getSubmitterId()
	{
		return submitterId;
	}
	public void setSubmitterId(String submitterId)
	{
		this.submitterId = submitterId;
	}
	public Timestamp getSubmissionTime()
	{
		return submissionTime;
	}
	public void setSubmissionTime(Timestamp submissionTime)
	{
		this.submissionTime = submissionTime;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public boolean isValid()
	{
		return valid;
	}
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}
	public String getFileIds()
	{
		return fileIds;
	}
	public void setFileIds(String fileIds)
	{
		this.fileIds = fileIds;
	}
	public String getCourseName()
	{
		return courseName;
	}
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	
	

}
