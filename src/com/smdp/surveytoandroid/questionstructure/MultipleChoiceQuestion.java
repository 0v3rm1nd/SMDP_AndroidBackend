package com.smdp.surveytoandroid.questionstructure;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question implements Questionable{

	public MultipleChoiceQuestion(String name, String question,
			boolean required, String other, ArrayList<Choice> choices) {
		super(name, question, required);
		this.other = other;
		this.choices = choices;
		// TODO Auto-generated constructor stub
	}

	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}

	private String other;
	public ArrayList<Choice> choices;


}
