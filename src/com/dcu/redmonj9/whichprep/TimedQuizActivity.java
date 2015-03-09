package com.dcu.redmonj9.whichprep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import com.dcu.redmonj9.whichprep.prepositions.Dictionary;
import com.dcu.redmonj9.whichprep.prepositions.PrepScrubber;
import com.dcu.redmonj9.whichprep.util.WhichPrepConstants;
import com.dcu.redmonj9.whichprep.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TimedQuizActivity extends Activity implements OnClickListener{

	private String key = "";
	private int points = 0;
	private int numQs = 1;
	private TextView questionField;
	private TextView pointsField;
	private Button option1;
	private Button option2;
	private Button option3;
	private Button option4;
	private ProgressBar progressBar;
	private Stack<String> questions;
	private Stack<String> answersOptions;
	private ArrayList<String> prepositions;
	private final String scoreText = "Your Score: ";
	private MyCountDownTimer myCountDownTimer;
	
	@Override
 	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		questionField = (TextView) findViewById(R.id.question_field);
		pointsField = (TextView) findViewById(R.id.points_field);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		option1 = (Button) findViewById(R.id.button1);
		option2 = (Button) findViewById(R.id.button2);
		option3 = (Button) findViewById(R.id.button3);
		option4 = (Button) findViewById(R.id.button4);
		option1.setOnClickListener(this);
		option2.setOnClickListener(this);
		option3.setOnClickListener(this);
		option4.setOnClickListener(this);

		questions = new Stack<String>();
		answersOptions = new Stack<String>();
		questions.addAll(Dictionary.getDictionary());
		Collections.shuffle(questions, new Random(System.nanoTime()));
		progressBar.setProgress(300);
		myCountDownTimer = new MyCountDownTimer(30000, 50);
		myCountDownTimer.start();
		progressBar.setMax(300);
		runQuiz();
	}
	
	public void runQuiz(){
		pointsField.setText(scoreText+""+points);
		if(numQs <= 10){
			String question = questions.pop();
			String prep = PrepScrubber.containsPrep(question, Dictionary.getPrepositions());
			questionField.setText(numQs + ". " + PrepScrubber.removePrep(question, PrepScrubber.containsPrep(question, Dictionary.getPrepositions())));
			prepositions = Dictionary.getPrepositions();
			prepositions.remove(prepositions.indexOf(prep));
			answersOptions.push(prep);
			key = prep;
			Collections.shuffle(prepositions);
			answersOptions.push(prepositions.get(0));
			answersOptions.push(prepositions.get(1));
			answersOptions.push(prepositions.get(2));
			Collections.shuffle(answersOptions);
			
			option1.setText(answersOptions.pop());
			option2.setText(answersOptions.pop());
			option3.setText(answersOptions.pop());
			option4.setText(answersOptions.pop());
		} else {
			Intent i = new Intent(this, QuizResultsActivity.class);
			i.putExtra("points", points);
			i.putExtra(WhichPrepConstants.QUIZTYPE.toString(), WhichPrepConstants.TIMEDQUIZ.toString());
			finish();
			startActivity(i);
		}
	}

	@Override
	public void onClick(View v) {
		String clickedAnswer = ((Button) v).getText().toString();
		TextView displayResult = (TextView) findViewById(R.id.question_result);
		if(clickedAnswer.equals(key)){
			displayResult.setText("Correct");
			points++;
			numQs++;
			runQuiz();
		} else {
			displayResult.setText("Incorrect");
			numQs++;
			runQuiz();
		}
	}
	
	@Override
	protected void onPause() {
		myCountDownTimer.cancel();
		finish();
		super.onPause();
	}
	
	public class MyCountDownTimer extends CountDownTimer {

		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onTick(long millisUntilFinished) {
			int progress = (int) (millisUntilFinished/100);
			progressBar.setProgress(progress);
		}

		@Override
		public void onFinish() {
			numQs++;
			this.cancel();
			Intent i = new Intent(TimedQuizActivity.this, QuizResultsActivity.class);
			i.putExtra("points", points);
			i.putExtra(WhichPrepConstants.QUIZTYPE.toString(), WhichPrepConstants.TIMEDQUIZ.toString());
			finish();
			startActivity(i);
		}
		
	}
}
