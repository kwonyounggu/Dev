<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>

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
	<table border=0 cellspacing=0 cellpadding=4 width='777' style="padding: 0 0 0 20px;" align="left"> 
		<tr>
		    <td  colspan=4>
				<table border=1 cellspacing=1 cellpadding=2 width='95%' style="padding: 0 0 0 0px;" align="center"> 
					<tr><td colspan=6>
							<span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times; vertical-align: middle;">Custom Reference Interval provided by caliperproject.ca</span>
						</td>
					</tr>				
					<tr style='background-color:#ffffff;'>
						<td class=header2_td width="25%">Aanalyte</td> 
						<td class=header2_td width="5%">Gender</td>
						<td class=header2_td width="10%">Age</td>
						<td class=header2_td width="25%">Reference Interval</td>
						<td class=header2_td width="25%">Ethnicity</td>
						<td class=header2_td width="15%">n</td>
					</tr>
					<tr style='background-color:#ffffff;'>
						<td class=center_td width="25%"><%=request.getParameter("analyte") %></td> 
						<td class=center_td width="5%"><%=request.getParameter("gender") %></td>
						<td class=center_td width="10%"><%=request.getParameter("age") %></td>
						<td class=center_td width="25%"><%=request.getParameter("interval") %></td>
						<td class=center_td width="25%"><%=request.getParameter("ethnicity") %></td>
						<td class=center_td width="15%"><%=request.getParameter("n") %></td>
					</tr>
				</table>
		    </td>						
		</tr>
	</table> 
</body>
</html>