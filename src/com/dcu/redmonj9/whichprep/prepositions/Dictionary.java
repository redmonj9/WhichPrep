package com.dcu.redmonj9.whichprep.prepositions;

import java.util.ArrayList;

public class Dictionary {
	private static ArrayList<String> dictionary;
	private static ArrayList<String> prepositions;
	
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
		prepositions.add("of");
		prepositions.add("to");
		prepositions.add("for");
		prepositions.add("with");
		prepositions.add("from");
		prepositions.add("by");
		prepositions.add("about");
		prepositions.add("as");
		prepositions.add("into");
		prepositions.add("like");
		prepositions.add("through");
		prepositions.add("after");
		prepositions.add("between");
		prepositions.add("out");
		prepositions.add("against");
		prepositions.add("during");
		prepositions.add("without");
		prepositions.add("before");
		prepositions.add("under");
		prepositions.add("around");
		prepositions.add("among");
		return prepositions;
	}
}
