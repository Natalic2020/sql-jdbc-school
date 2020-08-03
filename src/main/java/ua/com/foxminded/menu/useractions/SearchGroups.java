package ua.com.foxminded.menu.useractions;

import java.util.List;
import java.util.Scanner;
import ua.com.foxminded.dao.SchoolDao;
import ua.com.foxminded.dto.Group;

public class SearchGroups implements UserOption {

    SchoolDao school;

    public SearchGroups(SchoolDao school) {
        this.school = school;
    }

    @Override
    public void apply(Scanner scanInput) {
        System.out.print("Enter count students in group : ");
        int countStudents = scanInput.nextInt();
        List<Group> groups = school.searchGroups(countStudents);
        
        if (groups.size() == 0) {
            System.out.println("No group found");
        } else {
            groups.stream()
            .forEach(group -> System.out.println(group.toString()));
        }
    }
}
