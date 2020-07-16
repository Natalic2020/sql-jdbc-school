package ua.com.foxminded.userOption;

import java.util.Scanner;

import ua.com.foxminded.school.Request;

public class SearchGroups implements UserOption {

	@Override
	public void apply() {
//		try (Scanner inputValue = new Scanner(System.in)) {
			Scanner inputValue = new Scanner(System.in);
			Request query = new Request();

			System.out.print("Enter count students in group : ");
			int countStudents = inputValue.nextInt();
			query.searchGroups(countStudents);
		
//		}
	}
}
