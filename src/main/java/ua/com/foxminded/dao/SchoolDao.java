package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.com.foxminded.connection.BasicConnectionPool;

public class SchoolDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/school1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    static BasicConnectionPool basicConnectionPool = new BasicConnectionPool(URL, USERNAME, PASSWORD,
            new ArrayList<Connection>());

    public void searchGroups(int countStudents) {
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
                System.out.println(resultSet.getString("group_name") + " :  " + resultSet.getInt("count_students"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchStudentsInCourse(String courseName) {
        Connection connection = basicConnectionPool.getConnection();
        String sql = "select st.first_name, st.last_name from school.students st, school.students_courses sdl, school.courses cs "
                + "Where st.student_id = sdl.student_id and cs.course_id = sdl.course_id  and cs.course_name = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, courseName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(
                        String.format("%s %s", resultSet.getString("first_name"), resultSet.getString("last_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(int studentId, String firstName, String lastName) {
        Connection connection = basicConnectionPool.getConnection();
        String sql = "insert into school.students (student_id, first_name, last_name)  values (?,?,?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentID) {
        Connection connection = basicConnectionPool.getConnection();
        String sql = "delete from school.students st where st.student_id = ? ";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studentID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudentToCourse(int courseID, int studentID) {
        Connection connection = basicConnectionPool.getConnection();
        String sql = "insert into school.students_courses (course_id, student_id) values (?,?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, courseID);
            statement.setInt(2, studentID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeStudentFromCourse(int courseID, int studentID) {
        Connection connection = basicConnectionPool.getConnection();
        String sql = "delete from school.students_courses sd  where course_id = ? and student_id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, courseID);
            statement.setInt(2, studentID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
