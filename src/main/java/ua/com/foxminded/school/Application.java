package ua.com.foxminded.school;

public class Application {

	public static void main(String[] args) throws Exception {

			CreatDB db = new CreatDB();
			db.createAllDB();
			
			FillingDB fillDB = new FillingDB();
			fillDB.fillAllDB();
				
			Menu menu = new Menu();
     		menu.bildMenu();
	}
}
