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
import java.util.Map;
import java.util.Random;

public class FillingDB {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "1234";
	
	public void fillAllDB() throws SQLException {
			createGroupsWithShuffle();
			createCourses();
			createStudents();
			fillGroupByStudents();
			fillSchedule();
	}

	public void createGroupsWithShuffle()  {
		AuxiliaryValue value = new AuxiliaryValue();
		List<String> listWithValue = value.randomValue(10);

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
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
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void createCourses()  {

		AuxiliaryValue coursesValue = new AuxiliaryValue();
		String[] courses = coursesValue.courses();

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
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
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void createStudents()  {

		AuxiliaryValue bdValue = new AuxiliaryValue();
		List<Map<String, String>> names = bdValue.fillNames();

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			String sql = "insert into school.students (first_name, last_name)" + "values" + "(?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			
			for (Map<String, String> name : names) {
				statement.setString(1, name.get("firstName"));
				statement.setString(2, name.get("lastName"));
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void fillGroupByStudents()  {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			List<Integer> groups = receiveAllGroupsID();
			List<Integer> students = receiveAllStudentsRandom();
			
			String sql = "update  school.students SET group_id = ?" + "where student_id = ?";

			final Random random = new Random();
			PreparedStatement statement = connection.prepareStatement(sql);
			int j = 0;
            for (Integer groupID : groups) {
				int countStudent = random.nextInt(21);
				if (countStudent == 0) {
					continue;
				}
				
				for (int i = 0; i < countStudent + 9; i++) {
					statement.setInt(1, groupID);
					statement.setInt(2, students.get(j));
					j ++;
					statement.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<Integer> receiveAllStudentsRandom() {
		Connection connection = null;
		List<Integer> students = new ArrayList<>(); 
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String sqlStudent = "select st.student_id from school.students st";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStudent);
		
			while(resultSet.next()) {
				students.add(resultSet.getInt("student_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		Collections.shuffle(students);
		return students;
	}
	
	private List<Integer> receiveAllGroupsID() {
		Connection connection = null;
		List<Integer> groups = new ArrayList<>(); 
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String sqlGroup = "select gr.group_id from school.groups gr";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlGroup);
		
			while(resultSet.next()) {
				groups.add(resultSet.getInt("group_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return groups;
	}
	
	public void fillSchedule()  {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String sql = "insert into school.schedule (course_id, student_id)" + "values" + "(?,?)";

			final Random random = new Random();
			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0; i < 200; i++) {
				int quantityCourses = random.nextInt(3);
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
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
