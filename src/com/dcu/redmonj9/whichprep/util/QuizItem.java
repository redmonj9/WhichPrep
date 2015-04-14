package com.dcu.redmonj9.whichprep.util;

import com.dcu.redmonj9.whichprep.prepositions.Dictionary;
import com.dcu.redmonj9.whichprep.prepositions.PrepScrubber;

public class QuizItem {
	String originalSentence;
	String editedSentence;
	String key;

	public QuizItem(String sentence) {
		originalSentence = sentence;
		key = PrepScrubber.containsPrep(sentence, Dictionary.getPrepositions());
		editedSentence = PrepScrubber.removePrep(originalSentence, key);
	}

	public String getOriginalSentence() {
		return originalSentence;
	}

	public String getEditedSentence() {
		return editedSentence;
	}

	public String getKey() {
		return key;
	}
}
