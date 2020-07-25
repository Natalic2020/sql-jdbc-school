package ua.com.foxminded.servicedb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import ua.com.foxminded.dto.Course;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.dto.Student;

public class SchoolDataGenerator {

    final Random random = new Random();
    private static final String TEXT_SEPARATOR = "_";

    public Group createGroup(String text) {
        return new Group("gr-" + text);
    }

    public String[] formSubjects() {
        String[] subjects = { "maths", "history", "geography", "literature", "sport", "music", "biology", "art",
                "Informatics", "religion" };
        return subjects;
    }

    public List<String> recieveRandomValues(int amount) {
        List<String> listWithValue = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            listWithValue.add(String.valueOf(i + 10));
        }
        Collections.shuffle(listWithValue);
        return listWithValue;
    }

    public List<Group> receiveGroups(int countGroup) {
        List<String> listWithValue = recieveRandomValues(countGroup);
        List<Group> groups = listWithValue
                                          .stream()
                                          .map(this::createGroup)
                                          .collect(Collectors.toList());
        return groups;
    }

    public List<Course> receiveCourses() {
        String[] subjects = formSubjects();
        List<Course> course = Arrays
                                    .stream(subjects)
                                    .map(this::createCourse)
                                    .collect(Collectors.toList());
        return course;
    }

    public Course createCourse(String text) {
        return new Course(text);
    }

    public String[] formFirstNames() {
        String[] names = { "Liam", "Emma", "Noah", "Olivia", "Mason", "Ava", "Ethan", "Sophia", "Logan", "Isabella",
                "Lucas", "Mia", "Jackson", "Charlotte", "Aiden", "Amelia", "Oliver", "Emily", "Jacob", "Madison" };
        return names;
    }

    public String[] formLastNames() {
        String[] courses = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
                "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Wood", "Lewis",
                "Scott" };
        return courses;
    }

    public List<Student> receiveStudents(List<Group> groups) {
        Set<String> namesNoDouble = new HashSet<>();
        while (namesNoDouble.size() < 200) {
            namesNoDouble.add(formName());
        }
        List<Student> students = namesNoDouble
                                              .stream()
                                              .map(this::createStudents)
                                              .collect(Collectors.toList());
        return addGroupsToStudents(groups, students);
    }

    private String formName() {
        return formFirstNames()[random.nextInt(19)] + TEXT_SEPARATOR + formLastNames()[random.nextInt(19)];
    }

    private List<Student> addGroupsToStudents(List<Group> groups, List<Student> students) {
        Optional
                .ofNullable(groups)
                .orElseThrow(() -> new IllegalArgumentException("Null as groups is not allowed "));

        Optional
                .ofNullable(students)
                .orElseThrow(() -> new IllegalArgumentException("Null as students is not allowed "));

        Collections.shuffle(groups);
        Collections.shuffle(students);
        int j = 0;
        for (Group group : groups) {
            int countStudent = random.nextInt(21);
            for (int i = 0; i < countStudent + 9; i++) {
                students.get(j).setGroupId(group.getGroupId());
                j++;
                if (j >= students.size()) {
                    return students;
                }
            }
        }
        return students;
    }

    public Student createStudents(String text) {
        Optional
                .ofNullable(text)
                .orElseThrow(() -> new IllegalArgumentException("Null as name is not allowed "));
        return new Student(receiveFirstName(text), receiveLastName(text));
    }

    private String receiveFirstName(String text) {
        int indexSeparator = text.indexOf(TEXT_SEPARATOR);
        if (indexSeparator == -1) {
            return null;
        }
        return text.substring(0, indexSeparator);
    }

    private String receiveLastName(String text) {
        int indexSeparator = text.indexOf(TEXT_SEPARATOR);
        if (indexSeparator == -1) {
            return null;
        }
        return text.substring(indexSeparator + 1);
    }

    public Map<Integer, List<Integer>> receiveStudentsCourses(List<Course> courses, List<Student> students) {
        Map<Integer, List<Integer>> studentCourses = students
                                                             .stream()
                                                             .collect(Collectors.toMap(i -> i.getStudentId(),
                                                                     i -> receiveStudentCourses(courses)));
        return studentCourses;
    }

    public List<Integer> receiveStudentCourses(List<Course> courses) {
        Collections.shuffle(courses);
        List<Integer> studentcourses = new ArrayList<>();
        int quantityCourses = random.nextInt(3);
        for (int i = 0; i <= quantityCourses; i++) {
            studentcourses.add(courses.get(i).getCourseId());
        }
        return studentcourses;
    }
}
