package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmO_DE5Bean implements Serializable
{	
	private static final long serialVersionUID = 4817965223121508427L;
	
	private int DE5_ID=0;
	private String SITE="";
	private String PAT_NO="";
	private int EVENT_NO=0;
	private int WEEK_NO=0;
	

	private String eventHourLimit="";//48, 12, 1, tests_24, l_limit, u_limit
	
	private double haeHaemoglobin=-10000;
	private double haeWhiteCellCount=-10000;
	private double haeBands=-10000;
	private double haePlateletCount=-10000;
	
	private double eleSodium=-10000;
	private double elePotassium=-10000;
	private double eleCalciumTotal=-10000;
	private double eleCalciumIonized=-10000;
	private double eleMagnesium=-10000;
	
	private double bloPaO2=-10000;
	private double bloPaCO2=-10000;
	private double bloHCO3=-10000;
	private double bloPh=-10000;
	
	private double venPmvO2=-10000;
	private double venHCO3=-10000;
	
	private double metAmmonia=-10000;
	private double metLactate=-10000;
	private double metGlucose=-10000;
	
	private double liverAST=-10000;
	private double liverALT=-10000;
	private double liverBilirubinCon=-10000;
	private double liverBilirubinUncon=-10000;
	private double liverLDH=-10000;
	private double liverINR=-10000;
	private double liverPTT=-10000;
	
	private String haeHaemoglobinUnit="";
	private String haeWhiteCellCountUnit="";
	private String haeBandsUnit="";
	private String haePlateletCountUnit="";
	
	private String eleSodiumUnit="";
	private String elePotassiumUnit="";
	private String eleCalciumTotalUnit="";
	private String eleCalciumIonizedUnit="";
	private String eleMagnesiumUnit="";
	
	private String bloPaO2Unit="";
	private String bloPaCO2Unit="";
	private String bloHCO3Unit="";
	private String bloPhUnit="";
	
	private String venPmvO2Unit="";
	private String venHCO3Unit="";
	
	private String metAmmoniaUnit="";
	private String metLactateUnit="";
	private String metGlucoseUnit="";
	
	private String liverASTUnit="";
	private String liverALTUnit="";
	private String liverBilirubinConUnit="";
	private String liverBilirubinUnconUnit="";
	private String liverLDHUnit="";
	private String liverINRUnit="";
	private String liverPTTUnit="";
	public int getDE5_ID()
	{
		return DE5_ID;
	}
	public void setDE5_ID(int dE5_ID)
	{
		DE5_ID = dE5_ID;
	}
	public String getSITE()
	{
		return SITE;
	}
	public void setSITE(String sITE)
	{
		SITE = sITE;
	}
	public String getPAT_NO()
	{
		return PAT_NO;
	}
	public void setPAT_NO(String pAT_NO)
	{
		PAT_NO = pAT_NO;
	}
	public int getEVENT_NO()
	{
		return EVENT_NO;
	}
	public void setEVENT_NO(int eVENT_NO)
	{
		EVENT_NO = eVENT_NO;
	}
	public int getWEEK_NO()
	{
		return WEEK_NO;
	}
	public void setWEEK_NO(int wEEK_NO)
	{
		WEEK_NO = wEEK_NO;
	}
	public String getEventHourLimit()
	{
		return eventHourLimit;
	}
	public void setEventHourLimit(String eventHourLimit)
	{
		this.eventHourLimit = eventHourLimit;
	}
	public double getHaeHaemoglobin()
	{
		return haeHaemoglobin;
	}
	public void setHaeHaemoglobin(double haeHaemoglobin)
	{
		this.haeHaemoglobin = haeHaemoglobin;
	}
	public double getHaeWhiteCellCount()
	{
		return haeWhiteCellCount;
	}
	public void setHaeWhiteCellCount(double haeWhiteCellCount)
	{
		this.haeWhiteCellCount = haeWhiteCellCount;
	}
	public double getHaeBands()
	{
		return haeBands;
	}
	public void setHaeBands(double haeBands)
	{
		this.haeBands = haeBands;
	}
	public double getHaePlateletCount()
	{
		return haePlateletCount;
	}
	public void setHaePlateletCount(double haePlateletCount)
	{
		this.haePlateletCount = haePlateletCount;
	}
	public double getEleSodium()
	{
		return eleSodium;
	}
	public void setEleSodium(double eleSodium)
	{
		this.eleSodium = eleSodium;
	}
	public double getElePotassium()
	{
		return elePotassium;
	}
	public void setElePotassium(double elePotassium)
	{
		this.elePotassium = elePotassium;
	}
	public double getEleCalciumTotal()
	{
		return eleCalciumTotal;
	}
	public void setEleCalciumTotal(double eleCalciumTotal)
	{
		this.eleCalciumTotal = eleCalciumTotal;
	}
	public double getEleCalciumIonized()
	{
		return eleCalciumIonized;
	}
	public void setEleCalciumIonized(double eleCalciumIonized)
	{
		this.eleCalciumIonized = eleCalciumIonized;
	}
	public double getEleMagnesium()
	{
		return eleMagnesium;
	}
	public void setEleMagnesium(double eleMagnesium)
	{
		this.eleMagnesium = eleMagnesium;
	}
	public double getBloPaO2()
	{
		return bloPaO2;
	}
	public void setBloPaO2(double bloPaO2)
	{
		this.bloPaO2 = bloPaO2;
	}
	public double getBloPaCO2()
	{
		return bloPaCO2;
	}
	public void setBloPaCO2(double bloPaCO2)
	{
		this.bloPaCO2 = bloPaCO2;
	}
	public double getBloHCO3()
	{
		return bloHCO3;
	}
	public void setBloHCO3(double bloHCO3)
	{
		this.bloHCO3 = bloHCO3;
	}
	public double getBloPh()
	{
		return bloPh;
	}
	public void setBloPh(double bloPh)
	{
		this.bloPh = bloPh;
	}
	public double getVenPmvO2()
	{
		return venPmvO2;
	}
	public void setVenPmvO2(double venPmvO2)
	{
		this.venPmvO2 = venPmvO2;
	}
	public double getVenHCO3()
	{
		return venHCO3;
	}
	public void setVenHCO3(double venHCO3)
	{
		this.venHCO3 = venHCO3;
	}
	public double getMetAmmonia()
	{
		return metAmmonia;
	}
	public void setMetAmmonia(double metAmmonia)
	{
		this.metAmmonia = metAmmonia;
	}
	public double getMetLactate()
	{
		return metLactate;
	}
	public void setMetLactate(double metLactate)
	{
		this.metLactate = metLactate;
	}
	public double getMetGlucose()
	{
		return metGlucose;
	}
	public void setMetGlucose(double metGlucose)
	{
		this.metGlucose = metGlucose;
	}
	public double getLiverAST()
	{
		return liverAST;
	}
	public void setLiverAST(double liverAST)
	{
		this.liverAST = liverAST;
	}
	public double getLiverALT()
	{
		return liverALT;
	}
	public void setLiverALT(double liverALT)
	{
		this.liverALT = liverALT;
	}
	public double getLiverBilirubinCon()
	{
		return liverBilirubinCon;
	}
	public void setLiverBilirubinCon(double liverBilirubinCon)
	{
		this.liverBilirubinCon = liverBilirubinCon;
	}
	public double getLiverBilirubinUncon()
	{
		return liverBilirubinUncon;
	}
	public void setLiverBilirubinUncon(double liverBilirubinUncon)
	{
		this.liverBilirubinUncon = liverBilirubinUncon;
	}
	public double getLiverLDH()
	{
		return liverLDH;
	}
	public void setLiverLDH(double liverLDH)
	{
		this.liverLDH = liverLDH;
	}
	public double getLiverINR()
	{
		return liverINR;
	}
	public void setLiverINR(double liverINR)
	{
		this.liverINR = liverINR;
	}
	public double getLiverPTT()
	{
		return liverPTT;
	}
	public void setLiverPTT(double liverPTT)
	{
		this.liverPTT = liverPTT;
	}
	public String getHaeHaemoglobinUnit()
	{
		return haeHaemoglobinUnit;
	}
	public void setHaeHaemoglobinUnit(String haeHaemoglobinUnit)
	{
		this.haeHaemoglobinUnit = haeHaemoglobinUnit;
	}
	public String getHaeWhiteCellCountUnit()
	{
		return haeWhiteCellCountUnit;
	}
	public void setHaeWhiteCellCountUnit(String haeWhiteCellCountUnit)
	{
		this.haeWhiteCellCountUnit = haeWhiteCellCountUnit;
	}
	public String getHaeBandsUnit()
	{
		return haeBandsUnit;
	}
	public void setHaeBandsUnit(String haeBandsUnit)
	{
		this.haeBandsUnit = haeBandsUnit;
	}
	public String getHaePlateletCountUnit()
	{
		return haePlateletCountUnit;
	}
	public void setHaePlateletCountUnit(String haePlateletCountUnit)
	{
		this.haePlateletCountUnit = haePlateletCountUnit;
	}
	public String getEleSodiumUnit()
	{
		return eleSodiumUnit;
	}
	public void setEleSodiumUnit(String eleSodiumUnit)
	{
		this.eleSodiumUnit = eleSodiumUnit;
	}
	public String getElePotassiumUnit()
	{
		return elePotassiumUnit;
	}
	public void setElePotassiumUnit(String elePotassiumUnit)
	{
		this.elePotassiumUnit = elePotassiumUnit;
	}
	public String getEleCalciumTotalUnit()
	{
		return eleCalciumTotalUnit;
	}
	public void setEleCalciumTotalUnit(String eleCalciumTotalUnit)
	{
		this.eleCalciumTotalUnit = eleCalciumTotalUnit;
	}
	public String getEleCalciumIonizedUnit()
	{
		return eleCalciumIonizedUnit;
	}
	public void setEleCalciumIonizedUnit(String eleCalciumIonizedUnit)
	{
		this.eleCalciumIonizedUnit = eleCalciumIonizedUnit;
	}
	public String getEleMagnesiumUnit()
	{
		return eleMagnesiumUnit;
	}
	public void setEleMagnesiumUnit(String eleMagnesiumUnit)
	{
		this.eleMagnesiumUnit = eleMagnesiumUnit;
	}
	public String getBloPaO2Unit()
	{
		return bloPaO2Unit;
	}
	public void setBloPaO2Unit(String bloPaO2Unit)
	{
		this.bloPaO2Unit = bloPaO2Unit;
	}
	public String getBloPaCO2Unit()
	{
		return bloPaCO2Unit;
	}
	public void setBloPaCO2Unit(String bloPaCO2Unit)
	{
		this.bloPaCO2Unit = bloPaCO2Unit;
	}
	public String getBloHCO3Unit()
	{
		return bloHCO3Unit;
	}
	public void setBloHCO3Unit(String bloHCO3Unit)
	{
		this.bloHCO3Unit = bloHCO3Unit;
	}
	public String getBloPhUnit()
	{
		return bloPhUnit;
	}
	public void setBloPhUnit(String bloPhUnit)
	{
		this.bloPhUnit = bloPhUnit;
	}
	public String getVenPmvO2Unit()
	{
		return venPmvO2Unit;
	}
	public void setVenPmvO2Unit(String venPmvO2Unit)
	{
		this.venPmvO2Unit = venPmvO2Unit;
	}
	public String getVenHCO3Unit()
	{
		return venHCO3Unit;
	}
	public void setVenHCO3Unit(String venHCO3Unit)
	{
		this.venHCO3Unit = venHCO3Unit;
	}
	public String getMetAmmoniaUnit()
	{
		return metAmmoniaUnit;
	}
	public void setMetAmmoniaUnit(String metAmmoniaUnit)
	{
		this.metAmmoniaUnit = metAmmoniaUnit;
	}
	public String getMetLactateUnit()
	{
		return metLactateUnit;
	}
	public void setMetLactateUnit(String metLactateUnit)
	{
		this.metLactateUnit = metLactateUnit;
	}
	public String getMetGlucoseUnit()
	{
		return metGlucoseUnit;
	}
	public void setMetGlucoseUnit(String metGlucoseUnit)
	{
		this.metGlucoseUnit = metGlucoseUnit;
	}
	public String getLiverASTUnit()
	{
		return liverASTUnit;
	}
	public void setLiverASTUnit(String liverASTUnit)
	{
		this.liverASTUnit = liverASTUnit;
	}
	public String getLiverALTUnit()
	{
		return liverALTUnit;
	}
	public void setLiverALTUnit(String liverALTUnit)
	{
		this.liverALTUnit = liverALTUnit;
	}
	public String getLiverBilirubinConUnit()
	{
		return liverBilirubinConUnit;
	}
	public void setLiverBilirubinConUnit(String liverBilirubinConUnit)
	{
		this.liverBilirubinConUnit = liverBilirubinConUnit;
	}
	public String getLiverBilirubinUnconUnit()
	{
		return liverBilirubinUnconUnit;
	}
	public void setLiverBilirubinUnconUnit(String liverBilirubinUnconUnit)
	{
		this.liverBilirubinUnconUnit = liverBilirubinUnconUnit;
	}
	public String getLiverLDHUnit()
	{
		return liverLDHUnit;
	}
	public void setLiverLDHUnit(String liverLDHUnit)
	{
		this.liverLDHUnit = liverLDHUnit;
	}
	public String getLiverINRUnit()
	{
		return liverINRUnit;
	}
	public void setLiverINRUnit(String liverINRUnit)
	{
		this.liverINRUnit = liverINRUnit;
	}
	public String getLiverPTTUnit()
	{
		return liverPTTUnit;
	}
	public void setLiverPTTUnit(String liverPTTUnit)
	{
		this.liverPTTUnit = liverPTTUnit;
	}

}
