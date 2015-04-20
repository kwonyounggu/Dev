<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%@ page isELIgnored ="false" %>
<html>
<head>
<link REL="SHORTCUT ICON" HREF="ico/favicon.ico">
<style type="text/css">
	td{text-align:center; }
</style>
<script type="text/javascript"> 

(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-45955336-1', 'caliperdatabase.com');
  ga('send', 'pageview');
  
</script> 
</head>
<body>
<table border=1 cellspacing=0 cellpadding=0 width='1000' style="padding-left: 0px; padding-right: 0px; border-collapse: collapse;"> 
	<tr><td colspan=6><span style="color: #800000;">${analyte_name}
		</span>
		<br/><span style="font-size: 8.5pt; float: right;">		
		<%
			DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss");
			Date date = new Date();
			out.print(dateFormat.format(date));
		%>
		&nbsp;provided by www.CaliperDatabase.com&nbsp;&nbsp;</span>
		</td>
	</tr>
	<tr><td colspan=6 height=1 class=dv_line3></td></tr> 
	<tr>
		<td width="100%" colspan=6  align="center">Female Reference Intervals</td> 
	</tr>
	<tr>
		<td width="16%">Age</td> 
		<td width="19%">Lower Limit</td>
		<td width="19%">Upper Limit</td>
        <td width="8%">Samples</td>
        <td width="19%">Lower CI</td>
		<td width="19%">Higher CI</td>
	</tr>
	<%  
		List<StdReferenceIntervalBean> femaleList = (List<StdReferenceIntervalBean>)session.getAttribute("femaleList");
		if(femaleList != null) 
			for(StdReferenceIntervalBean sb : femaleList)
			{
				out.print("<tr>");
				out.print("<td width='16%'>"+sb.getAge_range()+"</td>"+ 
						  "<td width='19%'>"+sb.getRi_lower_limit()+"</td>"+
						  "<td width='19%'>"+sb.getRi_upper_limit()+"</td>"+
		        		  "<td width='8%'>"+sb.getSamples()+"</td>"+
		        		  "<td width='19%'>"+sb.getCi_lower_limit()+"</td>"+
						  "<td width='19%'>"+sb.getCi_upper_limit()+"</td>");
				out.print("</tr>");
			}
	%>	
	
	<tr><td colspan=6 height=1 class=dv_line3></td></tr> 
	<tr>
		<td width="100%" colspan=6  align="center">Male Reference Intervals</td> 
	</tr>
	<tr>
		<td width="16%">Age</td> 
		<td width="19%">Lower Limit</td>
		<td width="19%">Upper Limit</td>
        <td width="8%">Samples</td>
        <td width="19%">Lower CI</td>
		<td width="19%">Higher CI</td>
	</tr>
	<%  
		List<StdReferenceIntervalBean> maleList = (List<StdReferenceIntervalBean>)session.getAttribute("maleList");
		if(maleList != null) 
			for(StdReferenceIntervalBean sb : maleList)
			{
				out.print("<tr>");
				out.print("<td width='16%'>"+sb.getAge_range()+"</td>"+ 
						  "<td width='19%'>"+sb.getRi_lower_limit()+"</td>"+
						  "<td width='19%'>"+sb.getRi_upper_limit()+"</td>"+
		        		  "<td width='8%'>"+sb.getSamples()+"</td>"+
		        		  "<td width='19%'>"+sb.getCi_lower_limit()+"</td>"+
						  "<td width='19%'>"+sb.getCi_upper_limit()+"</td>");
				out.print("</tr>");
			}
	%>	

	
</table>

</body>
</html>