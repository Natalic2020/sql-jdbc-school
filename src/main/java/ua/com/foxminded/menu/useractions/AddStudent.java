package ua.com.foxminded.menu.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;
import ua.com.foxminded.dto.Student;

public class AddStudent implements UserOption {

    SchoolDao school;

    public AddStudent(SchoolDao school) {
        this.school = school;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter first name : ");
        String firstName = scanInput.next();
        System.out.print("Enter last name : ");
        String lastName = scanInput.next();
        Student student = new Student(firstName, lastName);
        System.out.println("Added " + school.addStudent(student.getStudentId(),firstName, lastName) + " student(s)");
    }
}
