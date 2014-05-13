package com.smdp.surveytoandroid;

import java.util.ArrayList;
import java.util.HashMap;

import com.smdp.surveytoandroid.questionstructure.MultipleChoiceQuestion;
import com.smdp.surveytoandroid.questionstructure.OpenQuestion;
import com.smdp.surveytoandroid.questionstructure.Question;
import com.smdp.surveytoandroid.questionstructure.Questionable;
import com.smdp.surveytoandroid.questionstructure.RankingQuestion;
import com.smdp.surveytoandroid.questionstructure.RatingQuestion;
import com.smdp.surveytoandroid.questionstructure.StapleQuestion;
import com.smdp.surveytoandroid.questionstructure.ConstantSumQuestion;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
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
import android.widget.Spinner;
import android.widget.TextView;

public class QuestionActivity extends Activity {
	// keep track of the current question
	int quid = 0;

	// declare views
	TextView txtSurvey;
	TextView txtQuestion;
	TextView txtOther;
	TextView txtRequired;
	Button butNext;
	EditText openOther;
	RadioGroup radiogroup;
	LinearLayout viewll;

	// will store all the answers, questions will be keys and values will be an
	// array list of answers
	HashMap<String, ArrayList<String>> answers = new HashMap<String, ArrayList<String>>();

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

		// set survey name
		txtSurvey.setText(CodeGenData.getSurveyName());

		// populate the questions into the Android UI
		populateQuestions();

	}

	/*
	 * Main function to populate the question data into the Android UI Note
	 * casting is required for the different types of questions
	 */
	@SuppressLint("NewApi")
	private void populateQuestions() {
		// if we have reached the end of the questions
		// start the result activity and pass the answers data
		if (questions.size() == quid) {
			// pass data to next activity
			quid = 0;
			Intent intent = new Intent(QuestionActivity.this,
					ResultActivity.class);
			intent.putExtra("answers", answers); // Put your answer to
			// your next Intent
			startActivity(intent);
			finish();
		} else {
			for (int i = quid; i < questions.size(); i++) {
				if (questions.get(quid) instanceof MultipleChoiceQuestion) {
					// specific multi choice question
					final MultipleChoiceQuestion multiQuest = ((MultipleChoiceQuestion) (questions
							.get(quid)));
					// initial set of the required field
					if (multiQuest.isRequired()) {
						txtRequired.setVisibility(View.VISIBLE);
					} else {
						txtRequired.setVisibility(View.GONE);
					}

					// check if the other field is enabled and if not disable
					// the
					// visibility
					if (multiQuest.getOther().trim().length() > 0) {
						txtOther.setText(multiQuest.getOther());
						txtOther.setVisibility(View.VISIBLE);
						openOther.setVisibility(View.VISIBLE);
					} else {
						// set the visibility of the other field to GONE
						txtOther.setVisibility(View.GONE);
						openOther.setVisibility(View.GONE);
					}

					txtQuestion.setText(multiQuest.getQuestion());

					/*
					 * generate dynamically check boxes OR radio buttons if the
					 * question has 2 options based on the number of choices in
					 * the choices array list of the question
					 */
					if (multiQuest.choices.size() == 2) {
						radiogroup.clearCheck();
						radiogroup.removeAllViews();
						// generate radio buttons based on the choices
						for (int e = 0; e < multiQuest.choices.size(); e++) {
							RadioButton rb = new RadioButton(this);
							rb.setId(e);
							rb.setText(multiQuest.choices.get(e)
									.getDescription().toString());
							radiogroup.addView(rb);

						}
						// dynamic check box creation
					} else {
						viewll.removeAllViews();
						for (int e = 0; e < multiQuest.choices.size(); e++) {
							CheckBox cb = new CheckBox(this);
							cb.setId(e);
							cb.setText(multiQuest.choices.get(e)
									.getDescription().toString());
							viewll.addView(cb);

						}
					}

					butNext.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// make a new array list to hold the results
							// it will be passed to the answers hash map
							ArrayList<String> values = new ArrayList<String>();
							// if the radio group is not empty then process the
							// event
							// based on the radio button selected
							if (radiogroup.getChildCount() > 0) {
								// if a question is required
								if (multiQuest.isRequired()
										&& radiogroup.getCheckedRadioButtonId() == -1) {
									txtRequired
											.setText("This question is required!");
								} else {
									if (radiogroup.getCheckedRadioButtonId() != -1) {
										// if the question is optional but still
										// there
										// is an answer provided to it
										// store the answer in the answers hash
										// map
										RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
										RadioButton answer = (RadioButton) findViewById(grp
												.getCheckedRadioButtonId());
										Log.e("Answer", " " + answer.getText());
										values.add(answer.getText().toString());
										// add the answer to the array list
										answers.put(txtQuestion.getText()
												.toString(), values);
										// manage multi choice forks
										manageForks(multiQuest, values);
									}

									// else we clear the radio group
									radiogroup.clearCheck();
									radiogroup.removeAllViews();
									// increment the question id
									quid++;
									// make a recursive call to load the next
									// question
									populateQuestions();

								}

							} else {
								// check if a check box in the list view is
								// checked
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
								// if the question is required and nothing has
								// been
								// picked up as an answer update text view to
								// complain
								// about it
								if (multiQuest.isRequired()
										&& openOther.getText().toString()
												.trim().length() == 0
										&& !oneChecked) {
									txtRequired
											.setText("This question is required!");
								} else {
									/*
									 * if the other field is populated use it as
									 * an answer to this question together with
									 * the checked options if any
									 */
									if (openOther.getText().toString().trim()
											.length() > 0) {

										Log.e("Answer",
												" " + openOther.getText());
										values.add(openOther.getText()
												.toString());
										// add the answer to the hash map
										answers.put(txtQuestion.getText()
												.toString(), values);
										// clear the other field in case it is
										// used
										// afterwards
										openOther.getText().clear();
										if (oneChecked) {
											CheckBox cb;

											for (int x = 0; x < viewll
													.getChildCount(); x++) {

												cb = (CheckBox) viewll
														.getChildAt(x);
												if (cb.isChecked()) {
													Log.e("Answer",
															" " + cb.getText());
													values.add(cb.getText()
															.toString() + ",");
													// add the answer to the
													// HashMap
													answers.put(txtQuestion
															.getText()
															.toString(), values);
												}
											}
											// manage multi choice forks
											manageForks(multiQuest, values);

										}
									} else if (oneChecked) {
										/*
										 * if the other field is not specified
										 * get the answer from the currently
										 * selected options only
										 */
										for (int x = 0; x < viewll
												.getChildCount(); x++) {
											CheckBox cb;
											cb = (CheckBox) viewll
													.getChildAt(x);
											if (cb.isChecked()) {
												Log.e("Answer",
														" " + cb.getText());
												values.add(cb.getText()
														.toString());
												// add the answer to the HashMap
												answers.put(txtQuestion
														.getText().toString(),
														values);

											}
										}
										// manage multi choice forks
										manageForks(multiQuest, values);

									}
								}

								// clear linear layout
								// set other fields visibility to gone
								viewll.removeAllViews();
								txtOther.setVisibility(View.GONE);
								openOther.setVisibility(View.GONE);
								// increment the question id
								quid++;
								// make a recursive call to load the next
								// question
								populateQuestions();

							}
						}

					});

				} else if ((questions.get(quid)) instanceof OpenQuestion) {
					// specific open question
					final OpenQuestion openQuest = ((OpenQuestion) (questions
							.get(quid)));
					// initial set of the required field
					if (openQuest.isRequired()) {

						txtRequired.setVisibility(View.VISIBLE);
					} else {
						txtRequired.setVisibility(View.GONE);
					}
					openOther.setVisibility(View.VISIBLE);
					txtOther.setVisibility(View.VISIBLE);
					txtOther.setText("Open:");
					// set question
					txtQuestion.setText(openQuest.getQuestion());

					butNext.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// make a new array list to hold the results
							// it will be passed to the answers hash map
							ArrayList<String> values = new ArrayList<String>();
							// if a question is required
							if (openQuest.isRequired()
									&& openOther.getText().toString().trim()
											.length() == 0) {
								txtRequired
										.setText("This question is required!");
							} else {
								if (openOther.getText().toString().trim()
										.length() > 0) {
									// if the question is optional but still
									// there
									// is an answer provided to it
									// store the answer in the answers hash map
									Log.e("Answer", " " + openOther.getText());
									values.add(openOther.getText().toString());
									answers.put(txtQuestion.getText()
											.toString(), values);
									// clear the used field in case it is used
									// afterwards
									openOther.getText().clear();
								}

								// other field visibility is set to gone
								txtOther.setVisibility(View.GONE);
								openOther.setVisibility(View.GONE);
								// increment the question id
								quid++;
								// make a recursive call to load the next
								// question
								populateQuestions();

							}
						}

					});
				} else if ((questions.get(quid)) instanceof RankingQuestion) {
					viewll.removeAllViews();
					// specific ranking question
					final RankingQuestion rankQuest = ((RankingQuestion) (questions
							.get(quid)));
					// initial set of the required field
					if (rankQuest.isRequired()) {

						txtRequired.setVisibility(View.VISIBLE);
					} else {
						txtRequired.setVisibility(View.GONE);
					}
					txtQuestion.setText(rankQuest.getQuestion());

					// will hold the number of the choices in an array list
					ArrayList<Integer> choiceRankNumber = new ArrayList<Integer>();
					// iterate over the choices and put them in one array list
					for (int j = 0; j < rankQuest.choices.size(); j++) {

						int number;
						number = j + 1;
						choiceRankNumber.add(number);
					}
					// there can be many choices each having its own spinner to
					// indicate the ranking options
					for (int e = 0; e < rankQuest.choices.size(); e++) {
						// create the text views for the choices that must be
						// ranked
						TextView tv = new TextView(this);
						tv.setText(rankQuest.choices.get(e).getDescription()
								.toString());
						// set properties of the text views
						tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
						tv.setTextColor(Color.parseColor("#abaaaa"));
						LayoutParams lp = new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
						lp.setMargins(0, 0, 10, 0);
						tv.setLayoutParams(lp);
						tv.setId(e);
						// creating adapter for spinner
						ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(
								this, android.R.layout.simple_spinner_item,
								choiceRankNumber);

						// Drop down layout style - list view with radio button
						dataAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						Spinner sp = new Spinner(this);
						sp.setId(e);
						sp.setAdapter(dataAdapter);
						// add the views to a separate linear layout at each
						// iteration
						LinearLayout ll = new LinearLayout(this);
						ll.setOrientation(LinearLayout.HORIZONTAL);
						ll.setLayoutParams(new LayoutParams(
								LayoutParams.MATCH_PARENT,
								LayoutParams.MATCH_PARENT));
						ll.addView(tv);
						ll.addView(sp);
						// add the dynamically created linear layout to the main
						// linear layout
						viewll.addView(ll);

					}

					butNext.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// make a new array list to hold the results
							// it will be passed to the answers hash map
							ArrayList<String> values = new ArrayList<String>();
							// will be passed to the fork method
							ArrayList<String> answersValues = new ArrayList<String>();
							// go through the main linear layout,get its linear
							// layout childs
							// get the spinner data out of the linear layout
							// childs
							// and the text view choice data
							for (int i = 0; i < viewll.getChildCount(); i++) {
								v = viewll.getChildAt(i);
								if (v instanceof LinearLayout) {
									for (int j = 0; j < ((LinearLayout) v)
											.getChildCount(); j++) {
										View s = ((LinearLayout) v)
												.getChildAt(j);

										String option = "";
										String value = "";
										// get the choice description
										if (s instanceof TextView) {
											option = ((TextView) s).getText()
													.toString();
											// get the value rank of the choice
										} else if (s instanceof Spinner) {
											value = "-> Ranked:"
													+ ((Spinner) s)
															.getSelectedItem()
															.toString() + "\n";
											// add spinner data to array list of
											// answers
											answersValues.add(((Spinner) s)
													.getSelectedItem()
													.toString());

										}
										// make the final answer
										String answer = new StringBuilder(
												option).append(value)
												.toString();
										Log.e("Answer", answer);
										values.add(answer);
										answers.put(txtQuestion.getText()
												.toString(), values);
									}

								}
							}
							// manage rank choice forks
							manageForks(rankQuest, answersValues);
							// remove all dynamically added added views
							viewll.removeAllViews();
							// increment quid
							quid++;
							// make a recursive call to load the next question
							populateQuestions();

						}

					});

				} else if ((questions.get(quid)) instanceof StapleQuestion) {
					viewll.removeAllViews();
					// specific staple question
					final StapleQuestion stapleQuest = ((StapleQuestion) (questions
							.get(quid)));
					// initial set of the required field
					if (stapleQuest.isRequired()) {

						txtRequired.setVisibility(View.VISIBLE);
					} else {
						txtRequired.setVisibility(View.GONE);
					}
					// set question
					txtQuestion.setText(stapleQuest.getQuestion() + " "
							+ stapleQuest.getFirst() + " equals -5 "
							+ stapleQuest.getMid() + " equals 0 " + " "
							+ stapleQuest.getLast() + " equals 5 ");

					// will hold the values in an array list
					ArrayList<Integer> spinnerRateData = new ArrayList<Integer>();
					// iterate over the choices and put them in one array list
					for (int j = -5; j <= 5; j++) {

						int number;
						number = j;
						spinnerRateData.add(number);
					}

					// creating adapter for spinner
					ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(
							this, android.R.layout.simple_spinner_item,
							spinnerRateData);

					// Drop down layout style - list view with radio button
					dataAdapter
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					final Spinner sp = new Spinner(this);
					sp.setAdapter(dataAdapter);
					viewll.addView(sp);
					butNext.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// make a new array list to hold the results
							// it will be passed to the answers hash map
							ArrayList<String> values = new ArrayList<String>();
							// get the value of the spinner data
							String answer = sp.getSelectedItem().toString();
							Log.e("Answer", answer);
							values.add(answer);
							// put the answers in the answer hashmap
							answers.put(txtQuestion.getText().toString(),
									values);
							// manage staple forks
							manageForks(stapleQuest, values);
							// remove all programatically added added views
							viewll.removeAllViews();
							// increment quid
							quid++;
							// make a recursive call to load the next question
							populateQuestions();

						}

					});

				} else if ((questions.get(quid)) instanceof RatingQuestion) {
					viewll.removeAllViews();
					// specific rating question
					final RatingQuestion rateQuest = ((RatingQuestion) (questions
							.get(quid)));
					// initial set of the required field
					if (rateQuest.isRequired()) {

						txtRequired.setVisibility(View.VISIBLE);
					} else {
						txtRequired.setVisibility(View.GONE);
					}
					// set question
					txtQuestion.setText(rateQuest.getQuestion() + " "
							+ rateQuest.getFirst() + " equals "
							+ rateQuest.getMin() + " " + rateQuest.getLast()
							+ " equals " + rateQuest.getMax());

					// will hold the values in an array list
					ArrayList<Integer> spinnerRateData = new ArrayList<Integer>();
					// iterate over the choices and put them in one array list
					for (int j = rateQuest.getMin(); j <= rateQuest.getMax(); j++) {

						int number;
						number = j;
						spinnerRateData.add(number);
					}

					// creating adapter for spinner
					ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(
							this, android.R.layout.simple_spinner_item,
							spinnerRateData);

					// Drop down layout style - list view with radio button
					dataAdapter
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					final Spinner sp = new Spinner(this);
					sp.setAdapter(dataAdapter);
					viewll.addView(sp);
					butNext.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// make a new array list to hold the results
							// it will be passed to the answers hash map
							ArrayList<String> values = new ArrayList<String>();
							// get the value of the spinner data
							String answer = sp.getSelectedItem().toString();
							Log.e("Answer", answer);
							values.add(answer);
							// put the answers in the answer hashmap
							answers.put(txtQuestion.getText().toString(),
									values);
							// manage rating forks
							manageForks(rateQuest, values);
							// remove all programatically added added views
							viewll.removeAllViews();
							// increment quid
							quid++;
							// make a recursive call to load the next question
							populateQuestions();

						}

					});

				} else if ((questions.get(quid)) instanceof ConstantSumQuestion) {
					viewll.removeAllViews();
					// specific constant sum question
					final ConstantSumQuestion constantSumQuest = ((ConstantSumQuestion) (questions
							.get(quid)));
					// initial set of the required field
					if (constantSumQuest.isRequired()) {

						txtRequired.setVisibility(View.VISIBLE);
					} else {
						txtRequired.setVisibility(View.GONE);
					}
					txtQuestion.setText(constantSumQuest.getQuestion()
							+ " up to a total of "
							+ constantSumQuest.getConstant());
					// there can be many choices each having its edit text to
					// fill
					// the value for the sum
					for (int e = 0; e < constantSumQuest.choices.size(); e++) {
						// create the text views for the choices that must be
						// ranked
						TextView tv = new TextView(this);
						tv.setText(constantSumQuest.choices.get(e)
								.getDescription().toString());
						// set properties of the text views
						tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
						tv.setTextColor(Color.parseColor("#abaaaa"));
						tv.setLayoutParams(new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
						tv.setId(e);
						// creating edit text for each value of the sum
						EditText et = new EditText(this);
						et.setId(e);
						// set a size of the empty edit text boxes
						et.setLayoutParams(new LinearLayout.LayoutParams(150,
								100));
						// only allow numbers to be inputed
						et.setInputType(InputType.TYPE_CLASS_NUMBER);
						// add the views to a separate linear layout at each
						// iteration
						LinearLayout ll = new LinearLayout(this);
						ll.setOrientation(LinearLayout.HORIZONTAL);
						ll.setLayoutParams(new LayoutParams(
								LayoutParams.MATCH_PARENT,
								LayoutParams.MATCH_PARENT));
						ll.addView(tv);
						ll.addView(et);
						// add the dynamically created linear layout to the main
						// linear layout
						viewll.addView(ll);

					}
					butNext.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// make a new array list to hold the results
							// it will be passed to the answers hash map
							ArrayList<String> values = new ArrayList<String>();
							// will be passed to the fork method
							ArrayList<String> answersValues = new ArrayList<String>();
							// go through the main linear layout,get its linear
							// layout childs
							// get the edit text data out of the linear layout
							// childs
							// and the text view choice data
							int sumCount = 0;
							for (int i = 0; i < viewll.getChildCount(); i++) {
								v = viewll.getChildAt(i);
								if (v instanceof LinearLayout) {
									for (int j = 0; j < ((LinearLayout) v)
											.getChildCount(); j++) {
										View s = ((LinearLayout) v)
												.getChildAt(j);

										String option = "";
										String value = "";
										// get the choice description
										if (s instanceof EditText) {
											if (((EditText) s).getText()
													.toString().equals("")) {
												txtRequired
														.setText("Please specify values for the choices!");

											} else {
												value = "-> Value:"
														+ ((EditText) s)
																.getText()
																.toString()
														+ "\n";
												// add edit text data to array
												// list of
												// answers
												answersValues
														.add(((EditText) s)
																.getText()
																.toString());
												// parse the value of the edit
												// text
												// to integer
												int sumValue = Integer
														.parseInt(((EditText) s)
																.getText()
																.toString());
												// if nothing is specified,
												// display an error
												sumCount += sumValue;

											}

											// get the value rank of the choice
										} else if (s instanceof TextView) {
											option = ((TextView) s).getText()
													.toString();

										}
										// make the final answer
										String answer = new StringBuilder(
												option).append(value)
												.toString();
										Log.e("Answer", answer);
										values.add(answer);
										answers.put(txtQuestion.getText()
												.toString(), values);

									}

								}
							}
							// if the sum does not match complain about it
							if (constantSumQuest.getConstant() != sumCount
									&& constantSumQuest.isRequired()) {
								txtRequired
										.setText("The values does not add up to "
												+ constantSumQuest
														.getConstant());
								// remove the already added values
								answers.remove(txtQuestion.getText().toString());

							} else {
								// manage constant sum choice forks
								manageForks(constantSumQuest, answersValues);
								txtRequired.setText("Required");
								txtRequired.setVisibility(View.GONE);
								// remove all dynamically added added views
								viewll.removeAllViews();
								// increment quid
								quid++;
								// make a recursive call to load the next
								// question
								populateQuestions();
							}

						}

					});
				}

			}
		}
	}

	/* method to handle the forking */
	private void manageForks(Questionable question, ArrayList<String> answers) {
		// handle multiple choice forks
		if (question instanceof MultipleChoiceQuestion) {
			// cast to multi question
			MultipleChoiceQuestion multiQuest = (MultipleChoiceQuestion) question;
			// if this question has forks
			if (multiQuest.forks.size() > 0) {
				// for each fork
				for (int j = 0; j < multiQuest.forks.size(); j++) {
					// if the array list associated with the picked choice is
					// not empty
					for (int i = 0; i < answers.size(); i++) {
						// for each answer
						if (multiQuest.forks.get(j).bindingChoiceQuest
								.get(answers.get(i)) != null) {
							// for each question in the forked questions array
							// list
							for (int k = 0; k < multiQuest.forks.get(j).bindingChoiceQuest
									.get(answers.get(i)).size(); k++) {
								// cast to question
								Question forkedQuestion = (Question) multiQuest.forks
										.get(j).bindingChoiceQuest.get(
										answers.get(i)).get(i);
								Log.e("fork", forkedQuestion.getName() + " "
										+ questions.indexOf(forkedQuestion));
								// make the next question the forked question
								quid = questions.indexOf(forkedQuestion) - 1;
							}

						}
					}
				}
			}
			// handle ranking forks
		} else if (question instanceof RankingQuestion) {
			// cast to rank question
			RankingQuestion rankQuest = (RankingQuestion) question;
			// if this question has forks
			if (rankQuest.forks.size() > 0) {
				// for each fork
				for (int j = 0; j < rankQuest.forks.size(); j++) {
					// for each choice
					for (int i = 0; i < rankQuest.choices.size(); i++) {
						// if this choices is used in a fork
						if (rankQuest.forks.get(j).bindingChoiceQuest
								.get(rankQuest.choices.get(i).getDescription()) != null) {
							// for each forked question based on the choice
							for (int l = 0; l < rankQuest.forks.get(j).bindingChoiceQuest
									.get(rankQuest.choices.get(i)
											.getDescription()).size(); l++) {
								// parse the answered spinner value to an
								// integer
								int answer = Integer.parseInt(answers.get(i));
								// check if the spinner value is between the
								// fork min and max values
								if (answer >= rankQuest.forks.get(j).getMin()
										&& answer <= rankQuest.forks.get(j)
												.getMax()) {
									Question forkedQuestion = (Question) rankQuest.forks
											.get(j).bindingChoiceQuest.get(
											rankQuest.choices.get(i)
													.getDescription()).get(j);
									Log.e("fork",
											forkedQuestion.getName()
													+ " "
													+ questions
															.indexOf(forkedQuestion));
									// make the next question the forked
									// question
									quid = questions.indexOf(forkedQuestion) - 1;
								}

							}
						}
					}
				}
			}

			// handle constant sum forks
		} else if (question instanceof ConstantSumQuestion) {
			// cast to rank question
			ConstantSumQuestion constSumQuest = (ConstantSumQuestion) question;
			// if this question has forks
			if (constSumQuest.forks.size() > 0) {
				// for each fork
				for (int j = 0; j < constSumQuest.forks.size(); j++) {
					// for each choice
					for (int i = 0; i < constSumQuest.choices.size(); i++) {
						// if this choices is used in a fork
						if (constSumQuest.forks.get(j).bindingChoiceQuest
								.get(constSumQuest.choices.get(i)
										.getDescription()) != null) {
							// for each forked question based on the choice
							for (int l = 0; l < constSumQuest.forks.get(j).bindingChoiceQuest
									.get(constSumQuest.choices.get(i)
											.getDescription()).size(); l++) {
								// parse the answered spinner value to an
								// integer
								int answer = Integer.parseInt(answers.get(i));
								// check if the spinner value is between the
								// fork min and max values
								if (answer >= constSumQuest.forks.get(j)
										.getMin()
										&& answer <= constSumQuest.forks.get(j)
												.getMax()) {
									Question forkedQuestion = (Question) constSumQuest.forks
											.get(j).bindingChoiceQuest.get(
											constSumQuest.choices.get(i)
													.getDescription()).get(j);
									Log.e("fork",
											forkedQuestion.getName()
													+ " "
													+ questions
															.indexOf(forkedQuestion));
									// make the next question the forked
									// question
									quid = questions.indexOf(forkedQuestion) - 1;
								}

							}
						}
					}
				}
			}

			// handle rating forks
		} else if (question instanceof RatingQuestion) {
			// cast to rank question
			RatingQuestion rateQuest = (RatingQuestion) question;
			// if this question has forks
			if (rateQuest.forks.size() > 0) {
				// for each fork
				for (int j = 0; j < rateQuest.forks.size(); j++) {
					// parse the answer to integer
					int answer = Integer.parseInt(answers.get(0));
					// if the answer is between the specified min and max in the
					// fork
					if (answer >= rateQuest.forks.get(j).getMin()
							&& answer <= rateQuest.forks.get(j).getMax()) {
						// for each question in the forked questions array
						// list
						for (int k = 0; k < rateQuest.forks.get(j).forkQues
								.size(); k++) {
							// cast to question
							Question forkedQuestion = (Question) rateQuest.forks
									.get(j).forkQues.get(k);
							Log.e("fork", forkedQuestion.getName() + " "
									+ questions.indexOf(forkedQuestion));
							// make the next question the forked question
							quid = questions.indexOf(forkedQuestion) - 1;
						}

					}
				}
			}

			// handle staple forks
		} else if (question instanceof StapleQuestion) {
			// cast to rank question
			StapleQuestion stapleQuest = (StapleQuestion) question;
			// if this question has forks
			if (stapleQuest.forks.size() > 0) {
				// for each fork
				for (int j = 0; j < stapleQuest.forks.size(); j++) {
					// parse the answer to integer
					int answer = Integer.parseInt(answers.get(0));
					// if the answer is between the specified min and max in the
					// fork
					if (answer >= stapleQuest.forks.get(j).getMin()
							&& answer <= stapleQuest.forks.get(j).getMax()) {
						// for each question in the forked questions array
						// list
						for (int k = 0; k < stapleQuest.forks.get(j).forkQues
								.size(); k++) {
							// cast to question
							Question forkedQuestion = (Question) stapleQuest.forks
									.get(j).forkQues.get(k);
							Log.e("fork", forkedQuestion.getName() + " "
									+ questions.indexOf(forkedQuestion));
							// make the next question the forked question
							quid = questions.indexOf(forkedQuestion) - 1;
						}

					}
				}
			}

		}
	}
}