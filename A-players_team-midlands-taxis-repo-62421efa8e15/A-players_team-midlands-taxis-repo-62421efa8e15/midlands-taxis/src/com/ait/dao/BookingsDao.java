package com.ait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ait.dto.BookingDto;
import com.ait.dto.DriverDto;
import com.ait.dto.JourneyStatus;

public class BookingsDao {

	public List<BookingDto> getAllBookings() {
		Connection connection = null;
		List<BookingDto> bookings = new ArrayList<>();
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT b.id, b.booking_no, b.pickup_location, b.drop_location, b.pickup_time, " + 
            		"dj.journey_status, d.firstName, d.lastName " + 
            		"FROM bookings AS b INNER JOIN driver_journeys AS dj ON dj.booking_id=b.id " + 
            		"INNER JOIN staff AS d ON dj.driver_id=d.id ORDER BY id DESC";
 			ResultSet resultSet = statement.executeQuery(query);
 			while(resultSet.next()) {
 				BookingDto booking = new BookingDto();
 				booking.setBookingId(resultSet.getInt("id"));
 				booking.setBookingNo(resultSet.getString("booking_no"));
 				booking.setPickupLocation(resultSet.getString("pickup_location"));
 				booking.setDropLocation(resultSet.getString("drop_location"));
 				booking.setPickupTime(resultSet.getTimestamp("pickup_time"));
 				booking.setStatus(resultSet.getString("journey_status"));
 				String firstName = resultSet.getString("firstName");
 				String lastName = resultSet.getString("lastName");
 				booking.setDriverName(firstName + " " + lastName);
 				bookings.add(booking);
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
		return bookings;
	}
	
	public List<BookingDto> getDriverBookings(int driverId) {
		Connection connection = null;
		List<BookingDto> bookings = new ArrayList<>();
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
            
            String query = "SELECT b.id, b.booking_no, b.pickup_location, b.drop_location, b.pickup_time, dj.journey_status " + 
            		"FROM bookings AS b INNER JOIN driver_journeys AS dj ON dj.booking_id=b.id AND dj.driver_id=? ORDER BY b.booking_no DESC";
            PreparedStatement statement = connection.prepareStatement(query);
    		statement.setInt(1, driverId);
            ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()) {
 				BookingDto booking = new BookingDto();
 				booking.setBookingId(resultSet.getInt("id"));
 				booking.setBookingNo(resultSet.getString("booking_no"));
 				booking.setPickupLocation(resultSet.getString("pickup_location"));
 				booking.setDropLocation(resultSet.getString("drop_location"));
 				booking.setPickupTime(resultSet.getTimestamp("pickup_time"));
 				booking.setStatus(resultSet.getString("journey_status"));
 				bookings.add(booking);
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
		return bookings;
	}
	
	public List<DriverDto> getAvailableDrivers() {
		Connection connection = null;
		List<DriverDto> availableDrivers = new ArrayList<>();
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			String query = "SELECT id, firstName, lastName FROM staff WHERE category='DRIVER' " + 
					"AND id NOT IN (SELECT d.id FROM staff as d INNER JOIN driver_journeys as dj ON d.id=dj.driver_id AND dj.journey_status='STARTED')";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int driverId = resultSet.getInt("id");
				String firstName = resultSet.getString("firstName");
 				String lastName = resultSet.getString("lastName");
 				DriverDto driver = new DriverDto(driverId, firstName + " " + lastName);
 				availableDrivers.add(driver);
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
		return availableDrivers;
		
	}

	public void addBooking(BookingDto booking) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			String bookingNo = generateBookingNo(connection);
			booking.setBookingNo(bookingNo);
			int bookingId = createNewBooking(booking, connection);
			if (bookingId > 0) {
				createDriverJourney(bookingId, booking.getDriverId(), connection);
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
	}

	private void createDriverJourney(int bookingId, int driverId, Connection connection) throws SQLException {
		String query = "INSERT INTO driver_journeys(journey_status, booking_id, driver_id) VALUES (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, "PENDING");
		statement.setInt(2, bookingId);
		statement.setInt(3, driverId);
		statement.executeUpdate();
		
	}

	private int createNewBooking(BookingDto booking, Connection connection) throws SQLException {
		String query = "INSERT INTO bookings(booking_no, pickup_location, drop_location, pickup_time) VALUES (?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, booking.getBookingNo());
		statement.setString(2, booking.getPickupLocation());
		statement.setString(3, booking.getDropLocation());
		if (booking.getPickupTime() != null) {
			statement.setTimestamp(4, new Timestamp(booking.getPickupTime().getTime()));
		}
		if (statement.executeUpdate() > 0) {
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if ( generatedKeys.next() ) {
                return generatedKeys.getInt(1);
            }
        }
		return 0;
	}

	private static String generateBookingNo(Connection connection) {
		int lastBookingId = 0;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			String sqlQuery = "SELECT MAX(id) FROM bookings";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			
			if (resultSet.next()) {
				lastBookingId = resultSet.getInt(1);
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
		return "MTX" + (++lastBookingId);
	}

	public void startJourney(int driverId, int bookingId) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			String sqlQuery = "UPDATE driver_journeys SET journey_status=?, journey_startedTime=? WHERE driver_id=? AND booking_id=?";
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, JourneyStatus.STARTED.name());
			statement.setTimestamp(2, new Timestamp(new Date().getTime()));
			statement.setInt(3, driverId);
			statement.setInt(4, bookingId);
			statement.executeUpdate();
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
	}

	public void completeJourney(int driverId, int bookingId) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			String sqlQuery = "UPDATE driver_journeys SET journey_status=?, journey_completedTime=? WHERE driver_id=? AND booking_id=?";
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, JourneyStatus.COMPLETED.name());
			statement.setTimestamp(2, new Timestamp(new Date().getTime()));
			statement.setInt(3, driverId);
			statement.setInt(4, bookingId);
			statement.executeUpdate();
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
	}
}
