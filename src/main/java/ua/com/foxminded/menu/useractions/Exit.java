package ua.com.foxminded.menu.useractions;

import java.util.Scanner;

import ua.com.foxminded.dao.SchoolDao;

public class Exit implements UserOption {

    SchoolDao school;

    public Exit(SchoolDao school) {
        this.school = school;
    }

    @Override
    public void apply(Scanner scanInput) {
        school.exit();   
    }
}
