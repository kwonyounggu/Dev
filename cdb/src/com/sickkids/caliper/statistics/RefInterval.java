package com.sickkids.caliper.statistics;

public class RefInterval
{
	private double lowerLimit=0.0d;
	private double upperLimit=0.0d;
	
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
	public String toString()
	{
		return lowerLimit+" to "+upperLimit;
	}
}
