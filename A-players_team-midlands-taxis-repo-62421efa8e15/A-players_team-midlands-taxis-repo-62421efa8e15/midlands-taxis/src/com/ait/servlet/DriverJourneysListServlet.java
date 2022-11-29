package com.ait.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.dao.DriversJourneysDao;
import com.ait.dto.DriverJourneyDto;

public class DriverJourneysListServlet extends HttpServlet {

	private static final long serialVersionUID = -5670294646017620712L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DriversJourneysDao driversJourney = new DriversJourneysDao();
		
		List<DriverJourneyDto> driverJourneys = driversJourney.getAllDriversJourneys();

		request.setAttribute("driverJourneys", driverJourneys);
		request.getRequestDispatcher("/pages/mng/driver-journeys.jsp")
			.forward(request, response);
	}
	
}
