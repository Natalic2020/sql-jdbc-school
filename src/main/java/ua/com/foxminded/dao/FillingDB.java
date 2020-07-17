package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ua.com.foxminded.useractions.AuxiliaryValue;

public class FillingDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public void fillAllDB() throws Exception {
        createGroupsWithShuffle();
        createCourses();
        createStudents();
        fillGroupByStudents();
        fillSchedule();
    }

    public void createGroupsWithShuffle() {
        AuxiliaryValue value = new AuxiliaryValue();
        List<String> listWithValue = value.randomValue(10);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "insert into school.groups (group_name)" + "values" + "('gr-'||?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            for (String i : listWithValue) {
                statement.setString(1, i);
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

    public void createCourses() {

        AuxiliaryValue coursesValue = new AuxiliaryValue();
        String[] courses = coursesValue.courses();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "insert into school.courses (course_name)" + "values" + "(?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < courses.length; i++) {
                statement.setString(1, courses[i]);
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

    public void createStudents() {

        AuxiliaryValue bdValue = new AuxiliaryValue();
        List<Map<String, String>> names = bdValue.fillNames();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "insert into school.students (first_name, last_name)" + "values" + "(?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            names.forEach(data -> {
                try {
                    statement.setString(1, data.get("firstName"));
                    statement.setString(2, data.get("lastName"));
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

    public void fillGroupByStudents() {

        Connection connection = null;
        AuxiliaryValue bdValue = new AuxiliaryValue();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "update  school.students SET group_id = ?" + "where student_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            Map<Integer, Integer> groupOfStudents = bdValue.recieveGroupOfStudents();

            groupOfStudents.forEach((k, v) -> {
                try {
                    statement.setInt(1, v);
                    statement.setInt(2, k);
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

    public void fillSchedule() {

        Connection connection = null;
        AuxiliaryValue bdValue = new AuxiliaryValue();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "insert into school.schedule (course_id, student_id)" + "values" + "(?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            List<Integer[]> courcesOfStudents = bdValue.recieveCourcesOfStudents();

            courcesOfStudents.forEach(data -> {
                try {
                    statement.setInt(1, data[1]);
                    statement.setInt(2, data[0]);
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
