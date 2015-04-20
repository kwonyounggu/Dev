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
public class CarmSQLDao
{
	private ConnectionPool cp=null;
	private Connection c=null;
	
	public CarmSQLDao()
	{
	}
	public CarmSQLDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public CarmSQLDao(Connection c)
	{
		this.c=c;
	}
	/*************************************************************************************
	 * Table: registered_user
	 *************************************************************************************/
	public AllRegisteredUserBean createRegisteredUserRecord(AllRegisteredUserBean rb) throws DAOException
	{

		Connection c = null;
		PreparedStatement ps = null;

		try
		{
			c = cp.getConnection();
			String sQuery="insert into all_registered_user values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = c.prepareStatement(sQuery);

			ps.setInt(1, rb.getApp_id());
			ps.setString(2, rb.getUser_id());
			ps.setString(3, rb.getFirst_name());
			ps.setString(4, rb.getLast_name());
			ps.setInt(5, rb.getLogin_level());//int
			ps.setString(6, rb.getHostpital_id());
			ps.setString(7, rb.getEmail());
			ps.setString(8, rb.getPassword());
			ps.setString(9, rb.getDepartment());
			ps.setString(10, rb.getPhone());
			ps.setInt(11, rb.getReset_password());//int
			ps.setString(12, rb.getPosition());
			ps.setString(13, rb.getDescription());
			ps.setString(14, rb.getAccessible_ids());
			ps.setTimestamp(15, rb.getAccess_limit_time());
			ps.setString(16, rb.getPractice_year());
			ps.setString(17, rb.getHealth_discipline());
			ps.setString(18, rb.getPrimay_clinical_practice());
			ps.setInt(19, rb.getSeniority());
			ps.setInt(20, rb.getMax_groups_to_review());
			ps.setString(21, rb.getItem_assignment_type());
			ps.setBoolean(22, rb.isMy_review_confirmatory());
			ps.setTimestamp(23, rb.getCreation_time());
			ps.setString(24, rb.getCreator_id());
			ps.setString(25, rb.getRemarks());
						
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" createRegisteredUserRecord() in CarmSQLDao.java");
			throw new DAOException(e+", createRegisteredUserRecord() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" createRegisteredUserRecord() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return rb;
	}
	//[CARM] arg: where statement
	public AllRegisteredUserBean getOneRecordFromRegisteredUserTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getOneRecordFromRegisteredUserTable("+arg+") in CarmSQLDao.java");
		AllRegisteredUserBean rb=new AllRegisteredUserBean();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM all_registered_user "+arg);
			if (rs.next())
			{
				rb.setApp_id(rs.getInt(1));
				rb.setUser_id(rs.getString(2));
				rb.setFirst_name(rs.getString(3));
				rb.setLast_name(rs.getString(4));
				rb.setLogin_level(rs.getInt(5));
				rb.setHostpital_id(rs.getString(6));
				rb.setEmail(rs.getString(7));
				rb.setPassword(rs.getString(8));
				rb.setDepartment(rs.getString(9));
				rb.setPhone(rs.getString(10));
				rb.setReset_password(rs.getInt(11));	
				rb.setPosition(rs.getString(12));
				rb.setDescription(rs.getString(13));	
				rb.setAccessible_ids(rs.getString(14));
				rb.setAccess_limit_time(rs.getTimestamp(15));
				rb.setPractice_year(rs.getString(16));
				rb.setHealth_discipline(rs.getString(17));
				rb.setPrimay_clinical_practice(rs.getString(18));
				rb.setSeniority(rs.getInt(19));			
				rb.setMax_groups_to_review(rs.getInt(20));
				rb.setItem_assignment_type(rs.getString(21));
				rb.setMy_review_confirmatory(rs.getBoolean(22));
				rb.setCreation_time(rs.getTimestamp(23));
				rb.setCreator_id(rs.getString(24));
				rb.setRemarks(rs.getString(25));
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" in CarmSQLDao.java");
			throw new DAOException(e+", getOneRecordFromRegisteredUserTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getOneRecordFromRegisteredUserTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return rb;

	}
	public AllRegisteredUserBeanM getOneRecordFromRegisteredUserTableM(String arg) throws DAOException
	{
		Utils.logger.info("call request for getOneRecordFromRegisteredUserTableM("+arg+") in CarmSQLDao.java");
		AllRegisteredUserBeanM rb=new AllRegisteredUserBeanM();
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
				rb.setAccessibleIds(rs.getString(14));
				rb.setAccessLimitTime(rs.getTimestamp(15));
				rb.setPracticeYear(rs.getString(16));
				rb.setHealthDiscipline(rs.getString(17));
				rb.setPrimayClinicalPractice(rs.getString(18));
				rb.setSeniority(rs.getInt(19));			
				rb.setMaxGroupsToReview(rs.getInt(20));
				rb.setItemAssignmentType(rs.getString(21));
				rb.setMyReviewConfirmatory(rs.getBoolean(22));
				rb.setCreationTime(rs.getTimestamp(23));
				rb.setCreatorId(rs.getString(24));
				rb.setRemarks(rs.getString(25));
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" in CarmSQLDao.java");
			throw new DAOException(e+", getOneRecordFromRegisteredUserTableM() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getOneRecordFromRegisteredUserTableM() in CarmSQLDao.java ");
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
		Utils.logger.info("call request for getListFromRegisteredUserTable("+arg+") in CarmSQLDao.java");
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
				rb.setApp_id(rs.getInt(1));
				rb.setUser_id(rs.getString(2));
				rb.setFirst_name(rs.getString(3));
				rb.setLast_name(rs.getString(4));
				rb.setLogin_level(rs.getInt(5));
				rb.setHostpital_id(rs.getString(6));
				rb.setEmail(rs.getString(7));
				rb.setPassword(rs.getString(8));
				rb.setDepartment(rs.getString(9));
				rb.setPhone(rs.getString(10));
				rb.setReset_password(rs.getInt(11));	
				rb.setPosition(rs.getString(12));
				rb.setDescription(rs.getString(13));	
				rb.setAccessible_ids(rs.getString(14));
				rb.setAccess_limit_time(rs.getTimestamp(15));
				rb.setPractice_year(rs.getString(16));
				rb.setHealth_discipline(rs.getString(17));
				rb.setPrimay_clinical_practice(rs.getString(18));
				rb.setSeniority(rs.getInt(19));			
				rb.setMax_groups_to_review(rs.getInt(20));
				rb.setItem_assignment_type(rs.getString(21));
				rb.setMy_review_confirmatory(rs.getBoolean(22));
				rb.setCreation_time(rs.getTimestamp(23));
				rb.setCreator_id(rs.getString(24));
				rb.setRemarks(rs.getString(25));

				list.add(rb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getListFromRegisteredUserTable(..) in CarmSQLDao.java");
			throw new DAOException(e+", getListFromRegisteredUserTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getListFromRegisteredUserTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getListFromRegisteredUserTable() of CarmSQLDao.java is ending with "+list.size());
		return list;

	}
	//return the number of rows updated
	public int updateRegisteredUserTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for updateRegisteredUserTable("+arg+") in CarmSQLDao.java");
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
			Utils.logger.severe(e.getMessage()+" updateRegisteredUserTable(arg) in CarmSQLDao.java" );
			throw new DAOException(e+", updateRegisteredUserTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" updateRegisteredUserTable() in CarmSQLDao.java ");
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
		Utils.logger.info("getLoginLevelList() of CarmSQLDao.java is called...");

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
			Utils.logger.severe(e.getMessage()+" getLoginLevelList() in CarmSQLDao.java");
			throw new DAOException(e+", getLoginLevelList() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getLoginLevelList() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getLoginLevelList() of CarmSQLDao.java is ending with "+list.size());
		return list;
	}
	//Check if there exists the same key
	public boolean existSameKey(String sqlCmd) throws DAOException
	{
		Utils.logger.info("existSameKey("+sqlCmd+") of CarmSQLDao.java is called...");

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
			throw new DAOException(e+", existSameKey() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" existSameKey() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage()+" existSameKey(sqlCmd) in CarmSQLDao.java");
			}
		}
		Utils.logger.info("existSameKey(sqlCmd) of CarmSQLDao.java is ending with "+bResult);
		return bResult;
	}
	public List<CarmDeMX> getCarmDeMXList(String whereClause) throws DAOException
	{
		Utils.logger.info("getCarmDeMXList("+whereClause+") of CarmSQLDao.java is called...");

		List<CarmDeMX> list = new ArrayList<CarmDeMX>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from carm_de_mx "+whereClause+";");
			while (rs.next())
			{
				CarmDeMX cb=new CarmDeMX();

				cb.setCarm_id(rs.getInt(1));
				cb.setSite(rs.getString(2));
				cb.setPat_no(rs.getString(3));
				cb.setEvent_no(rs.getInt(4));
				cb.setDe_ids(rs.getString(5));
				cb.setDe1_id(rs.getInt(6));
				cb.setDe2_id(rs.getInt(7));
				cb.setDe3_ids(rs.getString(8));
				cb.setDe4_id(rs.getInt(9));
				cb.setDe5_ids(rs.getString(10));
				cb.setDe6_id(rs.getInt(11));
				cb.setRemarks(rs.getString(12));
				cb.setCreated_time(rs.getTimestamp(13));
				cb.setFlag(rs.getBoolean(14));
				cb.setReview_status(rs.getString(15));
				
				list.add(cb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getCarmDeMXList() in CarmSQLDao.java");
			throw new DAOException(e+", getCarmDeMXList() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getCarmDeMXList() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getCarmDeMXList() of CarmSQLDao.java is ending with "+list.size());
		return list;
	}
	public List<CarmDeMXBeanM> getCarmDeMXListM(String whereClause) throws DAOException
	{
		Utils.logger.info("getCarmDeMXListM("+whereClause+") of CarmSQLDao.java is called...");

		List<CarmDeMXBeanM> list = new ArrayList<CarmDeMXBeanM>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from carm_de_mx "+whereClause+";");
			while (rs.next())
			{
				CarmDeMXBeanM cb=new CarmDeMXBeanM();

				cb.setCarmId(rs.getInt(1));
				cb.setSite(rs.getString(2));
				cb.setPatNo(rs.getString(3));
				cb.setEventNo(rs.getInt(4));
				cb.setDeIds(rs.getString(5));
				cb.setDe1Id(rs.getInt(6));
				cb.setDe2Id(rs.getInt(7));
				cb.setDe3Ids(rs.getString(8));
				cb.setDe4Id(rs.getInt(9));
				cb.setDe5Ids(rs.getString(10));
				cb.setDe6Id(rs.getInt(11));
				cb.setRemarks(rs.getString(12));
				cb.setCreatedTime(rs.getTimestamp(13));
				cb.setFlag(rs.getBoolean(14));
				cb.setReviewStatus(rs.getString(15));
				
				list.add(cb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getCarmDeMXListM() in CarmSQLDao.java");
			throw new DAOException(e+", getCarmDeMXListM() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getCarmDeMXListM() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getCarmDeMXListM() of CarmSQLDao.java is ending with "+list.size());
		return list;
	}
	public void createCarmDeMX_Records(List<CarmDeMX> list) throws DAOException
	{
		Utils.logger.info("INFO: createCarmDeMX_Records(List<CarmDeMX> list) is entering in CarmSQLDao.java");
		Connection c = null;
		PreparedStatement ps = null;

		try
		{
			c = cp.getConnection();
			String sQuery="insert into carm_de_mx values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = c.prepareStatement(sQuery);
			for(CarmDeMX cb : list)
				if(cb.isFlag())
				{
					System.err.println(cb.getCarm_id());
					ps.setInt(1, cb.getCarm_id());
					ps.setString(2, cb.getSite());
					ps.setString(3, cb.getPat_no());
					ps.setInt(4, cb.getEvent_no());
					ps.setString(5, cb.getDe_ids());
					ps.setInt(6, cb.getDe1_id());
					ps.setInt(7, cb.getDe2_id());
					ps.setString(8, cb.getDe3_ids());
					ps.setInt(9, cb.getDe4_id());
					ps.setString(10, cb.getDe5_ids());
					ps.setInt(11, cb.getDe6_id());
					ps.setString(12, cb.getRemarks());
					ps.setTimestamp(13, cb.getCreated_time());
					ps.setBoolean(14, false);
					ps.setString(15, cb.getReview_status());
					ps.executeUpdate();
				}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" createCarmDeMX_Records(List<CarmDeMX> list) in CarmSQLDao.java");
			throw new DAOException(e+", createCarmDeMX_Records(List<CarmDeMX> list) in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" createCarmDeMX_Records() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("INFO: createCarmDeMX_Records(List<CarmDeMX> list) is ending in CarmSQLDao.java");
	}
	public void createCarmClinicalSummary_Records(List<CarmClinicalSummaryEditBean> summaryList) throws DAOException
	{
		Utils.logger.info("INFO: createCarmClinicalSummary_Records(List<CarmClinicalSummaryEditBean> list) is entering in CarmSQLDao.java");
		Connection c = null;
		PreparedStatement ps = null;

		try
		{
			c = cp.getConnection();
			String sQuery="insert into carm_clinical_summary_edit values(?,?,?,?,?,?)";
			ps = c.prepareStatement(sQuery);
			for(CarmClinicalSummaryEditBean cb : summaryList)
			{
				ps.setInt(1, cb.getCarm_id());
				ps.setString(2, cb.getOld_clinical_summary());
				ps.setString(3, cb.getNew_clinical_summary());
				ps.setBoolean(4, cb.isEdited());
				ps.setTimestamp(5, cb.getEdit_date());
				ps.setString(6, cb.getEditor_id());
				
				ps.executeUpdate();
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" createCarmClinicalSummary_Records(List<CarmClinicalSummaryEditBean> list) in CarmSQLDao.java");
			throw new DAOException(e+", createCarmClinicalSummary_Records(List<CarmClinicalSummaryEditBean> list) in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" createCarmClinicalSummary_Records() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("INFO: createCarmClinicalSummary_Records(List<CarmClinicalSummaryEditBean> list) is ending in CarmSQLDao.java");
	}
	public List<CarmClinicalSummaryEditBean> getCarmClinicalSummaryEditList(String whereSql) throws DAOException
	{
		Utils.logger.info("getCarmClinicalSummaryEditList() of CarmSQLDao.java is called...");

		List<CarmClinicalSummaryEditBean> list = new ArrayList<CarmClinicalSummaryEditBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from carm_clinical_summary_edit "+whereSql+";");
			while (rs.next())
			{
				CarmClinicalSummaryEditBean cb=new CarmClinicalSummaryEditBean();

				cb.setCarm_id(rs.getInt(1));
				cb.setOld_clinical_summary(rs.getString(2));
				cb.setNew_clinical_summary(rs.getString(3));
				cb.setEdited(rs.getBoolean(4));
				cb.setEdit_date(rs.getTimestamp(5));
				cb.setEditor_id(rs.getString(6));
								
				list.add(cb);
			}
		}
		catch (Exception e)
		{
			Utils.logger.severe(e.getMessage()+" getCarmClinicalSummaryEditList() in CarmSQLDao.java");
			throw new DAOException(e+", getCarmClinicalSummaryEditList() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
        		if (c!= null) 
        		{
        			cp.free(c);
        			c=null;
        			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getCarmClinicalSummaryEditList() in CarmSQLDao.java ");
        		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		
		Utils.logger.info("getCarmClinicalSummaryEditList() of CarmSQLDao.java is ending with "+list.size());
		return list;
	}
	public List<CarmAssayUnitBean> getListFromCarmAssayUnitTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getListFromCarmAssayUnitTable("+arg+") in CarmSQLDao.java");
		List<CarmAssayUnitBean> list=new ArrayList<CarmAssayUnitBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sqlCmd="select * FROM carm_assay_unit "+arg;
			ResultSet rs = s.executeQuery(sqlCmd);
			while (rs.next())
			{				
				CarmAssayUnitBean cb=new CarmAssayUnitBean();
				
				cb.setSite_id(rs.getString(1));
				cb.setAssay_name(rs.getString(2));
				cb.setAssay_unit(rs.getString(3));
				
				list.add(cb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getListFromCarmAssayUnitTable(..) in CarmSQLDao.java");
			throw new DAOException(e+", getListFromCarmAssayUnitTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
        		{
        			cp.free(c);
        			c=null;
        			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getListFromCarmAssayUnitTable() in CarmSQLDao.java ");
        		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getListFromCarmAssayUnitTable() of CarmSQLDao.java is ending with "+list.size());
		return list;

	}
	public List<AllPointDescBean> getAllPointDescList(String whereSql) throws DAOException
	{
		Utils.logger.info("getAllPointDescList() of CarmSQLDao.java is called...");

		List<AllPointDescBean> list = new ArrayList<AllPointDescBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from all_point_desc "+whereSql+";");
			while (rs.next())
			{
				AllPointDescBean pb=new AllPointDescBean();

				pb.setApp_id(rs.getInt(1));
				pb.setItem_hierachy(rs.getString(2));
				pb.setPoint_id(rs.getInt(3));
				pb.setPoint_name(rs.getString(4));
				pb.setPoint_explanation(rs.getString(5));
				pb.setPoint_value_string(rs.getString(6));
				pb.setPoint_value_number(rs.getInt(7));
				pb.setPoint_value_min(rs.getInt(8));
				pb.setPoint_value_max(rs.getInt(9));
				pb.setPoint_value_units(rs.getString(10));
				pb.setPoint_value_options(rs.getString(11));
				pb.setConfidence_level(rs.getInt(12));
				pb.setCreation_time(rs.getTimestamp(13));
				pb.setCreator_id(rs.getString(14));
				pb.setRemarks(rs.getString(15));
								
				list.add(pb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getAllPointDescList() in CarmSQLDao.java");
			throw new DAOException(e+", getAllPointDescList() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
        		{
        			cp.free(c);
        			c=null;
        			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getAllPointDescList() in CarmSQLDao.java ");
        		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getAllPointDescList() of CarmSQLDao.java is ending with "+list.size());
		return list;
	}
	public List<AllPointDescBeanM> getAllPointDescListM(String whereSql) throws DAOException
	{
		Utils.logger.info("getAllPointDescListM() of CarmSQLDao.java is called...");

		List<AllPointDescBeanM> list = new ArrayList<AllPointDescBeanM>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from all_point_desc "+whereSql+";");
			while (rs.next())
			{
				AllPointDescBeanM pb=new AllPointDescBeanM();

				pb.setAppId(rs.getInt(1));
				pb.setItemHierachy(rs.getString(2));
				pb.setPointId(rs.getInt(3));
				pb.setPointName(rs.getString(4));
				pb.setPointExplanation(rs.getString(5));
				pb.setPointValueString(rs.getString(6));
				pb.setPointValueNumber(rs.getInt(7));
				pb.setPointValueMin(rs.getInt(8));
				pb.setPointValueMax(rs.getInt(9));
				pb.setPointValueUnits(rs.getString(10));
				pb.setPointValueOptions(rs.getString(11));
				pb.setConfidenceLevel(rs.getInt(12));
				pb.setCreationTime(rs.getTimestamp(13));
				pb.setCreatorId(rs.getString(14));
				pb.setRemarks(rs.getString(15));
								
				list.add(pb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getAllPointDescListM() in CarmSQLDao.java");
			throw new DAOException(e+", getAllPointDescListM() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
        		{
        			cp.free(c);
        			c=null;
        			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getAllPointDescListM() in CarmSQLDao.java ");
        		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getAllPointDescListM() of CarmSQLDao.java is ending with "+list.size());
		return list;
	}
	//return the number of rows updated
	public int updateInsertGenericSqlCmd(String arg) throws DAOException
	{
		Utils.logger.info("call request for updateInsertGenericSqlCmd("+arg+") in CarmSQLDao.java");
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
			Utils.logger.severe(e.getMessage()+" updateInsertGenericSqlCmd(arg) in CarmSQLDao.java" );
			throw new DAOException(e+", updateInsertGenericSqlCmd() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" updateInsertGenericSqlCmd() in CarmSQLDao.java ");
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
		Utils.logger.info("call request for updateInsertGenericSqlCmds(\n["+arg1+"],\n["+arg2+"]) in CarmSQLDao.java");
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
			Utils.logger.severe(e.getMessage()+" updateInsertGenericSqlCmds(arg) in CarmSQLDao.java" );
			throw new DAOException(e+", updateInsertGenericSqlCmds() in CarmSQLDao.java");
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
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" updateInsertGenericSqlCmds() in CarmSQLDao.java ");
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
		Utils.logger.info("call request for updateInsertGenericSql3Cmds(\n["+arg1+"],\n["+arg2+"],\n["+arg3+"]) in CarmSQLDao.java");
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
			Utils.logger.severe(e.getMessage()+" updateInsertGenericSql3Cmds(arg) in CarmSQLDao.java" );
			throw new DAOException(e+", updateInsertGenericSql3Cmds() in CarmSQLDao.java");
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
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" updateInsertGenericSql3Cmds() in CarmSQLDao.java ");
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
		
		
		Utils.logger.info("INFO: getLastIndex("+sqlCmd+") is entering in CarmSQLDao.java");
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
			Utils.logger.severe(e.getMessage()+" getLastIndex() in CarmSQLDao.java");
			throw new DAOException(e+", getLastIndex() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getLastIndex() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("INFO: getLastIndex() is ending in CarmSQLDao.java");
		return last_index;
	}
	
	//**************************************************************************************
	//  Generic Methods 
	//**************************************************************************************
	public String  getGenericString(String arg) throws DAOException
	{
		Utils.logger.info("call request for getGenericString("+arg+") in CarmSQLDao.java");
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
			Utils.logger.severe(e.getMessage()+" getGenericString(..) in CarmSQLDao.java");
			throw new DAOException(e+", getGenericString() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getGenericString() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getGenericString() of CarmSQLDao.java is ending with="+retString);
		return retString;

	}
	public long  getGenericLong(String arg) throws DAOException
	{
		Utils.logger.info("call request for getGenericLong("+arg+") in CarmSQLDao.java");
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
			Utils.logger.severe(e.getMessage()+" getGenericLong(..) in CarmSQLDao.java");
			throw new DAOException(e+", getGenericLong() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getGenericLong() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getGenericLong() of CarmSQLDao.java is ending with="+retVal);
		return retVal;

	}
	public List<String> getGenericStringList(String sqlCmd) throws DAOException
	{
		Utils.logger.info("getGenericStringList("+sqlCmd+") of CarmSQLDao.java is called...");

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
			Utils.logger.severe(e.getMessage()+" getGenericStringList() in CarmSQLDao.java");
			throw new DAOException(e+", getGenericStringList() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getGenericStringList() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}

		Utils.logger.info("getGenericStringList() of CarmSQLDao.java is ending with "+list.size());
		return list;
	}
	public long getCurrentSequence(String sequenceName) throws DAOException
	{		
		Utils.logger.info("call request for getCurrentSequence("+sequenceName+") in CarmSQLDao.java");
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
			Utils.logger.severe(e.getMessage()+" in getCurrentSequence("+sequenceName+") in CarmSQLDao.java");
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
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getCurrentSequence() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
			Utils.logger.info("call ending for getCurrentSequence("+sequenceName+") in CarmSQLDao.java");
		}
		return retVal;
	}
	//**************************************************************************************
	//*****************   ALL_TABLES **************************************************
	//Table: all_item_reviewer_mx
	//*************************************************************************
	//arg: where statement
	public List<AllItemReviewerBean> getListFromAllItemReviewerMXTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getListFromAllItemReviewerMXTable("+arg+") in CarmSQLDao.java");
		List<AllItemReviewerBean> list=new ArrayList<AllItemReviewerBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sqlCmd="select a.app_id, a.group_id, a.item_id, a.reviewer_id, a.mediator_id, a.reviewer_action_status, a.action_status_time, a.creator_id,  a.creation_time, a.time_out, a.confirmatory, "+ 
								 "b.group_name, b.time_out, b.reviewers_per_item, b.reviewer_assignment_type, b.confirmatory, b.disagreement_process "+
								 "from all_item_reviewer_mx as a, all_review_group as b "+arg;
			ResultSet rs = s.executeQuery(sqlCmd);
			while (rs.next())
			{
				
				AllItemReviewerBean rb=new AllItemReviewerBean();
				rb.setApp_id(rs.getInt(1));
				rb.setGroup_id(rs.getInt(2));
				rb.setItem_id(rs.getInt(3));
				rb.setReviewer_id(rs.getString(4));
				rb.setMediator_id(rs.getString(5));
				rb.setReviewer_action_status(rs.getString(6));	
				rb.setAction_status_time(rs.getTimestamp(7));
				rb.setCreator_id(rs.getString(8));	
				rb.setCreation_time(rs.getTimestamp(9));
				rb.setTime_out(rs.getTimestamp(10));
				rb.setConfirmatory(rs.getBoolean(11));
			
				rb.setGroup_name(rs.getString(12));
				rb.setGroup_time_out(rs.getTimestamp(13));
				rb.setGroup_reviewers_per_item(rs.getInt(14));
				rb.setGroup_reviewer_assignment_type(rs.getString(15));
				rb.setGroup_confirmatory(rs.getBoolean(16));
				rb.setGroup_disagreement_process(rs.getString(17));
				

				list.add(rb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getListFromAllItemReviewerMXTable(..) in CarmSQLDao.java");
			throw new DAOException(e+", getListFromAllItemReviewerMXTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getListFromAllItemReviewerMXTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getListFromAllItemReviewerMXTable() of CarmSQLDao.java is ending with "+list.size());
		return list;

	}
	public List<AllItemReviewerBean> getPeerListFromAllItemReviewerMXTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getPeerListFromAllItemReviewerMXTable("+arg+") in CarmSQLDao.java");
		List<AllItemReviewerBean> list=new ArrayList<AllItemReviewerBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sqlCmd="select * from all_item_reviewer_mx "+arg;
			ResultSet rs = s.executeQuery(sqlCmd);
			while (rs.next())
			{
				
				AllItemReviewerBean rb=new AllItemReviewerBean();
				rb.setApp_id(rs.getInt(1));
				rb.setGroup_id(rs.getInt(2));
				rb.setItem_id(rs.getInt(3));
				rb.setReviewer_id(rs.getString(4));
				rb.setMediator_id(rs.getString(5));
				rb.setReviewer_action_status(rs.getString(6));	
				rb.setAction_status_time(rs.getTimestamp(7));
				rb.setCreator_id(rs.getString(8));	
				rb.setCreation_time(rs.getTimestamp(9));
				rb.setTime_out(rs.getTimestamp(10));
				rb.setConfirmatory(rs.getBoolean(11));
				
				list.add(rb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getPeerListFromAllItemReviewerMXTable(..) in CarmSQLDao.java");
			throw new DAOException(e+", getPeerListFromAllItemReviewerMXTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getPeerListFromAllItemReviewerMXTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getPeerListFromAllItemReviewerMXTable() of CarmSQLDao.java is ending with "+list.size());
		return list;
	}
    //*************************************************************************
	//Table: all_review_group
	//arg: where statement
	//*************************************************************************
	public List<AllReviewGroupBean> getListFromAllReviewGroupTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getListFromAllReviewGroupTable("+arg+") in CarmSQLDao.java");
		List<AllReviewGroupBean> list=new ArrayList<AllReviewGroupBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sqlCmd="select * FROM all_review_group "+arg;
			ResultSet rs = s.executeQuery(sqlCmd);
			while (rs.next())
			{				
				AllReviewGroupBean rb=new AllReviewGroupBean();
				rb.setApp_id(rs.getInt(1));
				rb.setGroup_id(rs.getInt(2));
				rb.setGroup_name(rs.getString(3));
				rb.setTime_out(rs.getTimestamp(4));
				rb.setReviewers_per_item(rs.getInt(5));
				rb.setReviewer_assignment_type(rs.getString(6));
				rb.setConfirmatory(rs.getBoolean(7));
				rb.setDisagreement_process(rs.getString(8));
				rb.setCreation_time(rs.getTimestamp(9));
				rb.setCreator_id(rs.getString(10));
				
				list.add(rb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getListFromAllReviewGroupTable(..) in CarmSQLDao.java");
			throw new DAOException(e+", getListFromAllReviewGroupTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getListFromAllReviewGroupTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getListFromAllReviewGroupTable() of CarmSQLDao.java is ending with "+list.size());
		return list;

	}
	//*************************************************************************
	//arg: where statement
	//*************************************************************************
	public List<AllApplicationBean> getListFromAllApplicationTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getListFromAllApplicationTable("+arg+") in CarmSQLDao.java");
		List<AllApplicationBean> list=new ArrayList<AllApplicationBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sqlCmd="select * FROM all_application "+arg;
			ResultSet rs = s.executeQuery(sqlCmd);
			while (rs.next())
			{				
				AllApplicationBean rb=new AllApplicationBean();
				rb.setApp_id(rs.getInt(1));
				rb.setApp_id_revision(rs.getInt(2));
				rb.setPurpose_of_app(rs.getString(3));
				rb.setApp_symbol(rs.getString(4));
				rb.setNumber_of_groups(rs.getInt(5));
				rb.setReviewers_per_item(rs.getInt(6));
				rb.setReviewer_assignment_type(rs.getString(7));
				rb.setConfirmatory(rs.getBoolean(8));
				rb.setDisagreement_process(rs.getString(9));
				rb.setCreation_time(rs.getTimestamp(10));
				rb.setCreator_id(rs.getString(11));
				rb.setRemarks(rs.getString(12));

				list.add(rb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getListFromAllApplicationTable(..) in CarmSQLDao.java");
			throw new DAOException(e+", getListFromAllApplicationTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getListFromAllApplicationTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getListFromAllApplicationTable() of CarmSQLDao.java is ending with "+list.size());
		return list;

	}
	public List<AllReviewDataBean> getListFromAllReviewDataTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getListFromAllReviewDataTable("+arg+") in CarmSQLDao.java");
		List<AllReviewDataBean> list=new ArrayList<AllReviewDataBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sqlCmd="select * FROM all_review_data "+arg;
			ResultSet rs = s.executeQuery(sqlCmd);
			while (rs.next())
			{				
				AllReviewDataBean rb=new AllReviewDataBean();

				rb.setApp_id(rs.getInt(1));
				rb.setGroup_id(rs.getInt(2));
				rb.setItem_id(rs.getInt(3));
				rb.setItem_hierachy(rs.getString(4));
				rb.setPoint_id(rs.getInt(5));
				rb.setPoint_value_string(rs.getString(6));
				rb.setPoint_value_number(rs.getDouble(7));
				rb.setPoint_value_min(rs.getDouble(8));
				rb.setPoint_value_max(rs.getDouble(9));
				rb.setPoint_value_units(rs.getString(10));
				rb.setPoint_value_options(rs.getString(11));
				rb.setConfidence_level(rs.getFloat(12));
				rb.setReview_time(rs.getTimestamp(13));
				rb.setReviewer_id(rs.getString(14));
				rb.setPoint_review_status(rs.getString(15));
				rb.setPoint_id_revision(rs.getInt(16));
				rb.setRemarks(rs.getString(17));

				list.add(rb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getListFromAllReviewDataTable(..) in CarmSQLDao.java");
			throw new DAOException(e+", getListFromAllReviewDataTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getListFromAllReviewDataTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getListFromAllReviewDataTable() of CarmSQLDao.java is ending with "+list.size());
		return list;

	}
	public AllReviewDataBean getOneRecordFromAllReviewDataTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getOneRecordFromAllReviewDataTable("+arg+") in CarmSQLDao.java");
		AllReviewDataBean rb=new AllReviewDataBean();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM all_review_data "+arg);
			if (rs.next())
			{
				rb.setApp_id(rs.getInt(1));
				rb.setGroup_id(rs.getInt(2));
				rb.setItem_id(rs.getInt(3));
				rb.setItem_hierachy(rs.getString(4));
				rb.setPoint_id(rs.getInt(5));
				rb.setPoint_value_string(rs.getString(6));
				rb.setPoint_value_number(rs.getDouble(7));
				rb.setPoint_value_min(rs.getDouble(8));
				rb.setPoint_value_max(rs.getDouble(9));
				rb.setPoint_value_units(rs.getString(10));
				rb.setPoint_value_options(rs.getString(11));
				rb.setConfidence_level(rs.getFloat(12));
				rb.setReview_time(rs.getTimestamp(13));
				rb.setReviewer_id(rs.getString(14));
				rb.setPoint_review_status(rs.getString(15));
				rb.setPoint_id_revision(rs.getInt(16));
				rb.setRemarks(rs.getString(17));
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" in CarmSQLDao.java");
			throw new DAOException(e+", getOneRecordFromAllReviewDataTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getOneRecordFromAllReviewDataTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return rb;

	}
	public AllReviewDataHistBean getOneRecordFromAllReviewDataHistTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getOneRecordFromAllReviewDataHistTable("+arg+") in CarmSQLDao.java");
		AllReviewDataHistBean rb=new AllReviewDataHistBean();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM all_review_data_hist "+arg);
			if (rs.next())
			{
				rb.setReview_data_hist_index(rs.getLong(1));
				rb.setApp_id(rs.getInt(2));
				rb.setGroup_id(rs.getInt(3));
				rb.setItem_id(rs.getInt(4));
				rb.setItem_hierachy(rs.getString(5));
				rb.setPoint_id(rs.getInt(6));
				rb.setPoint_value_string(rs.getString(7));
				rb.setPoint_value_number(rs.getDouble(8));
				rb.setPoint_value_min(rs.getDouble(9));
				rb.setPoint_value_max(rs.getDouble(10));
				rb.setPoint_value_units(rs.getString(11));
				rb.setPoint_value_options(rs.getString(12));
				rb.setConfidence_level(rs.getFloat(13));
				rb.setReview_time(rs.getTimestamp(14));
				rb.setReviewer_id(rs.getString(15));
				rb.setPoint_review_status(rs.getString(16));
				rb.setPoint_id_revision(rs.getInt(17));
				rb.setRemarks(rs.getString(18));
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" in CarmSQLDao.java");
			throw new DAOException(e+", getOneRecordFromAllReviewDataHistTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getOneRecordFromAllReviewDataHistTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return rb;

	}
	public List<AllForumBean> getListFromAllForumTable(String arg) throws DAOException
	{
		Utils.logger.info("call request for getListFromAllForumTable("+arg+") in CarmSQLDao.java");
		List<AllForumBean> list=new ArrayList<AllForumBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sqlCmd="select * FROM all_forum "+arg;
			ResultSet rs = s.executeQuery(sqlCmd);
			while (rs.next())
			{				
				AllForumBean fb=new AllForumBean();
				
				fb.setAll_forum_index(rs.getLong(1));
				fb.setApp_id(rs.getInt(2));
				fb.setGroup_id(rs.getInt(3));
				fb.setItem_id(rs.getInt(4));
				fb.setPoint_id(rs.getInt(5));
				fb.setSubmitter_opinion(rs.getString(6));
				fb.setSubmitter_id(rs.getString(7));
				fb.setSubmission_time(rs.getTimestamp(8));
				fb.setReply_to_all_forum_index(rs.getLong(9));
				fb.setRemarks(rs.getString(10));

				list.add(fb);
			}
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage()+" getListFromAllForumTable(..) in CarmSQLDao.java");
			throw new DAOException(e+", getListFromAllForumTable() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getListFromAllForumTable() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getListFromAllForumTable() of CarmSQLDao.java is ending with "+list.size());
		return list;

	}
	public List<CarmO_DEBean> getVitalSigns(String where_statment) throws DAOException
	{

		Connection c = null;
		List<CarmO_DEBean> retList=new ArrayList<CarmO_DEBean>();
		String sQuery="";
		try
		{			
			Utils.logger.info("getVitalSigns("+where_statment+") of CarmSQLDao.java");
			c = cp.getConnection();
			
			sQuery="select * from EPOCH_EV_DE "+where_statment;
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
			Utils.logger.severe(e.getMessage()+" getVitalSigns() in CarmSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getVitalSigns() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getVitalSigns() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("----END: getVitalSigns("+where_statment+") of CarmSQLDao.java -----");
		return retList;
	}
	public CarmO_DE2Bean getDiagnoseRecord(String whereStatment) throws DAOException
	{

		Connection c = null;
		String sQuery="";
		CarmO_DE2Bean de2Bean=new CarmO_DE2Bean();
		try
		{			
			Utils.logger.info("getDiagnoseRecord("+whereStatment+") of CarmSQLDao.java");
			c = cp.getConnection();
			
			sQuery="select * from EPOCH_EV_DE2 "+whereStatment;
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
			Utils.logger.severe(e.getMessage()+" getDiagnoseRecord() in CarmSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getDiagnoseRecord() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getDiagnoseRecord() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return de2Bean;
	}
	//==========
	public CarmO_DE4Bean getProblemRecord(String whereStatment) throws DAOException
	{

		Connection c = null;
		String sQuery="";
		CarmO_DE4Bean de4Bean=new CarmO_DE4Bean();
		try
		{		
			Utils.logger.info("getProblemRecord() of CarmSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getProblemRecord() of CarmSQLDao.java");
			c = cp.getConnection();
			
			sQuery="select * from EPOCH_EV_DE4 "+whereStatment;
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
			Utils.logger.severe(e.getMessage()+" getProblemRecord() in CarmSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getProblemRecord() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getProblemRecord() in CarmSQLDao.java ");
	    		}
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
			Utils.logger.info("getLaboratoryRecord() of CarmSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getLaboratoryRecord() of CarmSQLDao.java");
			c = cp.getConnection();
			if(c==null) System.err.println("-------connection is null.........getLaboratoryRecord()");
			sQuery="select * from EPOCH_EV_DE5 "+whereStatment;
			Utils.logger.info("sQuery="+sQuery+" in getLaboratoryRecord() of CarmSQLDao.java");
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
			Utils.logger.severe(e.getMessage()+" getLaboratoryRecord() in CarmSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getLaboratoryRecord() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getLaboratoryRecord() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return de5Bean;
	}
	public List<CarmO_DE3Bean> getMedications(String where_statment) throws DAOException
	{

		Connection c = null;
		List<CarmO_DE3Bean> retList=new ArrayList<CarmO_DE3Bean>();
		String sQuery="";
		try
		{	
			Utils.logger.info("getMedications() of CarmSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getMedications() of CarmSQLDao.java");
			c = cp.getConnection();
			
			sQuery="select * from EPOCH_EV_DE3 "+where_statment;
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
			Utils.logger.severe(e.getMessage()+" getMedications() in CarmSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getMedications() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getMedications() in CarmSQLDao.java ");
	    		}
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
			Utils.logger.info("getRadiologyRecord() of CarmSQLDao.java");
			//Utils.logger.info("Available connections="+upc.getAvailableConnectionsCount()+" getRadiologyRecord() of CarmSQLDao.java");
			c = cp.getConnection();
			
			sQuery="select * from EPOCH_EV_DE6 "+whereStatment;
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
			Utils.logger.severe(e.getMessage()+" getRadiologyRecord() in CarmSQLDao.java.\nSQL="+sQuery);
			throw new DAOException(e+", getRadiologyRecord() in CarmSQLDao.java");
		}
		finally
		{
			try
	        {	
				if (c!= null) 
	    		{
	    			cp.free(c);
	    			c=null;
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" getRadiologyRecord() in CarmSQLDao.java ");
	    		}
	        }
			catch (Exception e)
			{
				Utils.logger.severe(e.getMessage());
			}
		}
		return de6Bean;
	}
}
