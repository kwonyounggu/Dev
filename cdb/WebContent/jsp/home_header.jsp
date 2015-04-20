<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sickkids.caliper.common.*" %>	

<script language="Javascript" type="text/javascript">

	function check_login()
	{
		if("${login_name}".length==0)
		{
			alert("Please sign in/login first!");
			return false;
		}
		else return true;
	}
</script>

<table cellspacing="0" cellpadding="0" width="970">
	<tr>
		<td id="main-nav" class="ddsmoothmenu" align="left">		
			<ul>
				<li><a href="<%= MenuLink.home %>" onfocus="this.blur()">Home</a></li>
				<li><a href="<%= MenuLink.caliper_study %>" onfocus="this.blur()">CALIPER Study</a>
				</li>
				<li><a href="<%= MenuLink.reference_intervals %>" onfocus="this.blur()">CALIPER Reference Intervals</a>
				</li>
				<li><a href="<%= MenuLink.custom_reference_intervals %>" onfocus="this.blur()">CALIPER Database</a>
					<ul>

					  <li><a href="<%= MenuLink.custom_reference_intervals %>" onfocus="this.blur()">Custom Reference Intervals</a></li>					  
<!--					  <li><a href="<%= MenuLink.downloads_raw_data %>" onfocus="this.blur()">Download Raw Data</a></li>-->
				  	</ul>
				</li>
				<li><a href="<%= MenuLink.community_q_and_a %>" onfocus="this.blur()">Q&A</a></li>	
				<li><a href="<%= MenuLink.community_faq %>" onfocus="this.blur()">FAQ</a></li>	
				<li><a href="<%= MenuLink.photos %>" onfocus="this.blur()">Photos</a></li>	
				<li><a href="<%= MenuLink.contactus %>" onfocus="this.blur()">Contact Us</a></li>
			  </ul>	
		</td>
	</tr> 
</table>