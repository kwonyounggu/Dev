<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %> 
<%@ page isELIgnored ="false" %>
<%
	CarmSQLDao csqlDao=(CarmSQLDao)application.getAttribute("csqlDao");
%>
<script language="Javascript" type="text/javascript" src="js/menu_page/create_login_account.js"></script>
<script type="text/javascript">
		$(document).ready(function () 
	    {
			$("#jqx_access_limit_date").jqxDateTimeInput({width: '110px', height: '20px', formatString: 'dd MMM yyyy', value: new Date(<%=Utils.login_access_limit_date.getTime()%>), min: new Date(), allowKeyboardDelete: false});
	    });
</script>
<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td valign="top"></td></tr>
	<tr><td align="center">
			<table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 0px 5px 20px 5px; "> 	
				<tr>
				    <td colspan=4 align="left">
						<table height="40" width="100%">
							<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
									<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
									<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Create Login Account</span>
								</td>
								<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
									<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.menu_carm_central_home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Admin&nbsp;&nbsp;>&nbsp;&nbsp;Create Login Account
								</td>
							</tr>	
							<tr><td colspan=2><hr/></td></tr>				
						</table>					
					</td>
				</tr>
				<tr>
				    <td colspan=4 align="center">
						<p><b>Welcome to 'Create Login Account'</b></p>
						<p><b>You are going to create a new login account by following the simple steps.</b></p>
					</td>
				</tr>
				<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;"><p></p>					
						<div id='create_login_account_body'>
						   <form id="create_login_account_form">
							<table  cellpadding='2' cellspacing="4" width='100%' style="padding: 20px 20px 20px 20px;"> 																					
								<tr><td align="right">First Name: </td><td colspan=4  align="left"><input type='text' id="first_name" size=38 value='' maxlength="80" class=input_text style='padding-left: 2px;imemode:inactive' placeholder="Your first name here please!"  pattern="[A-Za-z]{3,}" title="At least 3 characters" required/></td></tr>	
								<tr><td align="right">Last Name: </td><td colspan=4  align="left"><input type='text' id="last_name" size=38 value='' maxlength="80" class=input_text style='padding-left: 2px;imemode:inactive' placeholder="Your last name here please!"  pattern="[A-Za-z]{3,}" title="At least 3 characters" required/></td></tr>	
								<tr><td align="right">E-Mail: </td><td colspan=4  align="left"><input type='email' id="email" size=58 value='' maxlength="255" class=input_text style='padding-left: 2px;imemode:inactive' placeholder="Your full email here please!" required/></td></tr>											
								<tr><td align="right">Login Level:</td><td colspan=4  align="left">
										<select id="login_level" onchange="loginLevelChange(this.value)">															 
											<%
											//do error handler
												AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb");
												System.out.println("login_level="+crb.getLogin_level());
												List<AllLoginLevelBean> loginLievelList=(List<AllLoginLevelBean>)application.getAttribute("cloginLevelList");
																						
												for(AllLoginLevelBean cb : loginLievelList)	
													switch(cb.getLoginLevel())
													{
														case 1: break;
														case 2: if(crb.getLogin_level()<2)
																	out.print("<option value='"+cb.getLoginLevel()+"'>"+cb.getLoginLevelDescription()+"</option>");
																break;
														case 3: //Review Mediator
														case 4: out.print("<option value='"+cb.getLoginLevel()+"'>"+cb.getLoginLevelDescription()+"</option>");//Data Reveiwer
																break;
														case 5: break;//not used
														default: break;
													}
												
											%>
										</select>
									</td>
									
								</tr>

								<tr id="max_review_groups_tr"><td align="right">Max review groups:</td><td colspan=4  align="left">
										<select id="max_review_groups">		
										  <%
										  	int num_groups=Integer.parseInt(csqlDao.getGenericString("select number_of_groups from all_application where app_id="+crb.getApp_id()));
										  	for(int i=1;i<=num_groups;i++)
										  		out.print("<option value='"+i+"' "+(i==num_groups ? "selected":"")+">"+i+"</option>");
										  %>													 
										</select>
									</td>				
								</tr>
								<tr id="item_assignment_type_tr"><td align="right">Item Assignment Type:</td><td colspan=4  align="left">
										<select id="item_assignment_type">															 
											<option value='by_group'>By Group</option>
											<option value='by_item'>By Item</option>
										</select>
									</td>
								</tr>
								<!-- The following tr will not be used since confirmatory auto fill is in group level and group-item-reviewer assignment -->
								<tr id="review_confirmatory_tr" style="display:none;"><td align="right">Review Confirmatory:</td><td colspan=4  align="left">
										<select id="review_confirmatory">															 
											<option value='1'>Yes</option>
											<option value='0'>No</option>
										</select>
									</td>
								</tr>
								<tr id="extension_2" ><td align="right">Access Limit Date: </td><td colspan=4  align="left">
										<div id="jqx_access_limit_date"/>
									</td>
								</tr>	
								<!--come back here: put more fields only for reviewer such as practice year, health discipline, primary clinicial practice, seniority -->
								<tr><td align="right" valign="top">Practice Year: </td>
									<td colspan=4  align="left" valign="top">
										<select id="practice_year">
											<option value="<0" >&lt;0</option>
											<option value="0-2" >0-2</option>
											<option value="3-5" >3-5</option>
											<option value="6-10" >6-10</option>
											<option value=">10" >&gt;10</option>								
										</select>
									</td>
								</tr>
								<tr><td align="right" valign="top">Health Discipline: </td>
									<td colspan=4  align="left" valign="top">
										<select id="health_discipline">
											<option value="Pharmacy" >Pharmacy</option>
											<option value="Pharmacology" >Pharmacology</option>
											<option value="Nursing" >Nursing</option>
											<option value="Respiratory therapy" >Respiratory therapy</option>
											<option value="Medicine (physician)" >Medicine (physician)</option>	
											<option value="Medical record abstractor" >Medical record abstractor</option>	
											<option value="Other (outside healthcare profession)" >Other (outside healthcare profession)</option>								
										</select>
									</td>
								</tr>
								<tr><td align="right" valign="top">Primary Clinical Practice: </td>
									<td colspan=4  align="left" valign="top">
										<select id="primary_clinical_practice">
											<option value="Adult" >Adult</option>
											<option value="<18 years paediatric" ><18 years paediatric</option>
											<option value="Mixed adult-geriatric" >Mixed adult-geriatric</option>
											<option value="Adult-paediatric" >Adult-paediatric</option>
										</select>&nbsp;(not in practice)
									</td>
								</tr>
								<tr><td align="right" valign="top">Seniority: </td>
									<td colspan=4  align="left" valign="top">
										<select id="seniority">
											<option value="0" >0 (Lowest junior level)</option>
											<option value="1" >1</option>
											<option value="2" >2</option>
											<option value="3" >3</option>
											<option value="4" >4</option>
											<option value="5" >5 (Lowest senior level)</option>	
											<option value="6" >6</option>
											<option value="7" >7</option>
											<option value="8" >8</option>
											<option value="9" >9</option>
											<option value="10" >10 (Highest senior level)</option>							
										</select>
									</td>
								</tr>							
								<tr><td></td><td colspan=4 align="left"><br/><input type=submit id="submitButton" style='cursor:pointer;' value='Submit' /></td></tr>											 										
							 </table>
						   </form>
							<img id='spinner_img' src='images/common/spinner.gif' width=32 height=32  class="spinner_hidden" onload="initializeThisPage(<%=crb.getLogin_level()%>);"/>
						 </div>		
																							
					</td>
				</tr>				
			</table>				
 		</td>
	</tr>
</table>  