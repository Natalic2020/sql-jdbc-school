package ua.com.foxminded.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;

public class DeleteStudent implements UserOption {

    SchoolDao query;
    
    public DeleteStudent(SchoolDao query) {
        this.query = query;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter STUDENT_ID to remove student : ");
        int student_id = scanInput.nextInt();
        query.deleteStudent(student_id);
    }
}
