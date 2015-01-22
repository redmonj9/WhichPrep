package com.dcu.redmonj9.whichprep;

import com.dcu.redmonj9.whichprep.R;
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
		tv.setText("Congratulations, you got " + bundle.getInt("points") + " points");
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
