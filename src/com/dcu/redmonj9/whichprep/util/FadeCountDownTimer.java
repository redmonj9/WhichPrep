package com.dcu.redmonj9.whichprep.util;

import android.os.CountDownTimer;
import android.view.View;

public class FadeCountDownTimer extends CountDownTimer {

	View view;
	
	public FadeCountDownTimer(long millisInFuture, long countDownInterval, View view) {
		super(millisInFuture, countDownInterval);
		this.view = view;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		
	}

	@Override
	public void onFinish() {
		
	}

}
