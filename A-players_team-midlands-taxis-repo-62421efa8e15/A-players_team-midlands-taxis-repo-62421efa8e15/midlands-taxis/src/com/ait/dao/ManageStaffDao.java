package com.ait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ait.dto.StaffDto;

public class ManageStaffDao {

	public List<StaffDto> getAllStaffMembers() {
		Connection connection = null;
		List<StaffDto> staffMembers = new ArrayList<>();
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
            Statement statement = connection.createStatement();
 			ResultSet resultSet = statement.executeQuery("SELECT id, firstName, lastName, username, email, category FROM staff");
 			while(resultSet.next()) {
 				int staffId = resultSet.getInt("id");
 				String firstName = resultSet.getString("firstName");
 				String lastName = resultSet.getString("lastName");
 				String username = resultSet.getString("username");
 				String email = resultSet.getString("email");
 				String userCategory = resultSet.getString("category");
 				
 				StaffDto staff = new StaffDto(staffId, firstName, lastName, username, email, userCategory);
 				staffMembers.add(staff);
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
		return staffMembers;
	}
	
	public void createNewStaffMember(StaffDto staffDto) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO staff(firstName, lastName, username, password, email, category) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
			statement.setString(1, staffDto.getFirstName());
			statement.setString(2, staffDto.getLastName());
			statement.setString(3, staffDto.getUsername());
			statement.setString(4, staffDto.getPassword());
			statement.setString(5, staffDto.getEmail());
			statement.setString(6, staffDto.getUserCategory().name());
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
	
	public void updateStaffMember(StaffDto staffDto) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			String sqlQuery = "UPDATE staff SET firstName=?, lastName=?, username=?, email=?, category=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, staffDto.getFirstName());
			statement.setString(2, staffDto.getLastName());
			statement.setString(3, staffDto.getUsername());
			statement.setString(4, staffDto.getEmail());
			statement.setString(5, staffDto.getUserCategory().name());
			statement.setInt(6, staffDto.getStaffId());
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

	public boolean areCredentialsUnique(String userName, String email) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			String sqlQuery = "SELECT id FROM staff WHERE username=? OR email=?";
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, userName);
			statement.setString(2, email);
			ResultSet resultSet = statement.executeQuery();
			return !resultSet.next();
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
		return false;
	}
	
	public boolean areCredentialsUnique(String userName, String email, int staffId) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			String sqlQuery = "SELECT id FROM staff WHERE id!=? AND (username=? OR email=?)";
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, staffId);
			statement.setString(2, userName);
			statement.setString(3, email);
			ResultSet resultSet = statement.executeQuery();
			return !resultSet.next();
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
		return false;
	}

	public StaffDto getStaffMember(int staffId) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT id, firstName, lastName, username, email, category FROM staff WHERE id=?");
			statement.setInt(1, staffId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				staffId = resultSet.getInt("id");
 				String firstName = resultSet.getString("firstName");
 				String lastName = resultSet.getString("lastName");
 				String username = resultSet.getString("username");
 				String email = resultSet.getString("email");
 				String userCategory = resultSet.getString("category");
 				
 				return new StaffDto(staffId, firstName, lastName, username, email, userCategory);
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
		return new StaffDto();
	}

	public void deleteStaffMember(int staffId) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			String sqlQuery = "DELETE FROM staff WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, staffId);
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
