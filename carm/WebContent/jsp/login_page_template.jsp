<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sickkids.caliper.common.*" %>  
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE>Carm</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<META name=description content="www.centerforsafetyresearch.com">
<META name=keywords content="www.centerforsafetyresearch.com">
<META name=author content="Younggu Kwon">
<META name=resource-type content=document>
<META name=distribution content=global>
<META name=robots content=index,follow>
<META name=revisit-after content="1 days">
<META name=rating content=general>

<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<meta http-equiv="pragma" content="no-cache">

<link REL="SHORTCUT ICON" HREF="ico/favicon.ico">

<link rel="stylesheet" type="text/css" href="css/menu/droplinebar.css" />
<link rel="stylesheet" type="text/css" href="css/menu/ddsmoothmenu.css" /> 
<link rel="stylesheet" href="css/dhtmlwindow.css" type="text/css" />
<link rel="stylesheet" href="css/modal.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/csr.css" />


<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/menu/jquery.min.js"></script>
<script type="text/javascript" src="js/menu/droplinemenu.js" ></script>
<script type="text/javascript" src="js/dhtmlwindow.js"></script>
<script type="text/javascript" src="js/modal.js"></script>
<script type="text/javascript" src="js/validation.js" ></script>
<script type="text/javascript" src="js/httpRequest.js" ></script>

<script type="text/javascript" src="js/menu/ddsmoothmenu.js"></script>
<script type="text/javascript"> 
ddsmoothmenu.init
({
	mainmenuid: "main-nav", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})
</script> 

<script language="Javascript" type="text/javascript">

window.onload=function()
{ 
 
} 
</script>
</head>
<body leftmargin="0" topmargin="0"  marginheight="0" marginwidth="0" >

<center>
	<table height="100%" width="1000" border="0" cellspacing="0" cellpadding="0">
		<tr height="7"><td height="7" width="100%"></td> </tr>
		<tr><td  align="center" width="100%">
				<table height="120" width="1000" background="images/CSRWebHeader2013.gif">
					<tr><td align="left" valign="top" style="padding-left: 150px; padding-top: 7px; font-size:13pt;">
						<td align="right" valign="top">
						</td>
					</tr>	
					<tr>
						<td colspan=2 align="right" valign="bottom" style="padding-right: 250px;">
								<%
									/*
									MedicationSafetyRegisteredUserBean mrb=(MedicationSafetyRegisteredUserBean)session.getAttribute("mrb");
									
									if(mrb!=null) //handle more on this later, for ttt_central_logout and ttt_local_logout
									{	
										List<MedicationSafetyLoginLevelBean> loginLevelList=(List<MedicationSafetyLoginLevelBean>)application.getAttribute("msloginLevelList");
										if(mrb.getLogin_level()==1)//super admin
										{
											out.print("[M] "+loginLevelList.get(0).getLoginLevelDescription()+": <b><span style='color: #768596;font-family: arial;'>"+mrb.getFirst_name()+"</span></b>&nbsp;&nbsp;|&nbsp;&nbsp;");
											out.print("<a onfocus='this.blur()' href="+MenuLink.ms_central_logout+"><span style='color: #768596;font-family: arial;'>Logout</span></a>");
										}
										else if(mrb.getLogin_level()==2)//ms admin
										{
											out.print("[M] "+loginLevelList.get(1).getLoginLevelDescription()+": <b><span style='color: #768596;font-family: arial;'>"+mrb.getFirst_name()+"</span></b>&nbsp;&nbsp;|&nbsp;&nbsp;");
											out.print("<a onfocus='this.blur()' href="+MenuLink.ms_central_logout+"><span style='color: #768596;font-family: arial;'>Logout</span></a>");
										}
										else if(mrb.getLogin_level()==3)//data manager
										{
											out.print("[M] "+loginLevelList.get(2).getLoginLevelDescription()+": <b><span style='color: #768596;font-family: arial;'>"+mrb.getFirst_name()+"</span></b>&nbsp;&nbsp;|&nbsp;&nbsp;");
											out.print("<a onfocus='this.blur()' href="+MenuLink.ms_central_logout+"><span style='color: #768596;font-family: arial;'>Logout</span></a>");
										}
										else if(mrb.getLogin_level()==4)//data reviewer
										{
											out.print("[M] "+loginLevelList.get(3).getLoginLevelDescription()+": <b><span style='color: #768596;font-family: arial;'>"+mrb.getFirst_name()+"</span></b>&nbsp;&nbsp;|&nbsp;&nbsp;");
											out.print("<a onfocus='this.blur()' href="+MenuLink.ms_central_logout+"><span style='color: #768596;font-family: arial;'>Logout</span></a>");
										}
										else if(mrb.getLogin_level()==5)//data viewer
										{
											out.print("[M] "+loginLevelList.get(4).getLoginLevelDescription()+": <b><span style='color: #768596;font-family: arial;'>"+mrb.getFirst_name()+"</span></b>&nbsp;&nbsp;|&nbsp;&nbsp;");
											out.print("<a onfocus='this.blur()' href="+MenuLink.ms_central_logout+"><span style='color: #768596;font-family: arial;'>Logout</span></a>");
										}
										
									}	
									*/
								%>
						</td>
					</tr>				
				</table>
			</td></tr>		
		<tr><td align="center" valign="top" ><%@ include file="carm_central_menu_response/login_header.jsp" %></td></tr>
		<tr><td height="7" width="100%"></td> </tr>
		<tr>
			<td width="100%" valign="top" align="center">				
				<table cellspacing="0" cellpadding="0" >
					<tr><td align="center" width="1000"><div id="divBody"><jsp:include page='${body_panel}' flush='true'/></div></td></tr>
				</table>				
			</td>
		</tr>
		<tr><td height="7" width="100%"></td> </tr>
		<tr><td align="center" width="100%">
				<table cellspacing="0" cellpadding="0" height="46" width="1000" background="images/footer.gif">
					<tr>
						<td align="center" valign="bottom" style="font-size:8pt;">555 University Avenue, Toronto, Ontario, Canada M1S5C2</td>
					</tr>
					<tr>
						<td align="center" valign="top" style="font-size:8pt;">Copyright &copy; 2010-2014 CenterForSafetyResearch.com. All rights reserved.</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>
</body>
</html>
						