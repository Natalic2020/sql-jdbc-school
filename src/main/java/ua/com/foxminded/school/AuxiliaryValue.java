package ua.com.foxminded.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuxiliaryValue {

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
		for (int i = 0; i < amount; i++) {
			listWithValue.add(String.valueOf(i+1));
		}
		
		Collections.shuffle(listWithValue);
		return listWithValue;
	}
	
	public String[] firstName() {
		String[] names = {"Liam",
				"Emma", "Noah", "Olivia", "Mason", "Ava", "Ethan", "Sophia",  
				"Logan", "Isabella", "Lucas",  "Mia", "Jackson", "Charlotte", 
				"Aiden", "Amelia", "Oliver", "Emily", "Jacob", "Madison"};
		return names;
	}
	
	public String[] lastName() {
		String[] courses = {"Smith", "Johnson", "Williams", "Jones",
				"Brown", "Davis", "Miller",	"Wilson", "Moore", "Taylor",
				"Anderson", "Thomas", "Jackson", "White", "Harris",
				"Martin", "Thompson", "Wood", "Lewis", "Scott"};
		return courses;
	}
}
