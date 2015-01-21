package com.dcu.redmonj9.whichprep;

import com.example.whichprep.R;

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
		setContentView(R.layout.activity_front_menu);
		Button button1 = (Button) findViewById(R.id.quiz_button);
		Button button2 = (Button) findViewById(R.id.timed_quiz_button);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String buttonText = ((Button) v).getText().toString();
		Intent i = new Intent();
		if(v.getId()==R.id.quiz_button)
			i = new Intent(this, QuizActivity.class);
		else if(v.getId()==R.id.timed_quiz_button)
			i = new Intent(this, TimedQuizActivity.class);
		startActivity(i);
		
		
		/*
		 * if(v.getId()==R.id.quiz_button)
		 * 	   i = new Intent(this, QuizActivity.class);
		 * else if(v.getId()==R.id.timed_quiz_button)
		 * 	   i = new Intent(this, TimedQuizActivity.class);
		 */
	}
}
