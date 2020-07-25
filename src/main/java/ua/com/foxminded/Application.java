package ua.com.foxminded;

import ua.com.foxminded.dao.DBInitialize;
import ua.com.foxminded.menu.Menu;

public class Application {

    static {
        new DBInitialize().createDBWithTables();
        new DBInitialize().fillAllDB();
    }

    public static void main(String[] args) {
        new Menu().runMenu();
    }
}
