package com.sickkids.caliper.service;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import com.sickkids.caliper.common.Utils;

import com.sickkids.caliper.dao.CarmClinicalSummaryEditBean;
import com.sickkids.caliper.dao.CarmDeMX;
import com.sickkids.caliper.dao.CarmSQLDao;
import com.sickkids.caliper.dao.OracleSQLDao;


import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;

public class CarmScheduledTask extends Task
{
	private OracleSQLDao oracleDao=null;
	private CarmSQLDao csqlDao=null;
	private List<String> emailList=null;
	private List<String> nameList=null;
	
	public CarmScheduledTask(CarmSQLDao csqlDao, OracleSQLDao oracleDao)
	{
		super();
		this.csqlDao=csqlDao;	
		this.oracleDao=oracleDao;
	}
		
	//call this at 24:00pm = 00:00am
	@Override public void execute(TaskExecutionContext arg0) throws RuntimeException
	{
		List<CarmDeMX> unique_carm_list=null;
		try
		{
			Utils.logger.info("CarmScheduledTask.execute() is called and performing at "+Utils.getDateTime());
			unique_carm_list = csqlDao.getCarmDeMXList("");
			oracleDao.getCarmDeMXList(unique_carm_list);//get carm_id, site, pat_no, event_no
			oracleDao.getCarmDeMX_DE_Fields(unique_carm_list, this.csqlDao);//get de_id, de1_id ...
			//oracleDao.getCarmDeMX_DE_Fields(unique_carm_list);//Original Dec-29-2014
			
			//for(int i=0;i<unique_carm_list.size();i++)
			//	System.out.println("["+i+"]="+(CarmDeMX)unique_carm_list.get(i));
			
			csqlDao.createCarmDeMX_Records(unique_carm_list);// ***** Create 1st **********
			
			List<CarmClinicalSummaryEditBean> summaryList=oracleDao.getOnlyClinicalSummary(unique_carm_list);
			csqlDao.createCarmClinicalSummary_Records(summaryList);// ***** Create 2nd **********
			
			Utils.logger.info("CarmScheduledTask.execute() is ending at "+Utils.getDateTime());
		}
		catch(Exception e)
		{
			Utils.logger.info("ERROR: "+e+" in execute(.) of CarmScheduledTask.java");
		}
		if(unique_carm_list!=null && unique_carm_list.size()>0) unique_carm_list.clear();
	}

	public Calendar dueDate(Timestamp start_date)
	{
		Calendar due_entry=Calendar.getInstance();
		due_entry.setTimeInMillis(start_date.getTime());
		due_entry.add(Calendar.MONTH, 1);
		due_entry.add(Calendar.SECOND, -1);
		
		return due_entry;
	}
	public boolean monthAhead(Timestamp start_date, int m)
	{
		Calendar m_month_ahead_entry=Calendar.getInstance();
		m_month_ahead_entry.setTimeInMillis(start_date.getTime());
		m_month_ahead_entry.add(Calendar.MONTH, m);
		
		Calendar now=Calendar.getInstance();
		
		return now.getTimeInMillis()>m_month_ahead_entry.getTimeInMillis();
	}

	
}
