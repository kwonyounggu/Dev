<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>

<%	
	int sex=Integer.parseInt(request.getParameter("gender"));
	String gender=(sex==3) ? "M & F" : (sex==1) ? "Male":"Female";
	int age=Integer.parseInt(request.getParameter("age"));
	String sAge=(age==0)? "0-30 Days" : (age==1) ? "31D-12M": (age==2)? "1 Year" : (age-1)+" Years";
	
	ConnectionPool cp=(ConnectionPool)application.getAttribute("connectionPool");
	
	StatisticsBean sb=new SQLDao(cp).getStatisticsResult(request.getParameter("analyte"), age, sex, request.getParameter("ethnic"));
	String low=Utils.getDoublePrecision(sb.getMean()-2*sb.getStd());
	String high=Utils.getDoublePrecision(sb.getMean()+2*sb.getStd());
	
	String parameters="analyte="+request.getParameter("analyte")+"&age="+request.getParameter("age")+"&gender="+request.getParameter("gender")+"&ethnic="+request.getParameter("ethnic")+"&mean="+sb.getMean()+"&std="+sb.getStd();
	String printParameters="analyte="+ (String)Utils.analyteMap.get(request.getParameter("analyte"))+
						   "&gender="+ gender+
						   "&age="+ sAge+
						   "&interval="+ low+" - "+high+
						   "&ethnicity="+(String)Utils.ethnicMap.get(request.getParameter("ethnic"))+
						   "&n="+sb.getN();
%>

<table id="custom_search_chart_table" border=0 cellspacing=0 cellpadding=4 width='100%' style="padding: 0 0 0 20px;" align="center"> 
	<tr>
	    <td colspan=3>
			<img src="images/common/database_icon_24x24.gif" style="vertical-align: middle;"/><span style="color: #519DC7; font-weight:bold; font-size:12pt; font-family: times, 'Times New Roman', serif;">&nbsp;&nbsp;CALIPER Database Custom Search</span>
		</td>
		<td align="right"><span style="font-size:9pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;" >&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%= MenuLink.custom_reference_intervals %>" style="color: #800000;text-decoration:underline;">back</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr><td colspan=4><p><span style="margin-left:5em; color: #616161; font-weight:normal; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">Description: this section is to provide ...</span></p></td></tr> 
	<tr>
	    <td  colspan=4>
			<table border=0 cellspacing=1 cellpadding=2 width='95%' style="padding: 0 0 0 0px;" align="center"> 
				<tr><td colspan=4>&nbsp;&nbsp;
						<img src="images/common/arr3.jpg" style="vertical-align: middle;"/>
						<span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Result of CALIPER Database Query for Reference Interval</span>
					</td>
					<td colspan=2 align="right"><span style="font-size:9pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;" >&nbsp;&nbsp;&nbsp;&nbsp;<a href="jsp/menu_response/print_custom_table_rfi.jsp?<%=printParameters%>" style="color: #800000;text-decoration:underline;">print</a></span></td>
				</tr>
		 		<tr><td colspan=6 height=1 class=dv_line3></td></tr> 
				
				<tr>
					<td class=header2_td width="25%">Aanalyte</td> 
					<td class=header2_td width="5%">Gender</td>
					<td class=header2_td width="10%">Age</td>
					<td class=header2_td width="25%">Reference Interval</td>
					<td class=header2_td width="25%">Ethnicity</td>
					<td class=header2_td width="15%">n</td>
				</tr>
				<tr style='background-color:#EAF0FF;'>
					<td class=center_td width="25%"><%=(String)Utils.analyteMap.get(request.getParameter("analyte")) %></td> 
					<td class=center_td width="5%"><%=gender %></td>
					<td class=center_td width="10%"><%=sAge %></td>
					<td class=center_td width="25%"><%=low+" - "+high %></td>
					<td class=center_td width="25%"><%=(String)Utils.ethnicMap.get(request.getParameter("ethnic")) %></td>
					<td class=center_td width="15%"><%=sb.getN() %></td>
				</tr>
				<tr><td colspan=6 height=10></td></tr> 
				<tr><td colspan=6 align="center"><img src="jsp/menu_response/downloads_calculate_std_chart_table.jsp?<%=parameters%>"/></td></tr>
			</table>
	    </td>						
	</tr>
</table> 