package ua.com.foxminded.servicedb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.connection.BasicConnectionPool;
import ua.com.foxminded.dao.CreatDB;
import ua.com.foxminded.util.FileParser;

public class FileProcessing {

    private static final String URL = "jdbc:postgresql://localhost";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    CreatDB db;
    
    public FileProcessing(CreatDB db) {
        this.db = db;
    }

    public void createDBWithTables() {
        FileParser file = new FileParser();
        List<String> sqlQueryList = file.readFileToLines("sql.script");
        BasicConnectionPool  basicConnectionPool = new BasicConnectionPool(URL+"/", USERNAME, PASSWORD, new ArrayList<Connection>());
        sqlQueryList.stream().limit(2).forEach(sql -> runSQL(sql, basicConnectionPool.getConnection()));
        try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BasicConnectionPool  basicConnectionPool2 = new BasicConnectionPool(URL+":5432/school1", USERNAME, PASSWORD, new ArrayList<Connection>());
        sqlQueryList.stream().skip(2).forEach(sql -> runSQL(sql, basicConnectionPool2.getConnection()));
        try {
            basicConnectionPool2.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void runSQL(String sql, Connection connection) {
        db.runQuery(sql, connection);
    }
}
