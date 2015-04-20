package com.sickkids.caliper.dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.exceptions.DAOException;

import flex.messaging.FlexContext;

public class SQLDao
{
	private ConnectionPool cp=null;
	private Connection c=null;
	public SQLDao()
	{
		cp=(ConnectionPool)FlexContext.getServletContext().getAttribute("connectionPool");
	}
	public SQLDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public SQLDao(Connection c)
	{
		this.c=c;
	}
	public List<LodBean> getLODList() throws DAOException
	{
		Utils.logger.info("getLODList() of SQLDao.java is called...");

		List<LodBean> list = new ArrayList<LodBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select a.assay_name, l.lod from assay_name as a, lod_loq as l where a.assay_id=l.assay_id;");
			while (rs.next())
			{
				LodBean lb=new LodBean();
				lb.setAssay_name(rs.getString(1));
				lb.setLod(rs.getDouble(2));

				list.add(lb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	
        		if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getLODList() of SQLDao.java is ending with "+list.size());
		return list;

	}
	public List<StandardReferenceIntervalBean> getAnalyteRecordList(String analyte) throws DAOException
	{
		Utils.logger.info("getAnalyteRecordList() of SQLDao.java is called...");

		List<StandardReferenceIntervalBean> list = new ArrayList<StandardReferenceIntervalBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from standard_reference_interval where analyte like '%"+analyte+"%';");
			while (rs.next())
			{
				StandardReferenceIntervalBean sb=new StandardReferenceIntervalBean();
				
				sb.setAnalyte_index(rs.getInt(1));
				sb.setAnalyte(rs.getString(2));
				sb.setAge_range(rs.getString(3));
				sb.setFemale_reference_interval(rs.getString(4));
				sb.setFemale_number(rs.getInt(5));
				sb.setMale_reference_interval(rs.getString(6));
				sb.setMale_number(rs.getInt(7));
				sb.setCategory(rs.getString(8));
				sb.setMunit(rs.getString(9));

				list.add(sb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	
        		if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getAnalyteRecordList() of SQLDao.java is ending with "+list.size());
		return list;

	}
	public List<List<String>> getRawDataList(String analyte) throws DAOException
	{
		Utils.logger.info("getRawDataList("+analyte+") of SQLDao.java is called...");
		List<List<String>> csv = new ArrayList<List<String>>();
		//List list = new ArrayList();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			
			String sqlString="";
			if(analyte.equals("all"))
			{
				
				
				SortedSet<String> sortedset= new TreeSet<String>(Utils.analyteMap.keySet());
				
			    Iterator<String> it = sortedset.iterator();
			    //String analytes="";
			    while (it.hasNext()) 
			    {
			    	String key=it.next();
			    	//analytes+="\""+key+"\", ";
			    	sqlString+=key+", ";
			    	//sqlString+=it.hasNext()? ", " : "";
			    }
			    //analytes="\"participant_id\", \"sex\", \"age\", \"ethnic_group\","+ analytes+"\"weight\", \"height\", \"bmi\"";
			    //System.out.println("analytes="+analytes);
			    csv.add(Arrays.asList("participant_id", "sex", "age", "ethnic_group","albumin_g", "albumin_p", "alkaline_phosphatase", "alt", "alt_act", "amylase", "apo_a1", "apo_b", "aso", "ast", "ast_act", "bilirubin_direct", "bilirubin_total", "c3", "c4", "calcium", "che", "cholesterol", "co2", "creatinine_enzymatic", "creatinine_jaffee", "crp", "ggt", "haptoglobin", "iga", "igg", "igm", "iron", "iron_plasma", "ldh_ld", "lipase_lip", "magnesium", "phosphorus", "prealbumin", "rf_rheumatoid_factor", "total_protein", "transferrin_trf", "triglycerides", "uhdl_ultra_hdl", "urea", "uric_acid", "weight", "height", "bmi"));
			    //csv.add(Arrays.asList("participant_id", "sex", "age", "ethnic_group", analytes, "weight", "height", "bmi"));
			    
			    sqlString="select participant_id, sex, age, ethnic_group, "+sqlString+" weight, height, bmi from phase1completedatasetjune2011;";
			    //return null;
			}
			else 
			{
				csv.add(Arrays.asList("participant_id", "sex", "age", "ethnic_group", analyte, "weight", "height", "bmi"));
				sqlString="select participant_id, sex, age, ethnic_group, "+analyte+", weight, height, bmi from phase1completedatasetjune2011;";
			}
			
			System.out.println("sqlString="+sqlString);
			ResultSet rs = s.executeQuery(sqlString);
			while (rs.next())
			{
				if(analyte.equals("all"))
					csv.add(Arrays.asList(Long.toString(rs.getLong(1)),
							  Integer.toString(rs.getInt(2)),
							  Double.toString(rs.getDouble(3)),
							  Double.toString(rs.getDouble(4)),
							  Double.toString(rs.getDouble(5)),
							  Double.toString(rs.getDouble(6)),
							  Double.toString(rs.getDouble(7)),
							  Double.toString(rs.getDouble(8)),							  
							  Double.toString(rs.getDouble(9)),
							  Double.toString(rs.getDouble(10)),
							  Double.toString(rs.getDouble(11)),
							  Double.toString(rs.getDouble(12)),
							  Double.toString(rs.getDouble(13)),
							  Double.toString(rs.getDouble(14)),
							  Double.toString(rs.getDouble(15)),
							  Double.toString(rs.getDouble(16)),
							  Double.toString(rs.getDouble(17)),
							  Double.toString(rs.getDouble(18)),
							  Double.toString(rs.getDouble(19)),
							  Double.toString(rs.getDouble(20)),
							  Double.toString(rs.getDouble(21)),
							  Double.toString(rs.getDouble(22)),
							  Double.toString(rs.getDouble(23)),
							  Double.toString(rs.getDouble(24)),
							  Double.toString(rs.getDouble(25)),
							  Double.toString(rs.getDouble(26)),
							  Double.toString(rs.getDouble(27)),
							  Double.toString(rs.getDouble(28)),
							  Double.toString(rs.getDouble(29)),
							  Double.toString(rs.getDouble(30)),
							  Double.toString(rs.getDouble(31)),
							  Double.toString(rs.getDouble(32)),
							  Double.toString(rs.getDouble(33)),
							  Double.toString(rs.getDouble(34)),
							  Double.toString(rs.getDouble(35)),
							  Double.toString(rs.getDouble(36)),
							  Double.toString(rs.getDouble(37)),
							  Double.toString(rs.getDouble(38)),
							  Double.toString(rs.getDouble(39)),
							  Double.toString(rs.getDouble(40)),
							  Double.toString(rs.getDouble(41)),
							  Double.toString(rs.getDouble(42)),
							  Double.toString(rs.getDouble(43)),
							  Double.toString(rs.getDouble(44)),
							  Double.toString(rs.getDouble(45)),
							  Double.toString(rs.getDouble(46)),
							  Double.toString(rs.getDouble(47)),
							  Double.toString(rs.getDouble(48))
							 ));
				else
					csv.add(Arrays.asList(Long.toString(rs.getLong(1)),
										  Integer.toString(rs.getInt(2)),
										  Double.toString(rs.getDouble(3)),
										  Double.toString(rs.getDouble(4)),
										  Double.toString(rs.getDouble(5)),
										  Double.toString(rs.getDouble(6)),
										  Double.toString(rs.getDouble(7)),
										  Double.toString(rs.getDouble(8))
										 ));
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	
        		if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getRawDataList() of SQLDao.java is ending with "+csv.size());
		return csv;

	}
	public XYSeriesCollection getScatterPlotDataList(String analyte,String minAge, String maxAge, String gender, String ethnic) throws DAOException
	{
		Utils.logger.info("getScatterPlotDataList() of SQLDao.java is called...");

		XYSeriesCollection dataset=new XYSeriesCollection();
		
		Connection c = null;

		try
		{
			int sex=Integer.parseInt(gender);//male=1, female=2, both=3
			int ethnic_group=Integer.parseInt(ethnic);
			String sqlString="select sex, age, "+analyte+" from phase1completedatasetjune2011 where ";
			if(sex==3) //check minAge and maxAge by changing the value from the index or not change
				sqlString+="age>="+minAge+" and age<"+maxAge;
			else
				sqlString+="age>="+minAge+" and age<"+maxAge+" and sex="+sex;
			if(ethnic_group!=12) sqlString+=" and ethnic_group="+ethnic_group;
			
			XYSeries maleSeries = new XYSeries("Male");
			XYSeries femaleSeries = new XYSeries("Female");
			
			c = cp.getConnection();
			
			System.out.println("sqlString="+sqlString);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sqlString+";");
			while (rs.next())
			{
				//System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getInt(3));
				if(rs.getInt(1)==1) maleSeries.add(rs.getDouble(2), rs.getDouble(3));
				else femaleSeries.add(rs.getDouble(2), rs.getDouble(3));				
			}
			
			if(sex==3)
			{
				dataset.addSeries(maleSeries);
				dataset.addSeries(femaleSeries);
				Utils.logger.info("getScatterPlotDataList() of SQLDao.java is ending with itemCount="+maleSeries.getItemCount());
				Utils.logger.info("getScatterPlotDataList() of SQLDao.java is ending with itemCount="+femaleSeries.getItemCount());
			}
			else if(sex==1) dataset.addSeries(maleSeries);
			else dataset.addSeries(femaleSeries);
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	
        		if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getScatterPlotDataList() of SQLDao.java is ending with itemCount="+dataset.getSeriesCount());
		return dataset;

	}
	public StatisticsBean getStatisticsResult(String analyte, int age, int sex, String ethnic) throws DAOException
	{
		Utils.logger.info("getStatisticsResult() of SQLDao.java is called...");

		Connection c = null;
		StatisticsBean sb=new StatisticsBean();
		try
		{
			int ethnic_group=Integer.parseInt(ethnic);
			String sqlString="select stddev_samp("+analyte+"), avg("+analyte+"), count(*) from phase1completedatasetjune2011 where ";
			
			sqlString +=(age==0)? "age>=0 and age<0.9" : (age==1)? "age>=0.9 and age<1.0" : "age>="+(age-1)+" and age<"+age+" ";
			
			if(sex==3) sqlString+="and sex in (1,2)"+" "; 
			else sqlString+="and sex="+sex+" ";
			
			if(ethnic_group!=12) sqlString+=" and ethnic_group="+ethnic_group;
			
			c = cp.getConnection();
			
			System.out.println("sqlString="+sqlString);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sqlString+";");
			while (rs.next())
			{
				sb.setStd(rs.getDouble(1));
				sb.setMean(rs.getDouble(2));
				sb.setN(rs.getInt(3));
				break;				
			}

		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	
        		if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getStatisticsResult() of SQLDao.java is ending");
		return sb;

	}
	/*******************************************************************************************************
	 * Event Management
	 *******************************************************************************************************/
	public boolean eventFileUpload(EventManagementBean eb) throws DAOException
	{   //if(true) return false;//testing
		Utils.logger.info("eventFileUpload() of SQLDao.java is called...");

		Connection c = null;
		PreparedStatement ps = null;
		boolean bResult=true;
		try
		{
			c = cp.getConnection();
			c.setAutoCommit(false);
			String sQuery="";
			sQuery="insert into event_management values(default,?,?,?,?,?,?,?,?,?)";
			ps = c.prepareStatement(sQuery);
			
			ps.setString(1, eb.getAdmin_id());
			ps.setString(2, eb.getEvent_subject());
			ps.setString(3, eb.getEvent_summary());
			ps.setTimestamp(4, eb.getEvent_date());			
			ps.setString(5, eb.getEvent_location());
			ps.setString(6, eb.getEvent_file_location());
			ps.setTimestamp(7, eb.getWritten_date());
			ps.setInt(8, eb.getEvent_alive());
			ps.setString(9, eb.getRemarks());
				
			ps.executeUpdate();

			c.commit();//perform transaction
		}
		catch (Exception e)
		{
			bResult=false;
			Utils.logger.severe(e.getMessage()+" eventFileUpload() in SQLDao.java");
			throw new DAOException(e+", eventFileUpload() in SQLDao.java");
		}
		finally
		{
			try
	        {	
        		if (c!= null) 
        		{
        			if(!bResult) c.rollback();
        			c.setAutoCommit(true);
        			cp.free(c);
        		}
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("eventFileUpload() of SQLDao.java is ending");
		return bResult;
	}
	public List<EventManagementBean> getEventList() throws DAOException
	{
		Utils.logger.info("getEventList() of SQLDao.java is called...");

		List<EventManagementBean> list = new ArrayList<EventManagementBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from event_management where event_alive=1 order by event_index desc limit 0, 1;");
			while (rs.next())
			{
				EventManagementBean eb=new EventManagementBean();
				
				eb.setEvent_index(rs.getLong(1));
				eb.setAdmin_id(rs.getString(2));
				eb.setEvent_subject(rs.getString(3));
				eb.setEvent_summary(rs.getString(4));
				eb.setEvent_date(rs.getTimestamp(5));
				eb.setEvent_location(rs.getString(6));
				eb.setEvent_file_location(rs.getString(7));
				eb.setWritten_date(rs.getTimestamp(8));
				eb.setEvent_alive(rs.getInt(9));
				eb.setRemarks(rs.getString(10));
				
				list.add(eb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	
        		if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getEventList() of SQLDao.java is ending with "+list.size());
		return list;

	}
	/*****************************************************************************************************
	 * 
	 */
}
