package com.smdp.surveytoandroid;

import java.util.ArrayList;
import java.util.HashMap;


import com.smdp.surveytoandroid.questionstructure.Choice;
import com.smdp.surveytoandroid.questionstructure.DichotomousQuestion;
import com.smdp.surveytoandroid.questionstructure.Fork;
import com.smdp.surveytoandroid.questionstructure.MultipleChoiceQuestion;
import com.smdp.surveytoandroid.questionstructure.OpenQuestion;
import com.smdp.surveytoandroid.questionstructure.Question;
import com.smdp.surveytoandroid.questionstructure.Questionable;
import com.smdp.surveytoandroid.questionstructure.RankingQuestion;
import com.smdp.surveytoandroid.questionstructure.RatingQuestion;
import com.smdp.surveytoandroid.questionstructure.StapleQuestion;
import com.smdp.surveytoandroid.questionstructure.SumConstantQuestion;
import com.smdp.surveytoandroid.questionstructure.Survey;

public class QuestActivity {

	public static void main(String[] args) {
		// create survey
		Survey s1 = new Survey("Survey1");

		// create multiplechoice 1

		Choice choice1 = new Choice("choice1", "Superman");
		Choice choice2 = new Choice("choice2", "Batman");
		Choice choice3 = new Choice("choice3", "Spiderman");
		Choice choice4 = new Choice("choice4", "Flash");

		ArrayList<Choice> arraychoice1 = new ArrayList<Choice>();
		arraychoice1.add(choice1);
		arraychoice1.add(choice2);
		arraychoice1.add(choice3);
		arraychoice1.add(choice4);

		MultipleChoiceQuestion m1 = new MultipleChoiceQuestion("q1",
				"What is your favorite super hero?", true, "testOther1",
				arraychoice1);

		// create multiplechoice 2
		Choice choice5 = new Choice("choice5", "Thor");
		Choice choice6 = new Choice("choice6", "Hulk");
		Choice choice7 = new Choice("choice7", "Dr. Manhatan");
		Choice choice8 = new Choice("choice8", "Green Lantern");

		ArrayList<Choice> arraychoice2 = new ArrayList<Choice>();
		arraychoice2.add(choice5);
		arraychoice2.add(choice6);
		arraychoice2.add(choice7);
		arraychoice2.add(choice8);
		MultipleChoiceQuestion m2 = new MultipleChoiceQuestion("q2",
				"What is your least favorite super hero?", true, "testOther2",
				arraychoice2);

		// create ranking question 1
		Choice choice9 = new Choice("choice9", "Thing one");
		Choice choice10 = new Choice("choice10", "Thing two");
		Choice choice11 = new Choice("choice11", "Thing three");
		Choice choice12 = new Choice("choice12", "Thing four");

		ArrayList<Choice> arraychoice3 = new ArrayList<Choice>();
		arraychoice3.add(choice9);
		arraychoice3.add(choice10);
		arraychoice3.add(choice11);
		arraychoice3.add(choice12);
		RankingQuestion r1 = new RankingQuestion("q3",
				"You can choose from these", true, arraychoice3);

		// create ranking question 2
		Choice choice13 = new Choice("choice13", "Thing five");
		Choice choice14 = new Choice("choice14", "Thing six");
		Choice choice15 = new Choice("choice15", "Thing seven");
		Choice choice16 = new Choice("choice16", "Thing eight");

		ArrayList<Choice> arraychoice4 = new ArrayList<Choice>();
		arraychoice4.add(choice13);
		arraychoice4.add(choice14);
		arraychoice4.add(choice15);
		arraychoice4.add(choice16);
		RankingQuestion r2 = new RankingQuestion("q4",
				"You can choose from these other things", true, arraychoice4);

		// create an open question 1
		OpenQuestion o1 = new OpenQuestion("q5",
				"What do you think about Marvel?", false);

		// create an open question 2
		OpenQuestion o2 = new OpenQuestion("q9",
				"Waht do you think about DC Unviverse?", false);
		// create a dichotomous question 1
		DichotomousQuestion d1 = new DichotomousQuestion("q6",
				"Are you older than 20?", false);

		// create a dichotomous question 2
		DichotomousQuestion d2 = new DichotomousQuestion("q10",
				"Are you good at playing computer games?", false);

		// create sum constant question 1
		Choice choice17 = new Choice("choice17", "Algorithms");
		Choice choice18 = new Choice("choice18", "Design Patterns");
		Choice choice19 = new Choice("choice19", "Dicrete Math");
		Choice choice20 = new Choice("choice20", "SMDP");

		ArrayList<Choice> arraychoice5 = new ArrayList<Choice>();
		arraychoice5.add(choice17);
		arraychoice5.add(choice18);
		arraychoice5.add(choice19);
		arraychoice5.add(choice20);
		SumConstantQuestion sum1 = new SumConstantQuestion("q7",
				"Sum time spend on studying on different subjects", true, 100,
				arraychoice5);

		// create rating question 1
		RatingQuestion rate1 = new RatingQuestion("q8", "Rate marvel", false,
				1, 10, "aweful", "awesome");
		//forking
		Fork f1 = new Fork();
		m1.forks.add(f1);
		//make an array of questions to fork
		ArrayList<Question> ques1 = new ArrayList<Question>();
		//add the questions that should be forked to the array
		ques1.add(m2);
		//put into the fork hashmap the choice as key and the array list of questions as values


		ArrayList<Questionable> questions = new ArrayList<Questionable>();
		questions.add(m1);
		questions.add(m2);
		questions.add(r1);
		questions.add(r2);
		questions.add(o1);
		questions.add(o2);
		questions.add(d1);
		questions.add(d2);
		questions.add(sum1);
		questions.add(rate1);

		for (Questionable questionable : questions) {
			if (questionable instanceof MultipleChoiceQuestion) {
				System.out.println(((MultipleChoiceQuestion) questionable)
						.getName());
			} else if (questionable instanceof OpenQuestion) {
				System.out.println(((OpenQuestion) questionable).getName());
			} else if (questionable instanceof DichotomousQuestion) {
				System.out.println(((DichotomousQuestion) questionable)
						.getName());
			} else if (questionable instanceof RankingQuestion) {
				System.out.println(((RankingQuestion) questionable).getName());
			} else if (questionable instanceof RatingQuestion) {
				System.out.println(((RatingQuestion) questionable).getName());
			} else if (questionable instanceof SumConstantQuestion) {
				System.out.println(((SumConstantQuestion) questionable)
						.getName());
			} else if (questionable instanceof StapleQuestion) {
				System.out.println(((StapleQuestion) questionable).getName());
			}
		}
	}
}
/*
This is the old version of the app*/

//package com.smdp.surveytoandroid;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import com.smdp.surveytoandroid.questionstructure.Choice;
//import com.smdp.surveytoandroid.questionstructure.DichotomousQuestion;
//import com.smdp.surveytoandroid.questionstructure.MultipleChoiceQuestion;
//import com.smdp.surveytoandroid.questionstructure.OpenQuestion;
//import com.smdp.surveytoandroid.questionstructure.RankingQuestion;
//import com.smdp.surveytoandroid.questionstructure.RatingQuestion;
//import com.smdp.surveytoandroid.questionstructure.SumConstantQuestion;
//import com.smdp.surveytoandroid.questionstructure.Survey;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//
//public class QuestionActivity extends Activity {
//	// keep track of the current question
//	int quid = 0;
//
//	// views
//	TextView txtSurvey;
//	TextView txtQuestion;
//	TextView txtOther;
//	Button butNext;
//	EditText openOther;
//	RadioGroup radiogroup;
//	LinearLayout viewll;
//
//	// will store all the answers
//	HashMap<String, String> answers = new HashMap<String, String>();
//	// initialize an arraylist to hold the surveys if needed
//	ArrayList<Survey> survey = new ArrayList<Survey>();
//	// initialize an arraylist for every question type
//	ArrayList<MultipleChoiceQuestion> multiquest = new ArrayList<MultipleChoiceQuestion>();
//	ArrayList<RankingQuestion> rankquest = new ArrayList<RankingQuestion>();
//	ArrayList<DichotomousQuestion> dichotquest = new ArrayList<DichotomousQuestion>();
//	ArrayList<OpenQuestion> openquest = new ArrayList<OpenQuestion>();
//	ArrayList<SumConstantQuestion> sumconstquest = new ArrayList<SumConstantQuestion>();
//	ArrayList<RatingQuestion> ratequest = new ArrayList<RatingQuestion>();
//
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_question);
//		txtSurvey = (TextView) findViewById(R.id.textSurvey);
//		txtQuestion = (TextView) findViewById(R.id.textQuestion);
//		viewll = (LinearLayout) findViewById(R.id.viewLL);
//		txtOther = (TextView) findViewById(R.id.textOther);
//		butNext = (Button) findViewById(R.id.nextQuestion);
//		openOther = (EditText) findViewById(R.id.editTextOpenOther);
//		radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
//
//		// add the data into the array lists
//		addQuestionsToLists();
//		// hard code the survey title which must be changed
//		txtSurvey.setText(survey.get(0).getName());
//		// populate multi question data
//		populateMultiQuest();
//
//	}
//
//	private void populateSumConstQuest() {
//
//		txtQuestion.setText(sumconstquest.get(quid).getQuestion() + " up to "
//				+ sumconstquest.get(quid).getConstant());
//		// dynamically create radio buttons based on the choices array list for
//		// the question type
//		for (int i = 0; i < sumconstquest.get(quid).choices.size(); i++) {
//			RadioButton rdbtn = new RadioButton(this);
//			rdbtn.setId(i);
//			// set the text in an individual choices to the radio button
//			rdbtn.setText(multiquest.get(quid).choices.get(i).getDescription()
//					.toString());
//			// add the radio button view
//			radiogroup.addView(rdbtn);
//		}
//		butNext.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
//				RadioButton answer = (RadioButton) findViewById(grp
//						.getCheckedRadioButtonId());
//				Log.e("Answer", " " + answer.getText());
//
//				// add the answer to the array list
//				answers.put(txtQuestion.getText().toString(), answer.getText()
//						.toString());
//				// increment quid
//				quid++;
//				// if there are more sumconstant questions we get them via a
//				// recursive call
//				if (quid < sumconstquest.size()) {
//					radiogroup.clearCheck();
//					radiogroup.removeAllViews();
//					populateMultiQuest();
//				} else {
//					// else
//					radiogroup.clearCheck();
//					radiogroup.removeAllViews();
//					quid = 0;
//					Intent intent = new Intent(QuestionActivity.this,
//							ResultActivity.class);
//					intent.putExtra("answers", answers); // Put your answer to
//					// your next Intent
//					startActivity(intent);
//					finish();
//				}
//			}
//
//		});
//
//	}
//
//	// populate open questions
//	private void populateDichotQuest() {
//		txtQuestion.setText(dichotquest.get(quid).getQuestion());
//		// prepare yes no staple answers
//
//		// there are only 2 options yes and no so we can just hard code those
//		RadioButton rdbtnYes = new RadioButton(this);
//		RadioButton rdbtnNo = new RadioButton(this);
//		rdbtnYes.setId(1);
//		rdbtnNo.setId(2);
//		rdbtnYes.setText("Yes");
//		rdbtnNo.setText("No");
//		radiogroup.addView(rdbtnYes);
//		radiogroup.addView(rdbtnNo);
//		butNext.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
//				RadioButton answer = (RadioButton) findViewById(grp
//						.getCheckedRadioButtonId());
//				Log.e("Answer", " " + answer.getText());
//
//				// add the answer to the array list
//				answers.put(txtQuestion.getText().toString(), answer.getText()
//						.toString());
//				// increment quid
//				quid++;
//				// if there are more dichotomous question we get them via a
//				// recursive call
//				if (quid < dichotquest.size()) {
//					radiogroup.clearCheck();
//					radiogroup.removeAllViews();
//					populateDichotQuest();
//				} else {
//					// else we clear the radiogroup and call the
//					// populateSumConstQuest()
//					radiogroup.clearCheck();
//					radiogroup.removeAllViews();
//					quid = 0;
//					populateSumConstQuest();
//
//				}
//			}
//
//		});
//
//	}
//
//	// populate rank question
//	private void populateRankQuest() {
//
//		txtQuestion.setText(rankquest.get(quid).getQuestion());
//		// clears the previously added radio buttons
//		radiogroup.clearCheck();
//		radiogroup.removeAllViews();
//		// dynamically add choices as radio buttons depending on the choices
//		// array list for the specific ranking question
//		for (int i = 0; i < rankquest.get(quid).choices.size(); i++) {
//			RadioButton rdbtn = new RadioButton(this);
//			rdbtn.setId(i);
//			rdbtn.setText(rankquest.get(quid).choices.get(i).getDescription()
//					.toString());
//			radiogroup.addView(rdbtn);
//
//		}
//		butNext.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
//				RadioButton answer = (RadioButton) findViewById(grp
//						.getCheckedRadioButtonId());
//				Log.e("Answer", " " + answer.getText());
//
//				// add the answer to the array list
//				answers.put(txtQuestion.getText().toString(), answer.getText()
//						.toString());
//				// increment quid
//				quid++;
//				// if there are more ranking questions we make a recursive call
//				// to load them
//				if (quid < multiquest.size()) {
//					populateRankQuest();
//				} else {
//					// else we clear the radio group and we call the
//					// populateDichotQuest()
//					quid = 0;
//					radiogroup.clearCheck();
//					radiogroup.removeAllViews();
//					populateDichotQuest();
//				}
//			}
//
//		});
//
//	}
//
//	// populate open questions
//	private void populateOpenQuest() {
//		txtOther.setVisibility(View.GONE);
//		// we will only need the edit text field for this type of questions so
//		// we set it to visible
//		openOther.setVisibility(View.VISIBLE);
//		txtQuestion.setText(openquest.get(quid).getQuestion());
//		butNext.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (openOther.getText().toString().trim().length() > 0) {
//
//					Log.e("Answer", " " + openOther.getText());
//
//					// add the answer to the array list
//					answers.put(txtQuestion.getText().toString(), openOther
//							.getText().toString());
//					// increment quid
//					quid++;
//					// clear the used field
//					openOther.getText().clear();
//				} else {
//					Log.e("Answer problem", "Answer problem");
//				}
//				// if there are more openquestions in the array list we make a
//				// recursive call to load them
//				if (quid < openquest.size()) {
//					populateOpenQuest();
//				} else {
//					// else we call the populateRankQuest() which will populate
//					// the ranking questions
//					openOther.setVisibility(View.GONE);
//					quid = 0;
//					populateRankQuest();
//				}
//			}
//
//		});
//
//	}
//
//	// populate multi question
//	private void populateMultiQuest() {
//		// check if the other field is enabled and if not disable the
//		// visibility
//		if (multiquest.get(quid).isOther().equals("")) {
//			openOther.setVisibility(View.VISIBLE);
//		} else {
//			// set the visibility of the other field to GONE
//			txtOther.setVisibility(View.GONE);
//			openOther.setVisibility(View.GONE);
//		}
//
//		txtQuestion.setText(multiquest.get(quid).getQuestion());
//		// clears the previously added radio buttons
//		radiogroup.clearCheck();
//		radiogroup.removeAllViews();
//
//		// generate dynamically radio buttons based on the number of choices in
//		// the choices array list of the question
//		for (int i = 0; i < multiquest.get(quid).choices.size(); i++) {
//			RadioButton rdbtn = new RadioButton(this);
//			rdbtn.setId(i);
//			// set the text in an individual choices to the radio button
//			rdbtn.setText(multiquest.get(quid).choices.get(i).getDescription()
//					.toString());
//			// add the radio button view
//			radiogroup.addView(rdbtn);
//
//		}
//		butNext.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// if the other field is populated use it as an answer to this
//				// question
//				if (openOther.getText().toString().trim().length() > 0) {
//
//					Log.e("Answer", " " + openOther.getText());
//
//					// add the answer to the array list
//					answers.put(txtQuestion.getText().toString(), openOther
//							.getText().toString());
//					// increment quid
//					quid++;
//					// clear the used field in case it is used afterwards
//					openOther.getText().clear();
//				} else {
//					// if the other field is not specified get the answer from
//					// the currently selected radio button
//					RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
//					RadioButton answer = (RadioButton) findViewById(grp
//							.getCheckedRadioButtonId());
//					Log.e("Answer", " " + answer.getText());
//
//					// add the answer to the array list
//					answers.put(txtQuestion.getText().toString(), answer
//							.getText().toString());
//					// increment quid
//					quid++;
//				}
//				// if we have more multi questions in the array list we make a
//				// recursive call and load them
//
//				if (quid < multiquest.size()) {
//
//					radiogroup.clearCheck();
//					radiogroup.removeAllViews();
//					// recursive call
//					populateMultiQuest();
//				} else {
//					// else we clear the radio group, set the current quid to 0
//					// so that it can be used by the other question types
//					// and we call the populateOpenQuest() which will populate
//					// the open questions next
//					txtOther.setVisibility(View.GONE);
//					radiogroup.clearCheck();
//					radiogroup.removeAllViews();
//					quid = 0;
//					populateOpenQuest();
//				}
//			}
//
//		});
//
//	}
//
//	private void addQuestionsToLists() {
//
//		/**
//		 * DECLARE CODE GENERATED DATA HERE
//		 * */
//
//		// create survey
//		Survey s1 = new Survey("Survey1");
//
//		// create multiplechoice 1
//
//		Choice choice1 = new Choice("choice1", "Superman");
//		Choice choice2 = new Choice("choice2", "Batman");
//		Choice choice3 = new Choice("choice3", "Spiderman");
//		Choice choice4 = new Choice("choice4", "Flash");
//
//		ArrayList<Choice> arraychoice1 = new ArrayList<Choice>();
//		arraychoice1.add(choice1);
//		arraychoice1.add(choice2);
//		arraychoice1.add(choice3);
//		arraychoice1.add(choice4);
//
//		MultipleChoiceQuestion m1 = new MultipleChoiceQuestion("q1",
//				"What is your favorite super hero?", true, "testOther1",
//				arraychoice1);
//
//		// create multiplechoice 2
//		Choice choice5 = new Choice("choice5", "Thor");
//		Choice choice6 = new Choice("choice6", "Hulk");
//		Choice choice7 = new Choice("choice7", "Dr. Manhatan");
//		Choice choice8 = new Choice("choice8", "Green Lantern");
//
//		ArrayList<Choice> arraychoice2 = new ArrayList<Choice>();
//		arraychoice2.add(choice5);
//		arraychoice2.add(choice6);
//		arraychoice2.add(choice7);
//		arraychoice2.add(choice8);
//		MultipleChoiceQuestion m2 = new MultipleChoiceQuestion("q2",
//				"What is your least favorite super hero?", true, "testOther2",
//				arraychoice2);
//
//		// create ranking question 1
//		Choice choice9 = new Choice("choice9", "Thing one");
//		Choice choice10 = new Choice("choice10", "Thing two");
//		Choice choice11 = new Choice("choice11", "Thing three");
//		Choice choice12 = new Choice("choice12", "Thing four");
//
//		ArrayList<Choice> arraychoice3 = new ArrayList<Choice>();
//		arraychoice3.add(choice9);
//		arraychoice3.add(choice10);
//		arraychoice3.add(choice11);
//		arraychoice3.add(choice12);
//		RankingQuestion r1 = new RankingQuestion("q3",
//				"You can choose from these", true, arraychoice3);
//		
//		// create ranking question 2
//		Choice choice13 = new Choice("choice13", "Thing five");
//		Choice choice14 = new Choice("choice14", "Thing six");
//		Choice choice15 = new Choice("choice15", "Thing seven");
//		Choice choice16 = new Choice("choice16", "Thing eight");
//
//		ArrayList<Choice> arraychoice4 = new ArrayList<Choice>();
//		arraychoice4.add(choice13);
//		arraychoice4.add(choice14);
//		arraychoice4.add(choice15);
//		arraychoice4.add(choice16);
//		RankingQuestion r2 = new RankingQuestion("q4",
//				"You can choose from these other things", true, arraychoice4);
//
//		// create an open question 1
//		OpenQuestion o1 = new OpenQuestion("q5",
//				"What do you think about Marvel?", false);
//
//		// create an open question 2
//		OpenQuestion o2 = new OpenQuestion("q9",
//				"Waht do you think about DC Unviverse?", false);
//		// create a dichotomous question 1
//		DichotomousQuestion d1 = new DichotomousQuestion("q6",
//				"Are you older than 20?", false);
//
//		// create a dichotomous question 2
//		DichotomousQuestion d2 = new DichotomousQuestion("q10",
//				"Are you good at playing computer games?", false);
//
//		// create sum constant question 1
//		Choice choice17 = new Choice("choice17", "Algorithms");
//		Choice choice18 = new Choice("choice18", "Design Patterns");
//		Choice choice19 = new Choice("choice19", "Dicrete Math");
//		Choice choice20 = new Choice("choice20", "SMDP");
//
//		ArrayList<Choice> arraychoice5 = new ArrayList<Choice>();
//		arraychoice5.add(choice17);
//		arraychoice5.add(choice18);
//		arraychoice5.add(choice19);
//		arraychoice5.add(choice20);
//		SumConstantQuestion sum1 = new SumConstantQuestion("q7",
//				"Sum time spend on studying on different subjects", true, 100,
//				arraychoice5);
//
//		// create rating question 1
//		RatingQuestion rate1 = new RatingQuestion("q8", "Rate marvel", false,
//				1, 10, "aweful", "awesome");
//
//		/**
//		 * DECLARE CODE GENERATED DATA END
//		 * */
//		// add survery to array list of surveys
//		survey.add(s1);
//
//		// add data to individual question types to the respective arraylists
//		multiquest.add(m1);
//		multiquest.add(m2);
//		rankquest.add(r1);
//		rankquest.add(r2);
//		openquest.add(o1);
//		openquest.add(o2);
//		dichotquest.add(d1);
//		dichotquest.add(d2);
//		sumconstquest.add(sum1);
//		ratequest.add(rate1);
//	}
//	// quid = 0;
//	// Intent intent = new Intent(QuestionActivity.this,
//	// ResultActivity.class);
//	// intent.putExtra("answers", answers); // Put your answer to
//	// // your next Intent
//	// startActivity(intent);
//	// finish();
//}
