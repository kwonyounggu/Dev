package com.sickkids.caliper.dao;

import java.io.Serializable;

public class StatisticsBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double std=0.0d;
	private double mean=0.0d;
	private int n=0;
	public int getN()
	{
		return n;
	}
	public void setN(int n)
	{
		this.n = n;
	}
	public double getStd()
	{
		return std;
	}
	public void setStd(double std)
	{
		this.std = std;
	}
	public double getMean()
	{
		return mean;
	}
	public void setMean(double mean)
	{
		this.mean = mean;
	}
	
}
