package ua.com.foxminded.menu.useractions;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;
import ua.com.foxminded.dto.Student;

public class SearchStudentsInCourse implements UserOption {

    SchoolDao query;

    public SearchStudentsInCourse(SchoolDao query) {
        this.query = query;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter course name : ");
        String courseName = scanInput.next();
        List<Student> students = query.searchStudentsInCourse(courseName);
        students
                .stream()
                .forEach(student -> System.out.println(student.toString()));
    }
}
