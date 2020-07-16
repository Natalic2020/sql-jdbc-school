package ua.com.foxminded.userOption;

import java.util.Scanner;

import ua.com.foxminded.school.Request;

public class RemoveStudentFromCourse implements UserOption{

	@Override
	public void apply() {
//		try (Scanner inputValue = new Scanner(System.in)) {
			Scanner inputValue = new Scanner(System.in);
			Request query = new Request();
		
        System.out.print("Enter id course : ");
    	int remove_course_id = inputValue.nextInt();
    	System.out.print("Enter id student : ");
    	int remove_student_id = inputValue.nextInt();
    	query.removeStudentFromCourse(remove_course_id, remove_student_id);
		
		}
//	}
}
