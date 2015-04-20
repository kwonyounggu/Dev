	function httpRequestPost(strURL, strSubmit, strResultFunc) 
	{	//alert("--here--1--"+strURL+" "+strSubmit+" "+strResultFunc);
		var httpRequest;
        if (window.XMLHttpRequest) 
	    { 
	        httpRequest = new XMLHttpRequest();
	        if (httpRequest.overrideMimeType) httpRequest.overrideMimeType('text/xml');
	    } 
	    else if (window.ActiveXObject) 
	    { 
	        try 
	        {
	        	httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
	        } 
	        catch (e) 
	        {
	            try 
	            {
	            	httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	            } 
	            catch (e) {}
	        }
	    }
	
	    if (httpRequest==null) 
	    {
	        alert("ERROR :( Cannot create an XMLHTTP instance");
	        return;
	    }
	    httpRequest.open('POST', strURL, true);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        httpRequest.onreadystatechange = function()
        {	
        	try
        	{
                if (httpRequest.readyState == 4) //the full server response is received
                {
			           var strResponse = httpRequest.responseText;
			           switch (httpRequest.status) 
			           {
			                   case 404: alert('ERROR: The requested URL ' + strURL + ' could not be found.');// Page-not-found error
			                           	 break;
			                   case 500: handleErrorFullPage(strResponse);// Display results in a full window for server-side errors
			                             break;
			                   default: // Call JS alert for custom error or debug messages
			                            if (strResponse.indexOf('ERROR:') > -1 || strResponse.indexOf('Debug:') > -1) alert(strResponse);
			                            else eval(strResultFunc + '(strResponse);'); // Call the desired result function
			                            break;
			           }
			   }
            }
            catch(e)
			{
				alert("ERROR: there was a problem from the server response.\n(description: "+e.description+")");
			}
        }
        httpRequest.send(strSubmit);
    }
	function httpFileUploadRequestPost(strURL, strSubmit, strResultFunc) 
	{	
		var httpRequest;
        if (window.XMLHttpRequest) 
	    { 
	        httpRequest = new XMLHttpRequest();
	        if (httpRequest.overrideMimeType) httpRequest.overrideMimeType('text/xml');
	    } 
	    else if (window.ActiveXObject) 
	    { 
	        try 
	        {
	        	httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
	        } 
	        catch (e) 
	        {
	            try 
	            {
	            	httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	            } 
	            catch (e) {}
	        }
	    }
	
	    if (httpRequest==null) 
	    {
	        alert("ERROR :( Cannot create an XMLHTTP instance");
	        return;
	    }
	    httpRequest.open('POST', strURL, true);
	    //do not include the following when you upload a file to the file upload server
        //httpRequest.setRequestHeader('Content-Type', 'multipart/form-data');

        httpRequest.onreadystatechange = function()
        {	
        	try
        	{
                if (httpRequest.readyState == 4) //the full server response is received
                {
			           var strResponse = httpRequest.responseText;
			           switch (httpRequest.status) 
			           {
			                   case 404: alert('ERROR: The requested URL ' + strURL + ' could not be found.');// Page-not-found error
			                           	 break;
			                   case 500: handleErrorFullPage(strResponse);// Display results in a full window for server-side errors
			                             break;
			                   default: // Call JS alert for custom error or debug messages
			                            if (strResponse.indexOf('ERROR:') > -1 || strResponse.indexOf('Debug:') > -1) alert(strResponse);
			                            else eval(strResultFunc + '(strResponse);'); // Call the desired result function
			                            break;
			           }
			   }
            }
            catch(e)
			{
				alert("ERROR: there was a problem from the server response.\n(description: "+e.description+")");
			}
        }
        httpRequest.send(strSubmit);
    }
    function handleErrorFullPage(strIn) 
    {
		var errorWin;
		// Create new window and display error
        try 
        {
            errorWin = window.open('', 'errorWin');
            errorWin.document.body.innerHTML = strIn;
        }
        // If pop-up gets blocked, inform user
        catch(e) 
        {
            alert('An error occurred, but the error message cannot be'+' displayed because of your browser\'s pop-up blocker.\n'+'Please allow pop-ups from this Web site.');
        }
	}   
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