package ua.com.foxminded.userOption;

import java.util.Scanner;

import ua.com.foxminded.school.Request;

public class DeleteStudent implements UserOption{

	@Override
	public void apply() {
		Scanner inputValue = new Scanner(System.in);
        Request query = new Request();
		
        System.out.print("Enter STUDENT_ID to remove student : ");
        int student_id = inputValue.nextInt();
    	query.deleteStudent(student_id);
    	inputValue.close();
	}
}