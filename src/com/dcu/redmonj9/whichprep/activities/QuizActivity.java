package com.dcu.redmonj9.whichprep.activities;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import com.dcu.redmonj9.whichprep.prepositions.Dictionary;
import com.dcu.redmonj9.whichprep.util.Quiz;
import com.dcu.redmonj9.whichprep.util.QuizItem;
import com.dcu.redmonj9.whichprep.util.WhichPrepConstants;
import com.dcu.redmonj9.whichprep.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class QuizActivity extends Activity implements OnClickListener, AnimationListener{

	private int points = 0;
	private int numQs = 1;
	private TextView questionField;
	private TextView pointsField;
	private Button option1;
	private Button option2;
	private Button option3;
	private Button option4;
	private ProgressBar progressBar;
	private Quiz quiz;
	private QuizItem question;
	private Stack<String> answersOptions;
	private ArrayList<String> prepositions;
	private ArrayList<String> incorrectQuestions =  new ArrayList<String>();
	private final String scoreText = "Your Score: ";
	private MyCountDownTimer myCountDownTimer;
	private String[] mDrawerMenuItems;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private Animation animFadeIn;
	private Animation pointsEarnedFadeIn;
	private Animation pointsEarnedFadeOut;
	private TextView pointsEarned;
	
	@Override
 	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.screen_transition_in, R.anim.screen_transition_out);
		setContentView(R.layout.activity_quiz);
		mDrawerMenuItems = getResources().getStringArray(R.array.drawer_list);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerMenuItems));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  
                mDrawerLayout,         
                R.drawable.ic_drawer,  
                R.string.drawer_open,  
                R.string.drawer_close  
                ) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
		
		questionField = (TextView) findViewById(R.id.question_field);
		pointsField = (TextView) findViewById(R.id.points_field);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		pointsEarned = (TextView) findViewById(R.id.plus_points);
		option1 = (Button) findViewById(R.id.button1);
		option2 = (Button) findViewById(R.id.button2);
		option3 = (Button) findViewById(R.id.button3);
		option4 = (Button) findViewById(R.id.button4);
		
		animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
		pointsEarnedFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.points_flash_in);
		pointsEarnedFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.points_flash_out);
		animFadeIn.setAnimationListener(this);
		pointsEarnedFadeIn.setAnimationListener(this);
		pointsEarnedFadeOut.setAnimationListener(this);

		AssetManager assetManager = getAssets();
		InputStream inputStream;
		int fileNo = new Random().nextInt(9);
		try {
			inputStream = assetManager.open("output"+fileNo+".txt");
			quiz = new Quiz(Dictionary.getDictionary(inputStream));
		} catch (IOException e) {
			e.printStackTrace();
		}
		answersOptions = new Stack<String>();
		runQuiz();
	}
	
	public void runQuiz(){
		pointsField.setText(scoreText+""+points);
		if(numQs <= 10){
			option1.setOnClickListener(null);
			option2.setOnClickListener(null);
			option3.setOnClickListener(null);
			option4.setOnClickListener(null);
			progressBar.setProgress(100);
			myCountDownTimer = new MyCountDownTimer(10000, 25);
			option1.startAnimation(animFadeIn);
			option2.startAnimation(animFadeIn);
			option3.startAnimation(animFadeIn);
			option4.startAnimation(animFadeIn);
			question = quiz.getQuestion();
			String prep = question.getKey();
			questionField.setText(numQs + ". " + question.getEditedSentence());
			prepositions = Dictionary.getPrepositions();
			prepositions.remove(prepositions.indexOf(prep));
			answersOptions.push(prep);
			Collections.shuffle(prepositions);
			answersOptions.push(prepositions.get(0));
			answersOptions.push(prepositions.get(1));
			answersOptions.push(prepositions.get(2));
			Collections.shuffle(answersOptions);
			
			option1.setText(answersOptions.pop());
			option2.setText(answersOptions.pop());
			option3.setText(answersOptions.pop());
			option4.setText(answersOptions.pop());
		} else {
			Intent i = new Intent(this, QuizResultsActivity.class);
			i.putExtra(WhichPrepConstants.POINTS.constant, points);
			i.putStringArrayListExtra(WhichPrepConstants.INCORRECTQUESTIONS.constant, incorrectQuestions);
			i.putExtra(WhichPrepConstants.QUIZTYPE.constant, WhichPrepConstants.NORMALQUIZ.constant);
			finish();
			startActivity(i);
		}
	}

	@Override
	public void onClick(View v) {

		String clickedAnswer = ((Button) v).getText().toString();
		TextView displayResult = (TextView) findViewById(R.id.question_result);
		
		if(clickedAnswer.equals(question.getKey())){
			displayResult.setText("Correct");
			points+=(progressBar.getProgress()/5);
			pointsEarned.setText("  +" + progressBar.getProgress()/5);
			pointsEarned.startAnimation(pointsEarnedFadeIn);
		} else {
			displayResult.setText("Incorrect");
			incorrectQuestions.add("- "+question.getOriginalSentence());
		}
		numQs++;
		myCountDownTimer.cancel();
		runQuiz();
	}
	
	@Override
	protected void onPause() {
		myCountDownTimer.cancel();
		finish();
		super.onPause();
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	public class MyCountDownTimer extends CountDownTimer {

		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			int progress = (int) (millisUntilFinished/100);
			progressBar.setProgress(progress);
		}

		@Override
		public void onFinish() {
			numQs++;
			TextView displayResult = (TextView) findViewById(R.id.question_result);
			displayResult.setText("You ran out of time");
			incorrectQuestions.add("- "+question.getOriginalSentence());
			this.cancel();
			runQuiz();
		}
		
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    	if(mDrawerMenuItems[position].equals("Cancel")){
	    		Intent i = new Intent(QuizActivity.this, FrontMenuActivity.class);
	    		finish();
	    		startActivity(i);
	    	}
	    }
	}

	@Override
	public void onAnimationStart(Animation animation) {}

	@Override
	public void onAnimationEnd(Animation animation) {
		if(animation==animFadeIn){
			option1.setOnClickListener(QuizActivity.this);
			option2.setOnClickListener(QuizActivity.this);
			option3.setOnClickListener(QuizActivity.this);
			option4.setOnClickListener(QuizActivity.this);
			myCountDownTimer.start();
		} else if(animation==pointsEarnedFadeIn){
			pointsEarned.startAnimation(pointsEarnedFadeOut);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {}
}
