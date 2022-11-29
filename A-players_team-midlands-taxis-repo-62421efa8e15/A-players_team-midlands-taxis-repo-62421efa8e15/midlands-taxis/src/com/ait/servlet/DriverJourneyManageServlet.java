package com.ait.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dao.BookingsDao;
import com.ait.dto.JourneyStatus;
import com.ait.dto.UserDto;


public class DriverJourneyManageServlet extends HttpServlet {

	private static final long serialVersionUID = 481021511652819995L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDto driverUser = (UserDto) request.getSession(true).getAttribute("userSession");
		int bookingId = Integer.valueOf(request.getParameter("bookingId"));
		JourneyStatus journeyStatus = JourneyStatus.valueOf(request.getParameter("status"));
		BookingsDao bookingDao = new BookingsDao();
		if (journeyStatus.equals(JourneyStatus.PENDING)) {
			bookingDao.startJourney(driverUser.getId(), bookingId);
		} else if (journeyStatus.equals(JourneyStatus.STARTED)) {
			bookingDao.completeJourney(driverUser.getId(), bookingId);
		}
		
		request.getRequestDispatcher("/driver-bookings")
			.forward(request, response);
	}

}
