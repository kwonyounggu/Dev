<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sickkids.caliper.common.*" %>	

<script language="Javascript" type="text/javascript">
	function signup(menulink)
	{
		window.location.href=menulink;
	}
	function getParameter(parameter) 
	{
		var queryString = window.top.location.search.substring(1);
		var parameterName = parameter + "=";
		if ( queryString.length > 0 ) 
		{
			begin = queryString.indexOf ( parameterName );
			if ( begin != -1 ) 
			{
				begin += parameterName.length;
				end = queryString.indexOf ( "&" , begin );
				if ( end == -1 ) 
				{
					end = queryString.length
				}
				return unescape ( queryString.substring ( begin, end ) );
			}
			return "null";
		}
	}
	function login_menu()
	{	
		alert(getParameter("op")); 
		if(document.getElementById("loggedIn").value=="no")
		{
			alert("<%= Message.login_information1 %>");
			document.getElementById("input_login_email").focus();
		}
		else 
		{
			alert("<%= Message.login_information2 %>");
		}
	}
	function check_login()
	{
		if(document.getElementById("loggedIn").value=="no")
		{
			
			op=getParameter("op");
			if(op.indexOf("menu_downloads")!=-1 || op.indexOf("menu_community")!=-1)
			{
				if (confirm("<%= Message.login_information4 %>"))
				{
					signup("<%= MenuLink.signup %>");
				}
				else 
				{
					return false;
				}
				
			}
			else
			{
				alert("<%= Message.login_information3 %>");
				document.getElementById("input_login_email").focus();
				return false;
			}		
		}
		else return true;
	}
	function check_admin_login()
	{
		httpRequestPost("<%= MenuLink.CONTEXT %>","op=check_admin_login", "isAdminLoggedIn");
	}
	function isAdminLoggedIn(strResponse)
	{
		if(strResponse.indexOf('false:')==-1)//not found
		{
			writeQaMessage();
		}
		else 
		{
			alert("You need to have an admin privilege!!!");
		}
	}

</script>

<table cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<td id="main-nav" class="ddsmoothmenu" align="left">		
			<ul>
				<li><a href="<%= MenuLink.home %>" onfocus="this.blur()">Home</a></li>
				<li><a href="<%= MenuLink.whatiscaliper %>" onfocus="this.blur()">About CALIPER</a>
				  <ul>
					  <li><a href="<%= MenuLink.whatiscaliper %>" onfocus="this.blur()"> What is CALIPER?</a></li>
					 <li><a href="<%= MenuLink.moreaboutcaliper %>" onfocus="this.blur()">More about CALIPER</a></li>
					  <li><a href="<%= MenuLink.caliperacrosscanada %>" onfocus="this.blur()">CALIPER across Canada</a></li>
					  <li><a href="<%= MenuLink.storybook %>" onfocus="this.blur()">CALIPER Story book</a></li>
				  </ul>
				</li>
				<li><a href="<%= MenuLink.howtoparticipate_howtoparticipate %>" onfocus="this.blur()">How To Participate</a>
				  <ul>
					  <li><a href="<%= MenuLink.children_and_families %>" onfocus="this.blur()">Children and Families</a></li>
					  <li><a href="<%= MenuLink.howtoparticipate_schoolds_communities %>" onfocus="this.blur()">Schools & Communities</a></li>
					  <li><a href="<%= MenuLink.howtoparticipate_clinics %>" onfocus="this.blur()">CALIPER Clinics</a></li>
					  <li><a href="<%= MenuLink.howtoparticipate_howtoparticipate +"#questionaires_and_forms" %>" onfocus="this.blur()">Questionaires & Forms</a></li>
					  <li><a href="<%= MenuLink.howtoparticipate_register %>" onfocus="this.blur()">Register To Participate</a></li>
				  </ul>
				</li>
				<li><a href="<%= MenuLink.volunteering_program %>" onfocus="this.blur()">Volunteering Program</a>
					<ul>
					  <li><a href="<%= MenuLink.volunteering_program %>" onfocus="this.blur()">Volunteering Program</a></li>					  
					  <li><a href="<%= MenuLink.volunteering_program +"#how_to_volunteer" %>" onfocus="this.blur()">How To Volunteer</a></li>
					  <li><a href="<%= MenuLink.volunteering_program +"#volunteer_opportunities" %>" onfocus="this.blur()">Volunteer Opportunities</a></li>
				  	</ul>
				</li>
				<li><a href="<%= MenuLink.aboutcaliper_calipermoment %>" onfocus="this.blur()">Moments</a>
					<ul>
					  <li><a href="<%= MenuLink.aboutcaliper_calipermoment %>" onfocus="this.blur()">CALIPER Moments</a></li>					  
				  	</ul>
				</li>			
				<li><a href="<%= MenuLink.community_faq %>" onfocus="this.blur()">FAQ</a></li>
				<li><a href="<%= MenuLink.contactus %>" onfocus="this.blur()">Contact Us</a></li>
			  </ul>	
		</td>
	</tr> 
</table>