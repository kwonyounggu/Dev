package com.sickkids.caliper.proxy;
/*
import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
*/
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.sickkids.caliper.common.ClientInfo;
import com.sickkids.caliper.common.EmailText;
import com.sickkids.caliper.common.MenuLink;
import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.AllApplicationBean;
import com.sickkids.caliper.dao.AllForumBean;
import com.sickkids.caliper.dao.AllLoginHistoryBean;
import com.sickkids.caliper.dao.AllLoginHistoryDao;
import com.sickkids.caliper.dao.AllPointDescBeanM;
import com.sickkids.caliper.dao.AllRegisteredUserBean;
import com.sickkids.caliper.dao.AllRegisteredUserBeanM;
import com.sickkids.caliper.dao.AllReviewDataBean;
import com.sickkids.caliper.dao.AllReviewDataHistBean;
import com.sickkids.caliper.dao.CarmAssayUnitBean;
import com.sickkids.caliper.dao.CarmClinicalSummaryEditBean;
import com.sickkids.caliper.dao.CarmDeMXBeanM;
import com.sickkids.caliper.dao.CarmO_DE2Bean;
import com.sickkids.caliper.dao.CarmO_DE3Bean;
import com.sickkids.caliper.dao.CarmO_DE4Bean;
import com.sickkids.caliper.dao.CarmO_DE5Bean;
import com.sickkids.caliper.dao.CarmO_DE6Bean;
import com.sickkids.caliper.dao.CarmO_DEBean;
import com.sickkids.caliper.dao.CarmSQLDao;
import com.sickkids.caliper.dao.OracleSQLDao;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.exceptions.DAOException;
/*
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.hamcrest.Matchers;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.AnalyteNameBean;
import com.sickkids.caliper.dao.CustomizedRefIntervalInfoBean;
import com.sickkids.caliper.dao.RawDataVersionNBean;

import com.sickkids.caliper.dao.InstrumentBean;

import com.sickkids.caliper.dao.SQLDao;
import com.sickkids.caliper.exceptions.DAOException;
import com.sickkids.caliper.statistics.BoxCox;
import com.sickkids.caliper.statistics.CStatistics;
import com.sickkids.caliper.statistics.RankBean;
import com.sickkids.caliper.statistics.RefInterval;
*/
import flex.messaging.FlexContext;
import flex.messaging.FlexSession;



public class CarmProxy
{
	protected static Logger log = Utils.logger;
	private CarmSQLDao csqlDao=null;
	private ServletContext app=null;
	public HttpServletRequest request=null; 
    public FlexSession session=null; 
    private AllRegisteredUserBeanM crbm=null;
	
	
	public CarmProxy()
	{	
		log.info("CarmProxy() is called in CarmProxy.java");	
		app=FlexContext.getServletContext();
		csqlDao=(CarmSQLDao)app.getAttribute("csqlDao");
		
		request = FlexContext.getHttpRequest(); 
        session = FlexContext.getFlexSession(); 
        
        crbm=(AllRegisteredUserBeanM)session.getAttribute("crbm");
	}
	public String getTestString(String in)
	{
		System.out.println("getTestString("+in+") in CarmProxy.java is called.");
		return in+Calendar.getInstance().getTimeInMillis();
	}
	public AllRegisteredUserBeanM checkSignIn(int appId, String loginId, String password) throws DAOException
	{
		System.out.println("INFO (begin): checkSignIn("+appId+", "+loginId+", "+password+") in CarmProxy.java is called.");
		if(crbm!=null) 
		{
			session.removeAttribute("carm_login_id_m");
			session.removeAttribute("carm_logn_first_name_m");
			session.removeAttribute("carm_login_last_name_m");
			session.removeAttribute("carm_login_password_m");
			session.removeAttribute("app_symbol_m");
			session.removeAttribute("crbm");
			crbm=null;
		}
		
		crbm=csqlDao.getOneRecordFromRegisteredUserTableM("where app_id="+appId+" and user_id='"+loginId+"'");
		if(crbm.getPassword().equals(password))
		{
			session.setAttribute("carm_login_id_m", loginId);
			session.setAttribute("carm_logn_first_name_m", crbm.getFirstName()); 
			session.setAttribute("carm_login_last_name_m", crbm.getLastName());
			session.setAttribute("carm_login_password_m", password);
			session.setAttribute("app_symbol_m", csqlDao.getGenericString("select app_symbol from all_application where app_id="+appId));

			session.setAttribute("crbm", crbm);
		}
		System.out.println("INFO (end): checkSignIn("+crbm.getAppId()+", "+crbm.getUserId()+", "+crbm.getPassword()+", "+crbm.getResetPassword()+") in CarmProxy.java is called.");
		return crbm;
	}
	public boolean savePwd(int appId, String loginId, String password) throws DAOException
	{
		System.out.println("savePwd("+appId+", "+loginId+", "+password+") in CarmProxy.java is called.");
		if(crbm==null) throw new DAOException("session_timeout"+" in savePwd(..) of CarmProxy.java");
		
		System.out.println("carm attributes("+crbm.getAppId()+", "+crbm.getUserId()+", "+crbm.getPassword()+") in savedPwd(...) of CarmProxy.java is called.");
		boolean bSaved=false;
		try
		{
			String sqlCmd="update all_registered_user set password='"+password+"', reset_password=0 where app_id="+appId+" and user_id='"+loginId+"';";

			if(csqlDao.updateRegisteredUserTable(sqlCmd)>0) 
			{
				bSaved=true;
				if(crbm!=null) crbm.setPassword(password);//update the password of the session variable, crbm.
			}
						    
			    
			AllLoginHistoryBean lb=new AllLoginHistoryBean(0L,appId, loginId, Utils.currentTimestamp(),request.getRemoteAddr());
			lb.setRemarks(ClientInfo.getOs(request.getHeader("User-Agent")));
			
			session.setAttribute("all_login_hist_bean_m", new AllLoginHistoryDao((ConnectionPool)app.getAttribute("connectionPool")).create(lb));
			
			//email
			AllRegisteredUserBean tempRegisteredUserBean=crbm.copyM();
			tempRegisteredUserBean.setPassword(password);
			
			EmailText.emailCreateOrUpdatedPassword((String)session.getAttribute("app_symbol_m"), tempRegisteredUserBean, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()),MenuLink.CARM_CENTRAL_CONTEXT, false );
		}
		catch(Exception e)
		{
			log.severe(e.getMessage());
			throw new DAOException(e.getMessage()+" in savePwd(..) of CarmProxy.java");
		}
		return bSaved;
	}
	public boolean sendLoginInfo(int appId, String emailAddr) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in sendLoginInfo(..) of CarmProxy.java");
		
		List<AllRegisteredUserBean> idPasswordList=csqlDao.getListFromRegisteredUserTable("where app_id="+appId+" and email='"+emailAddr+"'");
		
		if(idPasswordList.size()>0) 
		{
			
			EmailText.emailForgottenPassword(csqlDao.getGenericString("select app_symbol from all_application where app_id="+appId), idPasswordList, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()), MenuLink.CARM_CENTRAL_CONTEXT );
			return true;
		}
		return false;
	}
	public List<AllApplicationBean> getAppList() throws DAOException
	{
		//if(crbm==null) throw new DAOException("session_timeout"+" in getAppList(..) of CarmProxy.java");
		return csqlDao.getListFromAllApplicationTable("order by app_id asc");
	}
	public List<CarmDeMXBeanM> getItemList(AllRegisteredUserBeanM ab, String review_status) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getItemList(..) of CarmProxy.java");
		//NOTE: ab=crbm but use ab due to another user bean may be requested
		System.out.println("getItemList("+ab+", "+review_status+") in CarmProxy.java is called.");
		
		List<CarmDeMXBeanM> itemList=null;
		String sqlCmd="select distinct(item_id) from all_item_reviewer_mx ";
		try
		{
			if(review_status.equals("FINALIZE")) //Disagreement is meaningful in ('STARTED','COMPLETE_EDITABLE')
			{
				List<String> finalizeItemList=new ArrayList<String>();
				if(ab.getLoginLevel()==3)//mediators
				{
					sqlCmd+="where app_id=1 and group_id=1 and mediator_id='"+ab.getUserId()+"' and reviewer_action_status in ('COMPLETE_NOT_EDITABLE','INCOMPLETE_EDITABLE') order by item_id asc;";
					List<String> myItemList=csqlDao.getGenericStringList(sqlCmd);
					AllReviewDataBean rb1=null, rb2=null;
					for(String itemId : myItemList)
					{
						List<String> reviewerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+itemId+" and reviewer_action_status in ('COMPLETE_NOT_EDITABLE','INCOMPLETE_EDITABLE')");
						if(reviewerList.size()==2)
						{
							rb1=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+reviewerList.remove(0)+"'");
							rb2=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+reviewerList.remove(0)+"'");
							
							if(rb1.getRemarks().startsWith("FINALIZED") && rb2.getRemarks().startsWith("FINALIZED")) continue;
							else if(rb1.getPoint_id_revision() != rb2.getPoint_id_revision()) finalizeItemList.add(itemId); //checked
							else if((int)rb1.getPoint_value_number() != (int)rb2.getPoint_value_number()) finalizeItemList.add(itemId);//checked
							else  //in case of a revision number which is greater than one, checked
							
								for(int i=1; i<rb1.getPoint_id_revision();i++)
								{
									AllReviewDataHistBean rhb1=csqlDao.getOneRecordFromAllReviewDataHistTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+rb1.getReviewer_id()+"' and point_id_revision="+i);
									AllReviewDataHistBean rhb2=csqlDao.getOneRecordFromAllReviewDataHistTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+rb2.getReviewer_id()+"' and point_id_revision="+i);
									
									if((int)rhb1.getPoint_value_number()!=(int)rhb2.getPoint_value_number())
									{	
										finalizeItemList.add(itemId);
										break;
									}
								}						
						}
						//else, do not allow to be finalized for zero, one or more
					}
				}
				itemList=getEventObjListForDisagreement(ab.getLoginLevel(), finalizeItemList);
			}
			else if(review_status.equals("DISAGREE")) //Disagreement is meaningful in ('STARTED','COMPLETE_EDITABLE')
			{
				List<String> discordantItemList=new ArrayList<String>();
				if(ab.getLoginLevel()==4)//reviewers
				{
					sqlCmd+="where app_id=1 and group_id=1 and reviewer_id='"+ab.getUserId()+"' and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE') order by item_id asc;";
					List<String> myItemList=csqlDao.getGenericStringList(sqlCmd);
					for(String itemId : myItemList)
					{
						List<String> peerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+itemId+" and reviewer_id!='"+ab.getUserId()+"' and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE')");
						AllReviewDataBean myBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+ab.getUserId()+"'");
						if(myBean.getReview_time()!=null) for(String peerId : peerList)
						{
							AllReviewDataBean peerBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+peerId+"'");
							if(peerBean.getReview_time()!=null && myBean.getReview_time()!=null && peerBean.getPoint_value_number()!=myBean.getPoint_value_number())
							{
								discordantItemList.add(itemId);
								break;
							}
						}
					}
				}
				else if(ab.getLoginLevel()==3)//mediators
				{
					sqlCmd+="where app_id=1 and group_id=1 and mediator_id='"+ab.getUserId()+"' and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE') order by item_id asc;";
					List<String> myItemList=csqlDao.getGenericStringList(sqlCmd);
					for(String itemId : myItemList)
					{
						List<String> reviewerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+itemId+" and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE')");
						if(!reviewerList.isEmpty())
						{
							AllReviewDataBean firstBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+reviewerList.remove(0)+"'");
							if(firstBean.getReview_time()!=null) for(String peerId : reviewerList)
							{
								AllReviewDataBean peerBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+peerId+"'");
								if(peerBean.getReview_time()!=null && peerBean.getPoint_value_number()!=firstBean.getPoint_value_number())
								{
									discordantItemList.add(itemId);
									break;
								}
							}
						}
					}
				}
				else
				{
					sqlCmd+="where app_id=1 and group_id=1 and reviewer_action_status ('STARTED','COMPLETE_EDITABLE') order by item_id asc;";
					List<String> myItemList=csqlDao.getGenericStringList(sqlCmd);
					for(String itemId : myItemList)
					{
						List<String> reviewerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+itemId+" and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE')");
						if(!reviewerList.isEmpty())
						{
							AllReviewDataBean firstBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+reviewerList.remove(0)+"'");
							if(firstBean.getReview_time()!=null) for(String peerId : reviewerList)
							{
								AllReviewDataBean peerBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+peerId+"'");
								if(peerBean.getReview_time()!=null && peerBean.getPoint_value_number()!=firstBean.getPoint_value_number())
								{
									discordantItemList.add(itemId);
									break;
								}
							}
						}
					}
				}
				
				itemList=getEventObjListForDisagreement(ab.getLoginLevel(), discordantItemList);
			}
			else
			{
				review_status=(review_status.equals("ALL") ? "%": review_status);
				
				if(ab.getLoginLevel()==4)//reviewers
					sqlCmd+="where app_id=1 and group_id=1 and reviewer_id='"+ab.getUserId()+"' and reviewer_action_status like '"+review_status+"' order by item_id asc;";
				else if(ab.getLoginLevel()==3)//mediators
					sqlCmd+="where app_id=1 and group_id=1 and mediator_id='"+ab.getUserId()+"' and reviewer_action_status like '"+review_status+"' order by item_id asc;";
				else
					sqlCmd+="where app_id=1 and group_id=1 and reviewer_action_status like '"+review_status+"' order by item_id asc;";
				
				itemList=getEventObjList(ab.getLoginLevel(), csqlDao.getGenericStringList(sqlCmd));//"" mean ALL review_status
			}
		}
		catch(Exception e)
		{
			throw new DAOException(e);
		}
		//Utils.delay(5);
		return itemList;
	}
	public List<CarmDeMXBeanM> getEventObjList(int loginLevel, List<String> conditionedItemList) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getEventObjList(..) of CarmProxy.java");
		String sqlWhereStatement="";
		if(loginLevel==3 || loginLevel==4)
		{
			if(conditionedItemList.size()>0) 
			{
				sqlWhereStatement="where carm_id in (";
				for(int i=0;i<conditionedItemList.size();i++) 
				{
					sqlWhereStatement+=conditionedItemList.get(i);
					sqlWhereStatement+=((i+1)==conditionedItemList.size() ? ")" : ",");
				}
			}
			else return new ArrayList<CarmDeMXBeanM>();//no items selected for the given review status for the given reviewer or mediator
		}
		sqlWhereStatement+=" order by carm_id asc";
		
		List<CarmDeMXBeanM> eventObjlist=(List<CarmDeMXBeanM>)csqlDao.getCarmDeMXListM(sqlWhereStatement);
		return eventObjlist;
	}
	public List<CarmDeMXBeanM> getEventObjListForDisagreement(int loginLevel, List<String> conditionedItemList) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getEventObjListForDisagreement(..) of CarmProxy.java");
		String sqlWhereStatement="";
		if(conditionedItemList.size()>0) 
		{
			sqlWhereStatement="where carm_id in (";
			for(int i=0;i<conditionedItemList.size();i++) 
			{
				sqlWhereStatement+=conditionedItemList.get(i);
				sqlWhereStatement+=((i+1)==conditionedItemList.size() ? ")" : ",");
			}
			sqlWhereStatement+=" order by carm_id asc";
			
			return (List<CarmDeMXBeanM>)csqlDao.getCarmDeMXListM(sqlWhereStatement);
		}
		return new ArrayList<CarmDeMXBeanM>();//no items selected for the given review status for the given reviewer or mediator
	}
	//itemId <=> carmId
	//AllRegisteredUserBeanM may be used in the future
	public CarmClinicalSummaryEditBean getClinicalSummary(AllRegisteredUserBeanM ab, String itemId) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getClinicalSummary(..) of CarmProxy.java");
		System.out.println("getClinicalSummary(AllRegisteredUserBeanM ab, "+itemId+") in CarmProxy.java is called.");
		return csqlDao.getCarmClinicalSummaryEditList("where carm_id="+itemId).get(0);
	}
	//AllRegisteredUserBeanM may be used in the future
	public CarmO_DE2Bean getDiagnoses(AllRegisteredUserBeanM ab, String de2Id) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getDiagnoses(..) of CarmProxy.java");
		System.out.println("getDiagnoses(AllRegisteredUserBeanM ab, "+de2Id+") in CarmProxy.java is called.");
		return csqlDao.getDiagnoseRecord("where de2_id="+de2Id);
	}
	//AllRegisteredUserBeanM may be used in the future
	public CarmO_DE4Bean getProblems(AllRegisteredUserBeanM ab, String de4Id) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getProblems(..) of CarmProxy.java");
		System.out.println("getProblems(AllRegisteredUserBeanM ab, "+de4Id+") in CarmProxy.java is called.");
		return csqlDao.getProblemRecord("where de4_id="+de4Id);
	}
	//AllRegisteredUserBeanM may be used in the future
	public List<CarmO_DE5Bean> getLaborotory(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getLaborotory(..) of CarmProxy.java");
		System.out.println("getLaborotory(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) in CarmProxy.java is called.");
		List<CarmO_DE5Bean> list=new ArrayList<CarmO_DE5Bean>();
		CarmO_DE5Bean de5Bean=csqlDao.getLaboratoryRecord("where site='"+cb.getSite()+"' and pat_no='"+cb.getPatNo()+"' and event_no='"+cb.getEventNo()+"' and de_time='1'");
		de5Bean.setHaeHaemoglobinUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='haeHaemoglobin'"));
		de5Bean.setHaeWhiteCellCountUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='haeWhiteCellCount'"));
		de5Bean.setHaeBandsUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='haeBands'"));
		de5Bean.setHaePlateletCountUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='haePlateletCount'"));
		de5Bean.setEleSodiumUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='eleSodium'"));
		de5Bean.setElePotassiumUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='elePotassium'"));
		de5Bean.setEleCalciumTotalUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='eleCalciumTotal'"));
		de5Bean.setEleCalciumIonizedUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='eleCalciumIonized'"));
		de5Bean.setEleMagnesiumUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='eleMagnesium'"));
		de5Bean.setBloPaO2Unit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='bloPaO2'"));
		de5Bean.setBloPaCO2Unit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='bloPaCO2'"));
		de5Bean.setBloHCO3Unit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='bloHCO3'"));
		de5Bean.setBloPhUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='bloPh'"));
		de5Bean.setVenPmvO2Unit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='venPmvO2'"));
		de5Bean.setVenHCO3Unit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='venHCO3'"));
		de5Bean.setMetAmmoniaUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='metAmmonia'"));
		de5Bean.setMetLactateUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='metLactate'"));
		de5Bean.setMetGlucoseUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='metGlucose'"));
		de5Bean.setLiverASTUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='liverAST'"));
		de5Bean.setLiverALTUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='liverALT'"));
		de5Bean.setLiverBilirubinConUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='liverBilirubinCon'"));
		de5Bean.setLiverBilirubinUnconUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='liverBilirubinUncon'"));
		de5Bean.setLiverLDHUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='liverLDH'"));
		de5Bean.setLiverINRUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='liverINR'"));
		de5Bean.setLiverPTTUnit(csqlDao.getGenericString("select assay_unit from carm_assay_unit where site_id='"+cb.getSite()+"' and assay_name='liverPTT'"));
		list.add(de5Bean);
		list.add(csqlDao.getLaboratoryRecord("where site='"+cb.getSite()+"' and pat_no='"+cb.getPatNo()+"' and event_no='"+cb.getEventNo()+"' and de_time='12'"));
		list.add(csqlDao.getLaboratoryRecord("where site='"+cb.getSite()+"' and pat_no='"+cb.getPatNo()+"' and event_no='"+cb.getEventNo()+"' and de_time='48'"));
		list.add(csqlDao.getLaboratoryRecord("where site='"+cb.getSite()+"' and pat_no='"+cb.getPatNo()+"' and event_no='"+cb.getEventNo()+"' and de_time='tests_24'"));
		list.add(csqlDao.getLaboratoryRecord("where site='"+cb.getSite()+"' and pat_no='"+cb.getPatNo()+"' and event_no='"+cb.getEventNo()+"' and de_time='l_limit'"));
		list.add(csqlDao.getLaboratoryRecord("where site='"+cb.getSite()+"' and pat_no='"+cb.getPatNo()+"' and event_no='"+cb.getEventNo()+"' and de_time='u_limit'"));

		System.out.println("getLaborotory(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) in CarmProxy.java is called with list.size()="+list.size());
		return list;
	}
	//AllRegisteredUserBeanM may be used in the future
	public List<CarmO_DEBean> getVitalSign(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getVitalSign(..) of CarmProxy.java");
		System.out.println("getVitalSign(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) in CarmProxy.java is called.");		
		return csqlDao.getVitalSigns("where site='"+cb.getSite()+"' and pat_no='"+cb.getPatNo()+"' and event_no='"+cb.getEventNo()+"' order by week_no, de_time asc");
	}
	public List<CarmO_DE3Bean> getMedications(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getMedications(..) of CarmProxy.java");
		System.out.println("getMedications(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) in CarmProxy.java is called.");	
		return csqlDao.getMedications("where site='"+cb.getSite()+"' and pat_no='"+cb.getPatNo()+"' and event_no='"+cb.getEventNo()+"' order by week_no, hr asc");
	}
	public CarmO_DE6Bean getRadiologyRecord(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getRadiologyRecord(..) of CarmProxy.java");
		System.out.println("getRadiologyRecord(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) in CarmProxy.java is called.");	
		return csqlDao.getRadiologyRecord("where site='"+cb.getSite()+"' and pat_no='"+cb.getPatNo()+"' and event_no='"+cb.getEventNo()+"'");
	}
	public List<AllPointDescBeanM> getAllPointDescList(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getAllPointDescList(..) of CarmProxy.java");
		System.out.println("getAllPointDescList(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) in CarmProxy.java is called.");	
		return csqlDao.getAllPointDescListM("where app_id="+ab.getAppId()+" order by point_id asc");
	}
	public List<AllForumBean> getListFromAllForumTable(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) throws DAOException
	{
		if(crbm==null) throw new DAOException("session_timeout"+" in getListFromAllForumTable(..) of CarmProxy.java");
		System.out.println("getListFromAllForumTable(AllRegisteredUserBeanM ab, CarmDeMXBeanM cb) in CarmProxy.java is called.");	
		return csqlDao.getListFromAllForumTable("where app_id="+ab.getAppId()+" and group_id=1 and item_id="+cb.getCarmId()+" and point_id=3 order by submission_time desc");//point_id=3 means about preventability
	}
	/*
	public List<InstrumentBean> getInstrumentNameList() throws DAOException
	{
		return sqlDao.getInstrumentNameList();
	}
	//The following is to return the name of analytes withing the given instrumentId and metricSystem
	public List<AnalyteNameBean> getAnalyteList(int instrumentId, int metricSystem) throws Exception
	{
		@SuppressWarnings("unchecked")
		List<AnalyteNameBean> analyteList = (List<AnalyteNameBean>)app.getAttribute("analyteList");
		List<AnalyteNameBean> analyteListFromInstrumentId = filter(Matchers.both(having(on(AnalyteNameBean.class).getInstrument_name_id(), Matchers.equalTo(instrumentId))).and(having(on(AnalyteNameBean.class).getMetric_system(), Matchers.equalTo(metricSystem))), analyteList);
		return analyteListFromInstrumentId;
	}

	public List<RawDataVersionNBean> getRawDataList(short instrumentId, int analyteId, double ageFrom, double ageTo, short genderId, short metricSystem)  throws DAOException
	{
		return sqlDao.getRawDataList(instrumentId, analyteId, ageFrom, ageTo, genderId, metricSystem);
	}
	//do it here and flex 
	public CustomizedRefIntervalInfoBean getCustomizedRefIntervals(short instrumentId, int analyteId, double ageFrom, double ageTo, short genderId, short metricSystem, int recordSize)  throws DAOException
	{
		double[] data=sqlDao.getRawDataDoubleArray(instrumentId, analyteId, ageFrom, ageTo, genderId, metricSystem, recordSize);
		
		try
		{
			CStatistics stat=new CStatistics();
			CustomizedRefIntervalInfoBean cb=null;
			int originalSize=data.length;
			
			data=stat.withoutOutliers(data);
			
			if(data.length>120) cb=stat.nonParametric(data, (List<RankBean>) app.getAttribute("rankTable"), originalSize);			
			else if(data.length>=40) cb=stat.robustBoxCox(data, originalSize);
						
			if(cb==null) 
			{
				cb=new CustomizedRefIntervalInfoBean();
				cb.setSamples(originalSize);
				cb.setSamplesWithLT(data.length);
			}
			return cb;//if cb==null, then put an empty object to prevent a crash
		}
		catch(Exception e)
		{
			log.severe(e.getMessage());
			throw new DAOException(e.getMessage()+" in getCustomizedRefIntervals(..) of CarmProxy.java");
		}
	}
	*/
	
	
	
	
	/*
	public List getPatientTestResultMxList() throws DAOException
	{
		return ddao.getPatientTestResultMxList();
	}
	public List getEthnicGenderAgeList() throws DAOException
	{
		return edao.getEthnicGenderAgeList();
	}
	public List getLODList() throws DAOException
	{
		return sqlDao.getLODList();
	}
	public List getPhase1CompleteDataSetJune2011List()throws DAOException
	{
		return pcdj2011Dao.getPhase1CompleteDatSetJune2011List();
	}
	*/
}
