package com.smdp.surveytoandroid.questionstructure;

public class StapleQuestion extends Question implements Questionable {

	public StapleQuestion(String name, String question, boolean required,
			String first, String mid, String last) {
		super(name, question, required);
		// TODO Auto-generated constructor stub
		this.first = first;
		this.mid = mid;
		this.last = last;
	}

	private String first;
	private String mid;
	private String last;

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

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

}
