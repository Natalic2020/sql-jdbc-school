package ua.com.foxminded.menu.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;

public class SearchStudentsInCourse implements UserOption {

    SchoolDao query;

    public SearchStudentsInCourse(SchoolDao query) {
        this.query = query;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter course name : ");
        String courseName = scanInput.next();
        query.searchStudentsInCourse(courseName);
    }
}
