package com.smdp.surveytoandroid;

import java.util.ArrayList;
import java.util.HashMap;

import com.smdp.surveytoandroid.questionstructure.Choice;
import com.smdp.surveytoandroid.questionstructure.DichotomousQuestion;
import com.smdp.surveytoandroid.questionstructure.MultipleChoiceQuestion;
import com.smdp.surveytoandroid.questionstructure.OpenQuestion;
import com.smdp.surveytoandroid.questionstructure.Questionable;
import com.smdp.surveytoandroid.questionstructure.RankingQuestion;
import com.smdp.surveytoandroid.questionstructure.RatingQuestion;
import com.smdp.surveytoandroid.questionstructure.StapleQuestion;
import com.smdp.surveytoandroid.questionstructure.SumConstantQuestion;
import com.smdp.surveytoandroid.questionstructure.Survey;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class QuestionActivity extends Activity {
	// keep track of the current question
	int quid = 0;

	// views
	TextView txtSurvey;
	TextView txtQuestion;
	TextView txtOther;
	TextView txtRequired;
	Button butNext;
	EditText openOther;
	RadioGroup radiogroup;
	LinearLayout viewll;
	

	// will store all the answers
	HashMap<String, String> answers = new HashMap<String, String>();
	// initialize an arraylist to hold the surveys if needed
	ArrayList<Survey> survey = new ArrayList<Survey>();
	// initialize an array list to hold all the questions
	ArrayList<Questionable> questions = new ArrayList<Questionable>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		txtSurvey = (TextView) findViewById(R.id.textSurvey);
		txtQuestion = (TextView) findViewById(R.id.textQuestion);
		viewll = (LinearLayout) findViewById(R.id.viewLL);
		txtOther = (TextView) findViewById(R.id.textOther);
		txtRequired = (TextView) findViewById(R.id.textRequired);
		butNext = (Button) findViewById(R.id.nextQuestion);
		openOther = (EditText) findViewById(R.id.editTextOpenOther);
		radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);

		// add the code generated data to the Questionable array list
		questions = CodeGenData.addQuestionsToArrList();
		// hard code the survey title which must be changed
		// txtSurvey.setText(survey.get(0).getName());
		// populate the questions into the Android UI
		populateQuestions();

	}

	// Main function to populate the question data into the Android UI
	// Note casting is required for the different types of questions
	@SuppressLint("NewApi")
	private void populateQuestions() {

		for (int i = quid; i < questions.size(); i++) {
			if (questions.get(quid) instanceof MultipleChoiceQuestion) {
				// initial set of the required field
				if (((MultipleChoiceQuestion) (questions.get(quid)))
						.isRequired()) {
					txtRequired.setVisibility(View.VISIBLE);
				} else {
					txtRequired.setVisibility(View.GONE);
				}

				// check if the other field is enabled and if not disable the
				// visibility
				if (((MultipleChoiceQuestion) (questions.get(quid))).getOther()
						.trim().length() > 0) {
					txtOther.setText(((MultipleChoiceQuestion) (questions
							.get(quid))).getOther());
					txtOther.setVisibility(View.VISIBLE);
					openOther.setVisibility(View.VISIBLE);
				} else {
					// set the visibility of the other field to GONE
					txtOther.setVisibility(View.GONE);
					openOther.setVisibility(View.GONE);
				}

				txtQuestion.setText(((MultipleChoiceQuestion) (questions
						.get(quid))).getQuestion());

				/*
				 * generate dynamically checkboxes based on the number of
				 * choices in the choices array list of the question
				 */
				viewll.removeAllViews();
				for (int e = 0; e < ((MultipleChoiceQuestion) (questions
						.get(quid))).choices.size(); e++) {
					CheckBox cb = new CheckBox(this);
					cb.setId(e);
					cb.setText(((MultipleChoiceQuestion) (questions.get(quid))).choices
							.get(e).getDescription().toString());
					viewll.addView(cb);

				}

				butNext.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// check if a checkbox in the list view is checked
						boolean oneChecked = false;
						for (int i = 0; i < viewll.getChildCount(); i++) {
							v = viewll.getChildAt(i);
							if (v instanceof CheckBox) {
								if (((CheckBox) v).isChecked()) {
									oneChecked = true;
									break;
								}
							}
						}
						// if the question is required and nothing has been
						// picked up as an answer update text view to complain
						// about it
						if (((MultipleChoiceQuestion) (questions.get(quid)))
								.isRequired()
								&& openOther.getText().toString().trim()
										.length() == 0 && !oneChecked) {
							txtRequired.setText("This question is required!");
						} else {
							/*
							 * if the other field is populated use it as an
							 * answer to this question together with the checked
							 * options if any
							 */
							if (openOther.getText().toString().trim().length() > 0) {

								Log.e("Answer", " " + openOther.getText());

								// add the answer to the array list
								answers.put(txtQuestion.getText().toString(),
										openOther.getText().toString());
								// clear the used field in case it is used
								// afterwards
								openOther.getText().clear();
								if (oneChecked) {
									CheckBox cb;

									for (int x = 0; x < viewll.getChildCount(); x++) {

										cb = (CheckBox) viewll.getChildAt(x);
										if (cb.isChecked()) {
											Log.e("Answer", " " + cb.getText());

											// add the answer to the HashMap
											answers.put(txtQuestion.getText()
													.toString(), cb.getText()
													.toString());

										}
									}

								}
							} else if (oneChecked) {
								/*
								 * if the other field is not specified get the
								 * answer from the currently selected options
								 * only
								 */
								for (int x = 0; x < viewll.getChildCount(); x++) {
									CheckBox cb;
									cb = (CheckBox) viewll.getChildAt(x);
									if (cb.isChecked()) {
										Log.e("Answer", " " + cb.getText());

										// add the answer to the HashMap
										answers.put(txtQuestion.getText()
												.toString(), cb.getText()
												.toString());

									}
								}
							}
//							// test multi forks
//							if (((MultipleChoiceQuestion) (questions.get(quid))).forks
//									.size() >= 0) {
//								for (int j = 0; j < ((MultipleChoiceQuestion) (questions
//										.get(quid))).forks.size(); j++) {
//									
//									if (((MultipleChoiceQuestion)(questions.get(quid))).forks.get(j).bindingChoiceQuest.get()) {
//										
//									}
//									
//								}
//							} else {

								// clear checkbox list view
								// visibility to gone
								viewll.removeAllViews();
								txtOther.setVisibility(View.GONE);
								// increment the question id
								quid++;
								// make a recursive call to load the next
								// question
								populateQuestions();
							
						}
					}

				});

			} else if ((questions.get(quid)) instanceof OpenQuestion) {
				// initial set of the required field
				if (((OpenQuestion) (questions.get(quid))).isRequired()) {

					txtRequired.setVisibility(View.VISIBLE);
				} else {
					txtRequired.setVisibility(View.GONE);
				}
				openOther.setVisibility(View.VISIBLE);
				txtOther.setVisibility(View.VISIBLE);
				txtOther.setText("Open:");
				// set question
				txtQuestion.setText(((OpenQuestion) (questions.get(quid)))
						.getQuestion());

				butNext.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						// if a question is required
						if (((OpenQuestion) (questions.get(quid))).isRequired()
								&& openOther.getText().toString().trim()
										.length() == 0) {
							txtRequired.setText("This question is required!");
						} else {
							if (openOther.getText().toString().trim().length() > 0) {
								// if the question is optional but still there
								// is an answer provided to it
								// store the answer in the answers hashmap
								Log.e("Answer", " " + openOther.getText());

								// add the answer to the array list
								answers.put(txtQuestion.getText().toString(),
										openOther.getText().toString());
								// clear the used field in case it is used
								// afterwards
								openOther.getText().clear();
							}

							// visibility to gone
							txtOther.setVisibility(View.GONE);
							openOther.setVisibility(View.GONE);
							// increment the question id
							quid++;
							// make a recursive call to load the next question
							populateQuestions();

						}
					}

				});
			} else if ((questions.get(quid)) instanceof DichotomousQuestion) {
				radiogroup.removeAllViews();
				// initial set of the required field
				if (((DichotomousQuestion) (questions.get(quid))).isRequired()) {

					txtRequired.setVisibility(View.VISIBLE);
				} else {
					txtRequired.setVisibility(View.GONE);
				}
				txtQuestion
						.setText(((DichotomousQuestion) (questions.get(quid)))
								.getQuestion());
				// there are only 2 options yes and no so we can just hard code
				// those
				RadioButton rdbtnYes = new RadioButton(this);
				RadioButton rdbtnNo = new RadioButton(this);
				rdbtnYes.setId(1);
				rdbtnNo.setId(2);
				rdbtnYes.setText("Yes");
				rdbtnNo.setText("No");
				radiogroup.addView(rdbtnYes);
				radiogroup.addView(rdbtnNo);

				butNext.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						// if a question is required
						if (((DichotomousQuestion) (questions.get(quid)))
								.isRequired()
								&& radiogroup.getCheckedRadioButtonId() == -1) {
							txtRequired.setText("This question is required!");
						} else {
							if (radiogroup.getCheckedRadioButtonId() != -1) {
								// if the question is optional but still there
								// is an answer provided to it
								// store the answer in the answers hashmap
								RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
								RadioButton answer = (RadioButton) findViewById(grp
										.getCheckedRadioButtonId());
								Log.e("Answer", " " + answer.getText());

								// add the answer to the array list
								answers.put(txtQuestion.getText().toString(),
										answer.getText().toString());
							}

							// else we clear the radiogroup
							radiogroup.clearCheck();
							radiogroup.removeAllViews();
							// increment the question id
							quid++;
							// make a recursive call to load the next question
							populateQuestions();

						}
					}

				});

			} else if ((questions.get(quid)) instanceof RankingQuestion) {
				viewll.removeAllViews();
				txtOther.setVisibility(View.GONE);
				openOther.setVisibility(View.GONE);
				// initial set of the required field
				if (((RankingQuestion) (questions.get(quid))).isRequired()) {

					txtRequired.setVisibility(View.VISIBLE);
				} else {
					txtRequired.setVisibility(View.GONE);
				}
				txtQuestion
						.setText(((RankingQuestion) (questions.get(quid)))
								.getQuestion());
				
				//will hold the number of the choices in an array list
				ArrayList<Integer> choiceRankNumber = new ArrayList<Integer>();
				//iterate over the choices and put them in one array list
				for (int j = 0; j < ((RankingQuestion) (questions
						.get(quid))).choices.size(); j++) {
					
					int number;
					number = j + 1;
					choiceRankNumber.add(number);
				}
				// there can be many choices each having its own spinner to indicate the ranking options
				for (int e = 0; e < ((RankingQuestion) (questions
						.get(quid))).choices.size(); e++) {
					//create the textviews for the choices that must be ranked
					TextView tv = new TextView(this);
					tv.setText(((RankingQuestion) (questions.get(quid))).choices.get(e).getDescription().toString());
					//set properties of the text views
					tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
					tv.setTextColor(Color.parseColor("#eef3f3"));
					tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					//creating adapter for spinner
					ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(
							this, android.R.layout.simple_spinner_item, choiceRankNumber);
					
					// Drop down layout style - list view with radio button
					dataAdapter
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					Spinner sp = new Spinner(this);
					sp.setId(e);
					sp.setAdapter(dataAdapter);
					//add the views
					viewll.addView(tv);
					viewll.addView(sp);


				}

			} else if ((questions.get(quid)) instanceof RatingQuestion) {

			} else if ((questions.get(quid)) instanceof SumConstantQuestion) {

			} else if ((questions.get(quid)) instanceof StapleQuestion) {

			}
		}

	}

	// quid = 0;
	// Intent intent = new Intent(QuestionActivity.this,
	// ResultActivity.class);
	// intent.putExtra("answers", answers); // Put your answer to
	// // your next Intent
	// startActivity(intent);
	// finish();
}