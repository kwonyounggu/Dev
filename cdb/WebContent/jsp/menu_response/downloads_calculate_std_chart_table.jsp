<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>

<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="com.sickkids.caliper.chart.Plot" %>
<%@ page import="java.util.List" %>

<%@ page  import="java.awt.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="org.jfree.chart.*" %>
<%@ page  import="org.jfree.chart.axis.*" %>
<%@ page  import="org.jfree.chart.entity.*" %>
<%@ page  import="org.jfree.chart.labels.*" %>
<%@ page  import="org.jfree.chart.plot.*" %>
<%@ page  import="org.jfree.chart.renderer.category.*" %>
<%@ page  import="org.jfree.chart.urls.*" %>
<%@ page  import="org.jfree.data.category.*" %>
<%@ page  import="org.jfree.data.general.*" %>
<%@ page  import="org.jfree.data.xy.*" %>

<%
	ChartUtilities.writeChartAsPNG(response.getOutputStream(), 
 								   Plot.createNormalDisributionGraph(
 										   				   (String)Utils.analyteMap.get(request.getParameter("analyte")), 
 										   				   request.getParameter("gender"),
 										   				   (String)Utils.ethnicMap.get(request.getParameter("ethnic")),
 										   				   request.getParameter("mean"),
 										   				   request.getParameter("std")
 										   				   ), 700, 400);
%>
