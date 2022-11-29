package com.ait.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dao.BookingsDao;
import com.ait.dto.BookingDto;
import com.ait.dto.UserDto;

public class DriverBookingsServlet extends HttpServlet {

	private static final long serialVersionUID = -2549992180719047510L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDto driverUser = (UserDto) request.getSession(true).getAttribute("userSession");
		BookingsDao bookingsDao = new BookingsDao();
		
		List<BookingDto> bookings = bookingsDao.getDriverBookings(driverUser.getId());
		
		request.setAttribute("bookings", bookings);
		request.getRequestDispatcher("/pages/driver/driver.jsp")
			.forward(request, response);
	}

}
