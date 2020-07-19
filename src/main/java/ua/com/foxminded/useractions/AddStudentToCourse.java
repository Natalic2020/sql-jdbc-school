package ua.com.foxminded.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;
import ua.com.foxminded.dto.Student;

public class AddStudentToCourse implements UserOption {

    SchoolDao query;

    public AddStudentToCourse(SchoolDao query) {
        this.query = query;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter id course : ");
        int add_course_id = scanInput.nextInt();
        System.out.print("Enter id student : ");
        int add_student_id = scanInput.nextInt();
        
        query.addStudentToCourse(add_course_id, add_student_id);
    }
}
