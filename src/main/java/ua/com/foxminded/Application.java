package ua.com.foxminded;

import ua.com.foxminded.dao.CreatDB;
import ua.com.foxminded.dao.FillingDB;
import ua.com.foxminded.menu.Menu;

public class Application {

    static {
        CreatDB db = new CreatDB();
        db.createAllDB();

        FillingDB fillDB = new FillingDB();
        try {
            fillDB.fillAllDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Menu menu = new Menu();
        menu.bildMenu();
    }
}
