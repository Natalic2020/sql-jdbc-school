package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.Base64.InputStream;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.dao.connection.BasicConnectionPool;

class SchoolDaoDBUnit3Test extends DBTestCase{

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/schoo57;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false";

    static final String USER = "sa";
    static final String PASS = "";

    protected IDatabaseTester tester;
    private Properties prop;
    protected IDataSet beforeData;
    
    static BasicConnectionPool basicConnectionPool = new BasicConnectionPool(DB_URL, USER, PASS,
           new ArrayList<Connection>());

//    SchoolDao school = new SchoolDao(basicConnectionPool);
    
    //"C:/eclipse_workspace/Mentoring7/sql-jdbc-school/src/test/resources/school2.xml"
    
    @BeforeAll
   static void  init() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = basicConnectionPool.getConnection();
            stmt = conn.createStatement();

            String sql;
            
             sql = "DROP ALL OBJECTS";
//            stmt.executeUpdate(sql);
//            sql = "CREATE SCHEMA school";
            stmt.executeUpdate(sql);
            sql = "create table  groups (group_id serial PRIMARY KEY, group_name character(50)  NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "create table students (student_id serial PRIMARY KEY, group_id int,    first_name character(50)  NOT NULL, last_name character(50) NOT NULL);";
            stmt.executeUpdate(sql);
            sql = "create table courses (course_id serial PRIMARY KEY,    course_name character(50)  NOT NULL,    course_description character(150))";
            stmt.executeUpdate(sql);
            sql = "create table students_courses (students_courses_id serial PRIMARY KEY, course_id int  ,  student_id int )";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                basicConnectionPool.shutdown();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }  
        
        }
    
    @BeforeEach
    public void setUp() throws Exception {

        tester = new JdbcDatabaseTester(JDBC_DRIVER, DB_URL, USER, PASS);
        
        Class.forName(JDBC_DRIVER);
        beforeData = new FlatXmlDataSetBuilder().build(
                     Thread.currentThread().getContextClassLoader()
                     .getResourceAsStream("school2.xml"));

        
        tester.setDataSet(beforeData);
        tester.onSetup();
    }
    
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
          .getResourceAsStream("school2.xml"));
    }

    @Test
    public void addStudent_schoudReturn1Student2_whenInput1Student() throws Exception {

//        SchoolDao school = new SchoolDao(basicConnectionPool);
//        int countStudent = school.addStudent(5, "Nata", "Svet");

        IDataSet expectedData = getDataSet();
        
        IDataSet actualData = tester.getConnection().createDataSet();
        
        Assertion.assertEquals(expectedData, actualData);
        
        Assert.assertEquals(expectedData.getTable("GROUPS").getRowCount(), 4);
        
   
    }
    
    
    @Test
    public void addStudent_schoudReturn1Student_whenInput1Student() throws Exception {

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("school2.xml"));
        
        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData, actualData);
        
        Assert.assertEquals(expectedData.getTable("GROUPS").getRowCount(), 4);
        
    }
    
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
    
    @AfterEach
    protected void tearDown() {
        basicConnectionPool.getUsedConnections().clear();
    }

    @AfterAll
    static void tearDownAll() {
        try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
}
