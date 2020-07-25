package ua.com.foxminded.connection;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection();
    void releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}
