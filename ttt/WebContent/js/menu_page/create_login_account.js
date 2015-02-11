	function initializeThisPage()
	{
		log("initializeThisPage() is called in create_login_account.jsp");
		if(!isCanvasSupported())
		{
			document.getElementById("submitButton").disabled = true;
		}
		$("#create_login_account_form").submit(function(e)
		{	 
			 e.preventDefault(); 
			 
			 if($('#login_level').val()=="3" || $('#login_level').val()=="4") //data creator or data reviewer
			 {	 
				 if(document.getElementById("drug_names_assigned").options.length==0)
				 {	
					jAlert("You have to assign drug names for this person!!!", 'Alert Dialog', function(r) 
					{
						return false;
					});
				 }
				
			 }
			 var cal = document.getElementById("calendar1_container")
			 if(cal.value.length==0)
			 {	
				jAlert("You have to choose 'Access Limit Date'!!!", 'Alert Dialog', function(r) 
				{
					cal.focus();
					return false;
				});
			 }
			 else if(new Date().getTime()<new Date(cal.value+" 23:59:59").getTime()==false)
			 {
					jAlert("You should select 'Access Limit Date' to be greather than the current time!!", 'Alert Dialog', function(r) 
					{
						cal.focus();
						return false;
					});
			 }
			 else
			 {
				 
				 var drug_names_assigned=document.getElementById("drug_names_assigned");
				 var drug_ids="";
				 for(var i=0;i<drug_names_assigned.options.length;i++)
				 {
					 drug_ids+=drug_names_assigned.options[i].value.split(":")[0]+",";
				 }
				 if(drug_ids.length>0) drug_ids=drug_ids.substring(0, drug_ids.length-1);
				 $.ajax
			     ({
			         type: "post",
			         dataType: "",
			         url: "/csr/ms_central?op=ajax_ms_central_create_login_account",
			         data: {
			    	 		first_name: document.getElementById("first_name").value,
			    	 		last_name:  document.getElementById("last_name").value,
			    	 		email:		document.getElementById("email").value,
			    	 		login_level:document.getElementById("login_level").value,
			    	 		drug_ids:   drug_ids,
			    	 		access_limit_date:document.getElementById("calendar1_container").value,
			    	 		practice_year:document.getElementById("practice_year").value,
			    	 		health_discipline:document.getElementById("health_discipline").value,
			    	 		primay_clinical_practice:document.getElementById("primary_clinical_practice").value,
			    	 		seniority:document.getElementById("seniority").value		    	 		
			     		   },
			         success: createLoginAccountResponse,
			         error: function(response) //called for 404 error, etc
			         {
			        	 jAlert(response.responseText, 'Alert Dialog');	
			             
			         }
			      }); 
			 }
		      return false;
		 });
	}
	function createLoginAccountResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
			jAlert("<p>Your session is expired. Please login again.</p>", "Warning Message", function(r) 
			{
				location.reload();
			});
		}
		else if(strResponse.indexOf('false:')==-1)//not found, means true
		{
			
			jConfirm('<p>Creating a new login account is successful.<br />Do you want to create another login account?</p>', 'Confirmation Dialog', function(r) 
			{
			    if(r==true)
			    {
			    	location.reload();
			    }
			    else
			    {
			    	location.replace(strResponse.substring(5));//remove true: prefix
			    }
			});
		}
		else if(strResponse.indexOf('duplicate key')!=-1)
		{
			jAlert("<p>The same name is already existing. Please check the duplication again.</p>", "Error Message", function(r) 
			{
				document.getElementById("first_name").focus();
			});
			
		}
		else
		{
			jAlert("<p>"+strResponse.substring(6)+"</p>", "Error Message");
		}
	}
	function addOption(sObjTo, optionVal)
	{
		try
		{
			var selectBoxOption = document.createElement("option");
			selectBoxOption.value = optionVal;
			selectBoxOption.text = optionVal.split(':')[1];
			sObjTo.add(selectBoxOption, null); 
			return true;
		}
		catch(e)
		{
			return false;
		}
	}
	function onAddDrugName(from, to)
	{
		var sObjSource = document.getElementById(from);
		var sObjTo = document.getElementById(to);
		
		if(sObjSource.selectedIndex<0)
		{
			jAlert("There is no item selected to move", 'Alert Dialog');
			sObjSource.focus();
		}
		else if(addOption(sObjTo, sObjSource.value))
		{
			sObjSource.remove(sObjSource.selectedIndex);
		}
	}
////////////////////////////////// Update Login Account ///////////////////////////////////////////////////////////////
	function loginAccountEvent(login_id)
	{
		log("loginAccountEvent("+login_id+") is called in create_login_account.jsp");
		httpRequestPost("/csr/ms_central","op=ajax_ms_central_update_login_account_get_account_info&login_id="+login_id, "getAccountInfoResponse");	
	}
	function getAccountInfoResponse(strResponse)
	{
		//log("getAccountInfoResponse("+strResponse+") is called in create_login_account.jsp");
		if(strResponse.indexOf('session_timeout')==0) 
		{
			jAlert("<p>Your session is expired. Please login again.</p>", "Warning Message", function(r) 
			{
				location.reload();
			});
		}
		else if(strResponse.indexOf('false:')==-1)//not found
		{
			var pieces=strResponse.substring(5).split("&");
			document.getElementById("first_name").value=pieces[0];
			document.getElementById("last_name").value=pieces[1];
			document.getElementById("user_id").value=pieces[2]; 
			document.getElementById("email").value=pieces[3];
			document.getElementById("login_level").selectedIndex=parseInt(pieces[4])-2;

			document.getElementById("drug_names_assigned").innerHTML=pieces[5];
			document.getElementById("drug_names_available").innerHTML=pieces[6];
			document.getElementById("drug_ids").value=pieces[7];
			document.getElementById("calendar1_container").value=pieces[8];
			
			onSetPracticeYear(pieces[9]);
			onSetHealthDiscipline(pieces[10]);
			onSetPrimaryClinicalPractice(pieces[11]);
			document.getElementById("seniority").selectedIndex=parseInt(pieces[12]);
		}
		else 
		{
			jAlert("<p>"+strResponse.substring(6)+"</p>", "Error Message");
		}
	}
	function onSetPracticeYear(practice_year)
	{
		var selectedIndex=-1;
		switch(practice_year)
		{
			case "<0": selectedIndex=0;break;
			case "0-2": selectedIndex=1;break;
			case "3-5": selectedIndex=2;break;
			case "6-10": selectedIndex=3;break;
			case ">10": selectedIndex=4;break;
			default: selectedIndex=-1; break;
		}

		document.getElementById("practice_year").selectedIndex=selectedIndex;
	}
	function onSetHealthDiscipline(health_discipline)
	{
		var selectedIndex=-1;
		switch(health_discipline)
		{
			case "Pharmacy": selectedIndex=0;break;
			case "Pharmacology": selectedIndex=1;break;
			case "Nursing": selectedIndex=2;break;
			case "Respiratory therapy": selectedIndex=3;break;
			case "Medicine (physician)": selectedIndex=4;break;
			case "Medical record abstractor": selectedIndex=5;break;
			case "Other (outside healthcare profession)": selectedIndex=6;break;
			default: selectedIndex=-1; break;
		}
		document.getElementById("health_discipline").selectedIndex=selectedIndex;
	}
	function onSetPrimaryClinicalPractice(primary_clinical_practice)
	{
		var selectedIndex=-1;
		switch(primary_clinical_practice)
		{
			case "Adult": selectedIndex=0;break;
			case "<18 years paediatric": selectedIndex=1;break;
			case "Mixed adult-geriatric": selectedIndex=2;break;
			case "Adult-paediatric": selectedIndex=3;break;
			default: selectedIndex=-1; break;
		}
		document.getElementById("primary_clinical_practice").selectedIndex=selectedIndex;
	}
	function initializeUpdatePage()
	{
		log("initializeUpdatePage() is called in create_login_account.jsp");
		if(!isCanvasSupported())
		{
			document.getElementById("submitButton").disabled = true;
		}
		$("#update_login_account_form").submit(function(e)
		{	 
			 e.preventDefault(); 
			 
			 if($('#login_level').val()=="3" || $('#login_level').val()=="4") //data creator or data reviewer
			 {	 
				 if(document.getElementById("drug_names_assigned").options.length==0)
				 {	
					jAlert("You have to assign drug names for this person!!!", 'Alert Dialog', function(r) 
					{
						return false;
					});
				 }
				
			 }
			 var cal = document.getElementById("calendar1_container")
			 if(cal.value.length==0)
			 {	
				jAlert("You have to choose 'Access Limit Date'!!!", 'Alert Dialog', function(r) 
				{
					cal.focus();
					return false;
				});
			 }
			 else if(new Date().getTime()<new Date(cal.value+" 23:59:59").getTime()==false)
			 {
					jAlert("You should select 'Access Limit Date' to be greather than the current time!!", 'Alert Dialog', function(r) 
					{
						cal.focus();
						return false;
					});
			 }
			 else
			 {
				 
				 var drug_names_assigned=document.getElementById("drug_names_assigned");
				 var drug_ids="";
				 for(var i=0;i<drug_names_assigned.options.length;i++)
				 {
					 drug_ids+=drug_names_assigned.options[i].value.split(":")[0]+",";
				 }
				 if(drug_ids.length>0) drug_ids=drug_ids.substring(0, drug_ids.length-1);
				 $.ajax
			     ({
			         type: "post",
			         dataType: "",
			         url: "/csr/ms_central?op=ajax_ms_central_update_login_account",
			         data: {
			    	 		user_id:    document.getElementById("user_id").value,
			    	 		first_name: document.getElementById("first_name").value,
			    	 		last_name:  document.getElementById("last_name").value,
			    	 		email:		document.getElementById("email").value,
			    	 		login_level:document.getElementById("login_level").value,
			    	 		drug_ids:   drug_ids,
			    	 		access_limit_date:document.getElementById("calendar1_container").value,
			    	 		practice_year:document.getElementById("practice_year").value,
			    	 		health_discipline:document.getElementById("health_discipline").value,
			    	 		primay_clinical_practice:document.getElementById("primary_clinical_practice").value,
			    	 		seniority:document.getElementById("seniority").value		    	 		
			     		   },
			         success: updateLoginAccountResponse,
			         error: function(response) //called for 404 error, etc
			         {
			        	 jAlert(response.responseText, 'Alert Dialog');	
			             
			         }
			      }); 
			 }
		      return false;
		 });
	}
	function updateLoginAccountResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
			jAlert("<p>Your session is expired. Please login again.</p>", "Warning Message", function(r) 
			{
				location.reload();
			});
		}
		else if(strResponse.indexOf('false:')==-1)//not found, means true
		{
			
			jConfirm('<p>Updating a new login account is successful.<br />Do you want to update another login account?</p>', 'Confirmation Dialog', function(r) 
			{
			    if(r==true)
			    {
			    	location.reload();
			    }
			    else
			    {
			    	location.replace(strResponse.substring(5));//remove true: prefix
			    }
			});
		}
		else
		{
			jAlert("<p>"+strResponse.substring(6)+"</p>", "Error Message");
		}
	}