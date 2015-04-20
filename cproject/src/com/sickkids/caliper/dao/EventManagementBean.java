package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import com.sickkids.caliper.common.Utils;

public class EventManagementBean implements Serializable
{
	
	private static final long serialVersionUID = -937761053770944669L;
	private long event_index=0L;
	private String admin_id="";
	private String event_subject="";
	private String event_summary="";
	private Timestamp event_date=null;
	private String event_location="";
	private String event_file_location="";
	private Timestamp written_date=null;
	private int event_alive=1;//0: dead, 1: alive
	private String remarks="";
	private String pdf_file="";
	
	public String getShortEventTitle()
	{
		if(event_subject.length()>30) return event_subject.substring(0, 30)+"...";
		return event_subject;
	}
	public String getShortEventSummary()
	{
		if(event_summary.length()>100) return event_summary.substring(0, 100)+"...";
		return event_summary;
	}
	public String getShortEventLocation()
	{
		if(event_location.length()>25) return event_location.substring(0, 25)+"...";
		return event_location;
	}
	public String getPdf_file()
	{
		return pdf_file;
	}
	public void setPdf_file(String pdf_file)
	{
		this.pdf_file = pdf_file;
	}
	public void setParams(String formField, String formValue)
	{
		System.err.println(formField+", "+formValue);
		if(formField.equals("submitter_id")) this.admin_id=formValue;
		else if(formField.equals("event_title")) this.event_subject=formValue;
		else if(formField.equals("event_location")) this.event_location=formValue;
		else if(formField.equals("event_summary")) this.event_summary=formValue;
		//ERROR as in "Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]"
		//	can be fixed in the following way. (Jan 08, 2012)
		else if(formField.equals("event_date"))
		{
			if(!formValue.equals(""))
			{	String temp[]=formValue.split("/");
				this.event_date=Timestamp.valueOf(temp[2]+"-"+temp[0]+"-"+temp[1]+" 00:00:00");
			}
			else this.event_date=Timestamp.valueOf("1970-11-11 11:11:11");//default	
		}
		//To fix the following error message, do the below compound statement. (Jan 08, 2012)
		//uploaded_files\pdfs\events\G:\MyDoc\csr\joomla_pdf\01_sickkids\events_20120108110913.pdf (The filename, directory name, or volume label syntax is incorrect)
		else if(formField.equals("pdf_file"))
		{
			if(formValue.indexOf("\\")!=-1 || formValue.indexOf(":")!=-1) //found from IE
			{
				String temp[]=formValue.split("\\\\");
				this.pdf_file=temp[temp.length-1];
			}
			else this.pdf_file=formValue;
		}
	}
	public String toString()
	{
		return "admin_id="+admin_id+", title="+event_subject+", event_location="+event_location+", event_date="+event_date+",\nsummary="+event_summary;
	}
	public long getEvent_index()
	{
		return event_index;
	}
	public void setEvent_index(long event_index)
	{
		this.event_index = event_index;
	}
	public String getAdmin_id()
	{
		return admin_id;
	}
	public void setAdmin_id(String admin_id)
	{
		this.admin_id = admin_id;
	}
	public String getEvent_subject()
	{
		return event_subject;
	}
	public void setEvent_subject(String event_subject)
	{
		this.event_subject = event_subject;
	}
	public String getEvent_summary()
	{
		return event_summary;
	}
	public void setEvent_summary(String event_summary)
	{
		this.event_summary = event_summary;
	}
	public Timestamp getEvent_date()
	{
		return event_date;
	}
	public void setEvent_date(Timestamp event_date)
	{
		this.event_date = event_date;
	}
	public String getEvent_location()
	{
		return event_location;
	}
	public void setEvent_location(String event_location)
	{
		this.event_location = event_location;
	}
	public String getEvent_file_location()
	{
		return event_file_location;
	}
	public void setEvent_file_location(String event_file_location)
	{
		this.event_file_location = event_file_location;
	}
	public Timestamp getWritten_date()
	{
		return written_date;
	}
	public void setWritten_date(Timestamp written_date)
	{
		this.written_date = written_date;
	}
	public int getEvent_alive()
	{
		return event_alive;
	}
	public void setEvent_alive(int event_alive)
	{
		this.event_alive = event_alive;
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
