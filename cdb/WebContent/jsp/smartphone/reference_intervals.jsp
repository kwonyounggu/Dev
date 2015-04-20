<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.SortedSet" %>
<%@ page import="java.util.TreeSet" %>

<script language="Javascript" type="text/javascript">

	function browseAanlyte(analyte)
	{
		if(analyte!=null)
		{
			httpRequestPost("<%= MenuLink.SMARTPHONE_CONTEXT %>","op=display_partition_rf&analyte="+analyte,"displayReferenceIntervals");
		}
	}
	function displayReferenceIntervals(strResponse)
	{	
		//alert(strResponse);
		removeRowFromTable("reference_interval_table");
		document.getElementById("display_reference_interval_table_span").innerHTML = strResponse;
	}
</script>

<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%">  
	<tr> 
		<td width=10></td> 
		<td  bgcolor=#ffffff valign="top" align="left"> 
			<table id="view_download_initial_table" border=0 cellspacing=0 cellpadding=4 width='100%' style="padding: 0 0 0 0px;"> 
				<tr>
				    <td colspan=4>
						<table height="40" width="300" >
							<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
									<span style="color: #1F5402; font-weight:bold; font-size:12pt; font-family: arial;">Pediatric Reference Intervals</span>
								</td>
								<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial; color: #ffffff;"></td>
							</tr>					
						</table>
						
					</td>
				</tr>
				<tr>
				    <td  colspan=4>
						<table border=0 cellspacing=0 cellpadding=0 width='98%' style="padding: 0 0 0 0px;"> 
							<tr><td colspan=4>&nbsp;
									<img src="images/common/arrow_dot.jpg" style="vertical-align: middle;"/>
									<span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;Analyte: </span>
									  <select id="analyte_id" onchange="browseAanlyte(this.value);">
										<option></option>
										<%  //do error handler
										
										SortedSet<String> sortedset= new TreeSet<String>(Utils.analyteMap.keySet());
										
									    Iterator<String> it = sortedset.iterator();

									    for(int i=0;it.hasNext();i++) 
									    {
									    	String key=it.next();
									        //System.out.println (key+" => "+Utils.analyteMap.get(key));
									        if(i==0)
									        	//out.print("<option onclick='browseAanlyte("+key+");' value='"+key+"' >"+Utils.analyteMap.get(key)+"</option>");
									        	out.print("<option value='"+key+"' >"+Utils.analyteMap.get(key)+"</option>");
									        else
									        	out.print("<option value='"+key+"' >"+Utils.analyteMap.get(key)+"</option>");
									    }
										%>
									</select>
								</td>
							</tr>
					 		<tr><td colspan=4><hr/></td></tr> 
							<tr>
								<td colspan=4>
									<span id="display_reference_interval_table_span"></span>
									<table id="reference_interval_table" border=0 cellspacing=2 cellpadding=0 width='100%' style="padding: 0 0 0 0px;"> 
										<tr>
											<td colspan=6></td> 
										</tr>
									</table>	
								</td>
							</tr> 
							
						 </table> 	
				    </td>
				</tr>
			</table> 	
		</td> 
		<td width=10 ></td> 
	</tr> 
</table> 
