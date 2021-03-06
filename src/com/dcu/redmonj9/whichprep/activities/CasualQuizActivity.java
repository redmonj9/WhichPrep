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

@SuppressWarnings({"deprecation", "rawtypes"})
public class CasualQuizActivity extends Activity implements OnClickListener{

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
	private String[] mDrawerMenuItems;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
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
		progressBar.setVisibility(View.INVISIBLE);
		option1 = (Button) findViewById(R.id.button1);
		option2 = (Button) findViewById(R.id.button2);
		option3 = (Button) findViewById(R.id.button3);
		option4 = (Button) findViewById(R.id.button4);
		option1.setOnClickListener(this);
		option2.setOnClickListener(this);
		option3.setOnClickListener(this);
		option4.setOnClickListener(this);

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
		progressBar.setProgress(300);
		progressBar.setMax(300);
		
		
		runQuiz();
	}
	
	public void runQuiz(){
		pointsField.setText(scoreText+""+points);
		if(numQs <= 10){
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
			i.putExtra(WhichPrepConstants.QUIZTYPE.constant, WhichPrepConstants.CASUALQUIZ.constant);
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
			points++;
			numQs++;
			runQuiz();
		} else {
			displayResult.setText("Incorrect");
			incorrectQuestions.add("- "+question.getOriginalSentence());
			numQs++;
			runQuiz();
		}
	}
	
	@Override
	protected void onPause() {
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
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
	    	Intent i = new Intent(CasualQuizActivity.this, FrontMenuActivity.class);
	    	finish();
	    	startActivity(i);
	    }
	}
}
