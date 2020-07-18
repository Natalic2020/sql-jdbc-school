package ua.com.foxminded.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;

public class RemoveStudentFromCourse implements UserOption {

    SchoolDao query;
    
    public RemoveStudentFromCourse(SchoolDao query) {
        this.query = query;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter id course : ");
        int remove_course_id = scanInput.nextInt();
        System.out.print("Enter id student : ");
        int remove_student_id = scanInput.nextInt();
        query.removeStudentFromCourse(remove_course_id, remove_student_id);
    }
}
