package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserAccessBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1602651167990080402L;

	private int idx=0;
	private String operation="";
	private String remoteIP="123.123.123.123";
	private String remoteHost="www.myesl.ca";
	private String locale="";
	private String encoding="";
	private String sessionID="";
	private String cid="";
	private long order_id=0L;
	private long hitCounter=0L;
	private Timestamp hitTimestamp=null;
	
	public UserAccessBean()
	{
		// TODO Auto-generated constructor stub
	}
	public UserAccessBean(int idx,String operation,String remoteIP,String remoteHost,String locale, 
						  String encoding,String sessionID,String cid,long order_id, long hitCounter,Timestamp hitTimestamp)
	{
		this.idx=idx;
		this.operation=operation;
		this.remoteIP=remoteIP;
		this.remoteHost=remoteHost;
		this.locale=locale;
		this.encoding=encoding;
		this.sessionID=sessionID;
		this.cid=cid;
		this.order_id=order_id;
		this.hitCounter=hitCounter;
		this.hitTimestamp=hitTimestamp;
	}
	public String toString()
	{
		return idx+" "+operation+" "+remoteIP+" "+remoteHost+" "+locale+" "+encoding+" "+
			   sessionID+" "+cid+" "+order_id+" "+hitCounter+" "+hitTimestamp.toString();
	}
	public String getCid()
	{
		return cid;
	}
	public void setCid(String cid)
	{
		this.cid = cid;
	}
	public String getEncoding()
	{
		return encoding;
	}
	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}
	public long getHitCounter()
	{
		return hitCounter;
	}
	public void setHitCounter(long hitCounter)
	{
		this.hitCounter = hitCounter;
	}
	public Timestamp getHitTimestamp()
	{
		return hitTimestamp;
	}
	public void setHitTimestamp(Timestamp hitTimestamp)
	{
		this.hitTimestamp = hitTimestamp;
	}
	public int getIdx()
	{
		return idx;
	}
	public void setIdx(int idx)
	{
		this.idx = idx;
	}
	public String getLocale()
	{
		return locale;
	}
	public void setLocale(String locale)
	{
		this.locale = locale;
	}
	public long getOrder_id()
	{
		return order_id;
	}
	public void setOrder_id(long order_id)
	{
		this.order_id = order_id;
	}
	public String getRemoteHost()
	{
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost)
	{
		this.remoteHost = remoteHost;
	}
	public String getRemoteIP()
	{
		return remoteIP;
	}
	public void setRemoteIP(String remoteIP)
	{
		this.remoteIP = remoteIP;
	}
	public String getSessionID()
	{
		return sessionID;
	}
	public void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}
	public String getOperation()
	{
		return operation;
	}
	public void setOperation(String operation)
	{
		this.operation = operation;
	}

}
