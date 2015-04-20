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

<style type='text/css'> 
     body,td,input,div,form,select,textarea,pre{font-size:10pt; font-family:arial; }
     body
     {	border-width:0; overflow:auto;
        scrollbar-face-color: #f2f2f2; scrollbar-highlight-color: #EFEFEF; 
        scrollbar-3dlight-color: #ffffff; scrollbar-shadow-color: #cccccc;
        scrollbar-darkshadow-color: #ffffff; scrollbar-track-color: #ffffff;
        scrollbar-arrow-color: #dcdcdc;
     }
     img{border:0;}
     a,label{cursor:pointer;}
     form{margin:0; padding:0;}
     A:link {color:#519DC7;}
     A:visited {color:#9EA48A; }
     A:hover {color:#FF5005; }
     
     
</style> 
<style type=text/css> 
	.input_text{border:1px solid #aaaaaa;}
	textarea{overflow:auto; border:1px solid #aaaaaa;}
	select{border:1px solid #aaaaaa;}
	.header_td{text-align:center; width:100%; background-color:#E4E4C0; border-bottom:1px solid #fdfdfd;}
	.header2_td{text-align:center; background-color:#E4E4C0; border-bottom:1px solid #fdfdfd;}
	.header2_1_td{text-align:center; background-color:#E4E4C0; border-bottom:0px solid #fdfdfd;}
	.header3_td{text-align:center; background-color:#F7F7EA; border-bottom:1px solid #fdfdfd;}
	.light_grey_td{text-align:center; background-color:#EFDCDC; color:#333333; border-bottom:1px solid #fdfdfd;}
	.left_td{text-align:left; padding: 0 0 0 20px; width:100px; background-color:#F0F0DC; border-bottom:1px solid #fdfdfd;} 
	.left_td2{text-align:center; padding: 0 0 0 20px; width:100px; background-color:#F0F0DC; border-bottom:1px solid #fdfdfd;} 
	.left_td3{text-align:left; padding: 0 0 0 10px; width:100px; background-color:#F0F0DC; border-bottom:1px solid #fdfdfd;} 
	.check_td{text-align:right; width:0px; background-color:#F0F0DC; border-bottom:1px solid #fdfdfd;} 
	.dv_line{background-color:#f8f8f8; height:1px; padding:0;} 
	.dv_line2{background-color:#E4E4C0; height:1px; padding:0;}
	.dv_line3{background-color:#AFAF4B; height:1px; padding:0;}
	.center_td{text-align:center;}
	.left_center_td{text-align:center;background-color:#F0F0DC;}
	.qa_td{text-align:left; padding: 0 0 0 10px; width:15%; background-color:#F0F0DC; border-bottom:1px solid #fdfdfd;}
	.company_pros_td{padding: 10px 10px 10px 10px;}
	.curriculum_td{padding: 3px 10px 3px 10px;}
</style>


<script type="text/javascript" src="js/menu/jquery.min.js"></script>
<script type="text/javascript" src="js/menu/droplinemenu.js" ></script>
<script type="text/javascript" src="js/dhtmlwindow.js"></script>
<script type="text/javascript" src="js/modal.js"></script>
<script type="text/javascript" src="js/validation.js" ></script>
<script type="text/javascript" src="js/httpRequest.js" ></script>
<script type="text/javascript" src="js/loginOut.js" ></script>
<script type="text/javascript" src="js/menu/ddsmoothmenu.js"></script>
<script type="text/javascript"> 
ddsmoothmenu.init({
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
<script language="Javascript" type="text/javascript">
	window.onload=function()
	{
	}
	function check_etalk()
	{
		if(check_login())
		{
			httpRequestPost("<%= MenuLink.CONTEXT %>","op=goto_etalk_video&value=${login_email}","openClassRoomResponse");
		}
	}
	function openClassRoomResponse(strResponse)
	{
		if(strResponse.indexOf('false:')==-1)//not found
		{
			classWindow=dhtmlmodal.open('LectureBox', 'iframe', strResponse.substring(5), 'this is a classroom.', 'width=935px, height=730px, center=1, resize=0, scrolling=0','recal');
		}
		else 
		{
			alert(strResponse.substring(6));
		}		
	} 
	function setAdminPrivilege(strResponse)
	{
		if(strResponse.indexOf('false:')==-1)//not found
		{
			location.reload();
			//alert("success");
		}
		else 
		{
			alert(strResponse.substring(6));
		}	
	}
	var emailwindow;
	var teacher_login_window;
	function openEmailInput()
	{
		//Open a modal window populated with the contents of a hidden DIV, and assign the result to a global variable called "emailwindow"
		emailwindow=dhtmlmodal.open('EmailBox', 'div', 'forgot_pwd', 'Page to find my login password', 'width=350px,height=230px,center=1,resize=0,scrolling=1');
		document.getElementById("emailfield").focus();
		
		emailwindow.onclose=function()
		{
			var theform=document.getElementById("myform"); //Access the form inside the modal window
			var theemail=document.getElementById("emailfield"); //Access form field with id="emailfield"
			if (!validateEmail(theemail.value))
			{ 
				document.getElementById("email_err_display").innerHTML="<%= Message.check_email %>";
				theemail.focus();
				return false; //cancel closing of modal window
			}
			else
			{ 	
				emailPassword(theemail.value);
				return true; //allow closing of window
			}
		}
	}
	function emailPassword(value)
	{
		httpRequestPost("<%= MenuLink.CONTEXT %>","op=forgot_pwd&value="+value,"emailPasswordResponse");
	}
	function emailPasswordResponse(strResponse)
	{
		//alert(strResponse);
		if(strResponse.indexOf('false:')==-1)//not found
		{
			//document.getElementById('agreement_box').innerHTML = strResponse;
			alert(strResponse.substring(5));
		}
		else 
		{
			alert(strResponse.substring(6));
		}
	}

	function checkAdminLogin()
	{
		//Open a modal window populated with the contents of a hidden DIV, and assign the result to a global variable called "emailwindow"
		teacher_login_window=dhtmlmodal.open('TeacherLoginBox', 'div', 'teacherLoginDiv', 'Page to access to Administration!', 'width=350px,height=240px,center=1,resize=0,scrolling=1');
		document.getElementById("teacher_name").focus();

		teacher_login_window.onclose=function()
		{	
			var thename=document.getElementById("teacher_name"); 
			var theemail=document.getElementById("teacher_email");
			
			if (!isEngName(thename))
			{ 	
				document.getElementById("email_err_display_teacher").innerHTML="Your name is not acceptable.";
				thename.focus();
				return false; //cancel closing of modal window
			}
			else if (!validateEmail(theemail.value))
			{ 	
				document.getElementById("email_err_display_teacher").innerHTML="Your email is not acceptable.";
				theemail.focus();
				return false; //cancel closing of modal window
			}
			else
			{ 	
				httpRequestPost("<%= MenuLink.CONTEXT %>","op=goto_check_admin_login&teacher_name="+thename.value+"&teacher_email="+theemail.value,"setAdminPrivilege");
				return true; //allow closing of window
			}
		}
	}
</script>
<head>
<body leftmargin="0" topmargin="0"  marginheight="0" marginwidth="0" style="background-color:#ffffff">

<center>
	<table height="100%" width="970" border="0" cellspacing="0" cellpadding="0">
		<tr height="7"><td height="7" width="100%"></td> </tr>
		<tr><td  align="center" width="100%">
				<table height="151" width="100%" background="images/hospitals/big_caliper_sickkids.jpg">
					<tr><td align="left" valign="top" style="padding-left: 300px; font-size:13pt;">
						</td>
						<td align="right" valign="top">
							<a href="http://<%=request.getServerName() %>:<%=Integer.toString(request.getServerPort()) %>/cproject/controller"><img src='images/common/goto_caliper_home.png' width=240 height=36 /></a>
						</td>
					</tr>					
				</table>
			</td></tr>		
		<tr><td align="center" valign="top" ><%@ include file="home_header.jsp" %></td></tr>
		<tr><td height="7" width="100%"></td> </tr>
		<tr>
			<td width="100%" valign="top" align="center">				
				<table cellspacing="0" cellpadding="0" >
					<tr><td align="center" width="100%"><jsp:include page='${body_panel}' flush='true'/></td></tr>
				</table>				
			</td>
		</tr>
		<tr><td height="7" width="100%"></td> </tr>
		<tr><td align="center" width="100%">
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

<div id="teacherLoginDiv" style="display:none">
	<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%" height="100%" style="padding: 10px 20px 10px 20px;"> 
		<tr> 
			<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
			<td height=10 background='images/table/horizontal_top.gif'></td> 
			<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
		</tr> 
		<tr> 
			<td width=10 background='images/table/vertical_left.gif'></td> 
			<td  bgcolor=#ffffff valign="top" align="left"> <p>
				<table align="center">
					<tr><td colspan=2><img src="images/common/blue_circle.gif" style="vertical-align: middle;"/>
							<span style="color: #519DC7; font-weight:bold; font-size:9pt;">This door is only for the admin.</span></td></tr>
					<form id="teacher_login_form">
						<tr><td colspan=2>Please input your first name and email.
						</td></tr>
						<tr><td width="20%">Name:</td><td width="80%"><input id="teacher_name" type="text"  size="30" maxlength="80" /></td></tr>
						<tr><td  width="20%">Email:</td><td width="80%"><input id="teacher_email" type="text"  size="30" maxlength="80" /></td></tr>
						<tr><td align="center" colspan=2><input type="button" value="OK" name="B2" onclick="javascript:teacher_login_window.hide()" onfocus=this.blur() />
						</td></tr>
						<tr><td colspan=2 align="left"><span id="email_err_display_teacher" style="color: #ff0000; font-weight:bold; font-size:9pt;"></span></td></tr>
					</form>
				</table></p>
			</td> 
			<td width=10 background='images/table/vertical_right.gif'></td> 
		</tr> 
		<tr> 
			<td width=10 height=10><img src='images/table/round_lhs_bottom.gif' width=10 height=10></td> 
			<td height=10 background='images/table/horizontal_bottom.gif'></td> 
			<td width=10 height=10><img src='images/table/round_rhs_bottom.gif' width=10 height=10></td> 
		</tr> 
	</table> 		
</div>
</body>
</html>
						