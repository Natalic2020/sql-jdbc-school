package ua.com.foxminded.servicedb;

import java.util.List;

import ua.com.foxminded.dao.CreatDB;
import ua.com.foxminded.util.FileParser;

public class FileProcessing {

    private static final String URL = "jdbc:postgresql://localhost";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public void createDBWithTables() {
        FileParser file = new FileParser();
        List<String> sqlQueryList = file.readFileToLines("sql.script");
        sqlQueryList.stream().limit(2).forEach(this::dropCreateDB);
        sqlQueryList.stream().skip(2).forEach(this::createTable);
    }
    
    public void dropCreateDB(String sql) {
        CreatDB db = new CreatDB();
        db.runQuery(URL+"/",  USERNAME, PASSWORD, sql);
    }

    public void createTable(String sql) {
        CreatDB db = new CreatDB();
        db.runQuery(URL+":5432/school1",  USERNAME, PASSWORD, sql);
    }  
}
