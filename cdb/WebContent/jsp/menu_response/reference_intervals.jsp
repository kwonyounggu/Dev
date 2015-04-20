<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%@ page isELIgnored ="false" %>


<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td valign="top"></td></tr>
	<tr><td height="10"></td> </tr>
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
					<table id="view_download_initial_table" border=0 cellspacing=0 cellpadding=4 width='100%' style="padding-left: 10px; padding-right: 10px;"> 
						<tr>
						    <td colspan=4>								
								<table height="40" width="100%">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">CALIPER Reference Interval Tables</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;CALIPER Reference Intervals
										</td>
									</tr>	
									<tr><td colspan='2'><hr/></td></tr>				
								</table>
							</td>
						</tr>
						
						<tr>
						    <td  colspan=4 align='left'>
						    	<span class="title">Select the followings:</span>
								<div class="box" style="padding-left: 20px; padding-top: 10px; padding-bottom: 15px;">
								<table border=0 cellspacing=2 cellpadding=0 width='100%' style="padding-left: 10px; padding-right: 10px;"> 
									<tr><td colspan='3' align="left">																				
											<div>
												<img src="images/common/arrow_dot.jpg" style="vertical-align: middle;" />&nbsp;&nbsp;Instrument:&nbsp;
												<select id="instrument_name" name="instrument_name" onchange="onChangeInstrument(this.value);">
													<%  //do error handler
														List<InstrumentBean> instrumentList = (List<InstrumentBean>)application.getAttribute("instrumentList");
														for(InstrumentBean ib : instrumentList)
														{
															out.print("<option value='"+ib.getInstrument_name_id()+"' >"+ib.getInstrument_name()+"</option>");
															break;//remove if more instruments are available
														}
													%>				
												</select>&nbsp;&nbsp;<a href="<%=instrumentList.get(0).getInstrument_link()%>" target='_blank'>for more information?</a>
											</div>
											<div>
												<img src="images/common/arrow_dot.jpg" style="vertical-align: middle;" />&nbsp;&nbsp;Metric Units:&nbsp;
												<input type="radio" id="by_unit_si" name="metric_unit" value="1" onclick="onChangeMetricUnit(this.value);" checked/>SI
												<input type="radio" id="by_unit_us" name="metric_unit" value="2" onclick="onChangeMetricUnit(this.value);"/>Conventional
											</div>
											<div>
												<img src="images/common/arrow_dot.jpg" style="vertical-align: middle;" />&nbsp;&nbsp;Analyte:
											
												<select id="analyte_name" name="analyte_name" onchange="onChangeAnalyte(this.value);">
													<%  //do error handler
													List<AnalyteNameBean> newAnalyteList = (List<AnalyteNameBean>)request.getAttribute("newAnalyteList");
													for(AnalyteNameBean ab : newAnalyteList)
														out.print("<option value='"+ab.getAnalyte_name_id()+"|"+ab.getV2_chart_path()+"|"+ab.getAnalyte_name()+"|"+ab.getValue_unit()+"' >"+ab.getAnalyte_name()+"</option>");
													%>
												</select>		
											</div>																	
										</td>
										<td id="spinner_td">
											<img id="spinner_img" src="images/common/spinner.gif" width=32 height=32 style="vertical-align: middle;" /> <!-- Initial action right after loading this page -->
										</td>
									</tr>								
								 </table>
								 </div>	
						    </td>
						</tr>
						<tr>
						    <td  colspan=4 align='left' style='background-color:#ffffff;'>
						    	<div id="display_reference_intervals_data_graph"></div>
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

<script type="text/javascript">	
	(function($) 
	{
	    $.fn.invisible = function() 
	    {
	        return this.each(function() 
	        {
	            $(this).css("visibility", "hidden");
	        });
	    };
	    $.fn.visible = function() 
	    {
	        return this.each(function() 
	        {
	            $(this).css("visibility", "visible");
	        });
	    };
	}(jQuery));
</script>

<script language="Javascript" type="text/javascript">

	function getRadioValue(radioGroupName) 
	{
	    var radios = document.getElementsByName(radioGroupName);
		var returnValue="";
	    for (var i = 0; i < radios.length; i++) 
		{
	        if (radios[i].checked) 
		    {
	        	returnValue=radios[i].value;
	        	break;
		    }
	    }
	    //console.log("getRadioValue("+radioGroupName+") return value="+returnValue);
	    return returnValue;
	}
	function onChangeInstrument(instrument_id)
	{
		//not implemented yet because there exists only one instrument now
	}
	function onChangeMetricUnit(metric_unit_id)
	{
		//this.current_metric_system=metric_chosen;
		//getAanlyte_v2(current_analyte);
		//1. call analyte name list for the metric unit
		//2. when receiving is done, call onChangeAnalyte(1)
		try
		{
			$("#spinner_img").visible();
			httpRequestPost("<%= MenuLink.CONTEXT %>","op=ajax_get_new_analyte_list"+
													  "&instrument_id="+document.getElementById("instrument_name").value+
													  "&metric_unit_id="+metric_unit_id,"onChangeMetricUnitResponse");
		}
		catch(e)
		{
			alert(e);
			$("#spinner_img").invisible();
		}
	}
	function onChangeMetricUnitResponse(strResponse)
	{
		$("#spinner_img").invisible();
		if(strResponse.indexOf('false:')==0) //not found
		{
			alert(strResponse.substring(6)); //display the returned error message		
		}
		else
		{
			//display the given new analyte list to <select>'analyte_name'</select>
			$('#analyte_name').children().remove();//remove all options
			$('#analyte_name').html(strResponse);//put new options
			log("the first child="+document.getElementById("analyte_name").options[0].value);
			onChangeAnalyte(document.getElementById("analyte_name").options[0].value);//display the 1st one
		}
	}
	function onChangeAnalyte(analyte_value)
	{   //log("onChangeAnalyte(analyte_value) is called");
		try
		{
			if(analyte_value==="1") analyte_value=document.getElementById("analyte_name").options[0].value;
			var analyte=analyte_value.split("|");
			log("[0]="+analyte[0]);
			log("[1]="+analyte[1]);
			if(parseInt(analyte[0]) > 0)
			{
				$("#spinner_img").visible();
				httpRequestPost("<%= MenuLink.CONTEXT %>","op=ajax_get_analyte_data_graph"+
														  "&instrument_id="+document.getElementById("instrument_name").value+
														  "&metric_unit_id="+getRadioValue("metric_unit")+
														  "&analyte_id="+analyte[0]+
														  "&analyte_chart_path="+analyte[1]+
														  "&analyte_name="+analyte[2]+
														  "&paper_link="+analyte[3],"onChangeAnalyteResponse");
			}
		}
		catch(e)
		{
			alert(e);
			$("#spinner_img").invisible();
		}
	}
	function onChangeAnalyteResponse(strResponse)
	{
		$("#spinner_img").invisible();
		if(strResponse.indexOf('false:')==0) //not found
		{
			alert(strResponse.substring(6)); //display the returned error message		
		}
		else
		{
			$("#display_reference_intervals_data_graph").html(strResponse);
		}
	}
	onChangeAnalyte('1');	
</script>