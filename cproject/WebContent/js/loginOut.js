	function admin_logout()
	{
		httpRequestPost("/caliperproject/admin","op=ajax_admin_logout", "adminLogoutResponse");//Handled by AdminController
	}
	function adminLogoutResponse(strResponse)
	{
		location.reload();
	}
	/*
	function login_button()
	{
		var emailElement=document.getElementById("input_login_email");
		var pwdElement=document.getElementById("input_login_password");

		//alert("email:"+emailElement.value+" pwd:"+pwdElement.value);
		emailElement.value=trim(emailElement.value);	
		
		if(!validateEmail(emailElement.value))
		{	
			alert("Please input your right email address.");
			emailElement.focus();
		}
		else if(!validatePassword(pwdElement.value))
		{
			alert("Your password is not correct. Please check if [Caps Lock] is turned on.");
			pwdElement.focus();
		}
		else loginRequest("/caliper/controller","op=login_check&input_login_email="+emailElement.value+"&input_login_password="+pwdElement.value,"loginResponse");
		return false;
	}
	function loginResponse(strResponse)
	{
		if(strResponse.indexOf('false:')==-1)//not found
		{
			//remove all the table contents
			removeRowFromTable("loginTable");
			document.getElementById('logBody').innerHTML = strResponse;
			document.getElementById("loggedIn").value="yes";
			//if(document.getElementById("loginNextLink").value!="no")
			{
				//alert("refresh")
				location.reload();
			}
		}
		else 
		{
			alert(strResponse.substring(6));
			//focus to the login input box
		}
	}
	function logout_button()
	{
		loginRequest("/caliper/controller","op=logout_check&email=abc@abc.ca&pwd=abc123","logoutResponse");
	}
	function logoutResponse(strResponse)
	{
		if(strResponse.indexOf('false:')==-1)//not found
		{
			//remove all the table contents
			removeRowFromTable("logoutTable");
			document.getElementById('logBody').innerHTML = strResponse;	
			document.getElementById("loggedIn").value="no";
		}
		else 
		{
			alert(strResponse.substring(6));
		}
	}*/
	/*
		Note:
			function removeNode(node) and 
			function removeRowFromTable(tableID)
		will be worked for removing all the table contents including inner tables (not tested).
		Tested to remove all the components; IMG, A, TD, TR, etc
	*/
	function removeNode(node)
	{
	    for(var i = (node.childNodes.length - 1); i >= 0; i--)
	        removeNode(node.childNodes[i]);
        //The following alert shows you what will be removed such as IMG, A, TD, TR, etc
		//alert("node name="+node.nodeName);
	    node.parentNode.removeChild(node);
	}
	function removeRowFromTable(tableID)
	{
	  var tbl = document.getElementById(tableID);
	  for(var i=(tbl.rows.length-1);i>=0;i--) 
	  {
		  removeNode(tbl.rows[i]);
			 
	  }
	}