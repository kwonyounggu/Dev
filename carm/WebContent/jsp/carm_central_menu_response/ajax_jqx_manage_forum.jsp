<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>
<style>
<!--
.forum_table
{
    position: relative;
    background-color: #FFFFFF;
    padding: 20px 20px 20px 20px;
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}


.forum_table table td 
{
    vertical-align: top;
    border: 1px solid #CCCCCC;
    font-size: 12px;
    padding-top: 3px;
}

.inner_forum_table
{
	 position: relative;
     background-color: #FFFFFF;
     padding: 20px 20px 20px 20px;
     -moz-border-radius: 4px;
     -webkit-border-radius: 4px;
     border-radius: 4px;
}
.inner_forum_table table td 
{
    vertical-align: top;
    border: 0px dotted #CCCCCC;
    font-size: 12px;
    padding-top: 3px;
}
.reply_forum_div
{
	 position: relative;
     background-color: #FFFFFF;
     padding: 20px 20px 20px 20px;
     -moz-border-radius: 4px;
     -webkit-border-radius: 4px;
     border-radius: 4px;
}
-->
</style>
<%!
	public String loginLevel(int login_level)
	{
		String _loginLevel="";
		switch(login_level)
		{
			case 1: _loginLevel="System Admin"; break;
			case 2: _loginLevel="Administrator"; break;
			case 3: _loginLevel="Mediator"; break;
			case 4: _loginLevel="Reviewer"; break;
			default: _loginLevel="Unknown"; break;
		}
		return _loginLevel;
	}
	public String loginLevel(String loginId)
	{
		String _loginLevel="";
		switch(Integer.parseInt(loginId.split("\\.")[2]))
		{
			case 1: _loginLevel="System Admin"; break;
			case 2: _loginLevel="Administrator"; break;
			case 3: _loginLevel="Mediator"; break;
			case 4: _loginLevel="Reviewer"; break;
			default: _loginLevel="Unknown"; break;
		}
		return _loginLevel;
	}
	public String replaceCR(String s)
	{
		return s.replace("/(\r\n|\n|\r)/g","<br/>");
	}
%>

<%
	CarmSQLDao csqlDao=(CarmSQLDao)application.getAttribute("csqlDao");
	List<AllForumBean> forumList=(List<AllForumBean>)request.getAttribute("forum_list");	
	AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb");
	int item_id=Integer.parseInt(request.getParameter("carm_id"));
	List<String> peerList=null;
	if(crb.getLogin_level()==4)
	{
		peerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+item_id+" and reviewer_id!='"+crb.getUser_id()+"'");
	}
	else if(crb.getLogin_level()==3)
	{
		//The following statement is not acceptable since there may be more than one mediator for an item. So in such case, a mediator for the item can only see his/her peer not all peers
		//peerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+item_id+" and mediator_id='"+crb.getUser_id()+"'");
		peerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+item_id);
	}
	else //admin
	{
		peerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+item_id);
	}
%>

<table cellpadding='0' cellspacing='0' width='100%' style='padding: 0px 10px 10px 10px;'>

  <tr>
    <td colspan='5'>
       <div class="forum_table">
       		
	       				<table border=0 width='100%' >
	       					<tr bgcolor='#E4E4C0'>
	       						<td colspan=2>
	       							<img src='images/common/item_icon.png' style='vertical-align: middle; margin-left: 5px; margin-right: 5px;'/>
	       							<span style='color: #888BFB; font-weight:normal; vertical-align: middle;'><%=item_id%></span>	       							
	       						</td>
	       					</tr>
	       					<tr ><td colspan=2 style='padding: 10px 10px 10px 12px;' align="right">
	       							The potential preventability scaled from 1 to 6 is resulted in&nbsp;
	       							<span style='color: #888BFB; font-weight:normal; vertical-align: middle; font-size: 10.5px;'>
	       								<%
	       								if(crb.getLogin_level()==4)//reviewer
	       								{
	       									AllReviewDataBean myAns=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+item_id+" and point_id=3 and reviewer_id='"+crb.getUser_id()+"'");//if review_time is not null, then there is a record
	       									if(myAns.getReview_time()==null) out.print(crb.getUser_id()+": <u>Not Applicable</u>");
	       									else out.print(crb.getUser_id()+": <u>"+(int)myAns.getPoint_value_number()+"</u>");
	       									
	       									for(int p=0;p<peerList.size();p++)
	       									{
	       										AllReviewDataBean peerBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+item_id+" and point_id=3 and reviewer_id='"+peerList.get(p)+"'");
	       										out.print(" | peer."+(p+1)+": "+(peerBean.getReview_time()==null? "<u>Not Applicable</u>":(peerBean.getPoint_value_number()==myAns.getPoint_value_number()? "<u>Agree</u>":"<u>Disagree</u>")));
	       									}
	       								}
	       								else //mediator, admin
	       								{	       								
	       									for(int p=0;p<peerList.size();p++)
	       									{
	       										AllReviewDataBean peerBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+item_id+" and point_id=3 and reviewer_id='"+peerList.get(p)+"'");
	       										out.print(peerList.get(p)+": <u>"+(peerBean.getReview_time()==null? "Not Applicable":(int)peerBean.getPoint_value_number())+"</u>");
	       										if((p+1)<peerList.size()) out.print(" | ");
	       										
	       										//the following will be used to provide each reviewer's value
	       										//default value, 1, is used to nueturalize the process
	       										out.print("<input type='hidden' id='reviewer_"+(p+1)+"' value='"+(peerBean.getReview_time()==null? "1":(int)peerBean.getPoint_value_number())+"'/>");
	       									}
	       								}
	       								%>
	       							</span>
	       						</td></tr>
	       				</table>
       				
       </div>

       <div class='reply_forum_div'>
       		<table border=0 width='100%' >
		       	<tr><td colspan=3 align="left" valign="bottom" style='font-weight: bold;'>&nbsp;&nbsp;&nbsp;<img src='images/common/write.jpg' style='vertical-align: middle;'/>&nbsp;&nbsp;Leave your opinion</td></tr>
				<tr><td colspan=3 valign="top">
						<textarea id="forum_reply" name="forum_reply" style="width: 100%; height: 100%;  border: 1; overflow-x: hidden;" maxlength="5100" rows="8"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan=2 align='left' id="finalizeTdSection" style='display:none; ' valign='middle'>
						Please confirm, very weak
						<input type='radio' name='q3RadioGroup'  value='1'>1</input>	
						<input type='radio' name='q3RadioGroup'  value='2'>2</input>
						<input type='radio' name='q3RadioGroup'  value='3'>3</input>
						<input type='radio' name='q3RadioGroup'  value='4'>4</input>
						<input type='radio' name='q3RadioGroup'  value='5'>5</input>
						<input type='radio' name='q3RadioGroup'  value='6'>6</input>	
						very strong, based on reviewers (<span id='reviewer_1_value_id' style='border: 1px solid #888BFB;'>&nbsp;1&nbsp;</span>, <span id='reviewer_2_value_id' style='border: 1px solid #888BFB;'>&nbsp;2&nbsp;</span>)				
					</td>
					<td align="right">
						<img id='spinner_img' src='images/common/spinner.gif' width=32 height=32  class="spinner_hidden"/>
						<input type='button' value='Submit' id="submitForumButton" style="vertical-align: bottom; cursor:pointer; margin-right: 10px;"/>
					</td>
				</tr>
			</table>
       </div>
       
       <div class="inner_forum_table">
			<% 
				for(AllForumBean fb : forumList)
				{	
					out.print("<table border=1 cellspacing=0 cellpadding=0 width='100%' style='border-collapse: collapse; border-color: #CBCCC6; border-style: dotted;'>");
					out.print("<tr><td height=26 style='border-style: dotted;border-width: 1px;border-color: #CBCCC6;'>");
						out.print("<table border=0 cellspacing=0 cellpadding=4 width='100%' >");
						out.print("<tr bgcolor='#fafafa'><td width=16><img src='images/common/subject2.png' style='vertical-align: middle;'/></td>");
						out.print("    <td width='40%' ><span style='color: #786161; font-weight:normal; font-size:9pt; vertical-align: middle;'>[ "+loginLevel(fb.getSubmitter_id())+" ]</span> ");
						out.print(""+"</td>");
						out.print("    <td align='right' width='60%' style='font-size:8pt; color: #786161;'> Posted by "+fb.getSubmitter_id()+"&nbsp;|&nbsp;"+Utils.getDetailedMMMDate(fb.getSubmission_time())+"&nbsp;");
						out.println("</td></tr>");
						out.print("</table>");
					out.print("</td></tr>");
					out.print("<tr><td style='padding: 20px 20px 20px 20px;'>"+fb.getSubmitter_opinionBr()+"</td></tr>");
					out.print("</table><br>");				
				}
				
			%>	
       </div>
	</td>
  </tr>
</table>

<script language="Javascript" type="text/javascript">
	$(document).ready(function () 
	{		
		var login_level=parseInt("${crb.login_level}");
		var bFinalizeBtnChecked=$('#jqxRadioButtonFinalize').jqxRadioButton('checked');
		//log("login_level="+login_level+", bFinalizeBtnChecked="+bFinalizeBtnChecked);
		if(login_level==3 && bFinalizeBtnChecked)
		{
			$("#finalizeTdSection").css( "display", "block");
			if(Number($("#reviewer_1").val())==Number($("#reviewer_2").val())) 
			{
				$("#reviewer_1_value_id").html("&nbsp;"+Number($("#reviewer_1").val())+"&nbsp;");
				$("#reviewer_2_value_id").html("&nbsp;"+Number($("#reviewer_2").val())+"&nbsp;");
			}
			else
			{
				$("#reviewer_1_value_id").html("&nbsp;"+Number($("#reviewer_1").val())+"&nbsp;");				
				$("#reviewer_2_value_id").html("&nbsp;"+Number($("#reviewer_2").val())+"&nbsp;");
				$("#reviewer_2_value_id").css( "color", "#ff0000");
			}
		}
        $("#submitForumButton").on('click', function () 
        {	
    		var itemID="<%=item_id%>";
    		var finalValue=getRadioValue('q3RadioGroup');
    		if(login_level==3 && bFinalizeBtnChecked && finalValue=="")
    		{
    			alert("You have to confirm on the value(s) the reviewers rated!");
    		}
    		else if($("#forum_reply").val().length<3)
    		{
    			alert("You have to put your own opinion with minimum three characters!");
    		}
    		else //Dec-5 task: put values into DB and read
    		{
    			document.getElementById("spinner_img").className="spinner_unhidden";
    			httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_save_forum_data"+
    																	 "&app_id=${crb.app_id}"+
    																	 "&group_id=1"+
    																	 "&item_id="+itemID+
    																	 "&point_id=3"+
    																	 "&bFinalizeBtnChecked="+bFinalizeBtnChecked+
    																	 "&finalValue="+finalValue+
    																	 "&myOpinion="+escape(removeNonAscii(jQuery("#forum_reply").val())), "saveForumDataResponse");
    		}
    		
        });
	});
	function saveForumDataResponse(strResponse)
	{
		document.getElementById("spinner_img").className="spinner_hidden";
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false_ajax_carm_central_save_forum_data:')==-1)//not found, means true
		{
			alert("Successfully submitted!");
			callAjaxAccordingly($('#jqxReviewTabs').jqxTabs('selectedItem'), $("#jqxEventList").jqxDropDownList('getSelectedItem'));//this will reload the same review item.
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_save_forum_data:".length));//false, then display			
		}
	}
</script>