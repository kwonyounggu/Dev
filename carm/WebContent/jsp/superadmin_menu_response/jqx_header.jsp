<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>	
<%@ page session="true" errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>

<script type="text/javascript">
    $(document).ready(function () {

        $("#jqxMenu").jqxMenu({ width: '100%', height: '30px', autoOpen: false, autoCloseOnMouseLeave: false, showTopLevelArrows: true, theme: 'fresh'});
        $("#jqxMenu").css('visibility', 'visible');               
    });
</script>

<div id='jqxMenu' style='visibility: hidden; margin-left: 0px;'>
<ul>
    
		<li style='list-style: none; width: 100%; '  ignoretheme='true'>

	        <table width='100%' height='100%'>
				<tr ><td align='right' width='80%'  height='100%' valign='top'>
				<%
					AllRegisteredUserBean sab=(AllRegisteredUserBean)session.getAttribute("sab");
									
					if(sab!=null) 
					{
						List<AllLoginLevelBean> loginLevelList1=(List<AllLoginLevelBean>)application.getAttribute("cloginLevelList");
						out.print("<span style='vertical-align: top; font-family: arial;'>[A] ");		
						out.print(loginLevelList1.get(sab.getLogin_level()-1).getLoginLevelDescription()+":</span> <b><span style='vertical-align: top; color: #768596;font-family: arial;'>"+sab.getFirst_name()+"&nbsp;&nbsp;|&nbsp;&nbsp;</span></b>");
						out.print("<a onfocus='this.blur()' href='"+MenuLink.superadmin_logout+"'><span style='vertical-align: top; color: #768596;font-family: arial;'>Logout</span></a>");
					}
				%>		
				&nbsp;&nbsp;
			</td>			
			</table>

	    </li>	
    </ul>
	
</div>