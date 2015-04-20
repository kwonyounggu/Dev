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


public class OnlineRegistrationDao
{
	private ConnectionPool cp=null;
	private Connection c=null;
	public OnlineRegistrationDao()
	{
		cp=(ConnectionPool)FlexContext.getServletContext().getAttribute("connectionPool");
	}
	public OnlineRegistrationDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public OnlineRegistrationDao(Connection c)
	{
		this.c=c;
	}
	public List getRegistrationList() throws DAOException
	{
		Utils.logger.info("getRegistrationList() of OnlineRegistrationDao.java is called...");

		List<OnlineRegistrationBean> list = new ArrayList<OnlineRegistrationBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			
			ResultSet rs = s.executeQuery("select registration_id, name, sex, email, phone, registration_date, school from online_registration;");
			while (rs.next())
			{
				OnlineRegistrationBean rb=new OnlineRegistrationBean();
		
				rb.setRegistration_id(rs.getLong(1));
				rb.setName(rs.getString(2));
				rb.setSex(rs.getInt(3));
				rb.setEmail(rs.getString(4));
				rb.setPhone(rs.getString(5));
				rb.setRegistration_date(rs.getTimestamp(6));
				rb.setSchool(rs.getString(7));
							
				list.add(rb);
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
		Utils.logger.info("getRegistrationList() of OnlineRegistrationDao.java is ending with size="+list.size());
		return list;

	}
	public OnlineRegistrationBean create(OnlineRegistrationBean rb) throws DAOException
	{		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = (cp==null)? this.c: cp.getConnection();
			
			String sQuery="insert into online_registration values(default,?,?,?,?,?,?,?,null)";
			ps = c.prepareStatement(sQuery);
			
			//ps.setLong(1, rb.getRegistration_id());//registration_id
			ps.setString(1, rb.getName());//name
			ps.setInt(2, rb.getSex());//sex
			ps.setString(3, rb.getEmail());//eamil
			ps.setString(4, rb.getPhone());//phone
			ps.setString(5, rb.getSchool());//school
			ps.setTimestamp(6, rb.getRegistration_date());//registration date
			ps.setString(7, rb.getRemarks());//remarks
						
						
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			rb.setRegistration_id(rs.getLong(1));	
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
		return rb;
	}

}
