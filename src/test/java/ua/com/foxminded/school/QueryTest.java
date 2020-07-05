package ua.com.foxminded.school;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class QueryTest {

	@Test
	void searchLessStudents() throws SQLException {
		Request query = new Request();
		query.searchLessStudents();
	}
	
	@Test
	void searchStudentsInCourse() throws SQLException {
		Request query = new Request();
		query.searchStudentsInCourse("maths");
	}
	
	@Test
	void addStudent() throws SQLException {
		Request query = new Request();
		query.addStudent("Vanja", "Ivanov");
	}
	
	@Test
	void deleteStudent() throws SQLException {
		Request query = new Request();
		query.deleteStudent(201);
	}
	
	@Test
	void addStudentToCourse() throws SQLException {
		Request query = new Request();
		query.addStudentToCourse(5, 1);
	}
	
	@Test
	void removeStudentFromCourse() throws SQLException {
		Request query = new Request();
		query.removeStudentFromCourse(5, 1);
	}
}
