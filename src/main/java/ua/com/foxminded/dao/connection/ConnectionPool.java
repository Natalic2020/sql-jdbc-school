package ua.com.foxminded.dao.connection;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection();
    void releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}
