package com.sickkids.caliper.statistics;

public class RankBean
{
	private int s1=0;
	private int s2=0;
	private int lower=0;
	private int upper=0;
	
	public RankBean(int s1, int s2, int lower, int upper)
	{
		this.s1=s1;
		this.s2=s2;
		this.lower=lower;
		this.upper=upper;
	}

	public int getS1()
	{
		return s1;
	}

	public void setS1(int s1)
	{
		this.s1 = s1;
	}

	public int getS2()
	{
		return s2;
	}

	public void setS2(int s2)
	{
		this.s2 = s2;
	}

	public int getLower()
	{
		return lower;
	}

	public void setLower(int lower)
	{
		this.lower = lower;
	}

	public int getUpper()
	{
		return upper;
	}

	public void setUpper(int upper)
	{
		this.upper = upper;
	}
	
	public boolean inBetween(int a)
	{
		return a>=s1 && a<=s2;
	}
}
