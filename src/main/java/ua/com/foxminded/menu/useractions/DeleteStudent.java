package ua.com.foxminded.menu.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;

public class DeleteStudent implements UserOption {

    SchoolDao school;
    
    public DeleteStudent(SchoolDao school) {
        this.school = school;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter STUDENT_ID to remove student : ");
        int student_id = scanInput.nextInt();
        System.out.println("Deleted " +  school.deleteStudent(student_id) + " student(s)");
    }
}
