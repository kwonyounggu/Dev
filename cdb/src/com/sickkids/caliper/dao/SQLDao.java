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
import com.sickkids.caliper.statistics.RankBean;


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
			String sqlStmt="";
			if(analyte.startsWith("alt"))
				if(analyte.equals("alt"))
					sqlStmt="select * from standard_reference_interval where analyte='ALT';";
				else
					sqlStmt="select * from standard_reference_interval where analyte='ALT (ACT)';";
			else if(analyte.startsWith("ast"))
				if(analyte.equals("ast"))
					sqlStmt="select * from standard_reference_interval where analyte='AST';";
				else
					sqlStmt="select * from standard_reference_interval where analyte='AST (ACT)';";
			else if(analyte.startsWith("creatinine"))
				if(analyte.equals("creatinine_jaffee"))
					sqlStmt="select * from standard_reference_interval where analyte='Creatinine (Jaffee)';";
				else
					sqlStmt="select * from standard_reference_interval where analyte='Creatinine (Enzymatic)';";
			else if(analyte.startsWith("ldh"))
					sqlStmt="select * from standard_reference_interval where analyte='LDH';";
			else if(analyte.startsWith("lipase"))
				sqlStmt="select * from standard_reference_interval where analyte='Lipase';";
			else if(analyte.startsWith("rf_rheumatoid_factor"))
				sqlStmt="select * from standard_reference_interval where analyte='Rheumatoid Factor';";
			else if(analyte.startsWith("transferrin_trf"))
				sqlStmt="select * from standard_reference_interval where analyte='Transferrin';";
			else if(analyte.startsWith("uhdl_ultra_hdl"))
				sqlStmt="select * from standard_reference_interval where analyte='UHDL';";
			else if(analyte.startsWith("che"))
				sqlStmt="select * from standard_reference_interval where analyte='Cholinesterase';";
			else
				sqlStmt="select * from standard_reference_interval where analyte like '%"+analyte+"%';";
			ResultSet rs = s.executeQuery(sqlStmt);
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
	//Standard Reference Interval V2
	public List<StandardReferenceIntervalV2Bean> getListStandardReferenceIntervalsV2(String analyte, boolean metric) throws DAOException
	{
		Utils.logger.info("getListStandardReferenceIntervalsV2() of SQLDao.java is called...");

		List<StandardReferenceIntervalV2Bean> list = new ArrayList<StandardReferenceIntervalV2Bean>();
		Connection c = null;
		
		try
		{
			String sqlString="SELECT * FROM standard_reference_interval_v2_0 where analyte like '"+analyte+"%' and metric_system=b'"+(metric? 1 : 0)+"';";
			Utils.logger.info("sql="+sqlString);
			c=cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sqlString);
			while (rs.next())
			{
				StandardReferenceIntervalV2Bean sb=new StandardReferenceIntervalV2Bean();
				sb.setAnalyte_index(rs.getInt(1));
				sb.setAnalyte(rs.getString(2));
				sb.setAge_range(rs.getString(3));
				sb.setFemale_ri_lower_limit(rs.getString(4));
				sb.setFemale_ri_upper_limit(rs.getString(5));
				sb.setFemale_samples(rs.getInt(6));
				sb.setFemale_ci_lower_limit(rs.getString(7));
				sb.setFemale_ci_uppper_limit(rs.getString(8));
				sb.setMale_ri_lower_limit(rs.getString(9));
				sb.setMale_ri_upper_limit(rs.getString(10));
				sb.setMale_samples(rs.getInt(11));
				sb.setMale_ci_lower_limit(rs.getString(12));
				sb.setMale_ci_uppper_limit(rs.getString(13));
				sb.setMatric_system(rs.getString(14));
				sb.setUnit(rs.getString(15));
				
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
		Utils.logger.info("getListStandardReferenceIntervalsV2() of SQLDao.java is ending with size="+list.size());
		return list;

	}
	public List<AnalyteNameBean> getAnalyteNameList(String where_clause) throws DAOException
	{
		Utils.logger.info("call request for getAnalyteNameList("+where_clause+") in SQLDao.java");
		List<AnalyteNameBean> list = new ArrayList<AnalyteNameBean>();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from analyte_name "+where_clause);
			
			while (rs.next())
			{
				AnalyteNameBean ab=new AnalyteNameBean();
				
				ab.setAnalyte_name_id(rs.getInt(1));
				ab.setAnalyte_name(rs.getString(2));
				ab.setInstrument_name_id(rs.getInt(3));
				ab.setMetric_system(rs.getInt(4));
				ab.setValue_unit(rs.getString(5));
				ab.setV2_chart_path(rs.getString(6));
				ab.setV3_chart_path(rs.getString(7));
				ab.setV4_chart_path(rs.getString(8));
				ab.setV5_chart_path(rs.getString(9));
											
				list.add(ab);
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
		Utils.logger.info("getAnalyteNameList() of SQLDao.java is ending with size="+list.size());
		return list;

	}
	public List<RankBean> getRankTable(String where_clause) throws DAOException
	{
		Utils.logger.info("call request for getRankTable("+where_clause+") in SQLDao.java");
		List<RankBean> list = new ArrayList<RankBean>();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from rank "+where_clause);
			
			while (rs.next())
			{
				list.add(new RankBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
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
		Utils.logger.info("getRankTable() of SQLDao.java is ending with size="+list.size());
		return list;

	}
	public List<InstrumentBean> getInstrumentNameList() throws DAOException
	{
		Utils.logger.info("call request for getInstrumentNameList() in SQLDao.java");
		List<InstrumentBean> list = new ArrayList<InstrumentBean>();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from instrument_name");
			
			while (rs.next())
			{
				InstrumentBean ib=new InstrumentBean();
				
				ib.setInstrument_name_id(rs.getInt(1));
				ib.setInstrument_name(rs.getString(2));
				ib.setInstrument_link(rs.getString(3));
					
				//System.out.println(ib.getInstrument_name_id()+", "+ib.getInstrument_name()+", "+ib.getInstrument_link());
				list.add(ib);
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
		Utils.logger.info("getInstrumentNameList() of SQLDao.java is ending with size="+list.size());
		return list;

	}
	
	public List<StdReferenceIntervalBean> getListStdReferenceIntervals(int analyte_id, int instrument_id, int metric_unit, int gender, int version) throws DAOException
	{
		Utils.logger.info("getListStdReferenceIntervals() of SQLDao.java is called...");

		List<StdReferenceIntervalBean> list = new ArrayList<StdReferenceIntervalBean>();
		Connection c = null;
		
		try
		{
			String sqlString="SELECT * FROM standard_reference_intervals_version_n where analyte_name_id="+analyte_id+" and instrument_name_id="+instrument_id+" and metric_system="+metric_unit+" and gender="+gender+" and version="+version;
			Utils.logger.info("sql="+sqlString);
			c=cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sqlString);
			while (rs.next())
			{
				StdReferenceIntervalBean sb=new StdReferenceIntervalBean();
				sb.setWhole_record_index(rs.getLong(1));
				sb.setAnalyte_name_id(rs.getInt(2));
				sb.setInstrument_name_id(rs.getShort(3));
				sb.setGender(rs.getShort(4));
				sb.setAge_range(rs.getString(5));
				sb.setRi_lower_limit(rs.getString(6));
				sb.setRi_upper_limit(rs.getString(7));
				sb.setSamples(rs.getInt(8));
				sb.setCi_lower_limit(rs.getString(9));
				sb.setCi_upper_limit(rs.getString(10));
				sb.setMetric_system(rs.getInt(11));
				sb.setUnit_value(rs.getString(12));
				sb.setVersion(rs.getShort(13));

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
		Utils.logger.info("getListStdReferenceIntervals() of SQLDao.java is ending with size="+list.size());
		return list;

	}
	public List<RawDataVersionNBean> getRawDataList(short instrumentId, int analyteId, double ageFrom, double ageTo, short genderId, short metricSystem) throws DAOException
	{
		Utils.logger.info("getRawDataList("+instrumentId+", "+analyteId+", "+ageFrom+", "+ageTo+", "+genderId+", "+metricSystem+") of SQLDao.java is called...");

		List<RawDataVersionNBean> list = new ArrayList<RawDataVersionNBean>();
		Connection c = null;
		
		try
		{
			//String sqlString="SELECT * FROM raw_data_version_n where analyte_name_id="+analyteId+" and instrument_name_id="+instrumentId+" and version=2 and age>="+ageFrom+" and age<"+ageTo;
			//Added on Feb-20-2014 in the where statement with si_unit_data!=-1 and conventional_unit_data!=-1
			String sqlString="SELECT * FROM raw_data_version_n where si_unit_data!=-1 and conventional_unit_data!=-1 and analyte_name_id="+analyteId+" and instrument_name_id="+instrumentId+" and version=2 and age>="+ageFrom+" and age<"+ageTo;
			if(genderId!=3) sqlString+=" and gender="+genderId;
			Utils.logger.info("sql="+sqlString);
			c=cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sqlString);
			while (rs.next())
			{
				RawDataVersionNBean sb=new RawDataVersionNBean();
				sb.setWhole_record_index(rs.getLong(1));
				sb.setParticipant_id(rs.getLong(2));
				sb.setGender(rs.getShort(3));
				sb.setAge(rs.getDouble(4));
				sb.setSi_unit_data(rs.getDouble(5));
				sb.setConventional_unit_data(rs.getDouble(6));
				sb.setSi_unit(rs.getString(7));
				sb.setConventional_unit(rs.getString(8));
				sb.setEthnic_group(rs.getShort(9));
				sb.setInstrument_name_id(rs.getShort(10));
				sb.setAnalyte_name_id(rs.getInt(11));
				sb.setVersion(rs.getShort(12));
				sb.setInsertion_time(rs.getTimestamp(13));

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
		Utils.logger.info("getRawDataList() of SQLDao.java is ending with size="+list.size());
		return list;
	}
	public double[] getRawDataDoubleArray(short instrumentId, int analyteId, double ageFrom, double ageTo, short genderId, short metricSystem, int size) throws DAOException
	{
		Utils.logger.info("getRawDataDoubleArray("+instrumentId+", "+analyteId+", "+ageFrom+", "+ageTo+", "+genderId+", "+metricSystem+", "+size+") of SQLDao.java is called...");

		double[] data=new double[size];
		Connection c = null;
		
		try
		{
			String sqlString="";
			if(metricSystem==1)//SI
				sqlString="select si_unit_data from raw_data_version_n where si_unit_data!=-1 and conventional_unit_data!=-1 and analyte_name_id="+analyteId+" and instrument_name_id="+instrumentId+" and version=2 and age>="+ageFrom+" and age<"+ageTo;
			else //2=Conventional Unit Data
				sqlString="select conventional_unit_data from raw_data_version_n where si_unit_data!=-1 and conventional_unit_data!=-1 and analyte_name_id="+analyteId+" and instrument_name_id="+instrumentId+" and version=2 and age>="+ageFrom+" and age<"+ageTo;
			if(genderId!=3) sqlString+=" and gender="+genderId;
			Utils.logger.info("sql="+sqlString);
			c=cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sqlString);
			for(int i=0; i<size && rs.next();i++) data[i]=rs.getDouble(1);
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
		Utils.logger.info("getRawDataDoubleArray() of SQLDao.java is ending with size="+data.length);
		return data;
	}
}
