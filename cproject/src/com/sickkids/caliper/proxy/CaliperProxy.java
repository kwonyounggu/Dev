package com.sickkids.caliper.proxy;

import java.util.List;
import java.util.logging.Logger;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.DiagnosticTestResultDao;
import com.sickkids.caliper.dao.EthnicGenderAgeDao;
import com.sickkids.caliper.dao.PatientDao;
import com.sickkids.caliper.dao.Phase1CompleteDataSetJune2011Dao;
import com.sickkids.caliper.dao.SQLDao;
import com.sickkids.caliper.exceptions.DAOException;



public class CaliperProxy
{
	protected static Logger log = Utils.logger;
	//private Bio424Mp1HuffmanDao bio424HuffmanDao=new Bio424Mp1HuffmanDao();
	//private Bio424Mp1GeneticAlgoDao bio424GeneticAlgoDao=new Bio424Mp1GeneticAlgoDao();
	private PatientDao pdao=new PatientDao();
	private EthnicGenderAgeDao edao=new EthnicGenderAgeDao();
	private DiagnosticTestResultDao ddao=new DiagnosticTestResultDao();
	private SQLDao sqlDao=new SQLDao();
	private Phase1CompleteDataSetJune2011Dao pcdj2011Dao=new Phase1CompleteDataSetJune2011Dao();
	
	public CaliperProxy()
	{	
		log.info("CaliperProxy() is called in CaliperProxy.java");	
	}
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
	
}
