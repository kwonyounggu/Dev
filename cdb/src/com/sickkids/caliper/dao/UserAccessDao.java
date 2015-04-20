package com.sickkids.caliper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.exceptions.DAOException;


public class UserAccessDao
{
	private ConnectionPool cp=null;
	public UserAccessDao()
	{
	}
	public UserAccessDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public List<UserAccessBean> getUserAccesses() throws DAOException
	{

		List<UserAccessBean> list = new ArrayList<UserAccessBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM user_access");
			while (rs.next())
			{
				UserAccessBean ub=new UserAccessBean();
				ub.setIdx(rs.getInt(1));
				ub.setOperation(rs.getString(2));
				ub.setRemoteIP(rs.getString(3));
				ub.setRemoteHost(rs.getString(4));
				ub.setLocale(rs.getString(5));
				ub.setEncoding(rs.getString(6));
				ub.setSessionID(rs.getString(7));
				ub.setCid(rs.getString(8));
				ub.setOrder_id(rs.getLong(9));
				ub.setHitCounter(rs.getLong(10));
				ub.setHitTimestamp(rs.getTimestamp(11));

				list.add(ub);
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
		return list;

	}
	//public List getCurrentOrdersByProductName(String product_name) throws DAOException
	//{
	//	return null;
	//}
	public UserAccessBean getUserAccess(int idx) throws DAOException
	{

		UserAccessBean ub = new UserAccessBean();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM user_access WHERE idx="+ idx);
			if (rs.next())
			{
				ub.setIdx(rs.getInt(1));
				ub.setOperation(rs.getString(2));
				ub.setRemoteIP(rs.getString(3));
				ub.setRemoteHost(rs.getString(4));
				ub.setLocale(rs.getString(5));
				ub.setEncoding(rs.getString(6));
				ub.setSessionID(rs.getString(7));
				ub.setCid(rs.getString(8));
				ub.setOrder_id(rs.getLong(9));
				ub.setHitCounter(rs.getLong(10));
				ub.setHitTimestamp(rs.getTimestamp(11));

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
	        {	if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		return ub;
	}

	public UserAccessBean create(UserAccessBean ub) throws DAOException
	{

		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = cp.getConnection();
			String sQuery="insert into user_access values(default,?,?,?,?,?,?,?,?,?,?,null,null)";
			ps = c.prepareStatement(sQuery);
			
			ps.setString(1, ub.getOperation());
			ps.setString(2, ub.getRemoteIP());
			ps.setString(3, ub.getRemoteHost());
			ps.setString(4, ub.getLocale());
			ps.setString(5, ub.getEncoding());
			ps.setString(6, ub.getSessionID());
			ps.setString(7, ub.getCid());
			ps.setLong(8, ub.getOrder_id());
			ps.setLong(9, ub.getHitCounter());
			ps.setTimestamp(10, ub.getHitTimestamp());
			

			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			ub.setIdx(rs.getInt(1));	
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		return ub;
	}

	@SuppressWarnings("unchecked")
	public boolean update(UserAccessBean newVersion, UserAccessBean oldVersion, List changes) throws DAOException
	{

		Connection c = null;

		try
		{
			c = cp.getConnection();
			String sQuery="update user_access set operation=?, remoteIP=?, remoteHost=?, locale=?, encoding=?, sessionID=?, cid=?, order_id=?, hitCounter=?, hitTimestamp=? where idx=?";
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setString(1, newVersion.getOperation());
			ps.setString(2, newVersion.getRemoteIP());
			ps.setString(3, newVersion.getRemoteHost());
			ps.setString(4, newVersion.getLocale());
			ps.setString(5, newVersion.getEncoding());
			ps.setString(6, newVersion.getSessionID());
			ps.setString(7, newVersion.getCid());
			ps.setLong(8, newVersion.getOrder_id());
			ps.setLong(9, newVersion.getHitCounter());
			ps.setTimestamp(10, newVersion.getHitTimestamp());
			ps.setInt(11, newVersion.getIdx());
			
			return (ps.executeUpdate() == 1);
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}

	}

	public boolean delete(UserAccessBean ub) throws DAOException
	{

		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement stmt = c.createStatement();
			int count = stmt.executeUpdate("DELETE FROM user_access WHERE idx='"+ ub.getIdx() + "'");
			return (count == 1);
		}
		catch (Exception e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	if (c!= null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
	}
}
