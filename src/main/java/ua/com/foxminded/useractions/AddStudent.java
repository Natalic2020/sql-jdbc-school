package ua.com.foxminded.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;

public class AddStudent implements UserOption {

    SchoolDao query;

    public AddStudent(SchoolDao query) {
        this.query = query;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter first name : ");
        String firstName = scanInput.next();
        System.out.print("Enter last name : ");
        String lastName = scanInput.next();
        query.addStudent(firstName, lastName);
    }
}
