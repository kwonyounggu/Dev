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
    function loginRequest(strURL, strSubmit, strResultFunc)
    {
		httpRequestPost(strURL, strSubmit, strResultFunc);
    }
    function logoutRequest(strURL, strSubmit, strResultFunc)
    {
		httpRequestPost(strURL, strSubmit, strResultFunc);
    }
	function menuRequest(strURL, strSubmit, strResultFunc)
    {
		httpRequestPost(strURL, strSubmit, strResultFunc);
    }
	function menuMainResponse(strResponse)
	{
		//alert(strResponse);
		document.getElementById('divBody').innerHTML = strResponse;
	}
	function menuSubMainResponse(strResponse)
	{
		//alert(strResponse);
		document.getElementById('subLhsMenu').innerHTML = strResponse;
	}