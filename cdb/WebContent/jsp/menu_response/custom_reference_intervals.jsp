<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>

<table cellpadding="0" cellspacing="0" width="100%" height="100%"> 
	<tr><td>
		<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%" align="left"> 
			<tr> 
				<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
				<td height=10 background='images/table/horizontal_top.gif'></td> 
				<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
			</tr> 
			<tr> 
				<td width=10 background='images/table/vertical_left.gif'></td> 
				<td  bgcolor=#ffffff valign="top"> 
					<span id="custom_search_initial_table_span"></span>
					<table id="custom_search_initial_table" border=0 cellspacing=0 cellpadding=4 width='100%' style="padding-top: 0px; padding-bottom: 0px;" align="left" >
						<tr>
						    <td colspan=4 align="left">
								<table height="40" width="100%">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">CALIPER Database Custom Search</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;CALIPER Database&nbsp;&nbsp;>&nbsp;&nbsp;CALIPER Database Custom Search
										</td>
									</tr>	
									<tr><td colspan='2'><hr/></td></tr>				
								</table>
							</td>
						</tr>
 
						<tr>
						    <td  colspan=4 align='center' id="flashContent">
								<div >
									<script type="text/javascript">  
																		
	 									var params = {scale: "exactFit"}; 
	 								    params.quality = "high"; 
	 								    params.bgcolor = "#ffffff"; 
	 								    params.allowscriptaccess = "sameDomain"; 
	 								    var attributes = {}; 
	 								    attributes.id = "TestProject"; 
	 								    attributes.name = "TestProject"; 
	 								    attributes.align = "middle"; 
 										var flashvars={host:"<%=request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort())%>"}
 										swfobject.embedSWF("cdb.swf", "myContent", "100%", "930", "10.0.0", "expressInstall.swf", flashvars, params, attributes,outputStatus);
 										
 										function resizeFlash(height)
 										{
 											console.log("New Height="+height);											
 										}
 										function outputStatus(e) 
 										{
 											console.log("e.success = " + e.success +"\ne.id = "+ e.id +"\ne.ref = "+ e.ref);
 										}
									</script> 
								</div>
								<div id="myContent"> 
									<h1>Alternative content</h1> 
									<p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" /></a></p> 
								</div> 
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