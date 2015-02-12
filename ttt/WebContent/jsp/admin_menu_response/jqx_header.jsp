<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>	
<%@ page session="true" errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>

<script type="text/javascript">
    $(document).ready(function () {

        $("#jqxMenu").jqxMenu({ width: '100%', height: '30px', autoOpen: false, autoCloseOnMouseLeave: false, showTopLevelArrows: true, 
        						theme: 'fresh'
        					  });
        $("#jqxMenu").css('visibility', 'visible');               
    });
</script>

<div id='jqxMenu' style='visibility: hidden; margin-left: 0px;'>
<ul>
    	<li><a href="<%= MenuLink.TTT_CONTEXT %>" onfocus="this.blur()">Home</a></li>				
		
		<li><a href="<%= MenuLink.menu_ttt_admin_manage_hospitals %>" onfocus="this.blur()">Hospital Management</a></li>	
		<li><a href="<%= MenuLink.menu_ttt_admin_manage_accounts %>" onfocus="this.blur()">Login Management</a></li>	
		<li><a href="<%= MenuLink.menu_ttt_admin_manage_training %>" onfocus="this.blur()">Session Management</a></li>
		<li>Data Library
			<ul style='width: 185px;'>
				<li><a onfocus='this.blur()' href='<%=MenuLink.menu_ttt_admin_manage_fileupload%>'>File Upload</a></li>
				<li><a onfocus='this.blur()' href='/carm/carm_controller?op=menu_carm_central_admin_update_login_account&sm=0'>NA</a></li>
				<li><a onfocus='this.blur()' href='/carm/carm_controller?op=menu_carm_central_admin_group_management&sm=0'>NA</a></li>
				<li><a onfocus='this.blur()' href='/carm/carm_controller?op=menu_carm_central_admin_map_event_group_reviewer&sm=0'>NA</a></li>
				<li><a onfocus='this.blur()' href='/carm/carm_controller?op=menu_carm_central_admin_clinical_summary&sm=0'>NA</a></li>
			</ul>
		</li>	
		
		<li style='list-style: none; align: right;'  ignoretheme='true'>
			<div style='margin-left: 50px;'>
	       		<img src='images/common/you_here.gif' width='25' height='25' style='vertical-align: middle;'/>&nbsp;&nbsp;
						<%
							AllRegisteredUserBean trb1=(AllRegisteredUserBean)session.getAttribute("trb");
							//Commented on JAN-29-2015 because displaying LECTURER, TEACHING ASSISTANT seems not so appropriate
							//CurriculumCurrentBean curriculumBean=(CurriculumCurrentBean)session.getAttribute("curriculum_bean");
							//ParticipantType pt=curriculumBean.getParticipantType(trb1.getUserId());				
							if(trb1!=null) 
							{
								List<AllLoginLevelBean> loginLevelList1=(List<AllLoginLevelBean>)application.getAttribute("tttloginLevelList");
								out.print("<span style='vertical-align: middle; font-family: arial;'>[TTT] ");		

								out.print("<b><font color='#768596'>"+Utils.getChoppedString(trb1.getFirstName(), 14)+"&nbsp;&nbsp;|&nbsp;&nbsp;");
								out.print("<a onfocus='this.blur()' href='"+MenuLink.ttt_logout+"'>Logout</font></b></a></span>");
							}
							
						%>		
			</div>

	    </li>	
    </ul>
	
</div>