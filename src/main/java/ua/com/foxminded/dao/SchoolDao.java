package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.dao.connection.BasicConnectionPool;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.dto.Student;

public class SchoolDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/school1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    static BasicConnectionPool basicConnectionPool = new BasicConnectionPool(URL, USERNAME, PASSWORD,
            new ArrayList<Connection>());

    public List<Group> searchGroups(int countStudents) {
        List<Group> groups = new ArrayList<>();
        Connection connection = basicConnectionPool.getConnection();
        String sql = "select Count(st.student_id) as count_students, st.group_id, gr.group_name group_name "
                + "from school.groups gr, school.students st where gr.group_id = st.group_id " //
                + "group by st.group_id, gr.group_name having Count(st.student_id) <= ? order by count_students ";

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, countStudents);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                groups.add(new Group(resultSet.getString("group_name"), resultSet.getInt("count_students")));     
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            basicConnectionPool.getUsedConnections().clear();
            return groups;
        }
    }

    public List<Student> searchStudentsInCourse(String courseName) {
        List<Student> students = new ArrayList<>();
        Connection connection = basicConnectionPool.getConnection();
        String sql = "select st.first_name, st.last_name from school.students st, school.students_courses sdl, school.courses cs "
                + "Where st.student_id = sdl.student_id and cs.course_id = sdl.course_id  and cs.course_name = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, courseName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                students.add(new Student(resultSet.getString("first_name"), resultSet.getString("last_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            basicConnectionPool.getUsedConnections().clear();
            return students;
        }
    }

    public int addStudent(int studentId, String firstName, String lastName) {
        int countStudents = 0; 
        Connection connection = basicConnectionPool.getConnection();
        String sql = "insert into school.students (student_id, first_name, last_name)  values (?,?,?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            countStudents = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            basicConnectionPool.getUsedConnections().clear();
            return countStudents;
        }
    }

    public int deleteStudent(int studentID) {
        int countStudents = 0;
        Connection connection = basicConnectionPool.getConnection();
        String sql = "delete from school.students st where st.student_id = ? ";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studentID);
            countStudents = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            basicConnectionPool.getUsedConnections().clear();
            return countStudents;
        }
    }

    public int addStudentToCourse(int courseID, int studentID) {
        int countStudents = 0;
        Connection connection = basicConnectionPool.getConnection();
        String sql = "insert into school.students_courses (course_id, student_id) values (?,?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, courseID);
            statement.setInt(2, studentID);
            countStudents = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            basicConnectionPool.getUsedConnections().clear();
            return countStudents;
        }
    }

    public int removeStudentFromCourse(int courseID, int studentID) {
        int countStudents = 0;
        Connection connection = basicConnectionPool.getConnection();
        String sql = "delete from school.students_courses sd  where course_id = ? and student_id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, courseID);
            statement.setInt(2, studentID);
            countStudents = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            basicConnectionPool.getUsedConnections().clear();
            return countStudents;
        }
    }

    public void exit() {
        try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            basicConnectionPool.getUsedConnections().clear();
        }
    }
}
