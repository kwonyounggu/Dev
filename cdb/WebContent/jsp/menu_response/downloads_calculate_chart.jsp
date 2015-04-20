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
	ConnectionPool cp=(ConnectionPool)application.getAttribute("connectionPool");
	XYSeriesCollection dataset=new SQLDao(cp).getScatterPlotDataList(request.getParameter("analyte"),request.getParameter("minAge"),request.getParameter("maxAge"),
																	 request.getParameter("gender"),request.getParameter("ethnic"));
 	ChartUtilities.writeChartAsPNG(response.getOutputStream(), 
 								   Plot.createChart(dataset, 
 										   				   request.getParameter("analyte"), 
 										   				   Integer.parseInt(request.getParameter("gender")),
 										   				   (String)Utils.ethnicMap.get(request.getParameter("ethnic")) 
 										   				   ), 700, 400);
%>
