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
<% 
	AllRegisteredUserBean trb=(AllRegisteredUserBean)session.getAttribute("trb"); 
	CurriculumCurrentBean curriculumBean=(CurriculumCurrentBean)session.getAttribute("curriculum_bean");
%>

 
<table border=0 cellpadding=0 cellspacing=0 bgcolor='#ffffff' width="100%" > 
	
		<tr>
			<td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
			 	
			</td>
		</tr>
		<tr><td colspan="4" height="10" ></td></tr>
		<tr>
			<td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
			<td colspan=4 style="font-size:10pt; font-family: arial; text-align: center; vertical-align: middle; word-wrap: break-word;">
				<div>
					<script type="text/javascript"> 
						/*
						http://help.adobe.com/en_US/flex/using/WS2db454920e96a9e51e63e3d11c0bf663fe-7fff.html
						swfobject.embedSWF("TestProject.swf", "flashContent","100%", "100%", "10.0.0", "playerProductInstall.swf", flashvars, params, attributes);
						var flashvars = {roomId : "TTT00", roomName : "BPEWS", loginLevel: "2"};
						swfobject.embedSWF("ttt.swf", "myContent", "940", "528", "10.0.0", "playerProductInstall.swf", flashvars, params);//FLEX 4.7, 940Wx528H
						swfobject.embedSWF("bin/tttClient.swf", "myContent", "940", "640", "9.0.0", "bin/expressInstall.swf", flashvars, params);//FLEX3.0, 940Wx640H
						*/
						var params={allowfullscreen : "true"};						
						var flashvars = 
						{	
								userId : 			"${trb.userId}",
								firstName : 		"${trb.firstName}",  
								lastName : 			"${trb.lastName}",
								loginLevel : 		"${trb.loginLevel}",  
								hospitalId : 		"${trb.hospitalId}",  
								email : 			"${trb.email}",
								hospitalName : 		"${trb.hospitalName}",  
								courseNumber : 		"${curriculum_bean.courseNumber}",
								courseName : 		"${curriculum_bean.courseName}",  
								participantType : 	"<%=curriculumBean.getParticipantType(trb.getUserId())%>"
						};
						swfobject.embedSWF("ttt.swf", "myContent", "940", "528", "10.0.0", "playerProductInstall.swf", flashvars, params);
						
					</script>
				</div>
				<div id="myContent"> 
					<h1>Alternative content</h1> 
					<p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" /></a></p> 
				</div> 		
			</td>
		</tr>
</table> 			