package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;

public class AllForumBean implements Serializable
{
	private static final long serialVersionUID = 1784356041998915483L;
	
	private long all_forum_index=0L;
	private int app_id=-1;
	private int group_id=-1;
	private int item_id=-1;
	private int point_id=-1;
	private String submitter_opinion="";
	private String submitter_id="";
	private Timestamp submission_time=null;
	private long reply_to_all_forum_index=0L;
	private String remarks="";
	
	private boolean bMediatorBtnChecked=false;
	private int mediatorValue=-1;
	public long getAll_forum_index()
	{
		return all_forum_index;
	}
	public void setAll_forum_index(long all_forum_index)
	{
		this.all_forum_index = all_forum_index;
	}
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
	public int getPoint_id()
	{
		return point_id;
	}
	public void setPoint_id(int point_id)
	{
		this.point_id = point_id;
	}
	public String getSubmitter_opinion()
	{
		return submitter_opinion;
	}
	public String getSubmitter_opinionBr()
	{
		return submitter_opinion.replaceAll("(\r\n|\n|\r)","<br />");
	}
	public void setSubmitter_opinion(String submitter_opinion)
	{
		this.submitter_opinion = submitter_opinion;
	}
	public String getSubmitter_id()
	{
		return submitter_id;
	}
	public void setSubmitter_id(String submitter_id)
	{
		this.submitter_id = submitter_id;
	}
	public Timestamp getSubmission_time()
	{
		return submission_time;
	}
	public void setSubmission_time(Timestamp submission_time)
	{
		this.submission_time = submission_time;
	}
	public long getReply_to_all_forum_index()
	{
		return reply_to_all_forum_index;
	}
	public void setReply_to_all_forum_index(long reply_to_all_forum_index)
	{
		this.reply_to_all_forum_index = reply_to_all_forum_index;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public boolean isbMediatorBtnChecked()
	{
		return bMediatorBtnChecked;
	}
	public void setbMediatorBtnChecked(boolean bMediatorBtnChecked)
	{
		this.bMediatorBtnChecked = bMediatorBtnChecked;
	}
	public int getMediatorValue()
	{
		return mediatorValue;
	}
	public void setMediatorValue(int mediatorValue)
	{
		this.mediatorValue = mediatorValue;
	}
	public String getInsertStmt()
	{
		return "insert into all_forum values(default,"+
				   " "+ app_id+", "+
				   " "+ group_id+", "+
				   " "+ item_id+", "+
				   " "+ point_id+", "+
				   "'"+StringEscapeUtils.escapeSql(submitter_opinion)+"', "+
				   "'"+submitter_id+"', "+
				   "'"+submission_time+"', "+
				   "'"+reply_to_all_forum_index+"', "+
				   "'"+remarks+"')";
	}
	
}
