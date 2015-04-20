package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class RawDataVersionNBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5764445842181448420L;
	
	private long whole_record_index=0L;
	private long participant_id=0L;
	private short gender=0;
	private double age=0.0d;
	private double si_unit_data=0.0d;
	private double conventional_unit_data=0.0d;
	private String si_unit="";
	private String conventional_unit="";
	private short ethnic_group=0;
	private short instrument_name_id=0;
	private int analyte_name_id=0;
	private short version=0;
	private Timestamp insertion_time=null;
	
	public long getWhole_record_index()
	{
		return whole_record_index;
	}
	public void setWhole_record_index(long whole_record_index)
	{
		this.whole_record_index = whole_record_index;
	}
	public long getParticipant_id()
	{
		return participant_id;
	}
	public void setParticipant_id(long participant_id)
	{
		this.participant_id = participant_id;
	}
	public short getGender()
	{
		return gender;
	}
	public void setGender(short gender)
	{
		this.gender = gender;
	}
	public double getAge()
	{
		return age;
	}
	public void setAge(double age)
	{
		this.age = age;
	}
	public double getSi_unit_data()
	{
		return si_unit_data;
	}
	public void setSi_unit_data(double si_unit_data)
	{
		this.si_unit_data = si_unit_data;
	}
	public double getConventional_unit_data()
	{
		return conventional_unit_data;
	}
	public void setConventional_unit_data(double conventional_unit_data)
	{
		this.conventional_unit_data = conventional_unit_data;
	}
	public String getSi_unit()
	{
		return si_unit;
	}
	public void setSi_unit(String si_unit)
	{
		this.si_unit = si_unit;
	}
	public String getConventional_unit()
	{
		return conventional_unit;
	}
	public void setConventional_unit(String conventional_unit)
	{
		this.conventional_unit = conventional_unit;
	}
	public short getEthnic_group()
	{
		return ethnic_group;
	}
	public void setEthnic_group(short ethnic_group)
	{
		this.ethnic_group = ethnic_group;
	}
	public short getInstrument_name_id()
	{
		return instrument_name_id;
	}
	public void setInstrument_name_id(short instrument_name_id)
	{
		this.instrument_name_id = instrument_name_id;
	}
	public int getAnalyte_name_id()
	{
		return analyte_name_id;
	}
	public void setAnalyte_name_id(int analyte_name_id)
	{
		this.analyte_name_id = analyte_name_id;
	}
	public short getVersion()
	{
		return version;
	}
	public void setVersion(short version)
	{
		this.version = version;
	}
	public Timestamp getInsertion_time()
	{
		return insertion_time;
	}
	public void setInsertion_time(Timestamp insertion_time)
	{
		this.insertion_time = insertion_time;
	}

}
