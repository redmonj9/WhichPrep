package com.dcu.redmonj9.whichprep;

import com.dcu.redmonj9.whichprep.R;
import com.dcu.redmonj9.whichprep.util.WhichPrepConstants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class QuizResultsActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		setContentView(R.layout.activity_quiz_results);
		TextView tv = (TextView) findViewById(R.id.results_points_view);
		String quizType = bundle.getString(WhichPrepConstants.QUIZTYPE.toString());
		int score = bundle.getInt("points");
		if(quizType.equals(WhichPrepConstants.NORMALQUIZ.toString())){
			if(score == 0){
				tv.setText("Sorry, you got no points.");
			}else if(score == 1){
				tv.setText("Sorry, you only got 1 point.");
			}else if(score > 0 && score < 10){
				tv.setText("Congratulations, you got " + score + " out of 10 points.");
			}else if(score == 10){
				tv.setText("Congratulations, you got a perfect " + score + " out of 10 points.");
			}
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
		button1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		if(v.getId()==R.id.return_to_menu_button)
			i = new Intent(this, FrontMenuActivity.class);
		finish();
		startActivity(i);
	}
}
