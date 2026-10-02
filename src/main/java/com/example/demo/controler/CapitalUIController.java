package com.example.demo.controler;


import java.util.HashMap;
import java.util.UUID;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import others.User;

@Controller
public class CapitalUIController 
{
		HashMap<String, User> usersDB = new HashMap<>();
		HashMap<String, String> usersAccess = new HashMap<>();
		{	
			usersDB.put("Jonny",new User().setName("Jonny").setPassword("Cage").setAcces("admin") );
			usersDB.put("Zen",new User().setName("Zen").setPassword("Dao").setAcces("normal") );
			
		
		}
		 // LOGIN
	    @PostMapping("/loginCheck")
	    public String handleForm(@RequestParam String username,
	                             @RequestParam String password,
	                             HttpServletResponse response) {
	    
	        User user = usersDB.get(username); // unique user name ! 

	        if (user != null && user.getPassword().equals(password)) {

	            String randomToken = UUID.randomUUID().toString(); // generate new random session id
	            Cookie cookie = new Cookie("user", randomToken);

	            usersAccess.put(randomToken, username);

	            cookie.setHttpOnly(true);
	            cookie.setPath("/");
	            cookie.setMaxAge(60 * 60);

	            response.addCookie(cookie);

	            return "redirect:/homepage";
	        }

	        return "loginTym"; // login failed
	    }
	
	    // HOMEPAGE
	    @GetMapping("/homepage")
	    public String homePage(@CookieValue(value = "user", required = false) String cookieValue) 
	    {
	    	
//	    	try {
//				Thread.sleep(60);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
	        if (cookieValue != null && usersAccess.containsKey(cookieValue)) {
	            return "homepage";
	        }

	        return "loginForm"; // server side rendering
	    } // http://192.168.0.101:9090/homepage

	    // ADMIN PAGE
	    @GetMapping("/homepage/admin")
	    public String adminPage(@CookieValue(value = "user", required = false) String cookieValue) {

	        if (cookieValue != null && usersAccess.containsKey(cookieValue)) {

	            String username = usersAccess.get(cookieValue);
	            User user = usersDB.get(username);

	            if (user != null && "admin".equals(user.getAcces())) {
	                return "admPage";
	            }
	        }

	        return "errorPage";
	    }

	    // CLIENT PAGE
	    @GetMapping("/homepage/client")
	    public String clientPage(@CookieValue(value = "user", required = false) String cookieValue) {

	        if (cookieValue != null && usersAccess.containsKey(cookieValue)) {

	            String username = usersAccess.get(cookieValue);
	            User user = usersDB.get(username);

	            if (user != null &&
	                ("admin".equals(user.getAcces()) || "normal".equals(user.getAcces()))) {
	                return "clientPage";
	            }
	        }

	        return "errorPage";
	    }
	 
	 
	@GetMapping("/test/motanel")
	public String testmotanel(HttpServletRequest request, HttpServletResponse response)
	{
		String username="z",password="z";
		/*
		
		String header_basic= request.getHeader("Authorization");
		if (header_basic != null && header_basic.startsWith("Basic")) {
		    String base64 = header_basic.substring(6);
		    String decoded = new String(Base64.getDecoder().decode(base64));

		    String[] parts = decoded.split(":");
		    username = parts[0];
		    password = parts[1];
		    System.out.println(username);
		    System.out.println(password);
		  
		}
		*/
	    if(username.equals("jonny")&&password.equals("cage"))
	    	 return "redirect:/homepage.html";
	    else if (username.equals("z")&&password.equals("z")) // first time on the server 
	    	return "redirect:/loginForm.html"; // redirection 
	    else
	    	return "loginTym"; // re-rendering of the page
	    	 
		/*
        // Creăm cookie-ul
        Cookie cookie = new Cookie("session2", "motanel"); // nume=valoare
        cookie.setHttpOnly(true);  // JS nu poate accesa
        cookie.setSecure(true);    // doar HTTPS (poți pune false la test local)
        cookie.setPath("/");       // disponibil pe tot site-ul
        cookie.setMaxAge(60 * 30); // 30 minute expirare
      //cookie.setSameSite("Strict"); // doar pe același site

        // Adăugăm cookie-ul la răspuns
        response.addCookie(cookie);
		
		String header_custom= request.getHeader("Test");
		if(!"tel".equals(header_custom))
			return "Don't work";
		else
			return "Merge... by motanel";
		*/
       
        
	} // Get http://localhost:9090//test/motanel
	
}
