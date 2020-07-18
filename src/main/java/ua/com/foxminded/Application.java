package ua.com.foxminded;

import java.util.List;

import ua.com.foxminded.dao.CreatDB;
import ua.com.foxminded.dao.FillingDB;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.menu.Menu;
import ua.com.foxminded.util.AuxiliaryValue;

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
//        AuxiliaryValue value = new AuxiliaryValue();
//        List<Group> groups = value.receiveGroups(10);
        Menu menu = new Menu();
        menu.bildMenu();
    }
}
