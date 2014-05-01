package com.smdp.surveytoandroid.questionstructure;

import java.util.ArrayList;

public class SumConstantQuestion extends Question implements Questionable{
	private int constant;
	public ArrayList<Choice> choices;

	public SumConstantQuestion(String name, String question, boolean required, int constant, ArrayList<Choice> choices) {
		super(name, question, required);
		// TODO Auto-generated constructor stub
		this.constant = constant;
		this.choices = choices;
	}

	public int getConstant() {
		return constant;
	}

	public void setConstant(int constant) {
		this.constant = constant;
	}

}
