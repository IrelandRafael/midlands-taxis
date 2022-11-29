package com.ait.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dao.ManageStaffDao;
import com.ait.dto.StaffDto;

public class StaffEditServlet  extends HttpServlet {

	private static final long serialVersionUID = -2434065152506317447L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageStaffDao manageStaffDao = new ManageStaffDao();
		int staffId = Integer.valueOf(request.getParameter("staff_id"));
		String userName = request.getParameter("user_name");
		String email = request.getParameter("email");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String category = request.getParameter("category");
		
		StaffDto staffMember = new StaffDto(staffId, firstName, lastName, userName, email, category);
		boolean unique = manageStaffDao.areCredentialsUnique(userName, email, staffId);
		if (!unique) {
			request.setAttribute("staffMember", staffMember);
			request.setAttribute("invalidInputMsg", "The introduced username or email is already in use.");
			request.getRequestDispatcher("/pages/mng/edit-staff.jsp").forward(request, response);
			return;
		}
		
		if (!validateInput(firstName, lastName, category)) {
			request.setAttribute("staffMember", staffMember);
			request.setAttribute("invalidInputMsg", "All fields are required.");
			request.getRequestDispatcher("/pages/mng/edit-staff.jsp").forward(request, response);
			return;
		}
		
		manageStaffDao.updateStaffMember(staffMember);
		response.sendRedirect(request.getContextPath() + "/manage-staff");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageStaffDao manageStaffDao = new ManageStaffDao();
		int staffId = Integer.valueOf(request.getParameter("staffId"));
		StaffDto staffMember = manageStaffDao.getStaffMember(staffId);
		
		System.out.println(staffMember);
		
		request.setAttribute("staffMember", staffMember);
		request.getRequestDispatcher("/pages/mng/edit-staff.jsp")
			.forward(request, response);
	}

	private boolean validateInput(String firstName, String lastName, String category) {
		if (firstName.isEmpty() || lastName.isEmpty() || category.isEmpty()) {
			return false;
		}
		return true;
	}
}
