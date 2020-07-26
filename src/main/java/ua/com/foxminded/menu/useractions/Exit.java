package ua.com.foxminded.menu.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;

public class Exit implements UserOption {

    SchoolDao query;

    public Exit(SchoolDao query) {
        this.query = query;
    }

    @Override
    public void apply(Scanner scanInput) {
        query.exit();   
    }
}
