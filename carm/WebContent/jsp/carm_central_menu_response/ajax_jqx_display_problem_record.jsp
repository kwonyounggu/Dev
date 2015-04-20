<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>

<%!
public String getMark(String s)
{
	if(s==null) return "[&nbsp;&nbsp;]";
	return s.equals("Y") ? "[x]" : "[&nbsp;&nbsp;]";
}
public String getMarkOList(String s)
{
	if(s==null) return "[&nbsp;&nbsp;]";
	return s.length()>0 ? "[x]" : "[&nbsp;&nbsp;]";
}
public String getDescOList(String s)
{
	return s==null ? "" : s;
}
%>
<% 
	CarmO_DE4Bean de4Bean=(CarmO_DE4Bean)request.getAttribute("de4_bean");
//The following color is corresponding with energyblue
//style='background-color:#E0E9F5;	
%>
 
<table cellpadding="0" cellspacing="0" width="100%">
<tr><td colspan='2' align="left" style='background-color:#E8E8E8; color: #000000; padding-left: 5px; padding-right: 5px; padding-top: 2px; padding-bottom: 2px;'><b>Current Problems</b>&nbsp;&nbsp;(in the 24 hours before the event)<span style='color: #888BFB; font-size: 9.5px; margin-left: 5px;'><img src='images/common/item_icon.png' style='margin-left: 5px; margin-right: 5px; vertical-align: bottom;'/><%=request.getParameter("carm_id")%></span></td></tr>
</table>
<%=de4Bean.displayProblems() %>