<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sickkids.caliper.common.*" %>  
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE>CALIPER</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<META name=description content="www.caliperproject.ca">
<META name=keywords content="www.caliperproject.ca">
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
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/menu/droplinebar.css" />
<link rel="stylesheet" type="text/css" href="css/menu/ddsmoothmenu.css" /> 
<link rel="stylesheet" href="css/dhtmlwindow.css" type="text/css" />
<link rel="stylesheet" href="css/modal.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/caliper.css" />

<script type="text/javascript" src="js/menu/jquery.min.js"></script>
<script type="text/javascript" src="js/menu/droplinemenu.js" ></script>
<script type="text/javascript" src="js/dhtmlwindow.js"></script>
<script type="text/javascript" src="js/modal.js"></script>
<script type="text/javascript" src="js/validation.js" ></script>
<script type="text/javascript" src="js/httpRequest.js" ></script>
<script type="text/javascript" src="js/loginOut.js" ></script>
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

  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-45955336-2', 'caliperproject.com');
  ga('send', 'pageview');

</script>
<head>
<body leftmargin="0" topmargin="0"  marginheight="0" marginwidth="0" style="background-color:#ffffff">

<center>
	<table height="100%" width="970" border="0" cellspacing="0" cellpadding="0">
		<tr height="7"><td height="7" width="100%"></td> </tr>
		<tr><td  align="center" width="100%">
				<table height="151" width="100%" background="images/hospitals/big_caliper_saskatoon.jpg">
					<tr><td align="left" valign="top" style="padding-left: 300px; font-size:13pt;">

						</td>
						<td align="right" valign="top">
							<a href="http://<%=request.getServerName() %>:<%=Integer.toString(request.getServerPort()) %>/cproject/controller"><img src='images/common/goto_caliper_home.png' width=240 height=36 /></a>
						</td>
					</tr>					
				</table>
			</td></tr>		
		<tr><td align="center" valign="top"  width='100%'><%@ include file="/local_hospitals/saskatoon/jsp/header.jsp" %></td></tr>
		<tr><td height="7" width="100%"></td> </tr>
		<tr>
			<td width="100%" valign="top" align="center">				
				<table cellspacing="0" cellpadding="0" >
					<tr><td align="center" width="970"><jsp:include page='${body_panel}' flush='true'/></td></tr>
				</table>				
			</td>
		</tr>
		<tr><td height="7" width="100%"></td> </tr>
		<tr><td align="center" width="100%">
			 <a href="<%= MenuLink.home %>" onfocus="this.blur()" style="color: #BF3A00;font-family: arial;">
				<table cellspacing="0" cellpadding="0" height="46" width="100%" background="images/footer_970x46.gif">
					<tr>
						<td align="center" valign="bottom" style="font-size:8pt;">555 University Avenue, Toronto, Ontario, Canada M1S5C2</td>
					</tr>
					<tr>
						<td align="center" valign="top" style="font-size:8pt;">Copyright &copy; 2006-2011 CaliperProject.com CaliperProject.org. All rights reserved.</td>
					</tr>
				</table>
				</a>
			</td>
		</tr>
	</table>
</center>
</body>
</html>
						