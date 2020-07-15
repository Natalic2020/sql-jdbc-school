package ua.com.foxminded.school;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import ua.com.foxminded.userOption.AddStudent;
import ua.com.foxminded.userOption.AddStudentToCourse;
import ua.com.foxminded.userOption.DeleteStudent;
import ua.com.foxminded.userOption.RemoveStudentFromCourse;
import ua.com.foxminded.userOption.SearchGroups;
import ua.com.foxminded.userOption.SearchStudentsInCourse;
import ua.com.foxminded.userOption.UserOption;

public class Menu {

	Map<Integer, String> menu = new HashMap<>();
	
	public Menu() {
		menu.put(1, "Find all groups with less or equals student count");
		menu.put(2, "Find all students related to course with given name");
		menu.put(3, "Add new student");
		menu.put(4, "Delete student by STUDENT_ID");
		menu.put(5, "Add a student to the course (from a list)");
		menu.put(6, "Remove the student from one of his or her courses");
		menu.put(7, "Exit");
	}

	public void bildMenu()  {
        int choice = 0;
        
        Map<Integer, Object> menuMap =  createMapMenu();
        while (choice!=7){
        	Scanner scanChoice = new Scanner(System.in);
        	for (Entry<Integer, String> menuItem : menu.entrySet()) {
				System.out.println(menuItem.getKey() + " " + menuItem.getValue());
			} 
        	
            choice = scanChoice.nextInt();
            
            System.out.println(String.format("%s%n%n%s", "******" , menu.get(choice)));

            UserOption userOption =  (UserOption) menuMap.get(choice);
            userOption.apply();
            
            System.out.println(String.format("%n%n%s", "******" )); 
            scanChoice.close();
        }
        
        System.out.println("Shao");	
	}
	
	public Map<Integer, Object>  createMapMenu(){
		Map<Integer, Object> menuMap =  new HashMap<>();
		SearchGroups searchGroups = new SearchGroups();
		menuMap.put(1, searchGroups);
		SearchStudentsInCourse searchStudentsInCourse = new SearchStudentsInCourse();
		menuMap.put(2, searchStudentsInCourse);
		AddStudent addStudent = new AddStudent();
		menuMap.put(3, addStudent);
		DeleteStudent deleteStudent = new DeleteStudent();
		menuMap.put(4, deleteStudent);
		AddStudentToCourse addStudentToCourse = new AddStudentToCourse();
		menuMap.put(5, addStudentToCourse);
		RemoveStudentFromCourse removeStudentFromCourse = new RemoveStudentFromCourse();
		menuMap.put(6, removeStudentFromCourse);
		return menuMap;
	}
}
