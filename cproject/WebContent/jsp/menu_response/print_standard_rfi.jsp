<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>

<html>
<head>
<style type=text/css> 
	.header_td{text-align:center; width:100%; background-color:#ffffff; border-bottom:1px solid #fdfdfd;}
	.header2_td{text-align:center; background-color:#ffffff; border-bottom:1px solid #fdfdfd;}
	.header2_1_td{text-align:center; background-color:#ffffff; border-bottom:0px solid #fdfdfd;}
	.center_td{text-align:center;}
</style>
</head>
<body>
	<table border=1 cellspacing=2 cellpadding=0 width='777' style="padding: 0 0 0 0px;" > 
		<tr><td colspan=6>
				<span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times; vertical-align: middle;">Standard Reference Intervals: <%= Utils.analyteMap.get(request.getParameter("analyte")) %></span>
			</td>
		</tr>
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
				out.print("<tr style='background-color:#ffffff;'>");
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
</body>
</html>