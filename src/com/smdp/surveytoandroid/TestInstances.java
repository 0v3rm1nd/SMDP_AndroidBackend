package com.smdp.surveytoandroid;

import java.util.ArrayList;

import com.smdp.surveytoandroid.questionstructure.Choice;
import com.smdp.surveytoandroid.questionstructure.DichotomousQuestion;
import com.smdp.surveytoandroid.questionstructure.Fork;
import com.smdp.surveytoandroid.questionstructure.MultipleChoiceQuestion;
import com.smdp.surveytoandroid.questionstructure.OpenQuestion;
import com.smdp.surveytoandroid.questionstructure.Questionable;
import com.smdp.surveytoandroid.questionstructure.RankingQuestion;
import com.smdp.surveytoandroid.questionstructure.RatingQuestion;
import com.smdp.surveytoandroid.questionstructure.SumConstantQuestion;
import com.smdp.surveytoandroid.questionstructure.Staple;
import com.smdp.surveytoandroid.questionstructure.Survey;

public class TestInstances {
	
	private static ArrayList<Questionable> questions = new ArrayList<Questionable>();

	public static ArrayList<Questionable> addQuestionsToArrList() {

		Survey Survey1 = new Survey ("Survey1");
		
		DichotomousQuestion Q1 = new DichotomousQuestion ("Q1","Do you attend ITU?",true);
		questions.add(Q1);				
		
		ArrayList<Choice> arrMultiQ2 = new ArrayList<Choice>();
		 arrMultiQ2.add(new Choice ("A","Bachelor")); 
		 arrMultiQ2.add(new Choice ("B","Master's")); 
		 arrMultiQ2.add(new Choice ("C","PHD")); 
		 MultipleChoiceQuestion Q2 = new MultipleChoiceQuestion ("Q2","At what level of education?",true, "", arrMultiQ2 );
		questions.add(Q2);			
		
		ArrayList<Choice> arrRankQ3 = new ArrayList<Choice>();
		 arrRankQ3.add(new Choice ("A","MDDP")); 
		 arrRankQ3.add(new Choice ("B","Database Tuning")); 
		 arrRankQ3.add(new Choice ("C","Discrete Mathematics")); 
		 RankingQuestion Q3 = new RankingQuestion ("Q3","Rank your favorite subjects",false, arrRankQ3 );
		questions.add(Q3);			
		
		OpenQuestion Q4 = new OpenQuestion ("Q4","What are your thoughts about model driven development?",false);
		questions.add(Q4);			
		
		RatingQuestion Q5 = new RatingQuestion ("Q5","How much do you like databases ",false, 0, 10, "Hate it"," I dream of databases at night");
		questions.add(Q5);				
		
		Staple Q6 = new Staple ("Q6","Have you been following the lectures",false,-5,5,"I've been asleep the entire time", "I am always a 100% focused", "I've been present");
		questions.add(Q6);
						
		return questions;
	}
}	

