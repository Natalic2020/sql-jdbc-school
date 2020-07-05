package ua.com.foxminded.school;

import java.sql.SQLException;

public class Application {

	public static void main(String[] args) throws Exception {

			CreatDB db1 = new CreatDB();
			
			db1.deleteTable("courses");
			db1.deleteTable("groups");
			db1.deleteTable("students");
			
			ParsingFile file = new ParsingFile();
			file.parseFile();
			
//			db1.createTables();
//			db1.createSchedule();
			
//			Menu menu = new Menu();
//			menu.bildMenu();

	}

}
