package com.ait.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dao.BookingsDao;
import com.ait.dto.BookingDto;
import com.ait.dto.DriverDto;


public class BookingManageServlet extends HttpServlet {

	private static final long serialVersionUID = 2015339036345286912L;
	private static final String PATTERN = "dd/MM/yyyy HH:mm";
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(PATTERN, Locale.ENGLISH);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookingDto booking = new BookingDto();
		String pickupLocation = request.getParameter("pickup_location");
		String pickupTime = request.getParameter("pickup_time");
		String dropLocation = request.getParameter("drop_location");
		String selectedDriver = request.getParameter("driver");

		booking.setPickupLocation(pickupLocation);
		booking.setDropLocation(dropLocation);
		request.setAttribute("booking", booking);
		if (pickupLocation.isEmpty() || selectedDriver.isEmpty()) {
			request.setAttribute("invalidInputMsg", "Pickup location and assign a driver fields are mandatory.");
			request.getRequestDispatcher("/pages/fd/add-booking.jsp")
			.forward(request, response);
			return;
		}
		booking.setDriverId(Integer.valueOf(selectedDriver));
		if (!pickupTime.isEmpty()) {
			Date pickupDateTime = covertPickupTimeToDate(pickupTime);
			booking.setPickupTime(pickupDateTime);
			if (pickupDateTime == null) {
				request.setAttribute("invalidInputMsg", "The Pickup time should be represented as '01/12/2019 07:00'.");
				request.getRequestDispatcher("/pages/fd/add-booking.jsp")
				.forward(request, response);
				return;
			}
		}
		BookingsDao bookingDao = new BookingsDao();
		bookingDao.addBooking(booking);
		response.sendRedirect(request.getContextPath() + "/bookings");
	
	}
	
	private static Date covertPickupTimeToDate(String pickupTime) {
		try {
			return FORMATTER.parse(pickupTime);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookingsDao bookingDao = new BookingsDao();
		List<DriverDto> availableDrivers = bookingDao.getAvailableDrivers();
		
		request.getSession().setAttribute("availableDrivers", availableDrivers);
		request.getRequestDispatcher("/pages/fd/add-booking.jsp")
			.forward(request, response);
	}

}
