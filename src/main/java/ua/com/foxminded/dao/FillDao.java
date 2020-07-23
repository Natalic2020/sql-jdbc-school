package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.connection.BasicConnectionPool;
import ua.com.foxminded.dto.Course;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.dto.Student;
import ua.com.foxminded.servicedb.AuxiliaryValue;

public class FillDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/school1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public void fillAllDB()  {
        
        BasicConnectionPool  basicConnectionPool = new BasicConnectionPool(URL, USERNAME, PASSWORD, new ArrayList<Connection>());

        AuxiliaryValue value = new AuxiliaryValue();
        
        List<Group> groups = value.receiveGroups(10);
        fillGroups(groups, basicConnectionPool.getConnection());
        
        List<Course> courses = value.receiveCourses();
        fillCourses(courses, basicConnectionPool.getConnection());
        
        List<Student> students = value.receiveStudents(groups);
        fillStudents(students, basicConnectionPool.getConnection());
        
        List<Integer[]> schedule = value.receiveSchedules(courses, students);
        fillSchedule(schedule, basicConnectionPool.getConnection());
        
        try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillGroups(List<Group> groups, Connection connection)  {

            String sql = "insert into school.groups (group_id, group_name)" + "values" + "(?,?)";

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

    public void fillCourses(List<Course> courses, Connection connection) {

            String sql = "insert into school.courses (course_id, course_name)" + "values" + "(?, ?)";

            PreparedStatement statement;
            try {
                statement = connection.prepareStatement(sql);
                courses.forEach(course -> {
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

    public void fillStudents(List<Student> students, Connection connection) {
       
            String sql = "insert into school.students (student_id, first_name, last_name, group_id)" + "values" + "(?,?,?,?)";

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

    public void fillSchedule(List<Integer[]> schedule, Connection connection) {
       
            String sql = "insert into school.students_courses ( student_id, course_id )" + "values" + "(?,?)";

            PreparedStatement statement;
            try {
                statement = connection.prepareStatement(sql);
                schedule.forEach(data -> {
                    try {
                        statement.setInt(1, data[0]);
                        statement.setInt(2, data[1]);
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            } catch (SQLException e1) {
                e1.printStackTrace();
            }     
    }
}
