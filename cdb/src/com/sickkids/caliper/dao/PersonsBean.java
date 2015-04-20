package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class PersonsBean implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4916505269305368226L;
	private String person_id="";
	private String korean_name="";
	private String eng_name="";
	private String email="";
	private String login_password="";
	private String dob=null;//jumin number
	private String sex="";
	private String address="";
	private String city="";
	private String province="";
	private String country="";
	private String postal_code="";
	private String phone1="";
	private String phone2="";
	private boolean want_einfo=false;
	private boolean still_member=false;	
	private String remarks="";
	private Timestamp registered_time=null;
	private String id_classification="";/* teacher, student, etc */
	private String sin_number="";
	private String org_name="";
	
	public String getOrg_name()
	{
		return org_name;
	}
	public void setOrg_name(String org_name)
	{
		this.org_name = org_name;
	}
	//private String reserved="";
	public PersonsBean()
	{
		
	}
	public PersonsBean(String person_id,String korean_name,String eng_name,String email,String login_password,String dob,String sex,String address,String city,
					   String province,String country,String postal_code,String phone1,String phone2,boolean want_einfo,boolean still_member,String remarks, 
					   Timestamp registered_time, String id_classification, String sin_number, String org_name)
	{
		this.person_id=person_id;
		this.korean_name=korean_name;
		this.eng_name=eng_name;
		this.email=email;
		this.login_password=login_password;
		this.dob=dob;//jumin number
		this.sex=sex;
		this.address=address;
		this.city=city;
		this.province=province;
		this.country=country;
		this.postal_code=postal_code;
		this.phone1=phone1;
		this.phone2=phone2;
		this.want_einfo=want_einfo;
		this.still_member=still_member;	
		this.remarks=remarks;
		this.registered_time=registered_time;
		this.id_classification=id_classification;
		this.sin_number=sin_number;
		this.org_name=org_name;
	}
	
	public String getId_classification() 
	{
		return id_classification;
	}
	public void setId_classification(String id_classification) 
	{
		this.id_classification = id_classification;
	}
	public String getSin_number() 
	{
		return sin_number;
	}
	public void setSin_number(String sin_number) 
	{
		this.sin_number = sin_number;
	}
	public Timestamp getRegistered_time() 
	{
		return registered_time;
	}
	public void setRegistered_time(Timestamp registered_time) 
	{
		this.registered_time = registered_time;
	}
	public String getLogin_password() 
	{
		return login_password;
	}
	public void setLogin_password(String login_password) 
	{
		this.login_password = login_password;
	}
	public boolean isStill_member() 
	{
		return still_member;
	}
	public void setStill_member(boolean still_member) 
	{
		this.still_member = still_member;
	}
	public String getPerson_id() 
	{
		return person_id;
	}
	public void setPerson_id(String person_id) 
	{
		this.person_id = person_id;
	}
	public String getKorean_name() 
	{
		return korean_name;
	}
	public void setKorean_name(String korean_name) 
	{
		this.korean_name = korean_name;
	}
	public String getEng_name() 
	{
		return eng_name;
	}
	public void setEng_name(String eng_name) 
	{
		this.eng_name = eng_name;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getDob() 
	{
		return dob;
	}
	public void setDob(String dob) 
	{
		this.dob = dob;
	}
	public String getSex() 
	{
		return sex;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public String getCity() 
	{
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
	public String getProvince() 
	{
		return province;
	}
	public void setProvince(String province) 
	{
		this.province = province;
	}
	public String getCountry() 
	{
		return country;
	}
	public void setCountry(String country) 
	{
		this.country = country;
	}
	public String getPostal_code() 
	{
		return postal_code;
	}
	public void setPostal_code(String postal_code) 
	{
		this.postal_code = postal_code;
	}
	public String getPhone1() 
	{
		return phone1;
	}
	public void setPhone1(String phone1) 
	{
		this.phone1 = phone1;
	}
	public String getPhone2() 
	{
		return phone2;
	}
	public void setPhone2(String phone2) 
	{
		this.phone2 = phone2;
	}
	
	public boolean isWant_einfo() 
	{
		return want_einfo;
	}
	public void setWant_einfo(boolean want_einfo) 
	{
		this.want_einfo = want_einfo;
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
