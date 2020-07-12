package ua.com.foxminded.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatDB {

	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "1234";
	
	public void createAllDB()  {
		deleteTable("courses");
		deleteTable("groups");
		deleteTable("students");
		
		FileParser file = new FileParser();
		createTable(file.parseFileToString("courses.script"));
		createTable(file.parseFileToString("groups.script"));
		createTable(file.parseFileToString("students.script"));
		createSchedule();
		}
	
	public void deleteTable(String table)  {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			Statement statement = connection.createStatement();

			java.sql.DatabaseMetaData dbm = connection.getMetaData();
			ResultSet rs = dbm.getTables(null, null, table, null);
			if (rs.next()) {
				String sqlScheduleDrop = "DROP TABLE school." + table;
				statement.executeUpdate(sqlScheduleDrop);
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
	
	public void createTable(String sql)  {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
			Statement statement = connection.createStatement();

			statement.executeUpdate(sql);

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
	
	public void createSchedule() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			Statement statement = connection.createStatement();

			java.sql.DatabaseMetaData dbm = connection.getMetaData();
			ResultSet rs = dbm.getTables(null, null, "schedule", null);
			if (rs.next()) {
				String sqlScheduleDrop = "DROP TABLE school.schedule";
				statement.executeUpdate(sqlScheduleDrop);
			}

			String sql = "create table school.schedule" + "	(schedule_id serial PRIMARY KEY, "
					+ "	course_id int  NOT NULL , " + "	student_id int  NOT NULL )";

			statement.executeUpdate(sql);

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
