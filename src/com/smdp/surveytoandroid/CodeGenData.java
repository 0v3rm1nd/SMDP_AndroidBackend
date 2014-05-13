package com.smdp.surveytoandroid;

import java.util.ArrayList;

import com.smdp.surveytoandroid.questionstructure.Choice;
import com.smdp.surveytoandroid.questionstructure.Fork;
import com.smdp.surveytoandroid.questionstructure.MultipleChoiceQuestion;
import com.smdp.surveytoandroid.questionstructure.OpenQuestion;
import com.smdp.surveytoandroid.questionstructure.Questionable;
import com.smdp.surveytoandroid.questionstructure.RankingQuestion;
import com.smdp.surveytoandroid.questionstructure.RatingQuestion;
import com.smdp.surveytoandroid.questionstructure.ConstantSumQuestion;
import com.smdp.surveytoandroid.questionstructure.StapleQuestion;
import com.smdp.surveytoandroid.questionstructure.Survey;

public class CodeGenData {

	private static ArrayList<Questionable> questions = new ArrayList<Questionable>();

	public static String getSurveyName() {

		Survey Survey1 = new Survey("Survey1");
		String surveyName = Survey1.getName();
		return surveyName;
	}

	public static ArrayList<Questionable> addQuestionsToArrList() {

		ArrayList<Choice> arrConstSumQ1 = new ArrayList<Choice>();
		arrConstSumQ1.add(new Choice("A", "Learning"));
		arrConstSumQ1.add(new Choice("B", "Cooking"));
		arrConstSumQ1.add(new Choice("C", "Training"));
		arrConstSumQ1.add(new Choice("D", "Gaming"));
		ConstantSumQuestion Q1 = new ConstantSumQuestion("Q1",
				"Divide your daily activities in hours", true, 100,
				arrConstSumQ1);

		questions.add(Q1);

		ArrayList<Choice> arrMultiQ2 = new ArrayList<Choice>();
		arrMultiQ2.add(new Choice("A", "SMDP"));
		arrMultiQ2.add(new Choice("B", "OOP"));
		arrMultiQ2.add(new Choice("C", "Algorithms"));
		arrMultiQ2.add(new Choice("D", "Discrete Mathematics"));
		MultipleChoiceQuestion Q2 = new MultipleChoiceQuestion("Q2",
				"What are your favorite subjects at ITU?", true, "", arrMultiQ2);

		questions.add(Q2);

		ArrayList<Choice> arrRankQ3 = new ArrayList<Choice>();
		arrRankQ3.add(new Choice("A", "SMDP"));
		arrRankQ3.add(new Choice("B", "OOP"));
		arrRankQ3.add(new Choice("C", "Discrete Mathematics"));
		RankingQuestion Q3 = new RankingQuestion("Q3",
				"Rank your favorite subjects", false, arrRankQ3);

		questions.add(Q3);

		OpenQuestion Q4 = new OpenQuestion("Q4",
				"What are your thoughts about model driven development?", false);
		questions.add(Q4);

		OpenQuestion Q5 = new OpenQuestion("Q5",
				"What are your thoughts about model driven development?", false);
		questions.add(Q5);

		ArrayList<Choice> arrMultiQ6 = new ArrayList<Choice>();
		arrMultiQ6.add(new Choice("A", "Bachelor"));
		arrMultiQ6.add(new Choice("B", "Master's"));
		arrMultiQ6.add(new Choice("C", "PHD"));
		MultipleChoiceQuestion Q6 = new MultipleChoiceQuestion("Q6",
				"What is your educational level?", true, "", arrMultiQ6);

		questions.add(Q6);

		StapleQuestion Q7 = new StapleQuestion("Q7",
				"Have you been following the lectures", false,
				"I've been asleep the entire time",
				"I am always a 100% focused", "I've been present");

		questions.add(Q7);

		RatingQuestion Q8 = new RatingQuestion("Q8", "Rate the SMDP class ",
				true, 1, 10, "Bad", " Best");

		questions.add(Q8);

		OpenQuestion Q9 = new OpenQuestion("Q9",
				"Tell us what you liked about the SMDP class", false);
		questions.add(Q9);

		Fork q1ForkId0 = new Fork(20, 40);
		ArrayList<Questionable> q1ForkArrId0 = new ArrayList<Questionable>();
		q1ForkArrId0.add(Q5);
		q1ForkId0.bindingChoiceQuest.put("Training", q1ForkArrId0);
		Q1.forks.add(q1ForkId0);

		Fork q2ForkId0 = new Fork();
		ArrayList<Questionable> q2ForkArrId0 = new ArrayList<Questionable>();
		q2ForkArrId0.add(Q5);
		q2ForkId0.bindingChoiceQuest.put("OOP", q2ForkArrId0);
		Q2.forks.add(q2ForkId0);

		Fork q3ForkId0 = new Fork(1, 2);
		ArrayList<Questionable> q3ForkArrId0 = new ArrayList<Questionable>();
		q3ForkArrId0.add(Q4);
		q3ForkId0.bindingChoiceQuest.put("Discrete Mathematics", q3ForkArrId0);
		Q3.forks.add(q3ForkId0);

		Fork q6ForkId0 = new Fork();
		ArrayList<Questionable> q6ForkArrId0 = new ArrayList<Questionable>();
		q6ForkArrId0.add(Q7);
		q6ForkId0.bindingChoiceQuest.put("Master's", q6ForkArrId0);
		Q6.forks.add(q6ForkId0);

		Fork q8ForkId0 = new Fork(5, 9);
		q8ForkId0.forkQues.add(Q9);
		Q8.forks.add(q8ForkId0);

		return questions;
	}
}