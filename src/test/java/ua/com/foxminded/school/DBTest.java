package ua.com.foxminded.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import org.junit.jupiter.api.Test;

class DBTest {

	@Test
	void createGroupsWithShuffle() throws SQLException {
		FillingDB filling = new FillingDB();
		filling.createGroupsWithShuffle();
	}
	
	@Test
	void createCourses() throws SQLException {
		FillingDB filling = new FillingDB();
		filling.createCourses();
	}
	
	@Test
	void createStudents() throws SQLException {
		FillingDB filling = new FillingDB();
		filling.createStudents();
	}
	
	@Test
	void fillGroupByStudents() throws SQLException {
		FillingDB filling = new FillingDB();
		filling.fillGroupByStudents();
	}
	
	@Test
	void fillSchedule() throws SQLException {
		FillingDB filling = new FillingDB();
		filling.fillSchedule();;
	}
	
	
	@Test
    void cleanGroupByStudent() throws SQLException {
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");
			
			String sql = "update  school.students SET group_id = NULL ";

			final Random random = new Random();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
}
