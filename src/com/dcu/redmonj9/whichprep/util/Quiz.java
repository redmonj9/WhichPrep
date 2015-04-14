package com.dcu.redmonj9.whichprep.util;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Quiz {
	Stack<QuizItem> questions = new Stack<QuizItem>();
	
	public Quiz(List<String> questions){
		for(String question: questions){
			this.questions.add(new QuizItem(question));
		}
		Collections.shuffle(this.questions);
	}
	
	public QuizItem getQuestion(){
		return questions.pop();
	}
}
