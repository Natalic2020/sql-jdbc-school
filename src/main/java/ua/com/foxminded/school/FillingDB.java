package ua.com.foxminded.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.management.monitor.StringMonitor;

public class FillingDB {

	public void createGroups() throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			Statement statement = connection.createStatement();

			String sql = "insert into school.groups (group_name)" + "values" + "('gr-'||floor(random()*(99-1+1))+1)";

			for (int i = 0; i < 10; i++) {
				statement.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void createGroupsWithShuffle() throws SQLException {
		AuxiliaryValue value = new AuxiliaryValue();
		List<String> listWithValue = value.randomValue(10);

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			String sql = "insert into school.groups (group_name)" + "values" + "('gr-'||?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			for (String i : listWithValue) {
				statement.setString(1, i);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void createCourses() throws SQLException {

		AuxiliaryValue coursesValue = new AuxiliaryValue();
		String[] courses = coursesValue.courses();

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			String sql = "insert into school.courses (course_name)" + "values" + "(?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0; i < courses.length; i++) {
				statement.setString(1, courses[i]);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void createStudents() throws SQLException {

		AuxiliaryValue bdValue = new AuxiliaryValue();
		String[] firstNames = bdValue.firstName();
		String[] lastNames = bdValue.lastName();

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			String sql = "insert into school.students (first_name, last_name)" + "values" + "(?,?)";

			final Random random = new Random();
			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0; i < 200; i++) {
				statement.setString(1, firstNames[random.nextInt(19)]);
				statement.setString(2, lastNames[random.nextInt(19)]);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void fillGroupByStudents() throws SQLException {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			String sql = "update  school.students SET group_id = ?" + "where student_id = ?";

			final Random random = new Random();
			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0; i < 200; i++) {
				statement.setInt(1, random.nextInt(9) + 1);
				statement.setInt(2, random.nextInt(199) + 1);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void fillSchedule() throws SQLException {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			String sql = "insert into school.schedule (course_id, student_id)" + "values" + "(?,?)";

			final Random random = new Random();
			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0; i < 200; i++) {
				int quantityCourses = random.nextInt(3);
				System.out.println(quantityCourses);
				for (int j = 0; j <= quantityCourses; j++) {
					statement.setInt(1, random.nextInt(9) + 1);
					statement.setInt(2, i + 1);
					statement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}
