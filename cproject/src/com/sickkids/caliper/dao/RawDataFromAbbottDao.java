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


public class RawDataFromAbbottDao
{
	private ConnectionPool cp=null;
	private Connection c=null;
	public RawDataFromAbbottDao()
	{
		cp=(ConnectionPool)FlexContext.getServletContext().getAttribute("connectionPool");
	}
	public RawDataFromAbbottDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public RawDataFromAbbottDao(Connection c)
	{
		this.c=c;
	}
	public List getPatientTestResultMxList() throws DAOException
	{
		Utils.logger.info("getPatientTestResultMxList() of RawDataFromAbbottDao.java is called...");

		List<PatientTestResultMxBean> list = new ArrayList<PatientTestResultMxBean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			//ResultSet rs = s.executeQuery("select patient_id, sex, age, albumin_g, albumin_p, alkaline_phosphatase, alt, alt_act, amylase, ast, ast_act,"+ 
			//							  "bilirubin_direct, bilirubin_total, calcium, cholesterol, creatinine_jaffee, creatinine_enzymatic, ggt, uhdl_ultra_hdl, iron, iron_plasma,"+ 
			//							  "ldh_ld, lipase_lip, magnesium, phosphorus, total_protein, triglycerides, uric_acid, urea, aso, co2, rf_rheumatoid_factor, che, apo_a1,"+ 
			//							  "apo_b, c3, c4, crp, haptoglobin, lga, lgg, lgm, prealbumin, transferrin_trf from patient p, diagnostic_test_result d where p.patient_id=d.diagnostic_test_result_id;");
			ResultSet rs = s.executeQuery("select e.patient_id, e.sex, e.age, albumin_g, albumin_p, alkaline_phosphatase, alt, alt_act, amylase, ast, ast_act,"+
										  "bilirubin_direct, bilirubin_total, calcium, cholesterol, creatinine_jaffee, creatinine_enzymatic, ggt, uhdl_ultra_hdl, iron, iron_plasma,"+
										  "ldh_ld, lipase_lip, magnesium, phosphorus, total_protein, triglycerides, uric_acid, urea, aso, co2, rf_rheumatoid_factor, che, apo_a1,"+
										  "apo_b, c3, c4, crp, haptoglobin, lga, lgg, lgm, prealbumin, transferrin_trf from ethnic_gender_age e, assay_data a where e.patient_id=a.participant_id;");
			while (rs.next())
			{
				PatientTestResultMxBean pb=new PatientTestResultMxBean();
		
				pb.setPatient_id(rs.getInt(1));
				pb.setSex((rs.getInt(2)==1)? "m":"f");
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
	public RawDataFromAbbottBean create(RawDataFromAbbottBean db) throws DAOException
	{		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = (cp==null)? this.c: cp.getConnection();

			String sQuery="insert into raw_data_from_abbott values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = c.prepareStatement(sQuery);
			
			ps.setString(1, db.getCp());
			ps.setLong(2, db.getSid());
			ps.setString(3, db.getPid());
			ps.setString(4, db.getName());
			ps.setString(5, db.getGender());
			ps.setString(6, db.getDob());
			ps.setString(7, db.getAssay());
			ps.setInt(8, db.getAssay_number());
			ps.setString(9, db.getResult());
			ps.setString(10, db.getInterpretation());
			ps.setString(11, db.getInstrument_response());
			ps.setString(12, db.getNormal_range());
			ps.setString(13, db.getDilution());
			ps.setString(14, db.getFlags());
			ps.setString(15, db.getCodes());
			ps.setString(16, db.getModule_sn());
			ps.setString(17, db.getCompleted_time());
			ps.setString(18, db.getCal_time());
			ps.setString(19, db.getOperator_id());
			ps.setString(20, db.getReagent_lot());
			ps.setString(21, db.getReagent_sn());
			ps.setString(22, db.getComment());
			ps.setString(23, db.getDoctor());
			ps.setString(24, db.getDraw_time());
			ps.setString(25, db.getLocation());
			ps.setString(26, db.getRemarks());
		
						
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			db.setSid(rs.getLong(2));
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			//throw new DAOException(e);
		}
		finally
		{
			try
	        {	//if (c!= null) cp.free(c);
	        	if (c!=null && cp!=null) cp.free(c);
	        }
			catch (Exception e) 
			{	
				Utils.logger.severe(e.getMessage());
			}
		}
		return db;
	}
}
