package com.sickkids.caliper.dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.exceptions.DAOException;

//**************************************************************
//This is to manage all common tables and carm-related-tables
//**************************************************************
public class SQLDao
{
	private ConnectionPool cp=null;
	private Connection c=null;
	
	public SQLDao()
	{
	}
	public SQLDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public SQLDao(Connection c)
	{
		this.c=c;
	}
	/*************************************************************************************
	 * Table: registered_user
	 *************************************************************************************/
	public AllRegisteredUserBean getOneRecordFromRegisteredUserTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getOneRecordFromRegisteredUserTable("+arg+") in SQLDao.java");
		AllRegisteredUserBean rb=new AllRegisteredUserBean();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM all_registered_user "+arg);
			if (rs.next())
			{	
				rb.setAppId(rs.getInt(1));
				rb.setUserId(rs.getString(2));
				rb.setFirstName(rs.getString(3));
				rb.setLastName(rs.getString(4));
				rb.setLoginLevel(rs.getInt(5));
				rb.setHospitalId(rs.getString(6));
				rb.setEmail(rs.getString(7));
				rb.setPassword(rs.getString(8));
				rb.setDepartment(rs.getString(9));
				rb.setPhone(rs.getString(10));
				rb.setResetPassword(rs.getInt(11));	
				rb.setPosition(rs.getString(12));
				rb.setDescription(rs.getString(13));	

				rb.setAccessLimitTime(rs.getTimestamp(14));
				rb.setPracticeYear(rs.getString(15));
				rb.setHealthDiscipline(rs.getString(16));
				rb.setPrimayClinicalPractice(rs.getString(17));
				rb.setSeniority(rs.getInt(18));			

				rb.setCreationTime(rs.getTimestamp(19));
				rb.setCreatorId(rs.getString(20));
				rb.setRemarks(rs.getString(21));
				rb.setValid(rs.getBoolean(22));
				
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" in SQLDao.java");
			throw new DAOException(e+", getOneRecordFromRegisteredUserTable() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getOneRecordFromRegisteredUserTable() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return rb;

	}

	//arg: where statement such as where email='abc@abc.ca'
	public List<AllRegisteredUserBean> getListFromRegisteredUserTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getListFromRegisteredUserTable("+arg+") in SQLDao.java");
		List<AllRegisteredUserBean> list=new ArrayList<AllRegisteredUserBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * FROM all_registered_user "+arg);
			while (rs.next())
			{	
				AllRegisteredUserBean rb=new AllRegisteredUserBean();
				rb.setAppId(rs.getInt(1));
				rb.setUserId(rs.getString(2));
				rb.setFirstName(rs.getString(3));
				rb.setLastName(rs.getString(4));
				rb.setLoginLevel(rs.getInt(5));
				rb.setHospitalId(rs.getString(6));
				rb.setEmail(rs.getString(7));
				rb.setPassword(rs.getString(8));
				rb.setDepartment(rs.getString(9));
				rb.setPhone(rs.getString(10));
				rb.setResetPassword(rs.getInt(11));	
				rb.setPosition(rs.getString(12));
				rb.setDescription(rs.getString(13));	

				rb.setAccessLimitTime(rs.getTimestamp(14));
				rb.setPracticeYear(rs.getString(15));
				rb.setHealthDiscipline(rs.getString(16));
				rb.setPrimayClinicalPractice(rs.getString(17));
				rb.setSeniority(rs.getInt(18));			

				rb.setCreationTime(rs.getTimestamp(19));
				rb.setCreatorId(rs.getString(20));
				rb.setRemarks(rs.getString(21));
				rb.setValid(rs.getBoolean(22));
				list.add(rb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getListFromRegisteredUserTable(..) in SQLDao.java");
			throw new DAOException(e+", getListFromRegisteredUserTable() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getListFromRegisteredUserTable() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getListFromRegisteredUserTable() of SQLDao.java is ending with "+list.size());
		return list;

	}
	
	//called from account_management
	public List<AllRegisteredUserBean> getListUserTableAndHospital() throws DAOException
	{
		Utils.logger.info("call request for getListUserTableAndHospital() in SQLDao.java");
		List<AllRegisteredUserBean> list=new ArrayList<AllRegisteredUserBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT user_id, first_name, last_name, login_level, a.hospital_id, email, reset_password, access_limit_time, practice_year, health_discipline, primay_clinical_practice, seniority, a.valid, b.hospital_name FROM all_registered_user as a, hospital as b where a.hospital_id=b.hospital_id order by hospital_id asc");
			while (rs.next())
			{	
				AllRegisteredUserBean rb=new AllRegisteredUserBean();
				rb.setUserId(rs.getString(1));
				rb.setFirstName(rs.getString(2));
				rb.setLastName(rs.getString(3));
				rb.setLoginLevel(rs.getInt(4));
				rb.setHospitalId(rs.getString(5));
				rb.setEmail(rs.getString(6));				
				rb.setResetPassword(rs.getInt(7));	
				rb.setAccessLimitTime(rs.getTimestamp(8));
				rb.setPracticeYear(rs.getString(9));
				rb.setHealthDiscipline(rs.getString(10));
				rb.setPrimayClinicalPractice(rs.getString(11));
				rb.setSeniority(rs.getInt(12));			
				rb.setValid(rs.getBoolean(13));
				rb.setHopitalName(rs.getString(14));
				list.add(rb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getListUserTableAndHospital(..) in SQLDao.java");
			throw new DAOException(e+", getListUserTableAndHospital() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getListUserTableAndHospital() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getListUserTableAndHospital() of SQLDao.java is ending with "+list.size());
		return list;

	}
	//return the number of rows updated
	public int updateRegisteredUserTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for updateRegisteredUserTable("+arg+") in SQLDao.java");
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement stmt = c.createStatement() ;
			int rows = stmt.executeUpdate(arg) ;

			return rows;
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" updateRegisteredUserTable(arg) in SQLDao.java" );
			throw new DAOException(e+", updateRegisteredUserTable() in SQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" updateRegisteredUserTable() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}

	}
	/*************************************************************************************
	 * Table: login_level
	 *************************************************************************************/
	public List<AllLoginLevelBean> getLoginLevelList() throws DAOException
	{
		Utils.logger.info("getLoginLevelList() of SQLDao.java is called...");

		List<AllLoginLevelBean> list = new ArrayList<AllLoginLevelBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select login_level, login_level_description from all_login_level order by login_level asc;");
			while (rs.next())
			{
				AllLoginLevelBean lb=new AllLoginLevelBean();

				lb.setLoginLevel(rs.getInt(1));
				lb.setLoginLevelDescription(rs.getString(2));

				list.add(lb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getLoginLevelList() in SQLDao.java");
			throw new DAOException(e+", getLoginLevelList() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getLoginLevelList() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getLoginLevelList() of SQLDao.java is ending with "+list.size());
		return list;
	}
	//Check if there exists the same key
	public boolean existSameKey(String sqlCmd) throws DAOException
	{
		Utils.logger.info("existSameKey("+sqlCmd+") of SQLDao.java is called...");

		Connection c = null;
		boolean bResult=false;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sqlCmd);
			if (rs.next()) bResult=(rs.getInt(1)>0);
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e+", existSameKey() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" existSameKey() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage()+" existSameKey(sqlCmd) in SQLDao.java");
			}
		}
		Utils.logger.info("existSameKey(sqlCmd) of SQLDao.java is ending with "+bResult);
		return bResult;
	}

	//return the number of rows updated
	public int updateInsertGenericSqlCmd(String arg) throws DAOException
	{
		Utils.logger.info("call request for updateInsertGenericSqlCmd("+arg+") in SQLDao.java");
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement stmt = c.createStatement() ;
			int rows = stmt.executeUpdate(arg) ;

			return rows;
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" updateInsertGenericSqlCmd(arg) in SQLDao.java" );
			throw new DAOException(e+", updateInsertGenericSqlCmd() in SQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" updateInsertGenericSqlCmd() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}

	}
	public int updateInsertGenericSqlCmds(String arg1, String arg2) throws DAOException
	{
		Utils.logger.info("call request for updateInsertGenericSqlCmds(\n["+arg1+"],\n["+arg2+"]) in SQLDao.java");
		Connection c = null;

		try
		{
			c = cp.getConnection();
			c.setAutoCommit(false);
			Statement s = c.createStatement();
			s.executeUpdate(arg1);		
			int rows = s.executeUpdate(arg2) ;
			c.commit();//perform transaction
			return rows;
		}
		catch (SQLException e)
		{
			try{c.rollback();}catch (Exception inner_e){}
			Utils.logger.severe(e.getMessage()+" updateInsertGenericSqlCmds(arg) in SQLDao.java" );
			throw new DAOException(e+", updateInsertGenericSqlCmds() in SQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
					c.setAutoCommit(true);
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" updateInsertGenericSqlCmds() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}

	}
	public int updateInsertGenericSql3Cmds(String arg1, String arg2, String arg3) throws DAOException
	{
		Utils.logger.info("call request for updateInsertGenericSql3Cmds(\n["+arg1+"],\n["+arg2+"],\n["+arg3+"]) in SQLDao.java");
		Connection c = null;

		try
		{
			c = cp.getConnection();
			c.setAutoCommit(false);
			Statement s = c.createStatement();
			s.executeUpdate(arg1);	
			s.executeUpdate(arg2);
			int rows = s.executeUpdate(arg3) ;
			c.commit();//perform transaction
			return rows;
		}
		catch (SQLException e)
		{
			try{c.rollback();}catch (Exception inner_e){}
			Utils.logger.severe(e.getMessage()+" updateInsertGenericSql3Cmds(arg) in SQLDao.java" );
			throw new DAOException(e+", updateInsertGenericSql3Cmds() in SQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
					c.setAutoCommit(true);
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" updateInsertGenericSql3Cmds() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}

	}
	public long getLastIndex(String sqlCmd)
	{
		
		
		Utils.logger.info("INFO: getLastIndex("+sqlCmd+") is entering in SQLDao.java");
		Connection c = null;
		long last_index=0L;

		try
		{
			c = cp.getConnection();


			Statement s = c.createStatement();

			ResultSet rs = s.executeQuery(sqlCmd);
			rs.next();
			last_index=rs.getLong(1);
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getLastIndex() in SQLDao.java");
			throw new DAOException(e+", getLastIndex() in SQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getLastIndex() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("INFO: getLastIndex() is ending in SQLDao.java");
		return last_index;
	}
	
	//**************************************************************************************
	//  Generic Methods 
	//**************************************************************************************
	public String  getGenericString(String arg) throws DAOException
	{
		Utils.logger.info("call request for getGenericString("+arg+") in SQLDao.java");
		String retString="";
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(arg);

			if (rs.next())
			{
				for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) retString += rs.getString(x)+"|";
				if(retString.length()>0) retString=retString.substring(0, retString.length()-1);//remove the last pipe
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getGenericString(..) in SQLDao.java");
			throw new DAOException(e+", getGenericString() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getGenericString() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getGenericString() of SQLDao.java is ending with="+retString);
		return retString;

	}
	public long  getGenericLong(String arg) throws DAOException
	{
		Utils.logger.info("call request for getGenericLong("+arg+") in SQLDao.java");
		long retVal=0L;
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(arg);

			if (rs.next())
			{
				retVal=rs.getLong(1);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getGenericLong(..) in SQLDao.java");
			throw new DAOException(e+", getGenericLong() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getGenericLong() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getGenericLong() of SQLDao.java is ending with="+retVal);
		return retVal;

	}
	public List<String> getGenericStringList(String sqlCmd) throws DAOException
	{
		Utils.logger.info("getGenericStringList("+sqlCmd+") of SQLDao.java is called...");

		List<String> list = new ArrayList<String>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs=s.executeQuery(sqlCmd);//select status from epoch_substudy_status order by status_index asc
			while (rs.next())
			{
				list.add(rs.getString(1));
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getGenericStringList() in SQLDao.java");
			throw new DAOException(e+", getGenericStringList() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getGenericStringList() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}

		Utils.logger.info("getGenericStringList() of SQLDao.java is ending with "+list.size());
		return list;
	}
	public long getCurrentSequence(String sequenceName) throws DAOException
	{		
		Utils.logger.info("call request for getCurrentSequence("+sequenceName+") in SQLDao.java");
		long retVal=0;
		try
		{
			c = cp.getConnection();
			ResultSet rs = c.createStatement().executeQuery("SELECT currval('"+sequenceName+"');");
			rs.next();
			retVal=rs.getLong(1);		
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" in getCurrentSequence("+sequenceName+") in SQLDao.java");
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getCurrentSequence() in SQLDao.java ");
	    		}
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
			Utils.logger.info("call ending for getCurrentSequence("+sequenceName+") in SQLDao.java");
		}
		return retVal;
	}
	/*************************************************************************************
	 * Table: curriculum_current
	 *************************************************************************************/
	public List<CurriculumCurrentBean> getCurriculumCurrentList(String where_statement) throws DAOException
	{
		Utils.logger.info("getCurriculumCurrentList() of SQLDao.java is called...");

		List<CurriculumCurrentBean> list = new ArrayList<CurriculumCurrentBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from curriculum_current "+where_statement+";");
			while (rs.next())
			{
				CurriculumCurrentBean lb=new CurriculumCurrentBean();

				lb.setCourseNumber(rs.getInt(1));
				lb.setCourseName(rs.getString(2));
				lb.setLecturerId(rs.getString(3));
				lb.setTaId(rs.getString(4));
				lb.setInteractiveSiteViewer1Id(rs.getString(5));
				lb.setInteractiveSiteViewer2Id(rs.getString(6));
				lb.setOnewaySiteViewer1Id(rs.getString(7));
				lb.setOnewaySiteViewer2Id(rs.getString(8));
				lb.setOnewaySiteViewer3Id(rs.getString(9));
				lb.setOnewaySiteViewer4Id(rs.getString(10));
				lb.setOnewaySiteViewer5Id(rs.getString(11));
				lb.setOnewaySiteViewer6Id(rs.getString(12));
				lb.setCourseDataFileNumber(rs.getInt(13));
				lb.setCourseTimeTableNumber(rs.getInt(14));
				lb.setCreatorId(rs.getString(15));
				lb.setCreationTime(rs.getTimestamp(16));
				lb.setRemarks(rs.getString(17));
				lb.setValid(rs.getBoolean(18));

				list.add(lb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getCurriculumCurrentList() in SQLDao.java");
			throw new DAOException(e+", getCurriculumCurrentList() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getCurriculumCurrentList() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getCurriculumCurrentList() of SQLDao.java is ending with "+list.size());
		return list;
	}
	/*************************************************************************************
	 * Table: country
	 *************************************************************************************/
	public List<CountryBean> getCountryList() throws DAOException
	{
		Utils.logger.info("getCountryList() of SQLDao.java is called...");

		List<CountryBean> list = new ArrayList<CountryBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from country;");
			while (rs.next())
			{
				CountryBean cb=new CountryBean();
				cb.setCountryId(rs.getInt(1));
				cb.setCountry(rs.getString(2));

				list.add(cb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getCountryList() in SQLDao.java");
			throw new DAOException(e+", getCountryList() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getCountryList() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getCountryList() of SQLDao.java is ending with "+list.size());
		return list;
	}
	//Check if there exists the same key
	public String getCountryName(int country_id) throws DAOException
	{
		Connection c = null;
		String country="NA";

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select country from country where country_id="+country_id+";");
			if (rs.next()) country=rs.getString(1);
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e+", getCountryName() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getCountryName() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return country;
	}
	//Get a list for non-created hospital codes
	public List<String> getNonCreatedHospitalCodeList() throws DAOException
	{
		Utils.logger.info("getNonCreatedHospitalCodeList() of SQLDao.java is called...");

		List<String> list = new ArrayList<String>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select hospital_id from hospital_id_list where hospital_id not in (select hospital_id from hospital);");
			while (rs.next())
			{
				list.add(rs.getString(1));
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getNonCreatedHospitalCodeList() in SQLDao.java");
			throw new DAOException(e+", getNonCreatedHospitalCodeList() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getNonCreatedHospitalCodeList() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getNonCreatedHospitalCodeList() of SQLDao.java is ending with "+list.size());
		return list;
	}
	public List<HospitalBean> getHospitalList(String whereStatement) throws DAOException
	{
		Utils.logger.info("getHospitalList() of SQLDao.java is called...");

		List<HospitalBean> list = new ArrayList<HospitalBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from hospital "+whereStatement);
			while (rs.next())
			{
				HospitalBean hb=new HospitalBean();			
				
				hb.setHospitalId(rs.getString(1));
				hb.setHospitalName(rs.getString(2));
				hb.setCountry(rs.getString(3));
				hb.setProvState(rs.getString(4));
				hb.setCity(rs.getString(5));
				hb.setAddress(rs.getString(6));
				hb.setPostalCode(rs.getString(7));
				hb.setPhone(rs.getString(8));
				hb.setDescription(rs.getString(9));
				hb.setCreatorId(rs.getString(10));
				hb.setCreationTime(rs.getTimestamp(11));
				hb.setRemarks(rs.getString(12));
				hb.setValid(rs.getBoolean(13));
				
				list.add(hb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getHospitalList() in SQLDao.java");
			throw new DAOException(e+", getHospitalList() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getHospitalList() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getHospitalList() of SQLDao.java is ending with "+list.size());
		return list;
	}
	public List<FileLibraryBean> getFileList(String whereStatement) throws DAOException
	{
		Utils.logger.info("getFileList() of SQLDao.java is called...");

		List<FileLibraryBean> list = new ArrayList<FileLibraryBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from file_library "+whereStatement);
			while (rs.next())
			{
				FileLibraryBean fb=new FileLibraryBean();			
				
				fb.setFileId(rs.getInt(1));
				fb.setFileNameFormal(rs.getString(2));
				fb.setDescription(rs.getString(3));
				fb.setFileType(rs.getString(4));
				fb.setFileLocationPath(rs.getString(5));
				fb.setFileVersion(rs.getInt(6));
				fb.setFileNameSubmitted(rs.getString(7));
				fb.setFileNameGenerated(rs.getString(8));
				fb.setSubmitterId(rs.getString(9));
				fb.setSubmissionTime(rs.getTimestamp(10));
				fb.setFileSize(rs.getLong(11));
				fb.setRemarks(rs.getString(12));
				fb.setValid(rs.getBoolean(13));
				
				list.add(fb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getFileList() in SQLDao.java");
			throw new DAOException(e+", getFileList() in SQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getFileList() in SQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getFileList() of SQLDao.java is ending with "+list.size());
		return list;
	}
}
