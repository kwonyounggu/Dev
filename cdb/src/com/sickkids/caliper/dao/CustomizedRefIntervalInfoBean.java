package com.sickkids.caliper.dao;

import java.io.Serializable;

public class CustomizedRefIntervalInfoBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -239124194893839135L;
	private double lowerLimit=-10000.0d;
	private double upperLimit=-10000.0d;
	private int samples=0;
	private double lowerCI5=-10000.0d;
	private double lowerCI95=-10000.0d;
	private double upperCI5=-10000.0d;
	private double upperCI95=-10000.0d;
	
	private double lowerLimitWithLT=-10000.0d;
	private double upperLimitWithLT=-10000.0d;
	private int samplesWithLT=0;
	private double lowerCI5WithLT=-10000.0d;
	private double lowerCI95WithLT=-10000.0d;
	private double upperCI5WithLT=-10000.0d;
	private double upperCI95WithLT=-10000.0d;
	
	private double pValue1=0.0d;
	private double pValue2=0.0d;
	
	
	public double getLowerLimit()
	{
		return lowerLimit;
	}
	public void setLowerLimit(double lowerLimit)
	{
		this.lowerLimit = lowerLimit;
	}
	public double getUpperLimit()
	{
		return upperLimit;
	}
	public void setUpperLimit(double upperLimit)
	{
		this.upperLimit = upperLimit;
	}
	public int getSamples()
	{
		return samples;
	}
	public void setSamples(int samples)
	{
		this.samples = samples;
	}
	public double getLowerCI5()
	{
		return lowerCI5;
	}
	public void setLowerCI5(double lowerCI5)
	{
		this.lowerCI5 = lowerCI5;
	}
	public double getLowerCI95()
	{
		return lowerCI95;
	}
	public void setLowerCI95(double lowerCI95)
	{
		this.lowerCI95 = lowerCI95;
	}
	public double getUpperCI5()
	{
		return upperCI5;
	}
	public void setUpperCI5(double upperCI5)
	{
		this.upperCI5 = upperCI5;
	}
	public double getUpperCI95()
	{
		return upperCI95;
	}
	public void setUpperCI95(double upperCI95)
	{
		this.upperCI95 = upperCI95;
	}
	public double getLowerLimitWithLT()
	{
		return lowerLimitWithLT;
	}
	public void setLowerLimitWithLT(double lowerLimitWithLT)
	{
		this.lowerLimitWithLT = lowerLimitWithLT;
	}
	public double getUpperLimitWithLT()
	{
		return upperLimitWithLT;
	}
	public void setUpperLimitWithLT(double upperLimitWithLT)
	{
		this.upperLimitWithLT = upperLimitWithLT;
	}
	public int getSamplesWithLT()
	{
		return samplesWithLT;
	}
	public void setSamplesWithLT(int samplesWithLT)
	{
		this.samplesWithLT = samplesWithLT;
	}
	public double getLowerCI5WithLT()
	{
		return lowerCI5WithLT;
	}
	public void setLowerCI5WithLT(double lowerCI5WithLT)
	{
		this.lowerCI5WithLT = lowerCI5WithLT;
	}
	public double getLowerCI95WithLT()
	{
		return lowerCI95WithLT;
	}
	public void setLowerCI95WithLT(double lowerCI95WithLT)
	{
		this.lowerCI95WithLT = lowerCI95WithLT;
	}
	public double getUpperCI5WithLT()
	{
		return upperCI5WithLT;
	}
	public void setUpperCI5WithLT(double upperCI5WithLT)
	{
		this.upperCI5WithLT = upperCI5WithLT;
	}
	public double getUpperCI95WithLT()
	{
		return upperCI95WithLT;
	}
	public void setUpperCI95WithLT(double upperCI95WithLT)
	{
		this.upperCI95WithLT = upperCI95WithLT;
	}
	public double getpValue1()
	{
		return pValue1;
	}
	public void setpValue1(double pValue1)
	{
		this.pValue1 = pValue1;
	}
	public double getpValue2()
	{
		return pValue2;
	}
	public void setpValue2(double pValue2)
	{
		this.pValue2 = pValue2;
	}
	
	
}
