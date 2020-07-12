package ua.com.foxminded.school;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class QueryTest {

	@Test
	void searchLessStudents()  {
		Request query = new Request();
		query.searchGroups(14);
	}
	
	@Test
	void searchStudentsInCourse() {
		Request query = new Request();
		query.searchStudentsInCourse("maths");
	}
	
	@Test
	void addStudent()  {
		Request query = new Request();
		query.addStudent("Vanja", "Ivanov");
	}
	
	@Test
	void deleteStudent()  {
		Request query = new Request();
		query.deleteStudent(201);
	}
	
	@Test
	void addStudentToCourse()  {
		Request query = new Request();
		query.addStudentToCourse(5, 1);
	}
	
	@Test
	void removeStudentFromCourse() {
		Request query = new Request();
		query.removeStudentFromCourse(5, 1);
	}
}
