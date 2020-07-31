package ua.com.foxminded.dao;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

public class SchoolDaoDBUnit2Test extends DataSourceBasedDBTestCase{
    
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/school4";

    static final String USER = "sa";
    static final String PASS = "";
    
  
        @Override
        protected DataSource getDataSource() {
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL(
              "jdbc:h2:~/school21;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:schema.sql'");
            dataSource.setUser("sa");
            dataSource.setPassword("");
            return dataSource;
        }
     
        @Override
        protected IDataSet getDataSet() throws Exception {
            return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
              .getResourceAsStream("school2.xml"));
        }
        
        @Override
        protected DatabaseOperation getSetUpOperation() {
            return DatabaseOperation.REFRESH;
        }
         
        @Override
        protected DatabaseOperation getTearDownOperation() {
            return DatabaseOperation.DELETE_ALL;
        }
        
//        protected void tearDown() throws Exception
//        {
//        // will call default tearDownOperation
//            databaseTester.onTearDown();
//        }
        
        @Test
        public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesAreEqual() throws Exception {
            IDataSet expectedDataSet = getDataSet();
            ITable expectedTable = expectedDataSet.getTable("school.groups");
            IDataSet databaseDataSet = getConnection().createDataSet();
//            ITable actualTable = databaseDataSet.getTable("SCHOOL.'GROUPS'");
            assertEquals(expectedDataSet, databaseDataSet);
        }
    }
