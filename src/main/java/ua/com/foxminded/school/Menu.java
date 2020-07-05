package ua.com.foxminded.school;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Menu {

	public Map<Integer, String>  fillMenu() {
		
	Map<Integer, String> menu = new HashMap<>();
	
	menu.put(1, "Find all groups with less or equals student count");
	menu.put(2, "Find all students related to course with given name");
	menu.put(3, "Add new student");
	menu.put(4, "Delete student by STUDENT_ID");
	menu.put(5, "Add a student to the course (from a list)");
	menu.put(6, "Remove the student from one of his or her courses");
	menu.put(7, "Exit");
	
	return menu;
	}
	
	public void bildMenu() throws SQLException {
		
		Scanner scan = new Scanner(System.in);
        int choice = 0;
        String numberOutput ="";
 
        while (!"7".equals(numberOutput)){
           
        	Map<Integer, String> menu  =	fillMenu();
        	for (Entry<Integer, String> menuItem : menu.entrySet()) {
				System.out.println(menuItem.getKey() + " " + menuItem.getValue());
			}   		
            numberOutput = scan.next();
                        
            try {
                choice = Integer.parseInt(numberOutput);
            } catch (NumberFormatException e){
                System.out.println("Неверный ввод");
            }
            
            System.out.println(String.format("%s%n%n%s", "******" , menu.get(choice)));
            Scanner in = new Scanner(System.in);
            Request query = new Request();
            switch (choice){
                case 1:
                	query.searchLessStudents();
                    break;
                case 2:
                    System.out.print("Еnter course name");
                    String courseName = in.nextLine();
                	query.searchStudentsInCourse(courseName);
                    break;
                case 3:
                	System.out.print("Еnter first name : ");
                	String firstName = in.nextLine();
                	System.out.print("Еnter last name : ");
                	String lastName = in.nextLine();
                	query.addStudent(firstName, lastName);
                    break;
                case 4:
                    System.out.print("Еnter STUDENT_ID to remove student : ");
                    int student_id = in.nextInt();
                	query.deleteStudent(student_id);
                    break;
                case 5:
                	System.out.print("Еnter id course : ");
                	int add_course_id = in.nextInt();
                	System.out.print("Еnter id student : ");
                	int add_student_id = in.nextInt();
                	query.addStudentToCourse(add_course_id, add_student_id);
                    break;
                case 6:
                	System.out.print("Еnter id course : ");
                	int remove_course_id = in.nextInt();
                	System.out.print("Еnter id student : ");
                	int remove_student_id = in.nextInt();
                	query.removeStudentFromCourse(remove_course_id, remove_student_id);
                    break;
            }
            System.out.println(String.format("%n%n%s", "******" ));
        }
        System.out.println("Shao");	
	}
}
