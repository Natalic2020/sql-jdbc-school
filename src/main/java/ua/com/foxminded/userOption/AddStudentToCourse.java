package ua.com.foxminded.userOption;

import java.util.Scanner;

import ua.com.foxminded.school.Request;

public class AddStudentToCourse implements UserOption{

	@Override
	public void apply() {
		Scanner inputValue = new Scanner(System.in);
        Request query = new Request();
		
        System.out.print("Enter id course : ");
    	int add_course_id = inputValue.nextInt();
    	System.out.print("Enter id student : ");
    	int add_student_id = inputValue.nextInt();
    	query.addStudentToCourse(add_course_id, add_student_id);
    	inputValue.close();
	}
}
