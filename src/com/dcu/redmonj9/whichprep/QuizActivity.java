package com.dcu.redmonj9.whichprep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import com.dcu.redmonj9.whichprep.prepositions.Dictionary;
import com.dcu.redmonj9.whichprep.prepositions.PrepScrubber;
import com.example.whichprep.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends Activity implements OnClickListener{

	private String key = "";
	private int points = 0;
	private TextView questionField;
	private TextView pointsField;
	private Button option1;
	private Button option2;
	private Button option3;
	private Button option4;
	private Stack<String> questions;
	private Stack<String> answersOptions;
	private ArrayList<String> prepositions;
	private final String scoreText = "Your Score: ";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		questionField = (TextView) findViewById(R.id.question_field);
		pointsField = (TextView) findViewById(R.id.points_field);
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
		runQuiz();
	}
	
	public void runQuiz(){
		pointsField.setText(scoreText+""+points);
		if(!questions.empty()){
			String question = questions.pop();
			String prep = PrepScrubber.containsPrep(question, Dictionary.getPrepositions());
			questionField.setText(PrepScrubber.removePrep(question, PrepScrubber.containsPrep(question, Dictionary.getPrepositions())));
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
			questionField.setText("Quiz Complete");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String clickedAnswer = ((Button) v).getText().toString();
		TextView displayResult = (TextView) findViewById(R.id.question_result);
		if(clickedAnswer.equals(key)){
			displayResult.setText("Correct!!! :D");
			points++;
			runQuiz();
		} else {
			displayResult.setText("Wrongo!!! :(");
			runQuiz();
		}
	}
}
