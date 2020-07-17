package ua.com.foxminded.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.Request;

public class SearchStudentsInCourse implements UserOption {

    @Override
    public void apply(Scanner scanInput) {
        Request query = new Request();

        System.out.print("Enter course name : ");
        String courseName = scanInput.next();
        query.searchStudentsInCourse(courseName);
    }
}
