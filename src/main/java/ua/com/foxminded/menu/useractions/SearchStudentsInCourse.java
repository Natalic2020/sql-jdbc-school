package ua.com.foxminded.menu.useractions;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;
import ua.com.foxminded.dto.Student;

public class SearchStudentsInCourse implements UserOption {

    SchoolDao school;

    public SearchStudentsInCourse(SchoolDao school) {
        this.school = school;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter course name : ");
        String courseName = scanInput.next();
        List<Student> students = school.searchStudentsInCourse(courseName);
        
        if (students.size() == 0) {
            System.out.println("No student found");
        } else {
            students
            .stream()
            .forEach(student -> System.out.println(student.toString()));
        }            
    }
}
