package com.smdp.surveytoandroid;

import java.util.ArrayList;

import com.smdp.surveytoandroid.questionstructure.Choice;
import com.smdp.surveytoandroid.questionstructure.Fork;
import com.smdp.surveytoandroid.questionstructure.MultipleChoiceQuestion;
import com.smdp.surveytoandroid.questionstructure.OpenQuestion;
import com.smdp.surveytoandroid.questionstructure.Questionable;
import com.smdp.surveytoandroid.questionstructure.RankingQuestion;
import com.smdp.surveytoandroid.questionstructure.RatingQuestion;
import com.smdp.surveytoandroid.questionstructure.StapleQuestion;
import com.smdp.surveytoandroid.questionstructure.ConstantSumQuestion;
import com.smdp.surveytoandroid.questionstructure.Survey;

public class CodeGenData {
	// will be used to put the different type questions into the same array list
	// NOTE casting will be required
	private static ArrayList<Questionable> questions = new ArrayList<Questionable>();

	// get the survey name
	public static String getSurveyName() {

		// create survey
		Survey s1 = new Survey("Survey1");
		String surveyName = s1.getName();
		return surveyName;

	}

	public static ArrayList<Questionable> addQuestionsToArrList() {

		// create multiple choice 1 - dichotomous like
		Choice choice1 = new Choice("choice1", "Yes");
		Choice choice2 = new Choice("choice2", "No");

		ArrayList<Choice> arraychoice1 = new ArrayList<Choice>();
		arraychoice1.add(choice1);
		arraychoice1.add(choice2);

		MultipleChoiceQuestion m1 = new MultipleChoiceQuestion("Q1",
				"Did you find the Ask language useful?", true, "", arraychoice1);

		// create multiple choice 2
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
				"What is your least favorite super hero?", false, "testOther2",
				arraychoice2);

		// create ranking question 1
		Choice choice9 = new Choice("choice9", "Code Generation");
		Choice choice10 = new Choice("choice10", "Editor");
		Choice choice11 = new Choice("choice11", "Forking");


		ArrayList<Choice> arraychoice3 = new ArrayList<Choice>();
		arraychoice3.add(choice9);
		arraychoice3.add(choice10);
		arraychoice3.add(choice11);
		RankingQuestion r1 = new RankingQuestion("Q3",
				"Favorite Features", true, arraychoice3);

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
				"What do you think about Marvel?", true);

		// create an open question 2
		OpenQuestion o2 = new OpenQuestion("q9",
				"What do you think about DC Unviverse?", false);

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
		ConstantSumQuestion sum1 = new ConstantSumQuestion("q7",
				"Sum time spend on studying on different subjects", true, 100,
				arraychoice5);

		// create rating question 1
		RatingQuestion rate1 = new RatingQuestion("q8", "Ease of use", false,
				1, 10, "Aweful", "Amazing");

		// create stape question 1
		StapleQuestion staple1 = new StapleQuestion("q9",
				"Have you been following lecures", true, "yes", "somethines",
				"no");

		// QUESTION 1 FORKS
		// fork1 for m1
		Fork m1f1 = new Fork();
		ArrayList<Questionable> forkedQuestionsM1 = new ArrayList<Questionable>();
		forkedQuestionsM1.add(r1);
		m1f1.bindingChoiceQuest.put(choice1.getDescription(), forkedQuestionsM1);
		// fork2 for question 1
		Fork m1f2 = new Fork();
		ArrayList<Questionable> forkedQuestionsM2 = new ArrayList<Questionable>();
		forkedQuestionsM2.add(staple1);
		m1f2.bindingChoiceQuest.put(choice2.getDescription(), forkedQuestionsM2);
		// add forks to question1
		m1.forks.add(m1f1);
		m1.forks.add(m1f2);
		
		//Question m2 forks
		//fork1 for m2
		Fork m2f1 = new Fork();
		ArrayList<Questionable> forkedQuestionsMulti1 = new ArrayList<Questionable>();
		forkedQuestionsMulti1.add(r2);
		m2f1.bindingChoiceQuest.put(choice5.getDescription(), forkedQuestionsMulti1);
		//fork2 for m2
		Fork m2f2 = new Fork();
		ArrayList<Questionable> forkedQuestionsMulti2 = new ArrayList<Questionable>();
		forkedQuestionsMulti2.add(sum1);
		m2f2.bindingChoiceQuest.put(choice6.getDescription(), forkedQuestionsMulti2);
		
		m2.forks.add(m2f1);
		m2.forks.add(m2f2);
		
		// QUESTION 3 FORKS
		// fork1 for r1
		Fork r1f1 = new Fork(1, 3);
		ArrayList<Questionable> forkedQuestionsR1 = new ArrayList<Questionable>();
		forkedQuestionsR1.add(r2);
		r1f1.bindingChoiceQuest.put(choice9.getDescription(), forkedQuestionsR1);
		// fork2 for r1
//		Fork r1f2 = new Fork(1, 2);
//		ArrayList<Questionable> forkedQuestionsR2 = new ArrayList<Questionable>();
//		forkedQuestionsR2.add(o1);
//		r1f2.bindingChoiceQuest.put(choice10.getDescription(), forkedQuestionsR2);
		// add the forks to question3
		r1.forks.add(r1f1);
//		r1.forks.add(r1f2);
		
		// QUESTION 2 FORKS
		// fork1 for r1
		Fork rate1f1 = new Fork(7, 10);
		rate1f1.forkQues.add(o2);
		// fork2 for r1
		Fork rate1f2 = new Fork(2, 5);
		rate1f2.forkQues.add(sum1);
		// add the forks to question2
		rate1.forks.add(rate1f1);
		rate1.forks.add(rate1f2);
		
		//QUESTION 9 FORKS
		// fork1 for staple1
		Fork staple1f1 = new Fork(-5, 0);
		staple1f1.forkQues.add(sum1);
		// fork1 for staple2
		Fork staple1f2 = new Fork(1, 5);
		staple1f2.forkQues.add(o2);
		
		staple1.forks.add(staple1f1);
		staple1.forks.add(staple1f2);
		
		//QUESTION constant sum FORKS
		// fork1 for r1
//		Fork sum1f1 = new Fork(20, 50);
//		ArrayList<Questionable> forkedQuestionsSum1 = new ArrayList<Questionable>();
//		forkedQuestionsSum1.add(r2);
//		sum1f1.bindingChoiceQuest.put(choice17.getDescription(), forkedQuestionsR1);
//		sum1.forks.add(sum1f1);

		// add questions to the Questionable array list
//		questions.add(m1);
//		questions.add(rate1);
//		questions.add(r1);
		questions.add(m2);
		questions.add(staple1);
		questions.add(o1);
		questions.add(o2);
		questions.add(r2);
		questions.add(sum1);

		// return the populated Questionable array list
		return questions;
	}

}
