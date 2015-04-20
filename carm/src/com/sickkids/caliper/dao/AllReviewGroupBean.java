package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class AllReviewGroupBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 812024328687106617L;
	
	private int app_id=-1;
	private int group_id=-1;
	private String group_name="";
	private Timestamp time_out=null;
	private int reviewers_per_item=-1;
	private String reviewer_assignment_type="";
	private boolean confirmatory=true;
	private String disagreement_process="";
	private Timestamp creation_time=null;
	private String creator_id="";
	
	public int getApp_id()
	{
		return app_id;
	}
	public void setApp_id(int app_id)
	{
		this.app_id = app_id;
	}
	public int getGroup_id()
	{
		return group_id;
	}
	public void setGroup_id(int group_id)
	{
		this.group_id = group_id;
	}
	public String getGroup_name()
	{
		return group_name;
	}
	public void setGroup_name(String group_name)
	{
		this.group_name = group_name;
	}
	public Timestamp getTime_out()
	{
		return time_out;
	}
	public void setTime_out(Timestamp time_out)
	{
		this.time_out = time_out;
	}
	public int getReviewers_per_item()
	{
		return reviewers_per_item;
	}
	public void setReviewers_per_item(int reviewers_per_item)
	{
		this.reviewers_per_item = reviewers_per_item;
	}
	public String getReviewer_assignment_type()
	{
		return reviewer_assignment_type;
	}
	public void setReviewer_assignment_type(String reviewer_assignment_type)
	{
		this.reviewer_assignment_type = reviewer_assignment_type;
	}
	public boolean isConfirmatory()
	{
		return confirmatory;
	}
	public void setConfirmatory(boolean confirmatory)
	{
		this.confirmatory = confirmatory;
	}
	public String getDisagreement_process()
	{
		return disagreement_process;
	}
	public void setDisagreement_process(String disagreement_process)
	{
		this.disagreement_process = disagreement_process;
	}
	public Timestamp getCreation_time()
	{
		return creation_time;
	}
	public void setCreation_time(Timestamp creation_time)
	{
		this.creation_time = creation_time;
	}
	public String getCreator_id()
	{
		return creator_id;
	}
	public void setCreator_id(String creator_id)
	{
		this.creator_id = creator_id;
	}

	public String getInsertStmt()
	{
		return "insert into all_review_group values("+
				   " "+ app_id+", "+
				   " "+group_id+", "+
				   "'"+group_name+"', "+
				   "'"+time_out+"', "+
				   " "+reviewers_per_item+", "+
				   "'"+reviewer_assignment_type+"', "+
				   " "+confirmatory+", "+
				   "'"+disagreement_process+"', "+
				   "'"+creation_time+"', "+
				   "'"+creator_id+"')";
	}
	public String getUpdateStmt()
	{
		return "update all_review_group set "+
					"group_name='"+group_name+"', "+
					"time_out='"+time_out+"', "+
					"reviewers_per_item="+reviewers_per_item+", "+
					"reviewer_assignment_type='"+reviewer_assignment_type+"', "+
					"confirmatory="+confirmatory+", "+
					"disagreement_process='"+disagreement_process+"' "+
					"where app_id="+app_id+" and group_id="+group_id;
					
	}
	
}
