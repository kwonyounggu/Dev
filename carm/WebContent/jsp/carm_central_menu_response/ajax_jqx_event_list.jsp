<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>
 
<%
	AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb");
	List<CarmDeMX> eventObjlist=(List<CarmDeMX>)request.getAttribute("my_event_list");
%>
[
	 		<%
	 			for(int i=0; i<eventObjlist.size();i++)
	 			{
	 				CarmDeMX o=eventObjlist.get(i);
	 				out.print("{"); 	
	 				out.print("\"display_item\": \""+o.getCarm_id()+"\",");	
	 				//out.print("\"display_item\": \""+o.getCarm_id()+"\t - [Status: "+o.getReview_status()+"]\",");	
	 				out.print("\"carm_id\": "+o.getCarm_id()+",");	
	 				out.print("\"site\": \""+o.getSite()+"\",");	
	 				out.print("\"pat_no\": \""+o.getPat_no()+"\",");	
	 				out.print("\"event_no\": "+o.getEvent_no()+",");	
	 				out.print("\"de_ids\": \""+o.getDe_ids()+"\",");	
	 				out.print("\"de1_id\": "+o.getDe1_id()+",");	
	 				out.print("\"de2_id\": "+o.getDe2_id()+",");//check real data since db field data all zeros
	 				out.print("\"de3_ids\": \""+o.getDe3_ids()+"\",");	
	 				out.print("\"de4_id\": "+o.getDe4_id()+",");	//check real data since db field data all zeros
	 				out.print("\"de5_ids\": \""+o.getDe5_ids()+"\",");
	 				out.print("\"de6_id\": "+o.getDe6_id()+",");
	 				out.print("\"flag\": "+o.isFlag()+",");	
	 				out.print("\"review_status\": \""+o.getReview_status()+"\"");
	 				
					out.print(((i+1)==eventObjlist.size() ? "}" : "},"));				
	 			}
	 		%>
	 	  ]

