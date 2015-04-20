package com.sickkids.caliper.proxy;

import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

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

import flex.messaging.FlexContext;



public class CaliperProxy
{
	protected static Logger log = Utils.logger;
	private SQLDao sqlDao=new SQLDao();
	private ServletContext app=null;
	
	//private Bio424Mp1HuffmanDao bio424HuffmanDao=new Bio424Mp1HuffmanDao();
	//private Bio424Mp1GeneticAlgoDao bio424GeneticAlgoDao=new Bio424Mp1GeneticAlgoDao();
	/*private PatientDao pdao=new PatientDao();
	private EthnicGenderAgeDao edao=new EthnicGenderAgeDao();
	private DiagnosticTestResultDao ddao=new DiagnosticTestResultDao();	
	private Phase1CompleteDataSetJune2011Dao pcdj2011Dao=new Phase1CompleteDataSetJune2011Dao();
	*/
	
	public CaliperProxy()
	{	
		log.info("CaliperProxy() is called in CaliperProxy.java");	
		app=FlexContext.getServletContext();
	}
	public String getTestString(String in)
	{
		System.out.println("getTestString("+in+") in CaliperProxy.java is called.");
		return in+Calendar.getInstance().getTimeInMillis();
	}
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
			throw new DAOException(e.getMessage()+" in getCustomizedRefIntervals(..) of CaliperProxy.java");
		}
	}
	
	
	
	
	
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
