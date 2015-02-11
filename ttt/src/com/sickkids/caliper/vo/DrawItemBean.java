package com.sickkids.caliper.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DrawItemBean implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2256033845582668228L;
	private short penSize=0;
	private short penColor=0;
	private List<Point> pointList=null;
	
	public short getPenSize() 
	{
		return penSize;
	}
	public void setPenSize(short penSize) 
	{
		this.penSize = penSize;
	}
	public short getPenColor() 
	{
		return penColor;
	}
	public void setPenColor(short penColor) 
	{
		this.penColor = penColor;
	}
	
	public DrawItemBean() 
	{
		// TODO Auto-generated constructor stub
		pointList=new ArrayList<Point>();
	}
	public List<Point> getPointList() 
	{
		return pointList;
	}
	public void setPointList(List<Point> pointList) 
	{
		this.pointList = pointList;
	}
	@Override public String toString() 
	{
	    StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");

	    result.append(this.getClass().getName() + " Object {" + NEW_LINE);
	    result.append(" penSize: " + penSize + NEW_LINE);
	    result.append(" penColor: " + penColor + NEW_LINE);
	    result.append(" pointList: " + pointList.toString() + NEW_LINE );
	    result.append("}");

	    return result.toString();
	  }
}
