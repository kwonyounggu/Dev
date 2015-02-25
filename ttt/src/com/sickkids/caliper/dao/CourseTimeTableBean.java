package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CourseTimeTableBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4824252711860121888L;
	
	private int time_table_id=-1;
	private int course_number=-1;
	private Timestamp start_time=null;
	private Timestamp end_time=null;
	private int duration=-1;
	private String email_alert_to="";
	private String hist_record_path="";
	private String course_status="";
	private String subcontents="";
	private String submitter_id="";
	private Timestamp submission_time=null;
	private String remarks="";
	private boolean valid=true;
	public int getTime_table_id()
	{
		return time_table_id;
	}
	public void setTime_table_id(int time_table_id)
	{
		this.time_table_id = time_table_id;
	}
	public int getCourse_number()
	{
		return course_number;
	}
	public void setCourse_number(int course_number)
	{
		this.course_number = course_number;
	}
	public Timestamp getStart_time()
	{
		return start_time;
	}
	public void setStart_time(Timestamp start_time)
	{
		this.start_time = start_time;
	}
	public Timestamp getEnd_time()
	{
		return end_time;
	}
	public void setEnd_time(Timestamp end_time)
	{
		this.end_time = end_time;
	}
	public int getDuration()
	{
		return duration;
	}
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	public String getEmail_alert_to()
	{
		return email_alert_to;
	}
	public void setEmail_alert_to(String email_alert_to)
	{
		this.email_alert_to = email_alert_to;
	}
	public String getHist_record_path()
	{
		return hist_record_path;
	}
	public void setHist_record_path(String hist_record_path)
	{
		this.hist_record_path = hist_record_path;
	}
	public String getCourse_status()
	{
		return course_status;
	}
	public void setCourse_status(String course_status)
	{
		this.course_status = course_status;
	}
	public String getSubcontents()
	{
		return subcontents;
	}
	public void setSubcontents(String subcontents)
	{
		this.subcontents = subcontents;
	}
	public String getSubmitter_id()
	{
		return submitter_id;
	}
	public void setSubmitter_id(String submitter_id)
	{
		this.submitter_id = submitter_id;
	}
	public Timestamp getSubmission_time()
	{
		return submission_time;
	}
	public void setSubmission_time(Timestamp submission_time)
	{
		this.submission_time = submission_time;
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
}
