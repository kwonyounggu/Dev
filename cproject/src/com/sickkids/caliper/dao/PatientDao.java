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


public class PatientDao
{
	private ConnectionPool cp=null;
	private Connection c=null;
	public PatientDao()
	{
		cp=(ConnectionPool)FlexContext.getServletContext().getAttribute("connectionPool");
	}
	public PatientDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public PatientDao(Connection c)
	{
		this.c=c;
	}
	public List getPatientTestResultMxList() throws DAOException
	{
		Utils.logger.info("getPatientTestResultMxList() of PatientDao.java is called...");

		List<PatientTestResultMxBean> list = new ArrayList<PatientTestResultMxBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select patient_id, sex, age, albumin_g, albumin_p, alkaline_phosphatase, alt, alt_act, amylase, ast, ast_act,"+ 
										  "bilirubin_direct, bilirubin_total, calcium, cholesterol, creatinine_jaffee, creatinine_enzymatic, ggt, uhdl_ultra_hdl, iron, iron_plasma,"+ 
										  "ldh_ld, lipase_lip, magnesium, phosphorus, total_protein, triglycerides, uric_acid, urea, aso, co2, rf_rheumatoid_factor, che, apo_a1,"+ 
										  "apo_b, c3, c4, crp, haptoglobin, lga, lgg, lgm, prealbumin, transferrin_trf from patient p, diagnostic_test_result d where p.patient_id=d.diagnostic_test_result_id;");
			while (rs.next())
			{
				PatientTestResultMxBean pb=new PatientTestResultMxBean();
		
				pb.setPatient_id(rs.getInt(1));
				pb.setSex(rs.getString(2));
				pb.setAge(rs.getDouble(3));
				pb.setAlbumin_g(rs.getDouble(4));
				pb.setAlbumin_p(rs.getDouble(5));
				pb.setAlkaline_phosphatase(rs.getDouble(6));
				pb.setAlt(rs.getDouble(7));
				pb.setAlt_act(rs.getDouble(8));
				pb.setAmylase(rs.getDouble(9));
				pb.setAst(rs.getDouble(10));
				pb.setAst_act(rs.getDouble(11));
				pb.setBilirubin_direct(rs.getDouble(12));
				pb.setBilirubin_total(rs.getDouble(13));
				pb.setCalcium(rs.getDouble(14));
				pb.setCholesterol(rs.getDouble(15));
				pb.setCreatinine_jaffee(rs.getDouble(16));
				pb.setCreatinine_enzymatic(rs.getDouble(17));
				pb.setGgt(rs.getDouble(18));
				pb.setUhdl_ultra_hdl(rs.getDouble(19));
				pb.setIron(rs.getDouble(20));
				pb.setIron_plasma(rs.getDouble(21));
				pb.setLdh_ld(rs.getDouble(22));
				pb.setLipase_lip(rs.getDouble(23));
				pb.setMagnesium(rs.getDouble(24));
				pb.setPhosphorus(rs.getDouble(25));
				pb.setTotal_protein(rs.getDouble(26));
				pb.setTriglycerides(rs.getDouble(27));
				pb.setUric_acid(rs.getDouble(28));
				pb.setUrea(rs.getDouble(29));
				pb.setAso(rs.getDouble(30));
				pb.setCo2(rs.getDouble(31));
				pb.setRf_rheumatoid_factor(rs.getDouble(32));
				pb.setChe(rs.getDouble(33));
				pb.setApo_a1(rs.getDouble(34));
				pb.setApo_b(rs.getDouble(35));
				pb.setC3(rs.getDouble(36));
				pb.setC4(rs.getDouble(37));
				pb.setCrp(rs.getDouble(38));
				pb.setHaptoglobin(rs.getDouble(39));
				pb.setLga(rs.getDouble(40));
				pb.setLgg(rs.getDouble(41));
				pb.setLgm(rs.getDouble(42));
				pb.setPrealbumin(rs.getDouble(43));
				pb.setTransferrin_trf(rs.getDouble(44));
				
				list.add(pb);
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
		Utils.logger.info("getPatientList() of Bio414Lab1ExcelDao.java is ending with "+list.size());
		return list;

	}
	public PatientBean create(PatientBean pb) throws DAOException
	{		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = (cp==null)? this.c: cp.getConnection();
			
			String sQuery="insert into patient values(?,?,?,null,null,null)";
			ps = c.prepareStatement(sQuery);
			
			ps.setInt(1, pb.getDiagnostic_test_result_id());
			ps.setString(2, pb.getSex());
			ps.setDouble(3, pb.getAge());
			
						
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			pb.setPatient_id(rs.getInt(1));	
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
		return pb;
	}
	/*
	public boolean update(RoomHistoryBean newVersion) throws DAOException
	{
		Connection c = null;

		try
		{
			c = cp.getConnection();
			
			String sQuery="update room_history set room_id=?, user_id=?, start_time=?, "+
			  "end_time=?, remote_ip=?, browser_info=?, remarks=? where room_history_index=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setLong(1, newVersion.getRoom_id());
			ps.setLong(2, newVersion.getUser_id());
			ps.setTimestamp(3, newVersion.getStart_time());
			ps.setTimestamp(4, newVersion.getEnd_time());
			ps.setString(5, newVersion.getRemote_ip());
			ps.setString(6, newVersion.getBrowser_info());
			ps.setString(7, newVersion.getRemarks());
						
			ps.setLong(8, newVersion.getRoom_history_index());
			
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
	public boolean updateEndTime(RoomHistoryBean newVersion) throws DAOException
	{
		Connection c = null;

		try
		{
			c = cp.getConnection();
			
			String sQuery="update room_history set end_time=?, remarks=? where room_id=? and user_id=?";
			
			PreparedStatement ps = c.prepareStatement(sQuery);
			
			ps.setTimestamp(1, newVersion.getEnd_time());
			ps.setString(2, newVersion.getRemarks());
						
			ps.setLong(3, newVersion.getRoom_id());
			ps.setLong(4, newVersion.getUser_id());
			
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
	
	public boolean delete(RoomHistoryBean rb) throws DAOException
	{	
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement stmt = c.createStatement();
			int count = stmt.executeUpdate("DELETE FROM room_history WHERE room_history_index="+ rb.getRoom_history_index());
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
	*/
}
