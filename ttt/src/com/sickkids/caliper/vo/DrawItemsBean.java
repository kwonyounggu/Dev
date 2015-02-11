package com.sickkids.caliper.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DrawItemsBean implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5216746761555833784L;
	
	private short myMenu=0;	
	private List<DrawItemBean> items=null;
	
	public DrawItemsBean() 
	{
		items=new ArrayList<DrawItemBean>();
	}
	public DrawItemsBean(short myMenu) 
	{
		this.myMenu=myMenu;
		items=new ArrayList<DrawItemBean>();
	}
	public short getMyMenu() 
	{
		return myMenu;
	}
	public void setMyMenu(short myMenu) 
	{
		this.myMenu = myMenu;
	}
	public List<DrawItemBean> getItems() 
	{
		return items;
	}
	public void setItems(List<DrawItemBean> items) 
	{
		this.items = items;
	}
	public void clearAll()
	{
		for(int i=0;i<items.size();i++)
			items.get(i).getPointList().clear();
		items.clear();
	}
	@Override public String toString() 
	{
	    StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");

	    result.append(this.getClass().getName() + " Object {" + NEW_LINE);
	    result.append(" myMenu: " + myMenu + NEW_LINE);
	    result.append(" itemList: " + items.toString() + NEW_LINE );
	    result.append("}");

	    return result.toString();
	  }

}
