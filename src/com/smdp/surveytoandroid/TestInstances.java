//package com.smdp.surveytoandroid;
//
//import java.util.ArrayList;
//
//import com.smdp.surveytoandroid.questionstructure.Choice;
//import com.smdp.surveytoandroid.questionstructure.Fork;
//import com.smdp.surveytoandroid.questionstructure.MultipleChoiceQuestion;
//import com.smdp.surveytoandroid.questionstructure.OpenQuestion;
//import com.smdp.surveytoandroid.questionstructure.Questionable;
//import com.smdp.surveytoandroid.questionstructure.RankingQuestion;
//import com.smdp.surveytoandroid.questionstructure.RatingQuestion;
//import com.smdp.surveytoandroid.questionstructure.ConstantSumQuestion;
//import com.smdp.surveytoandroid.questionstructure.StapleQuestion;
//import com.smdp.surveytoandroid.questionstructure.Survey;
//
//public class CodeGenData {
//	
//	private static ArrayList<Questionable> questions = new ArrayList<Questionable>();
//
//	public static ArrayList<Questionable> addQuestionsToArrList() {
//
//		Survey Survey1 = new Survey ("Survey1");
//		
//		ArrayList<Choice> arrMultiQ2 = new ArrayList<Choice>();
//		arrMultiQ2.add(new Choice ("A2","Bachelor")); 
//		arrMultiQ2.add(new Choice ("B2","Master's")); 
//		arrMultiQ2.add(new Choice ("C2","PHD")); 
//		MultipleChoiceQuestion Q2 = new MultipleChoiceQuestion ("Q2","At what level of education?",true, "", arrMultiQ2 );		
//		
//		ArrayList<Questionable> q2ForkArr = new ArrayList<Questionable>();
//		
//		questions.add(Q2);	
//		
//		ArrayList<Choice> arrRankQ3 = new ArrayList<Choice>();
//		 arrRankQ3.add(new Choice ("A3","MDDP")); 
//		 arrRankQ3.add(new Choice ("B3","Database Tuning")); 
//		 arrRankQ3.add(new Choice ("C3","Discrete Mathematics")); 
//		RankingQuestion Q3 = new RankingQuestion ("Q3","Rank your favorite subjects",false, arrRankQ3);
//		
//		ArrayList<Questionable> q3ForkArr = new ArrayList<Questionable>();
//		
//		Fork q3ForkId0 = new Fork(1, 2);
//		q3ForkArr.add(Q4); 
//		q3ForkId0.bindingChoiceQuest.put("C3",q3ForkArr);
//		Q3.forks.add(q3ForkId0);
//			
//		questions.add(Q3);
//		
//		OpenQuestion Q4 = new OpenQuestion ("Q4","What are your thoughts about model driven development?",false);
//		questions.add(Q4);			
//		
//		OpenQuestion Q8 = new OpenQuestion ("Q8","What are your thoughts about model driven development?",false);
//		questions.add(Q8);			
//		
//		ArrayList<Choice> arrMultiQ6 = new ArrayList<Choice>();
//		arrMultiQ6.add(new Choice ("A","Bachelor")); 
//		arrMultiQ6.add(new Choice ("B","Master's")); 
//		arrMultiQ6.add(new Choice ("C","PHD")); 
//		MultipleChoiceQuestion Q6 = new MultipleChoiceQuestion ("Q6","At what level of education?",true, "", arrMultiQ6 );		
//		
//		ArrayList<Questionable> q6ForkArr = new ArrayList<Questionable>();
//		
//		Fork q6ForkId1 = new Fork();
//		q6ForkArr.add(Q9); 
//		q6ForkId1.bindingChoiceQuest.put("A",q6ForkArr);
//		Q6.forks.add(q6ForkId1);
//			
//		Fork q6ForkId0 = new Fork();
//		q6ForkArr.add(Q7); 
//		q6ForkId0.bindingChoiceQuest.put("B",q6ForkArr);
//		Q6.forks.add(q6ForkId0);
//			
//		questions.add(Q6);	
//		
//		StapleQuestion Q7 = new StapleQuestion ("Q7","Have you been following the lectures",false,-5,5,"I've been asleep the entire time", "I am always a 100% focused", "I've been present");
//		
//		ArrayList<Questionable> q7ForkArr = new ArrayList<Questionable>();
//		
//		questions.add(Q7);
//						
//		OpenQuestion Q9 = new OpenQuestion ("Q9","What are your thoughts about model driven development?",false);
//		questions.add(Q9);			
//		
//		return questions;
//	}
//}	