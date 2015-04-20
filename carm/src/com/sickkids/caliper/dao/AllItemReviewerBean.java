package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class AllItemReviewerBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1399664353656685050L;

	private int app_id=-1;
	private int group_id=-1;
	private int item_id=-1;
	private String reviewer_id="";
	private String mediator_id="";
	private String reviewer_action_status="";
	private Timestamp action_status_time=null;
	private String creator_id="";
	private Timestamp creation_time=null;
	private Timestamp time_out=null;
	private boolean confirmatory=true;
	
	private String group_name=""; //just for display on the page not from this table
	private Timestamp group_time_out=null;
	private int group_reviewers_per_item=-1;
	private String group_reviewer_assignment_type="";
	private boolean group_confirmatory=true;
	private String group_disagreement_process="";
	
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

	public int getItem_id()
	{
		return item_id;
	}

	public void setItem_id(int item_id)
	{
		this.item_id = item_id;
	}

	public String getReviewer_id()
	{
		return reviewer_id;
	}

	public void setReviewer_id(String reviewer_id)
	{
		this.reviewer_id = reviewer_id;
	}

	public String getMediator_id()
	{
		return mediator_id;
	}

	public void setMediator_id(String mediator_id)
	{
		this.mediator_id = mediator_id;
	}

	public String getReviewer_action_status()
	{
		return reviewer_action_status;
	}

	public void setReviewer_action_status(String reviewer_action_status)
	{
		this.reviewer_action_status = reviewer_action_status;
	}

	public Timestamp getAction_status_time()
	{
		return action_status_time;
	}

	public void setAction_status_time(Timestamp action_status_time)
	{
		this.action_status_time = action_status_time;
	}

	public String getCreator_id()
	{
		return creator_id;
	}

	public void setCreator_id(String creator_id)
	{
		this.creator_id = creator_id;
	}

	public Timestamp getCreation_time()
	{
		return creation_time;
	}

	public void setCreation_time(Timestamp creation_time)
	{
		this.creation_time = creation_time;
	}

	public Timestamp getTime_out()
	{
		return time_out;
	}

	public void setTime_out(Timestamp time_out)
	{
		this.time_out = time_out;
	}

	public boolean isConfirmatory()
	{
		return confirmatory;
	}

	public void setConfirmatory(boolean confirmatory)
	{
		this.confirmatory = confirmatory;
	}

	public String getGroup_name()
	{
		return group_name;
	}

	public void setGroup_name(String group_name)
	{
		this.group_name = group_name;
	}

	public Timestamp getGroup_time_out()
	{
		return group_time_out;
	}

	public void setGroup_time_out(Timestamp group_time_out)
	{
		this.group_time_out = group_time_out;
	}

	public int getGroup_reviewers_per_item()
	{
		return group_reviewers_per_item;
	}

	public void setGroup_reviewers_per_item(int group_reviewers_per_item)
	{
		this.group_reviewers_per_item = group_reviewers_per_item;
	}

	public String getGroup_reviewer_assignment_type()
	{
		return group_reviewer_assignment_type;
	}

	public void setGroup_reviewer_assignment_type(
			String group_reviewer_assignment_type)
	{
		this.group_reviewer_assignment_type = group_reviewer_assignment_type;
	}

	public boolean isGroup_confirmatory()
	{
		return group_confirmatory;
	}

	public void setGroup_confirmatory(boolean group_confirmatory)
	{
		this.group_confirmatory = group_confirmatory;
	}

	public String getGroup_disagreement_process()
	{
		return group_disagreement_process;
	}

	public void setGroup_disagreement_process(String group_disagreement_process)
	{
		this.group_disagreement_process = group_disagreement_process;
	}
	
	public String toString()
	{
		return
				""+app_id+", \n"+
				""+group_id+", \n"+
				""+item_id+", \n"+
				""+reviewer_id+", \n"+
				""+mediator_id+", \n"+
				""+reviewer_action_status+", \n"+
				""+action_status_time+", \n"+
				""+creator_id+", \n"+
				""+creation_time+", \n"+
				""+time_out+", \n"+
				""+confirmatory+", \n"+
				
				""+group_name+", \n"+
				""+group_time_out+", \n"+
				""+group_reviewers_per_item+", \n"+
				""+group_reviewer_assignment_type+", \n"+
				""+group_confirmatory+", \n"+
				""+group_disagreement_process;
	}
	
	public String getInsertStmt()
	{
		return "insert into all_item_reviewer_mx values("+
				   " "+ app_id+", "+
				   " "+ group_id+", "+
				   " "+ item_id+", "+
				   "'"+reviewer_id+"', "+
				   "'"+mediator_id+"', "+
				   "'"+reviewer_action_status+"', "+
				   "'"+action_status_time+"', "+
				   "'"+creator_id+"', "+
				   "'"+creation_time+"', "+
				   "'"+time_out+"', "+
				   " "+confirmatory+")";
	}
	public String getUpdateStmt(String old_reviewer_id)
	{
		if(old_reviewer_id.equals(reviewer_id))
			return "update all_item_reviewer_mx set "+
						"reviewer_id='"+reviewer_id+"', "+ //same as before
						"mediator_id='"+mediator_id+"', "+						
						"time_out='"+time_out+"', "+
						"confirmatory="+confirmatory+" "+
						"where app_id="+app_id+" and group_id="+group_id+" and item_id="+item_id+" and reviewer_id='"+old_reviewer_id+"'";
		else
			return "update all_item_reviewer_mx set "+
					"reviewer_id='"+reviewer_id+"', "+ //new reviewer_id
					"mediator_id='"+mediator_id+"', "+
					"reviewer_action_status='NOT_STARTED', "+
					"action_status_time=CURRENT_TIMESTAMP, "+
					"time_out='"+time_out+"', "+
					"confirmatory="+confirmatory+" "+
					"where app_id="+app_id+" and group_id="+group_id+" and item_id="+item_id+" and reviewer_id='"+old_reviewer_id+"'";
					
	}
	public String getNewUpdateStmt(String new_reviewer_action_status)
	{
		return "update all_item_reviewer_mx set "+				
					"reviewer_action_status='"+new_reviewer_action_status+"', "+
					"action_status_time=CURRENT_TIMESTAMP "+					
					"where app_id="+app_id+" and group_id="+group_id+" and item_id="+item_id+" and reviewer_id='"+reviewer_id+"'";
					
	}
	public static String reviewStatus(String status)
	{
		if(status.indexOf("NOT_STARTED")!=-1) return "Not Started";
		else if(status.indexOf("STARTED")!=-1) return "Started";
		else if(status.indexOf("COMPLETE_EDITABLE")!=-1) return "Complete But Editable";
		else if(status.indexOf("COMPLETE_NOT_EDITABLE")!=-1) return "Complete so Not Editable";
		else if(status.indexOf("INCOMPLETE_NOT_EDITABLE")!=-1) return "Incomplete but Not Editable";
		return "Not Applicable";
	}
	public static String reviewNextStatus(String status, boolean fullPointDone)
	{
		String retStatus="";
		if(status.indexOf("NOT_STARTED")!=-1) retStatus=(fullPointDone? "COMPLETE_EDITABLE":"STARTED");
		else if(status.indexOf("STARTED")!=-1) retStatus=(fullPointDone? "COMPLETE_EDITABLE":"STARTED");
		else if(status.indexOf("COMPLETE_EDITABLE")!=-1) retStatus=(fullPointDone? "COMPLETE_EDITABLE":"STARTED");
		return retStatus;
	}
	
}
