package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class AllApplicationHistBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3413450839661671190L;
	
	private long app_hist_id=0L;
	private int app_id=0;
	private int app_id_revision=0;
	private String purpose_of_app="";
	private String app_symbol="";
	private int number_of_groups=0;
	private int reviewers_per_item=0;
	private String reviewer_assignment_type="";
	private boolean confirmatory=true;
	private String disagreement_process="";
	private Timestamp creation_time=null;
	private String creator_id="";
	private String remarks="";
	
	public AllApplicationHistBean(AllApplicationBean ab)
	{
		app_id=ab.getApp_id();
		app_id_revision=ab.getApp_id_revision();
		purpose_of_app=ab.getPurpose_of_app();
		app_symbol=ab.getApp_symbol();
		number_of_groups=ab.getNumber_of_groups();
		reviewers_per_item=ab.getReviewers_per_item();
		reviewer_assignment_type=ab.getReviewer_assignment_type();
		confirmatory=ab.isConfirmatory();
		disagreement_process=ab.getDisagreement_process();
		creation_time=ab.getCreation_time();
		creator_id=ab.getCreator_id();
		remarks=ab.getRemarks();
	}
	public long getApp_hist_id() 
	{
		return app_hist_id;
	}
	public void setApp_hist_id(long app_hist_id) 
	{
		this.app_hist_id = app_hist_id;
	}
	public int getApp_id()
	{
		return app_id;
	}
	public void setApp_id(int app_id)
	{
		this.app_id = app_id;
	}
	public String getPurpose_of_app()
	{
		return purpose_of_app;
	}
	public void setPurpose_of_app(String purpose_of_app)
	{
		this.purpose_of_app = purpose_of_app;
	}
	public String getApp_symbol()
	{
		return app_symbol;
	}
	public void setApp_symbol(String app_symbol)
	{
		this.app_symbol = app_symbol;
	}
	public int getNumber_of_groups()
	{
		return number_of_groups;
	}
	public void setNumber_of_groups(int number_of_groups)
	{
		this.number_of_groups = number_of_groups;
	}
	public String getSReviewers_per_item()
	{
		String sReviewers_per_item="0";
		switch(reviewers_per_item)
		{
			case 1:
			case 2:
			case 3:
			case 4: sReviewers_per_item=Integer.toString(reviewers_per_item);
					break;
			case 10:sReviewers_per_item="<10";
					break;
			case 20:sReviewers_per_item="<20";
					break;
			case 10000: sReviewers_per_item=">=20";
					break;
			default: break;
		}
		return sReviewers_per_item;
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
	public int getApp_id_revision()
	{
		return app_id_revision;
	}
	public void setApp_id_revision(int app_id_revision)
	{
		this.app_id_revision = app_id_revision;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public String getInsertStmt()
	{
		return "insert into all_application_hist values(default, "+
				   " "+ app_id+", "+
				   " "+ app_id_revision+", "+
				   "'"+purpose_of_app+"', "+
				   "'"+app_symbol+"', "+
				   " "+number_of_groups+", "+
				   " "+reviewers_per_item+", "+
				   "'"+reviewer_assignment_type+"', "+
				   " "+confirmatory+", "+
				   "'"+disagreement_process+"', "+
				   "'"+creation_time+"', "+
				   "'"+creator_id+"', "+
				   "'"+remarks+"')";
	}
	public static String getInsertStmtFromAllApplication(int app_id)
	{
		return "insert into all_application_hist ("+
				   "app_id, "+
				   "app_id_revision, "+
				   "purpose_of_app, "+
				   "app_symbol, "+
				   "number_of_groups, "+
				   "reviewers_per_item, "+
				   "reviewer_assignment_type, "+
				   "confirmatory, "+
				   "disagreement_process, "+
				   "creation_time, "+
				   "creator_id, "+
				   "remarks) "+
				   "(select * from all_application where app_id="+app_id+")";
	}
	
	
}
