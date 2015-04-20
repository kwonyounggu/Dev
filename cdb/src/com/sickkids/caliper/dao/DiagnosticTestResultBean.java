package com.sickkids.caliper.dao;

import java.io.Serializable;

public class DiagnosticTestResultBean implements Serializable
{
	  private static final long serialVersionUID = 1L;
	  private int diagnostic_test_result_id=0; 
	  private double albumin_g=0.0d; 
	  private double albumin_p=0.0d;
	  private double alkaline_phosphatase=0.0d;
	  private double alt=0.0d;
	  private double alt_act=0.0d;
	  private double amylase=0.0d;
	  private double ast=0.0d;
	  private double ast_act=0.0d;
	  private double bilirubin_direct=0.0d;
	  private double bilirubin_total=0.0d;
	  private double calcium=0.0d;
	  private double cholesterol=0.0d;
	  private double creatinine_jaffee=0.0d;
	  private double creatinine_enzymatic=0.0d;
	  private double ggt=0.0d;
	  private double uhdl_ultra_hdl=0.0d;
	  private double iron=0.0d;
	  private double iron_plasma=0.0d;
	  private double ldh_ld=0.0d;
	  private double lipase_lip=0.0d;
	  private double magnesium=0.0d;
	  private double phosphorus=0.0d;
	  private double total_protein=0.0d;
	  private double triglycerides=0.0d;
	  private double uric_acid=0.0d;
	  private double urea=0.0d;
	  private double aso=-1.0d;
	  private double co2=0.0d;
	  private double rf_rheumatoid_factor=0.0d;
	  private double che=0.0d;
	  private double apo_a1=0.0d;
	  private double apo_b=0.0d;
	  private double c3=0.0d;
	  private double c4=0.0d;
	  private double crp=0.0d;
	  private double haptoglobin=0.0d;
	  private double lga=0.0d;
	  private double lgg=0.0d;
	  private double lgm=0.0d;
	
	private double prealbumin=0.0d;
	private double transferrin_trf=0.0d;
	
	public void setAfterVerification(String testType, double value)
	{
		
	}
    public double getLgm()
	{
		return lgm;
	}
	public void setLgm(double lgm)
	{
		this.lgm = lgm;
	}
	public int getDiagnostic_test_result_id()
	{
		return diagnostic_test_result_id;
	}
	public void setDiagnostic_test_result_id(int diagnostic_test_result_id)
	{
		this.diagnostic_test_result_id = diagnostic_test_result_id;
	}
	public double getAlbumin_g()
	{
		return albumin_g;
	}
	public void setAlbumin_g(double albumin_g)
	{
		this.albumin_g = albumin_g;
	}
	public double getAlbumin_p()
	{
		return albumin_p;
	}
	public void setAlbumin_p(double albumin_p)
	{
		this.albumin_p = albumin_p;
	}
	public double getAlkaline_phosphatase()
	{
		return alkaline_phosphatase;
	}
	public void setAlkaline_phosphatase(double alkaline_phosphatase)
	{
		this.alkaline_phosphatase = alkaline_phosphatase;
	}
	public double getAlt()
	{
		return alt;
	}
	public void setAlt(double alt)
	{
		this.alt = alt;
	}
	public double getAlt_act()
	{
		return alt_act;
	}
	public void setAlt_act(double alt_act)
	{
		this.alt_act = alt_act;
	}
	public double getAmylase()
	{
		return amylase;
	}
	public void setAmylase(double amylase)
	{
		this.amylase = amylase;
	}
	public double getAst()
	{
		return ast;
	}
	public void setAst(double ast)
	{
		this.ast = ast;
	}
	public double getAst_act()
	{
		return ast_act;
	}
	public void setAst_act(double ast_act)
	{
		this.ast_act = ast_act;
	}
	public double getBilirubin_direct()
	{
		return bilirubin_direct;
	}
	public void setBilirubin_direct(double bilirubin_direct)
	{
		this.bilirubin_direct = bilirubin_direct;
	}
	public double getBilirubin_total()
	{
		return bilirubin_total;
	}
	public void setBilirubin_total(double bilirubin_total)
	{
		this.bilirubin_total = bilirubin_total;
	}
	public double getCalcium()
	{
		return calcium;
	}
	public void setCalcium(double calcium)
	{
		this.calcium = calcium;
	}
	public double getCholesterol()
	{
		return cholesterol;
	}
	public void setCholesterol(double cholesterol)
	{
		this.cholesterol = cholesterol;
	}
	public double getCreatinine_jaffee()
	{
		return creatinine_jaffee;
	}
	public void setCreatinine_jaffee(double creatinine_jaffee)
	{
		this.creatinine_jaffee = creatinine_jaffee;
	}
	public double getCreatinine_enzymatic()
	{
		return creatinine_enzymatic;
	}
	public void setCreatinine_enzymatic(double creatinine_enzymatic)
	{
		this.creatinine_enzymatic = creatinine_enzymatic;
	}
	public double getGgt()
	{
		return ggt;
	}
	public void setGgt(double ggt)
	{
		this.ggt = ggt;
	}
	public double getUhdl_ultra_hdl()
	{
		return uhdl_ultra_hdl;
	}
	public void setUhdl_ultra_hdl(double uhdl_ultra_hdl)
	{
		this.uhdl_ultra_hdl = uhdl_ultra_hdl;
	}
	public double getIron()
	{
		return iron;
	}
	public void setIron(double iron)
	{
		this.iron = iron;
	}
	public double getIron_plasma()
	{
		return iron_plasma;
	}
	public void setIron_plasma(double iron_plasma)
	{
		this.iron_plasma = iron_plasma;
	}
	public double getLdh_ld()
	{
		return ldh_ld;
	}
	public void setLdh_ld(double ldh_ld)
	{
		this.ldh_ld = ldh_ld;
	}
	public double getLipase_lip()
	{
		return lipase_lip;
	}
	public void setLipase_lip(double lipase_lip)
	{
		this.lipase_lip = lipase_lip;
	}
	public double getMagnesium()
	{
		return magnesium;
	}
	public void setMagnesium(double magnesium)
	{
		this.magnesium = magnesium;
	}
	public double getPhosphorus()
	{
		return phosphorus;
	}
	public void setPhosphorus(double phosphorus)
	{
		this.phosphorus = phosphorus;
	}
	public double getTotal_protein()
	{
		return total_protein;
	}
	public void setTotal_protein(double total_protein)
	{
		this.total_protein = total_protein;
	}
	public double getTriglycerides()
	{
		return triglycerides;
	}
	public void setTriglycerides(double triglycerides)
	{
		this.triglycerides = triglycerides;
	}
	public double getUric_acid()
	{
		return uric_acid;
	}
	public void setUric_acid(double uric_acid)
	{
		this.uric_acid = uric_acid;
	}
	public double getUrea()
	{
		return urea;
	}
	public void setUrea(double urea)
	{
		this.urea = urea;
	}
	public double getAso()
	{
		return aso;
	}
	public void setAso(double aso)
	{
		this.aso = aso;
	}
	public double getCo2()
	{
		return co2;
	}
	public void setCo2(double co2)
	{
		this.co2 = co2;
	}
	public double getRf_rheumatoid_factor()
	{
		return rf_rheumatoid_factor;
	}
	public void setRf_rheumatoid_factor(double rf_rheumatoid_factor)
	{
		this.rf_rheumatoid_factor = rf_rheumatoid_factor;
	}
	public double getChe()
	{
		return che;
	}
	public void setChe(double che)
	{
		this.che = che;
	}
	public double getApo_a1()
	{
		return apo_a1;
	}
	public void setApo_a1(double apo_a1)
	{
		this.apo_a1 = apo_a1;
	}
	public double getApo_b()
	{
		return apo_b;
	}
	public void setApo_b(double apo_b)
	{
		this.apo_b = apo_b;
	}
	public double getC3()
	{
		return c3;
	}
	public void setC3(double c3)
	{
		this.c3 = c3;
	}
	public double getC4()
	{
		return c4;
	}
	public void setC4(double c4)
	{
		this.c4 = c4;
	}
	public double getCrp()
	{
		return crp;
	}
	public void setCrp(double crp)
	{
		this.crp = crp;
	}
	public double getHaptoglobin()
	{
		return haptoglobin;
	}
	public void setHaptoglobin(double haptoglobin)
	{
		this.haptoglobin = haptoglobin;
	}
	public double getLga()
	{
		return lga;
	}
	public void setLga(double lga)
	{
		this.lga = lga;
	}
	public double getLgg()
	{
		return lgg;
	}
	public void setLgg(double lgg)
	{
		this.lgg = lgg;
	}
	public double getPrealbumin()
	{
		return prealbumin;
	}
	public void setPrealbumin(double prealbumin)
	{
		this.prealbumin = prealbumin;
	}
	public double getTransferrin_trf()
	{
		return transferrin_trf;
	}
	public void setTransferrin_trf(double transferrin_trf)
	{
		this.transferrin_trf = transferrin_trf;
	}
	public String toString()
	{
		return 
		  (diagnostic_test_result_id+" "+
		  albumin_g+" "+
		  albumin_p+" "+
		  alkaline_phosphatase+" "+
		  alt+" "+
		  alt_act+" "+
		  amylase+" "+
		  ast+" "+
		  ast_act+" "+
		  bilirubin_direct+" "+
		  bilirubin_total+" "+
		  calcium+" "+
		  cholesterol+" "+
		  creatinine_jaffee+" "+
		  creatinine_enzymatic+" "+
		  ggt+" "+
		  uhdl_ultra_hdl+" "+
		  iron+" "+
		  iron_plasma+" "+
		  ldh_ld+" "+
		  lipase_lip+" "+
		  magnesium+" "+
		  phosphorus+" "+
		  total_protein+" "+
		  triglycerides+" "+
		  uric_acid+" "+
		  urea+" "+
		  aso+" "+
		  co2+" "+
		  rf_rheumatoid_factor+" "+
		  che+" "+
		  apo_a1+" "+
		  apo_b+" "+
		  c3+" "+
		  c4+" "+
		  crp+" "+
		  haptoglobin+" "+
		  lga+" "+
		  lgg+" "+
		  lgm+" "+	
		prealbumin+" "+
		transferrin_trf);
	}
}
