<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>
<style>
<!--
.quick-nav 
{
    position: relative;
    background-color: #FFFFFF;
    font-size: 9px;
    padding: 20px 90px 20px 20px;
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}
 
.quick-nav table th.skew 
{
    height: 100px;
    width: 30px;
    position: relative;
    vertical-align: bottom;
    white-space: nowrap;
}
 
.quick-nav table th.last_skew 
{
    height: 80px;
    width: 30px;
    position: relative;
    vertical-align: bottom;
    white-space: nowrap;
}
.quick-nav table th.skew > div 
{
    position: relative;
    top: 0px;
    left: 50px;
    height: 100%;
    transform:skew(-45deg,0deg);
    -ms-transform:skew(-45deg,0deg);
    -moz-transform:skew(-45deg,0deg);
    -webkit-transform:skew(-45deg,0deg);
    -o-transform:skew(-45deg,0deg);    
    overflow: hidden;
    border-top: 1px solid #CCCCCC;
    border-left: 1px solid #CCCCCC;
    border-right: 1px solid #CCCCCC;
}
 
.quick-nav table th.skew span 
{
    transform:skew(45deg,0deg) rotate(315deg);
    -ms-transform:skew(45deg,0deg) rotate(315deg);
    -moz-transform:skew(45deg,0deg) rotate(315deg);
    -webkit-transform:skew(45deg,0deg) rotate(315deg);
    -o-transform:skew(45deg,0deg) rotate(315deg);        
    position: absolute;
    bottom: 8px;
    left: 1px;
    display: inline-block;
    width: 100%;
    text-align: left;
    font-weight: normal; 
}
 
.quick-nav table td 
{
    width: 30px;
    height: 25px;
    text-align: center;
    vertical-align: middle;
    border: 1px solid #CCCCCC;
    font-size: 10px;
}

.quick-nav table .odd 
{
    background-color: #E8E8E8;
}
/*------------------------------------------*/
.medication_table
{
    position: relative;
    background-color: #FFFFFF;
    font-size: 9.5px;
    padding: 20px 90px 20px 20px;
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}
.medication_table table th.skew 
{
   
    width: 30px;
    position: relative;
    vertical-align: bottom;
    white-space: nowrap;
}
.medication_table table th.skew > div 
{
    position: relative;
    top: 0px;
    left: 0px;
    height: 100%; 
    overflow: hidden;
    border-top: 1px solid #CCCCCC;
    border-left: 1px solid #CCCCCC;
    border-right: 1px solid #CCCCCC;
    
    text-align: center;
    font-weight: normal; 
}

.medication_table table td 
{
    width: 30px;
    height: 25px;
    text-align: center;
    vertical-align: middle;
    border: 1px solid #CCCCCC;
    font-size: 11px;
}

.medication_table table .odd 
{
    background-color: #E8E8E8;
}
-->
</style>
<%!
public String getSValue(float d)
{
	return d==-10000 ? "" : Float.toString(d);
}
public String getSValue(int n)
{
	return n==-10000 ? "" : Integer.toString(n);
}
public String getS0Value(float d)//no decimal point and the rest
{
	return d==-10000 ? "" : Float.toString(d).split("\\.")[0];
}
public String adjustS(String s)
{
	return s.length()>3 ? ("<a title='"+s+"'><span style='font-size: 9px;'>"+s.substring(0, 4)+"</span></a>"):s;
}
%>

<div><label><u>Vital Signs</u></label><span style='color: #888BFB; font-size: 9.5px; margin-left: 5px;'><img src='images/common/item_icon.png' style='margin-left: 5px; margin-right: 5px; vertical-align: bottom;'/><%=request.getParameter("carm_id")%></span></div>
<div class="quick-nav">
    <table>
        <thead>
            <tr>
                <th class="skew"><div class="odd"><span>Hours Pre-event</span></div></th>
                <th class="skew"><div class=""><span>Heart Rate</span></div></th>
                <th class="skew"><div class="odd"><span>Compressions</span></div></th>
                <th class="skew"><div class=""><span>Resp Rate</span></div></th>
                <th class="skew"><div class="odd"><span>Saturation</span></div></th>
                <th class="skew"><div class=""><span>Systolic BP</span></div></th>
                <th class="skew"><div class="odd"><span>Diastolic BP</span></div></th>
                <th class="skew"><div class=""><span>Capillary Refill</span></div></th>
                <th class="skew"><div class="odd"><span>Temperature &#8451;</span></div></th>
                <th class="skew"><div class=""><span>Consciousness AVPU</span></div></th>
                <th class="skew"><div class="odd"><span style='font-size: 9px; color: #000000;'>Consciousness<br/>Glasgow Coma Scale</span></div></th>
                <th class="skew"><div class=""><span>Pupils</span></div></th>
                <th class="skew"><div class="odd"><span>O<sub style="font-size:9px;">2</sub> Method</span></div></th>
                <th class="skew"><div class=""><span>O<sub style="font-size:9px;">2</sub> Amount Units</span></div></th>             
                <th class="skew"><div class=""><span>O<sub style="font-size:9px;">2</sub> Amount</span></div></th>
                <th class="skew"><div class="odd"><span>Respiratory Effort</span></div></th>
                <th class="skew"><div class=""><span>Stridor</span></div></th>
                <th class="skew"><div class="odd"><span>Total IV Fluid ml/Kg</span></div></th>
                <th class="skew"><div class=""><span>Urine Output vol/Kg</span></div></th>
                <th class="skew"><div class="odd"><span>Urine ml/Kg Yes-No</span></div></th>
                <th class="skew"><div class=""><span>Narcotic Infusion</span></div></th>
                <th class="skew"><div class="odd"><span>Physician Review</span></div></th>
                <th class="skew"><div class=""><span>Stat Call</span></div></th>
                <th class="skew"><div class="odd"><span>Nurse Patient Ratio</span></div></th>
                <th class="skew"><div class=""><span>Parent Present</span></div></th>
            </tr>
        </thead>
        <tbody>
        <%
        	List<CarmO_DEBean> vitalSignList=(List<CarmO_DEBean>)request.getAttribute("vital_sign_list");
        	for(CarmO_DEBean db : vitalSignList)
        	{
        		out.print("<tr>");
        		out.print("<td class='odd'>"+db.getHrPreevent()+"</td>");
        		out.print("<td class=''>"+getS0Value(db.getHeartRate())+"</td>");
        		out.print("<td class='odd'>"+db.getCompression()+"</td>");
        		out.print("<td class=''>"+getS0Value(db.getRespRate())+"</td>");
        		out.print("<td class='odd'>"+getS0Value(db.getSaturation())+"</td>");
        		out.print("<td class=''>"+getS0Value(db.getSystolicBp())+"</td>");
        		out.print("<td class='odd'>"+getS0Value(db.getDiastolicBp())+"</td>");
        		out.print("<td class=''>"+getSValue(db.getCapillaryRefill())+"</td>");
        		out.print("<td class='odd'>"+getSValue(db.getTemperature())+"</td>");
        		out.print("<td class=''>"+db.getConsciousnessAvpu()+"</td>");
        		out.print("<td class='odd'>"+getS0Value(db.getConsciousnessGcs())+"</td>");
        		out.print("<td class=''>"+adjustS(db.getPupils())+"</td>");
        		out.print("<td class='odd'>"+adjustS(db.getO2Method())+"</td>");
        		out.print("<td class=''>"+db.getO2AmountUnit()+"</td>");
        		out.print("<td class='odd'>"+getSValue(db.getO2Amount())+"</td>");
        		out.print("<td class=''>"+adjustS(db.getRespEffort())+"</td>");
        		out.print("<td class='odd'>"+db.getStridor()+"</td>");
        		out.print("<td class=''>"+getSValue(db.getTotalIvFluid())+"</td>");
        		out.print("<td class='odd'>"+getSValue(db.getUrineOutput())+"</td>");
        		out.print("<td class=''>"+db.getUrineYesNo()+"</td>");
        		out.print("<td class='odd'>"+db.getNarcoticInfusion()+"</td>");
        		out.print("<td class=''>"+db.getPhysicianReview()+"</td>");
        		out.print("<td class='odd'>"+db.getStatCall()+"</td>");
        		out.print("<td class=''>"+db.getNursePatientRatio()+"</td>");
        		out.print("<td class='odd'>"+db.getParentPresent()+"</td>");
        		out.print("</tr>");
        	}
        %>
        </tbody>
    </table>
</div>
<div><label><u>Medications Administered in the 24 hours before event</u></label><span style='color: #888BFB; font-size: 9.5px; margin-left: 5px;'><img src='images/common/item_icon.png' style='margin-left: 5px; margin-right: 5px; vertical-align: bottom;'/><%=request.getParameter("carm_id")%></span></div>
<div class="medication_table">
    <table width='100%'>
        <thead>
            <tr>
                <th class="skew" width='6%'><div class="odd">Hour</div></th>
                <th class="skew" width='10%'><div class="odd">Route</div></th>
                <th class="skew" width='34%'><div class="odd">Medication</div></th>
                <th class="skew" width='6%'><div class="odd">Hour</div></th>
                <th class="skew" width='10%'><div class="odd">Route</div></th>
                <th class="skew" width='34%'><div class="odd">Medication</div></th>
            </tr>
        </thead>
        <tbody>
        <%
        	List<CarmO_DE3Bean> medicationList=(List<CarmO_DE3Bean>)request.getAttribute("medication_list");
        	//System.out.println("size="+medicationList.size()+", n="+(medicationList.size()+1)/2);
        	for(int i=0, n=(medicationList.size()+1)/2;i<n;i++)
        	{
        		CarmO_DE3Bean mb=(CarmO_DE3Bean)medicationList.get(i);
        		out.print("<tr>");
        		out.print("<td class='odd'>"+getSValue(mb.getHour())+"</td>");
        		out.print("<td class=''>"+mb.getRoute()+"</td>");    		
        		out.print("<td class='' align='left'>"+mb.getMedication()+"</td>");
        		if((n+i)<medicationList.size())
        		{
        			mb=(CarmO_DE3Bean)medicationList.get((n+i));
        			out.print("<td class='odd'>"+getSValue(mb.getHour())+"</td>");
            		out.print("<td class=''>"+mb.getRoute()+"</td>");    		
            		out.print("<td class='' align='left'>"+mb.getMedication()+"</td>");
        			out.print("</tr>");
        		}
        		else
        		{
        			out.print("<td class='odd'></td>");
        			out.print("<td class=''></td>");
        			out.print("<td class=''></td>");
        			out.print("</tr>"); 
        		}
        	}        	
        %>
        </tbody>
    </table>
</div>

