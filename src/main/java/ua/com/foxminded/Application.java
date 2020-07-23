package ua.com.foxminded;

import ua.com.foxminded.dao.FillDao;
import ua.com.foxminded.menu.Menu;
import ua.com.foxminded.servicedb.FileProcessing;

public class Application {

    static {
        new FileProcessing().createDBWithTables();
        new FillDao().fillAllDB();
    }

    public static void main(String[] args) {
        new Menu().runMenu();
    }
}
