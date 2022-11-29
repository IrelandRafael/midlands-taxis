package com.ait.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dao.StaffLoginDao;
import com.ait.dto.UserDto;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 270285853117219483L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("user_name");
		String password = request.getParameter("password");

		System.out.println("Check credentials for username: " + userName + " and password: " + password);

		StaffLoginDao staffLoginDao = new StaffLoginDao();
		UserDto user = staffLoginDao.validateUserCredentials(userName, password);
		
		System.out.println("Authorized user: " + user);

		if (user == null) {
			request.setAttribute("invalidInputMsg", "Invalid username or password.");
			request.getRequestDispatcher("/staff-login.jsp").forward(request, response);
			return;
		}
		
		request.getSession(true).setAttribute("userSession", user);

		switch (user.getCategory()) {
			case MANAGEMENT: {
				response.sendRedirect(request.getContextPath() + "/manage-staff");
				break;
			}
			case FRONT_DESK: {
				response.sendRedirect(request.getContextPath() + "/bookings");
				break;
			}
			case DRIVER: {
				response.sendRedirect(request.getContextPath() + "/driver-bookings");
				break;
			}
			default:{
				response.sendRedirect(request.getContextPath() + "/staff-login.jsp");
				break;
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).invalidate();
		response.sendRedirect(request.getContextPath() + "/staff-login.jsp");
	}
}
