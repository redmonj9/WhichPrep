package com.dcu.redmonj9.whichprep.activities;

import com.dcu.redmonj9.whichprep.R;
import com.dcu.redmonj9.whichprep.util.ClearDataDialog;
import com.dcu.redmonj9.whichprep.util.WhichPrepConstants;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StatisticsActivity extends FragmentActivity implements OnClickListener{

	public static final String PREFS_NAME = "MyPrefsFile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.screen_transition_in, R.anim.screen_transition_out);
		setContentView(R.layout.activity_statistics);
		TextView standardBest = (TextView) findViewById(R.id.standard_best);
		TextView standardPrev = (TextView) findViewById(R.id.standard_prev);
		TextView timedBest = (TextView) findViewById(R.id.timed_best);
		TextView timedPrev = (TextView) findViewById(R.id.timed_prev);
		TextView casualBest = (TextView) findViewById(R.id.casual_best);
		TextView casualPrev = (TextView) findViewById(R.id.casual_prev);
		Button clearData = (Button) findViewById(R.id.clear_data_button);
		clearData.setOnClickListener(this);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		
		standardBest.setText("Standard Best: "+settings.getInt("best"+WhichPrepConstants.NORMALQUIZ.toString(), 0));
		standardPrev.setText("Standard Prev: "+settings.getInt("previous"+WhichPrepConstants.NORMALQUIZ.toString(), 0));
		timedBest.setText("Timed Best: "+settings.getInt("best"+WhichPrepConstants.TIMEDQUIZ.toString(), 0));
		timedPrev.setText("Timed Prev: "+settings.getInt("previous"+WhichPrepConstants.TIMEDQUIZ.toString(), 0));
		casualBest.setText("Casual Best: "+settings.getInt("best"+WhichPrepConstants.CASUALQUIZ.toString(), 0));
		casualPrev.setText("Casual Prev: "+settings.getInt("previous"+WhichPrepConstants.CASUALQUIZ.toString(), 0));
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.clear_data_button){
			ClearDataDialog dialog = new ClearDataDialog(getSharedPreferences(PREFS_NAME, 0));
			dialog.show(getSupportFragmentManager(), "AlertDialogFragment");
		}
	}
}
