<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>

<%  //do error handler
	List<AnalyteNameBean> newAnalyteList=(List<AnalyteNameBean>)request.getAttribute("newAnalyteList");
	for(AnalyteNameBean ab : newAnalyteList)
		out.print("<option value='"+ab.getAnalyte_name_id()+"|"+ab.getV2_chart_path()+"|"+ab.getAnalyte_name()+"' >"+ab.getAnalyte_name()+"</option>");
%>