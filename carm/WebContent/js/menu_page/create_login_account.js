	function initializeThisPage(loginLevel)
	{
		log("initializeThisPage() is called in create_login_account.jsp");
		if(!isCanvasSupported())
		{
			document.getElementById("submitButton").disabled = true;
		}

		if(Number(loginLevel)==1) hideThingsForReviewers(true);
		
		$("#create_login_account_form").submit(function(e)
		{	 
			 e.preventDefault(); 			 
			 if(Number(document.getElementById("login_level").value)==3 && Number(document.getElementById("seniority").value)<5)
			 {
				 alert("The selected mediator is expected to have a higher seniority.");
				 return false;
			 }
			 $.ajax
		     ({
		         type: "post",
		         dataType: "",
		         url: "/carm/carm_controller?op=ajax_carm_central_create_login_account",
		         data: 
		         {
		    	 		first_name: document.getElementById("first_name").value,
		    	 		last_name:  document.getElementById("last_name").value,
		    	 		email:		document.getElementById("email").value,
		    	 		login_level:document.getElementById("login_level").value,
		    	 		max_review_groups:document.getElementById("max_review_groups").value,
		    	 		item_assignment_type:document.getElementById("item_assignment_type").value,
		    	 		review_confirmatory:(document.getElementById("review_confirmatory").value==1),
		    	 		access_limit_date:Date.parse($("#jqx_access_limit_date").jqxDateTimeInput('getText')),
		    	 		practice_year:document.getElementById("practice_year").value,
		    	 		health_discipline:document.getElementById("health_discipline").value,
		    	 		primay_clinical_practice:document.getElementById("primary_clinical_practice").value,
		    	 		seniority:document.getElementById("seniority").value		    	 		
		     	 },
		         success: createLoginAccountResponse,
		         error: function(response) //called for 404 error, etc
		         {
		        	 alert(response.responseText);	
		             
		         }
		      }); 
			 document.getElementById("spinner_img").className="spinner_unhidden";
			
		      return false;
		 });
	}
	function createLoginAccountResponse(strResponse)
	{
		document.getElementById("spinner_img").className="spinner_hidden";
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false:')==-1)//not found, means true
		{
			
			if(confirm("Creating a new login account is successful.\nDo you want to create another login account?")) location.reload();
			else location.replace(strResponse.substring(5));//remove true: prefix

		}
		else if(strResponse.indexOf('duplicate key')!=-1)
		{
			alert("The same ID is already existing. Please check the duplication again.");
			document.getElementById("first_name").focus();
			
		}
		else
		{
			alert(strResponse.substring(6));
		}
	}
	function addOption(sObjTo, optionVal)
	{
		try
		{
			var selectBoxOption = document.createElement("option");
			selectBoxOption.value = optionVal;
			selectBoxOption.text = optionVal;
			//selectBoxOption.text = optionVal.split(':')[1];
			sObjTo.add(selectBoxOption, null); 
			return true;
		}
		catch(e)
		{
			return false;
		}
	}
	function onAddEventName(from, to)
	{
		var sObjSource = document.getElementById(from);
		var sObjTo = document.getElementById(to);
		
		if(sObjSource.selectedIndex<0)
		{
			alert("There is no item selected to move");
			sObjSource.focus();
		}
		else if(addOption(sObjTo, sObjSource.value))
		{
			sObjSource.remove(sObjSource.selectedIndex);
		}
	}
	function carmIdAlreadyInvolved(carm_id, user_id)
	{
		//check if there is a record with carm_id with the given user_id
		var retValue=false;//not involved yet
		$.ajax
	     ({
	         type: "post",
	         dataType: "",
	         url: "/carm/carm_controller?op=ajax_carm_central_check_if_carm_id_involved&carm_id"+carm_id+"&user_id="+user_id,	         
	         success: function(data) 
	         {
	        	 retValue=(data=="true");	             
	         },
	         error: function(response) //called for 404 error, etc
	         {
	        	 alert(response.responseText);	
	             
	         }
	      }); 
		
		  return retValue;
	}
////////////////////////////////// Update Login Account ///////////////////////////////////////////////////////////////
	function loginAccountEvent(login_id)
	{
		log("loginAccountEvent("+login_id+") is called in update_login_account.jsp");
		httpRequestPost("/carm/carm_controller","op=ajax_carm_central_update_login_account_get_account_info&login_id="+login_id, "getAccountInfoResponse");	
		document.getElementById("spinner_img").className="spinner_unhidden";
	}
	function getAccountInfoResponse(strResponse)
	{
		document.getElementById("spinner_img").className="spinner_hidden";
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false:')==-1)//not found
		{
			var pieces=strResponse.substring(5).split("&");
			document.getElementById("first_name").value=pieces[2];
			document.getElementById("last_name").value=pieces[3];
			document.getElementById("user_id").value=pieces[1]; 
			document.getElementById("email").value=pieces[6];
			document.getElementById("login_level").selectedIndex=parseInt(pieces[4])-2;

			document.getElementById("max_review_groups").selectedIndex=parseInt(pieces[19])-1;//because it starts from zero index
			document.getElementById("hidden_max_review_groups").value=pieces[19];
			document.getElementById("item_assignment_type").selectedIndex=(pieces[20]=="by_group"? 0 : 1); 
			document.getElementById("review_confirmatory").selectedIndex=(pieces[21]=="true"? 0 : 1);
			
			$("#jqx_access_limit_date").jqxDateTimeInput({ value: new Date(Number(pieces[14])) });
			
			loginLevelChange(pieces[4]);
			onSetPracticeYear(pieces[15]);
			onSetHealthDiscipline(pieces[16]);
			onSetPrimaryClinicalPractice(pieces[17]);
			document.getElementById("seniority").selectedIndex=parseInt(pieces[18]);
		}
		else 
		{
			alert(strResponse.substring(6));
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
	function initializeUpdatePage(loginLevel)
	{
		log("initializeUpdatePage("+loginLevel+") is called in update_login_account.jsp");
		if(!isCanvasSupported())
		{
			document.getElementById("submitButton").disabled = true;
		}
		if(Number(loginLevel)==2) hideThingsForReviewers(true);
		$("#update_login_account_form").submit(function(e)
		{	 
			 e.preventDefault(); 
			 if(Number(document.getElementById("login_level").value)==3 && Number(document.getElementById("seniority").value)<5)
			 {
				 alert("The selected mediator is expected to have a higher seniority.");
				 return false;
			 }//if($("#number_of_groups").val()<Number(selectedDataRecord.number_of_groups))
			 else if(Number($("#max_review_groups").val())<Number($("#hidden_max_review_groups").val()))
			 {
				 alert("The maximum review groups should be >= the exiting value.");
				 return false;
			 }
			 $.ajax
		     ({
		         type: "post",
		         dataType: "",
		         url: "/carm/carm_controller?op=ajax_carm_central_update_login_account",
		         data: {
		    	 		user_id:    document.getElementById("user_id").value,
		    	 		first_name: document.getElementById("first_name").value,
		    	 		last_name:  document.getElementById("last_name").value,
		    	 		email:		document.getElementById("email").value,
		    	 		login_level:document.getElementById("login_level").value,
		    	 		max_review_groups:document.getElementById("max_review_groups").value,
		    	 		item_assignment_type:document.getElementById("item_assignment_type").value,
		    	 		review_confirmatory:(document.getElementById("review_confirmatory").value==1),
		    	 		access_limit_date:Date.parse($("#jqx_access_limit_date").jqxDateTimeInput('getText')),
		    	 		practice_year:document.getElementById("practice_year").value,
		    	 		health_discipline:document.getElementById("health_discipline").value,
		    	 		primay_clinical_practice:document.getElementById("primary_clinical_practice").value,
		    	 		seniority:document.getElementById("seniority").value
		     		   },
		         success: updateLoginAccountResponse,
		         error: function(response) //called for 404 error, etc
		         {
		        	 alert(response.responseText);	
		             
		         }
		      }); 
		      return false;
		 });
	}
	function updateLoginAccountResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false:')==-1)//not found, means true
		{
			
			if(confirm("Updating a new login account is successful.\nDo you want to update another login account?")) location.reload();
			else location.replace(strResponse.substring(5));//remove true: prefix
		}
		else
		{
			alert(strResponse.substring(6));
		}
	}
	
	function loginLevelChange(loginLevel)//invoke this when the combobox is changed at the right handside
	{		
		if(Number(loginLevel)==1 || Number(loginLevel)==2) 	hideThingsForReviewers(true);
		else hideThingsForReviewers(false);
	}
	function hideThingsForReviewers(bVal)//invoke this when the combobox is changed at the right handside
	{
		if(bVal)//hide
		{
			document.getElementById("max_review_groups_tr").style.display='none';
			document.getElementById("item_assignment_type_tr").style.display='none';
			//document.getElementById("review_confirmatory_tr").style.display='none';
		}
		else //show
		{
			document.getElementById("max_review_groups_tr").style.display='';
			document.getElementById("item_assignment_type_tr").style.display='';
			//document.getElementById("review_confirmatory_tr").style.display='';
		}
			
	}