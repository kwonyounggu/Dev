<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sickkids.caliper.common.*" %>  
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page isELIgnored ="false" %>

<HTML xmlns="http://www.w3.org/1999/xhtml">
<head>
	<TITLE>CALIPER for SmartPhone Users</TITLE>
	<script type="text/javascript" src="js/swfobject.js"></script>
	<script type="text/javascript" src="js/menu/jquery.min.js"></script>
	<script type="text/javascript" src="js/validation.js" ></script>
	<script type="text/javascript" src="js/httpRequest.js" ></script>
	<script type="text/javascript" src="js/loginOut.js" ></script>

<head>
<body leftmargin="0" topmargin="0"  marginheight="0" marginwidth="0" >

<center>				
	<table cellspacing="0" cellpadding="0" width="300">
		<tr><td align="center"><div id="divBody"><jsp:include page='${body_panel}' flush='true'/></div></td></tr>
	</table>				
</center>

</body>
</html>
						