package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AllRegisteredUserBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8001067104530250928L;
	private int app_id=0;
	private String user_id="first_name7.last_name9.01";
	private String first_name="";
	private String last_name="";
	private int login_level=-1;
	private String hospital_id="00";
	private String email="";
	private String password="";
	private String department="";
	private String phone="";
	private int reset_password=1;//0=false, 1=true	
	private String position="";
	private String description="";
	private char initial='\0';
	private String accessible_ids="";//1001,1002...
	private Timestamp access_limit_time=null;
	private long access_limit_time_long=0L;
	private String s_access_limit_time="07/01/2017";
	private String practice_year="<0(For Student)";
	private String health_discipline="";
	private String primay_clinical_practice="";
	private int seniority=0;
	
	private int max_groups_to_review=0;
	private String item_assignment_type="";//ALL-ITEMS/EACH-ITEM
	private boolean my_review_confirmatory=true;
	private Timestamp creation_time=null;
	private String creator_id="";
	private String remarks="";
	
	public long getAccess_limit_time_long()
	{
		return access_limit_time.getTime();
	}
	public String getPractice_year()
	{
		return practice_year;
	}
	public void setPractice_year(String practice_year)
	{
		this.practice_year = practice_year;
	}
	public String getHealth_discipline()
	{
		return health_discipline;
	}
	public void setHealth_discipline(String health_discipline)
	{
		this.health_discipline = health_discipline;
	}
	public String getPrimay_clinical_practice()
	{
		return primay_clinical_practice;
	}
	public void setPrimay_clinical_practice(String primay_clinical_practice)
	{
		this.primay_clinical_practice = primay_clinical_practice;
	}
	public int getSeniority()
	{
		return seniority;
	}
	public void setSeniority(int seniority)
	{
		this.seniority = seniority;
	}
	public String getS_access_limit_time()
	{
		if(access_limit_time==null)
			return s_access_limit_time;
		else
			return new SimpleDateFormat("MM/dd/yyyy").format(access_limit_time);
	}
	public String getAccessible_ids()
	{
		return accessible_ids;
	}

	public void setAccessible_ids(String accessible_ids)
	{
		this.accessible_ids = accessible_ids;
	}

	public Timestamp getAccess_limit_time()
	{
		return access_limit_time;
	}

	public void setAccess_limit_time(Timestamp access_limit_time)
	{
		this.access_limit_time = access_limit_time;
	}


	public AllRegisteredUserBean()
	{

	}

	public char getInitial()
	{
		return first_name.toCharArray()[0];
	}


	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getFirst_name()
	{
		return first_name;
	}

	public void setFirst_name(String first_name)
	{
		this.first_name = Character.toUpperCase(first_name.charAt(0))+first_name.substring(1).toLowerCase();
	}

	public String getLast_name()
	{
		return last_name;
	}

	public void setLast_name(String last_name)
	{
		this.last_name = Character.toUpperCase(last_name.charAt(0))+last_name.substring(1).toLowerCase();
	}

	public int getLogin_level()
	{
		return login_level;
	}

	public void setLogin_level(int login_level)
	{
		this.login_level = login_level;
	}

	public String getHostpital_id()
	{
		return hospital_id;
	}

	public void setHostpital_id(String hospital_id)
	{
		this.hospital_id = hospital_id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public int getReset_password()
	{
		return reset_password;
	}

	public void setReset_password(int reset_password)
	{
		this.reset_password = reset_password;
	}

	public Timestamp getCreation_time()
	{
		return creation_time;
	}

	public void setCreation_time(Timestamp creation_time)
	{
		this.creation_time = creation_time;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public int getApp_id()
	{
		return app_id;
	}
	public void setApp_id(int app_id)
	{
		this.app_id = app_id;
	}
	public int getMax_groups_to_review()
	{
		return max_groups_to_review;
	}
	public void setMax_groups_to_review(int max_groups_to_review)
	{
		this.max_groups_to_review = max_groups_to_review;
	}
	public String getItem_assignment_type()
	{
		return item_assignment_type;
	}
	public void setItem_assignment_type(String item_assignment_type)
	{
		this.item_assignment_type = item_assignment_type;
	}
	public boolean isMy_review_confirmatory()
	{
		return my_review_confirmatory;
	}
	public void setMy_review_confirmatory(boolean my_review_confirmatory)
	{
		this.my_review_confirmatory = my_review_confirmatory;
	}
	public String getCreator_id()
	{
		return creator_id;
	}
	public void setCreator_id(String creator_id)
	{
		this.creator_id = creator_id;
	}
	public AllRegisteredUserBean copy()
	{
		AllRegisteredUserBean temp=new AllRegisteredUserBean();
		temp.app_id=this.app_id;
		temp.user_id=this.user_id;
		temp.first_name=this.first_name;
		temp.last_name=this.last_name;
		temp.login_level=this.login_level;
		temp.hospital_id=this.hospital_id;
		temp.email=this.email;
		temp.password=this.password;
		temp.department=this.department;
		temp.phone=this.phone;
		temp.reset_password=this.reset_password;
		temp.creation_time=this.creation_time;
		temp.position=this.position;
		temp.description=this.description;
		temp.remarks=this.remarks;
		temp.accessible_ids=this.accessible_ids;
		temp.access_limit_time=this.access_limit_time;
		temp.practice_year=this.practice_year;
		temp.health_discipline=this.health_discipline;
		temp.primay_clinical_practice=this.primay_clinical_practice;
		temp.seniority=this.seniority;
				
		return temp;
	}
	public String toString()
	{
		return "user_id="+user_id+", "+
		"first_name="+first_name+", "+
		"last_name="+last_name+", "+
		"hospital_id="+hospital_id+", "+
		"department="+department+", "+
		"position="+position+", "+
		"email="+email+", "+
		"phone="+phone+", "+
		"login_level="+login_level+", "+
		"description="+description+", "+
		"remarks="+remarks+", "+
		"accessible_ids="+accessible_ids+", "+
		"access_limit_time="+access_limit_time+", "+
		"practice_year="+practice_year+", "+
		"health_discipline="+health_discipline+", "+
		"primay_clinical_practice="+primay_clinical_practice+", "+
		"seniority="+seniority;
	}
	public String toStringParameters()
	{
		return 
				""+app_id+
				"&"+user_id+
				"&"+first_name+
				"&"+last_name+
				"&"+login_level+
				"&"+hospital_id+
				"&"+email+
				"&"+password+
				"&"+department+
				"&"+phone+
				"&"+reset_password+
				"&"+position+
				"&"+description+
				"&"+accessible_ids+
				"&"+getAccess_limit_time_long()+
				"&"+practice_year+
				"&"+health_discipline+
				"&"+primay_clinical_practice+
				"&"+seniority+
				"&"+max_groups_to_review+
				"&"+item_assignment_type+
				"&"+my_review_confirmatory+
				"&"+creation_time+
				"&"+creator_id+
				"&"+remarks;
	}

}
