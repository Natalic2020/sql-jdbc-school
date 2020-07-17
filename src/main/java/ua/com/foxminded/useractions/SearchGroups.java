package ua.com.foxminded.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.Request;

public class SearchGroups implements UserOption {

    @Override
    public void apply(Scanner scanInput) {
        Request query = new Request();

        System.out.print("Enter count students in group : ");
        int countStudents = scanInput.nextInt();
        query.searchGroups(countStudents);
    }
}
