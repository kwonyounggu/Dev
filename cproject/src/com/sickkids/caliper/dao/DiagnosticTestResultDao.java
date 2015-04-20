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


public class DiagnosticTestResultDao
{
	private ConnectionPool cp=null;
	private Connection c=null;
	public DiagnosticTestResultDao()
	{
		cp=(ConnectionPool)FlexContext.getServletContext().getAttribute("connectionPool");
	}
	public DiagnosticTestResultDao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public DiagnosticTestResultDao(Connection c)
	{
		this.c=c;
	}
	public List getPatientTestResultMxList() throws DAOException
	{
		Utils.logger.info("getPatientTestResultMxList() of DiagnosticTestResultDao.java is called...");

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
	public DiagnosticTestResultBean create(DiagnosticTestResultBean db) throws DAOException
	{		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = (cp==null)? this.c: cp.getConnection();

			String sQuery="insert into diagnostic_test_result values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = c.prepareStatement(sQuery);
			
			ps.setInt(1, db.getDiagnostic_test_result_id());
			ps.setDouble(2, db.getAlbumin_g());
			ps.setDouble(3, db.getAlbumin_p());
			ps.setDouble(4, db.getAlkaline_phosphatase());
			ps.setDouble(5, db.getAlt());
			ps.setDouble(6, db.getAlt_act());
			ps.setDouble(7, db.getAmylase());
			ps.setDouble(8, db.getAst());
			ps.setDouble(9, db.getAst_act());
			ps.setDouble(10, db.getBilirubin_direct());
			ps.setDouble(11, db.getBilirubin_total());
			ps.setDouble(12, db.getCalcium());
			ps.setDouble(13, db.getCholesterol());
			ps.setDouble(14, db.getCreatinine_jaffee());
			ps.setDouble(15, db.getCreatinine_enzymatic());
			ps.setDouble(16, db.getGgt());
			ps.setDouble(17, db.getUhdl_ultra_hdl());
			ps.setDouble(18, db.getIron());
			ps.setDouble(19, db.getIron_plasma());
			ps.setDouble(20, db.getLdh_ld());
			ps.setDouble(21, db.getLipase_lip());
			ps.setDouble(22, db.getMagnesium());
			ps.setDouble(23, db.getPhosphorus());
			ps.setDouble(24, db.getTotal_protein());
			ps.setDouble(25, db.getTriglycerides());
			ps.setDouble(26, db.getUric_acid());
			ps.setDouble(27, db.getUrea());
			ps.setDouble(28, db.getAso());
			ps.setDouble(29, db.getCo2());
			ps.setDouble(30, db.getRf_rheumatoid_factor());
			ps.setDouble(31, db.getChe());
			ps.setDouble(32, db.getApo_a1());
			ps.setDouble(33, db.getApo_b());
			ps.setDouble(34, db.getC3());
			ps.setDouble(35, db.getC4());
			ps.setDouble(36, db.getCrp());
			ps.setDouble(37, db.getHaptoglobin());
			ps.setDouble(38, db.getLga());
			ps.setDouble(39, db.getLgg());
			ps.setDouble(40, db.getLgm());
			ps.setDouble(41, db.getPrealbumin());
			ps.setDouble(42, db.getTransferrin_trf());		
						
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			db.setDiagnostic_test_result_id(rs.getInt(1));
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
