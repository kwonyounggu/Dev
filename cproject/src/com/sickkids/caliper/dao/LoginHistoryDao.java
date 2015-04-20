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

public class LoginHistoryDao
{
	protected static Logger log = Utils.logger;
	private ConnectionPool cp=null;

	public LoginHistoryDao(ConnectionPool cp)
	{	
		log.info("LoginHistoryDao() is called in PersonsDao.java"); 
		this.cp=cp;		
	}

	public List<LoginHistoryBean> getLoginHistoryList() throws DAOException
	{
		//log.info("call request for getLoginHistoryList() in LoginHistoryDao.java");
		List<LoginHistoryBean> list = new ArrayList<LoginHistoryBean>();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM login_history");
			
			while (rs.next())
			{
				LoginHistoryBean lb=new LoginHistoryBean();
				
				lb.setLogin_index(rs.getLong(1));
				lb.setPerson_id(rs.getString(2));
				lb.setLogin_time(rs.getTimestamp(3));
				lb.setRemarks(rs.getString(4));
				
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
        		if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				log.severe(e.getMessage());
			}
		}
		return list;

	}
	public LoginHistoryBean getARecord(long login_index) throws DAOException
	{
		//log.info("call request for getARecord("+login_index+")");
		LoginHistoryBean lb=new LoginHistoryBean();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM login_history where login_index="+login_index);
						
			if (rs.next())
			{
				lb.setLogin_index(rs.getLong(1));
				lb.setPerson_id(rs.getString(2));
				lb.setLogin_time(rs.getTimestamp(3));
				lb.setRemarks(rs.getString(4));
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
        		if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				log.severe(e.getMessage());
			}
		}
		return lb;

	}

	public LoginHistoryBean create(LoginHistoryBean lb) throws DAOException
	{		
		Connection c = null;
		PreparedStatement ps = null;
		
		try
		{
			c = cp.getConnection();
			String sQuery="insert into login_history values(default,?,?,?,null)";
			ps = c.prepareStatement(sQuery);
			
			ps.setString(1,lb.getPerson_id());
			ps.setTimestamp(2,lb.getLogin_time());
			ps.setString(3,lb.getRemarks());
									
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			lb.setLogin_index(rs.getLong(1));
		}
		catch (SQLException e)
		{
			log.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				log.severe(e.getMessage());
			}
		}
		return lb;
	}
	
	public boolean update(LoginHistoryBean newVersion) throws DAOException
	{
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			
			String sQuery="update persons set person_id=?, login_time=?, remarks=? where login_index=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setString(1,newVersion.getPerson_id());
			ps.setTimestamp(2,newVersion.getLogin_time());
			ps.setString(3,newVersion.getRemarks());
						
			ps.setLong(4, newVersion.getLogin_index());
			
			return (ps.executeUpdate() == 1);
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				System.err.println(e.getMessage());
			}
		}
	}

	public boolean delete(LoginHistoryBean lb) throws DAOException
	{	
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement stmt = c.createStatement();
			int count = stmt.executeUpdate("DELETE FROM login_history WHERE login_index="+ lb.getLogin_index());
			return (count == 1);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				System.err.println(e.getMessage());
			}
		}
	}
}
