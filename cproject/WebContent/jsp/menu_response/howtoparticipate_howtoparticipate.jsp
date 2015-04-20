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
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">How To Participate</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;How To Participate
										</td>
									</tr>		
									<tr><td colspan='2'><hr/></td></tr>					
								</table>					
							</td>
						</tr>

						<tr><td colspan=4 style="font-size:11pt; font-family: arial; vertical-align: middle; word-wrap: break-word; padding-right: 40px; padding-bottom: 0px; padding-left: 40px;">						
								<p>We are glad that your interest in the CALIPER project brings you to this section of our website, and we appreciate your
								contribution toward improving the assessment and treatment of children with various medical concerns.</p>
								<p>On this page you will find all the necessary information about how to participate in the project, either you are a
								   parent/guardian consenting for your child, or a teenager between 16 and 18 years old consenting for yourself.</p>
								<p>There is a short <b>questionnaire to be completed</b> and <b>a consent form to be signed</b>. If the participant is under 16, their parent/guardian must sign the consent form. Adolescents aged 16-18 years old can sign the consent form themselves. These forms can be viewed, printed, read and/or completed at home, or at the appointment time.</p>
								<p>Assistance with completing the questionnaire and the consent form will be available at the appointment time by
								   a CALIPER Volunteer. If participating at a local school, all forms must be completed and returned to the school before the clinic date. For any questions about participating in the project or completing the forms, please contact <a onfocus="this.blur()" href="<%= MenuLink.contactus %>" style="color: #519DC7;" title="">Olivia Virag</a>, CALIPER
								   Coordinator.</p>
								<p>Living outside of Toronto and would like to participate in the project? Click on <a a href="<%= MenuLink.caliperacrosscanada %>" onfocus="this.blur()" style="color: #519DC7;" title="">CALIPER Across Canada</a>, to find
								   the other participating hospitals in Canada. Children living anywhere in Canada or visiting Canada are welcome to participate in
								   the project.</p>
								<p align="left">The <b>Consent Form</b> will take you through the process of participating in the CALIPER Project, including the steps to follow,
								   the potential benefits for participants, and more.</p>
								<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
									<tbody>
										<tr>
											<td align="center" width="50%">
												<img src="images/main_body/14624-wcharacters_four.gif" alt="Caliper project four characters" border="0" height="310" width="240" /> 
											</td>
											<td width="50%">
												<h3 style="color: #519DC7;"><a id="questionaires_and_forms" name="questionaires_and_forms">Questionnaires:</a><br /></h3>
												<ul><li><a href="pdf/questionnaire_birth_18 years_2014.pdf" onfocus="this.blur()" title="">Questionnaire</a><br />(Birth - 18 years old)</li>
<!--													<li><a href="pdf/21107-0907Questionnaire_1-18yrs.pdf" onfocus="this.blur()" title="">Questionnaire</a><br />(1 year old - 18 years old)</li>-->
												</ul>
												<h3 style="color: #519DC7;">Consent Forms:<br /></h3>
												<ul><li><a href="pdf/consent_form_birth_18_2014.pdf" onfocus="this.blur()" title="">Consent Form</a><br />(Birth - 18 years old)</li>
<!-- 													<li><a href="pdf/21110-0907ConsentForm_16-18yrs.pdf" onfocus="this.blur()" title="">Consent Form</a><br />(16 years old - 18 years old)</li> -->
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
									<p>For more information, please <a href="<%= MenuLink.contactus %>" onfocus="this.blur()" title="" style="color: #519DC7;">Contact Us</a></p>	
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