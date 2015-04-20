package com.sickkids.caliper.dao;

import java.io.Serializable;

public class InstrumentBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -335978508721107509L;
	
	private int instrument_name_id = 0;
	private String instrument_name = "";
	private String instrument_link = "";
	
	public int getInstrument_name_id()
	{
		return instrument_name_id;
	}
	public void setInstrument_name_id(int instrument_name_id)
	{
		this.instrument_name_id = instrument_name_id;
	}
	public String getInstrument_name()
	{
		return instrument_name;
	}
	public void setInstrument_name(String instrument_name)
	{
		this.instrument_name = instrument_name;
	}
	public String getInstrument_link()
	{
		return instrument_link;
	}
	public void setInstrument_link(String instrument_link)
	{
		this.instrument_link = instrument_link;
	}
}
