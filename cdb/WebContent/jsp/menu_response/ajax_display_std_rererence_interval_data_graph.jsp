<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<table border=0 cellspacing=1 cellpadding=0 width='100%' style="padding-right: 0px;"> 
	<tr><td colspan=6 height=3></td></tr>
	<tr><td colspan=6 align="center"><img src='${analyte_chart_path}' style='vertical-align: middle;'/>
		</td>
	</tr> 
	<%
        String analyte_name=(String)session.getAttribute("analyte_name");
        boolean bMagnesium=analyte_name.startsWith("Magnesium");
        
        if(bMagnesium)
        {
            out.print("<tr><td colspan=6 style='padding-right: 15px; padding-bottom: 0px; padding-left: 15px;'>");
            out.print("<p><div align='center' style='background-color: red; box-shadow: 0px 0px 0px #888888; color: white; padding-right: 15px; padding-left: 15px;'>The Magnesium reference intervals should NOT be used as the Abbott method and reagents have changed. New reference intervals will be reported soon using the new Abbott method.</div></p>");
            out.print("</td></tr>");
    	}
        String styleString=(bMagnesium)? "style='display:none;'":"";
    %>
	<tr <%=styleString %>><td colspan=3><span style="font-size:9pt; color: #800000;">&nbsp;&nbsp;${analyte_name}
		</span>
		</td>
		<td colspan=3 align="right">
			<span style="font-size:9pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;" >
 				<a href="http://www.ncbi.nlm.nih.gov/pubmed/${paper_link}" style="color: #800000;text-decoration:underline;" target="_blank" title="National Center for Biotechnology Information" onfocus='this.blur()'>Published paper for the analyte on NCBI (Clinical Chemistry ${paper_year})</a>
 				&nbsp;&nbsp;&nbsp;&nbsp; 
				<a href="<%=MenuLink.menu_print_reference_intervals %>" target="_blank" style="color: #800000;text-decoration:underline;" onfocus='this.blur()'>print</a>
				&nbsp;&nbsp;
			</span>
		</td>
	</tr>
	<tr <%=styleString %>><td colspan=6 height=1 class=dv_line3></td></tr> 
	<tr <%=styleString %>>
		<td class=header_std_ri_v2_td width="100%" colspan=6>Female Reference Intervals</td> 
	</tr>
	<tr <%=styleString %>>
		<td class=header_std_ri_v2_td width="16%">Age</td> 
		<td class=header_std_ri_v2_td width="19%">Lower Limit</td>
		<td class=header_std_ri_v2_td width="19%">Upper Limit</td>
        <td class=header_std_ri_v2_td width="8%">Samples</td>
        <td class=header_std_ri_v2_td width="19%">Lower CI</td>
		<td class=header_std_ri_v2_td width="19%">Higher CI</td>
	</tr>
	<%  
		if(!bMagnesium)
		{
			List<StdReferenceIntervalBean> femaleList = (List<StdReferenceIntervalBean>)session.getAttribute("femaleList");
			for(StdReferenceIntervalBean sb : femaleList)
			{
				out.print("<tr style='background-color:#EAF0FF;'>");
				out.print("<td class=center_std_ri_v2_td width='16%'>"+sb.getAge_range()+"</td>"+ 
						  "<td class=center_std_ri_v2_td width='19%'>"+sb.getRi_lower_limit()+"</td>"+
						  "<td class=center_std_ri_v2_td width='19%'>"+sb.getRi_upper_limit()+"</td>"+
		        		  "<td class=center_std_ri_v2_td width='8%'>"+sb.getSamples()+"</td>"+
		        		  "<td class=center_std_ri_v2_td width='19%'>"+sb.getCi_lower_limit()+"</td>"+
						  "<td class=center_std_ri_v2_td width='19%'>"+sb.getCi_upper_limit()+"</td>");
				out.print("</tr>");
			}
		}
	%>
	<tr <%=styleString %>><td colspan=6 height=20></td></tr>
	<tr <%=styleString %>><td colspan=6 height=1 class=dv_line3></td></tr> 
	<tr <%=styleString %>>
		<td class=header_std_ri_v2_td width="100%" colspan=6>Male Reference Intervals</td> 
	</tr>
	<tr <%=styleString %>>
		<td class=header_std_ri_v2_td width="16%">Age</td> 
		<td class=header_std_ri_v2_td width="19%">Lower Limit</td>
		<td class=header_std_ri_v2_td width="19%">Upper Limit</td>
        <td class=header_std_ri_v2_td width="8%">Samples</td>
        <td class=header_std_ri_v2_td width="19%">Lower CI</td>
		<td class=header_std_ri_v2_td width="19%">Higher CI</td>
	</tr>
	<%  
		if(!bMagnesium)
		{
			List<StdReferenceIntervalBean> maleList = (List<StdReferenceIntervalBean>)session.getAttribute("maleList");
			for(StdReferenceIntervalBean sb : maleList)
			{
				out.print("<tr style='background-color:#EAF0FF;'>");
				out.print("<td class=center_std_ri_v2_td width='16%'>"+sb.getAge_range()+"</td>"+ 
						  "<td class=center_std_ri_v2_td width='19%'>"+sb.getRi_lower_limit()+"</td>"+
						  "<td class=center_std_ri_v2_td width='19%'>"+sb.getRi_upper_limit()+"</td>"+
		        		  "<td class=center_std_ri_v2_td width='8%'>"+sb.getSamples()+"</td>"+
		        		  "<td class=center_std_ri_v2_td width='19%'>"+sb.getCi_lower_limit()+"</td>"+
						  "<td class=center_std_ri_v2_td width='19%'>"+sb.getCi_upper_limit()+"</td>");
				out.print("</tr>");
			}
		}
	%>
	<tr><td colspan=6 style="padding-right: 15px; padding-bottom: 0px; padding-left: 15px; "><p>
		<span style="font-size:10pt; font-family: arial; vertical-align: middle;">	
			<p>This section provides a summary of age and sex-partitioned pediatric reference intervals for serum chemistry, enzyme, protein, and lipid analytes. 
			</p>
			<p>
				Blood samples were collected from healthy children from birth to 18 years of age, centrifuged, and separated, and serum aliquots were kept frozen at -80&#8451;. Serum was tested on the Abbott Architect ${abbott_architect}. 
				Data was analyzed in accordance with CLSI C28-A3 guidelines on defining, establishing, and verifying reference intervals in the clinical laboratory. Outliers were removed after visual inspection and the Dixon test. Age and sex partitions were determined by visual inspection of scatter plots, distribution plots, and examination of the overall trend. The partitions were assessed for normality of distribution using the skewness statistic and non-Gaussian distributed partitions were log-transformed. The partitions were evaluated using the Harris and Boyd method. For partitions with a sample size greater than 120, the non-parametric rank method was used to calculate the reference intervals. For partitions with a sample size less than 120, the robust statistical method was employed. 90% confidence intervals around the lower and upper reference limits were calculated.
			</p>
			<p style="font-size:8pt;"> <i>The last data update done on Nov 30, 2013.</i>
			</p>
		</span>
		</td>
	</tr> 
</table>
