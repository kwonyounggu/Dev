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

public class PersonsDao
{
	protected static Logger log = Utils.logger;
	private ConnectionPool cp=null;

	public PersonsDao(ConnectionPool cp)
	{	
		log.info("PersonsDao() is called in PersonsDao.java"); 
		this.cp=cp;		
	}

	public List<PersonsBean> getPersonsList() throws DAOException
	{
		//log.info("call request for getPersonsList() in PersonsDao.java");
		List<PersonsBean> list = new ArrayList<PersonsBean>();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM persons");
			
			while (rs.next())
			{
				PersonsBean pb=new PersonsBean();
				
				pb.setPerson_id(rs.getString(1));
				pb.setKorean_name(rs.getString(2));
				pb.setEng_name(rs.getString(3));
				pb.setEmail(rs.getString(4));
				pb.setLogin_password(rs.getString(5));
				pb.setDob(rs.getString(6));
				pb.setSex(rs.getString(7));
				pb.setAddress(rs.getString(8));
				pb.setCity(rs.getString(9));
				pb.setProvince(rs.getString(10));
				pb.setCountry(rs.getString(11));
				pb.setPostal_code(rs.getString(12));
				pb.setPhone1(rs.getString(13));
				pb.setPhone2(rs.getString(14));
				pb.setWant_einfo(rs.getBoolean(15));
				pb.setStill_member(rs.getBoolean(16));
				pb.setRemarks(rs.getString(17));
				pb.setRegistered_time(rs.getTimestamp(18));
				pb.setId_classification(rs.getString(19));
				pb.setSin_number(rs.getString(20));
				pb.setOrg_name(rs.getString(21));
							
				list.add(pb);
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
	public PersonsBean getARecord(String arg) throws DAOException
	{
		//log.info("call request for getARecord("+arg+")");
		PersonsBean pb=new PersonsBean();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM persons "+arg);			
			if (rs.next())
			{
				pb.setPerson_id(rs.getString(1));
				pb.setKorean_name(rs.getString(2));
				pb.setEng_name(rs.getString(3));
				pb.setEmail(rs.getString(4));
				pb.setLogin_password(rs.getString(5));
				pb.setDob(rs.getString(6));
				pb.setSex(rs.getString(7));
				pb.setAddress(rs.getString(8));
				pb.setCity(rs.getString(9));
				pb.setProvince(rs.getString(10));
				pb.setCountry(rs.getString(11));
				pb.setPostal_code(rs.getString(12));
				pb.setPhone1(rs.getString(13));
				pb.setPhone2(rs.getString(14));
				pb.setWant_einfo(rs.getBoolean(15));
				pb.setStill_member(rs.getBoolean(16));
				pb.setRemarks(rs.getString(17));
				pb.setRegistered_time(rs.getTimestamp(18));
				pb.setId_classification(rs.getString(19));
				pb.setSin_number(rs.getString(20));
				pb.setOrg_name(rs.getString(21));
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
		return pb;

	}

	public PersonsBean create(PersonsBean pb) throws DAOException
	{		
		//log.info("Korean name= "+pb.getKorean_name()+", Address= "+pb.getAddress());
		Connection c = null;
		PreparedStatement ps = null;
		
		try
		{
			c = cp.getConnection();
			String sQuery="insert into persons values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = c.prepareStatement(sQuery);
			
			ps.setString(1,pb.getPerson_id());
			ps.setString(2,pb.getKorean_name());
			ps.setString(3,pb.getEng_name());
			ps.setString(4,pb.getEmail().toLowerCase());
			ps.setString(5, pb.getLogin_password());
			ps.setString(6,pb.getDob());
			ps.setString(7,pb.getSex());
			ps.setString(8,pb.getAddress());
			ps.setString(9,pb.getCity());
			ps.setString(10,pb.getProvince());
			ps.setString(11,pb.getCountry());
			ps.setString(12,pb.getPostal_code());
			ps.setString(13,pb.getPhone1());
			ps.setString(14,pb.getPhone2());
			ps.setBoolean(15,pb.isWant_einfo());
			ps.setBoolean(16, pb.isStill_member());
			ps.setString(17, pb.getRemarks());
			ps.setTimestamp(18, pb.getRegistered_time());
			ps.setString(19, pb.getId_classification());
			ps.setString(20, pb.getSin_number());
			ps.setString(21, pb.getOrg_name());
			
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			pb.setPerson_id(rs.getString(1));	
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
		return pb;
	}
	
	public boolean update(PersonsBean newVersion) throws DAOException
	{
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			
			String sQuery="update persons set korean_name=?, eng_name=?, email=?, login_password=?, dob=?, sex=?, address=?, city=?, province=?, country=?,"+
			  			  "postal_code=?, phone1=?, phone2=?, want_einfo=?, still_member=?, remarks=?, registered_time=?, id_classification=?, sin_number=?, org_name=? where person_id=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setString(1,newVersion.getKorean_name());
			ps.setString(2,newVersion.getEng_name());
			ps.setString(3,newVersion.getEmail().toLowerCase());
			ps.setString(4, newVersion.getLogin_password());
			ps.setString(5,newVersion.getDob());
			ps.setString(6,newVersion.getSex());
			ps.setString(7,newVersion.getAddress());
			ps.setString(8,newVersion.getCity());
			ps.setString(9,newVersion.getProvince());
			ps.setString(10,newVersion.getCountry());
			ps.setString(11,newVersion.getPostal_code());
			ps.setString(12,newVersion.getPhone1());
			ps.setString(13,newVersion.getPhone2());
			ps.setBoolean(14,newVersion.isWant_einfo());
			ps.setBoolean(15, newVersion.isStill_member());
			ps.setString(16, newVersion.getRemarks());
			ps.setTimestamp(17, newVersion.getRegistered_time());
			ps.setString(18, newVersion.getId_classification());
			ps.setString(19, newVersion.getSin_number());
			ps.setString(20, newVersion.getOrg_name());
						
			ps.setString(21,newVersion.getPerson_id());
			
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
	public boolean updateMyInfo(PersonsBean newVersion) throws DAOException
	{
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			
			String sQuery="update persons set login_password=?, dob=?, sex=?, address=?, city=?, province=?, country=?,"+
			  			  "postal_code=?, phone1=?, phone2=?, want_einfo=?, still_member=?, remarks=?, registered_time=?, id_classification=?, sin_number=?, org_name=? where person_id=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setString(1, newVersion.getLogin_password());
			ps.setString(2,newVersion.getDob());
			ps.setString(3,newVersion.getSex());
			ps.setString(4,newVersion.getAddress());
			ps.setString(5,newVersion.getCity());
			ps.setString(6,newVersion.getProvince());
			ps.setString(7,newVersion.getCountry());
			ps.setString(8,newVersion.getPostal_code());
			ps.setString(9,newVersion.getPhone1());
			ps.setString(10,newVersion.getPhone2());
			ps.setBoolean(11,newVersion.isWant_einfo());
			ps.setBoolean(12, newVersion.isStill_member());
			ps.setString(13, newVersion.getRemarks());
			ps.setTimestamp(14, newVersion.getRegistered_time());
			ps.setString(15, newVersion.getId_classification());
			ps.setString(16, newVersion.getSin_number());
			ps.setString(17, newVersion.getOrg_name());
						
			ps.setString(18,newVersion.getPerson_id());
			
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
	public boolean delete(PersonsBean pb) throws DAOException
	{	
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement stmt = c.createStatement();
			int count = stmt.executeUpdate("DELETE FROM persons WHERE person_id="+ pb.getPerson_id());
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
