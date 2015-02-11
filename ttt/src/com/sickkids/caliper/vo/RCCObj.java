package com.sickkids.caliper.vo;

import java.io.Serializable;

public class RCCObj implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2684570519108539103L;
	
	private String cmd="";
	private double val1=0.0;
	private double val2=0.0;
	
	public RCCObj(String cmd)
	{
		this.cmd=cmd;

	}
	public RCCObj(String cmd, double val1)
	{
		this.cmd=cmd;
		this.val1=val1;//file manager, file grid index value, isExpanded, seek, playPause
	}
	public RCCObj(String cmd, double val1, double val2)
	{
		this.cmd=cmd;
		this.val1=val1;//file manager, file grid index value, isExpanded, seek, playPause
		this.val2=val2;
	}
	public String getCmd() 
	{
		return cmd;
	}
	public void setCmd(String cmd) 
	{
		this.cmd = cmd;
	}
	public double getVal1() 
	{
		return val1;
	}
	public void setVal1(double val1) 
	{
		this.val1 = val1;
	}
	public double getVal2() 
	{
		return val2;
	}
	public void setVal2(double val2) 
	{
		this.val2 = val2;
	}
}

