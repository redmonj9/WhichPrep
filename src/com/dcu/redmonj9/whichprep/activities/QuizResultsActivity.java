package com.dcu.redmonj9.whichprep.activities;

import java.util.ArrayList;

import com.dcu.redmonj9.whichprep.R;
import com.dcu.redmonj9.whichprep.util.WhichPrepConstants;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class QuizResultsActivity extends Activity implements OnClickListener {

	private static String quizType;
	private SharedPreferences settings;
	public static final String PREFS_NAME = "MyPrefsFile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.screen_transition_in, R.anim.screen_transition_out);
		Bundle bundle = getIntent().getExtras();
		setContentView(R.layout.activity_quiz_results);
		TextView tv = (TextView) findViewById(R.id.results_points_view);
		quizType = bundle.getString(WhichPrepConstants.QUIZTYPE.toString());
		int score = bundle.getInt(WhichPrepConstants.POINTS.toString());
		ArrayList<String> incorrectQuestions = bundle.getStringArrayList(WhichPrepConstants.INCORRECTQUESTIONS.toString());
		if(quizType.equals(WhichPrepConstants.NORMALQUIZ.toString())){
			tv.setText("Congratulations, you got " + score + " points.");
		} else if(quizType.equals(WhichPrepConstants.TIMEDQUIZ.toString())){
			tv.setText("Congratulations, you got " + score + " points in 30 seconds.");
		} else if(quizType.equals(WhichPrepConstants.CASUALQUIZ.toString())){
			if(score == 0){
				tv.setText("Sorry, you got no points.");
			}else if(score == 1){
				tv.setText("Sorry, you only got 1 point.");
			}else if(score > 0 && score < 10){
				tv.setText("Congratulations, you got " + score + " out of 10 points.");
			}else if(score == 10){
				tv.setText("Congratulations, you got a perfect " + score + " out of 10 points.");
			}
		}
		Button button1 = (Button) findViewById(R.id.return_to_menu_button);
		Button button2 = (Button) findViewById(R.id.retry_button);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		
		ListView wrongQuestions = (ListView) findViewById(R.id.wrong_questions);
		wrongQuestions.setAdapter(new ArrayAdapter<String>(this, R.layout.incorrect_questions_list, incorrectQuestions));
		
		TextView feedback = (TextView) findViewById(R.id.feedback);
		
		settings = getSharedPreferences(PREFS_NAME, 0);
		int previousScore = settings.getInt("previous"+quizType, 0);
		int bestScore = settings.getInt("best"+quizType, 0);
		
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("previous"+quizType, score);
		if(score > bestScore && bestScore != 0){
			feedback.setText("You also beat your previous high score of "+bestScore+" points");
			editor.putInt("best"+quizType, score);
		}else if(score > previousScore && previousScore != 0){
			feedback.setText("You also beat your last attempt by "+(score-previousScore)+" points");
		}else if(score < previousScore){
			feedback.setText("but, in your last attempt you got "+previousScore+" points");
		}
		
		editor.commit();
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		if(v.getId()==R.id.return_to_menu_button)
			i = new Intent(this, FrontMenuActivity.class);
		else if(v.getId()==R.id.retry_button){
			if(quizType.equals(WhichPrepConstants.NORMALQUIZ.toString()))
				i = new Intent(this, QuizActivity.class);
			else if(quizType.equals(WhichPrepConstants.TIMEDQUIZ.toString()))
				i = new Intent(this, TimedQuizActivity.class);
			else if(quizType.equals(WhichPrepConstants.CASUALQUIZ.toString()))
				i = new Intent(this, CasualQuizActivity.class);
		}
		finish();
		startActivity(i);
	}
}
