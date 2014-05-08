package com.smdp.surveytoandroid;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResultActivity extends Activity {
	Button sendEmail;
	StringBuilder results = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		sendEmail = (Button) findViewById(R.id.btnSendEmail);

		// get the hash map and iterate to get the individual answers
		Intent intent = getIntent();
		HashMap<String, ArrayList<String>> answers = (HashMap<String, ArrayList<String>>) intent
				.getSerializableExtra("answers");
		for (HashMap.Entry<String, ArrayList<String>> entry : answers.entrySet()) {
			String key = entry.getKey();
			ArrayList<String> values = entry.getValue();
			//loop through the array list of answers and get the values
			String result = "";
			for (int i = 0; i < values.size(); i++) {
				String value = values.get(i);
				result +=value + " ";
			}
			String question = "Question: " + key + "\n" + "Answer: " + result
					+ "\n";
			Log.e("Results", question);
			results.append(question + "\n");

		}

		sendEmail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL,
						new String[] { "maiva1337@gmail.com" });
				i.putExtra(Intent.EXTRA_SUBJECT, "Survey results Ask language");
				i.putExtra(Intent.EXTRA_TEXT, results.toString());
				try {
					startActivity(Intent.createChooser(i, "Send mail..."));
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(ResultActivity.this,
							"There are no email clients installed.",
							Toast.LENGTH_SHORT).show();
				}

			}

		});

	}

}
