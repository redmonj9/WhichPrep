package com.dcu.redmonj9.whichprep.util;

public enum WhichPrepConstants {
	INCORRECTQUESTIONS("incorrectQuestions"),
	INCORRECTANSWERS("incorrectAnswers"),
	POINTS("points"),
	QUIZTYPE ("quiztype"),
	TIMEDQUIZ ("TimedQuiz"),
	NORMALQUIZ ("NormalQuiz"),
	CASUALQUIZ ("CasualQuiz");
	
	public final String constant;
	
	WhichPrepConstants(String constant) {
		this.constant = constant;
	}
}
