package com.dcu.redmonj9.whichprep.activities;

import com.dcu.redmonj9.whichprep.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FrontMenuActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.screen_transition_in, R.anim.screen_transition_out);
		setContentView(R.layout.activity_front_menu);
		Button button1 = (Button) findViewById(R.id.quiz_button);
		Button button2 = (Button) findViewById(R.id.timed_quiz_button);
		Button button3 = (Button) findViewById(R.id.casual_quiz_button);
		Button button4 = (Button) findViewById(R.id.statistics_button);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		if(v.getId()==R.id.quiz_button)
			i = new Intent(this, QuizActivity.class);
		else if(v.getId()==R.id.timed_quiz_button)
			i = new Intent(this, TimedQuizActivity.class);
		else if(v.getId()==R.id.casual_quiz_button)
			i = new Intent(this, CasualQuizActivity.class);
		else if(v.getId()==R.id.statistics_button)
			i = new Intent(this, StatisticsActivity.class);
		startActivity(i);
	}
}