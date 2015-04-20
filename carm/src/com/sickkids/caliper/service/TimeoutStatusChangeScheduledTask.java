package com.sickkids.caliper.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.sickkids.caliper.common.MenuLink;
import com.sickkids.caliper.common.Utils;

import com.sickkids.caliper.dao.AllItemReviewerBean;
import com.sickkids.caliper.dao.AllReviewDataBean;
import com.sickkids.caliper.dao.AllReviewDataHistBean;
import com.sickkids.caliper.dao.CarmClinicalSummaryEditBean;
import com.sickkids.caliper.dao.CarmDeMX;
import com.sickkids.caliper.dao.CarmSQLDao;
import com.sickkids.caliper.dao.OracleSQLDao;


import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;

public class TimeoutStatusChangeScheduledTask extends Task
{
	private OracleSQLDao oracleDao=null;
	private CarmSQLDao csqlDao=null;
	private List<String> emailList=null;
	private List<String> nameList=null;
	
	
	public TimeoutStatusChangeScheduledTask(CarmSQLDao csqlDao, OracleSQLDao oracleDao)
	{
		super();
		this.csqlDao=csqlDao;	
		this.oracleDao=oracleDao;
	}
		
	//call this at 24:00pm = 00:00am
	@Override public void execute(TaskExecutionContext arg0) throws RuntimeException
	{
		try
		{
			Utils.logger.info("TimeoutStatusChangeScheduledTask.execute() is called and performing at "+Utils.getDateTime());
			//System.out.println("current time="+ DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH));
			String sqlCmd="";
			List<AllItemReviewerBean> itemReviewerList=csqlDao.getPeerListFromAllItemReviewerMXTable("where reviewer_action_status NOT LIKE '%_NOT_EDITABLE' and date_part('day', (current_timestamp-time_out))>0");
			for(AllItemReviewerBean ab : itemReviewerList)
			{
				System.out.println(ab.getReviewer_action_status()+", "+ab.getTime_out());
				if(ab.getReviewer_action_status().equals("NOT_STARTED"))//delete
				{
					//The following codes are in op=ajax_carm_central_action_item_reviewer_mx from CarmCentralController.java
					sqlCmd="delete from all_item_reviewer_mx where app_id="+ab.getApp_id()+" and group_id="+ab.getGroup_id()+" and item_id="+ab.getItem_id()+" and reviewer_id='"+ab.getReviewer_id()+"'";
					csqlDao.updateInsertGenericSql3Cmds(sqlCmd,
														AllReviewDataBean.deleteStatement(ab.getApp_id(), ab.getGroup_id(), ab.getItem_id(), ab.getReviewer_id()),
														AllReviewDataHistBean.deleteStatement(ab.getApp_id(), ab.getGroup_id(), ab.getItem_id(), ab.getReviewer_id())
														);
					
					//if there exists an item in the group of the app then no action
					//else remove the item from the item list.
					sqlCmd="select * from all_item_reviewer_mx where app_id="+ab.getApp_id()+" and item_id="+ab.getItem_id();//regardless of group_id
					if(!csqlDao.existSameKey(sqlCmd))
						csqlDao.updateInsertGenericSqlCmd("delete from all_item where app_id="+ab.getApp_id()+" and item_id="+ab.getItem_id());//regardless of group_id
				}
				else if(ab.getReviewer_action_status().startsWith("STARTED"))
				{
					//update only the record
					csqlDao.updateInsertGenericSqlCmd(ab.getNewUpdateStmt("INCOMPLETE_NOT_EDITABLE"));
				}
				else if(ab.getReviewer_action_status().startsWith("COMPLETE"))
				{
					//update only the record
					csqlDao.updateInsertGenericSqlCmd(ab.getNewUpdateStmt("COMPLETE_NOT_EDITABLE"));
				}
			}

			itemReviewerList.clear();
			Utils.logger.info("TimeoutStatusChangeScheduledTask.execute() is ending at "+Utils.getDateTime());
		}
		catch(Exception e)
		{
			Utils.logger.info("ERROR: "+e+" in execute(.) of CarmScheduledTask.java");
		}
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
