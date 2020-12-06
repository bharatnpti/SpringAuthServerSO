package com.spring.cloud;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserInfoController {
	
	  @GetMapping(value="/userinfo")
	    public HashMap<String, Object> user(Principal principal) {
	       
		  System.err.println("UserInfoController.user()" + principal); 
		  
	        	HashMap<String, Object>  userInfoMap= new HashMap<>();
				
				  userInfoMap.put("customName", principal.getName());
				  userInfoMap.put("authorities",
				  SecurityContextHolder.getContext().getAuthentication().getAuthorities());
				 
	        	

				 userInfoMap.put("customName2", "bharatnpti");
				 userInfoMap.put("authorities2", "read2");
				 
	        	
	          return userInfoMap;
	     
	    }
	
	/*
	  @GetMapping("/userinfo")
	public MyAppUser userInfo(Principal principal) {
		
	
		
		
		System.out.println("UserInfoController.userInfo()");
		return new MyAppUser(principal.getName());
		
	}
	
	*/

}
