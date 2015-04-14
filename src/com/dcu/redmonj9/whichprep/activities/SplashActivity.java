package com.dcu.redmonj9.whichprep.activities;

import com.dcu.redmonj9.whichprep.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	private static final int TIME = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(SplashActivity.this, FrontMenuActivity.class);
				startActivity(i);
				finish();
			}
		}, TIME);
	}
}
