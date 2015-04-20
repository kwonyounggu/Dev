package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import com.sickkids.caliper.common.FormType;
import com.sickkids.caliper.common.OracleEventTable;

public class OracleCommonEventBean implements Serializable
{
	private static final long serialVersionUID = -160416254052381010L;
	private String hospital_id="";
	private int event_no=0;
	private int week_no=0;
	private FormType form_type;
	private OracleEventTable oracle_event_table;
	private String inputter="";
	private Timestamp entered_date=null;

	
	public OracleEventTable getOracle_event_table()
	{
		return oracle_event_table;
	}
	public void setOracle_event_table(OracleEventTable oracle_event_table)
	{
		this.oracle_event_table = oracle_event_table;
	}
	public String toString()
	{
		return hospital_id+", "+form_type.name()+", "+event_no+", "+week_no;
	}
	public FormType getForm_type()
	{
		return form_type;
	}
	public void setForm_type(FormType form_type)
	{
		this.form_type = form_type;
	}
	public String getHospital_id()
	{
		return hospital_id;
	}
	public void setHospital_id(String hospital_id)
	{
		this.hospital_id = hospital_id;
	}
	public int getEvent_no()
	{
		return event_no;
	}
	public void setEvent_no(int event_no)
	{
		this.event_no = event_no;
	}
	public int getWeek_no()
	{
		return week_no;
	}
	public void setWeek_no(int week_no)
	{
		this.week_no = week_no;
	}
	public String getInputter()
	{
		return inputter;
	}
	public void setInputter(String inputter)
	{
		this.inputter = inputter;
	}
	public Timestamp getEntered_date()
	{
		return entered_date;
	}
	public void setEntered_date(Timestamp entered_date)
	{
		this.entered_date = entered_date;
	}
}
