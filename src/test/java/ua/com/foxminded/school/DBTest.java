package ua.com.foxminded.school;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DBTest {

//	@Test
//	void createGroups() throws SQLException {
//		FillingDB filling = new FillingDB();
//		filling.createGroups();
//	}

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
	
	
	
}
