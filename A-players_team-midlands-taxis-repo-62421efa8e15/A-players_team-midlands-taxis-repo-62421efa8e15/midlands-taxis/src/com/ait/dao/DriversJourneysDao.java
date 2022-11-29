package com.ait.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ait.dto.DriverJourneyDto;

public class DriversJourneysDao {
	
	public List<DriverJourneyDto> getAllDriversJourneys(){
		List<DriverJourneyDto> driveJourneys = new ArrayList<>();
		
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
            Statement statement = connection.createStatement();
 			ResultSet resultSet = statement.executeQuery(
 					"SELECT d.firstName, d.lastName, b.booking_no, "
 					+ "dj.journey_status, dj.journey_startedTime, dj.journey_completedTime" + 
 					"    FROM driver_journeys as dj "
 					+ "INNER JOIN staff as d ON dj.driver_id=d.id "
 					+ "INNER JOIN bookings as b ON dj.booking_id=b.id ORDER BY b.booking_no DESC");
 			
 			while(resultSet.next()) {
 				String firstName = resultSet.getString("firstName");
 				String lastName = resultSet.getString("lastName");
 				String bookingNumber = resultSet.getString("booking_no");
 				String status = resultSet.getString("journey_status");
 				Date startedTime = resultSet.getTimestamp("journey_startedTime");
 				Date completedTime = resultSet.getTimestamp("journey_completedTime");
 				
 				DriverJourneyDto driveJourney = new DriverJourneyDto(firstName, lastName, bookingNumber, status, startedTime, completedTime);
 				driveJourneys.add(driveJourney);
 			}
		} catch (Exception e) {
			System.out.print("Failed to initialise DB Connection, " + e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.print("Failed to close DB Connection, " + e);
				}
			}
		}
		return driveJourneys;
	}

}
