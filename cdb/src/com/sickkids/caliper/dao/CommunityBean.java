package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommunityBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6316185059682869765L;
	private long community_index=0L;
	private int community_class=0;//0: 수업후기, 1: 공지사항,  2: 질문 및 응답, 3: 자주하는질문
	private int qa_class=0;//0:일반, 1:수업료, 2:화상통화, 3:선생님, 4:캠프, 5:영어
	private String person_id="";
	private String person_name="";//korean_name or eng_name
	private String topic_subject="";
	private String contents="";
	private long topic_number=0L;//same as community_index
	private String in_response_to="";//community_index:권영구
	private Timestamp written_date=null;
	private int access_number=0;
	private String delete_password="";
	private int mypage=0;
	private String remarks="";
	
	//not mapping to table records
	private int replies=0;
	private String s_response_to="";
	
	public CommunityBean()
	{
		// TODO Auto-generated constructor stub
	}
	public CommunityBean(long community_index,int community_class,int qa_class, String person_id, String person_name, String topic_subject, String contents, long topic_number,
			String in_response_to, Timestamp written_date, int access_number, String delete_password, int mypage, String remarks)
	{
		this.community_index=community_index;
		this.community_class=community_class;//0: 수업후기, 1: 공지사항,  2: 질문 및 응답, 3: 자주하는질문
		this.qa_class=qa_class;//0:일반, 1:수업료, 2:화상통화, 3:선생님, 4:캠프, 5:영어
		this.person_id=person_id;
		this.person_name=person_name;//korean_name or eng_name
		this.topic_subject=topic_subject;
		this.contents=contents;
		this.topic_number=topic_number;//same as community_index
		this.in_response_to=in_response_to;
		this.written_date=written_date;
		this.access_number=access_number;
		this.delete_password=delete_password;
		this.mypage=mypage;
		this.remarks=remarks;
	}
	
	public String getS_response_to()
	{
		/*if(in_response_to==null||in_response_to.equals("")||in_response_to.equals("0")) return "";
		String[] temp=in_response_to.split(":");
		if(temp==null || temp.length<2) return "";
		return temp[1];*/
		return s_response_to;
	}
	public void setS_response_to(String s_response_to)
	{
		this.s_response_to = s_response_to;
	}
	public int getReplies()
	{
		return replies;
	}
	public void setReplies(int replies)
	{
		this.replies = replies;
	}
	public int getMypage()
	{
		return mypage;
	}

	public void setMypage(int mypage)
	{
		this.mypage = mypage;
	}

	public long getCommunity_index()
	{
		return community_index;
	}

	public void setCommunity_index(long community_index)
	{
		this.community_index = community_index;
	}

	public int getCommunity_class()
	{
		return community_class;
	}

	public void setCommunity_class(int community_class)
	{
		this.community_class = community_class;
	}

	public int getQa_class()
	{
		return qa_class;
	}

	public void setQa_class(int qa_class)
	{
		this.qa_class = qa_class;
	}

	public String getPerson_id()
	{
		return person_id;
	}

	public void setPerson_id(String person_id)
	{
		this.person_id = person_id;
	}

	public String getPerson_name()
	{
		return person_name;
	}

	public void setPerson_name(String person_name)
	{
		this.person_name = person_name;
	}

	public String getTopic_subject()
	{
		return topic_subject;
	}

	public void setTopic_subject(String topic_subject)
	{
		this.topic_subject = topic_subject;
	}

	public String getContents()
	{
		return contents;
	}

	public void setContents(String contents)
	{
		this.contents = contents;
	}

	public long getTopic_number()
	{
		return topic_number;
	}

	public void setTopic_number(long topic_number)
	{
		this.topic_number = topic_number;
	}

	public String getIn_response_to()
	{
		return in_response_to;
	}

	public void setIn_response_to(String in_response_to)
	{
		this.in_response_to = in_response_to;
	}

	public Timestamp getWritten_date()
	{
		return written_date;
	}

	public void setWritten_date(Timestamp written_date)
	{
		this.written_date = written_date;
	}

	public int getAccess_number()
	{
		return access_number;
	}

	public void setAccess_number(int access_number)
	{
		this.access_number = access_number;
	}

	public String getDelete_password()
	{
		return delete_password;
	}

	public void setDelete_password(String delete_password)
	{
		this.delete_password = delete_password;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

}
