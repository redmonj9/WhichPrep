package com.dcu.redmonj9.whichprep;

import com.example.whichprep.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FrontMenuActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_front_menu);
		Button button1 = (Button) findViewById(R.id.quiz_button);
		button1.setOnClickListener(this);
		Button button2 = (Button) findViewById(R.id.timer_button);
		button2.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.front_menu, menu);
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
		String buttonText = ((Button) v).getText().toString();
		Intent i = new Intent();
		if(buttonText.equals("Quiz"))
			i = new Intent(this, QuizActivity.class);
		else if(buttonText.equals("Timer Task"))
			i = new Intent(this, AndroidTimerTaskExample.class);
		startActivity(i);
	}
}
