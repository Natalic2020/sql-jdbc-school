package ua.com.foxminded.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreatDB {

	public void deleteTable(String table) throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

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
				connection.close();
			}
		}
	}
	
	public void  parseFile() {
	
		
	}
	
	
	public void createTable(String sql) throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			Statement statement = connection.createStatement();

			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void createSchedule() throws SQLException {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

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
				connection.close();
			}
		}
	}

	public void createTables() throws SQLException {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			Statement statement = connection.createStatement();

			java.sql.DatabaseMetaData dbm = connection.getMetaData();
			ResultSet rs = dbm.getTables(null, null, "groups", null);
			if (rs.next()) {
				String sqlGroupDrop = "DROP TABLE school.groups";
				statement.executeUpdate(sqlGroupDrop);
			}

			String sqlGroup = "create table school.groups" + "	(group_id serial PRIMARY KEY, "
					+ "	group_name character(50)  NOT NULL )";

			statement.executeUpdate(sqlGroup);

			rs = dbm.getTables(null, null, "students", null);
			if (rs.next()) {
				String sqlGroupDrop = "DROP TABLE school.students";
				statement.executeUpdate(sqlGroupDrop);
			}

			String sqlStudents = "create table school.students(" + "	student_id serial PRIMARY KEY,"
					+ "	group_id int, " + "	first_name character(50)  NOT NULL, "
					+ "	last_name character(50)  NOT NULL )";

			statement.executeUpdate(sqlStudents);

			rs = dbm.getTables(null, null, "courses", null);
			if (rs.next()) {
				String sqlGroupDrop = "DROP TABLE school.courses";
				statement.executeUpdate(sqlGroupDrop);
			}

			String sqlCourses = "create table school.courses(" + "	course_id serial PRIMARY KEY, "
					+ "	course_name character(50)  NOT NULL, " + "	course_description character(150) " + ")";

			statement.executeUpdate(sqlCourses);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}
