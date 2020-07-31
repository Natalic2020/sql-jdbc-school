package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.dao.connection.BasicConnectionPool;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.dto.Student;

class SchoolDaoTest {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/school1";

    static final String USER = "sa";
    static final String PASS = "";

    static BasicConnectionPool basicConnectionPool = new BasicConnectionPool(DB_URL, USER, PASS,
            new ArrayList<Connection>());

    SchoolDao school = new SchoolDao(basicConnectionPool);

    @BeforeEach
    void init() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = basicConnectionPool.getConnection();
            stmt = conn.createStatement();

            String sql = "DROP ALL OBJECTS";
            stmt.executeUpdate(sql);
            sql = "CREATE SCHEMA school";
            stmt.executeUpdate(sql);
            sql = "create table school.groups (group_id serial PRIMARY KEY, group_name character(50)  NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "create table school.students (student_id serial PRIMARY KEY, group_id int,    first_name character(50)  NOT NULL, last_name character(50) NOT NULL);";
            stmt.executeUpdate(sql);
            sql = "create table school.courses (course_id serial PRIMARY KEY,    course_name character(50)  NOT NULL,    course_description character(150))";
            stmt.executeUpdate(sql);
            sql = "create table school.students_courses (students_courses_id serial PRIMARY KEY, course_id int  REFERENCES school.courses ON DELETE CASCADE,  student_id int REFERENCES school.students ON DELETE CASCADE)";
            stmt.executeUpdate(sql);

            String sqlGroups = "insert into school.groups (group_id, group_name) values (1,'gr1')";
            stmt.executeUpdate(sqlGroups);
            sqlGroups = "insert into school.groups (group_id, group_name) values (2,'gr2')";
            stmt.executeUpdate(sqlGroups);
            sqlGroups = "insert into school.groups (group_id, group_name) values (3,'gr3')";
            stmt.executeUpdate(sqlGroups);

            String sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                    + "(1,'Nina','Ivanova', 1)";
            stmt.executeUpdate(sqlStudent);
            sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                    + "(2,'Nona','Petrova', 1)";
            stmt.executeUpdate(sqlStudent);
            sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                    + "(3,'Igor','Beliy', 3)";
            stmt.executeUpdate(sqlStudent);

            String sqlCourses = "insert into school.courses (course_id, course_name) values (1, 'maths')";
            stmt.executeUpdate(sqlCourses);
            sqlCourses = "insert into school.courses (course_id, course_name) values (2, 'art')";
            stmt.executeUpdate(sqlCourses);

            String sqlStCourses = "insert into school.students_courses ( student_id, course_id ) values (1,1)";
            stmt.executeUpdate(sqlStCourses);
            sqlStCourses = "insert into school.students_courses ( student_id, course_id ) values (2,1)";
            stmt.executeUpdate(sqlStCourses);
            sqlStCourses = "insert into school.students_courses ( student_id, course_id ) values (3,2)";
            stmt.executeUpdate(sqlStCourses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        basicConnectionPool.getUsedConnections().clear();
    }

    @AfterAll
    static void tearDownAll() {
        try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addStudent_shouldThrowIllegalArgumentException_whenInputFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> school.addStudent(5, null, "Svet"));
    }

    @Test
    public void addStudent_shouldThrowIllegalArgumentException_whenInputLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> school.addStudent(5, "Nata", null));
    }

    @Test
    public void searchStudentsInCourse_shouldThrowIllegalArgumentException_whenInputCourseNull() {
        assertThrows(IllegalArgumentException.class, () -> school.searchStudentsInCourse(null));
    }

    @Test
    public void addStudent_schoudReturn1Student_whenInput1Student() {
        int expected = 1;

        int actual = school.addStudent(5, "Nata", "Svet");

        assertEquals(expected, actual, "Added 1 student to course");
    }

    @Test
    public void searchGroups_schoudReturn2Groups_whenInput2RightGroups() {
        List<Group> expected = new ArrayList<Group>();
        expected.add(new Group("gr3", 1));

        List<Group> actual = school.searchGroups(1);

        assertEquals(expected, actual);
    }

    @Test
    public void searchStudentsInCourse_schoudReturn2Students_whenInput1Course() {
        List<Student> expected = new ArrayList<>();
        expected.add(new Student("Nina", "Ivanova"));
        expected.add(new Student("Nona", "Petrova"));

        List<Student> actual = school.searchStudentsInCourse("maths");

        assertEquals(expected, actual);
    }

    @Test
    public void deleteStudent_schoudDeletedStudent_whenDeleteStudent() {
        int expected = 1;
        
        int actual = school.deleteStudent(2);

        assertEquals(expected, actual, "Deleted 1 Student");
    }

    @Test
    public void addStudentToCourse_schoudReturn1StudentInCourse_whenInputStudentCourse() {
        int expected = 1;
        
        int actual = school.addStudentToCourse(2, 2);

        assertEquals(expected, actual, "Added 1 Student to Course");
    }

    @Test
    public void removeStudentFromCourse_schoudReturnEmpty_whenDeleteStudentFromCourse() {
        int expected = 1;
        
        int actual = school.removeStudentFromCourse(1, 1);

        assertEquals(expected, actual, "Deleted 1 Student to Course");
    }
}
