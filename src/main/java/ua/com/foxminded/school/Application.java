package ua.com.foxminded.school;

import ua.com.foxminded.DAO.CreatDB;
import ua.com.foxminded.DAO.FillingDB;
import ua.com.foxminded.menu.Menu;

public class Application {

	static {
		CreatDB db = new CreatDB();
		db.createAllDB();
		
		FillingDB fillDB = new FillingDB();
		try {
			fillDB.fillAllDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {	
			Menu menu = new Menu();
     		menu.bildMenu();
	}
}
