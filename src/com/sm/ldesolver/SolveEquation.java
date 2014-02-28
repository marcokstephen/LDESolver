package com.sm.ldesolver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

public class SolveEquation extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solve_equation);
		TextView gcdIntroTextView = (TextView) findViewById(R.id.gcdIntroTextView);
		TextView gcdTextView = (TextView) findViewById(R.id.gcdTextView);
		TextView oneSolutionTextView = (TextView) findViewById(R.id.oneSolutionTextView);
		TextView fullSolutionTextView = (TextView) findViewById(R.id.fullSolutionTextView);
		TextView explanationTextView = (TextView) findViewById(R.id.explanationTextView);
		TextView fullSolutionBigTextView = (TextView) findViewById(R.id.fullSolutionBigTextView);
		
		Intent intent = getIntent();
		//bundle contains the three inputs from before in an array of Strings
		Bundle coeffs = intent.getExtras();
		String coeffArray = null;
		String[] array = coeffs.getStringArray(coeffArray);
		long a = Long.parseLong(array[0]);
		long b = Long.parseLong(array[1]);
		long c = Long.parseLong(array[2]);
		long gcd = GetGCD.findGCD(Math.abs(a), Math.abs(b));
		long[] EEAanswerArray = SolveEEA.main(array);
		
		gcdIntroTextView.setText(Html.fromHtml("The <B>GCD</B> of " + array[0] + " and " + array[1] + " is:"));
		gcdTextView.setText("" + gcd);
		
		if (isPossibleSolution(c, gcd)){
			//oneSolutionTextView.setText(EEAanswer);
			oneSolutionTextView.setText("A possible x-value is " + EEAanswerArray[0] + " and a possible y-value is " + EEAanswerArray[1] + ".\n");
			fullSolutionTextView.setText(Html.fromHtml("\nThe <B>full solution</B> is as follows:"));
			fullSolutionBigTextView.setText("x = " + EEAanswerArray[0] + " + " + b/gcd + "n \ny = " + EEAanswerArray[1] + " - " + a/gcd + "n");
			explanationTextView.setText("\n\nfor all integers, n."); //only used to explain why there is no solution in the event that there isn't
		}else{
			oneSolutionTextView.setText(Html.fromHtml("<B>No solution to the LDE!</B>"));
			fullSolutionTextView.setText("" + GetGCD.findGCD(a, b) + " is not a factor of " + c + ".");
			explanationTextView.setText("To have a solution, the GCD must be a factor of c!");
			fullSolutionBigTextView.setText("");
		}
		
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	public boolean isPossibleSolution(long c, long gcd){
		if ((c % gcd) != 0){ //LDE has solution <==> gcd(a,b) divides c
			return false;
		}
		else{
			return true;
		}
	}

	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
