package com.smdp.surveytoandroid.questionstructure;

import java.util.ArrayList;

public class Question {
	//will hold the forks
	public ArrayList<Fork> forks = new ArrayList<Fork>();
	
	private String name;
	private String question;
	private boolean required;
	private String answer;
	private int req;

	public Question(String name, String question, boolean required) {
		super();
		this.name = name;
		this.question = question;
		this.required = required;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public int getReq() {
		if (required) {
			req = 1;
			return req;
		} else {
			return req = 0;

		}
	}

}
