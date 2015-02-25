package com.sickkids.caliper.dao;

import java.io.Serializable;

public class CourseTimeFileMxBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 811088008146609928L;
	private int course_number=-1;
	private int time_table_id=-1;
	private int file_id=-1;
	public int getCourse_number()
	{
		return course_number;
	}
	public void setCourse_number(int course_number)
	{
		this.course_number = course_number;
	}
	public int getTime_table_id()
	{
		return time_table_id;
	}
	public void setTime_table_id(int time_table_id)
	{
		this.time_table_id = time_table_id;
	}
	public int getFile_id()
	{
		return file_id;
	}
	public void setFile_id(int file_id)
	{
		this.file_id = file_id;
	}
}
