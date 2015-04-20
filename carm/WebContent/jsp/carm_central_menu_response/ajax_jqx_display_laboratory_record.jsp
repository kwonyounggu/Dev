<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>
<style>
<!--
.lab_table
{
    position: relative;
    background-color: #FFFFFF;
    padding: 20px 20px 20px 20px;
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}


.lab_table table td 
{
    vertical-align: middle;
    border: 1px solid #CCCCCC;
    font-size: 12px;
}

.lab_table table .odd 
{
    background-color: #E8E8E8;
}
-->
</style>
<%!
public String getSValue(double d)
{
	return d==-10000 ? "" : Double.toString(d);
}
public String getS0Value(double d)
{
	return d==-10000 ? "" : Double.toString(d).split("\\.")[0];
}
%>
<% 
	CarmO_DE5Bean de5_bean_1=(CarmO_DE5Bean)request.getAttribute("de5_bean_1");
	CarmO_DE5Bean de5_bean_12=(CarmO_DE5Bean)request.getAttribute("de5_bean_12");
	CarmO_DE5Bean de5_bean_48=(CarmO_DE5Bean)request.getAttribute("de5_bean_48");
	CarmO_DE5Bean de5_bean_tests_24=(CarmO_DE5Bean)request.getAttribute("de5_bean_tests_24");
	CarmO_DE5Bean de5_bean_l_limit=(CarmO_DE5Bean)request.getAttribute("de5_bean_l_limit");
	CarmO_DE5Bean de5_bean_u_limit=(CarmO_DE5Bean)request.getAttribute("de5_bean_u_limit");
	Map assay_unit_map=(Map)request.getAttribute("assay_unit_map");
//The following color is corresponding with energyblue
//style='background-color:#E0E9F5;	
%>
<div><span style='color: #888BFB; font-size: 9.5px; margin-left: 5px;'><img src='images/common/item_icon.png' style='margin-left: 5px; margin-right: 5px; vertical-align: bottom;'/><%=request.getParameter("carm_id")%></span></div> 
<div class="lab_table">
<table cellpadding="1" cellspacing="2" width="100%">
	<thead>
	<tr style='background-color:#E8E8E8; color: #000000;'>
		<td align="center" valign='middle'  width="25%"></td>
		<td align="center"  style="padding-top: 5px;" width="11%"><div>closest to 48 hrs before AND<br/>&le;48 h & &gt;24 h before event</div></td>
		<td align="center"  style="padding-top: 5px;" width="11%"><div>closest to 12 hrs before AND<br/>&le;24 h & &ge;12 hr before event</div></td>
		<td align="center"  style="padding-top: 5px;" width="11%"><div>closest to one hr before AND<br/>&lt;12 h & &ge;1 hr before event</div></td>
		<td align="center"  valign="bottom" width="11%"><div>number of tests between<br/>&le;24 hr and &gt;1 hr</div></td>
		<td align="center"  valign="bottom" width="11%"><div>lower limit of normal</div></td>
		<td align="center"  valign="bottom" width="11%"><div>upper limit of normal</div></td>
		<td align="center"  valign="bottom" width="9%"><div>units</div></td>
	</tr>
	</thead>
	<tbody>
	<tr style='background-color:#EAEAFF; color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="left"  width="25%" style='padding-left: 5px;'><b>Haematologic</b></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="9%"></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Haemoglobin</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getHaeHaemoglobin()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getHaeHaemoglobin()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getHaeHaemoglobin()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getHaeHaemoglobin()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getHaeHaemoglobin()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getHaeHaemoglobin()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("haeHaemoglobin") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>White Cell Count</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getHaeWhiteCellCount()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getHaeWhiteCellCount()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getHaeWhiteCellCount()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getHaeWhiteCellCount()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getHaeWhiteCellCount()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getHaeWhiteCellCount()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("haeWhiteCellCount") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Bands</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getHaeBands()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getHaeBands()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getHaeBands()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getHaeBands()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getHaeBands()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getHaeBands()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("haeBands") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Platelet Count</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getHaePlateletCount()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getHaePlateletCount()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getHaePlateletCount()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getHaePlateletCount()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getHaePlateletCount()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getHaePlateletCount()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("haePlateletCount") %></td>
	</tr>
	
	<tr style='background-color:#EAEAFF; color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="left"  width="25%" style='padding-left: 5px;'><b>Electrolyte</b></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="9%"></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Sodium</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getEleSodium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getEleSodium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getEleSodium()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getEleSodium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getEleSodium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getEleSodium()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("eleSodium") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Potassium</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getElePotassium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getElePotassium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getElePotassium()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getElePotassium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getElePotassium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getElePotassium()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("elePotassium") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Calcium (total)</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getEleCalciumTotal()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getEleCalciumTotal()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getEleCalciumTotal()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getEleCalciumTotal()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getEleCalciumTotal()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getEleCalciumTotal()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("eleCalciumTotal") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Calcium Ionized</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getEleCalciumIonized()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getEleCalciumIonized()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getEleCalciumIonized()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getEleCalciumIonized()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getEleCalciumIonized()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getEleCalciumIonized()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("eleCalciumIonized") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Magnesium</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getEleMagnesium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getEleMagnesium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getEleMagnesium()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getEleMagnesium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getEleMagnesium()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getEleMagnesium()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("eleMagnesium") %></td>
	</tr>
	
	<tr style='background-color:#EAEAFF; color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="left"  width="25%" style='padding-left: 5px;'><b>Blood Gas Arterial</b></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="9%"></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>PaO<sub style="font-size:9px;">2</sub></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getBloPaO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getBloPaO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getBloPaO2()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getBloPaO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getBloPaO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getBloPaO2()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("bloPaO2") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>PaCO<sub style="font-size:9px;">2</sub></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getBloPaCO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getBloPaCO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getBloPaCO2()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getBloPaCO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getBloPaCO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getBloPaCO2()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("bloPaCO2") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>HCO<sub style="font-size:9px;">3</sub> <span style='font-size: 9px;'>measured, calculated</span></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getBloHCO3()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getBloHCO3()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getBloHCO3()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getBloHCO3()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getBloHCO3()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getBloHCO3()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("bloHCO3") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>pH</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getBloPh()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getBloPh()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getBloPh()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getBloPh()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getBloPh()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getBloPh()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("bloPh") %></td>
	</tr>
	
	<tr style='background-color:#EAEAFF; color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="left"  width="25%" style='padding-left: 5px;'>Blood Gas <b>Mixed Venous</b></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="9%"></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>PmvO<sub style="font-size:9px;">2</sub></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getVenPmvO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getVenPmvO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getVenPmvO2()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getVenPmvO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getVenPmvO2()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getVenPmvO2()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("venPmvO2") %></td>
	</tr>	
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>HCO<sub style="font-size:9px;">3</sub> <span style='font-size: 9px;'>measured, calculated</span></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getVenHCO3()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getVenHCO3()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getVenHCO3()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getVenHCO3()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getVenHCO3()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getVenHCO3()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("venHCO3") %></td>
	</tr>

	<tr style='background-color:#EAEAFF; color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="left"  width="25%" style='padding-left: 5px;'><b>Metabolic</b></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="9%"></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Ammonia</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getMetAmmonia()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getMetAmmonia()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getMetAmmonia()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getMetAmmonia()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getMetAmmonia()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getMetAmmonia()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("metAmmonia") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Lactate</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getMetLactate()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getMetLactate()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getMetLactate()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getMetLactate()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getMetLactate()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getMetLactate()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("metLactate") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Glucose</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getMetGlucose()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getMetGlucose()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getMetGlucose()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getMetGlucose()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getMetGlucose()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getMetGlucose()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("metGlucose") %></td>
	</tr>
	
	<tr style='background-color:#EAEAFF; color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="left"  width="25%" style='padding-left: 5px;'><b>Liver</b></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="11%"></td>
		<td align="center"  width="9%"></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Aspartate Aminotransferase</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getLiverAST()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getLiverAST()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getLiverAST()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getLiverAST()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getLiverAST()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getLiverAST()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("liverAST") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Alanine Aminotransferase</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getLiverALT()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getLiverALT()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getLiverALT()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getLiverALT()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getLiverALT()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getLiverALT()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("liverALT") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Bilirubin conjugated</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getLiverBilirubinCon()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getLiverBilirubinCon()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getLiverBilirubinCon()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getLiverBilirubinCon()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getLiverBilirubinCon()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getLiverBilirubinCon()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("liverBilirubinCon") %></td>
	</tr>
		<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Bilirubin unconjugated</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getLiverBilirubinUncon()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getLiverBilirubinUncon()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getLiverBilirubinUncon()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getLiverBilirubinUncon()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getLiverBilirubinUncon()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getLiverBilirubinUncon()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("liverBilirubinUncon") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Lactate Dehydrogenase</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getLiverLDH()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getLiverLDH()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getLiverLDH()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getLiverLDH()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getLiverLDH()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getLiverLDH()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("liverLDH") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>International Norm Ratio (INR)</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getLiverINR()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getLiverINR()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getLiverINR()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getLiverINR()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getLiverINR()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getLiverINR()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("liverINR") %></td>
	</tr>
	<tr style='color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'>
		<td align="right"  width="25%" style='padding-right: 5px;'>Partial Prothrombin Time</td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_48.getLiverPTT()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_12.getLiverPTT()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_1.getLiverPTT()) %></td>
		<td align="left"  width="11%"><%=getS0Value(de5_bean_tests_24.getLiverPTT()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_l_limit.getLiverPTT()) %></td>
		<td align="left"  width="11%"><%=getSValue(de5_bean_u_limit.getLiverPTT()) %></td>
		<td align="left"  width="9%"><%=assay_unit_map.get("liverPTT") %></td>
	</tr>
	</tbody>
</table>
</div>

