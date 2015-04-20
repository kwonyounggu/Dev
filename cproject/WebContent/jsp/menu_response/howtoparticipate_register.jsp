<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>

<script language="Javascript" type="text/javascript">
	
	function verifyEmail()
	{	
		var form = this.document.reg_form;
		this.document.reg_form.reg_email.value=trim(this.document.reg_form.reg_email.value);		
		if(!validateEmail(form.reg_email.value))
		{	
			alert("<%= Message.incorrect_email %>");
			form.reg_email.focus();
			return false;
		}
		else
		{
			return true;
		}
	}

	function signupConfirm()
	{
		var form = this.document.reg_form;
		
		form.reg_name.value=removeSpecialChars(form.reg_name.value);
		//form.reg_email.value=removeSpecialChars(form.reg_email.value);
		form.reg_phone.value=removeSpecialChars(form.reg_phone.value);
		form.reg_school.value=removeSpecialChars(form.reg_school.value);
		
		form.reg_name.value=trim(form.reg_name.value);	
		form.reg_email.value=trim(form.reg_email.value);
		form.reg_phone.value=trim(form.reg_phone.value);
		form.reg_school.value=trim(form.reg_school.value);

		//1. check name
		//2. check email
		//3. check phone
	
		if(form.reg_name.value.length<2) 
		{ 	alert("Please put your right name!"); 
			form.reg_name.focus();
		}
		else if(!validateEmail(form.reg_email.value))
		{ 	alert("<%= Message.incorrect_email %>"); 
			form.reg_email.focus();
		}
		else if( !isPhoneNumber(form.reg_phone))
		{
			alert("Please put your right phone number!"); 
			form.reg_phone.focus();
		}
		else 
		{	form.submit();	
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
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">Register To Participate</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Register To Participate
										</td>
									</tr>			
									<tr><td colspan='2'><hr/></td></tr>				
								</table>					
							</td>
						</tr>

						<tr><td colspan=4 style="font-size:11pt; font-family: arial; vertical-align: top; word-wrap: break-word; padding-right: 40px; padding-bottom: 0px; padding-left: 40px;">						
								<p>Please fill out the form at the right hand side. We will contact you shortly for the next step.</p>
								
								<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td align="center" width="50%">
											<img src="images/main_body/14624-wcharacters_four.gif" alt="Caliper project four characters" border="0" height="310" width="240" /> 
										</td>
										<td width="50%" valign="top">
											<form name='reg_form' method='post' action='controller '><input type='hidden' name='op' value='reg_signup_complete' />
											<table border=0 cellspacing=0 cellpadding=4 width='95%' style="padding: 0 0 0 0px;"> 
										 		<tr><td colspan=4 height=1 class=dv_line></td></tr> 	
												<tr> 
												
													<td class=left_td3><span style="color: #000000;">*</span>&nbsp;Name</td> 
													<td class=check_td></td> 
													<td colspan=2 align="left"> 
														<input type=text name="reg_name" size="25" maxlength="50" class=input_text /> 
														<input type='hidden' name='reg_name_hidden'/>	
													</td> 
												</tr> 	
												<tr><td colspan=4 height=1 class=dv_line></td></tr> 
							 					<tr> 
													<td class=left_td3><span style="color: #000000;">*</span>&nbsp;Sex</td> 
													<td class=check_td></td> 
													<td colspan=2 align="left"> 
														<input type="radio" name="reg_sex" value="1" checked>Male</input>
														<input type="radio" name="reg_sex" value="2" >Female</input> 						
													</td> 
												</tr> 
												<tr><td colspan=4 height=1 class=dv_line></td></tr> 
							 					<tr> 
													<td class=left_td3><span style="color: #000000;">*</span>&nbsp;Email</td> 
													<td class=check_td></td> 
													<td colspan=2 align="left"> 
										 				<input type=text name="reg_email" size=50 value='' maxlength="80" class=input_text style='imemode:inactive' /> 
														<input type='hidden' name='reg_email_hidden'/>							
													</td> 
												</tr> 
												<tr><td colspan=4 height=1 class=dv_line></td></tr> 
												<tr> 
													<td class=left_td3><span style="color: #000000;">*</span>&nbsp;Phone</td> 
													<td class=check_td></td> 
													<td colspan=2 align="left">
														<input type=text name="reg_phone" size=18 maxlength=12 class="input_text" style='imemode:inactive'/>											
													</td> 
												</tr> 
																	
												<tr><td colspan=4 height=1 class=dv_line></td></tr> 
											
												<tr> 
													<td class=left_td3>&nbsp;&nbsp;School</td> 
													<td class=check_td></td> 
													<td colspan=2 > 
														<input type=text name="reg_school" size=30  maxlength=30 class=input_text />
													</td> 
												</tr> 
 						 
												<tr> 
													<td colspan=4 align=center><br> 
														<input type=button style='cursor:pointer;' value='Submit' onclick="signupConfirm();" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(<span style="color: #000000;">*</span>:&nbsp;&nbsp;<span>You must fill-out.)</span>
													</td> 
												</tr> 
											 </table>
											 </form>
											</td>
										</tr>
									</table>
									<p>For more information, please <a href="<%= MenuLink.contactus %>" onfocus="this.blur()" title="" style="color: #519DC7;">Contact Us</a></p>	
							</td>
						</tr>		
								
					</table><br/>
					<table border=0 cellspacing=0 cellpadding=0 width='100%' height='220'> 	
						<tr><td></td></tr>
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