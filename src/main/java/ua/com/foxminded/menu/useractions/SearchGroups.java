package ua.com.foxminded.menu.useractions;

import java.util.Scanner;
import ua.com.foxminded.dao.SchoolDao;

public class SearchGroups implements UserOption {

    SchoolDao query;

    public SearchGroups(SchoolDao query) {
        this.query = query;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter count students in group : ");
        int countStudents = scanInput.nextInt();
        query.searchGroups(countStudents)
             .stream()
             .forEach(group -> System.out.println(group.toString()));
    }
}
