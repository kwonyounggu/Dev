<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sickkids.caliper.common.*" %>  
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page isELIgnored ="false" %>
<!doctype html>
<html>
<head>
<title>TTT</title>
<META name=author content="Younggu Kwon">
<META name=department content="CCM at the Hospital for Sick Children in Toronto">
<META name=email content="younggu.kwon@sickkids.ca">

<link rel="SHORTCUT ICON" href="ico/favicon.ico" />


<link rel="stylesheet" type="text/css" href="css/menu/ddsmoothmenu.css" /> 
<link rel="stylesheet" href="css/dhtmlwindow.css" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/csr.css" />

<!-- about calendar program -->
<link rel="stylesheet" type="text/css" href="js/epoch_v202_en/epoch_styles.css" /> 
<script type="text/javascript" src="js/epoch_v202_en/epoch_classes.js"></script> 

<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="https://jqwidgets.com/jquery-widgets-demo/jqwidgets/jqx-all.js"></script>
<link rel="stylesheet" type="text/css" href="https://jqwidgets.com/jquery-widgets-demo/jqwidgets/styles/jqx.base.css" />    
<link rel="stylesheet" type="text/css" href="https://jqwidgets.com/jquery-widgets-demo/jqwidgets/styles/jqx.fresh.css" />    
<link rel="stylesheet" type="text/css" href="https://jqwidgets.com/jquery-widgets-demo/jqwidgets/styles/jqx.energyblue.css" />    

<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/validation.js" ></script>
<script type="text/javascript" src="js/httpRequest.js" ></script>
<script type="text/javascript" src="js/common.js" ></script>

<script type="text/javascript" src="js/menu/ddsmoothmenu.js"></script>

<script type="text/javascript" src="js/utils.js"></script>

<script language="Javascript" type="text/javascript">
	//var calendar1, calendar2, calendar3; /*must be declared in global scope*/
	/*put the calendar initializations in the window's onload() method*/
	var calendar1;
	window.onload=function()
	{	
		calendar1 = new Epoch('cal1','flat',document.getElementById('calendar1_container'),false);
		//calendar1 = new Epoch('cal2','popup',document.getElementById('calendar1_container'),false);
		//calendar2 = new Epoch('cal2','popup',document.getElementById('calendar2_container'),false);
		//calendar3 = new Epoch('cal3','flat',document.getElementById('calendar3_container'),true);
	}
	if (!isCanvasSupported())
	{
		alert("ERROR: your browser is not supporting HTML5!!!\n\nPlease upgrade your browser with recent version.\n\nOtherwise it won't work properly!!!");
	}
</script>

<style>
	.rounded_div
	{
		border:4px solid #f4f4f4;
		padding:10px 10px 10px 10px; 
		background:#ffffff;
		width:1000;
		height:100%;
		border-radius:10px;
	}
</style>
</head>
<body leftmargin="0" topmargin="0"  marginheight="0" marginwidth="0" >
<center>
	<table height="100%" width="1000" border="0" cellspacing="0" cellpadding="0">
		<tr height="7"><td height="7" width="100%"></td> </tr>
		<tr><td  align="center" width="100%"></td></tr>		

		<tr><td align="center" valign="top" ><%@ include file="/jsp/oneway_viewer_menu_response/jqx_header.jsp" %></td></tr>
		<tr><td height="7" width="100%"></td> </tr>
		<tr>
			<td width="100%"  height="100%" valign="top" align="center">				
				<table cellspacing="0" cellpadding="0" width="100%" height="100%">
					<tr><td align="center" width="1000"><div id="divBody" class="rounded_div"><jsp:include page='${body_panel}' flush='true'/></div></td></tr>
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
						<td align="center" valign="top" style="font-size:8pt;">Copyright &copy; 2010-2012 CenterForSafetyResearch.com. All rights reserved.</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

</body>
</html>
						