<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<table id="reference_interval_table" border=0 cellspacing=2 cellpadding=0 width='100%' style="padding: 0 0 0 0px;"> 
	<tr><td colspan=4>&nbsp;&nbsp;
			<img src="images/common/arrow_dot.jpg" style="vertical-align: middle;"/>
			<span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Reference Intervals: <%= Utils.analyteMap.get(request.getParameter("analyte")) %></span>
		</td>
		<td colspan=2 align="right"><span style="font-size:9pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;" >&nbsp;&nbsp;&nbsp;&nbsp;<a href="jsp/menu_response/print_standard_rfi.jsp?<%="analyte="+request.getParameter("analyte")%>" style="color: #800000;text-decoration:underline;">print</a></span></td>
	</tr>
	<tr><td colspan=6 height=1 class=dv_line3></td></tr> 
	<tr>
		<td class=header2_1_td width="20%" rowspan=2>Age</td> 
		<td class=header2_1_td width="25%">Female</td>
		<td class=header2_1_td width="10%" rowspan=2>n</td>
		<td class=header2_1_td width="2%" rowspan=2></td>
		<td class=header2_1_td width="25%">Male</td>
		<td class=header2_1_td width="10%" rowspan=2>n</td>
	</tr>
	<tr>
 
		<td class=header2_td width="25%">Reference Interval</td>

		<td class=header2_td width="25%">Reference Interval</td>

	</tr>
	<%  //do error handler
		
		String analyte=request.getParameter("analyte");
		ConnectionPool cp=(ConnectionPool)application.getAttribute("connectionPool");
		List analyteList=new SQLDao(cp).getAnalyteRecordList(analyte);
		for(int i=0;i<analyteList.size();i++)
		{
			StandardReferenceIntervalBean sb=(StandardReferenceIntervalBean)analyteList.get(i);
			out.print("<tr style='background-color:#EAF0FF;'>");
			out.print("<td class=center_td width='20%'>"+sb.getAge_range()+"</td>"+
					  "<td class=center_td width='25%'>"+sb.getFemale_reference_interval()+"</td>"+
					  "<td class=center_td width='10%'>"+sb.getFemale_number()+"</td>"+
					  "<td class=center_td width='2%'></td>"+
					  "<td class=center_td width='25%'>"+sb.getMale_reference_interval()+"</td>"+
					  "<td class=center_td width='10%'>"+sb.getMale_number()+"</td>");
			out.print("</tr>");
		}
	%>
</table>