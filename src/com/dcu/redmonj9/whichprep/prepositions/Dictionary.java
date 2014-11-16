package com.dcu.redmonj9.whichprep.prepositions;

import java.util.ArrayList;

public class Dictionary {
	private ArrayList<String> dictionary;
	private ArrayList<String> prepositions;
	
	public Dictionary(){
		dictionary = new ArrayList<String>();
		prepositions = new ArrayList<String>();
		dictionary.add("He will see them in October");
		dictionary.add("He will see them on the 3rd of October");
		dictionary.add("He will arrive at the airport");
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
	
	public ArrayList<String> getDictionary(){
		return dictionary;
	}
	
	public ArrayList<String> getPrepositions(){
		return prepositions;
	}
}
