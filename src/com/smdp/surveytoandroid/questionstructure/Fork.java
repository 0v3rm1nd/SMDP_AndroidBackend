package com.smdp.surveytoandroid.questionstructure;

import java.util.ArrayList;
import java.util.HashMap;

//will represent the forks
public class Fork {
	// will represent the binding between choices and questions for the fork
	// applicable to multiple choice, ranking, constant sum
	public HashMap<String, ArrayList<Questionable>> bindingChoiceQuest = new HashMap<String, ArrayList<Questionable>>();
	// applicable to stable and rating
	public ArrayList<Questionable> forkQues = new ArrayList<Questionable>();

	public Fork() {
		super();
	}

	public Fork(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}

	private int min;
	private int max;

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

}
