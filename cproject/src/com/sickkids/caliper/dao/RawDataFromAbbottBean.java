package com.sickkids.caliper.dao;

import java.io.Serializable;

public class RawDataFromAbbottBean implements Serializable
{
	  private static final long serialVersionUID = 1L;
	  private String cp="";
	  private long sid=0L;
	  private String pid=""; 
	  private String name=""; 
	  private String gender=""; 
	  private String dob=""; 
	  private String assay=""; 
	  private int assay_number=-1; 
	  private String result="";
	  private String interpretation="";
	  private String instrument_response="";
	  private String normal_range="";
	  private String dilution=""; 
	  private String flags=""; 
	  private String codes=""; 
	  private String module_sn=""; 
	  private String completed_time="";
	  private String cal_time=""; 
	  private String operator_id=""; 
	  private String reagent_lot=""; 
	  private String reagent_sn=""; 
	  private String comment=""; 
	  private String doctor=""; 
	  private String draw_time=""; 
	  private String location=""; 
	  private String remarks="";
	  
	public String getCp()
	{
		return cp;
	}
	public void setCp(String cp)
	{
		this.cp = cp;
	}
	public long getSid()
	{
		return sid;
	}
	public void setSid(long sid)
	{
		this.sid = sid;
	}
	public String getPid()
	{
		return pid;
	}
	public void setPid(String pid)
	{
		this.pid = pid;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getDob()
	{
		return dob;
	}
	public void setDob(String dob)
	{
		this.dob = dob;
	}
	public String getAssay()
	{
		return assay;
	}
	public void setAssay(String assay)
	{
		this.assay = assay;
	}
	public int getAssay_number()
	{
		return assay_number;
	}
	public void setAssay_number(int assay_number)
	{
		this.assay_number = assay_number;
	}
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	public String getInterpretation()
	{
		return interpretation;
	}
	public void setInterpretation(String interpretation)
	{
		this.interpretation = interpretation;
	}
	public String getInstrument_response()
	{
		return instrument_response;
	}
	public void setInstrument_response(String instrument_response)
	{
		this.instrument_response = instrument_response;
	}
	public String getNormal_range()
	{
		return normal_range;
	}
	public void setNormal_range(String normal_range)
	{
		this.normal_range = normal_range;
	}
	public String getDilution()
	{
		return dilution;
	}
	public void setDilution(String dilution)
	{
		this.dilution = dilution;
	}
	public String getFlags()
	{
		return flags;
	}
	public void setFlags(String flags)
	{
		this.flags = flags;
	}
	public String getCodes()
	{
		return codes;
	}
	public void setCodes(String codes)
	{
		this.codes = codes;
	}
	public String getModule_sn()
	{
		return module_sn;
	}
	public void setModule_sn(String module_sn)
	{
		this.module_sn = module_sn;
	}
	public String getCompleted_time()
	{
		return completed_time;
	}
	public void setCompleted_time(String completed_time)
	{
		this.completed_time = completed_time;
	}
	public String getCal_time()
	{
		return cal_time;
	}
	public void setCal_time(String cal_time)
	{
		this.cal_time = cal_time;
	}
	public String getOperator_id()
	{
		return operator_id;
	}
	public void setOperator_id(String operator_id)
	{
		this.operator_id = operator_id;
	}
	public String getReagent_lot()
	{
		return reagent_lot;
	}
	public void setReagent_lot(String reagent_lot)
	{
		this.reagent_lot = reagent_lot;
	}
	public String getReagent_sn()
	{
		return reagent_sn;
	}
	public void setReagent_sn(String reagent_sn)
	{
		this.reagent_sn = reagent_sn;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	public String getDoctor()
	{
		return doctor;
	}
	public void setDoctor(String doctor)
	{
		this.doctor = doctor;
	}
	public String getDraw_time()
	{
		return draw_time;
	}
	public void setDraw_time(String draw_time)
	{
		this.draw_time = draw_time;
	}
	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location = location;
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
