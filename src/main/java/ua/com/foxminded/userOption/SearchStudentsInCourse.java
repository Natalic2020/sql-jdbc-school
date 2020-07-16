package ua.com.foxminded.userOption;

import java.util.Scanner;

import ua.com.foxminded.school.Request;

public class SearchStudentsInCourse  implements UserOption{

	@Override
	public void apply() {
//		try (Scanner inputValue = new Scanner(System.in)) {
			Scanner inputValue = new Scanner(System.in);
			Request query = new Request();
		
        System.out.print("Enter course name : ");
        String courseName = inputValue.nextLine();
    	query.searchStudentsInCourse(courseName);
		
//		}
	}
}
