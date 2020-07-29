package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.dao.connection.BasicConnectionPool;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.dto.Student;

class SchoolDaoTest {

    // JDBC driver name and database URL 
    static final String JDBC_DRIVER = "org.h2.Driver";   
    static final String DB_URL = "jdbc:h2:~/test";  
    
    //  Database credentials 
    static final String USER = "sa"; 
    static final String PASS = ""; 
   
    @Test
    public  void  addStudent_schoudReturn1Student_whenInput1Student() { 
       Connection conn = null; 
       Statement stmt = null; 
       BasicConnectionPool basicConnectionPool = new BasicConnectionPool(DB_URL, USER, PASS,
               new ArrayList<Connection>());
       try { 
          Class.forName(JDBC_DRIVER);     
          System.out.println("Connecting to database...");  
          conn = basicConnectionPool.getConnection();

          System.out.println("Creating table in given database..."); 
          stmt = conn.createStatement(); 
          String sql = "DROP ALL OBJECTS";
          stmt.executeUpdate(sql);
          sql = "CREATE SCHEMA school";
          stmt.executeUpdate(sql);
          sql =  "create table school.groups (group_id serial PRIMARY KEY, group_name character(50)  NOT NULL)";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students (student_id serial PRIMARY KEY, group_id int,    first_name character(50)  NOT NULL, last_name character(50) NOT NULL);";  
          stmt.executeUpdate(sql);
          sql =  "create table school.courses (course_id serial PRIMARY KEY,    course_name character(50)  NOT NULL,    course_description character(150))";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students_courses (students_courses_id serial PRIMARY KEY, course_id int  REFERENCES school.courses ON DELETE CASCADE,  student_id int REFERENCES school.students ON DELETE CASCADE)";  
          stmt.executeUpdate(sql);
          System.out.println("Created tables in given database..."); 
          
          SchoolDao school = new SchoolDao(basicConnectionPool);
          school.addStudent(1, "Nata", "Svet");
          
          sql = "Select * From school.students";
          ResultSet rs = stmt.executeQuery(sql);  
 
          List<Student> students = new ArrayList<>(); 
          
          while(rs.next()){ 
             int id  = rs.getInt("student_id"); 
             String first = rs.getString("first_name"); 
             String last = rs.getString("last_name");
             students.add(new Student(first, last)); 
             assertEquals(1, id, "id = 1");
             assertEquals("Nata", first, "first = Nata");
             assertEquals("Svet", last, "last = Svet");
          }
          
          assertEquals(1, students.size(), "Amount line = 1");
          
       } catch(SQLException se) {   
          se.printStackTrace(); 
       } catch(Exception e) {        
          e.printStackTrace(); 
       } finally { 
           try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       } 
       System.out.println("Goodbye!");
    } 
    
    
    @Test
    public  void  searchGroups_schoudReturn2Groups_whenInput2RightGroups() { 
       Connection conn = null; 
       Statement stmt = null; 
       BasicConnectionPool basicConnectionPool = new BasicConnectionPool(DB_URL, USER, PASS,
               new ArrayList<Connection>());
       try { 
          Class.forName(JDBC_DRIVER);     
          System.out.println("Connecting to database...");  
          conn = basicConnectionPool.getConnection();

          System.out.println("Creating table in given database..."); 
          stmt = conn.createStatement(); 
          String sql = "DROP ALL OBJECTS";
          stmt.executeUpdate(sql);
          sql = "CREATE SCHEMA school";
          stmt.executeUpdate(sql);
          sql =  "create table school.groups (group_id serial PRIMARY KEY, group_name character(50)  NOT NULL)";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students (student_id serial PRIMARY KEY, group_id int,    first_name character(50)  NOT NULL, last_name character(50) NOT NULL);";  
          stmt.executeUpdate(sql);
          sql =  "create table school.courses (course_id serial PRIMARY KEY,    course_name character(50)  NOT NULL,    course_description character(150))";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students_courses (students_courses_id serial PRIMARY KEY, course_id int  REFERENCES school.courses ON DELETE CASCADE,  student_id int REFERENCES school.students ON DELETE CASCADE)";  
          stmt.executeUpdate(sql);
          System.out.println("Created tables in given database..."); 
          
          String sqlGroups = "insert into school.groups (group_id, group_name) values (1,'gr1')";
          stmt.executeUpdate(sqlGroups);
          sqlGroups = "insert into school.groups (group_id, group_name) values (2,'gr2')";
          stmt.executeUpdate(sqlGroups);
          sqlGroups = "insert into school.groups (group_id, group_name) values (3,'gr3')";
          stmt.executeUpdate(sqlGroups);
          
          String sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(1,'Nina','Ivanova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(2,'Nona','Petrova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(3,'Igor','Beliy', 3)";
          stmt.executeUpdate(sqlStudent);
          
          SchoolDao school = new SchoolDao(basicConnectionPool);
          
          List<Group> expected = new ArrayList<Group>();
          expected.add(new Group("gr3", 1));
          
          List<Group> actual = school.searchGroups(1);
          
          assertEquals(expected, actual);
          
       } catch(SQLException se) {   
          se.printStackTrace(); 
       } catch(Exception e) {        
          e.printStackTrace(); 
       } finally { 
           try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       } 
       System.out.println("Goodbye!");
    } 
    
    @Test
    public  void  searchStudentsInCourse_schoudReturn2Students_whenInput1Course() { 
       Connection conn = null; 
       Statement stmt = null; 
       BasicConnectionPool basicConnectionPool = new BasicConnectionPool(DB_URL, USER, PASS,
               new ArrayList<Connection>());
       try { 
          Class.forName(JDBC_DRIVER);     
          System.out.println("Connecting to database...");  
          conn = basicConnectionPool.getConnection();

          System.out.println("Creating table in given database..."); 
          stmt = conn.createStatement(); 
          String sql = "DROP ALL OBJECTS";
          stmt.executeUpdate(sql);
          sql = "CREATE SCHEMA school";
          stmt.executeUpdate(sql);
          sql =  "create table school.groups (group_id serial PRIMARY KEY, group_name character(50)  NOT NULL)";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students (student_id serial PRIMARY KEY, group_id int,    first_name character(50)  NOT NULL, last_name character(50) NOT NULL);";  
          stmt.executeUpdate(sql);
          sql =  "create table school.courses (course_id serial PRIMARY KEY,    course_name character(50)  NOT NULL,    course_description character(150))";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students_courses (students_courses_id serial PRIMARY KEY, course_id int  REFERENCES school.courses ON DELETE CASCADE,  student_id int REFERENCES school.students ON DELETE CASCADE)";  
          stmt.executeUpdate(sql);
          System.out.println("Created tables in given database..."); 
          
          String sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(1,'Nina','Ivanova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(2,'Nona','Petrova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(3,'Igor','Beliy', 3)";
          stmt.executeUpdate(sqlStudent);
          
          String sqlCourses = "insert into school.courses (course_id, course_name) values (1, 'maths')";
          stmt.executeUpdate(sqlCourses);
          sqlCourses = "insert into school.courses (course_id, course_name) values (2, 'art')";
          stmt.executeUpdate(sqlCourses);
          
          String sqlStCourses = "insert into school.students_courses ( student_id, course_id ) values (1,1)";
          stmt.executeUpdate(sqlStCourses);
          sqlStCourses = "insert into school.students_courses ( student_id, course_id ) values (2,1)";
          stmt.executeUpdate(sqlStCourses);
          sqlStCourses = "insert into school.students_courses ( student_id, course_id ) values (3,2)";
          stmt.executeUpdate(sqlStCourses);
          
          SchoolDao school = new SchoolDao(basicConnectionPool);
          
          
          List<Student> expected = new ArrayList<>();
          expected.add(new Student("Nina", "Ivanova"));
          expected.add(new Student("Nona","Petrova"));
          
          List<Student> actual = school.searchStudentsInCourse("maths");
          
          assertEquals(expected, actual);
          
       } catch(SQLException se) {   
          se.printStackTrace(); 
       } catch(Exception e) {        
          e.printStackTrace(); 
       } finally { 
           try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       } 
       System.out.println("Goodbye!");
    } 
    
    @Test
    public  void  deleteStudent_schoudDeletedStudent_whenDeleteStudent() { 
       Connection conn = null; 
       Statement stmt = null; 
       BasicConnectionPool basicConnectionPool = new BasicConnectionPool(DB_URL, USER, PASS,
               new ArrayList<Connection>());
       try { 
          Class.forName(JDBC_DRIVER);     
          System.out.println("Connecting to database...");  
          conn = basicConnectionPool.getConnection();

          System.out.println("Creating table in given database..."); 
          stmt = conn.createStatement(); 
          String sql = "DROP ALL OBJECTS";
          stmt.executeUpdate(sql);
          sql = "CREATE SCHEMA school";
          stmt.executeUpdate(sql);
          sql =  "create table school.groups (group_id serial PRIMARY KEY, group_name character(50)  NOT NULL)";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students (student_id serial PRIMARY KEY, group_id int,    first_name character(50)  NOT NULL, last_name character(50) NOT NULL);";  
          stmt.executeUpdate(sql);
          sql =  "create table school.courses (course_id serial PRIMARY KEY,    course_name character(50)  NOT NULL,    course_description character(150))";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students_courses (students_courses_id serial PRIMARY KEY, course_id int  REFERENCES school.courses ON DELETE CASCADE,  student_id int REFERENCES school.students ON DELETE CASCADE)";  
          stmt.executeUpdate(sql);
          System.out.println("Created tables in given database..."); 
          
          String sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(1,'Nina','Ivanova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(2,'Nona','Petrova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(3,'Igor','Beliy', 3)";
          stmt.executeUpdate(sqlStudent);
          
          SchoolDao school = new SchoolDao(basicConnectionPool);
          int countDeletedStudent = school.deleteStudent(2);
          
          assertEquals(1, countDeletedStudent, "Deleted 1 Student");
          
          sql = "Select * From school.students where student_id = 2";
          ResultSet rs = stmt.executeQuery(sql);    
          
          assertFalse(rs.first());
          
       } catch(SQLException se) {   
          se.printStackTrace(); 
       } catch(Exception e) {        
          e.printStackTrace(); 
       } finally { 
           try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       } 
       System.out.println("Goodbye!");
    } 
    
    @Test
    public  void  addStudentToCourse_schoudReturn1StudentInCourse_whenInputStudentCourse() { 
       Connection conn = null; 
       Statement stmt = null; 
       BasicConnectionPool basicConnectionPool = new BasicConnectionPool(DB_URL, USER, PASS,
               new ArrayList<Connection>());
       try { 
          Class.forName(JDBC_DRIVER);     
          System.out.println("Connecting to database...");  
          conn = basicConnectionPool.getConnection();

          System.out.println("Creating table in given database..."); 
          stmt = conn.createStatement(); 
          String sql = "DROP ALL OBJECTS";
          stmt.executeUpdate(sql);
          sql = "CREATE SCHEMA school";
          stmt.executeUpdate(sql);
          sql =  "create table school.groups (group_id serial PRIMARY KEY, group_name character(50)  NOT NULL)";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students (student_id serial PRIMARY KEY, group_id int,    first_name character(50)  NOT NULL, last_name character(50) NOT NULL);";  
          stmt.executeUpdate(sql);
          sql =  "create table school.courses (course_id serial PRIMARY KEY,    course_name character(50)  NOT NULL,    course_description character(150))";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students_courses (students_courses_id serial PRIMARY KEY, course_id int  REFERENCES school.courses ON DELETE CASCADE,  student_id int REFERENCES school.students ON DELETE CASCADE)";  
          stmt.executeUpdate(sql);
          System.out.println("Created tables in given database..."); 
          
          String sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(1,'Nina','Ivanova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(2,'Nona','Petrova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(3,'Igor','Beliy', 3)";
          stmt.executeUpdate(sqlStudent);
          
          String sqlCourses = "insert into school.courses (course_id, course_name) values (1, 'maths')";
          stmt.executeUpdate(sqlCourses);
          sqlCourses = "insert into school.courses (course_id, course_name) values (2, 'art')";
          stmt.executeUpdate(sqlCourses);
          
          SchoolDao school = new SchoolDao(basicConnectionPool);
          int countStudent = school.addStudentToCourse(1,1);
          
          assertEquals(1, countStudent, "Added 1 Student to Course");
          
          sql = "Select * From school.students_courses where student_id = 1 and course_id = 1";
          ResultSet rs = stmt.executeQuery(sql); 
          
          assertTrue(rs.first());
          
              int idStudent  = rs.getInt("student_id"); 
              int idCourse  = rs.getInt("course_id"); 
              assertEquals(1, idStudent, "student_id = 1");
              assertEquals(1, idCourse, "course_id = 1");
          
       } catch(SQLException se) {   
          se.printStackTrace(); 
       } catch(Exception e) {        
          e.printStackTrace(); 
       } finally { 
           try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       } 
       System.out.println("Goodbye!");
    } 
    
    @Test
    public  void  removeStudentFromCourse_schoudReturnEmpty_whenDeleteStudentFromCourse() { 
       Connection conn = null; 
       Statement stmt = null; 
       BasicConnectionPool basicConnectionPool = new BasicConnectionPool(DB_URL, USER, PASS,
               new ArrayList<Connection>());
       try { 
          Class.forName(JDBC_DRIVER);     
          System.out.println("Connecting to database...");  
          conn = basicConnectionPool.getConnection();

          System.out.println("Creating table in given database..."); 
          stmt = conn.createStatement(); 
          String sql = "DROP ALL OBJECTS";
          stmt.executeUpdate(sql);
          sql = "CREATE SCHEMA school";
          stmt.executeUpdate(sql);
          sql =  "create table school.groups (group_id serial PRIMARY KEY, group_name character(50)  NOT NULL)";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students (student_id serial PRIMARY KEY, group_id int,    first_name character(50)  NOT NULL, last_name character(50) NOT NULL);";  
          stmt.executeUpdate(sql);
          sql =  "create table school.courses (course_id serial PRIMARY KEY,    course_name character(50)  NOT NULL,    course_description character(150))";  
          stmt.executeUpdate(sql);
          sql =  "create table school.students_courses (students_courses_id serial PRIMARY KEY, course_id int  REFERENCES school.courses ON DELETE CASCADE,  student_id int REFERENCES school.students ON DELETE CASCADE)";  
          stmt.executeUpdate(sql);
          System.out.println("Created tables in given database..."); 
          
          String sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(1,'Nina','Ivanova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(2,'Nona','Petrova', 1)";
          stmt.executeUpdate(sqlStudent);
          sqlStudent = "insert into school.students (student_id, first_name, last_name, group_id) values"
                  + "(3,'Igor','Beliy', 3)";
          stmt.executeUpdate(sqlStudent);
          
          String sqlCourses = "insert into school.courses (course_id, course_name) values (1, 'maths')";
          stmt.executeUpdate(sqlCourses);
          sqlCourses = "insert into school.courses (course_id, course_name) values (2, 'art')";
          stmt.executeUpdate(sqlCourses);
          
          String sqlStCourses = "insert into school.students_courses ( student_id, course_id ) values (1,1)";
          stmt.executeUpdate(sqlStCourses);
          sqlStCourses = "insert into school.students_courses ( student_id, course_id ) values (2,1)";
          stmt.executeUpdate(sqlStCourses);
          sqlStCourses = "insert into school.students_courses ( student_id, course_id ) values (3,2)";
          stmt.executeUpdate(sqlStCourses);
          
          SchoolDao school = new SchoolDao(basicConnectionPool);
          int countStudent = school.removeStudentFromCourse(1,1);
          
          assertEquals(1, countStudent, "Deleted 1 Student to Course");
          
          sql = "Select * From school.students_courses where student_id = 1 and course_id = 1";
          ResultSet rs = stmt.executeQuery(sql); 
          
          assertFalse(rs.first());
          
       } catch(SQLException se) {   
          se.printStackTrace(); 
       } catch(Exception e) {        
          e.printStackTrace(); 
       } finally { 
           try {
            basicConnectionPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       } 
       System.out.println("Goodbye!");
    } 
}
