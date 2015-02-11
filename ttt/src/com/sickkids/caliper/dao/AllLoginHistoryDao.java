package com.sickkids.caliper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.exceptions.DAOException;

public class AllLoginHistoryDao
{
	protected static Logger log = Utils.logger;
	private ConnectionPool cp=null;

	public AllLoginHistoryDao(ConnectionPool cp)
	{	
		log.info("AllLoginHistoryDao() is called in AllLoginHistoryDao.java"); 
		this.cp=cp;		
	}

	public List<AllLoginHistoryBean> getLoginHistoryList() throws DAOException
	{
		List<AllLoginHistoryBean> list = new ArrayList<AllLoginHistoryBean>();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM all_login_hist");
			
			while (rs.next())
			{
				AllLoginHistoryBean lb=new AllLoginHistoryBean();
				
				lb.setLogin_hist_index(rs.getLong(1));
				lb.setApp_id(rs.getInt(2));
				lb.setUser_id(rs.getString(3));
				lb.setLogin_time(rs.getTimestamp(4));
				lb.setLogin_ip(rs.getString(5));
				lb.setRemarks(rs.getString(6));
				lb.setLogout_time(rs.getTimestamp(7));
				
				list.add(lb);
			}
		}
		catch (SQLException e)
		{
			log.severe(e.getMessage());
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
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" create(AllLoginHistoryBean lb) in AllLoginHistoryDao.java ");
	    		}
	        }
			catch (Exception e) 
			{	
				log.severe(e.getMessage());
			}
		}
		return list;

	}
	public AllLoginHistoryBean getARecord(long login_hist_index) throws DAOException
	{
		//log.info("call request for getARecord("+login_index+")");
		AllLoginHistoryBean lb=new AllLoginHistoryBean();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM all_login_hist where login_hist_index="+login_hist_index);
						
			if (rs.next())
			{
				lb.setLogin_hist_index(rs.getLong(1));
				lb.setApp_id(rs.getInt(2));
				lb.setUser_id(rs.getString(3));
				lb.setLogin_time(rs.getTimestamp(4));
				lb.setLogin_ip(rs.getString(5));
				lb.setRemarks(rs.getString(6));
				lb.setLogout_time(rs.getTimestamp(7));
			}
		}
		catch (SQLException e)
		{
			log.severe(e.getMessage());
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
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" create(AllLoginHistoryBean lb) in AllLoginHistoryDao.java ");
	    		}
	        }
			catch (Exception e) 
			{	
				log.severe(e.getMessage());
			}
		}
		return lb;

	}

	public AllLoginHistoryBean create(AllLoginHistoryBean lb) throws DAOException
	{		
		Utils.logger.info("call request for create(AllLoginHistoryBean lb) in AllLoginHistoryDao.java");
		Connection c = null;
		PreparedStatement ps = null;
		
		try
		{
			c = cp.getConnection();
			String sQuery="insert into all_login_hist values(default,?,?,?,?,?,?)";
			ps = c.prepareStatement(sQuery);
			
			ps.setInt(1, lb.getApp_id());
			ps.setString(2,lb.getUser_id());
			ps.setTimestamp(3, lb.getLogin_time());
			ps.setString(4,lb.getLogin_ip());
			ps.setString(5, lb.getRemarks());
			ps.setTimestamp(6, lb.getLogout_time());
									
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			//ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			//for PostGreSQL, use this SELECT CURRVAL(pg_get_serial_sequence('my_tbl_name','id_col_name'))
			//ResultSet rs = s.executeQuery("SELECT CURRVAL(pg_get_serial_sequence('all_login_hist','login_hist_index'));");;//not working
			ResultSet rs = s.executeQuery("SELECT CURRVAL('all_login_hist_index_seq');");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			lb.setLogin_hist_index(rs.getLong(1));
			if(Utils.debug) System.err.println("INFO: last inserted id for the table of all_login_hist="+lb.getLogin_hist_index());
		}
		catch (SQLException e)
		{
			log.severe(e.getMessage()+" in create(AllLoginHistoryBean lb) in AllLoginHistoryDao.java");
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
	    			System.err.println("INFO: a connection freed, total connections="+cp.totalConnections()+" create(AllLoginHistoryBean lb) in AllLoginHistoryDao.java ");
	    		}
	        }
			catch (Exception e) 
			{	
				log.severe(e.getMessage());
			}
			Utils.logger.info("call ending for create(AllLoginHistoryBean lb) in AllLoginHistoryDao.java");
		}
		return lb;
	}
}
