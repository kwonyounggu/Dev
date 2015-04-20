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

public class HitCountDao
{
	private ConnectionPool cp=null;
	public HitCountDao()
	{
	}
	public HitCountDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public List<HitCountBean> getCurrentHitCounters() throws DAOException
	{

		List<HitCountBean> list = new ArrayList<HitCountBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM hit_count");
			while (rs.next())
			{
				HitCountBean hb=new HitCountBean();
				hb.setIdx(rs.getInt(1));
				hb.setHitCounter(rs.getLong(2));
				hb.setHitTimestamp(rs.getTimestamp(3));
				
				list.add(hb);
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
	public HitCountBean getHitCounter(int idx) throws DAOException
	{

		HitCountBean hb = new HitCountBean();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM hit_count where idx='"+idx+"'");
			if (rs.next())
			{	hb.setIdx(rs.getInt(1));
				hb.setHitCounter(rs.getLong(2));
				hb.setHitTimestamp(rs.getTimestamp(3));
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
		return hb;
	}

	public HitCountBean create(HitCountBean hb) throws DAOException
	{

		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = cp.getConnection();
			String sQuery="insert into hit_count values(default,?,?,null)";
			ps = c.prepareStatement(sQuery);
			ps.setLong(1, hb.getHitCounter());
			ps.setTimestamp(2, hb.getHitTimestamp());

			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			hb.setHitCounter(rs.getLong(1));	
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
		return hb;
	}

	@SuppressWarnings("unchecked")
	public boolean update(HitCountBean newVersion, HitCountBean oldVersion, List changes) throws DAOException
	{

		Connection c = null;

		try
		{
			c = cp.getConnection();
			String sQuery="update hit_count set hitCounter=?, hitTimestamp=? where idx=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			ps.setLong(1, newVersion.getHitCounter());
			ps.setTimestamp(2, newVersion.getHitTimestamp());
			ps.setInt(3, newVersion.getIdx());
		
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

	public boolean delete(HitCountBean hb) throws DAOException
	{

		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement stmt = c.createStatement();
			int count = stmt.executeUpdate("DELETE FROM hit_count WHERE idx='"+ hb.getIdx() + "'");
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
