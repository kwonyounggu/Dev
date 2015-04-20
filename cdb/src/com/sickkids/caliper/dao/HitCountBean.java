package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class HitCountBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1500961130275872120L;
	private int idx=1;
	private long hitCounter=0L;
	private Timestamp hitTimestamp=null;
	public HitCountBean()
	{
		// TODO Auto-generated constructor stub
	}
	public HitCountBean(int idx,long hitCounter,Timestamp hitTimestamp)
	{	this.idx=idx;
		this.hitCounter=hitCounter;
		this.hitTimestamp=hitTimestamp;
	}
	public long getHitCounter()
	{
		return hitCounter;
	}
	public void setHitCounter(long hitCounter)
	{
		this.hitCounter = hitCounter;
	}
	public Timestamp getHitTimestamp()
	{
		return hitTimestamp;
	}
	public void setHitTimestamp(Timestamp hitTimestamp)
	{
		this.hitTimestamp = hitTimestamp;
	}
	public int getIdx()
	{
		return idx;
	}
	public void setIdx(int idx)
	{
		this.idx = idx;
	}
	

}
