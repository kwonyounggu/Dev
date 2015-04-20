<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>

<%
	String parameters="analyte="+request.getParameter("analyte")+"&minAge="+request.getParameter("minAge")+"&maxAge="+request.getParameter("maxAge")+"&gender="+request.getParameter("gender")+"&ethnic="+request.getParameter("ethnic");
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
			<table border=0 cellspacing=1 cellpadding=0 width='98%' style="padding: 0 0 0 0px;" align="center"> 
				<tr><td colspan=4 align="center"><img src="jsp/menu_response/downloads_calculate_chart.jsp?<%=parameters%>"/></td></tr>
			 </table> 	
	    </td>						
	</tr>
</table> 