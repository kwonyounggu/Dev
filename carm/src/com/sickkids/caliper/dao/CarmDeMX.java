package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmDeMX implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6745933368836426417L;
	private int carm_id=0;
	private String site="00";
	private String pat_no="0000";
	private int event_no=0;
	private String de_ids="";
	private int de1_id=0;
	private int de2_id=0;
	private String de3_ids="";
	private int de4_id=0;
	private String de5_ids="";
	private int de6_id=0;
	private String remarks="";
	private Timestamp created_time=null;
	private boolean flag=false;
	private String review_status="not used";//This field is not used
	
	private int current_reviewers_per_item=0;

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public int getCarm_id()
	{
		return carm_id;
	}

	public void setCarm_id(int carm_id)
	{
		this.carm_id = carm_id;
	}

	public String getSite()
	{
		return site;
	}

	public void setSite(String site)
	{
		this.site = site;
	}

	public String getPat_no()
	{
		return pat_no;
	}

	public void setPat_no(String pat_no)
	{
		this.pat_no = pat_no;
	}

	public int getEvent_no()
	{
		return event_no;
	}

	public void setEvent_no(int event_no)
	{
		this.event_no = event_no;
	}

	public String getDe_ids()
	{
		return de_ids;
	}

	public void setDe_ids(String de_ids)
	{
		this.de_ids = de_ids;
	}

	public int getDe1_id()
	{
		return de1_id;
	}

	public void setDe1_id(int de1_id)
	{
		this.de1_id = de1_id;
	}

	public int getDe2_id()
	{
		return de2_id;
	}

	public void setDe2_id(int de2_id)
	{
		this.de2_id = de2_id;
	}

	public String getDe3_ids()
	{
		return de3_ids;
	}

	public void setDe3_ids(String de3_ids)
	{
		this.de3_ids = de3_ids;
	}

	public int getDe4_id()
	{
		return de4_id;
	}

	public void setDe4_id(int de4_id)
	{
		this.de4_id = de4_id;
	}

	public String getDe5_ids()
	{
		return de5_ids;
	}

	public void setDe5_ids(String de5_ids)
	{
		this.de5_ids = de5_ids;
	}

	public int getDe6_id()
	{
		return de6_id;
	}

	public void setDe6_id(int de6_id)
	{
		this.de6_id = de6_id;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public Timestamp getCreated_time()
	{
		return created_time;
	}

	public void setCreated_time(Timestamp created_time)
	{
		this.created_time = created_time;
	}

	public String getReview_status()
	{
		return review_status;
	}

	public void setReview_status(String review_status)
	{
		this.review_status = review_status;
	}

	public int getCurrent_reviewers_per_item()
	{
		return current_reviewers_per_item;
	}

	public void setCurrent_reviewers_per_item(int current_reviewers_per_item)
	{
		this.current_reviewers_per_item = current_reviewers_per_item;
	}

	@Override
	public String toString()
	{
		return "carm_id="+carm_id+" "+
			   "site="+site+" "+
			   "patient no="+pat_no+" "+
			   "event no="+event_no+" "+
			   "de_ids="+de_ids+" "+
			   "de1_id="+de1_id+" "+
			   "de2_id="+de2_id+" "+
			   "de3_ids="+de3_ids+" "+
			   "de4_id="+de4_id+" "+
			   "de5_ids="+de5_ids+" "+
			   "de6_id="+de6_id+" "+
			   "created_time="+created_time+" "+
			   "remarks="+remarks+" "+
			   "flag="+flag+" "+
			   "review_status="+review_status
			   ;
	}
	//check if contains the same site, patient no and event no
	@Override
    public boolean equals(Object o)
    {
        if (o != null && o instanceof CarmDeMX)
        {
        	CarmDeMX b=(CarmDeMX)o;
            return (this.site.equals(b.getSite()) && this.pat_no.equals(b.getPat_no()) && this.event_no==b.getEvent_no());
        }

        return false;
    }

}
