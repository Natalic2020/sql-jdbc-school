package ua.com.foxminded.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ua.com.foxminded.useractions.AddStudent;
import ua.com.foxminded.useractions.AddStudentToCourse;
import ua.com.foxminded.useractions.DeleteStudent;
import ua.com.foxminded.useractions.RemoveStudentFromCourse;
import ua.com.foxminded.useractions.SearchGroups;
import ua.com.foxminded.useractions.SearchStudentsInCourse;
import ua.com.foxminded.useractions.UserOption;

public class Menu {

    Map<Integer, Object> menuMap = new HashMap<>();
   
    public Menu() {
        List<String> menu = new ArrayList<>();
        menu.add("1. Find all groups with less or equals student count");
        menu.add("2. Find all students related to course with given name");
        menu.add("3. Add new student");
        menu.add("4. Delete student by STUDENT_ID");
        menu.add("5. Add a student to the course (from a list)");
        menu.add("6. Remove the student from one of his or her courses");
        menu.add("7. Exit");
        menu.forEach(System.out::println);
        createMapMenu();
    }

    public void bildMenu() {
        int choice = 0;

        try (Scanner scanInput = new Scanner(System.in)) {
            while (choice != 7) {
                choice = scanInput.nextInt();
                runChoice(choice, scanInput);
            }
        }
        System.out.println("Shao");
    }

    private void runChoice(int choice, Scanner scanInput) {
        
        if (choice < 1 || choice > 6) {
            return ;
        }
        UserOption userOption = (UserOption) menuMap.get(choice);
        userOption.apply(scanInput);
        return  ;
    }

    private void createMapMenu() {
        menuMap.put(1, new SearchGroups());
        menuMap.put(2, new SearchStudentsInCourse());
        menuMap.put(3, new AddStudent());
        menuMap.put(4, new DeleteStudent());
        menuMap.put(5, new AddStudentToCourse());
        menuMap.put(6, new RemoveStudentFromCourse());
    }
}
