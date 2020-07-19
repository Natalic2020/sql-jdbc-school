package ua.com.foxminded.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import ua.com.foxminded.dto.Course;
import ua.com.foxminded.dto.Group;
import ua.com.foxminded.dto.Student;

public class AuxiliaryValue {

//    int groupId = 1;
//    int courseId = 1;
//    int studentId = 1;
//    int scheduleId = 1;
    private static final String TEXT_SEPARATOR = "_";

    public Group createGroup(String text) {
        return new Group( "gr-" +  text) ;
    }
    
    public String[] subjects() {
        String[] subjects = { "maths", "history", "geography", "literature", "sport", "music", "biology", "art",
                "Informatics", "religion" };
        return subjects;
    }

    public List<String> randomValue(int amount) {
        List<String> listWithValue = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            listWithValue.add(String.valueOf(i + 10));
        }
        Collections.shuffle(listWithValue);
        return listWithValue;
    }

    public List<Group>  receiveGroups(int countGroup) {
        List<String> listWithValue = randomValue(countGroup); 
        List<Group> groups = listWithValue.stream().map(this::createGroup).collect(Collectors.toList());
        return groups;
    } 
    
    public List<Course>  receiveCourses() {
        String[] subjects = subjects();
        List<Course> course = Arrays.stream(subjects).map(this::createCourse).collect(Collectors.toList());
        return course;
    }
    
    public Course createCourse(String text) {
        return new Course(text) ;
    }
    
    public String[] firstNames() {
        String[] names = { "Liam", "Emma", "Noah", "Olivia", "Mason", "Ava", "Ethan", "Sophia", "Logan", "Isabella",
                "Lucas", "Mia", "Jackson", "Charlotte", "Aiden", "Amelia", "Oliver", "Emily", "Jacob", "Madison" };
        return names;
    }

    public String[] lastNames() {
        String[] courses = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
                "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Wood", "Lewis",
                "Scott" };
        return courses;
    }
    
    public List<Student>  receiveStudents(List<Group> groups) {
        Set<String> namesNoDouble = new HashSet<>();
        final Random random = new Random();
        while (namesNoDouble.size() < 200) {
            namesNoDouble.add(firstNames()[random.nextInt(19)] + TEXT_SEPARATOR + lastNames()[random.nextInt(19)]);
        }   
        List<Student> students = namesNoDouble.stream().map(this::createStudents).collect(Collectors.toList());
        return addGroupsToStudents(groups, students);
    }

    private List<Student> addGroupsToStudents(List<Group> groups, List<Student> students) {
        Collections.shuffle(groups);
        Collections.shuffle(students);    
        final Random random = new Random();
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
        return new Student(receiveFirstName(text), receiveLastName(text)) ;
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
    
    public List<Integer[]> receiveSchedules(List<Course> courses, List<Student> students) {
        
        List<Integer[]> schedule = new ArrayList<>();
        students.forEach(student -> receiveCourses(schedule, courses, student));
        return schedule;
    }

    public void receiveCourses(List<Integer[]> schedule, List<Course> courses, Student student) {
        Collections.shuffle(courses);
        final Random random = new Random();
        int quantityCourses = random.nextInt(3);
        for (int i = 0; i <= quantityCourses; i++) {
            Integer[] element = { student.getStudentId(), courses.get(i).getCourseId() };
            schedule.add(element);
        }
    }
}
