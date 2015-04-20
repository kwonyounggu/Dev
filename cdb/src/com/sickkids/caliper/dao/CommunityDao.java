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


public class CommunityDao
{
	protected static Logger log = Utils.logger;
	private ConnectionPool cp=null;

	public CommunityDao(ConnectionPool cp)
	{	
		log.info("CommunityDao() is called in CommunityDao.java"); 
		this.cp=cp;		
	}

	public List<CommunityBean> getCommunityList(String where_clause) throws DAOException
	{
		//log.info("call request for getCommunityList("+where_clause+") in CommunityDao.java");
		List<CommunityBean> list = new ArrayList<CommunityBean>();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from community "+where_clause);
			
			while (rs.next())
			{
				CommunityBean cb=new CommunityBean();
				
				cb.setCommunity_index(rs.getLong(1));
				cb.setCommunity_class(rs.getInt(2));
				cb.setQa_class(rs.getInt(3));
				cb.setPerson_id(rs.getString(4));
				cb.setPerson_name(rs.getString(5));
				cb.setTopic_subject(rs.getString(6));
				//cb.setContents(rs.getString(7));
				cb.setTopic_number(rs.getLong(8));
				cb.setIn_response_to(rs.getString(9));
				cb.setWritten_date(rs.getTimestamp(10));
				cb.setAccess_number(rs.getInt(11));
				cb.setDelete_password(rs.getString(12));
				cb.setMypage(rs.getInt(13));
				cb.setRemarks(rs.getString(14));
				
				list.add(cb);
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
	public List<CommunityBean> getCommunityList2(String where_clause) throws DAOException
	{
		//log.info("call request for getCommunityList2("+where_clause+") in CommunityDao.java");
		List<CommunityBean> list = new ArrayList<CommunityBean>();
		Connection c = null;
		//select community_index, qa_class, person_id, person_name, topic_subject, topic_number, written_date, access_number, delete_password, count(*)  from community where community_class=2 group by topic_number order by written_date desc limit 50 offset 0;
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select community_index, qa_class, person_id, person_name, topic_subject, topic_number, written_date, access_number, delete_password, count(*)  from community "+where_clause);
			
			while (rs.next())
			{
				CommunityBean cb=new CommunityBean();
				
				cb.setCommunity_index(rs.getLong(1));
				cb.setQa_class(rs.getInt(2));
				cb.setPerson_id(rs.getString(3));
				cb.setPerson_name(rs.getString(4));
				cb.setTopic_subject(rs.getString(5));
				cb.setTopic_number(rs.getLong(6));
				cb.setWritten_date(rs.getTimestamp(7));
				cb.setAccess_number(rs.getInt(8));
				cb.setDelete_password(rs.getString(9));
				cb.setReplies(rs.getInt(10)-1);

				list.add(cb);
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
	public List<CommunityBean> getCommunityList3(String where_clause) throws DAOException
	{
		//log.info("call request for getCommunityList3("+where_clause+") in CommunityDao.java");
		List<CommunityBean> list = new ArrayList<CommunityBean>();
		Connection c = null;
		//select community_index, qa_class, person_id, person_name, topic_subject, written_date, in_response_to, delete_password from community where community_class=2 and topic_number=15 order by written_date asc;
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select community_index, qa_class, person_id, person_name, topic_subject, topic_number, written_date, in_response_to, delete_password, contents from community "+where_clause);
			
			while (rs.next())
			{
				CommunityBean cb=new CommunityBean();
				
				cb.setCommunity_index(rs.getLong(1));
				cb.setQa_class(rs.getInt(2));
				cb.setPerson_id(rs.getString(3));
				cb.setPerson_name(rs.getString(4));
				cb.setTopic_subject(rs.getString(5));
				cb.setTopic_number(rs.getLong(6));
				cb.setWritten_date(rs.getTimestamp(7));
				cb.setIn_response_to(rs.getString(8));
				cb.setDelete_password(rs.getString(9));
				cb.setContents(rs.getString(10));

				list.add(cb);
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
	public CommunityBean getARecord(String where_clause) throws DAOException
	{
		//log.info("call request for getARecord("+where_clause+")");
		CommunityBean cb=new CommunityBean();
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from community "+where_clause);
						
			if (rs.next())
			{
				cb.setCommunity_index(rs.getLong(1));
				cb.setCommunity_class(rs.getInt(2));
				cb.setQa_class(rs.getInt(3));
				cb.setPerson_id(rs.getString(4));
				cb.setPerson_name(rs.getString(5));
				cb.setTopic_subject(rs.getString(6));
				cb.setContents(rs.getString(7));
				cb.setTopic_number(rs.getLong(8));
				cb.setIn_response_to(rs.getString(9));
				cb.setWritten_date(rs.getTimestamp(10));
				cb.setAccess_number(rs.getInt(11));
				cb.setDelete_password(rs.getString(12));
				cb.setMypage(rs.getInt(13));
				cb.setRemarks(rs.getString(14));
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
		return cb;

	}

	public int getCount(String sqlStatement) throws DAOException
	{
		//log.info("call request for getCount("+sqlStatement+")");
		int count=0;
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sqlStatement);
						
			if (rs.next()) count=rs.getInt(1);
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
		return count;

	}

	public CommunityBean create(CommunityBean cb) throws DAOException
	{		
		//log.info("Subject="+cb.getTopic_subject()+", Contents="+cb.getContents());
		Connection c = null;
		PreparedStatement ps = null;
		
		try
		{
			c = cp.getConnection();
			String sQuery="insert into community values(default,?,?,?,?,?,?,?,?,?,?,?,?,?,null)";
			ps = c.prepareStatement(sQuery);
			
			ps.setInt(1, cb.getCommunity_class());
			ps.setInt(2, cb.getQa_class());
			ps.setString(3, cb.getPerson_id());
			ps.setString(4, cb.getPerson_name());
			ps.setString(5, cb.getTopic_subject());
			ps.setString(6, cb.getContents());
			ps.setLong(7, cb.getTopic_number());
			ps.setString(8, cb.getIn_response_to());
			ps.setTimestamp(9, cb.getWritten_date());
			ps.setInt(10, cb.getAccess_number());
			ps.setString(11, cb.getDelete_password());
			ps.setInt(12, cb.getMypage());
			ps.setString(13, cb.getRemarks());
			
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			cb.setCommunity_index(rs.getLong(1));
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
		return cb;
	}
	//Here the community index is equal to the topic number for the 1st subject being created
	public boolean updateAccessNumber(long community_index) throws DAOException
	{
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			
			String sQuery="update community set access_number=access_number+1 where community_index=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);		
			
			ps.setLong(1, community_index);
			
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
	//Here the community index is equal to the topic number for the 1st subject being created
	public boolean updateTopicNumber(CommunityBean newVersion) throws DAOException
	{
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			
			String sQuery="update community set topic_number=? where community_index=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setLong(1, newVersion.getCommunity_index());
			
			ps.setLong(2, newVersion.getCommunity_index());
			
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
	public boolean update(CommunityBean newVersion) throws DAOException
	{
		Connection c = null;
		
		try
		{
			c = cp.getConnection();
			
			String sQuery="update community set community_class=?, qa_class=?, person_id=?, person_name=?, topic_subject=?, "+
						  "contents=?, topic_number=?, in_response_to=?, written_date=?, access_number=?, delete_password=?, mypage=?, remarks=? where community_index=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setInt(1, newVersion.getCommunity_class());
			ps.setInt(2, newVersion.getQa_class());
			ps.setString(3, newVersion.getPerson_id());
			ps.setString(4, newVersion.getPerson_name());
			ps.setString(5, newVersion.getTopic_subject());
			ps.setString(6, newVersion.getContents());
			ps.setLong(7, newVersion.getTopic_number());
			ps.setString(8, newVersion.getIn_response_to());
			ps.setTimestamp(9, newVersion.getWritten_date());
			ps.setInt(10, newVersion.getAccess_number());
			ps.setString(11, newVersion.getDelete_password());
			ps.setInt(12, newVersion.getMypage());
			ps.setString(13, newVersion.getRemarks());
			
			ps.setLong(14, newVersion.getCommunity_index());
			
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

	public boolean delete(CommunityBean cb) throws DAOException
	{	
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement stmt = c.createStatement();
			int count = stmt.executeUpdate("DELETE from community WHERE community_index="+ cb.getCommunity_index());
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
