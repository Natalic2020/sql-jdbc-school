package ua.com.foxminded.servicedb;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.dto.Course;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.dto.Student;

class SchoolDataGeneratorTest {

SchoolDataGenerator generator = new SchoolDataGenerator();
    
    @Test
    void receiveGroups_returnGroupsSize3_whetCreate3Groups() {  
        int expected = 3;
        List<Group> groups = generator.receiveGroups(3);
        int actual = groups.size();
        assertEquals(expected, actual);
    }
    
    @Test
    void receiveCourses_returnListOfCourses_whetCreateCourses() { 
        int expected = 10; 
        List<Course> courses = generator.receiveCourses();
        int actual = courses.size();
        assertEquals(expected, actual);
    }
    
    @Test
    void receiveStudents_returnListOfStudents_whetCreateStudents() { 
        int expected = 200; 
        List<Group> groups = new ArrayList<Group>();
        groups.add(new Group("gr1"));
        groups.add(new Group("gr2"));
        groups.add(new Group("gr3"));
        List<Student> students = generator.receiveStudents(groups);
        int actual = students.size();
        assertEquals(expected, actual);
    }
    
    @Test
    void receiveStudentsCourses_returnListOfIDStudentCourses_whetAddStudentsToCourses() { 
        int expected = 3; 
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("maths"));
        courses.add(new Course("art"));
        courses.add(new Course("geo"));
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ivan", "Ivanov"));
        students.add(new Student("Petr", "Petrov"));
        students.add(new Student("Vasja", "Vasiliy"));
        Map<Integer, List<Integer>> studentCourses = generator.receiveStudentsCourses(courses,  students);
        int actual = studentCourses.size();
        assertEquals(expected, actual);
    }
}
