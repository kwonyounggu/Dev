<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>


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
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">Forms To Download in BC CHILDREN'S HOSPITAL</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial; ">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home_vancouver %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Forms To Download
										</td>
									</tr>		
									<tr><td colspan='2'><hr/></td></tr>				
								</table>
							</td>
						</tr>
						<tr height=10><td colspan=4></td></tr>
						<tr><td colspan=4  style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
								<p><h3><span style=" color: #ff6699;">For participation in CALIPER project:</span></h3></p>
							</td>
						</tr>
						<tr><td colspan=4 width="50%"  style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
								
								<p align="left">The <b>Assent Form</b> and <b>Consent Form</b> will lead you throughout the process of participating in the CALIPER Project, steps to follow,
								   potential benefits for participants, and more.</p>
								<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
									<tbody>
										<tr>
											<td align="center" width="50%">
												<img src="images/main_body/14624-wcharacters_four.gif" alt="Caliper project four characters" border="0" height="310" width="240" /> 
											</td>
											<td width="50%">
												<h3 style="color: #519DC7;"><a id="questionaires_and_forms" name="questionaires_and_forms">Assent Forms:</a><br /></h3>
												<ul><li><a href="local_hospitals/ottawa/doc/Assent_May_25_2011_Clean_copy.doc" onfocus="this.blur()" title="" onclick="alert('<%=Message.doc_na %>'); return false">Assent Form (English)</a></li>
													<li><a href="local_hospitals/ottawa/doc/FRENCH_Assent_Aug_17_11.doc" onfocus="this.blur()" title="" onclick="alert('<%=Message.doc_na %>'); return false">Assent Form (French)</a></li>
												</ul>
												<h3 style="color: #519DC7;">Consent Forms:<br /></h3>
												<ul><li><a href="local_hospitals/ottawa/doc/Consent_Form May_25_11_Parent_CLEAN_COPY.doc" onfocus="this.blur()" title="" onclick="alert('<%=Message.doc_na %>'); return false">Consent Form for Parent (English)</a></li>
													<li><a href="local_hospitals/ottawa/doc/FRENCH_Consent_Form_Aug_17_11_Parent.doc" onfocus="this.blur()" title="" onclick="alert('<%=Message.doc_na %>'); return false">Consent Form for Parent (French)</a></li>
													<li><a href="local_hospitals/ottawa/doc/Consent_Form_May_25_11_Youth_CLEAN_COPY.doc" onfocus="this.blur()" title="" onclick="alert('<%=Message.doc_na %>'); return false">Consent Form for Youth (English)</a></li>
													<li><a href="local_hospitals/ottawa/doc/FRENCH_Consent_Form_Aug_17_11_Youth.doc" onfocus="this.blur()" title="" onclick="alert('<%=Message.doc_na %>'); return false">Consent Form for Youth (French)</a></li>
												</ul>
											</td>
										</tr>
										<tr>
											<td width="50%"></td>
											<td valign="top" align="left" width="50%">
											</td>
										</tr>
									  </tbody>
									</table>					
							</td>						
						</tr>	

								
					</table>
					<br/>
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