package com.dcu.redmonj9.whichprep;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.example.whichprep.R;
import com.example.whichprep.R.id;
import com.example.whichprep.R.layout;
import com.example.whichprep.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class AndroidTimerTaskExample extends Activity {

	Timer timer;
	TimerTask timerTask;
	// we are going to use a handler to be able to run in our TimerTask
	final Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// onResume we start our timer so it can start when the app comes from
		// the background
		startTimer();
	}

	public void startTimer() {
		// set a new Timer
		timer = new Timer();
		// initialize the TimerTask's job
		initializeTimerTask();
		// schedule the timer, after the first 5000ms the TimerTask will run
		// every 10000ms
		timer.schedule(timerTask, 10000, 10000); //
	}

	public void stoptimertask(View v) {
		// stop the timer, if it's not already null
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	public void initializeTimerTask() {
		timerTask = new TimerTask() {
			public void run() {
				// use a handler to run a toast that shows the current timestamp
				handler.post(new Runnable() {
					public void run() {
						// get the current timeStamp
						final String strDate = "Time's up";
						// show the toast
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(getApplicationContext(),strDate, duration);
						toast.show();
					}
				});
			}
		};
	}
}