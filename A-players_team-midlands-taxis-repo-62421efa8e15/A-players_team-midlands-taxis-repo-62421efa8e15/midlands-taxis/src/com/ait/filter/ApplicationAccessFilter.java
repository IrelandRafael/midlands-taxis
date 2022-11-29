package com.ait.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dto.UserCategory;
import com.ait.dto.UserDto;

public class ApplicationAccessFilter implements Filter {

	private UserCategory userCategory;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		String category = filterConfig.getInitParameter("category");
		userCategory = UserCategory.valueOf(category);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		UserDto user = (UserDto) httpRequest.getSession().getAttribute("userSession");
		System.out.println("app access, user: " + user + ", category: " + userCategory);
		if (user == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/staff-login.jsp");
		} else if (!isAuthorized(user)) {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	    } else {
	    	filterChain.doFilter(request, response);
	    }
	}

	private boolean isAuthorized(UserDto user) {
		return user.getCategory().equals(userCategory);
	}

	public void destroy() {
	}

}
