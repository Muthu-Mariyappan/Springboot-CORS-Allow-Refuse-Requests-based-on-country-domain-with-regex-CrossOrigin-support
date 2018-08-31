## Springboot-CORS-Allow-Refuse-Requests-based-on-country-domain-with-regex-CrossOrigin-support
-----------------------------------------------------------------------------------------------

	This project is simple greeting app, which greets user with greeting message. 
	But the goal of this project is demonstrate the use of CORS and regex in RestAPI.

### What is CORS?
------------------

###### From wikipedia 

	'Cross-origin resource sharing (CORS) is a mechanism that allows restricted resources on a web page to be 
	requested from another domain outside the domain from which the first resource was served.'
	https://en.wikipedia.org/wiki/Cross-origin_resource_sharing
	
	Due to same origin security policy, web application on another domain cannot access resources of some another domain.
	But by enabling CORS in our web app we can open our API to the public or intended party.
	
	
	
#### Access-Control-Allow-Origin

	This header must be present in all cross origin requests, with allowed origin value. 
	It can be set to '*'. This will make the API open to public. 
	
	But in this project, our goal is to allow requests only from specific country domain or 
	reject requests from specific country domain. 
	
	Access-Control-Allow-Origin will only take absolute value such as http://google.co.in or '*'.
	But we need to allow requests only originating from france. So allow only domains ending in xxxx.fr/
	
	But CORS implementation of Springboot/java won't take regular expression.
	
#### Regex in CORS 

	To achieve the goal, following method is followed. 
	
	> Specify the pattern to be matched
	> Get the origin from request header
	> compare origin with pattern, if it matches then add that origin to Access-Control-Allow-Origin header.

	To achieve this new RequestFilter is used to do pattern matching and to add CORS headers.
	
	This project used CORSFilter proposed by
	https://github.com/predix/spring-cors-filter/blob/master/src/main/java/com/ge/predix/web/cors/CORSFilter.java
	https://stackoverflow.com/questions/42162874/spring-cors-add-pattern-in-the-allowed-origins
	
	You can find more about regex in application.properties file

#### Update in predix's CORSFilter

	For some reason, i was not able do successful pattern matching of allowed origins, since it completely opened to API to public, or completely refuses.
	To overcome this, i added few lines
	
	String orig = request.getHeader("origin");
        for(Pattern patt:this.corsXhrAllowedOriginPatterns) {	
	        if(Pattern.matches(patt.pattern(), orig)) {
	        	response.addHeader("Access-Control-Allow-Origin", orig);
	        	break;
	        }
        }
		
	You can find more on CORSFilter.java file
	
#### Testing out CORS 

	To test CORS, create a dummy consumer app that calls the rest api from another origin.(eg different port no)
	For more:https://www.youtube.com/watch?v=D4tnEwxWAAs
	