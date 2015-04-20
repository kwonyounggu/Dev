<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>

<script language="Javascript" type="text/javascript">
	
	function signinConfirm()
	{		
		var login_email=document.getElementById("login_email");
		var login_password=document.getElementById("login_password");

		login_email.value=trim(login_email.value);

		if(!validateEmail(login_email.value))
		{ 	alert("<%= Message.incorrect_email %>"); 
			login_email.focus();
		}
		else if(!validatePassword(login_password.value))
		{
			alert("Your password is not correct. Please check if [Caps Lock] is turned on.");
			login_password.focus();
		}
		else 
		{	
			loginRequest("/caliperdatabase/controller","op=login_check&login_email="+login_email.value+"&login_password="+login_password.value,"loginResponse");	
		}		
	}
	function forgot_password_confirm()
	{   
		var login_email=document.getElementById("login_email");

		login_email.value=trim(login_email.value);

		if(!validateEmail(login_email.value))
		{ 	alert("<%= Message.incorrect_email %>"); 
			login_email.focus();
		}
		else 
		{	
			httpRequestPost("/caliperdatabase/controller","op=forgot_pwd_check&login_email="+login_email.value,"forgot_emailPasswordResponse");	
		}		
	}
	function forgot_emailPasswordResponse(strResponse)
	{
		if(strResponse.indexOf('false:')==-1)//not found
		{
			alert(strResponse.substring(5));
		}
		else 
		{
			alert(strResponse.substring(6));
		}
	}
	function get_forgot_pwd_page()
	{
		httpRequestPost("<%= MenuLink.CONTEXT %>","op=forgot_pwd","forgotPasswordResponse");
	}
	function forgotPasswordResponse(strResponse)
	{
		removeRowFromTable("login_table");
		document.getElementById('login_body').innerHTML = strResponse;
	}
	function loginResponse(strResponse)
	{	
		if(strResponse.indexOf('false:')==-1)//not found
		{
			//back to home page after a successful login done. You don't use location.reload() since it will call /caliperdatabase/controller?op=login again.
			location.replace(strResponse);
		}
		else 
		{
			alert(strResponse.substring(6));
			//focus to the login input box
		}
	}
</script>
<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td valign="top"></td></tr>
	<tr><td align="center">
		<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%"> 
			<tr> 
				<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
				<td height=10 background='images/table/horizontal_top.gif'></td> 
				<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
			</tr> 
			<tr> 
				<td width=10 background='images/table/vertical_left.gif'></td> 
				<td  bgcolor=#ffffff valign="top" align="left"> 
					<table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 0px 20px 20px 20px; "> 	
						<tr>
						    <td colspan=4 align="left">
								<table height="40" width="100%">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">Sign in to CALIPER</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Sign in to CALIPER
										</td>
									</tr>	
									<tr><td colspan='2'><hr/></td></tr>				
								</table>				
							</td>
						</tr>

						<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;"><p></p>					
								<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td align="center" width="50%">
											<img src="images/main_body/14624-wcharacters_four.gif" alt="Caliper project four characters" border="0" height="310" width="240" /> 
										</td>
										<td width="50%" valign="top">
											<div id='login_body'>
												<table  id='login_table' border='0' cellspacing='0' cellpadding='4' width='95%' style="padding: 0 0 0 0px;"> 
											 		<tr><td colspan=4 height=1 class=dv_line></td></tr> 	
													<tr><td colspan=4>Login Email <span style="font-size: .95em;">(eg: caliper@gmail.com)</span></td></tr> 		
																								
								 					<tr> 
														<td colspan=4 align="left"> 
											 				<input type=text id="login_email" size=38 value='' maxlength="80" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) signinConfirm();"/> 						
														</td> 
													</tr> 
													<tr><td colspan=4 height=1 class=dv_line></td></tr> 
													<tr><td colspan=4>Password</td></tr> 													
								 					<tr> 
														<td colspan=4 align="left"> 
											 				<input type='password' id="login_password" size=38 value='' maxlength="20" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) signinConfirm();"/> 						
														</td> 
													</tr> 					
													<tr><td colspan=4 height=1 class=dv_line></td></tr> 					 
													<tr> 
														<td colspan=4 align=center><br/> 
															<input type=button style='cursor:pointer;' value='Sign In' onclick="signinConfirm();" />	
															<br/><br/>
															<p ><a onfocus=this.blur() href="#" onclick="get_forgot_pwd_page(); return false;" >Did you forget your password?</a></p>												
														</td> 
													</tr> 
												 </table>
											 </div>											
											</td>
										</tr>
									</table>	
							</td>
						</tr>		
								
					</table>
				</td> 
				<td width=10 background='images/table/vertical_right.gif'></td> 
			</tr> 
			<tr> 
				<td width=10 height=10><img src='images/table/round_lhs_bottom.gif' width=10 height=10></td> 
				<td height=10 background='images/table/horizontal_bottom.gif'></td> 
				<td width=10 height=10><img src='images/table/round_rhs_bottom.gif' width=10 height=10></td> 
			</tr> 
		</table> 
 	</td>
	</tr>
</table>  