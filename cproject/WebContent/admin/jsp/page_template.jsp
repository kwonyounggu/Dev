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
<!-- about calendar program -->
<link rel="stylesheet" type="text/css" href="js/epoch_v202_en/epoch_styles.css" /> 
<script type="text/javascript" src="js/epoch_v202_en/epoch_classes.js"></script> 

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
	var calendar1, calendar2, calendar3; /*must be declared in global scope*/
	/*put the calendar initializations in the window's onload() method*/
	window.onload=function()
	{	
		//calendar1 = new Epoch('cal1','flat',document.getElementById('calendar1_container'),false);
		calendar1 = new Epoch('cal2','popup',document.getElementById('calendar1_container'),false);
		calendar2 = new Epoch('cal2','popup',document.getElementById('calendar2_container'),false);
		calendar3 = new Epoch('cal3','flat',document.getElementById('calendar3_container'),true);
	}
</script>
<head>
<body leftmargin="0" topmargin="0"  marginheight="0" marginwidth="0" style="background-color:#ffffff">

<center>
	<table height="100%" width="970" border="0" cellspacing="0" cellpadding="0">
		<tr height="7"><td height="7" width="970"></td> </tr>
		<tr><td  align="center" width="970">
				<table height="190" width="970" background="images/caliperbanner.jpeg">
					<tr><td align="left" valign="top" style="padding-left: 150px; font-size:13pt;">
							<a href="<%= MenuLink.home %>" onfocus="this.blur()" style="color: #BF3A00;font-family: arial;"><h10>CaliperProject.com</h10></a></td>
						<td align="right" valign="top"><a href="http://www.caliperdatabase.com"><img src='images/main_body/gotoprofessionalwebsite.png' width=240 height=36 /></a></td>
					</tr>	
					<tr><td align="left" valign="bottom" style="padding-left: 150px; font-size:13pt;"></td>
						<td align="right" valign="bottom" style="padding-right: 30px; ">
							<%  String admin_email=(String)session.getAttribute("admin_email"); 
								if(admin_email!=null)
									out.print("<a style='color: #4197C1; font-weight:bold; font-size:10pt; font-family: arial;' href='javascript:admin_logout()'>admin out</a>");
							%>
						</td>
					</tr>				
				</table>
			</td></tr>		
		<tr><td align="center" valign="top" width="970"><%@ include file="header.jsp" %></td></tr>
		<tr><td height="7" width="970"></td> </tr>
		<tr>
			<td width="970" valign="top" align="center">				
				<table cellspacing="0" cellpadding="0" width="970">
					<tr><td align="center" width="970"><jsp:include page='${body_panel}' flush='true'/></td></tr>
				</table>				
			</td>
		</tr>
		<tr><td height="7" width="970"></td> </tr>
		<tr><td align="center" width="970">
				<table cellspacing="0" cellpadding="0" height="46" width="100%" background="images/footer_970x46.gif">
					<tr>
						<td align="center" valign="bottom" style="font-size:8pt;">555 University Avenue, Toronto, Ontario, Canada M1S5C2</td>
					</tr>
					<tr>
						<td align="center" valign="top" style="font-size:8pt;">Copyright &copy; 2006-2011 CaliperProject.com CaliperProject.org. All rights reserved.</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>


</body>
</html>
						