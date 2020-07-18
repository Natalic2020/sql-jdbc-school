package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.com.foxminded.dto.Course;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.dto.Student;
import ua.com.foxminded.util.AuxiliaryValue;

public class FillingDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public void fillAllDB() throws Exception {
        AuxiliaryValue value = new AuxiliaryValue();
        
        List<Group> groups = value.receiveGroups(10);
        fillGroups(groups);
        
        List<Course> courses = value.receiveCourses();
        fillCourses(courses);
        
        List<Student> students = value.receiveStudents(groups);
        fillStudents(students);
        
        List<Integer[]> schedule = value.receiveSchedules(courses, students);
        fillSchedule(schedule);
    }

    public void fillGroups(List<Group> groups) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "insert into school.groups (group_id, group_name)" + "values" + "(?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            
            for (Group group : groups) {
               statement.setInt(1, group.getGroupId());
               statement.setString(2, group.getGroupName());
               statement.executeUpdate();
            }            
       } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fillCourses(List<Course> courses) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "insert into school.courses (course_id, course_name)" + "values" + "(?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            courses.forEach(course -> {
                try {
                    statement.setInt(1, course.getCourseId());
                    statement.setString(2, course.getCourseName());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }   
            });  
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fillStudents(List<Student> students) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "insert into school.students (student_id, first_name, last_name, group_id)" + "values" + "(?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

         for (Student student : students) {
             statement.setInt(1, student.getStudentId());
             statement.setString(2, student.getFirstName());
             statement.setString(3, student.getLastName());
             statement.setInt(4, student.getGroupId());
             statement.executeUpdate();
        }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fillSchedule(List<Integer[]> schedule) {

       Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "insert into school.schedule (schedule_id, student_id, course_id )" + "values" + "(?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            schedule.forEach(data -> {
                try {
                    statement.setInt(1, data[0]);
                    statement.setInt(2, data[1]);
                    statement.setInt(3, data[2]);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
