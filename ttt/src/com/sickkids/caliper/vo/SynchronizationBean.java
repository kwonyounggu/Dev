package com.sickkids.caliper.vo;

import java.io.Serializable;

public class SynchronizationBean implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3988560398825278905L;
	
	private short currentMenu=0;
	private String textFilePath="";
	private String videoFilePath="";
	private DrawItemsBean itemsList=null;
	private DrawItemsBean itemsListTextBook=null;
	private DrawItemsBean itemsListWhiteBoard=null;
	private float currentScale=0;
	private int horizontalPosition=0;
	private int verticalPosition=0;
	
	public float getCurrentScale() 
	{
		return currentScale;
	}
	public void setCurrentScale(float currentScale) 
	{
		this.currentScale = currentScale;
	}
	public int getHorizontalPosition() 
	{
		return horizontalPosition;
	}
	public void setHorizontalPosition(int horizontalPosition) 
	{
		this.horizontalPosition = horizontalPosition;
	}
	public int getVerticalPosition() 
	{
		return verticalPosition;
	}
	public void setVerticalPosition(int verticalPosition) 
	{
		this.verticalPosition = verticalPosition;
	}
	public DrawItemsBean getItemsListTextBook() 
	{
		return itemsListTextBook;
	}
	public void setItemsListTextBook(DrawItemsBean itemsListTextBook) 
	{
		this.itemsListTextBook = itemsListTextBook;
	}
	public DrawItemsBean getItemsListWhiteBoard() 
	{
		return itemsListWhiteBoard;
	}
	public void setItemsListWhiteBoard(DrawItemsBean itemsListWhiteBoard) 
	{
		this.itemsListWhiteBoard = itemsListWhiteBoard;
	}
	public short getCurrentMenu() 
	{
		return currentMenu;
	}
	public void setCurrentMenu(short currentMenu) 
	{
		this.currentMenu = currentMenu;
	}
	public String getTextFilePath() 
	{
		return textFilePath;
	}
	public void setTextFilePath(String textFilePath) 
	{
		this.textFilePath = textFilePath;
	}
	public String getVideoFilePath() 
	{
		return videoFilePath;
	}
	public void setVideoFilePath(String videoFilePath) 
	{
		this.videoFilePath = videoFilePath;
	}
	public DrawItemsBean getItemsList() 
	{
		return itemsList;
	}
	public void setItemsList(DrawItemsBean itemsList) 
	{
		this.itemsList = itemsList;
	}
}
