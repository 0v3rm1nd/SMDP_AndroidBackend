package com.smdp.surveytoandroid.questionstructure;

public class Staple extends RatingQuestion{
	private String mid;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public Staple(String name, String question, boolean required, int min,
			int max, String first, String last, String mid) {
		super(name, question, required, min, max, first, last);
		// TODO Auto-generated constructor stub
		this.mid = mid;
	}
	

}
