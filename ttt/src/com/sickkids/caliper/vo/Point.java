package com.sickkids.caliper.vo;

import java.io.Serializable;

public class Point implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1111178734058635526L;
	
	private int x=0;
	private int y=0;
	public int getX() 
	{
		return x;
	}
	public void setX(int x) 
	{
		this.x = x;
	}
	public int getY() 
	{
		return y;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	@Override public String toString()
	{
		return "("+x+","+y+")";
	}

}
