package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatDB {
    public void runQuery(String sql, Connection connection) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
