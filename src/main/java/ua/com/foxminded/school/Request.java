package ua.com.foxminded.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Request {

	public void searchLessStudents() throws SQLException {
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("searchLessStudents.....");

			String sql = "select Count(st.student_id) as count_course, st.group_id, gr.group_name group_name " + 
					"from school.groups gr, " + 
					"school.students st " + 
					"where gr.group_id = st.group_id " + 
					"group by st.group_id, gr.group_name " + 
					"order by count_course " + 
					"limit 1";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
		
			while(resultSet.next()) {
				System.out.println(resultSet.getString("group_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
			}
		}
	}
	
  public void searchStudentsInCourse(String courseName) throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("searchStudentsInCourse......");

			String sql = "select st.first_name, st.last_name from school.students st, school.schedule sdl, school.courses cs " + 
					"Where st.student_id = sdl.student_id " + 
					"and cs.course_id = sdl.course_id  " + 
					"and cs.course_name = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, courseName);
			ResultSet resultSet = statement.executeQuery();
		
			while(resultSet.next()) {
				System.out.println(String.format("%s %s", resultSet.getString("first_name"), resultSet.getString("last_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
			}
		}
	}
  
  public void addStudent(String firstName, String lastName) throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("add student.....");

			String sql = "insert into school.students (first_name, last_name)" + 
					"values" + 
					"(?,?)" ;
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
				statement.setString(1, firstName);
				statement.setString(2, lastName);
				statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
			}
		}
	}
  
  public void deleteStudent(int studentID) throws SQLException {	
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("delete student......");

			String sql = "delete from school.students st " + 
					"where st.student_id = ? " ;
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
				statement.setInt(1, studentID);
				statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
			}
		}
	}
  
  
  public  void addStudentToCourse(int courseID, int studentID) throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			String sql = "insert into school.schedule (course_id, student_id)" + 
					"values" + 
					"(?,?)" ;
			PreparedStatement statement = connection.prepareStatement(sql);
			
					statement.setInt(1, courseID);
					statement.setInt(2, studentID);
					statement.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
			}
		}
	}
  
  public  void removeStudentFromCourse(int courseID, int studentID) throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Connenc PostgreSQL");

			String sql = "delete from school.schedule sd " + 
					"where course_id = ? and student_id = ?";	
			
			PreparedStatement statement = connection.prepareStatement(sql);
					statement.setInt(1, courseID);
					statement.setInt(2, studentID);
					statement.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
			}
		}
	}
}
