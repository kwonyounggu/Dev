<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*;" %>


<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td height="10"></td> </tr>
	<tr><td align="center">
		<div id="qa_write_div"></div>
		<div id="qa_table_div">
		<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%"> 
			<tr> 
				<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
				<td height=10 background='images/table/horizontal_top.gif'></td> 
				<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
			</tr> 
			<tr> 
				<td width=10 background='images/table/vertical_left.gif'></td> 
				<td  bgcolor=#ffffff valign="top" align="left"> 
					<table border=0 cellspacing=2 cellpadding=4 width='100%' style="padding: 0 0 0 10px;"> 
						<tr>
						    <td colspan=4>
								<table height="40" width="100%">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">Frequently Asked Questions</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;FAQ
										</td>
									</tr>	
									<tr><td colspan='2'><hr/></td></tr>				
								</table>
							</td>
						</tr>
						<tr><td colspan=4 height=1 class=dv_line></td></tr>
						<tr>
						    <td colspan=4>
									<table width="100%" cellspacing="20" cellpadding="0">
										<tr>
											<td width="50%" >
												<p>
													<b>What are CALIPER’s goals?</b><br/>
													CALIPER aims to improve the diagnosis and treatment of children all across Canada. We are trying to determine what is normal and healthy when a child is screened for a disease. Our findings are used by doctors in order to compare healthy and normal blood levels to those of their sick patients.
								
												</p>
												<p>
													<b>Can I see my child’s blood results?</b><br/>
													In a case where we find something of concern or abnormal with your child’s blood then your family physician will be contacted. They are able to share that information with you and choose the appropriate action to take.
												</p>
												<p>
													<b>Does it hurt?</b><br/>
													This varies with each child. Some say it feels like a mosquito bite and others say it feels like a pinch. We use a needle called the butterfly needle which are used on babies when treated at hospitals. It is the smallest type of needle and pain is minimal.
												</p>
												<p>
													<b>How much blood do you take?</b><br/>
													The amount of blood varies with age but ranges from 1mL (newborns and infants) to 10mL (11-18 years of age). Generally, it is approximately a tablespoon of blood.
												</p>
												<p>
													<b>What kind of tests are done to the blood?</b><br/>
													So far, we test for 67 levels within the blood. This includes hormones, vitamins, minerals, proteins, amino acids, etc. These levels ultimately look at serum chemistry, enzymes, proteins, and lipid analytes.
								
												</p>
												<p>
													<b>Where can I see results?</b><br/>
													You can find the results of our pediatric reference intervals on www.CALIPERdatabase.com. This information is being used by doctors across Canada and all over the world.
												</p>
												<p>
													<b>Can my child donate at McMaster and not at a clinic?</b><br/>
													Yes you certainly can. If you leave us your information we can contact you and make an appointment to donate here at the hospital within the lab.
												</p>
												<p>
													<b>How long is the study going on for?</b><br/>
													This is an ongoing project that is expanding throughout Canada. We are trying to obtain a true representative of the Canadian youth population. Having as many children as possible participate will increase the accuracy of our results.
												</p>
											</td>
											<td width="50%" valign="top">
												<p>
													<b>My daughter is diabetic, can she still donate?</b><br/>
													Yes, of course! We take blood from any child who wishes to donate. However, her blood may not be used towards our pediatric reference database since it is not “normal” but may be used in future tests to help other children with illnesses.
												</p>
												<p>
													<b>I have donated before, can I donate again?</b><br/>
													Unfortunately, no. This is a one-time donation for participants.
												</p>
												<p>
													<b>Who draws the blood?</b><br/>
													We have fully trained phlebotomists who are apart of the CALIPER team and employed by SickKids Hospital that will draw the blood. These are people who are experienced with childrenand are professionals in their field.
												</p>
												<p>
													<b>Who will have access to the CALIPER database?</b><br/>
													Our database is accessible to everyone including yourself. You can access it at www.CALIPERdatabase.com.
												</p>
												<p>
													<b>Is there a possibility that my child will feel sick after donating?</b><br/>
													Not likely. Most children do not feel sick after donating since it is a small amount which is taken. However, some children may feel anxious, scared and nervous and sometimes feel faint or nauseas after donating. These reactions usually only last minutes but rarely occur. Generally, children are able to return to their normal daily activities since there is no rest period after donating.
												</p>
												<p>
													<b>If my child does not want to partake in donating, can they withdraw?</b><br/>
													Yes. The child can withdraw from participating at any time. They will still receive their gifts for being brave enough for trying!
												</p>
												<p>
													<b>Will my child’s information be confidential?</b><br/>
													Yes. All data entered will be confidential and results published are anonymous.
												</p>
												<p>
													<b>How long does this process take?</b><br/>
													Usually the whole process takes about 10 minutes. However, the needle is only in the child for about 30 seconds to a minute.
												</p>
												<p>
													<b>Does my child have to fast before blood collection?</b><br/>
													No, we require the child to eat a substantial amount of food, juices and water prior to donating. No fasting is necessary.
												</p>
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
		</div>
 	</td>
	</tr>
</table>  
</div>