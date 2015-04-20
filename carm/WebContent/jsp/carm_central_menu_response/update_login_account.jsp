<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>
 
<script language="Javascript" type="text/javascript" src="js/menu_page/create_login_account.js"></script>

<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td valign="top"></td></tr>
	<tr><td align="center">
					<table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 0px 20px 20px 20px; "> 	
						<tr>
						    <td colspan=4 align="left">
								<table height="40" width="100%">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
											<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Update Login Account</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.menu_carm_central_home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Admin&nbsp;&nbsp;>&nbsp;&nbsp;Update Login Account
										</td>
									</tr>	
									<tr><td colspan=2><hr/></td></tr>				
								</table>					
							</td>
						</tr>
						<tr>
						    <td colspan=4 align="center">
								<p><b>Welcome to 'Update Login Account'</b></p>
								<p><b>You are going to update an existing login account by following the simple steps.</b></p>
							</td>
						</tr>
						<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;"><p></p>					
								<div id='update_login_account_body'>
									<form id="update_login_account_form">
									<table  id='update_login_account_table' cellpadding='2' cellspacing="4" width='100%' style="padding: 20px 20px 20px 20px;"> 
								 		<tr><td align="right">&nbsp;Login Account ID: </td><td colspan=4 align="left">												  
											  <select id="login_account_id" onchange="loginAccountEvent(this.value);">
												<%
													//do error handler
																							CarmSQLDao sqlDao=(CarmSQLDao)application.getAttribute("csqlDao");
																							
																							
																							AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb");
																							List<AllRegisteredUserBean> accountList=(List<AllRegisteredUserBean>)request.getAttribute("account_list");
																							AllRegisteredUserBean first_rb=null;
																							boolean only_first=true;
																							for(int i=0;i<accountList.size();i++)
																							{	
																								AllRegisteredUserBean rb=(AllRegisteredUserBean)accountList.get(i);
																								if(rb.getLogin_level()<=crb.getLogin_level()) continue;
																								if(only_first)
																								{
																									first_rb=rb;
																									request.setAttribute("cs_rb",rb);//<-------------------
																									out.print("<option value='"+rb.getUser_id()+"' selected>"+rb.getUser_id()+"</option>");
																									only_first=false;
																								}
																								else
																									out.print("<option value='"+rb.getUser_id()+"'>"+rb.getUser_id()+"</option>");																												
																							}
												%>
												</select>
											</td>
											
										</tr> 		
										<tr><td align="right">&nbsp;First Name: </td><td colspan=4  align="left"><input type='text' id="first_name" size=38 value='${cs_rb.first_name}' maxlength="80" class=input_text style='padding-left: 2px;imemode:inactive' disabled/></td></tr>	
										<tr><td align="right">&nbsp;Last Name: </td><td colspan=4  align="left"><input type='text' id="last_name" size=38 value='${cs_rb.last_name}' maxlength="80" class=input_text style='padding-left: 2px;imemode:inactive' disabled/></td></tr>	
										<tr><td align="right">E-Mail: </td><td colspan=4  align="left"><input type='email' id="email" size=58 value='${cs_rb.email}' maxlength="255" class=input_text style='padding-left: 2px;imemode:inactive' placeholder="Your full email here please!" required/></td></tr>											
										<tr><td align="right">&nbsp;Login Level:</td><td colspan=4  align="left">
												<select id="login_level" disabled>															 
												<%
													//do error handler
													List<AllLoginLevelBean> loginLievelList=(List<AllLoginLevelBean>)application.getAttribute("cloginLevelList");																						
													for(int i=0;i<loginLievelList.size();i++)
													{
														AllLoginLevelBean lb=(AllLoginLevelBean)loginLievelList.get(i);
														if(lb.getLoginLevel()==1) continue;
														if(lb.getLoginLevel()==((AllRegisteredUserBean)request.getAttribute("cs_rb")).getLogin_level())
															out.print("<option value='"+lb.getLoginLevel()+"' selected>"+lb.getLoginLevelDescription()+"</option>");	
														else
															out.print("<option value='"+lb.getLoginLevel()+"'>"+lb.getLoginLevelDescription()+"</option>");	
													}
												%>
												</select>
											</td>
											
										</tr>
										
										<tr id="max_review_groups_tr"><td align="right">Max review groups:</td><td colspan=4  align="left">
												<select id="max_review_groups">		
												  <%
												  	int num_groups=Integer.parseInt(sqlDao.getGenericString("select number_of_groups from all_application where app_id="+crb.getApp_id()));
												    AllRegisteredUserBean selected_rb=(AllRegisteredUserBean)request.getAttribute("cs_rb");
												  	for(int i=1;i<=num_groups;i++)
												  		out.print("<option value='"+i+"' "+(i==selected_rb.getMax_groups_to_review() ? "selected":"")+">"+i+"</option>");
												  %>													 
												</select>
												<input type="hidden" id="hidden_max_review_groups" value="${cs_rb.max_groups_to_review}">
											</td>				
										</tr>
										<tr id="item_assignment_type_tr"><td align="right">Item Assignment Type:</td><td colspan=4  align="left">
												<select id="item_assignment_type">															 
													<option value='by_group' <%=selected_rb.getItem_assignment_type().equals("by_group")? "selected" : ""%>>By Group</option>
													<option value='by_item' <%=selected_rb.getItem_assignment_type().equals("by_item")? "selected" : ""%>>By Item</option>
												</select>
											</td>
										</tr>
										<tr id="review_confirmatory_tr" style="display:none;"><td align="right">Review Confirmatory:</td><td colspan=4  align="left">
												<select id="review_confirmatory">															 
													<option value='1' <%=selected_rb.isMy_review_confirmatory()? "selected" : ""%>>Yes</option>
													<option value='0' <%=selected_rb.isMy_review_confirmatory()? "" : "selected"%>>No</option>
												</select>
											</td>
										</tr>
										<tr id="extension_2" ><td align="right">Access Limit Date: </td><td colspan=4  align="left">
												<input type='hidden' id="user_id" size=58 value='${cs_rb.user_id}' maxlength="256" class=input_text style='imemode:inactive' />
												<div id="jqx_access_limit_date"/>
											</td>
										</tr>	
										
										
								<tr><td align="right" valign="top">Practice Year: </td>
									<td colspan=4  align="left" valign="top">
										<select id="practice_year">
											
											<%
												String practice_year=((AllRegisteredUserBean)request.getAttribute("cs_rb")).getPractice_year();
											%>		
											<option value="<0" <%=practice_year.equals("<0")? "selected" : ""%> >&lt;0</option>
											<option value="0-2"  <%=practice_year.equals("0-2")? "selected" : ""%> >0-2</option>
											<option value="3-5"  <%=practice_year.equals("3-5")? "selected" : ""%> >3-5</option>
											<option value="6-10"  <%=practice_year.equals("6-10")? "selected" : ""%> >6-10</option>
											<option value=">10"  <%=practice_year.equals(">10")? "selected" : ""%> >&gt;10</option>						
										</select>
									</td>
								</tr>
								<tr><td align="right" valign="top">Health Discipline: </td>
									<td colspan=4  align="left" valign="top">
										<select id="health_discipline">
											<%
												String health_discipline=((AllRegisteredUserBean)request.getAttribute("cs_rb")).getHealth_discipline();
											%>	
											<option value="Pharmacy"   <%=health_discipline.equals("Pharmacy")? "selected" : ""%> >Pharmacy</option>
											<option value="Pharmacology"   <%=health_discipline.equals("Pharmacology")? "selected" : ""%> >Pharmacology</option>
											<option value="Nursing"   <%=health_discipline.equals("Nursing")? "selected" : ""%> >Nursing</option>
											<option value="Respiratory therapy"   <%=health_discipline.equals("Respiratory therapy")? "selected" : ""%> >Respiratory therapy</option>
											<option value="Medicine (physician)"   <%=health_discipline.equals("Medicine (physician)")? "selected" : ""%> >Medicine (physician)</option>	
											<option value="Medical record abstractor"   <%=health_discipline.equals("Medical record abstractor")? "selected" : ""%> >Medical record abstractor</option>	
											<option value="Other (outside healthcare profession)"   <%=health_discipline.equals("Other (outside healthcare profession)")? "selected" : ""%> >Other (outside healthcare profession)</option>								
										</select>
									</td>
								</tr>
								<tr><td align="right" valign="top">Primary Clinical Practice: </td>
									<td colspan=4  align="left" valign="top">
										<select id="primary_clinical_practice">
											<%
												String primary_clinical_practice=((AllRegisteredUserBean)request.getAttribute("cs_rb")).getPrimay_clinical_practice();
											%>	
											<option value="Adult"   <%=primary_clinical_practice.equals("Adult")? "selected" : ""%> >Adult</option>
											<option value="<18 years paediatric"   <%=primary_clinical_practice.equals("<18 years paediatric")? "selected" : ""%> ><18 years paediatric</option>
											<option value="Mixed adult-geriatric"   <%=primary_clinical_practice.equals("Mixed adult-geriatric")? "selected" : ""%> >Mixed adult-geriatric</option>
											<option value="Adult-paediatric"   <%=primary_clinical_practice.equals("Adult-paediatric")? "selected" : ""%> >Adult-paediatric</option>
											
										</select>&nbsp;(not in practice)
									</td>
								</tr>
								<tr><td align="right" valign="top">Seniority: </td>
									<td colspan=4  align="left" valign="top">
										<select id="seniority">
											<%
												int seniority=((AllRegisteredUserBean)request.getAttribute("cs_rb")).getSeniority();
											%>	
											<option value="0"   <%=seniority==0 ? "selected" : ""%> >0 (Lowest junior level)</option>
											<option value="1"   <%=seniority==1 ? "selected" : ""%> >1</option>
											<option value="2"   <%=seniority==2 ? "selected" : ""%> >2</option>
											<option value="3"   <%=seniority==3 ? "selected" : ""%> >3</option>
											<option value="4"   <%=seniority==4 ? "selected" : ""%> >4</option>
											<option value="5"   <%=seniority==5 ? "selected" : ""%> >5 (Lowest senior level)</option>	
											<option value="6"   <%=seniority==6 ? "selected" : ""%> >6</option>
											<option value="7"   <%=seniority==7 ? "selected" : ""%> >7</option>
											<option value="8"   <%=seniority==8 ? "selected" : ""%> >8</option>
											<option value="9"   <%=seniority==9 ? "selected" : ""%> >9</option>
											<option value="10"   <%=seniority==10 ? "selected" : ""%> >10 (Highest senior level)</option>	
																
										</select>
									</td>
								</tr>							
								<tr><td></td><td colspan=4 align="left"><br/><input type=submit id="submitButton" style='cursor:pointer;' value='Submit' /></td></tr>														 										
								</table>
								</form><img id='spinner_img' src='images/common/spinner.gif' width=32 height=32  class="spinner_hidden" onload="initializeUpdatePage(<%=selected_rb.getLogin_level()%>);"/>
							</div>		
																									
							</td>
						</tr>				
					</table> 
 	</td>
	</tr>
</table>  
<script type="text/javascript">
	$(document).ready(function () 
    {
		$("#jqx_access_limit_date").jqxDateTimeInput({width: '110px', height: '20px', formatString: 'dd MMM yyyy', value: new Date(${cs_rb.access_limit_time_long}), min: new Date(), allowKeyboardDelete: false});
    });
</script>