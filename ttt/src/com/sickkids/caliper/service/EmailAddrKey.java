package com.sickkids.caliper.service;

public class EmailAddrKey implements Comparable<EmailAddrKey>
{
	private String email;
	private String name;
	private String user_id;
	public EmailAddrKey(String email, String name, String user_id)
	{
		this.email=email;
		this.name=name;
		this.user_id=user_id;
	}
	
	@Override public int hashCode()
	{
		return this.user_id.toString().hashCode();
	}

	@Override public boolean equals(Object o)
	{
		if(o==this) return true;
		if(o==null || !(o instanceof EmailAddrKey)) return false;
		EmailAddrKey cp= EmailAddrKey.class.cast(o);
		return user_id.equals(cp.user_id);
	}

	@Override public String toString()
	{
	   return "email: "+email+", name: "+name+", user_id: "+user_id;
	}
	
	public int compareTo(EmailAddrKey cp)
	{
		if(cp==this) return 0;
		return user_id.compareTo(cp.user_id);
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	
}
