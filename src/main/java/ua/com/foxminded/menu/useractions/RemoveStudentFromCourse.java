package ua.com.foxminded.menu.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;

public class RemoveStudentFromCourse implements UserOption {

    SchoolDao school;
    
    public RemoveStudentFromCourse(SchoolDao school) {
        this.school = school;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter id course : ");
        int remove_course_id = scanInput.nextInt();
        System.out.print("Enter id student : ");
        int remove_student_id = scanInput.nextInt();
        System.out.println("Removed " +  school.removeStudentFromCourse(remove_course_id, remove_student_id) + " student(s) from course(s)");
    }
}
