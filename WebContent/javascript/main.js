
// Function to check IE version.
function msieversion() 
{
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");

    if (msie > 0) // If Internet Explorer, return version number
    	return true;
    else  // If another browser, return 0
    	return false;
}

// Function to check IE version.
function msieversion11() 
{

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))  // If Internet Explorer, return version number
        return true;
    else  // If another browser, return 0
        return false;

}

var is_ms_browser = (msieversion() || msieversion11);

// If browser is IE, throw not supported message.
if(document.cookie == null && is_ms_browser)
{
	document.cookie = "warning_shown=true;";
	
	window.alert("Software Eureka: Your browser is out of date! Please update to a newer web browser, like google chrome or firefox. \n\n Some designs won't display correctly.");
}


//AJAX call for visit counting.
function incrementVisit()
{
    try
    {
    	var async_visit_call = new XMLHttpRequest();
        async_visit_call.addEventListener("readystatechange", null, false);
        async_visit_call.open('GET', 'visit', true);
        async_visit_call.send(null);
    }
    catch(exception)
    {
		alert("Request failed");
    }
}


// Add listener to fire AJAX call. 
window.addEventListener("load", incrementVisit(), false);

