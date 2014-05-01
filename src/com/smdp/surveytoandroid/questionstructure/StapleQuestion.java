package com.smdp.surveytoandroid.questionstructure;

public class StapleQuestion extends RatingQuestion implements Questionable{

	public StapleQuestion(String name, String question, boolean required, int min,
			int max, String first, String last, String mid) {
		super(name, question, required, min, max, first, last);
		this.mid = mid;
		// TODO Auto-generated constructor stub
	}

	private String mid;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

}
