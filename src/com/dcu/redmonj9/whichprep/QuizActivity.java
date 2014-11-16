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
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		TextView tv = (TextView) findViewById(R.id.textView1);
		Dictionary dict = new Dictionary();
		Stack<String> questions = new Stack<String>();
		questions.addAll(dict.getDictionary());
		Collections.shuffle(questions, new Random(System.nanoTime()));
		PrepScrubber ps = new PrepScrubber();
		//select a random sentence from the dictionary
		String sentence = questions.pop();
		String prep = ps.containsPrep(sentence, dict.getPrepositions());
		tv.setText(ps.removePrep(sentence, ps.containsPrep(sentence, dict.getPrepositions())));
		Stack<String> answers = new Stack<String>();
		ArrayList<String> preps = dict.getPrepositions();
		preps.remove(preps.indexOf(prep));
		answers.push(prep);
		Collections.shuffle(preps);
		answers.push(preps.get(0));
		answers.push(preps.get(1));
		answers.push(preps.get(2));
		Collections.shuffle(answers);
		
		Button button = (Button) findViewById(R.id.button1);
		button.setText(answers.pop());
		button = (Button) findViewById(R.id.button2);
		button.setText(answers.pop());
		button = (Button) findViewById(R.id.button3);
		button.setText(answers.pop());
		button = (Button) findViewById(R.id.button4);
		button.setText(answers.pop());
				
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
}
