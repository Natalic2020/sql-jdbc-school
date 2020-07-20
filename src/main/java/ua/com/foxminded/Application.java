package ua.com.foxminded;

import ua.com.foxminded.dao.CreatDB;
import ua.com.foxminded.dao.FillDao;
import ua.com.foxminded.menu.Menu;

public class Application {

    static {
        new CreatDB().createAllDB();
        new FillDao().fillAllDB();
    }

    public static void main(String[] args) {
        new Menu().runMenu();
    }
}
