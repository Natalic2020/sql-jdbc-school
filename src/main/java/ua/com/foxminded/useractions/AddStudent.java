package ua.com.foxminded.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.Request;

public class AddStudent implements UserOption {

    @Override
    public void apply(Scanner scanInput) {
        Request query = new Request();

        System.out.print("Enter first name : ");
        String firstName = scanInput.next();
        System.out.print("Enter last name : ");
        String lastName = scanInput.next();
        query.addStudent(firstName, lastName);
    }
}
