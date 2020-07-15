package ua.com.foxminded.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import org.junit.jupiter.api.Test;

class DBTest {

//	@Test
//	void createGroupsWithShuffle()  {
//		FillingDB filling = new FillingDB();
//		filling.createGroupsWithShuffle();
//	}
//	
//	@Test
//	void createCourses()  {
//		FillingDB filling = new FillingDB();
//		filling.createCourses();
//	}
//	
//	@Test
//	void createStudents()  {
//		FillingDB filling = new FillingDB();
//		filling.createStudents();
//	}
//	
//	@Test
//	void fillGroupByStudents()  {
//		FillingDB filling = new FillingDB();
//		filling.fillGroupByStudents();
//	}
//	
//	@Test
//	void fillSchedule()  {
//		FillingDB filling = new FillingDB();
//		filling.fillSchedule();;
//	}
//	
//	
//	@Test
//    void cleanGroupByStudent()  {
//		
//		Connection connection = null;
//		try {
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
//			System.out.println("Connenc PostgreSQL");
//			
//			String sql = "update  school.students SET group_id = NULL ";
//
//			final Random random = new Random();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			
//			statement.executeUpdate();
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	
}
