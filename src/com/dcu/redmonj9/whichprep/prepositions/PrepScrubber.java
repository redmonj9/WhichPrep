package com.dcu.redmonj9.whichprep.prepositions;

import java.util.ArrayList;

public class PrepScrubber {
	
  /**
   * This method scans a sentence for a preposition found in the provided list
   * @param sentence The sentence to be searched for a preposition
   * @param preps The list of possible prepositions to search for
   * @return The preposition found in the sentence
   */
  public static String containsPrep(String sentence, ArrayList<String> preps){
		String result = "";
		for(String prep: preps){
			if(sentence.contains(" "+prep+" "))
				result = prep;
		}
		return result;
	}
	
  /**
   * This method scans the sentence for the given preposition 
   * and replaces with a string of underscores
   * @param sentence The sentence to be searched
   * @param prep The preposition to be removed
   * @return The sentence with preposition replaced
   */
	public static String removePrep(String sentence, String prep){
		return sentence.replace(prep, "____");
	}
}
