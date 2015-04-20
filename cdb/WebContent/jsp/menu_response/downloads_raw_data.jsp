<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="java.util.Iterator" %>

<%@ page import="java.util.SortedSet" %>
<%@ page import="java.util.TreeSet" %>

<script language="Javascript" type="text/javascript">

</script>
<table cellpadding="0" cellspacing="0" width="100%"> 
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
					<table id="custom_search_initial_table" border=0 cellspacing=0 cellpadding=4 width='100%' style="padding: 0 0 0 20px;" align="left" >
						<tr>
						    <td colspan=4 align="left">
								<table height="40" width="710" background="images/common/title.gif">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<img src="images/common/download_24x24.png" style="vertical-align: middle;"/><span style="color: #ffffff; font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;&nbsp;&nbsp;Raw Data Download</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial; color: #ffffff;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Raw Data Download
										</td>
									</tr>					
								</table>
							</td>
						</tr>
						<tr><td colspan=4 align="left">
							<span style="margin-left:5em; color: #616161; font-weight:normal; font-size:12pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">
								  <p>This section provides you to download a full-raw data including 41 analytes and partial-raw data for each analyte in MS-Excel format.</p>
							</span>						
						</td></tr> 
						<tr>
						    <td  colspan=4 align="left">
								<table border=0 cellspacing=2 cellpadding=0 width='98%' align="left" style="padding: 0 0 0 0px;" > 
									<tr><td colspan=4>&nbsp;&nbsp;<img src="images/common/arr3.jpg" style="vertical-align: middle;"/><span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Download full raw data or partial raw data by the name of Analyte</span></td></tr>
									<tr><td colspan=4 height=1 class=dv_line3></td></tr> 
									<tr>
										<td class=header2_1_td width="40%">Analyte</td> 
										<td class=header2_1_td width="20%">Release Date</td>
										<td class=header2_1_td width="20%">File Size</td>
										<td class=header2_1_td width="20" ></td>
									</tr>
									<tr style='background-color:#EAF0FF;'>
										<td align='left' style='background-color:#F0F0DC;' width='40%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ALL (full raw data download)</td>
										<td class=center_td width='20%'>June 2011</td>
										<td class=center_td width='20%'>600kb</td>
										<td class=center_td width='20%'><a onfocus="this.blur()" href="csv/all.csv" style="color: #800000;" onclick="if(check_login()) return true; else return false;">download</a></td>
									</tr>
										<%  //do error handler
											SortedSet<String> sortedset= new TreeSet<String>(Utils.analyteMap.keySet());
	
										    Iterator<String> it = sortedset.iterator();
	
										    while (it.hasNext()) 
										    {
										    	String key=it.next();
										        //System.out.println (key+" => "+Utils.analyteMap.get(key));
										        out.print("<tr style='background-color:#EAF0FF;'>");
												out.print("<td align='left' style='background-color:#F0F0DC;' width='40%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+Utils.analyteMap.get(key)+"</td>"+
														  "<td class=center_td width='20%'>"+"June 2011"+"</td>"+
														  "<td class=center_td width='20%'>"+"80kb"+"</td>"+
														  "<td class=center_td width='20%'><a onfocus='this.blur()' href='csv/"+key+".csv' style='color: #800000;' onclick='if(check_login()) return true; else return false;'>download</a></td>");
												out.print("</tr>");
										    }
										%>
								 </table> 	
						    </td>						
						</tr>
						<tr><td colspan=4 height=10>
						<tr><td colspan=4 align="left">
							<table border=0 cellspacing=2 cellpadding=0 width='98%' align="left" style="padding: 0 0 0 0px;" > 
								<tr><td colspan=4>&nbsp;&nbsp;<img src="images/common/arr3.jpg" style="vertical-align: middle;"/><span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Gender Codes being used in the raw data</span></td></tr>
								<tr><td colspan=4 height=1 class=dv_line3></td></tr> 
								<tr>
									<td colspan=4>
										<ul>
											<li><b>1 Male</b></li>
											<li><b>2 Female</b></li>
										  </ul>
									</td>
								</tr>
							</table>			
						</td></tr> 
						<tr><td colspan=4 height=10>
						<tr><td colspan=4 align="left">
							<table border=0 cellspacing=2 cellpadding=0 width='98%' align="left" style="padding: 0 0 0 0px;" > 
								<tr><td colspan=4>&nbsp;&nbsp;<img src="images/common/arr3.jpg" style="vertical-align: middle;"/><span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Ethnicity Codes being used in the raw data</span></td></tr>
								<tr><td colspan=4 height=1 class=dv_line3></td></tr> 
								<tr>
									<td colspan=4>
										<ul>
											<li><b>1 Aboriginal</b> (eg. Inuit, Metis, North Americal Indian)</li>
											<li><b>2 Arab/West Asian/Persian</b> (eg. Armenian, Egyptian, Iranian, Lebanese, Moroccan)</li>
											<li><b>3 Black</b> (eg. African, Haitian, Jamaican, Somali)</li>
											<li><b>4 Chinese</b></li>
											<li><b>5 Filipino</b></li>
											<li><b>6 Japanese</b></li>
											<li><b>7 Korean</b></li>
											<li><b>8 Latin American</b> (eg. Hispanic, Mexican, South American)</li>
											<li><b>9 South Asian/West Indian/Guyanese</b> (eg. Trinidadian, India, Pakistan, Bangladesh, Nepal, Afghanistan, Sri Lanka)</li>
											<li><b>10 South East Asian</b> (eg. Burma, Cambodia, Thailand, Vietnam, Singapore)</li>
											<li><b>11 Caucasian</b> (eg. Canadian, White, European)</li>
											<li><b>120 Mixed Race</b></li>
										  </ul>
									</td>
								</tr>
							</table>			
						</td></tr> 
						<tr>
						    <td  colspan=4 align="left">
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