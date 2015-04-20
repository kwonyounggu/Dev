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
					<table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 0px 20px 20px 20px; vertical-align: middle;"> 	
						<tr>
						    <td colspan=4 align="left">
								<table height="40" width="710" background="images/common/title.gif">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<span style="color: #ffffff; font-weight:bold; font-size:12pt;">&nbsp;&nbsp;Children And Families</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; color: #ffffff;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Children And Families
										</td>
									</tr>					
								</table>
							</td>
						</tr>
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold; font-size:12pt;">What CALIPER means to me</span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
							There are many sick kids in Canada and the world, and I want to help out.  I know that I will be helping doctors treat children by donating a little bit of my blood to the CALIPER project.  Doctors will be able to use my test results, along with those from other healthy children, to determine when values are abnormal in a sick child.  This knowledge will help them diagnose and treat sick kids faster and better. My mom says we should all plan for the future. One day soon, my siblings, cousins, friends, or even I may get sick. If that happens I know my donation to the CALIPER project will help the doctors make us feel healthy again.
							</p>							
							</td>
						</tr>		
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold; font-size:12pt;">How do I get involved?</span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
							We are glad that your interest in the CALIPER project brings you to this section of our website and appreciate your contribution toward improving the assessment and treatment of children with various medical concerns. On this page you will find all the necessary information about 'How to Participate' in the project, whether you are a parent/guardian consenting for your child, or a teenager between 16 and 18 years old consenting for yourself.
							</p>							
							</td>
						</tr>	
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold;"><i>Eligibility</i></span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
								<ul>
									<li>Children 0-18 years of age</li>
									<li>Children ages 0-16: parent/guardian consent and attendance is necessary</li>
									<li>Teenagers ages 16-18: consent for yourself</li>
									<li>No known diseases </li>
								</ul>
							</p>							
							</td>
						</tr>		
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold;"><i>Steps to take</i></span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
								<ol>
									<li>Read the “About CALIPER” information about the goals of the CALIPER project</li>
									<li>Read the “How to participate” information</li>
									<li>Contact the CALIPER project coordinator (see below for contact information)</li>
									<li>Complete a short Questionnaire and complete and sign a Consent Form. These forms can be viewed, printed, read and/or completed at home, as well as at the appointment time. Assistance with completing the Questionnaire and/or the Consent Form will be available at the appointment time by a CALIPER Volunteer. </li>
									<li>Attend CALIPER clinic (see list at “CALIPER Clinics”)</li>
								</ol>
							</p>							
							</td>
						</tr>		
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold;"><i>What to expect</i></span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
								Participants are scheduled for an appointment at one of the CALIPER Clinics. The participants submit the questionnaire and consent forms completed at home, or complete a questionnaire (see below) and complete and sign consent form at the clinic. After review of the forms by the CALIPER volunteer, participants donate blood. The blood draw is performed by an experienced phlebotomist. The volume of blood taken varies with age but ranges from 1 mL (neonates/infants) to 10 mL (11-18 year olds). 10 ml approximately equals to two teaspoons of blood.
							</p>							
							</td>
						</tr>	
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold;"><i>Living outside of Toronto and would like to participate in the project? </i></span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
								Click on <a href="#" style="color: #3973AC;" onfocus="this.blur()">CALIPER Across Canada</a>, to find a list of collaborating hospitals in Canada. Any child living anywhere in Canada or visiting Canada is welcome to participate in the project.
							</p>							
							</td>
						</tr>		
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold;"><i>Living inside of Toronto and would like to participate in the project? </i></span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
								Click on <a href="<%= MenuLink.howtoparticipate_clinics %>" style="color: #3973AC;" onfocus="this.blur()">CALIPER Clinics</a>. 
							</p>							
							</td>
						</tr>		
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold; font-size:12pt;"">For any questions about participating in the project, please contact the CALIPER Project Coordinator:</span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
								<ul>
									<li>Name: Jennifer Clarke</li>
									<li>Email: <a href="mailto:jennifer.clarke@sickkids.ca">jennifer.clarke@sickkids.ca</a></li>
									<li>Phone: 416-813-7654 ext. 2673</li>
									<li>Fax: 416-813-6257</li>
									<li>Mail: Department of Paediatric Laboratory Medicine, The Hospital for Sick Children              555 University Ave. Toronto, ON, M5G 1X8</li>
								</ul>
							</p>							
							</td>
						</tr>	
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold; font-size:12pt;"">Questionnaires:</span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
								<ul>
									<li><a href="pdf/21108-0907Questionnaire_birth-12mo.pdf" onfocus="this.blur()" title="">Questionnaire</a><br />(Birth - 12 months old)</li>
									<li><a href="pdf/21107-0907Questionnaire_1-18yrs.pdf" onfocus="this.blur()" title="">Questionnaire</a><br />(1 year old - 18 years old)</li>
								</ul>
							</p>							
							</td>
						</tr>	
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>					
							<span style="color: #3973AC; font-weight:bold; font-size:12pt;"">Consent Forms:</span>						
							</td>
						</tr>
						<tr><td colspan=4>
							<p>
								<ul>
									<li><a href="pdf/21109-0907ConsentForm_birth -15yrs.pdf" onfocus="this.blur()" title="">Consent Form</a><br />(Birth - 15 years old)</li>
									<li><a href="pdf/21110-0907ConsentForm_16-18yrs.pdf" onfocus="this.blur()" title="">Consent Form</a><br />(16 years old - 18 years old)</li>
								</ul>
							</p>							
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