package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import ua.com.foxminded.util.FileParser;

public class CreatDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/school1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public void createDBWithTables() {
        FileParser file = new FileParser();
        List<String> sqlQueryList = file.readFileToLines("sql.script");
        sqlQueryList.stream().limit(2).forEach(this::dropCreateDB);
        sqlQueryList.stream().skip(2).forEach(this::createTable);
    }
    
    public void dropCreateDB(String sql) {
        System.out.println("delete BD");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/", "postgres", "1234"); 
               
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
}
