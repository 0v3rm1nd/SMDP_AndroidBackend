package com.smdp.surveytoandroid.questionstructure;

import java.util.ArrayList;

public class RankingQuestion extends Question implements Questionable {
	public ArrayList<Choice> choices;

	public RankingQuestion(String name, String question, boolean required, ArrayList<Choice> choices) {
		super(name, question, required);
		// TODO Auto-generated constructor stub
		this.choices = choices;
	}


}
