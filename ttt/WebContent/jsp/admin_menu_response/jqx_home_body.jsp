<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>
<style type="text/css">

	
	hr
	{	
		border-top: 1px dashed #C1C2FD;
		background-color: #E8E8E8;
		height: 0px;
		width: 100%;
	}
</style>
<% AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb"); %>

 
<table border=0 cellpadding=0 cellspacing=0 bgcolor='#ffffff' width="100%" > 
	
		<tr>
			<td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
			 	1. Create Hospitals <br/>
			 	2. Create Accounts for each Hospital<br/>
			 	3. Create Seminars and corresponding scheduling<br/>
			 	4. Upload any required training files any time<br/>
			</td>
		</tr>
		<tr><td colspan="4" height="10" ></td></tr>
		<tr>
			<td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
			 	
			</td>
		</tr>
</table> 			