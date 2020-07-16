package ua.com.foxminded.school;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class AuxiliaryValue {

	private static final String TEXT_SEPARATOR = "_";
	
	public String[] courses() {
		String[] courses = {"maths",
				"history",
				"geography",
				"literature",
				"sport",
				"music",
				"biology",
				"art",
				"Informatics",
				"religion"};
		return courses;
	}
	
	public List<String> randomValue(int amount) {
		List<String> listWithValue = new ArrayList<>();
		for (int i = 0; i < amount ; i++) {
			listWithValue.add(String.valueOf(i+10));
		}
		
		Collections.shuffle(listWithValue);
		return listWithValue;
	}
	
	public String[] firstNames() {
		String[] names = {"Liam",
				"Emma", "Noah", "Olivia", "Mason", "Ava", "Ethan", "Sophia",  
				"Logan", "Isabella", "Lucas",  "Mia", "Jackson", "Charlotte", 
				"Aiden", "Amelia", "Oliver", "Emily", "Jacob", "Madison"};
		return names;
	}
	
	public String[] lastNames() {
		String[] courses = {"Smith", "Johnson", "Williams", "Jones",
				"Brown", "Davis", "Miller",	"Wilson", "Moore", "Taylor",
				"Anderson", "Thomas", "Jackson", "White", "Harris",
				"Martin", "Thompson", "Wood", "Lewis", "Scott"};
		return courses;
	}
	
	public List<Map<String, String>> fillNames(){
		Set<String> namesNoDouble = new HashSet<>();
		final Random random = new Random();
		while (namesNoDouble.size()<200) {		
				namesNoDouble.add(firstNames()[random.nextInt(19)] + TEXT_SEPARATOR + lastNames()[random.nextInt(19)]);
		}
		List<Map<String, String>>  nameArray = namesNoDouble.stream().map(this::addName)
				.collect(Collectors.toList());
		return nameArray;
	}
	
	private Map<String, String> addName (String text) {
		Map<String, String> name = new HashMap<>(); 
		name.put("firstName", receiveFirstName(text));
		name.put("lastName", receiveLastName(text));
	    return name;	
	}
	
	private String receiveFirstName(String text) {
		int indexSeparator = text.indexOf(TEXT_SEPARATOR);
		if (indexSeparator==-1) {
			return null;
		}
		return text.substring(0, indexSeparator);
	}
	
	private String receiveLastName(String text) {
		int indexSeparator = text.indexOf(TEXT_SEPARATOR);
		if (indexSeparator==-1) {
			return null;
		}
		return text.substring(indexSeparator + 1);
	}	
	
	public Map<Integer, Integer> recieveGroupOfStudents() {

		Map<Integer, Integer> groupOfStudents = new HashMap<>();

		Request request = new Request();
		
		List<Integer> groups = request.receiveAllGroupsIDRandom();
		List<Integer> students = request.receiveAllStudentsRandom();

		final Random random = new Random();
		int j = 0;
		for (Integer groupID : groups) {
			int countStudent = random.nextInt(21);
			for (int i = 0; i < countStudent + 9; i++) {
				groupOfStudents.put(students.get(j), groupID);
				j++;
				if (j >= students.size()) {
					return groupOfStudents;
				}
			}
		}
		return groupOfStudents;
	}
	
	public List<Integer[]> recieveCourcesOfStudents() {
	List<Integer[]> courcesOfStudents = new ArrayList<>();
	Request request = new Request();
	List<Integer> students = request.receiveAllStudents();
	List<Integer> courses = request.receiveAllCourcesID();
	
	students.forEach(data -> {
		receiveCourses( courcesOfStudents, courses, data);
	});
	return courcesOfStudents;
	}
	
	public void receiveCourses(List<Integer[]> courcesOfStudents, List<Integer> courses, Integer studentID) {
		Collections.shuffle(courses);
		final Random random = new Random();
		int quantityCourses = random.nextInt(3);
		for (int i = 0; i <= quantityCourses; i++) {
			Integer[] element = {studentID, courses.get(i)};
			courcesOfStudents.add(element);
		}
	}
}
