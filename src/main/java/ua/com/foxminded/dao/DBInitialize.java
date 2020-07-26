package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import ua.com.foxminded.dao.connection.BasicConnectionPool;
import ua.com.foxminded.dao.connection.ConnectionPool;
import ua.com.foxminded.dto.Course;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.dto.Student;
import ua.com.foxminded.servicedb.SchoolDataGenerator;
import ua.com.foxminded.util.FileParser;

public class DBInitialize {

    private static final String URL = "jdbc:postgresql://localhost";
    private static final String URL_SCHOOL = URL + ":5432/school1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    BasicConnectionPool basicConnectionPool= new BasicConnectionPool(URL_SCHOOL, USERNAME, PASSWORD,
            new ArrayList<Connection>());

    public void createDBWithTables() {
        FileParser file = new FileParser();
        List<String> sqlQueryList = file.readFileToLines("sql.script");
        BasicConnectionPool basicConnectionPool = new BasicConnectionPool(URL + "/", USERNAME, PASSWORD,
                new ArrayList<Connection>());
        sqlQueryList
                    .stream()
                    .limit(2)
                    .forEach(sql -> runSQL(sql, basicConnectionPool.getConnection()));
        try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BasicConnectionPool basicConnectionPool2 = new BasicConnectionPool(URL_SCHOOL, USERNAME, PASSWORD,
                new ArrayList<Connection>());
        sqlQueryList
                    .stream()
                    .skip(2)
                    .forEach(sql -> runSQL(sql, basicConnectionPool2.getConnection()));
        try {
            basicConnectionPool2.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void runSQL(String sql, Connection connection) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillAllDB() {

        SchoolDataGenerator value = new SchoolDataGenerator();

        List<Group> groups = value.receiveGroups(10);
        fillGroups(groups);

        List<Course> courses = value.receiveCourses();
        fillCourses(courses);

        List<Student> students = value.receiveStudents(groups);
        fillStudents(students);

        Map<Integer, List<Integer>> studentsCourses = value.receiveStudentsCourses(courses, students);
        fillSchedule(studentsCourses);

        try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillGroups(List<Group> groups) {

        String sql = "insert into school.groups (group_id, group_name) values (?,?)";
  
        Connection connection = basicConnectionPool.getConnection();
        
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            for (Group group : groups) {
                statement.setInt(1, group.getGroupId());
                statement.setString(2, group.getGroupName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillCourses(List<Course> courses) {

        String sql = "insert into school.courses (course_id, course_name) values (?, ?)";

        Connection connection = basicConnectionPool.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            courses.forEach(course ->
                {
                    try {
                        statement.setInt(1, course.getCourseId());
                        statement.setString(2, course.getCourseName());
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void fillStudents(List<Student> students) {

        String sql = "insert into school.students (student_id, first_name, last_name, group_id) values"
                + "(?,?,?,?)";

        Connection connection = basicConnectionPool.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            for (Student student : students) {
                statement.setInt(1, student.getStudentId());
                statement.setString(2, student.getFirstName());
                statement.setString(3, student.getLastName());
                statement.setInt(4, student.getGroupId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void fillSchedule(Map<Integer, List<Integer>> studentsCourses) {

        String sql = "insert into school.students_courses ( student_id, course_id ) values (?,?)";

        Connection connection = basicConnectionPool.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            studentsCourses.entrySet().stream().forEach(data -> {
                data.getValue().forEach(data2 -> {
                    try {
                        statement.setInt(1, data.getKey());
                        statement.setInt(2, data2);
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }   
                });
            });
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
