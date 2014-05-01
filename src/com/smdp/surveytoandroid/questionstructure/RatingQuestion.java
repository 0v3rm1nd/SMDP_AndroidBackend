package com.smdp.surveytoandroid.questionstructure;

public class RatingQuestion extends Question implements Questionable {
	public RatingQuestion(String name, String question, boolean required,
			int min, int max, String first, String last) {
		super(name, question, required);
		this.first = first;
		this.last = last;
		this.min = min;
		this.max = max;
		// TODO Auto-generated constructor stub
	}

	private int min;
	private int max;
	private String first;
	private String last;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
