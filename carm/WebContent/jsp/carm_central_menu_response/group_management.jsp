<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>

<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %> 
<%
	CarmSQLDao csqlDao=(CarmSQLDao)application.getAttribute("csqlDao");
 	
 	AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb");
 	List<AllReviewGroupBean> groupList=(List<AllReviewGroupBean>)csqlDao.getListFromAllReviewGroupTable("where app_id="+crb.getApp_id()+" order by group_name asc");	
 	AllReviewGroupBean gb=null;
 	AllApplicationBean ab=(AllApplicationBean)session.getAttribute("app_obj");
 	
 	if(groupList.size()>0) gb=groupList.get(0);
 %>

<script language="Javascript" type="text/javascript">
/*
 * Note: autofill = confirmatory in group level and point level
 */
 	
	$(document).ready(function () 
	{
		Date.prototype.addDays = function(noOfDays) 
		{
		    this.setTime(this.getTime() + (noOfDays * (1000 * 60 * 60 * 24)));
		    return this;
		}
		
		$("#jqx_timeout_date").jqxDateTimeInput({width: '110px', height: '20px', formatString: 'dd MMM yyyy', allowKeyboardDelete: false});
		<% 
		    if(gb==null)
				out.print("$('#jqx_timeout_date').jqxDateTimeInput('val',new Date().addDays("+Utils.group_review_timeout_offset*7+"));");
			else 
				out.print("$('#jqx_timeout_date').jqxDateTimeInput('val',new Date("+gb.getTime_out().getTime()+"));");
		%>
		
        // update the edited row when the user clicks the 'Save' button.
        $("#groupSubmit").click(function () 
        {
        	if(Number($("#reviewers_per_item").val())<Number($("#existing_reviewers_per_item").val()))
			{
				 alert("The reviewers per item should be >= the exiting value.");
			}
        	else if(Date.parse($("#jqx_timeout_date").jqxDateTimeInput('getText'))<Number(<%=Utils.nowPlus(1)%>))
        	{
        		alert("Group Time-Out is too tight to complete!");//within 1 day additional to the current time
        	}
        	else
        	{
    			document.getElementById("spinner_img").className="spinner_unhidden";
    			
    			$.ajax
	   		     ({
	   		         type: "post",
	   		         dataType: "",
	   		         url: "/carm/carm_controller?op=ajax_carm_central_submit_single_group_management",
	   		         data: 
	   		         {
	   		    	 		app_id:  				    "${crb.app_id}",
	   		    	 		group_id:					$("#group_id").val(),
	   		    	 		group_name:					"${app_obj.app_symbol}",
	   		    			time_out:					Date.parse($("#jqx_timeout_date").jqxDateTimeInput('getText')),
	   		    	 		reviewers_per_item:			$("#reviewers_per_item").val(),
	   		    	 		assignment_type:			$("#assignment_type").val(),
	   		    	 		confirmatory:				getRadioValue('confirmatoryRadio'),
	   		    	 		disagreement_process:		getRadioValue('toMediatorRadio')		    	 		
	   		     	 },
	   		         success: getGroupManagementResponse,
	   		         error: function(response) //called for 404 error, etc
	   		         {
	   		        	 alert(response.responseText);	
	   		             
	   		         }
	   		      }); 
        	}      	
        });
             
	});//$(document).ready
	function getGroupManagementResponse(strResponse)
	{
		document.getElementById("spinner_img").className="spinner_hidden";
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false:')==-1)//not found
		{
			location.reload();//so that the page will reload the saved data			
		}
		else 
		{
			alert(strResponse.substring(6));
		}
	}
</script>
<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td valign="top">
			
		</td>
	</tr>
	<tr><td align="center">
		<table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 0px 20px 20px 20px; "> 	
			<tr>
			    <td colspan=4 align="left">
					<table height="40" width="100%">
						<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
								<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
								<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Single Group Management</span>
							</td>
							<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
								<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.menu_carm_central_home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Group Management
							</td>
						</tr>	
						<tr><td colspan=2><hr/></td></tr>				
					</table>					
				</td>
			</tr>
			<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
				 <div id="groupManagementDiv">
			            <div style="overflow: hidden;">
			                <table  cellpadding='2' cellspacing="4" width='100%' style="padding: 20px 20px 20px 20px;">
			                	<tr><td colspan='2' align="center">
				                		<p>
				                		<% 
				                			if(gb!=null) 
				                			{
				                				out.print("<b>The most recent group data that has been saved!</b>");
				                				out.print("<input type='hidden' id='group_id' value='"+gb.getGroup_id()+"'/>");
				                			}
				                			else 
				                			{
				                				out.print("<span style='color: #CB1A1A;'><b>The group has not defined yet!</b></span>");
				                				out.print("<input type='hidden' id='group_id' value='-1'/>");
				                			}
				                		%>
				                		</p>
			                		</td>
			                	</tr>
			                	<tr height='15'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="43%">Group Time-Out:</td>
			                        <td align="left"> <div id='jqx_timeout_date'/>										  
			                        </td>
			                    </tr>
			                    <tr height='15'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="43%">Reviewers per item:</td>
			                        <td align="left">
			                        	<select id="reviewers_per_item">		
										  	<option value='1' <%=(gb==null? (ab.getReviewers_per_item()==1? "selected":""): (gb.getReviewers_per_item()==1? "selected":"")) %>>1</option>
										  	<option value='2' <%=(gb==null? (ab.getReviewers_per_item()==2? "selected":""): (gb.getReviewers_per_item()==2? "selected":"")) %>>2</option>
										  	<option value='3' <%=(gb==null? (ab.getReviewers_per_item()==3? "selected":""): (gb.getReviewers_per_item()==3? "selected":"")) %>>3</option>
										  	<option value='4' <%=(gb==null? (ab.getReviewers_per_item()==4? "selected":""): (gb.getReviewers_per_item()==4? "selected":"")) %>>4</option>
			                        		<option value='10' <%=(gb==null? (ab.getReviewers_per_item()==10? "selected":""): (gb.getReviewers_per_item()==10? "selected":"")) %>>&lt;10</option>
			                        		<option value='20' <%=(gb==null? (ab.getReviewers_per_item()==20? "selected":""): (gb.getReviewers_per_item()==20? "selected":"")) %>>&lt;20</option>
			                        		<option value='10000' <%=(gb==null? (ab.getReviewers_per_item()==10000? "selected":""): (gb.getReviewers_per_item()==10000? "selected":"")) %>>&ge;20</option>													 
										</select>
										<input type='hidden' id='existing_reviewers_per_item' value='<%=(gb==null? -1: gb.getReviewers_per_item())%>'/>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="43%">Assignment type:</td>
			                        <td align="left">
			                        	<select id="assignment_type">		
										  	<option value='by_group' <%=(gb==null? (ab.getReviewer_assignment_type().equals("by_group")? "selected":""): (gb.getReviewer_assignment_type().equals("by_group")? "selected":"")) %>>By Group</option>
										  	<option value='by_item'  <%=(gb==null? (ab.getReviewer_assignment_type().equals("by_item")? "selected":""): (gb.getReviewer_assignment_type().equals("by_item")? "selected":"")) %>>By Item</option>													 
										</select>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="43%">Confirmatory:</td>
			                        <td align="left">
			                        			<input type='radio' id='confirmatoryNo' name='confirmatoryRadio' value='false' <%=(gb==null? (ab.isConfirmatory()? "":"checked"): (gb.isConfirmatory()? "":"checked")) %>>No</input>
			                        			<input type='radio' id='confirmatoryYes' name='confirmatoryRadio' value='true' <%=(gb==null? (ab.isConfirmatory()? "checked":""): (gb.isConfirmatory()? "checked":"")) %>>Yes</input>		                        			
			                        </td>
			                    </tr>

								<tr>
			                        <td align="right" width="43%">Disagreement process:</td>
			                        <td align="left">
			                        			<input type='radio' id='toMediatorNo' name='toMediatorRadio' value='peer_to_peer'  <%=(gb==null? (ab.getDisagreement_process().equals("peer_to_peer")? "checked":""): (gb.getDisagreement_process().equals("peer_to_peer")? "checked":"")) %>>Peer to Peer</input>
			                        			<input type='radio' id='toMediatorYes' name='toMediatorRadio' value='peer_to_mediator' <%=(gb==null? (ab.getDisagreement_process().equals("peer_to_mediator")? "checked":""): (gb.getDisagreement_process().equals("peer_to_mediator")? "checked":"")) %>>Peer to Mediator</input>
			                        </td>
			                    </tr>
			                    
			                    <tr><td colspan='2' align='center'><img id='spinner_img' src='images/common/spinner.gif' width=32 height=32  class="spinner_hidden"/></td></tr>
			                    <tr>
			                        <td colspan='2' style="padding-top: 10px;" align="center">
			                        	<input style="margin-right: 5px;" type="button" id="groupSubmit" value="Submit" />
									</td>
			                    </tr>
			                </table>
			            </div>
			       </div>
										
				</td>
			</tr>			
		</table>
 	</td>
	</tr>
</table>  
