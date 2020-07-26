package ua.com.foxminded.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {
    
    private static final int MAX_POOL_SIZE = 20;
    private static final int MAX_TIMEOUT = 10;
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;
    
    public static BasicConnectionPool create(
      String url, String user, 
      String password) throws SQLException {
 
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new BasicConnectionPool(url, user, password, pool);
    }
    
    public BasicConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        super();
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    @Override
    public Connection getConnection() {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                try {
                    connectionPool.add(createConnection(url, user, password));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                throw new RuntimeException(
                  "Maximum pool size reached, no available connections!");
            }
        }
     
        Connection connection = connectionPool
          .remove(connectionPool.size() - 1);
     
        try {
            if(!connection.isValid(MAX_TIMEOUT)){
                connection = createConnection(url, user, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
        usedConnections.add(connection);
        return connection;
    }
    
    @Override
    public void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }
    
    private static Connection createConnection(
      String url, String user, String password) 
      throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    public List<Connection> getUsedConnections() {
        return usedConnections;
    }

    public static int getINITIAL_POOL_SIZE() {
        return INITIAL_POOL_SIZE;
    }
    
    public void shutdown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        usedConnections.clear();
        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();
    }
}
