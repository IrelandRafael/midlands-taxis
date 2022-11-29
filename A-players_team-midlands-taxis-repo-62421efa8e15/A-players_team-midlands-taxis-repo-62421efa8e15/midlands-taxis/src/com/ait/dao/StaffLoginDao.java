package com.ait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ait.dto.UserCategory;
import com.ait.dto.UserDto;

public class StaffLoginDao {

	public UserDto validateUserCredentials(String userName, String password) {
		Connection connection = null;
		try {
			connection = DatabaseConnectionUtils.getDBConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT id, category FROM staff WHERE username=? AND password=?");
			statement.setString(1, userName);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int userId = resultSet.getInt("id");
				String userCategory = resultSet.getString("category");
				return new UserDto(userId, UserCategory.valueOf(userCategory));
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
		return null;
	}

}
