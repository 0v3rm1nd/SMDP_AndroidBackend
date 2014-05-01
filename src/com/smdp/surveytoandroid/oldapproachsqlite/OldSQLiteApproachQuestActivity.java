//package com.smdp.surveytoandroid.oldapproachsqlite;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//
//import com.smdp.surveytoandroid.library.DatabaseHandler;
//
//public class OldSQLiteApproachQuestActivity extends Activity {
//
////	// keep track of the current questions
////	int multiQid = 0;
////	int openQid = 0;
////	TextView txtQuestion;
////	Button butNext;
////	EditText openOther;
////	// will store all the answers
////	HashMap<String, String> answers = new HashMap<String, String>();
////	// initialize a list to hold cursor result for the questions
////	List<String> quest = new ArrayList<String>();
////	// initialize a list to hold the cursor results for the choices
////	List<String> choices = new ArrayList<String>();
////
////	protected void onCreate(Bundle savedInstanceState) {
////		super.onCreate(savedInstanceState);
////		setContentView(R.layout.activity_question);
////		txtQuestion = (TextView) findViewById(R.id.textView1);
////		butNext = (Button) findViewById(R.id.nextQuestion);
////		openOther = (EditText) findViewById(R.id.editText1);
////		
////		
////		// Load the data
////		setOpenQuestions();
////		setMultiQuestions();
////	}
////
////	// Load the multiquest data from sqlite db
////	private void setMultiQuestions() {
////		//set the open edit text visibility to gone
////		openOther.setVisibility(View.GONE);
////		Cursor cursorMulti;
////		Cursor cursorChoices;
////		DatabaseHandler db = new DatabaseHandler(this);
////		// Get the cursor containing our data
////		cursorMulti = db.getMultiQuestions();
////		if (cursorMulti.moveToFirst()) {
////
////			do {
////				quest.add(cursorMulti.getString(cursorMulti
////						.getColumnIndex("question")));
////
////			} while (cursorMulti.moveToNext());
////
////		} else {
////			Log.e("Error Database", "Empty MultiQuestion table");
////		}
////		txtQuestion.setText(quest.get(multiQid).toString());
////		multiQid++;
////
////		// Get the returned cursor from the slite db depending on the question
////		// id
////		cursorChoices = db.getMultiChoices("q" + multiQid);
////		choices.clear();
////
////		// Loop through the cursor data and assign its values to a string
////		// variable
////		if (cursorChoices.moveToFirst()) {
////
////			do {
////				choices.add(cursorChoices.getString(cursorChoices
////						.getColumnIndex("description")));
////
////			} while (cursorChoices.moveToNext());
////
////		} else {
////			Log.e("Error Database", "Empty choices table");
////		}
////
////		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
////		// clears the previously added radio buttons
////		radiogroup.clearCheck();
////		radiogroup.removeAllViews();
////		for (int i = 0; i < choices.size(); i++) {
////			RadioButton rdbtn = new RadioButton(this);
////			rdbtn.setId(i);
////			rdbtn.setText(choices.get(i).toString());
////			radiogroup.addView(rdbtn);
////
////		}
////		butNext.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View v) {
////				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
////				RadioButton answer = (RadioButton) findViewById(grp
////						.getCheckedRadioButtonId());
////				Log.e("Answer", " " + answer.getText());
////
////				// add the answer to the array list
////				answers.put(txtQuestion.getText().toString(), answer.getText()
////						.toString());
////
////				if (multiQid < 2) {
////					setMultiQuestions();
////				} else {
////					Intent intent = new Intent(QuestionActivity.this,
////							ResultActivity.class);
////					intent.putExtra("answers", answers); // Put your answer to
////															// your next Intent
////					startActivity(intent);
////					finish();
////				}
////			}
////
////		});
////
////	}
////
////	// Load the openquest data from sqlite db
////	private void setOpenQuestions() {
////		openOther.setVisibility(View.VISIBLE);
////		Cursor cursorOpen;
////		DatabaseHandler db = new DatabaseHandler(this);
////		// Get the cursor containing our data
////		cursorOpen = db.getOpenQuestions();
////
////		// currentQ = multiquest.get(qid);
////		if (cursorOpen.moveToFirst()) {
////
////			do {
////				quest.add(cursorOpen.getString(cursorOpen
////						.getColumnIndex("question")));
////
////			} while (cursorOpen.moveToNext());
////
////		} else {
////			Log.e("Error Database", "Empty OpenQuestion table");
////		}
////		txtQuestion.setText(quest.get(openQid).toString());
////		openQid++;
////
////		butNext.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View v) {
////				Log.e("Answer", " " + openOther.getText());
////
////				// add the answer to the array list
////				answers.put(txtQuestion.getText().toString(), openOther
////						.getText().toString());
////
////				if (openQid < 1) {
////					setOpenQuestions();
////				} else {
////					Intent intent = new Intent(QuestionActivity.this,
////							ResultActivity.class);
////					intent.putExtra("answers", answers); // Put your answer to
////															// your next Intent
////					startActivity(intent);
////					finish();
////				}
////			}
////
////		});
////
////	}
//}