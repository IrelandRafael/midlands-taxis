package com.ait.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dao.ManageStaffDao;
import com.ait.dto.StaffDto;

public class StaffManageServlet extends HttpServlet {

	private static final long serialVersionUID = -2434065152506317447L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageStaffDao manageStaffDao = new ManageStaffDao();
		String password = request.getParameter("password");
		String confirmedPassword = request.getParameter("confirm_password");
		if (password.isEmpty() || !password.equals(confirmedPassword)) {
			request.setAttribute("invalidInputMsg", "The introduced passwords dont match.");
			request.getRequestDispatcher("/pages/mng/edit-staff.jsp")
			.forward(request, response);
			return;
		}
		
		String userName = request.getParameter("user_name");
		String email = request.getParameter("email");
		boolean unique = manageStaffDao.areCredentialsUnique(userName, email);
		if (!unique) {
			request.setAttribute("invalidInputMsg", "The introduced username or email is already in use.");
			request.getRequestDispatcher("/pages/mng/edit-staff.jsp")
			.forward(request, response);
			return;
		}
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String category = request.getParameter("category");
		
		if (!validateInput(firstName, lastName, category)) {
			request.setAttribute("invalidInputMsg", "All fields are required.");
			request.getRequestDispatcher("/pages/mng/edit-staff.jsp?invalid=true")
			.forward(request, response);
			return;
		}
		StaffDto staffDto = new StaffDto(firstName, lastName, userName, email, category, password);
		System.out.println(staffDto);
		
		manageStaffDao.createNewStaffMember(staffDto);
		response.sendRedirect(request.getContextPath() + "/manage-staff");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageStaffDao manageStaffDao = new ManageStaffDao();
		int staffId = Integer.valueOf(request.getParameter("staffId"));
		manageStaffDao.deleteStaffMember(staffId);
		response.sendRedirect(request.getContextPath() + "/manage-staff");
	}

	private boolean validateInput(String firstName, String lastName, String category) {
		if (firstName.isEmpty() || lastName.isEmpty() || category.isEmpty()) {
			return false;
		}
		return true;
	}
}
