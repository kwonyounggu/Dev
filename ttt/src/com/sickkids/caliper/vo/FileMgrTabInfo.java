package com.sickkids.caliper.vo;

import java.io.Serializable;

public class FileMgrTabInfo implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3983059640890367887L;
	
	private int dgindex=-1;
	private short expanded=0;
	private short playbutton=0;
	private short sldrPress=0;
	private double sldrDrag=0.0;
	private short sdrRelease=0;
	
	public void resetValues()
	{
		dgindex=-1;
		expanded=0;
		playbutton=0;
		sldrPress=0;
		sldrDrag=0.0;
		sdrRelease=0;
	}
	public int getDgindex() 
	{
		return dgindex;
	}
	public void setDgindex(int dgindex) 
	{
		this.dgindex = dgindex;
	}
	public short getExpanded() 
	{
		return expanded;
	}
	public void setExpanded(short expanded) 
	{
		this.expanded = expanded;
	}
	public short getPlaybutton() 
	{
		return playbutton;
	}
	public void setPlaybutton(short playbutton) 
	{
		this.playbutton = playbutton;
	}
	public short getSldrPress() 
	{
		return sldrPress;
	}
	public void setSldrPress(short sldrPress) 
	{
		this.sldrPress = sldrPress;
	}
	public double getSldrDrag() 
	{
		return sldrDrag;
	}
	public void setSldrDrag(double sldrDrag) 
	{
		this.sldrDrag = sldrDrag;
	}
	public short getSdrRelease() 
	{
		return sdrRelease;
	}
	public void setSdrRelease(short sdrRelease) 
	{
		this.sdrRelease = sdrRelease;
	}

}
