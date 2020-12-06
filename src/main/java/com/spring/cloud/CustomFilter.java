package com.spring.cloud;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//@Component
public class CustomFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Enumeration<String> attributeNames = request.getAttributeNames();
		
		//attributeNames.asIterator().forEachRemaining(attr -> System.out.println(attr + ".." + request.getAttribute(attr)));
		
		//System.out.println("auth type : " + request.getAuthType());
		
		request.getHeaderNames().asIterator().forEachRemaining(attr -> System.out.println(attr + ".." + request.getHeader(attr)));
		
		filterChain.doFilter(request, response);
	}

}
