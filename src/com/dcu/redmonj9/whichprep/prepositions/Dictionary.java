package com.dcu.redmonj9.whichprep.prepositions;

import java.util.ArrayList;

public class Dictionary {
	private static ArrayList<String> dictionary;
	private static ArrayList<String> prepositions;
	
	public Dictionary(){
		dictionary = new ArrayList<String>();
		prepositions = new ArrayList<String>();
		dictionary.add("He will see them in October");
		dictionary.add("They will meet him in two months");
		dictionary.add("She put on some music");
		dictionary.add("He will see them on the 3rd of October");
		dictionary.add("He will arrive at the airport");
		dictionary.add("He met her at noon for tea");
		dictionary.add("They will meet regurlarly over 2 months");
		dictionary.add("He was pulled over for speeding");
		dictionary.add("He looked above him for the timetable");
		dictionary.add("A captain is above a lieutenant");
		dictionary.add("He considered such an action below his notice");
		dictionary.add("The water was well below freezing");
		
		prepositions.add("in");
		prepositions.add("on");
		prepositions.add("at");
		prepositions.add("over");
		prepositions.add("above");
		prepositions.add("below");
	}
	
	public String getDictionaryItem(int index){
		return dictionary.get(index);
	}
	
	public String getPrepItem(int index) {
		return prepositions.get(index);
	}
	
	public static ArrayList<String> getDictionary(){
		dictionary = new ArrayList<String>();
		dictionary.add("He will see them in October");
		dictionary.add("They will meet him in two months");
		dictionary.add("She put on some music");
		dictionary.add("He will see them on the 3rd of October");
		dictionary.add("He will arrive at the airport");
		dictionary.add("He met her at noon for tea");
		dictionary.add("They will meet regurlarly over 2 months");
		dictionary.add("He was pulled over for speeding");
		dictionary.add("He looked above him for the timetable");
		dictionary.add("A captain is above a lieutenant");
		dictionary.add("He considered such an action below his notice");
		dictionary.add("The water was well below freezing");
		return dictionary;
	}
	
	public static ArrayList<String> getPrepositions(){
		prepositions = new ArrayList<String>();
		prepositions.add("in");
		prepositions.add("on");
		prepositions.add("at");
		prepositions.add("over");
		prepositions.add("above");
		prepositions.add("below");
		return prepositions;
	}
}
