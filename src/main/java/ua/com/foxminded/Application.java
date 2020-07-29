package ua.com.foxminded;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.com.foxminded.dao.DBInitializer;
import ua.com.foxminded.dao.connection.BasicConnectionPool;
import ua.com.foxminded.menu.Menu;

public class Application {

    private static final String URL = "jdbc:postgresql://localhost";
    private static final String URL_SCHOOL = URL + ":5432/school1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    
    static BasicConnectionPool basicConnectionPool9 = new BasicConnectionPool(URL_SCHOOL, USERNAME, PASSWORD,
            new ArrayList<Connection>());
    
    static {
        DBInitializer dbInitializer = new DBInitializer(basicConnectionPool9);
        dbInitializer.createDBWithTables();
        dbInitializer.fillAllDB();
    }

    public static void main(String[] args) {
        new Menu().runMenu();
        try {
            basicConnectionPool9.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
