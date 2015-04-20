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


public class Phase1CompleteDataSetJune2011Dao
{
	private ConnectionPool cp=null;
	private Connection c=null;
	public Phase1CompleteDataSetJune2011Dao()
	{
		cp=(ConnectionPool)FlexContext.getServletContext().getAttribute("connectionPool");
	}
	public Phase1CompleteDataSetJune2011Dao(ConnectionPool cp)
	{
		this.cp=cp;
	}
	public Phase1CompleteDataSetJune2011Dao(Connection c)
	{
		this.c=c;
	}
	public List<Phase1CompleteDataSetJune2011Bean> getPhase1CompleteDatSetJune2011List() throws DAOException
	{
		Utils.logger.info("getPhase1CompleteDatSetJune2011List() of Phase1CompleteDatSetJune2011.java is called...");

		List<Phase1CompleteDataSetJune2011Bean> list = new ArrayList<Phase1CompleteDataSetJune2011Bean>();
		Connection c = null;

		try
		{
			c = cp.getConnection();
			Statement s = c.createStatement();
			
			ResultSet rs = s.executeQuery("select participant_id, sex, age, ethnic_group, albumin_g, albumin_p, alkaline_phosphatase, alt, alt_act, amylase, ast, ast_act,"+
										  "bilirubin_direct, bilirubin_total, calcium, cholesterol, creatinine_jaffee, creatinine_enzymatic, ggt, uhdl_ultra_hdl, iron, iron_plasma,"+
										  "ldh_ld, lipase_lip, magnesium, phosphorus, total_protein, triglycerides, uric_acid, urea, aso, co2, rf_rheumatoid_factor, che, apo_a1,"+
										  "apo_b, c3, c4, crp, haptoglobin, iga, igg, igm, prealbumin, transferrin_trf, weight, height, bmi from phase1completedatasetjune2011;");
			while (rs.next())
			{
				Phase1CompleteDataSetJune2011Bean pb=new Phase1CompleteDataSetJune2011Bean();
		
				pb.setParticipant_id(rs.getLong(1));
				pb.setSex(rs.getInt(2));
				pb.setAge(rs.getDouble(3));
				pb.setEthnic_group(rs.getInt(4));
				pb.setAlbumin_g(rs.getDouble(5));
				pb.setAlbumin_p(rs.getDouble(6));
				pb.setAlkaline_phosphatase(rs.getDouble(7));
				pb.setAlt(rs.getDouble(8));
				pb.setAlt_act(rs.getDouble(9));
				pb.setAmylase(rs.getDouble(10));
				pb.setAst(rs.getDouble(11));
				pb.setAst_act(rs.getDouble(12));
				pb.setBilirubin_direct(rs.getDouble(13));
				pb.setBilirubin_total(rs.getDouble(14));
				pb.setCalcium(rs.getDouble(15));
				pb.setCholesterol(rs.getDouble(16));
				pb.setCreatinine_jaffee(rs.getDouble(17));
				pb.setCreatinine_enzymatic(rs.getDouble(18));
				pb.setGgt(rs.getDouble(19));
				pb.setUhdl_ultra_hdl(rs.getDouble(20));
				pb.setIron(rs.getDouble(21));
				pb.setIron_plasma(rs.getDouble(22));
				pb.setLdh_ld(rs.getDouble(23));
				pb.setLipase_lip(rs.getDouble(24));
				pb.setMagnesium(rs.getDouble(25));
				pb.setPhosphorus(rs.getDouble(26));
				pb.setTotal_protein(rs.getDouble(27));
				pb.setTriglycerides(rs.getDouble(28));
				pb.setUric_acid(rs.getDouble(29));
				pb.setUrea(rs.getDouble(30));
				pb.setAso(rs.getDouble(31));
				pb.setCo2(rs.getDouble(32));
				pb.setRf_rheumatoid_factor(rs.getDouble(33));
				pb.setChe(rs.getDouble(34));
				pb.setApo_a1(rs.getDouble(35));
				pb.setApo_b(rs.getDouble(36));
				pb.setC3(rs.getDouble(37));
				pb.setC4(rs.getDouble(38));
				pb.setCrp(rs.getDouble(39));
				pb.setHaptoglobin(rs.getDouble(40));
				pb.setIga(rs.getDouble(41));
				pb.setIgg(rs.getDouble(42));
				pb.setIgm(rs.getDouble(43));
				pb.setPrealbumin(rs.getDouble(44));
				pb.setTransferrin_trf(rs.getDouble(45));
				pb.setWeight(rs.getDouble(46));
				pb.setHeight(rs.getDouble(47));
				pb.setBmi(rs.getDouble(48));
				
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
	public Phase1CompleteDataSetJune2011Bean create(Phase1CompleteDataSetJune2011Bean db) throws DAOException
	{		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = (cp==null)? this.c: cp.getConnection();

			String sQuery="insert into phase1completedatasetjune2011 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = c.prepareStatement(sQuery);
			
			ps.setLong(1, db.getParticipant_id());//participant id
			ps.setInt(2, db.getSex());//sex
			ps.setDouble(3, db.getAge());//age
			ps.setDouble(4, db.getEthnic_group());//ethnic group, 3 more+3more=6 more fields
			
			ps.setDouble(5, db.getAlbumin_g());
			ps.setDouble(6, db.getAlbumin_p());
			ps.setDouble(7, db.getAlkaline_phosphatase());
			ps.setDouble(8, db.getAlt());
			ps.setDouble(9, db.getAlt_act());
			ps.setDouble(10, db.getAmylase());
			ps.setDouble(11, db.getAst());
			ps.setDouble(12, db.getAst_act());
			ps.setDouble(13, db.getBilirubin_direct());
			ps.setDouble(14, db.getBilirubin_total());
			ps.setDouble(15, db.getCalcium());
			ps.setDouble(16, db.getCholesterol());
			ps.setDouble(17, db.getCreatinine_jaffee());
			ps.setDouble(18, db.getCreatinine_enzymatic());
			ps.setDouble(19, db.getGgt());
			ps.setDouble(20, db.getUhdl_ultra_hdl());
			ps.setDouble(21, db.getIron());
			ps.setDouble(22, db.getIron_plasma());
			ps.setDouble(23, db.getLdh_ld());
			ps.setDouble(24, db.getLipase_lip());
			ps.setDouble(25, db.getMagnesium());
			ps.setDouble(26, db.getPhosphorus());
			ps.setDouble(27, db.getTotal_protein());
			ps.setDouble(28, db.getTriglycerides());
			ps.setDouble(29, db.getUric_acid());
			ps.setDouble(30, db.getUrea());
			ps.setDouble(31, db.getAso());
			ps.setDouble(32, db.getCo2());
			ps.setDouble(33, db.getRf_rheumatoid_factor());
			ps.setDouble(34, db.getChe());
			ps.setDouble(35, db.getApo_a1());
			ps.setDouble(36, db.getApo_b());
			ps.setDouble(37, db.getC3());
			ps.setDouble(38, db.getC4());
			ps.setDouble(39, db.getCrp());
			ps.setDouble(40, db.getHaptoglobin());
			ps.setDouble(41, db.getIga());
			ps.setDouble(42, db.getIgg());
			ps.setDouble(43, db.getIgm());
			ps.setDouble(44, db.getPrealbumin());
			ps.setDouble(45, db.getTransferrin_trf());	
			
			ps.setDouble(46, db.getWeight());
			ps.setDouble(47, db.getHeight());
			ps.setDouble(48, db.getBmi());
						
			ps.executeUpdate();
			Statement s = c.createStatement();
			
			// MySQL Syntax to get the identity (product_id) of inserted row
			ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must get returned to the client.
			db.setParticipant_id(rs.getLong(1));
		}
		catch (SQLException e)
		{
			Utils.logger.severe(e.getMessage());
			throw new DAOException(e);
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
