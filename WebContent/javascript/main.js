function msieversion() 
{
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");

    if (msie > 0) // If Internet Explorer, return version number
    	return true;
    else  // If another browser, return 0
    	return false;
}

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
	
	window.alert("Software Eureka: Your browser is out of date! Please update to a newer web browser, like google chrome or firefox. \n\nWe cannot" +
			" ensure your experience will be the best the site has to offer.");
}

var asyncRequest;

function start()
{
    try
    {
        asyncRequest = new XMLHttpRequest();
        asyncRequest.addEventListener("readystatechange", null, false);
        asyncRequest.open('GET', 'visit', true);    //   /Test is url to Servlet!
        asyncRequest.send(null);
    }
    catch(exception)
    {
		alert("Request failed");
    }
}

window.addEventListener("load", start(), false);

