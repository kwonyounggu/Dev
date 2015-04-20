<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sickkids.caliper.common.*" %>	
<TABLE WIDTH=205 height=135 BORDER=0 CELLPADDING=0 CELLSPACING=0 id="loginTable" STYLE="background-image: url('images/login/login_out.gif');">
	<tr height=40><td colspan=2></td></tr>
	<tr>
		<td colspan=2>
			<table width="100" height="100%" BORDER=0 CELLPADDING=0 CELLSPACING=2>
				<tr><td width="80%" style="padding: 0px 1px 0px 2px;" nowrap><span style="color: #ffffff; font-size:7pt; font-weight: bold;">Email:</span><input id="input_login_email" type="text" style="width:100px; height: 16px" maxlength="80" onKeyDown="if(event.keyCode==13) login_button();"/></td><td rowspan=2 style="padding: 0px 2px 0px 1px; cursor:pointer;" onclick="login_button();"><IMG SRC="images/login/login_button.png"></td></tr>
				<tr><td width="80%" style="padding: 0px 1px 0px 2px;" nowrap><span style="color: #ffffff; font-size:7pt; font-weight: bold;">&nbsp;PWD:</span><input id="input_login_password" type="password" style="width:100px; height: 16px" maxlength="20" onKeyDown="if(event.keyCode==13) login_button();"/></td></tr>
			</table>
		</td>
	</tr>
	<tr height=35>
		<td align="center" valign="top" style="padding: 2px 0px 0px 0px;" colspan=2>
			<a onfocus=this.blur() href="<%= MenuLink.signup %>"><IMG SRC="images/login/join_button.png"></a>
			<a onfocus=this.blur() href="#" onclick="openEmailInput(); return false;"><IMG SRC="images/login/find_pwd_button.png"></a>
		</td>
	</tr>
</TABLE>
