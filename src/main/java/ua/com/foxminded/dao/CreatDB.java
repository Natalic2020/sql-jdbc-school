package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Stream;

import ua.com.foxminded.util.FileParser;

public class CreatDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public void createAllDB() {
        Stream.of("courses", "groups", "students").forEach(this::deleteTable);

        FileParser file = new FileParser();
        List<String> sqlQueryList = file.readFileToLines("sql.script");
        sqlQueryList.forEach(this::createTable);

        createSchedule();
    }

    public void deleteTable(String table) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sqlSDrop = "DROP TABLE IF EXISTS school." + table;

            statement.executeUpdate(sqlSDrop);

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

    public void createTable(String sql) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);

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

    public void createSchedule() {

        deleteTable("schedule");

        String sqlSchedule = "create table school.schedule" + "	(schedule_id serial PRIMARY KEY, "
                + "	course_id int  NOT NULL , " + "	student_id int  NOT NULL )";
        createTable(sqlSchedule);
    }
}
