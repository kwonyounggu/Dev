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

public class UsersDao
{
	protected static Logger log = Utils.logger;
	private ConnectionPool cp=null;

//	public UsersDao()
//	{	
//		log.info("UsersDao() is called");
//		this.cp=ConnectionPoolManager.connectionPool;
//		
//	}
	public UsersDao(ConnectionPool cp)
	{	
		log.info("UsersDao() is called in UsersDao.java"); 
		this.cp=cp;		
	}
	public List<UsersBean> getUserList(long room_id) throws DAOException
	{
		//log.info("call request for getUserList("+room_id+")");
		List<UsersBean> list = new ArrayList<UsersBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM users where is_active=1 and room_id="+room_id);
			while (rs.next())
			{
				UsersBean ub=new UsersBean();
				
				ub.setUser_id(rs.getLong(1));
				ub.setRoom_id(rs.getLong(2));
				ub.setFirst_name(rs.getString(3));
				ub.setLast_name(rs.getString(4));
				ub.setEmail(rs.getString(5));
				ub.setIs_owner(rs.getBoolean(6));
				ub.setOwner_password(rs.getString(7));
				ub.setCreation_date(rs.getTimestamp(8));
				ub.setIs_active(rs.getBoolean(9));
				ub.setIs_login(rs.getBoolean(10));
				//rb.setReserved(something);
							
				list.add(ub);
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
	public List<String> getEmailList(long room_id) throws DAOException
	{
		//log.info("call request for getEmailList("+room_id+")");
		List<String> list = new ArrayList<String>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT email FROM users where is_active=1 and room_id="+room_id); //include myself in the list

			while (rs.next())
			{									
				list.add(rs.getString(1));
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
	public List<String> getNameList(long room_id) throws DAOException
	{
		//log.info("call request for getEmailList("+room_id+")");
		List<String> list = new ArrayList<String>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT first_name FROM users where is_active=1 and room_id="+room_id); //include myself in the list
		
			while (rs.next())
			{									
				list.add(rs.getString(1));
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
	public String getAnEmail(long room_id, long user_id) throws DAOException
	{
		//log.info("call request for getAnEmail("+room_id+", "+user_id+")");
		Connection c = null;
		String emailAddr="unknown.person@etalk.com";
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT email FROM users where is_active=1 and room_id="+room_id+" and user_id="+user_id);
			if(rs.next())
			{									
				emailAddr=rs.getString(1);
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
		return emailAddr;

	}
	public UsersBean getARecord(String arg) throws DAOException
	{
		//log.info("call request for getARecord("+arg+")");
		UsersBean ub=new UsersBean();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM users "+arg);			
			if (rs.next())
			{
				ub.setUser_id(rs.getLong(1));
				ub.setRoom_id(rs.getLong(2));
				ub.setFirst_name(rs.getString(3));
				ub.setLast_name(rs.getString(4));
				ub.setEmail(rs.getString(5));
				ub.setIs_owner(rs.getBoolean(6));
				ub.setOwner_password(rs.getString(7));
				ub.setCreation_date(rs.getTimestamp(8));
				ub.setIs_active(rs.getBoolean(9));
				ub.setIs_login(rs.getBoolean(10));
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
		return ub;

	}

	public UsersBean create(UsersBean ub) throws DAOException
	{		
		//log.info("create() is called with room_id="+ub.getRoom_id());
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = cp.getConnection();
			String sQuery="insert into users values(default,?,?,?,?,?,?,?,?,?,null)";
			ps = c.prepareStatement(sQuery);
			
			ps.setLong(1, ub.getRoom_id());
			ps.setString(2, ub.getFirst_name());
			ps.setString(3, ub.getLast_name());//lastname
			ps.setString(4, ub.getEmail().toLowerCase());
			ps.setBoolean(5, ub.isIs_owner());//is_owner
			ps.setString(6, ub.getOwner_password());
			ps.setTimestamp(7, Utils.currentTimestamp());
			ps.setBoolean(8, ub.isIs_active());
			ps.setBoolean(9, ub.isIs_login());
			//ps.setSomething(..);
			
			
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			ub.setUser_id(rs.getLong(1));	
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
		return ub;
	}
	
	public boolean update(UsersBean newVersion) throws DAOException
	{
		Connection c = null;

		try
		{
			c = cp.getConnection();
			String sQuery="update users set room_id=?, first_name=?, last_name=?, email=?, is_owner=?, "+
					      "owner_password=?, creation_date=?, is_active=?, is_login=? where user_id=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setLong(1, newVersion.getRoom_id());
			ps.setString(2, newVersion.getFirst_name());
			ps.setString(3, newVersion.getLast_name());//lastname
			ps.setString(4, newVersion.getEmail().toLowerCase());
			ps.setBoolean(5, newVersion.isIs_owner());//is_owner
			ps.setString(6, newVersion.getOwner_password());
			ps.setTimestamp(7, newVersion.getCreation_date());
			ps.setBoolean(8, newVersion.isIs_active());
			ps.setBoolean(9, newVersion.isIs_login());
			
			ps.setLong(10, newVersion.getUser_id());
			
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
	public boolean updateLogin(UsersBean newVersion) throws DAOException
	{
		Connection c = null;

		try
		{
			c = cp.getConnection();
			String sQuery="update users set is_login=? where user_id=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setBoolean(1, newVersion.isIs_login());
			
			ps.setLong(2, newVersion.getUser_id());
			
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
	//This is to be called when Red5 Application is starting and ending for the clearance
	public boolean updateIsLogin() throws DAOException
	{
		Connection c = null;

		try
		{
			c = cp.getConnection();
			String sQuery="update users set is_login=0";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
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
	public boolean delete(UsersBean ub) throws DAOException
	{	
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement stmt = c.createStatement();
			int count = stmt.executeUpdate("DELETE FROM users WHERE user_id="+ ub.getUser_id());
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
	
	//This will be used for AddRoomMember and UpdateMemeber to check if anyone already roommed in.
	public boolean isAnyoneRoommedIn(UsersBean ub) throws DAOException
	{	//roomidx, username, useremail need to be unique
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sql="SELECT count(*) FROM users where is_active=1 and room_id="+ub.getRoom_id()+" and is_login=true";
			log.info("sql="+sql);
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{				
				int count=rs.getInt(1);
				//log.info("count="+count);
				return (count>=1);
			}		
			else throw new SQLException("ERROR: No record returned on select count(*)");

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
	}
	//in the same roomIdx, userName, userEmail needs to be unique
	//true if exists, otherwise false
	public boolean isUserIn(UsersBean ub) throws DAOException
	{	//roomidx, username, useremail need to be unique
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sql="SELECT count(*) FROM users where "+
		      "(is_active=1 and room_id="+ub.getRoom_id()+" and first_name='"+ub.getFirst_name()+"')"+
		      " or (is_active=1 and room_id="+ub.getRoom_id()+" and email='"+ub.getEmail().toLowerCase()+"')";
			log.info("sql="+sql);
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{				
				int count=rs.getInt(1);
				//log.info("count="+count);
				return (count>=1);
			}		
			else throw new SQLException("ERROR: No record returned on select count(*)");

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
	}
//	in the same roomIdx, userName, userEmail needs to be unique
	//true if exists, otherwise false
	public boolean isOwnerIn(UsersBean ub) throws DAOException
	{	//roomidx, username, useremail need to be unique
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sql="SELECT count(*) FROM users where "+
			"is_active=1 and room_id="+ub.getRoom_id()+" and first_name='"+ub.getFirst_name()+"' and is_owner="+ub.isIs_owner();
		      
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{				
				int count=rs.getInt(1);
				//log.info("count="+count);
				return (count>=1);
			}
			else throw new SQLException("ERROR: No record returned on select count(*)");
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
	}
	public boolean isOwnerPassword(UsersBean ub) throws DAOException
	{	
		Connection c = null;
		//log.info("room_id="+ub.getRoom_id()+"pwd="+ub.getOwner_password()+",name="+ub.getFirst_name()+", is_owner="+ub.isIs_owner());
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			String sql="SELECT count(*) FROM users where "+
			"is_active=1 and room_id="+ub.getRoom_id()+" and first_name='"+ub.getFirst_name()+"' and is_owner="+ub.isIs_owner()+" and owner_password='"+ub.getOwner_password()+"'";
		      
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{				
				int count=rs.getInt(1);
				//log.info("count="+count);
				return (count>=1);
			}
			else throw new SQLException("ERROR: No record returned on select count(*)");
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
	}
	public int getRoomMemberCount(long room_id)
	{
		int count=0;
		//log.info("call request for getRoomMemberCount("+room_id+")");
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT count(*) FROM users where is_active=1 and room_id="+room_id);
			if (rs.next())
			{				
				count=rs.getInt(1);
				return count;
			}
			else 
			{	
				//count is zero in default, but error since at least one member, room owner, must be there
				throw new SQLException("ERROR: No record returned on select count(*) for the number of room members");		
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
	}
	public int getLoggedInRoomMemberCount(long room_id)
	{
		int count=0;
		//log.info("call request for getLoggedInRoomMemberCount("+room_id+")");
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT count(*) FROM users where is_active=1 and is_login=true and room_id="+room_id);
			if (rs.next())
			{				
				count=rs.getInt(1);
				return count;
			}
			else 
			{	
				//supposed to be rs.next() is true even with a value=0
				throw new SQLException("ERROR: No record returned on select count(*) for the number of room members");		
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
	}
}
