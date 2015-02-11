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
		<li style='list-style: none;' ignoretheme='true'>
			<img src='images/common/you_here.gif' width='25' height='25' style='vertical-align: middle;'/>&nbsp;&nbsp;
						<%
							AllRegisteredUserBean trb1=(AllRegisteredUserBean)session.getAttribute("trb");
							//Commented on JAN-29-2015 because displaying LECTURER, TEACHING ASSISTANT seems not so appropriate
							//CurriculumCurrentBean curriculumBean=(CurriculumCurrentBean)session.getAttribute("curriculum_bean");
							//ParticipantType pt=curriculumBean.getParticipantType(trb1.getUserId());				
							if(trb1!=null) 
							{
								List<AllLoginLevelBean> loginLevelList1=(List<AllLoginLevelBean>)application.getAttribute("tttloginLevelList");
								out.print("<span style='vertical-align: middle; font-family: arial;'>[TTT Main] ");		

								out.print("<b><font color='#768596'>"+trb1.getFirstName()+"&nbsp;&nbsp;|&nbsp;&nbsp;");
								out.print("<a onfocus='this.blur()' href='"+MenuLink.ttt_logout+"'>Logout</font></b></a></span>");
							}
							
						%>		
						
			&nbsp;&nbsp;<span style='vertical-align: middle; font-family: arial;'>Session: <b>${curriculum_bean.courseName}</b></span>
	    </li>	
    </ul>	
</div>