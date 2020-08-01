package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.dao.connection.BasicConnectionPool;

class Creater {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/school50;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false";

    static final String USER = "sa";
    static final String PASS = "";
    
//    static BasicConnectionPool basicConnectionPool = new BasicConnectionPool(DB_URL, USER, PASS,
//            new ArrayList<Connection>());
    
   @Test
    void createXML() throws Exception {
            // database connection
       Connection jdbcConnection = null;
       IDatabaseConnection connection = null;
            try {
                Class driverClass = Class.forName(JDBC_DRIVER);
                 jdbcConnection = DriverManager.getConnection(
                        DB_URL, USER, PASS);
                 connection = new DatabaseConnection(jdbcConnection);
                 
                IDataSet fullDataSet = connection.createDataSet();
                FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                connection.close();
                jdbcConnection.close();
            }
           
            
                 
            
        }


}
