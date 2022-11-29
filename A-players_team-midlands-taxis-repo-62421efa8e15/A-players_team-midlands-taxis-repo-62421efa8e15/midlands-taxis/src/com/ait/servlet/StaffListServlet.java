package com.ait.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dao.ManageStaffDao;
import com.ait.dto.StaffDto;

public class StaffListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3289408353989665296L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManageStaffDao manageStaffDao = new ManageStaffDao();
		List<StaffDto> staffMembers = manageStaffDao.getAllStaffMembers();
		request.setAttribute("staffMembers", staffMembers);
		request.getRequestDispatcher("/pages/mng/management-staff.jsp")
			.forward(request, response);
	}

}

