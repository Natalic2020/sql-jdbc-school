package ua.com.foxminded.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.Request;

public class AddStudentToCourse implements UserOption {

    @Override
    public void apply(Scanner scanInput) {
        Request query = new Request();

        System.out.print("Enter id course : ");
        int add_course_id = scanInput.nextInt();
        System.out.print("Enter id student : ");
        int add_student_id = scanInput.nextInt();
        query.addStudentToCourse(add_course_id, add_student_id);
    }
}
