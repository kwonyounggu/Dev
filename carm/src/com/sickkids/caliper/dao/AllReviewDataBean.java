package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringEscapeUtils;

public class AllReviewDataBean implements Serializable
{
	private static final long serialVersionUID = 2147987750702804393L;

	private int app_id=-1;
	private int group_id=-1;
	private int item_id=-1;
	private String item_hierachy="";
	private int point_id=-1;
	private String point_value_string="";
	private double point_value_number=-1000000.0;
	private double point_value_min=0.0;//future use
	private double point_value_max=1000000.0;//future use
	private String point_value_units="";//future use
	private String point_value_options="";//future use
	private float confidence_level=-1;
	private Timestamp review_time=null;//revised time
	private String reviewer_id="";
	private String point_review_status="";//future use
	private int point_id_revision=0;
	private String remarks="";//revised
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
	public String getItem_hierachy()
	{
		return item_hierachy;
	}
	public void setItem_hierachy(String item_hierachy)
	{
		this.item_hierachy = item_hierachy;
	}
	public int getPoint_id()
	{
		return point_id;
	}
	public void setPoint_id(int point_id)
	{
		this.point_id = point_id;
	}
	public String getPoint_value_string()
	{
		return point_value_string;
	}
	public void setPoint_value_string(String point_value_string)
	{
		this.point_value_string = point_value_string;
	}
	public double getPoint_value_number()
	{
		return point_value_number;
	}
	public void setPoint_value_number(double point_value_number)
	{
		this.point_value_number = point_value_number;
	}
	public double getPoint_value_min()
	{
		return point_value_min;
	}
	public void setPoint_value_min(double point_value_min)
	{
		this.point_value_min = point_value_min;
	}
	public double getPoint_value_max()
	{
		return point_value_max;
	}
	public void setPoint_value_max(double point_value_max)
	{
		this.point_value_max = point_value_max;
	}
	public String getPoint_value_units()
	{
		return point_value_units;
	}
	public void setPoint_value_units(String point_value_units)
	{
		this.point_value_units = point_value_units;
	}
	public String getPoint_value_options()
	{
		return point_value_options;
	}
	public void setPoint_value_options(String point_value_options)
	{
		this.point_value_options = point_value_options;
	}
	public float getConfidence_level()
	{
		return confidence_level;
	}
	public void setConfidence_level(float confidence_level)
	{
		this.confidence_level = confidence_level;
	}
	public Timestamp getReview_time()
	{
		return review_time;
	}
	public void setReview_time(Timestamp review_time)
	{
		this.review_time = review_time;
	}
	public String getReviewer_id()
	{
		return reviewer_id;
	}
	public void setReviewer_id(String reviewer_id)
	{
		this.reviewer_id = reviewer_id;
	}
	public String getPoint_review_status()
	{
		return point_review_status;
	}
	public void setPoint_review_status(String point_review_status)
	{
		this.point_review_status = point_review_status;
	}
	
	public int getPoint_id_revision()
	{
		return point_id_revision;
	}
	public void setPoint_id_revision(int point_id_revision)
	{
		this.point_id_revision = point_id_revision;
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
		return "insert into all_review_data values("+
				   " "+ app_id+", "+
				   " "+ group_id+", "+
				   " "+ item_id+", "+
				   "'"+item_hierachy+"', "+
				   " "+ point_id+", "+
				   "'"+StringEscapeUtils.escapeSql(point_value_string)+"', "+
				   "'"+point_value_number+"', "+
				   "'"+point_value_min+"', "+
				   "'"+point_value_max+"', "+
				   "'"+point_value_units+"', "+
				   "'"+point_value_options+"', "+
				   "'"+confidence_level+"', "+
				   "'"+review_time+"', "+
				   "'"+reviewer_id+"', "+
				   "'"+point_review_status+"', "+
				   " "+point_id_revision+", "+
				   "'"+remarks+"')";
	}
	public String sameRecord()
	{
		return "select point_id from all_review_data where "+
			   "app_id="+app_id+
			   " and group_id="+group_id+
			   " and item_id="+item_id+
			   " and point_id="+point_id+
			   " and reviewer_id='"+reviewer_id+"'";
	}
	public String samePointValueNumber()
	{
		return "select point_id from all_review_data where "+
			   "app_id="+app_id+
			   " and group_id="+group_id+
			   " and item_id="+item_id+
			   " and point_id="+point_id+
			   " and reviewer_id='"+reviewer_id+"'"+
			   " and point_value_number="+point_value_number;
	}
	public String samePointValueString()
	{
		return "select point_id from all_review_data where "+
			   "app_id="+app_id+
			   " and group_id="+group_id+
			   " and item_id="+item_id+
			   " and point_id="+point_id+
			   " and reviewer_id='"+reviewer_id+"'"+
			   " and point_value_string='"+StringEscapeUtils.escapeSql(point_value_string)+"'";
	}
	public String getUpdateWithoutRevisionIncrement()
	{
		return "update all_review_data set "+
					   "item_hierachy='"+item_hierachy+"', "+
					   "point_value_string='"+StringEscapeUtils.escapeSql(point_value_string)+"', "+
					   "point_value_number='"+point_value_number+"', "+					   
					   "confidence_level='"+confidence_level+"', "+
					   "review_time='"+review_time+"', "+
					   "reviewer_id='"+reviewer_id+"', "+
					   "point_review_status='"+point_review_status+"', "+
					   "remarks='"+remarks+"' "+
					   "where app_id="+app_id+" and group_id="+group_id+" and item_id="+item_id+" and point_id="+point_id+" and reviewer_id='"+reviewer_id+"'";
					
	}
	public String getUpdateWithRevisionIncrement()
	{
		return "update all_review_data set "+
					   "item_hierachy='"+item_hierachy+"', "+
					   "point_value_string='"+StringEscapeUtils.escapeSql(point_value_string)+"', "+
					   "point_value_number='"+point_value_number+"', "+					   
					   "confidence_level='"+confidence_level+"', "+
					   "review_time='"+review_time+"', "+
					   "reviewer_id='"+reviewer_id+"', "+
					   "point_review_status='"+point_review_status+"', "+
					   "point_id_revision=(point_id_revision+1)"+", "+
					   "remarks='"+remarks+"' "+
					   "where app_id="+app_id+" and group_id="+group_id+" and item_id="+item_id+" and point_id="+point_id+" and reviewer_id='"+reviewer_id+"'";					
	}
	public static String deleteStatement(int app_id, int group_id, int item_id, String reviewer_id)
	{
		return "delete from all_review_data where app_id="+app_id+" and group_id="+group_id+" and item_id="+item_id+" and reviewer_id='"+reviewer_id+"'";
	}
}
