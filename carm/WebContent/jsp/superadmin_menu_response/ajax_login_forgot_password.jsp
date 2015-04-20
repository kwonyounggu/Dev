<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sickkids.caliper.common.*" %>

<table  id='forgot_password_table' border='0' cellspacing='0' cellpadding='4' width='95%' style="padding: 20px 0px 20px 60px;"> 
	<tr><td colspan=4 height=1 class=dv_line></td></tr> 	
	<tr><td colspan=4>Please enter your email to get your Password or LoginID back!</td></tr> 		
												
		<tr> 
		<td colspan=4 align="left"> 
				<input type=text id="login_email" size=38 value='' maxlength="80" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) forgot_password_confirm();"/> 						
		</td> 
	</tr> 
					
	<tr><td colspan=4 height=1 class=dv_line></td></tr> 					 
	<tr> 
		<td colspan=4 align=left><br/> 
			<input type=button style='cursor:pointer;' value='Submit' onclick="forgot_password_confirm();" />	
			<p>
				<br/>
				<a href="<%= MenuLink.SUPERADMIN_CONTEXT %>" onfocus="this.blur()"><span style='color: #333333;font-family: arial;'>Application Admin to login</span></a>
			</p>											
		</td> 
	</tr> 
 </table>