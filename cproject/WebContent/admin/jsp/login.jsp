<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>

<script language="Javascript" type="text/javascript">

	function loginConfirm()
	{		
		var admin_name=document.getElementById("admin_name");
		var admin_email=document.getElementById("admin_email");

		admin_name.value=trim(admin_name.value);
		admin_email.value=trim(admin_email.value);

		if(!check_alphanumeric(admin_name.value))
		{ 	
			alert("Your LoginID is invalid."); 
			admin_name.focus();
		}
		else if(!isEmail(admin_email))
		{
			alert("Your admin email is not correct"); 
			admin_email.focus();
		}
		else 
		{	
			httpRequestPost("/cproject/admin","op=ajax_admin_login_check&admin_name="+admin_name.value+"&admin_email="+admin_email.value,"loginResponse");	
		}		
	}

	function loginResponse(strResponse)
	{	//alert(strResponse);
		if(strResponse.indexOf('session_timeout')==0) location.reload();
		else if(strResponse.indexOf('true:')==0)//call a central/local page
		{			
			location.replace(strResponse.substring(5));//remove true: prefix
		}
		else if(strResponse.indexOf('false:')==0)
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
											<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Login</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Admin&nbsp;&nbsp;>&nbsp;&nbsp;Login
										</td>
									</tr>	
									<tr><td colspan=2><hr/></td></tr>				
								</table>					
							</td>
						</tr>
						<tr>
						    <td colspan=4 align="center" style="padding-left: 160px; padding-right: 160px; ">
								<p><b>Welcome to CALIPER</b></p>
								<p><b>This is a classified area. Access is permitted only to authorized personnel. Any violation will be prosecuted according to the national and international laws.</b></p>
							</td>
						</tr>
						<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;"><p></p>					
								<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td align="center" width="50%">
											<img src="images/common/login_icon.png" alt="Caliper project four characters" border="0" height="256" width="256" /> 
										</td>
										<td width="50%" valign="top" align="left">
											<div id='login_body'>
												<table  id='login_table' cellpadding='2' cellspacing="4" width='100%' style="padding: 20px 0px 20px 60px;"> 
											 		
													<tr><td colspan=4>LoginID</td></tr> 		
																								
								 					<tr> 
														<td colspan=4 align="left"> 
											 				<input type=text id="admin_name" size=38 value='caliperproject' maxlength="80" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) loginConfirm();"/> 						
														</td> 
													</tr> 
												
													<tr><td colspan=4>E-Mail</td></tr> 													
								 					<tr> 
														<td colspan=4 align="left"> 
											 				<input type='text' id="admin_email" size=38 value='caliperproject@caliperproject.ca' maxlength="120" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) loginConfirm();"/> 						
														</td> 
													</tr> 					
																		 
													<tr> 
														<td colspan=4 align="left"><br/> 
															<input type=button style='cursor:pointer;' value='Login' onclick="loginConfirm();" />	
															<br/><br/>
<!--															<p ><a onfocus='this.blur()' href="#" onclick="get_forgot_pwd_page(); return false;" >Did you forget your password?</a></p>-->
<!--															<p ><a onfocus='this.blur()' href="#" onclick="get_forgot_pwd_page(); return false;" >Did you forget your loginID?</a></p>-->
														</td> 
													</tr> 
												 </table>
											 </div>											
											</td>
										</tr>
									</table>	
							</td>
						</tr>		
						<tr>
						    <td colspan=4 align="center">
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