package com.dcu.redmonj9.whichprep.prepositions;

import java.util.ArrayList;

public class PrepScrubber {
		
	public String containsPrep(String sentence, ArrayList<String> preps){
		String result = "";
		for(String prep: preps){
			if(sentence.contains(" "+prep+" "))
				result = prep;
		}
		return result;
	}
	
	public String removePrep(String sentence, String prep){
		return sentence.replace(prep, "____");
	}
}
