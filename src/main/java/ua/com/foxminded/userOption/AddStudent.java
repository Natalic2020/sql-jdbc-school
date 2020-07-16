package ua.com.foxminded.userOption;

import java.util.Scanner;

import ua.com.foxminded.school.Request;

public class AddStudent  implements UserOption{

	@Override
	public void apply() {

//		try (Scanner inputValue = new Scanner(System.in)) {
			Scanner inputValue = new Scanner(System.in);
		Request query = new Request();
        
        System.out.print("Enter first name : ");
    	String firstName = inputValue.nextLine();
    	System.out.print("Enter last name : ");
    	String lastName = inputValue.nextLine();
    	query.addStudent(firstName, lastName);
//		}
	}
}
