package com.dcu.redmonj9.whichprep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import com.dcu.redmonj9.whichprep.prepositions.Dictionary;
import com.dcu.redmonj9.whichprep.prepositions.PrepScrubber;
import com.dcu.redmonj9.whichprep.util.WhichPrepConstants;
import com.dcu.redmonj9.whichprep.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class QuizActivity extends Activity implements OnClickListener{

	private String key = "";
	private int points = 0;
	private int numQs = 1;
	private TextView questionField;
	private TextView pointsField;
	private Button option1;
	private Button option2;
	private Button option3;
	private Button option4;
	private ProgressBar progressBar;
	private Stack<String> questions;
	private Stack<String> answersOptions;
	private ArrayList<String> prepositions;
	private final String scoreText = "Your Score: ";
	private MyCountDownTimer myCountDownTimer;
	private DelayCountDownTimer delay;
	private String[] mDrawerMenuItems;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
	@Override
 	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		mDrawerMenuItems = getResources().getStringArray(R.array.drawer_list);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerMenuItems));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
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
		option1 = (Button) findViewById(R.id.button1);
		option2 = (Button) findViewById(R.id.button2);
		option3 = (Button) findViewById(R.id.button3);
		option4 = (Button) findViewById(R.id.button4);
		
		questions = new Stack<String>();
		answersOptions = new Stack<String>();
		questions.addAll(Dictionary.getDictionary());
		Collections.shuffle(questions, new Random(System.nanoTime()));
		runQuiz();
	}
	
	public void runQuiz(){
		pointsField.setText(scoreText+""+points);
		if(numQs <= 10){
			option1.setAlpha(0);
			option2.setAlpha(0);
			option3.setAlpha(0);
			option4.setAlpha(0);
			option1.setOnClickListener(null);
			option2.setOnClickListener(null);
			option3.setOnClickListener(null);
			option4.setOnClickListener(null);
			progressBar.setProgress(100);
			myCountDownTimer = new MyCountDownTimer(10000, 50);
			delay = new DelayCountDownTimer(3000, 3000);
			delay.start();
			String question = questions.pop();
			String prep = PrepScrubber.containsPrep(question, Dictionary.getPrepositions());
			questionField.setText(numQs + ". " + PrepScrubber.removePrep(question, PrepScrubber.containsPrep(question, Dictionary.getPrepositions())));
			prepositions = Dictionary.getPrepositions();
			prepositions.remove(prepositions.indexOf(prep));
			answersOptions.push(prep);
			key = prep;
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
			i.putExtra("points", points);
			i.putExtra(WhichPrepConstants.QUIZTYPE.toString(), WhichPrepConstants.NORMALQUIZ.toString());
			finish();
			startActivity(i);
		}
	}

	@Override
	public void onClick(View v) {
		String clickedAnswer = ((Button) v).getText().toString();
		TextView displayResult = (TextView) findViewById(R.id.question_result);
		myCountDownTimer.cancel();
		delay.cancel();
		if(clickedAnswer.equals(key)){
			displayResult.setText("Correct");
			points++;
			numQs++;
			runQuiz();
		} else {
			displayResult.setText("Incorrect");
			numQs++;
			runQuiz();
		}
	}
	
	@Override
	protected void onPause() {
		myCountDownTimer.cancel();
		delay.cancel();
		finish();
		super.onPause();
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

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
			this.cancel();
			runQuiz();
		}
		
	}
	
	public class DelayCountDownTimer extends CountDownTimer{

		public DelayCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {}

		@Override
		public void onFinish() {
			option1.setAlpha(1);
			option2.setAlpha(1);
			option3.setAlpha(1);
			option4.setAlpha(1);
			option1.setOnClickListener(QuizActivity.this);
			option2.setOnClickListener(QuizActivity.this);
			option3.setOnClickListener(QuizActivity.this);
			option4.setOnClickListener(QuizActivity.this);
			this.cancel();
			myCountDownTimer.start();
		}
		
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
	    	Intent i = new Intent(QuizActivity.this, FrontMenuActivity.class);
	    	finish();
	    	startActivity(i);
	    }
	}
}
