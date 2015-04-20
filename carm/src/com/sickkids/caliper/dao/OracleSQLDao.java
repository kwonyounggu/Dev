package com.sickkids.caliper.dao;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.ConnectionPoolDataSource;

import org.apache.commons.lang.StringEscapeUtils;

import oracle.ucp.jdbc.PoolDataSource;

import com.sickkids.caliper.common.FormType;
import com.sickkids.caliper.common.OracleEventTable;
import com.sickkids.caliper.common.Utils;

import com.sickkids.caliper.exceptions.DAOException;


public class OracleSQLDao
{
	private ConnectionPoolDataSource ds=null;
	private PoolDataSource upc=null;
	private Random randGen=new Random();
	
	public OracleSQLDao(PoolDataSource upc)
	{
		this.upc=upc;
	}
	public OracleSQLDao(ConnectionPoolDataSource ds)
	{
		this.ds=ds;
	}
	
	public Connection getConnection() throws DAOException
	{
		Connection c=null;
		try
		{
			c=this.ds.getPooledConnection().getConnection();
		}
		catch (Exception e)
		{
			Utils.logger.severe(e.getMessage()+" getConnection() in OracleSQLDao.java.");
			throw new DAOException(e+", getConnection() in OracleSQLDao.java");
		}
		return c;
	}
	/*public Connection getConnection() throws DAOException
	{
		Connection c=null;
		try
		{
			c=this.upc.getConnection();
		}
		catch (Exception e)
		{
			Utils.logger.severe(e.getMessage()+" getConnection() in OracleSQLDao.java.");
			throw new DAOException(e+", getConnection() in OracleSQLDao.java");
		}
		return c;
	}*/
	public void getCarmDeMXList(List<CarmDeMX> list) throws DAOException
	{
		Utils.logger.info("getCarmDeMXList() of OracleSQLDao.java is starting ...");
		Connection c = null;
		String sQuery="";
		try
		{			
			Utils.logger.info("getCarmDeMXList() of OracleSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getCarmDeMXList() of OracleSQLDao.java");
			c = this.getConnection();
			Statement s=c.createStatement();
			sQuery="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE where site IS NOT NULL and pat_no IS NOT NULL and event_no IS NOT NULL order by site, pat_no, event_no asc";
			addAfterChecking(s.executeQuery(sQuery), list);

			for(int i=1;i<7;i++)
			{
				sQuery="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE"+i+" where site IS NOT NULL and pat_no IS NOT NULL and event_no IS NOT NULL order by site, pat_no, event_no asc";
				addAfterChecking(s.executeQuery(sQuery), list);
			}
			s.close();
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getCarmDeMXList() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getCarmDeMXList() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}	        	
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getCarmDeMXList() of OracleSQLDao.java is ending with "+list.size());
	}
	private void addAfterChecking(ResultSet rs, List<CarmDeMX> list) throws DAOException, SQLException
	{
		int new_carm_id=9999;
		while (rs.next())
		{
			CarmDeMX cb=new CarmDeMX();
			cb.setSite(rs.getString(2));
			cb.setPat_no(rs.getString(3));
			cb.setEvent_no(rs.getInt(4));
			if(!list.contains(cb)) //check unique(site, pat_no, event_no)
			{
					while(true)
				    {
						new_carm_id=randGen.nextInt((9999-1000)+1)+1000;
						if(!exists(list, new_carm_id))
						{
							cb.setCarm_id(new_carm_id);
							cb.setFlag(true);//new
							cb.setCreated_time(Utils.currentTimestamp());
							list.add(cb);
							break;
						}
				    }			
			}
		}
		rs.close();
	}
	public boolean exists(List<CarmDeMX> list, int new_carm_id)
	{
		for(CarmDeMX cb : list)
			if(cb.getCarm_id()==new_carm_id) return true;
		return false;
	}
	/*
	public void getCarmDeMX_DE_Fields(List<CarmDeMX> list) throws DAOException
	{
		Utils.logger.info("getCarmDeMX_DE_Fields(List<CarmDeMX> list) of OracleSQLDao.java is starting...");
		Connection c = null;
		String[] sQuery=new String[7];
		try
		{		
			Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getCarmDeMX_DE_Fields() of OracleSQLDao.java");
			c = this.getConnection();		
			Statement s=c.createStatement();
			ResultSet rs=null;
			String sDe_ids="";
			for(CarmDeMX cb : list)
			{
				if(cb.isFlag())//new
				{
					sQuery[0]="select de_id from "+Utils.ORALCE_DB+".EPOCH_EV_DE where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no()+" order by de_id asc";
					sQuery[1]="select de1_id from "+Utils.ORALCE_DB+".EPOCH_EV_DE1 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no();
					sQuery[2]="select de2_id from "+Utils.ORALCE_DB+".EPOCH_EV_DE2 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no();
					sQuery[4]="select de4_id from "+Utils.ORALCE_DB+".EPOCH_EV_DE4 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no();
					sQuery[6]="select de6_id from "+Utils.ORALCE_DB+".EPOCH_EV_DE6 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no();
					sQuery[3]="select de3_id from "+Utils.ORALCE_DB+".EPOCH_EV_DE3 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no()+" order by de3_id asc";
					sQuery[5]="select de5_id from "+Utils.ORALCE_DB+".EPOCH_EV_DE5 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no()+" order by de5_id asc";
					rs = s.executeQuery(sQuery[0]);
					sDe_ids="";
					while(rs.next()) sDe_ids+=rs.getInt(1)+","; rs.close();
					if(sDe_ids.length()>0) sDe_ids=sDe_ids.substring(0, sDe_ids.length()-1);
					cb.setDe_ids(sDe_ids);									
					
					rs = s.executeQuery(sQuery[3]);
					sDe_ids="";
					while(rs.next()) sDe_ids+=rs.getInt(1)+","; rs.close();
					if(sDe_ids.length()>0) sDe_ids=sDe_ids.substring(0, sDe_ids.length()-1);
					cb.setDe3_ids(sDe_ids);
					
					rs = s.executeQuery(sQuery[5]);
					sDe_ids="";
					while(rs.next()) sDe_ids+=rs.getInt(1)+","; rs.close();
					if(sDe_ids.length()>0) sDe_ids=sDe_ids.substring(0, sDe_ids.length()-1);
					cb.setDe5_ids(sDe_ids);
					
					rs = s.executeQuery(sQuery[1]);
					if(rs.next()) cb.setDe1_id(rs.getInt(1)); rs.close();
					
					rs = s.executeQuery(sQuery[2]);
					if(rs.next()) cb.setDe2_id(rs.getInt(1)); rs.close();
					
					rs = s.executeQuery(sQuery[4]);
					if(rs.next()) cb.setDe4_id(rs.getInt(1)); rs.close();
					
					rs = s.executeQuery(sQuery[6]);
					if(rs.next()) cb.setDe6_id(rs.getInt(1)); rs.close();
				}
			}	
			s.close();
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getCarmDeMX_DE_Fields() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getCarmDeMX_DE_Fields() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getCarmDeMX_DE_Fields() of OracleSQLDao.java is ending with "+list.size());
	}*/
	public void getCarmDeMX_DE_Fields(List<CarmDeMX> list, CarmSQLDao csqlDao) throws DAOException, Exception
	{
		Utils.logger.info("getCarmDeMX_DE_Fields(List<CarmDeMX> list) of OracleSQLDao.java is starting...");
		Connection c = null;
		String[] sQuery=new String[7];
		try
		{		
			Utils.logger.info("getCarmDeMX_DE_Fields() of OracleSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getCarmDeMX_DE_Fields() of OracleSQLDao.java");
			c = this.getConnection();		
			Statement s=c.createStatement();
			ResultSet rs=null;
			String sDe_ids="";
			for(CarmDeMX cb : list)
			{
				if(cb.isFlag())//new
				{
					sQuery[0]="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no()+" order by de_id asc";
					sQuery[1]="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE1 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no();
					sQuery[2]="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE2 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no();
					sQuery[4]="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE4 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no();
					sQuery[6]="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE6 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no();
					sQuery[3]="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE3 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no()+" order by de3_id asc";
					sQuery[5]="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE5 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no()+" order by de5_id asc";
					rs = s.executeQuery(sQuery[0]);
					sDe_ids="";
					while(rs.next()) 
					{
						sDe_ids+=rs.getInt(1)+","; 
						csqlDao.updateInsertGenericSqlCmd(toPostGresDE(rs));
					}					
					rs.close();
					if(sDe_ids.length()>0) sDe_ids=sDe_ids.substring(0, sDe_ids.length()-1);
					cb.setDe_ids(sDe_ids);									
					
					rs = s.executeQuery(sQuery[3]);
					sDe_ids="";
					while(rs.next()) 
					{
						sDe_ids+=rs.getInt(1)+","; 
						csqlDao.updateInsertGenericSqlCmd(toPostGresDE3(rs));
					}
					rs.close();
					if(sDe_ids.length()>0) sDe_ids=sDe_ids.substring(0, sDe_ids.length()-1);
					cb.setDe3_ids(sDe_ids);
					
					rs = s.executeQuery(sQuery[5]);
					sDe_ids="";
					while(rs.next()) 
					{
						sDe_ids+=rs.getInt(1)+","; 
						csqlDao.updateInsertGenericSqlCmd(toPostGresDE5(rs));
					}
					rs.close();
					if(sDe_ids.length()>0) sDe_ids=sDe_ids.substring(0, sDe_ids.length()-1);
					cb.setDe5_ids(sDe_ids);
					
					rs = s.executeQuery(sQuery[1]);
					if(rs.next()) 
					{
						cb.setDe1_id(rs.getInt(1)); 
					}
					rs.close();
					
					rs = s.executeQuery(sQuery[2]);
					if(rs.next()) 
					{
						cb.setDe2_id(rs.getInt(1)); 
						csqlDao.updateInsertGenericSqlCmd(toPostGresDE2(rs));
					}
					rs.close();
					
					rs = s.executeQuery(sQuery[4]);
					if(rs.next()) 
					{
						cb.setDe4_id(rs.getInt(1)); 
						csqlDao.updateInsertGenericSqlCmd(toPostGresDE4(rs));
					}
					rs.close();
					
					rs = s.executeQuery(sQuery[6]);
					if(rs.next()) 
					{
						cb.setDe6_id(rs.getInt(1)); 
						csqlDao.updateInsertGenericSqlCmd(toPostGresDE6(rs));
					}
					rs.close();
				}
			}	
			s.close();
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getCarmDeMX_DE_Fields() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getCarmDeMX_DE_Fields() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getCarmDeMX_DE_Fields() of OracleSQLDao.java is ending with "+list.size());
	}
	public List<CarmClinicalSummaryEditBean> getOnlyClinicalSummary(List<CarmDeMX> list) throws DAOException
	{
		Utils.logger.info("getOnlyClinicalSummary(List<CarmDeMX> list) of OracleSQLDao.java is starting...");
		Connection c = null;
		List<CarmClinicalSummaryEditBean> retList=new ArrayList<CarmClinicalSummaryEditBean>();
		String sQuery="";
		
		try
		{		
			Utils.logger.info("getOnlyClinicalSummary() of OracleSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getOnlyClinicalSummary() of OracleSQLDao.java");
			c = this.getConnection();
			
			Statement s=c.createStatement();
			ResultSet rs=null;
			
			for(CarmDeMX cb : list)
			{
				if(cb.isFlag())//new
				{
					sQuery="select CLIN_SUMMARY from "+Utils.ORALCE_DB+".EPOCH_EV_DE1 where site='"+cb.getSite()+"' and pat_no='"+cb.getPat_no()+"' and event_no="+cb.getEvent_no()+"";					
					rs = s.executeQuery(sQuery);
					
					if(rs.next()) 
					{
						CarmClinicalSummaryEditBean csb=new CarmClinicalSummaryEditBean();
						csb.setCarm_id(cb.getCarm_id());
						csb.setOld_clinical_summary(rs.getString(1));
						retList.add(csb); 
					}
					
					rs.close();
				}
			}	
			s.close();
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getOnlyClinicalSummary() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getOnlyClinicalSummary() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getOnlyClinicalSummary() of OracleSQLDao.java is ending with "+retList.size());
		return retList;
	}
	
	public CarmO_DE2Bean getDiagnoseRecord(String whereStatment) throws DAOException
	{

		Connection c = null;
		String sQuery="";
		CarmO_DE2Bean de2Bean=new CarmO_DE2Bean();
		try
		{			
			Utils.logger.info("getDiagnoseRecord() of OracleSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getDiagnoseRecord() of OracleSQLDao.java");
			c = this.getConnection();
			
			sQuery="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE2 "+whereStatment;
			ResultSet rs = c.createStatement().executeQuery(sQuery);
			if(rs.next())
			{
				de2Bean.setDE2_ID(rs.getInt(1));
				de2Bean.setSITE(rs.getString(2));
				de2Bean.setPAT_NO(rs.getString(3));
				de2Bean.setEVENT_NO(rs.getInt(4));
				de2Bean.setWEEK_NO(rs.getInt(5));
				
				de2Bean.setRsAirwayLower(rs.getString(6));
				de2Bean.setRsAirwayUpper(rs.getString(7));
				de2Bean.setRsChronicLungDisease(rs.getString(8));
				de2Bean.setRsOtherDisease(rs.getString(9));
				de2Bean.setRsOtherList(rs.getString(10));
				
				de2Bean.setHcStructuralDiseaseNotCyanotic(rs.getString(11));
				de2Bean.setHcStructuralDiseaseCyanotic(rs.getString(12));
				de2Bean.setHcPulmonary(rs.getString(13));
				de2Bean.setHcSystemic(rs.getString(14));
				de2Bean.setHcVascular(rs.getString(15));
				de2Bean.setHcVasculitis(rs.getString(16));
				de2Bean.setHcOtherDisease(rs.getString(17));
				de2Bean.setHcOtherList(rs.getString(18));
				
				de2Bean.setNeuroSeizures(rs.getString(19));
				de2Bean.setNeuroHydrocphalus(rs.getString(20));
				de2Bean.setNeuroStatic(rs.getString(21));
				de2Bean.setNeuroProgressive(rs.getString(22));
				de2Bean.setNeuroMyopathy(rs.getString(23));
				de2Bean.setNeuroOtherList(rs.getString(24));
				
				de2Bean.setGastroLiver(rs.getString(25));
				de2Bean.setGastroIntestinalBleeding(rs.getString(26));
				de2Bean.setGastroMalabsorbtion(rs.getString(27));
				de2Bean.setGastroIntestinalDisease(rs.getString(28));
				de2Bean.setGastroOtherList(rs.getString(29));
				
				de2Bean.setRenalImpairment(rs.getString(30));
				de2Bean.setRenalDisease(rs.getString(31));
				de2Bean.setRenalEletrolyte(rs.getString(32));
				de2Bean.setRenalOtherList(rs.getString(33));
				
				de2Bean.setMusJoint(rs.getString(34));
				de2Bean.setMusSytemic(rs.getString(35));
				de2Bean.setMusOrthopaedic(rs.getString(36));
				de2Bean.setMusScoliosis(rs.getString(37));
				de2Bean.setMusCutaneous(rs.getString(38));
				de2Bean.setMusOtherList(rs.getString(39));
				
				de2Bean.setHaeMalignancyHae(rs.getString(40));
				de2Bean.setHaeMalignancyOrgan(rs.getString(41));
				de2Bean.setHaeNonMalignancy(rs.getString(42));
				de2Bean.setHaePrimary(rs.getString(43));
				de2Bean.setHaeAcquired(rs.getString(44));
				de2Bean.setHaeBone(rs.getString(45));
				de2Bean.setHaeSolidOrgan(rs.getString(46));
				de2Bean.setHaeProthrombotic(rs.getString(47));
				de2Bean.setHaeBleeding(rs.getString(48));
				de2Bean.setHaeOtherList(rs.getString(49));
				
				de2Bean.setEndoMetabolic(rs.getString(50));
				de2Bean.setEndoDiabetes(rs.getString(51));
				de2Bean.setEndoEndocrineDisease(rs.getString(52));
				de2Bean.setEndoOtherList(rs.getString(53));
				
				de2Bean.setAllKnown(rs.getString(54));
				de2Bean.setAllOther(rs.getString(55));
				de2Bean.setAllOtherList(rs.getString(56));
				
				de2Bean.setDevicesOxygen(rs.getString(57));
				de2Bean.setDevicesTrachNoVent(rs.getString(58));
				de2Bean.setDevicesTrachVent(rs.getString(59));
				de2Bean.setDevicesVentricularInternal(rs.getString(60));
				de2Bean.setDevicesVentricularExternal(rs.getString(61));
				de2Bean.setDevicesInternalPacemaker(rs.getString(62));
				de2Bean.setDevicesWheelchair(rs.getString(63));
				de2Bean.setDevicesGastro(rs.getString(64));
				de2Bean.setDevicesIndwellingVas(rs.getString(65));
				de2Bean.setDevicesIndwellingHamo(rs.getString(66));
				de2Bean.setDevicesPeritoneal(rs.getString(67));
				de2Bean.setDevicesOtherList(rs.getString(68));
				
				de2Bean.setEnteredBy(rs.getString(69));
				de2Bean.setUpdatedOn(rs.getTimestamp(70));
				
				
			}
			rs.close();
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getDiagnoseRecord() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getDiagnoseRecord() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return de2Bean;
	}
	public CarmO_DE4Bean getProblemRecord(String whereStatment) throws DAOException
	{

		Connection c = null;
		String sQuery="";
		CarmO_DE4Bean de4Bean=new CarmO_DE4Bean();
		try
		{		
			Utils.logger.info("getProblemRecord() of OracleSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getProblemRecord() of OracleSQLDao.java");
			c = this.getConnection();
			
			sQuery="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE4 "+whereStatment;
			ResultSet rs = c.createStatement().executeQuery(sQuery);
			if(rs.next())
			{
				de4Bean.setDE4_ID(rs.getInt(1));
				de4Bean.setSITE(rs.getString(2));
				de4Bean.setPAT_NO(rs.getString(3));
				de4Bean.setEVENT_NO(rs.getInt(4));
				de4Bean.setWEEK_NO(rs.getInt(5));
				
				de4Bean.setRsUpperAirwayObstruction(rs.getString(6));
				de4Bean.setRsBronchospasm(rs.getString(7));
				de4Bean.setRsConsolidation(rs.getString(8));
				de4Bean.setRsPneumothorax(rs.getString(9));
				de4Bean.setRsPleuralEffusion(rs.getString(10));
				de4Bean.setRsPulmonaryOedema(rs.getString(11));
				de4Bean.setRsPulmonaryHemorrhage(rs.getString(12));
				de4Bean.setRsThoracicSurgery(rs.getString(13));
				de4Bean.setRsThoracicTrauma(rs.getString(14));
				de4Bean.setRsOtherList(rs.getString(15));
				
				de4Bean.setHcArrhythmia(rs.getString(16));
				de4Bean.setHcCardiomypathy(rs.getString(17));
				de4Bean.setHcCardiacSurgery(rs.getString(18));
				de4Bean.setHcHaemorrhagicShock(rs.getString(19));
				de4Bean.setHcPericardialEffusion(rs.getString(20));
				de4Bean.setHcSepticShock(rs.getString(21));
				de4Bean.setHcSystemicHypertension(rs.getString(22));
				de4Bean.setHcPulmonaryHypertension(rs.getString(23));
				de4Bean.setHcBTshunt(rs.getString(24));
				de4Bean.setHcArterialThrombosis(rs.getString(25));
				de4Bean.setHcVenousThrombosis(rs.getString(26));
				de4Bean.setHcOtherList(rs.getString(27));
				
				de4Bean.setAbAbdominalSepsis(rs.getString(28));
				de4Bean.setAbAbdominalTrauma(rs.getString(29));
				de4Bean.setAbBleedingIntraabdominal(rs.getString(30));
				de4Bean.setAbSevereDiarrhoea(rs.getString(31));
				de4Bean.setAbBowelPerforation(rs.getString(32));
				de4Bean.setAbBowelObstruction(rs.getString(33));
				de4Bean.setAbAbominalSurgery(rs.getString(34));
				de4Bean.setAbAcuteLiverDysfunction(rs.getString(35));
				de4Bean.setAbOtherList(rs.getString(36));
				
				de4Bean.setRenalAcuteRenalDysfunction(rs.getString(37));
				de4Bean.setRenalHyponatraemia(rs.getString(38));
				de4Bean.setRenalElectrolyteDisorder(rs.getString(39));
				de4Bean.setRenalUrologicSurgery(rs.getString(40));
				de4Bean.setRenalOtherList(rs.getString(41));
				
				de4Bean.setHaeMalignancyNotInRemission(rs.getString(42));
				de4Bean.setHaeChemotherapy(rs.getString(43));
				de4Bean.setHaeAnaemia(rs.getString(44));
				de4Bean.setHaeThrombocytopenia(rs.getString(45));
				de4Bean.setHaeLeucopeniaNeutropaenia(rs.getString(46));
				de4Bean.setHaeCoagulopathy(rs.getString(47));
				de4Bean.setHaeGraftVsHostDisease(rs.getString(48));
				de4Bean.setHaeTransplantRejection(rs.getString(49));
				de4Bean.setHaeSystemicInflammatoryDisease(rs.getString(50));
				de4Bean.setHaeSevereCutaneousDisease(rs.getString(51));
				de4Bean.setHaeOtherList(rs.getString(52));
				
				de4Bean.setEndoAdrenalInsufficiency(rs.getString(53));
				de4Bean.setEndoHyperAmmonemia(rs.getString(54));
				de4Bean.setEndoHyperGlycaemia(rs.getString(55));
				de4Bean.setEndoHypoGlycaemia(rs.getString(56));
				de4Bean.setEndoKetoAcidosis(rs.getString(57));
				de4Bean.setEndoOtherList(rs.getString(58));
				
				de4Bean.setNeuroAcuteEncephalopathy(rs.getString(59));
				de4Bean.setNeuroBulbarDysfunction(rs.getString(60));
				de4Bean.setNeuroHydrocephalus(rs.getString(61));
				de4Bean.setNeuroIntracranialHaemorrhage(rs.getString(62));
				de4Bean.setNeuroParesisOfLimbs(rs.getString(63));
				de4Bean.setNeuroParesisOfDiaphragm(rs.getString(64));
				de4Bean.setNeuroNeurologicSurgery(rs.getString(65));
				de4Bean.setNeuroSeizures(rs.getString(66));
				de4Bean.setNeuroTraumaticBrainInjury(rs.getString(67));
				de4Bean.setNeuroOtherList(rs.getString(68));
				
				de4Bean.setInfectionCentralNervousSystem(rs.getString(69));
				de4Bean.setInfectionSofttissue(rs.getString(70));
				de4Bean.setInfectionRespiratory(rs.getString(71));
				de4Bean.setInfectionIntraabdominal(rs.getString(72));
				de4Bean.setInfectionUrinary(rs.getString(73));
				de4Bean.setInfectionBlood(rs.getString(74));
				de4Bean.setInfectionOtherPrimarySite(rs.getString(75));
				de4Bean.setInfectionOtherList(rs.getString(76));
								
				de4Bean.setEnteredBy(rs.getString(77));
				de4Bean.setUpdatedOn(rs.getTimestamp(78));
				
				de4Bean.seteOthSpec(rs.getString(79));
				
				
			}
			rs.close();
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getProblemRecord() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getProblemRecord() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return de4Bean;
	}
	public CarmO_DE5Bean getLaboratoryRecord(String whereStatment) throws DAOException
	{

		Connection c = null;
		String sQuery="";
		CarmO_DE5Bean de5Bean=new CarmO_DE5Bean();
		try
		{		
			Utils.logger.info("getLaboratoryRecord() of OracleSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getLaboratoryRecord() of OracleSQLDao.java");
			c = this.getConnection();
			
			sQuery="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE5 "+whereStatment;
			Utils.logger.info("sQuery="+sQuery+" in getLaboratoryRecord() of OracleSQLDao.java");
			ResultSet rs = c.createStatement().executeQuery(sQuery);
			if(rs.next())
			{
				de5Bean.setDE5_ID(rs.getInt(1));
				de5Bean.setSITE(rs.getString(2));
				de5Bean.setPAT_NO(rs.getString(3));
				de5Bean.setEVENT_NO(rs.getInt(4));
				de5Bean.setWEEK_NO(rs.getInt(5));
				
				de5Bean.setEventHourLimit(rs.getString(6));
				if(de5Bean.getEventHourLimit().equals("tests_24"))
				{
					de5Bean.setHaeHaemoglobin(rs.getString("HGB_NO")==null? -10000 : rs.getDouble("HGB_NO"));//do like this for this section and number section above
					de5Bean.setHaeWhiteCellCount(rs.getString("WBC_NO")==null? -10000 : rs.getDouble("WBC_NO"));
					de5Bean.setHaeBands(rs.getString("BANDS_NO")==null? -10000 : rs.getDouble("BANDS_NO"));
					de5Bean.setHaePlateletCount(rs.getString("PLT_NO")==null? -10000 : rs.getDouble("PLT_NO"));
					
					de5Bean.setEleSodium(rs.getString("NA_NO")==null? -10000 : rs.getDouble("NA_NO"));
					de5Bean.setElePotassium(rs.getString("POT_NO")==null? -10000 : rs.getDouble("POT_NO"));
					de5Bean.setEleCalciumTotal(rs.getString("CA_TOT_NO")==null? -10000 : rs.getDouble("CA_TOT_NO"));
					de5Bean.setEleCalciumIonized(rs.getString("CA_ION_NO")==null? -10000 : rs.getDouble("CA_ION_NO"));
					de5Bean.setEleMagnesium(rs.getString("MAG_NO")==null? -10000 : rs.getDouble("MAG_NO"));
					
					de5Bean.setBloPaO2(rs.getString("A_PAO2_NO")==null? -10000 : rs.getDouble("A_PAO2_NO"));
					de5Bean.setBloPaCO2(rs.getString("A_PACO2_NO")==null? -10000 : rs.getDouble("A_PACO2_NO"));
					de5Bean.setBloHCO3(rs.getString("A_HCO3_NO")==null? -10000 : rs.getDouble("A_HCO3_NO"));
					de5Bean.setBloPh(rs.getString("A_PH_NO")==null? -10000 : rs.getDouble("A_PH_NO"));
					
					de5Bean.setVenPmvO2(rs.getString("V_PMVO2_NO")==null? -10000 : rs.getDouble("V_PMVO2_NO"));
					de5Bean.setVenHCO3(rs.getString("V_HCO3_NO")==null? -10000 : rs.getDouble("V_HCO3_NO"));
					
					de5Bean.setMetAmmonia(rs.getString("AMMONIA_NO")==null? -10000 : rs.getDouble("AMMONIA_NO"));
					de5Bean.setMetLactate(rs.getString("LAC_NO")==null? -10000 : rs.getDouble("LAC_NO"));
					de5Bean.setMetGlucose(rs.getString("GLU_NO")==null? -10000 : rs.getDouble("GLU_NO"));
					
					de5Bean.setLiverAST(rs.getString("AST_NO")==null? -10000 : rs.getDouble("AST_NO"));
					de5Bean.setLiverALT(rs.getString("ALT_NO")==null? -10000 : rs.getDouble("ALT_NO"));
					de5Bean.setLiverBilirubinCon(rs.getString("BILI_CONJ_NO")==null? -10000 : rs.getDouble("BILI_CONJ_NO"));
					de5Bean.setLiverBilirubinUncon(rs.getString("BILI_UNC_NO")==null? -10000 : rs.getDouble("BILI_UNC_NO"));
					de5Bean.setLiverLDH(rs.getString("LDH_NO")==null? -10000 : rs.getDouble("LDH_NO"));
					de5Bean.setLiverINR(rs.getString("INR_NO")==null? -10000 : rs.getDouble("INR_NO"));
					de5Bean.setLiverPTT(rs.getString("PTT_NO")==null? -10000 : rs.getDouble("PTT_NO"));
				}
				else
				{
					de5Bean.setHaeHaemoglobin(rs.getString("HGB")==null? -10000 : rs.getDouble("HGB"));//do like this for this section and number section above
					de5Bean.setHaeWhiteCellCount(rs.getString("WBC")==null? -10000 : rs.getDouble("WBC"));
					de5Bean.setHaeBands(rs.getString("BANDS")==null? -10000 : rs.getDouble("BANDS"));
					de5Bean.setHaePlateletCount(rs.getString("PLT")==null? -10000 : rs.getDouble("PLT"));
					
					de5Bean.setEleSodium(rs.getString("NA")==null? -10000 : rs.getDouble("NA"));
					de5Bean.setElePotassium(rs.getString("POT")==null? -10000 : rs.getDouble("POT"));
					de5Bean.setEleCalciumTotal(rs.getString("CA_TOT")==null? -10000 : rs.getDouble("CA_TOT"));
					de5Bean.setEleCalciumIonized(rs.getString("CA_ION")==null? -10000 : rs.getDouble("CA_ION"));
					de5Bean.setEleMagnesium(rs.getString("MAG")==null? -10000 : rs.getDouble("MAG"));
					
					de5Bean.setBloPaO2(rs.getString("A_PAO2")==null? -10000 : rs.getDouble("A_PAO2"));
					de5Bean.setBloPaCO2(rs.getString("A_PACO2")==null? -10000 : rs.getDouble("A_PACO2"));
					de5Bean.setBloHCO3(rs.getString("A_HCO3")==null? -10000 : rs.getDouble("A_HCO3"));
					de5Bean.setBloPh(rs.getString("A_PH")==null? -10000 : rs.getDouble("A_PH"));
					
					de5Bean.setVenPmvO2(rs.getString("V_PMVO2")==null? -10000 : rs.getDouble("V_PMVO2"));
					de5Bean.setVenHCO3(rs.getString("V_HCO3")==null? -10000 : rs.getDouble("V_HCO3"));
					
					de5Bean.setMetAmmonia(rs.getString("AMMONIA")==null? -10000 : rs.getDouble("AMMONIA"));
					de5Bean.setMetLactate(rs.getString("LAC")==null? -10000 : rs.getDouble("LAC"));
					de5Bean.setMetGlucose(rs.getString("GLU")==null? -10000 : rs.getDouble("GLU"));
					
					de5Bean.setLiverAST(rs.getString("AST")==null? -10000 : rs.getDouble("AST"));
					de5Bean.setLiverALT(rs.getString("ALT")==null? -10000 : rs.getDouble("ALT"));
					de5Bean.setLiverBilirubinCon(rs.getString("BILI_CONJ")==null? -10000 : rs.getDouble("BILI_CONJ"));
					de5Bean.setLiverBilirubinUncon(rs.getString("BILI_UNC")==null? -10000 : rs.getDouble("BILI_UNC"));
					de5Bean.setLiverLDH(rs.getString("LDH")==null? -10000 : rs.getDouble("LDH"));
					de5Bean.setLiverINR(rs.getString("INR")==null? -10000 : rs.getDouble("INR"));
					de5Bean.setLiverPTT(rs.getString("PTT")==null? -10000 : rs.getDouble("PTT"));
				}
				
				
			}
			rs.close();
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getLaboratoryRecord() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getLaboratoryRecord() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return de5Bean;
	}
	public List<CarmO_DEBean> getVitalSigns(String where_statment) throws DAOException
	{

		Connection c = null;
		List<CarmO_DEBean> retList=new ArrayList<CarmO_DEBean>();
		String sQuery="";
		try
		{			
			Utils.logger.info("getVitalSigns() of OracleSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getVitalSigns() of OracleSQLDao.java");
			c = this.getConnection();
			
			sQuery="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE "+where_statment;
			ResultSet rs = c.createStatement().executeQuery(sQuery);
			while (rs.next())
			{
				CarmO_DEBean db=new CarmO_DEBean();

				db.setDE_ID(rs.getInt(1));
				db.setSITE(rs.getString(2));
				db.setPAT_NO(rs.getString(3));
				db.setEVENT_NO(rs.getInt(4));
				db.setWEEK_NO(rs.getInt(5));
				
				db.setHrPreevent(rs.getString("DE_TIME")==null? -10000 : rs.getInt("DE_TIME"));
				db.setHeartRate(rs.getString("HR")==null? -10000 : rs.getFloat("HR"));
				db.setCompression(rs.getString("COMPRESSION")==null? "": rs.getString("COMPRESSION"));
				db.setRespRate(rs.getString("RR")==null? -10000 : rs.getFloat("RR"));
				db.setSaturation(rs.getString("SAT")==null? -10000 : rs.getFloat("SAT"));
				db.setSystolicBp(rs.getString("SBP")==null? -10000 : rs.getFloat("SBP"));
				db.setDiastolicBp(rs.getString("DBP")==null? -10000 : rs.getFloat("DBP"));//DB-DATA TYPE=VARCHAR
				db.setCapillaryRefill(rs.getString("CAP_REF_NUMBER")==null? -10000 : rs.getInt("CAP_REF_NUMBER"));//DB-DATA TYPE=VARCHAR, NUMBER
				db.setTemperature(rs.getString("TEMPER")==null? -10000 : rs.getFloat("TEMPER"));
				db.setConsciousnessAvpu(rs.getString("CONS_AVPU")==null? "" : rs.getString("CONS_AVPU"));
				db.setConsciousnessGcs(rs.getString("CONS_GCS")==null? -10000 : rs.getFloat("CONS_GCS"));
				db.setPupils(rs.getString("PUPILS")==null? "" : rs.getString("PUPILS"));
				db.setO2Method(rs.getString("O2_METHOD")==null? "" : rs.getString("O2_METHOD"));
				db.setO2AmountUnit(rs.getString("O2_UNITS")==null? "" : rs.getString("O2_UNITS"));
				db.setO2Amount(rs.getString("O2_AMOUNT")==null? -10000 : rs.getFloat("O2_AMOUNT"));
				db.setRespEffort(rs.getString("RESP_EFF")==null? "" : rs.getString("RESP_EFF"));
				db.setStridor(rs.getString("STRIDOR")==null? "" : rs.getString("STRIDOR"));
				db.setTotalIvFluid(rs.getString("IV_TOT")==null? -10000 : rs.getFloat("IV_TOT"));
				db.setUrineOutput(rs.getString("UO")==null? -10000 : rs.getFloat("UO"));
				db.setUrineYesNo(rs.getString("URINE")==null? "" : rs.getString("URINE"));
				db.setNarcoticInfusion(rs.getString("NARC")==null? "" : rs.getString("NARC"));
				db.setPhysicianReview(rs.getString("MD_REVIEW")==null? "" : rs.getString("MD_REVIEW"));
				db.setStatCall(rs.getString("STAT")==null? "" : rs.getString("STAT"));
				db.setNursePatientRatio(rs.getString("NP_RATIO")==null? "" : rs.getString("NP_RATIO"));
				db.setParentPresent(rs.getString("PARENT")==null? "" : rs.getString("PARENT"));
				
				db.setEnteredBy(rs.getString("ENTERED_BY"));
				db.setUpdatedOn(rs.getTimestamp("UPDATED_ON"));

				retList.add(db);
				
			}

		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getVitalSigns() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getVitalSigns() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        	Utils.logger.info("sqlCmd="+sQuery+" in getVitalSigns() in OracleSQLDao.java");
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return retList;
	}
	public List<CarmO_DE3Bean> getMedications(String where_statment) throws DAOException
	{

		Connection c = null;
		List<CarmO_DE3Bean> retList=new ArrayList<CarmO_DE3Bean>();
		String sQuery="";
		try
		{	
			Utils.logger.info("getMedications() of OracleSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getMedications() of OracleSQLDao.java");
			c = this.getConnection();
			
			sQuery="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE3 "+where_statment;
			ResultSet rs = c.createStatement().executeQuery(sQuery);
			while (rs.next())
			{
				CarmO_DE3Bean db=new CarmO_DE3Bean();

				db.setDE3_ID(rs.getInt(1));
				db.setSITE(rs.getString(2));
				db.setPAT_NO(rs.getString(3));
				db.setEVENT_NO(rs.getInt(4));
				db.setWEEK_NO(rs.getInt(5));
				
				db.setHour(rs.getString("HR")==null? -10000 : rs.getInt("HR"));
				db.setRoute(rs.getString("ROUTE")==null? "" : rs.getString("ROUTE"));
				db.setMedication(rs.getString("MEDICATION")==null? "" : rs.getString("MEDICATION"));
				
				db.setEnteredBy(rs.getString("ENTERED_BY"));
				db.setUpdatedOn(rs.getTimestamp("UPDATED_ON"));

				retList.add(db);
				
			}

		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getMedications() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getMedications() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        	Utils.logger.info("sqlCmd="+sQuery+" in getMedications() in OracleSQLDao.java");
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return retList;
	}
	public CarmO_DE6Bean getRadiologyRecord(String whereStatment) throws DAOException
	{

		Connection c = null;
		String sQuery="";
		CarmO_DE6Bean de6Bean=new CarmO_DE6Bean();
		try
		{				
			Utils.logger.info("getRadiologyRecord() of OracleSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getRadiologyRecord() of OracleSQLDao.java");
			c = this.getConnection();
			
			sQuery="select * from "+Utils.ORALCE_DB+".EPOCH_EV_DE6 "+whereStatment;
			ResultSet rs = c.createStatement().executeQuery(sQuery);
			if(rs.next())
			{
				de6Bean.setDE6_ID(rs.getInt(1));
				de6Bean.setSITE(rs.getString(2));
				de6Bean.setPAT_NO(rs.getString(3));
				de6Bean.setEVENT_NO(rs.getInt(4));
				de6Bean.setWEEK_NO(rs.getInt(5));
				

				de6Bean.setChestImaging(rs.getString(6));
				de6Bean.setCnsImaging(rs.getString(7));
				de6Bean.setAbdominalImaging(rs.getString(8));
				de6Bean.setOtherImaging(rs.getString(9));
				
				de6Bean.setEnteredBy(rs.getString(10));
				de6Bean.setUpdatedOn(rs.getTimestamp(11));	
			}
			rs.close();
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getRadiologyRecord() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getRadiologyRecord() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return de6Bean;
	}
	/**************************************************************************************************
	 * Note: remove the semicolon at the end of statement. It will cause an error: invalid character.
	 * @param ob
	 * @return
	 * @throws DAOException
	 */
	public OracleCommonEventBean getDataEntryInformation(OracleCommonEventBean ob) throws DAOException
	{

		Connection c = null;
		String sQuery="";
		try
		{				
			c = this.getConnection();
			
			sQuery="select count(*) from all_tables where table_name='"+ob.getOracle_event_table().name()+"'";
			ResultSet rs = c.createStatement().executeQuery(sQuery);
			if(rs.next())
			{
				if(rs.getInt(1)>0) //there exists the table
				{
					switch(ob.getForm_type())
					{
						
						case CD: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EV_CD.name()+
									    " where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+""; break;
						case DE: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EV_DE.name()+
					    				" where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+""; break;
						case HM: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EV_HM.name()+
					    				" where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+""; break;
						case HR: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EV_HR48.name()+
					    				" where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+""; break;
						case RR: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EV_RR.name()+
					    				" where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+""; break;
						case RT: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EV_RT.name()+
					    				" where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+""; break;
						case SC: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EV_SC.name()+
					    				" where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+""; break;
						case UP: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EV_UP.name()+
					    				" where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+""; break;
						case DR: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_DRR.name()+
										" where SITE='"+ob.getHospital_id()+"' and WEEK_NO="+ob.getWeek_no()+" and RAND_NO="+ob.getEvent_no()+""; break;//added RAND_NO on April 03/2012
						case BP: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_BP.name()+
										" where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+""; break;
						case HA: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_ACTIVITY.name()+
										" where SITE='"+ob.getHospital_id()+"' and WEEK_NO="+ob.getWeek_no()+""; break;
						case HW: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_WARDS.name()+
										" where SITE='"+ob.getHospital_id()+"' and PERIOD="+ob.getWeek_no()+""; break;
						case HP: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_HP.name()+
										" where SITE='"+ob.getHospital_id()+"' and PERIOD="+ob.getWeek_no()+""; break;
						case DI: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_DI.name()+
										" where SITE='"+ob.getHospital_id()+"' and SUBJECT_ID="+ob.getWeek_no()+""; break;
						case SD: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_SD.name()+
										" where SITE='"+ob.getHospital_id()+"' and SUBJECT_ID="+ob.getWeek_no()+""; break;
						case EE: sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EE.name()+
										" where SITE='"+ob.getHospital_id()+"' and SUBJECT_ID="+ob.getWeek_no()+""; break;
						default: break;
					}
					if(ob.getForm_type()==FormType.DE)
					{
						rs = c.createStatement().executeQuery(sQuery);
						if(rs.next())
						{
							ob.setInputter(rs.getString(1));
							ob.setEntered_date(rs.getTimestamp(2));
						}
						else
						{
							for(int i=1;i<=6;i++)
							{
								sQuery="select ENTERED_BY, UPDATED_ON from "+Utils.ORALCE_DB+"."+OracleEventTable.EPOCH_EV_DE.name()+i+
			    						" where SITE='"+ob.getHospital_id()+"' and EVENT_NO="+ob.getEvent_no()+" and WEEK_NO="+ob.getWeek_no()+"";
								rs = c.createStatement().executeQuery(sQuery);
								if(rs.next())
								{
									ob.setInputter(rs.getString(1));
									ob.setEntered_date(rs.getTimestamp(2));
									break;
								}
							}
						}
					}
					else
					{
						rs = c.createStatement().executeQuery(sQuery);
						if(rs.next())
						{
							ob.setInputter(rs.getString(1));
							ob.setEntered_date(rs.getTimestamp(2));
						}
					}
				}
			}

		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getDataEntryInformation() in OracleSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getDataEntryInformation() in OracleSQLDao.java");
		}
		finally
		{
			try
	        {	if (c!= null) {c.close(); c=null;}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return ob;
	}
	public String toPostGresDE(ResultSet rs) throws Exception
	{
		String insertStmt="insert into epoch_ev_de values("+
						rs.getInt(1)+", '"+			//DE_ID
						rs.getString(2)+"', '"+		//SITE
						rs.getString(3)+"', "+	//PAT_NO
						rs.getInt(4)+", "+	//EVENT_NO
						rs.getInt(5)+", "+	//WEEK_NO
						(isNull(rs, "DE")? null : "'"+rs.getString(6)+"'")+", "+	//DE
						(isNull(rs, "DE_TIME")? null : rs.getInt(7))+", "+	//DE_TIME
						(isNull(rs, "HR")? null : rs.getInt(8))+", "+	//HR
						(isNull(rs, "RR")? null : rs.getInt(9))+", "+	//RR
						(isNull(rs, "SAT")? null : rs.getInt(10))+", "+	//SAT
						(isNull(rs, "SBP")? null : rs.getInt(11))+", "+	//SBP
						(isNull(rs, "DBP")? null : "'"+rs.getString(12)+"'")+", "+	//DBP
						(isNull(rs, "CAP_REF")? null : "'"+rs.getString(13)+"'")+", "+	//CAP_REF
						(isNull(rs, "TEMPER")? null : rs.getFloat(14))+", "+	//TEMPER
						(isNull(rs, "CONS_AVPU")? null : "'"+rs.getString(15)+"'")+", "+	//CONS_AVPU
						(isNull(rs, "CONS_GCS")? null : rs.getInt(16))+", "+	//CONS_GCS
						(isNull(rs, "PUPILS")? null : "'"+rs.getString(17)+"'")+", "+	//PUPILS
						(isNull(rs, "O2_METHOD")? null : "'"+rs.getString(18)+"'")+", "+	//O2_METHOD
						(isNull(rs, "O2_UNITS")? null : "'"+rs.getString(19)+"'")+", "+	//O2_UNITS
						(isNull(rs, "O2_AMOUNT")? null : rs.getFloat(20))+", "+	//O2_AMOUNT
						(isNull(rs, "RESP_EFF")? null : "'"+rs.getString(21)+"'")+", "+	//RESP_EFF
						(isNull(rs, "STRIDOR")? null : "'"+rs.getString(22)+"'")+", "+	//STRIDOR
						(isNull(rs, "IV_TOT")? null : rs.getFloat(23))+", "+	//IV_TOT
						(isNull(rs, "UO")? null : rs.getFloat(24))+", "+	//UO
						(isNull(rs, "URINE")? null : "'"+rs.getString(25)+"'")+", "+	//URINE
						(isNull(rs, "NARC")? null : "'"+rs.getString(26)+"'")+", "+	//NARC
						(isNull(rs, "MD_REVIEW")? null : "'"+rs.getString(27)+"'")+", "+	//MD_REVIEW
						(isNull(rs, "STAT")? null : "'"+rs.getString(28)+"'")+", "+	//STAT
						(isNull(rs, "NP_RATIO")? null : "'"+rs.getString(29)+"'")+", "+	//NP_RATIO
						(isNull(rs, "PARENT")? null : "'"+rs.getString(30)+"'")+", "+	//PARENT
						(isNull(rs, "ENTERED_BY")? null : "'"+rs.getString(31)+"'")+", "+	//ENTERED_BY
						(isNull(rs, "UPDATED_ON")? null : "'"+rs.getTimestamp(32)+"'")+", "+	//UPDATED_ON
						(isNull(rs, "TEMP_UNIT")? null : "'"+rs.getString(33)+"'")+", "+	//TEMP_UNIT
						(isNull(rs, "COMPRESSION")? null : "'"+rs.getString(34)+"'")+", "+	//COMPRESSION
						(isNull(rs, "CAP_REF_SIGN")? null : "'"+rs.getString(35)+"'")+", "+	//CAP_REF_SIGN
						(isNull(rs, "CAP_REF_NUMBER")? null : "'"+rs.getString(36)+"'")+", "+	//CAP_REF_NUMBER
						(isNull(rs, "SBP_NA")? null : "'"+rs.getString(37)+"'")+", "+	//SBP_NA
						(isNull(rs, "DBP_NA")? null : "'"+rs.getString(38)+"'")+");";	//DBP_NA
				
		
		return insertStmt;
	}
	public boolean isNull(ResultSet rs, String fieldName) throws SQLException
	{
		return (rs.getString(fieldName)==null);
	}
	//Diagnostics
	public String toPostGresDE2(ResultSet rs) throws Exception
	{
		String insertStmt="insert into epoch_ev_de2 values("+
						rs.getInt(1)+", '"+			//DE2_ID
						rs.getString(2)+"', '"+		//SITE
						rs.getString(3)+"', "+	//PAT_NO
						rs.getInt(4)+", "+	//EVENT_NO
						rs.getInt(5)+", "+	//WEEK_NO
						(isNull(rs, "RS_LOW")? null : "'"+rs.getString(6)+"'")+", "+
						(isNull(rs, "RS_UP")? null : "'"+rs.getString(7)+"'")+", "+
						(isNull(rs, "RS_CHR")? null : "'"+rs.getString(8)+"'")+", "+
						(isNull(rs, "RS_OTH")? null : "'"+rs.getString(9)+"'")+", "+
						(isNull(rs, "RS_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(10))+"'")+", "+
						(isNull(rs, "HC_SHD_NOCYAN")? null : "'"+rs.getString(11)+"'")+", "+
						(isNull(rs, "HC_SHD_CYAN")? null : "'"+rs.getString(12)+"'")+", "+
						(isNull(rs, "HC_PULM_H")? null : "'"+rs.getString(13)+"'")+", "+
						(isNull(rs, "HC_SYS_H")? null : "'"+rs.getString(14)+"'")+", "+
						(isNull(rs, "HC_VASC_INJ")? null : "'"+rs.getString(15)+"'")+", "+
						(isNull(rs, "HC_VASCULITIS")? null : "'"+rs.getString(16)+"'")+", "+
						(isNull(rs, "HC_VESSELS_OTH")? null : "'"+rs.getString(17)+"'")+", "+
						(isNull(rs, "HC_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(18))+"'")+", "+
						(isNull(rs, "N_SEIZ")? null : "'"+rs.getString(19)+"'")+", "+
						(isNull(rs, "N_HYDROC")? null : "'"+rs.getString(20)+"'")+", "+
						(isNull(rs, "N_ST_EN")? null : "'"+rs.getString(21)+"'")+", "+
						(isNull(rs, "N_PR_EN")? null : "'"+rs.getString(22)+"'")+", "+
						(isNull(rs, "N_M_PATHY")? null : "'"+rs.getString(23)+"'")+", "+
						(isNull(rs, "N_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(24))+"'")+", "+
						(isNull(rs, "GI_LIVER")? null : "'"+rs.getString(25)+"'")+", "+
						(isNull(rs, "GI_BLEED")? null : "'"+rs.getString(26)+"'")+", "+
						(isNull(rs, "GI_MALABSORB")? null : "'"+rs.getString(27)+"'")+", "+
						(isNull(rs, "GI_OTH")? null : "'"+rs.getString(28)+"'")+", "+
						(isNull(rs, "GI_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(29))+"'")+", "+
						(isNull(rs, "RE_NODIAL")? null : "'"+rs.getString(30)+"'")+", "+
						(isNull(rs, "RE_DIAL")? null : "'"+rs.getString(31)+"'")+", "+
						(isNull(rs, "RE_ELYT")? null : "'"+rs.getString(32)+"'")+", "+
						(isNull(rs, "RE_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(33))+"'")+", "+
						(isNull(rs, "M_JOINT")? null : "'"+rs.getString(34)+"'")+", "+
						(isNull(rs, "M_RHEU")? null : "'"+rs.getString(35)+"'")+", "+
						(isNull(rs, "M_ORTHO")? null : "'"+rs.getString(36)+"'")+", "+
						(isNull(rs, "M_SCOLIO")? null : "'"+rs.getString(37)+"'")+", "+
						(isNull(rs, "M_CUT")? null : "'"+rs.getString(38)+"'")+", "+
						(isNull(rs, "M_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(39))+"'")+", "+
						(isNull(rs, "HO_MAL_H")? null : "'"+rs.getString(40)+"'")+", "+
						(isNull(rs, "HO_MAL_O")? null : "'"+rs.getString(41)+"'")+", "+
						(isNull(rs, "HO_TUM")? null : "'"+rs.getString(42)+"'")+", "+
						(isNull(rs, "HO_P_IM")? null : "'"+rs.getString(43)+"'")+", "+
						(isNull(rs, "HO_A_IM")? null : "'"+rs.getString(44)+"'")+", "+
						(isNull(rs, "HO_BMT")? null : "'"+rs.getString(45)+"'")+", "+
						(isNull(rs, "HO_TRNSPLNT")? null : "'"+rs.getString(46)+"'")+", "+
						(isNull(rs, "HO_PRO_THROMB")? null : "'"+rs.getString(47)+"'")+", "+
						(isNull(rs, "HO_BLEED")? null : "'"+rs.getString(48)+"'")+", "+
						(isNull(rs, "HO_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(49))+"'")+", "+
						(isNull(rs, "E_METAB")? null : "'"+rs.getString(50)+"'")+", "+
						(isNull(rs, "E_DM")? null : "'"+rs.getString(51)+"'")+", "+
						(isNull(rs, "E_ENDO")? null : "'"+rs.getString(52)+"'")+", "+
						(isNull(rs, "E_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(53))+"'")+", "+
						(isNull(rs, "A_DRUG_KNOW")? null : "'"+rs.getString(54)+"'")+", "+
						(isNull(rs, "A_OTHER")? null : "'"+rs.getString(55)+"'")+", "+
						(isNull(rs, "A_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(56))+"'")+", "+
						(isNull(rs, "DEV_O2")? null : "'"+rs.getString(57)+"'")+", "+
						(isNull(rs, "DEV_TRACH")? null : "'"+rs.getString(58)+"'")+", "+
						(isNull(rs, "DEV_TRACH_V")? null : "'"+rs.getString(59)+"'")+", "+
						(isNull(rs, "DEV_VENTR_D_I")? null : "'"+rs.getString(60)+"'")+", "+
						(isNull(rs, "DEV_VENTR_D_E")? null : "'"+rs.getString(61)+"'")+", "+
						(isNull(rs, "DEV_PACE")? null : "'"+rs.getString(62)+"'")+", "+
						(isNull(rs, "DEV_WHEEL")? null : "'"+rs.getString(63)+"'")+", "+
						(isNull(rs, "DEV_GE_TUBE")? null : "'"+rs.getString(64)+"'")+", "+
						(isNull(rs, "DEV_V_ACC")? null : "'"+rs.getString(65)+"'")+", "+
						(isNull(rs, "DEV_HD_ACC")? null : "'"+rs.getString(66)+"'")+", "+
						(isNull(rs, "DEV_PD_ACC")? null : "'"+rs.getString(67)+"'")+", "+
						(isNull(rs, "DEV_OTH_LIST")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(68))+"'")+", "+
						(isNull(rs, "ENTERED_BY")? null : "'"+rs.getString(69)+"'")+", "+
						(isNull(rs, "UPDATED_ON")? null : "'"+rs.getTimestamp(70)+"'")+");";
		return insertStmt;
	}	
	//Problems
	public String toPostGresDE4(ResultSet rs) throws Exception
	{
		String insertStmt="insert into epoch_ev_de4 values("+
				rs.getInt(1)+", '"+			//DE4_ID
				rs.getString(2)+"', '"+		//SITE
				rs.getString(3)+"', "+	//PAT_NO
				rs.getInt(4)+", "+	//EVENT_NO
				rs.getInt(5)+", "+	//WEEK_NO
				(isNull(rs, "RS_UP")? null : "'"+rs.getString(6)+"'")+", "+
				(isNull(rs, "RS_BRONCH")? null : "'"+rs.getString(7)+"'")+", "+
				(isNull(rs, "RS_CONS")? null : "'"+rs.getString(8)+"'")+", "+
				(isNull(rs, "RS_PNEUMO")? null : "'"+rs.getString(9)+"'")+", "+
				(isNull(rs, "RS_EFFUSION")? null : "'"+rs.getString(10)+"'")+", "+
				(isNull(rs, "RS_OEDEMA")? null : "'"+rs.getString(11)+"'")+", "+
				(isNull(rs, "RS_HAEMO")? null : "'"+rs.getString(12)+"'")+", "+
				(isNull(rs, "RS_SX")? null : "'"+rs.getString(13)+"'")+", "+
				(isNull(rs, "RS_TRAUMA")? null : "'"+rs.getString(14)+"'")+", "+
				(isNull(rs, "RS_OTH")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(15))+"'")+", "+
				(isNull(rs, "HC_ARRHYTMIA")? null : "'"+rs.getString(16)+"'")+", "+
				(isNull(rs, "HC_C_SHOCK")? null : "'"+rs.getString(17)+"'")+", "+
				(isNull(rs, "HC_SX")? null : "'"+rs.getString(18)+"'")+", "+
				(isNull(rs, "HC_H_SHOCK")? null : "'"+rs.getString(19)+"'")+", "+
				(isNull(rs, "HC_EFFUSION")? null : "'"+rs.getString(20)+"'")+", "+
				(isNull(rs, "HC_S_SHOCK")? null : "'"+rs.getString(21)+"'")+", "+
				(isNull(rs, "HC_SYS_HYPERT")? null : "'"+rs.getString(22)+"'")+", "+
				(isNull(rs, "HC_P_HYPERT")? null : "'"+rs.getString(23)+"'")+", "+
				(isNull(rs, "HC_SHUNT")? null : "'"+rs.getString(24)+"'")+", "+
				(isNull(rs, "HC_A_THROMB")? null : "'"+rs.getString(25)+"'")+", "+
				(isNull(rs, "HC_V_THROMB")? null : "'"+rs.getString(26)+"'")+", "+
				(isNull(rs, "HC_OTH")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(27))+"'")+", "+
				(isNull(rs, "ABD_SEPSIS")? null : "'"+rs.getString(28)+"'")+", "+
				(isNull(rs, "ABD_TRAUMA")? null : "'"+rs.getString(29)+"'")+", "+
				(isNull(rs, "ABD_BLEED")? null : "'"+rs.getString(30)+"'")+", "+
				(isNull(rs, "ABD_DIARRH")? null : "'"+rs.getString(31)+"'")+", "+
				(isNull(rs, "ABD_BOW_PERF")? null : "'"+rs.getString(32)+"'")+", "+
				(isNull(rs, "ABD_BOW_OBSTR")? null : "'"+rs.getString(33)+"'")+", "+
				(isNull(rs, "ABD_SX")? null : "'"+rs.getString(34)+"'")+", "+
				(isNull(rs, "ABD_AC_LIVER")? null : "'"+rs.getString(35)+"'")+", "+
				(isNull(rs, "ABD_OTH")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(36))+"'")+", "+
				(isNull(rs, "RE_ACUTE")? null : "'"+rs.getString(37)+"'")+", "+
				(isNull(rs, "RE_HYP_NA")? null : "'"+rs.getString(38)+"'")+", "+
				(isNull(rs, "RE_ELYT")? null : "'"+rs.getString(39)+"'")+", "+
				(isNull(rs, "RE_SX")? null : "'"+rs.getString(40)+"'")+", "+
				(isNull(rs, "RE_OTH")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(41))+"'")+", "+
				(isNull(rs, "HO_MALABS")? null : "'"+rs.getString(42)+"'")+", "+
				(isNull(rs, "HO_CHEMO")? null : "'"+rs.getString(43)+"'")+", "+
				(isNull(rs, "HO_ANAEMIA")? null : "'"+rs.getString(44)+"'")+", "+
				(isNull(rs, "HO_THROM")? null : "'"+rs.getString(45)+"'")+", "+
				(isNull(rs, "HO_LEUC")? null : "'"+rs.getString(46)+"'")+", "+
				(isNull(rs, "HO_COAG")? null : "'"+rs.getString(47)+"'")+", "+
				(isNull(rs, "HO_GVHD")? null : "'"+rs.getString(48)+"'")+", "+
				(isNull(rs, "HO_TRNS_REJ")? null : "'"+rs.getString(49)+"'")+", "+
				(isNull(rs, "HO_SID")? null : "'"+rs.getString(50)+"'")+", "+
				(isNull(rs, "HO_CUT_D")? null : "'"+rs.getString(51)+"'")+", "+
				(isNull(rs, "HO_OTH")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(52))+"'")+", "+
				(isNull(rs, "E_ADREN")? null : "'"+rs.getString(53)+"'")+", "+
				(isNull(rs, "E_H_AMMON")? null : "'"+rs.getString(54)+"'")+", "+
				(isNull(rs, "E_H_GLY")? null : "'"+rs.getString(55)+"'")+", "+
				(isNull(rs, "E_L_GLY")? null : "'"+rs.getString(56)+"'")+", "+
				(isNull(rs, "E_KETOACID")? null : "'"+rs.getString(57)+"'")+", "+
				(isNull(rs, "E_OTH")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(58))+"'")+", "+
				(isNull(rs, "N_AC_ENC")? null : "'"+rs.getString(59)+"'")+", "+
				(isNull(rs, "N_BULBAR")? null : "'"+rs.getString(60)+"'")+", "+
				(isNull(rs, "N_HYDROC")? null : "'"+rs.getString(61)+"'")+", "+
				(isNull(rs, "N_HAEMORR")? null : "'"+rs.getString(62)+"'")+", "+
				(isNull(rs, "N_LIMB_PAR")? null : "'"+rs.getString(63)+"'")+", "+
				(isNull(rs, "N_DIAPH_PAR")? null : "'"+rs.getString(64)+"'")+", "+
				(isNull(rs, "N_SX")? null : "'"+rs.getString(65)+"'")+", "+
				(isNull(rs, "N_SEIZ")? null : "'"+rs.getString(66)+"'")+", "+
				(isNull(rs, "N_TBI")? null : "'"+rs.getString(67)+"'")+", "+
				(isNull(rs, "N_OTH")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(68))+"'")+", "+
				(isNull(rs, "INF_CNS")? null : "'"+rs.getString(69)+"'")+", "+
				(isNull(rs, "INF_MUSC")? null : "'"+rs.getString(70)+"'")+", "+
				(isNull(rs, "INF_RESP")? null : "'"+rs.getString(71)+"'")+", "+
				(isNull(rs, "INF_ABD")? null : "'"+rs.getString(72)+"'")+", "+
				(isNull(rs, "INF_UTI")? null : "'"+rs.getString(73)+"'")+", "+
				(isNull(rs, "INF_BLOOD")? null : "'"+rs.getString(74)+"'")+", "+
				(isNull(rs, "INF_OTHER")? null : "'"+rs.getString(75)+"'")+", "+
				(isNull(rs, "INF_OTH_SPEC")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(76))+"'")+", "+
				(isNull(rs, "ENTERED_BY")? null : "'"+rs.getString(77)+"'")+", "+
				(isNull(rs, "UPDATED_ON")? null : "'"+rs.getTimestamp(78)+"'")+", "+
				(isNull(rs, "E_OTH_SPEC")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(79))+"'")+");";
		return insertStmt;
	}	
	//Lab data
	public String toPostGresDE5(ResultSet rs) throws Exception
	{
		String insertStmt="insert into epoch_ev_de5 values("+
				rs.getInt(1)+", '"+			//DE5_ID
				rs.getString(2)+"', '"+		//SITE
				rs.getString(3)+"', "+	//PAT_NO
				rs.getInt(4)+", "+	//EVENT_NO
				rs.getInt(5)+", "+	//WEEK_NO
				(isNull(rs, "DE_TIME")? null : "'"+rs.getString(6)+"'")+", "+
				(isNull(rs, "HGB")? null : "'"+rs.getInt(7)+"'")+", "+
				(isNull(rs, "HGB_NA")? null : "'"+rs.getString(8)+"'")+", "+
				(isNull(rs, "WBC")? null : "'"+rs.getDouble(9)+"'")+", "+
				(isNull(rs, "WBC_NA")? null : "'"+rs.getString(10)+"'")+", "+
				(isNull(rs, "BANDS")? null : "'"+rs.getDouble(11)+"'")+", "+
				(isNull(rs, "BANDS_NA")? null : "'"+rs.getString(12)+"'")+", "+
				(isNull(rs, "PLT")? null : "'"+rs.getInt(13)+"'")+", "+
				(isNull(rs, "PLT_NA")? null : "'"+rs.getString(14)+"'")+", "+
				(isNull(rs, "NA")? null : "'"+rs.getInt(15)+"'")+", "+
				(isNull(rs, "NA_NA")? null : "'"+rs.getString(16)+"'")+", "+
				(isNull(rs, "POT")? null : "'"+rs.getDouble(17)+"'")+", "+
				(isNull(rs, "POT_NA")? null : "'"+rs.getString(18)+"'")+", "+
				(isNull(rs, "CA_TOT")? null : "'"+rs.getDouble(19)+"'")+", "+
				(isNull(rs, "CA_TOT_NA")? null : "'"+rs.getString(20)+"'")+", "+
				(isNull(rs, "CA_ION")? null : "'"+rs.getDouble(21)+"'")+", "+
				(isNull(rs, "CA_ION_NA")? null : "'"+rs.getString(22)+"'")+", "+
				(isNull(rs, "MAG")? null : "'"+rs.getDouble(23)+"'")+", "+
				(isNull(rs, "MAG_NA")? null : "'"+rs.getString(24)+"'")+", "+
				(isNull(rs, "A_PAO2")? null : "'"+rs.getDouble(25)+"'")+", "+
				(isNull(rs, "A_PAO2_NA")? null : "'"+rs.getString(26)+"'")+", "+
				(isNull(rs, "A_PACO2")? null : "'"+rs.getDouble(27)+"'")+", "+
				(isNull(rs, "A_PACO2_NA")? null : "'"+rs.getString(28)+"'")+", "+
				(isNull(rs, "A_HCO3")? null : "'"+rs.getDouble(29)+"'")+", "+
				(isNull(rs, "A_HCO3_NA")? null : "'"+rs.getString(30)+"'")+", "+
				(isNull(rs, "A_HCO3_METHOD")? null : "'"+rs.getString(31)+"'")+", "+
				(isNull(rs, "A_PH")? null : "'"+rs.getDouble(32)+"'")+", "+
				(isNull(rs, "A_PH_NA")? null : "'"+rs.getString(33)+"'")+", "+
				(isNull(rs, "V_PMVO2")? null : "'"+rs.getInt(34)+"'")+", "+
				(isNull(rs, "V_PMVO2_NA")? null : "'"+rs.getString(35)+"'")+", "+
				(isNull(rs, "V_HCO3")? null : "'"+rs.getDouble(36)+"'")+", "+
				(isNull(rs, "V_HCO3_NA")? null : "'"+rs.getString(37)+"'")+", "+
				(isNull(rs, "V_HCO3_METHOD")? null : "'"+rs.getString(38)+"'")+", "+
				(isNull(rs, "AMMONIA")? null : "'"+rs.getDouble(39)+"'")+", "+
				(isNull(rs, "AMMONIA_NA")? null : "'"+rs.getString(40)+"'")+", "+
				(isNull(rs, "LAC")? null : "'"+rs.getDouble(41)+"'")+", "+
				(isNull(rs, "LAC_NA")? null : "'"+rs.getString(42)+"'")+", "+
				(isNull(rs, "GLU")? null : "'"+rs.getDouble(43)+"'")+", "+
				(isNull(rs, "GLU_NA")? null : "'"+rs.getString(44)+"'")+", "+
				(isNull(rs, "AST")? null : "'"+rs.getDouble(45)+"'")+", "+
				(isNull(rs, "AST_NA")? null : "'"+rs.getString(46)+"'")+", "+
				(isNull(rs, "ALT")? null : "'"+rs.getDouble(47)+"'")+", "+
				(isNull(rs, "ALT_NA")? null : "'"+rs.getString(48)+"'")+", "+
				(isNull(rs, "BILI_CONJ")? null : "'"+rs.getDouble(49)+"'")+", "+
				(isNull(rs, "BILI_CONJ_NA")? null : "'"+rs.getString(50)+"'")+", "+
				(isNull(rs, "BILI_UNC")? null : "'"+rs.getDouble(51)+"'")+", "+
				(isNull(rs, "BILI_UNC_NA")? null : "'"+rs.getString(52)+"'")+", "+
				(isNull(rs, "LDH")? null : "'"+rs.getDouble(53)+"'")+", "+
				(isNull(rs, "LDH_NA")? null : "'"+rs.getString(54)+"'")+", "+
				(isNull(rs, "INR")? null : "'"+rs.getDouble(55)+"'")+", "+
				(isNull(rs, "INR_NA")? null : "'"+rs.getString(56)+"'")+", "+
				(isNull(rs, "PTT")? null : "'"+rs.getInt(57)+"'")+", "+
				(isNull(rs, "PTT_NA")? null : "'"+rs.getString(58)+"'")+", "+
				(isNull(rs, "HGB_NO")? null : "'"+rs.getInt(59)+"'")+", "+
				(isNull(rs, "WBC_NO")? null : "'"+rs.getDouble(60)+"'")+", "+
				(isNull(rs, "BANDS_NO")? null : "'"+rs.getInt(61)+"'")+", "+
				(isNull(rs, "PLT_NO")? null : "'"+rs.getInt(62)+"'")+", "+
				(isNull(rs, "NA_NO")? null : "'"+rs.getInt(63)+"'")+", "+
				(isNull(rs, "POT_NO")? null : "'"+rs.getInt(64)+"'")+", "+
				(isNull(rs, "CA_TOT_NO")? null : "'"+rs.getInt(65)+"'")+", "+
				(isNull(rs, "CA_ION_NO")? null : "'"+rs.getInt(66)+"'")+", "+
				(isNull(rs, "MAG_NO")? null : "'"+rs.getInt(67)+"'")+", "+
				(isNull(rs, "A_PAO2_NO")? null : "'"+rs.getInt(68)+"'")+", "+
				(isNull(rs, "A_PACO2_NO")? null : "'"+rs.getInt(69)+"'")+", "+
				(isNull(rs, "A_HCO3_NO")? null : "'"+rs.getInt(70)+"'")+", "+
				(isNull(rs, "A_PH_NO")? null : "'"+rs.getInt(71)+"'")+", "+
				(isNull(rs, "V_PMVO2_NO")? null : "'"+rs.getInt(72)+"'")+", "+
				(isNull(rs, "V_HCO3_NO")? null : "'"+rs.getInt(73)+"'")+", "+
				(isNull(rs, "AMMONIA_NO")? null : "'"+rs.getInt(74)+"'")+", "+
				(isNull(rs, "LAC_NO")? null : "'"+rs.getInt(75)+"'")+", "+
				(isNull(rs, "GLU_NO")? null : "'"+rs.getInt(76)+"'")+", "+
				(isNull(rs, "AST_NO")? null : "'"+rs.getInt(77)+"'")+", "+
				(isNull(rs, "ALT_NO")? null : "'"+rs.getInt(78)+"'")+", "+
				(isNull(rs, "BILI_CONJ_NO")? null : "'"+rs.getInt(79)+"'")+", "+
				(isNull(rs, "BILI_UNC_NO")? null : "'"+rs.getInt(80)+"'")+", "+
				(isNull(rs, "LDH_NO")? null : "'"+rs.getInt(81)+"'")+", "+
				(isNull(rs, "INR_NO")? null : "'"+rs.getDouble(82)+"'")+", "+
				(isNull(rs, "PTT_NO")? null : "'"+rs.getInt(83)+"'")+", "+
				(isNull(rs, "ENTERED_BY")? null : "'"+rs.getString(84)+"'")+", "+
				(isNull(rs, "UPDATED_ON")? null : "'"+rs.getTimestamp(85)+"'")+", "+
				(isNull(rs, "DATA_NA")? null : "'"+rs.getString(86)+"'")+", "+
				(isNull(rs, "A_PAO2_U")? null : "'"+rs.getString(87)+"'")+", "+
				(isNull(rs, "A_PACO2_U")? null : "'"+rs.getString(88)+"'")+", "+
				(isNull(rs, "V_PMVO2_U")? null : "'"+rs.getString(89)+"'")+", "+
				(isNull(rs, "GLU_U")? null : "'"+rs.getString(90)+"'")+", "+
				(isNull(rs, "CAL_U")? null : "'"+rs.getString(91)+"'")+", "+
				(isNull(rs, "BILI_C_U")? null : "'"+rs.getString(92)+"'")+", "+
				(isNull(rs, "MAG_U")? null : "'"+rs.getString(93)+"'")+", "+
				(isNull(rs, "BILI_U_U")? null : "'"+rs.getString(94)+"'")+");";
		return insertStmt;
	}
	//Medication
	public String toPostGresDE3(ResultSet rs) throws Exception
	{
		String insertStmt="insert into epoch_ev_de3 values("+
				rs.getInt(1)+", '"+			//DE3_ID
				rs.getString(2)+"', '"+		//SITE
				rs.getString(3)+"', "+	//PAT_NO
				rs.getInt(4)+", "+	//EVENT_NO
				rs.getInt(5)+", "+	//WEEK_NO
				(isNull(rs, "MEDICATION")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(6))+"'")+", "+
				(isNull(rs, "HR")? null : "'"+rs.getInt(7)+"'")+", "+
				(isNull(rs, "ROUTE")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(8))+"'")+", "+
				(isNull(rs, "ENTERED_BY")? null : "'"+rs.getString(9)+"'")+", "+
				(isNull(rs, "UPDATED_ON")? null : "'"+rs.getTimestamp(10)+"'")+");";
		return insertStmt;
	}	
	//Radiology
	public String toPostGresDE6(ResultSet rs) throws Exception
	{
		String insertStmt="insert into epoch_ev_de6 values("+
				rs.getInt(1)+", '"+			//DE6_ID
				rs.getString(2)+"', '"+		//SITE
				rs.getString(3)+"', "+	//PAT_NO
				rs.getInt(4)+", "+	//EVENT_NO
				rs.getInt(5)+", "+	//WEEK_NO
				(isNull(rs, "CHEST_IMG")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(6))+"'")+", "+
				(isNull(rs, "CNS_IMG")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(7))+"'")+", "+
				(isNull(rs, "ABD_IMG")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(8))+"'")+", "+
				(isNull(rs, "OTH_IMG")? null : "'"+StringEscapeUtils.escapeSql(rs.getString(9))+"'")+", "+
				(isNull(rs, "ENTERED_BY")? null : "'"+rs.getString(10)+"'")+", "+
				(isNull(rs, "UPDATED_ON")? null : "'"+rs.getTimestamp(11)+"'")+");";
		return insertStmt;
	}	
}
