package com.dcu.redmonj9.whichprep.prepositions;

import java.util.ArrayList;
import java.util.Random;

public class PrepScrubber {
		
	public static String containsPrep(String sentence, ArrayList<String> preps){
		ArrayList<String> results = new ArrayList<String>();
		for(String prep: preps){
			if(sentence.contains(" "+prep+" "))
				results.add(prep);
		}
		return results.get(new Random().nextInt(results.size()));
	}
	
	public static String removePrep(String sentence, String prep){
		return sentence.replace(prep, "____");
	}
}
