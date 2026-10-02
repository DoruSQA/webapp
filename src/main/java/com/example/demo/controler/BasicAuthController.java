package com.example.demo.controler;


import java.io.IOException;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;


@RestController
public class BasicAuthController 
{
	
	@GetMapping("/test/resource2/auth")
	public void basicAuth3(@RequestHeader(name = "Authorization", required = false) String authHeader,
	                      HttpServletResponse response) throws IOException 
	{

	   
	    if (authHeader == null || !authHeader.startsWith("Basic ")) 
	    {
	        response.setHeader("WWW-Authenticate", "Basic realm=\"Area1\"");
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Unauthorized - please provide credentials");
	    }
	    else
	    {
	    	String base64Credentials = authHeader.substring("Basic ".length());
		    String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
		    String[] values = credentials.split(":", 2);
		    String username = values[0];
		    String password = values[1];
		    if ("jonny".equals(username) && "cage".equals(password)) 
		        response.getWriter().write("Welcome " + username);
		    else 
		    {    
		        response.setHeader("WWW-Authenticate", "Basic realm=\"Area1\"");
		        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // in case he click cancel... 
		        response.getWriter().write("Invalid credentials");
		    }
	    }
	 }//http://localhost:9090/test/resource2/auth
	
	//Browser-ul retrimite automat același GET către același endpoint dupa ce se introduc credentialele
	

	@GetMapping("/test/resource/auth")
	public void basicAuth(@RequestHeader(name = "Authorization", required = false) String authHeader,
	                      HttpServletResponse response) throws IOException {

	   
//	    if (authHeader == null || !authHeader.startsWith("Basic ")) {
//	        response.setHeader("WWW-Authenticate", "Basic realm=\"Area1\"");
//	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	        response.getWriter().write("Unauthorized - please provide credentials");
//	        return;
//	    }

	    
	    String base64Credentials = authHeader.substring("Basic ".length());
	    String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
	    String[] values = credentials.split(":", 2);
	    String username = values[0];
	    String password = values[1];

	    
	    if ("jonny".equals(username) && "cage".equals(password)) {
	        response.getWriter().write("Welcome " + username);
	    } else {
	        
	        response.setHeader("WWW-Authenticate", "Basic realm=\"Area1\"");
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Invalid credentials");
	    }
	} //http://localhost:9090/test/resource/auth

	@GetMapping("/test/auth") // //http://localhost:9090/test/auth
    public void basicAuth2(@RequestHeader(name = "Authorization", required = false) String authHeader, HttpServletResponse response) throws IOException 
	{
		String username="";
		String password ="";
			if(authHeader !=null)
			{
				String base64Credentials = authHeader.substring("Basic ".length());
			    String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
			    String[] values = credentials.split(":", 2);
			     username = values[0];
			     password = values[1];
			}
		 	
		    if ("jonny".equals(username) && "cage".equals(password)) {
		        response.getWriter().write("Welcome " + username);
		    } else {
		        
		        response.setHeader("WWW-Authenticate", "Basic realm=\"Area1\"");
		        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		        response.getWriter().write("Invalid credentials");
		    }
    }
	
}
