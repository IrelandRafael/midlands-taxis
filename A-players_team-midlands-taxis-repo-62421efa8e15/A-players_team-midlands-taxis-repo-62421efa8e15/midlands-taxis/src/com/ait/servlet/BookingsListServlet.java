package com.ait.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dao.BookingsDao;
import com.ait.dto.BookingDto;

public class BookingsListServlet extends HttpServlet {

	private static final long serialVersionUID = -2130703715830739894L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookingsDao bookingsDao = new BookingsDao();
		List<BookingDto> bookings = bookingsDao.getAllBookings();
		
		request.setAttribute("bookings", bookings);
		request.getRequestDispatcher("/pages/fd/front-desk.jsp")
			.forward(request, response);
	}

}
