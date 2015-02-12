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
	//This will return with XML strings
	function httpRequestFileUploadProgress(strURL) 
	{	//document.write("httpRequestFileUploadProgress("+strURL+") is called...")
		//alert(strURL);
		//var uploadURL=strURL;
		var httpRequest;
        if (window.XMLHttpRequest) 
	    { 
	        httpRequest = new XMLHttpRequest();
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
	            catch (e) 
	            {}
	        }
	    }
	
	    if (httpRequest==null) 
	    {
	        alert("ERROR :( Cannot create an XMLHTTP instance");
	        return;
	    }
	    httpRequest.open('GET', strURL, true);

        httpRequest.onreadystatechange = function()
        {	
        	try
        	{
                if (httpRequest.readyState == 4) //the full server response is received
                {
                	if (httpRequest.status == 200)
            		{
            			var xml = httpRequest.responseXML;
            			var isNotFinished = xml.getElementsByTagName("finished")[0];
            			var myBytesRead = xml.getElementsByTagName("bytes_read")[0];
            			var myContentLength = xml.getElementsByTagName("content_length")[0];
            			var myPercent = xml.getElementsByTagName("percent_complete")[0];
            			if ((isNotFinished == null) && (myPercent == null))
            			{
            				document.getElementById("initializing").style.visibility = "visible";
            				window.setTimeout("httpRequestFileUploadProgress("+strURL+");", 100);
            			} 
            			else 
            			{
            				document.getElementById("initializing").style.visibility = "hidden";
            				document.getElementById("progressBarTable").style.visibility = "visible";
            				document.getElementById("percentCompleteTable").style.visibility = "visible";
            				document.getElementById("bytesRead").style.visibility = "visible";

            				myBytesRead = myBytesRead.firstChild.data;
            				myContentLength = myContentLength.firstChild.data;

            				if (myPercent != null) 
            				{
            					myPercent = myPercent.firstChild.data;
            		
            					document.getElementById("progressBar").style.width = myPercent + "%";
            					document.getElementById("bytesRead").innerHTML = myBytesRead + " of " + 
            						myContentLength + " bytes read";
            					document.getElementById("percentComplete").innerHTML = myPercent + "100%";
            					window.setTimeout("httpRequestFileUploadProgress("+strURL+");", 100);
            				} 
            				else 
            				{
            					document.getElementById("bytesRead").style.visibility = "hidden";
            					document.getElementById("progressBar").style.width = "100%";
            					document.getElementById("percentComplete").innerHTML = "File Uploading Done!";
            					document.getElementById("txtFile").value="";
            				}
            			}
            		} 
            		else 
            		{
            			alert(httpRequest.statusText);
            		}
			   }
            }
            catch(e)
			{
				alert("ERROR: there was a problem from the server response.\n(error string: "+e.description+") from httpRequestFileUploadProgress("+strURL+") in httpRequest.js");
			}
        }
        httpRequest.send(null);
    }
	//**********************************************************************************************
	//http://stackoverflow.com/questions/4856917/jquery-upload-progress-and-ajax-file-upload
	//var fd = new FormData;
	//fd.append('photo1', file);
	//fd.append('photo2', file2);
	//fd.append('other_data', 'foo bar');
	//xhr.send(fd);
	//**********************************************************************************************
	function httpRequestFileUpload(strURL, formData, strResultFunc) 
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
	            catch (e) 
	            {}
	        }
	    }
	
	    if (httpRequest==null) 
	    {
	        alert("ERROR :( Cannot create an XMLHTTP instance");
	        return;
	    }
	    
	    httpRequest.addEventListener('progress', function(e) 
	    {
	        var done = e.position || e.loaded, total = e.totalSize || e.total;
	        log('httpRequest progress: ' + (Math.floor(done/total*1000)/10) + '%');
	    }, false);
	    
	    if ( httpRequest.upload ) 
	    {
	    	httpRequest.upload.onprogress = function(e) 
	        {
	            var done = e.loaded || e.loaded, total = e.totalSize || e.total;
	            log('httpRequest.upload progress: ' + done + ' / ' + total + ' = ' + (Math.floor(done/total*1000)/10) + '%');
	        };
	    }
	    
        httpRequest.onreadystatechange = function(e)
        {	
        	try
        	{		
                if (this.readyState == 4) //the full server response is received with 4 when it is complete
                {	
                	var strResponse = this.responseText;
			        switch (this.status)
			        {
				        case 404: alert('ERROR: The requested URL ' + strURL + ' could not be found, httpRequestFileUpload(...) of httpRequest.js.');// Page-not-found error
	                  	 		  break;
				        case 500: handleErrorFullPage(strResponse);// Display results in a full window for server-side errors
				        		  break;
				        default:  eval(strResultFunc + '(strResponse);');// Call the desired result function
				                   break;
			        }               	
			   }//(httpRequest.readyState == 4)
            }//try
            catch(e)
			{
            	log("ERROR: "+e+" from httpRequestFileUpload("+strURL+") in httpRequest.js");
				alert("ERROR: there was a problem from the server response.\n(error string: "+e+")");
			}
        }

        httpRequest.open('POST', strURL, true);
        httpRequest.send(formData);
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