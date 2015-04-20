package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmO_DEBean implements Serializable
{	
	private static final long serialVersionUID = -1917541516777232041L;
	
	private int DE_ID=0;
	private String SITE="";
	private String PAT_NO="";
	private int EVENT_NO=0;
	private int WEEK_NO=0;
	

	private int hrPreevent=-10000;
	private float heartRate=-10000;
	private String compression="";
	private float respRate=-10000;
	private float saturation=-10000;	
	private float systolicBp=-10000;
	private float diastolicBp=-10000;
	private int capillaryRefill=-10000;
	private float temperature=-10000;
	private String consciousnessAvpu="";	
	private float consciousnessGcs=-10000;
	private String pupils="";
	private String o2Method="";
	private String o2AmountUnit="";
	private float o2Amount=-10000;
	private String respEffort="";
	private String stridor="";
	private float totalIvFluid=-10000;
	private float urineOutput=-10000;	
	private String urineYesNo="";
	private String narcoticInfusion="";
	private String physicianReview="";
	private String statCall="";
	private String nursePatientRatio="";
	private String parentPresent="";
	
	private String enteredBy="";
	private Timestamp updatedOn=null;
		
	
	public int getDE_ID()
	{
		return DE_ID;
	}
	public String getSITE()
	{
		return SITE;
	}
	public String getPAT_NO()
	{
		return PAT_NO;
	}
	public int getEVENT_NO()
	{
		return EVENT_NO;
	}
	public int getWEEK_NO()
	{
		return WEEK_NO;
	}
	public int getHrPreevent()
	{
		return hrPreevent;
	}
	public float getHeartRate()
	{
		return heartRate;
	}
	public String getCompression()
	{
		return compression;
	}
	public float getRespRate()
	{
		return respRate;
	}
	public float getSaturation()
	{
		return saturation;
	}
	public float getSystolicBp()
	{
		return systolicBp;
	}
	public float getDiastolicBp()
	{
		return diastolicBp;
	}
	public int getCapillaryRefill()
	{
		return capillaryRefill;
	}
	public float getTemperature()
	{
		return temperature;
	}
	public String getConsciousnessAvpu()
	{
		return consciousnessAvpu;
	}
	public float getConsciousnessGcs()
	{
		return consciousnessGcs;
	}
	public String getPupils()
	{
		return pupils;
	}
	public String getO2Method()
	{
		return o2Method;
	}
	public String getO2AmountUnit()
	{
		return o2AmountUnit;
	}
	public float getO2Amount()
	{
		return o2Amount;
	}
	public String getRespEffort()
	{
		return respEffort;
	}
	public String getStridor()
	{
		return stridor;
	}
	public float getTotalIvFluid()
	{
		return totalIvFluid;
	}
	public float getUrineOutput()
	{
		return urineOutput;
	}
	public String getUrineYesNo()
	{
		return urineYesNo;
	}
	public String getNarcoticInfusion()
	{
		return narcoticInfusion;
	}
	public String getPhysicianReview()
	{
		return physicianReview;
	}
	public String getStatCall()
	{
		return statCall;
	}
	public String getNursePatientRatio()
	{
		return nursePatientRatio;
	}
	public String getParentPresent()
	{
		return parentPresent;
	}
	public void setDE_ID(int dE_ID)
	{
		DE_ID = dE_ID;
	}
	public void setSITE(String sITE)
	{
		SITE = sITE;
	}
	public void setPAT_NO(String pAT_NO)
	{
		PAT_NO = pAT_NO;
	}
	public void setEVENT_NO(int eVENT_NO)
	{
		EVENT_NO = eVENT_NO;
	}
	public void setWEEK_NO(int wEEK_NO)
	{
		WEEK_NO = wEEK_NO;
	}
	public void setHrPreevent(int hrPreevent)
	{
		this.hrPreevent = hrPreevent;
	}
	public void setHeartRate(float heartRate)
	{
		this.heartRate = heartRate;
	}
	public void setCompression(String compression)
	{
		this.compression = compression;
	}
	public void setRespRate(float respRate)
	{
		this.respRate = respRate;
	}
	public void setSaturation(float saturation)
	{
		this.saturation = saturation;
	}
	public void setSystolicBp(float systolicBp)
	{
		this.systolicBp = systolicBp;
	}
	public void setDiastolicBp(float diastolicBp)
	{
		this.diastolicBp = diastolicBp;
	}
	public void setCapillaryRefill(int capillaryRefill)
	{
		this.capillaryRefill = capillaryRefill;
	}
	public void setTemperature(float temperature)
	{
		this.temperature = temperature;
	}
	public void setConsciousnessAvpu(String consciousnessAvpu)
	{
		this.consciousnessAvpu = consciousnessAvpu;
	}
	public void setConsciousnessGcs(float consciousnessGcs)
	{
		this.consciousnessGcs = consciousnessGcs;
	}
	public void setPupils(String pupils)
	{
		this.pupils = pupils;
	}
	public void setO2Method(String o2Method)
	{
		this.o2Method = o2Method;
	}
	public void setO2AmountUnit(String o2AmountUnit)
	{
		this.o2AmountUnit = o2AmountUnit;
	}
	public void setO2Amount(float o2Amount)
	{
		this.o2Amount = o2Amount;
	}
	public void setRespEffort(String respEffort)
	{
		this.respEffort = respEffort;
	}
	public void setStridor(String stridor)
	{
		this.stridor = stridor;
	}
	public void setTotalIvFluid(float totalIvFluid)
	{
		this.totalIvFluid = totalIvFluid;
	}
	public void setUrineOutput(float urineOutput)
	{
		this.urineOutput = urineOutput;
	}
	public void setUrineYesNo(String urineYesNo)
	{
		this.urineYesNo = urineYesNo;
	}
	public void setNarcoticInfusion(String narcoticInfusion)
	{
		this.narcoticInfusion = narcoticInfusion;
	}
	public void setPhysicianReview(String physicianReview)
	{
		this.physicianReview = physicianReview;
	}
	public void setStatCall(String statCall)
	{
		this.statCall = statCall;
	}
	public void setNursePatientRatio(String nursePatientRatio)
	{
		this.nursePatientRatio = nursePatientRatio;
	}
	public void setParentPresent(String parentPresent)
	{
		this.parentPresent = parentPresent;
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
