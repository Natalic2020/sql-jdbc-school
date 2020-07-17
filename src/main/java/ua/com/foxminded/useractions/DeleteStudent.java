package ua.com.foxminded.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.Request;

public class DeleteStudent implements UserOption {

    @Override
    public void apply(Scanner scanInput) {
        Request query = new Request();

        System.out.print("Enter STUDENT_ID to remove student : ");
        int student_id = scanInput.nextInt();
        query.deleteStudent(student_id);
    }
}
