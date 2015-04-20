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


import flex.messaging.FlexContext;


public class EthnicGenderAgeDao
{
	private ConnectionPool cp=null;
	private Connection c=null;
	public EthnicGenderAgeDao()
	{
		cp=(ConnectionPool)FlexContext.getServletContext().getAttribute("connectionPool");
	}
	public EthnicGenderAgeDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public EthnicGenderAgeDao(Connection c)
	{
		this.c=c;
	}
	public List<EthnicGenderAgeBean> getEthnicGenderAgeList() throws DAOException
	{
		Utils.logger.info("getEthnicGenderAgeList() of EthnicGenderAgeDao.java is called...");

		List<EthnicGenderAgeBean> list = new ArrayList<EthnicGenderAgeBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from ethnic_gender_age");
			while (rs.next())
			{
				EthnicGenderAgeBean eb=new EthnicGenderAgeBean();
				
				eb.setPatient_id(rs.getInt(1));
				eb.setSex(rs.getInt(2));
				eb.setAge(rs.getDouble(3));
				eb.setEthnic_group(rs.getInt(4));
				
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
        		if (c!= null && cp!=null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		Utils.logger.info("getEthnicGenderAgeList() of EthnicGenderAgeDao.java is ending with "+list.size());
		return list;

	}
	public EthnicGenderAgeBean create(EthnicGenderAgeBean eb) throws DAOException
	{		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = (cp==null)? this.c: cp.getConnection();
			
			String sQuery="insert into ethnic_gender_age values(?,?,?,?)";
			ps = c.prepareStatement(sQuery);
			
			ps.setInt(1, eb.getPatient_id());
			ps.setInt(2, eb.getSex());
			ps.setDouble(3, eb.getAge());
			ps.setInt(4, eb.getEthnic_group());
			
						
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			eb.setPatient_id(rs.getInt(1));	
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
		}
		finally
		{
			try
	        {	if (c!=null && cp!=null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		return eb;
	}

}
