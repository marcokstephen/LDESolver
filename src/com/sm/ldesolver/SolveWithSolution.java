package com.sm.ldesolver;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.Html;


public class SolveWithSolution extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solve_with_solution);

		TextView gcdIntroTextView = (TextView) findViewById(R.id.gcdIntroTextView);
		TextView gcdTextView = (TextView) findViewById(R.id.gcdTextView);
		TableLayout eeaStepsTableLayout = (TableLayout) findViewById(R.id.eeaStepsTableLayout);
	
		Intent intent = getIntent();
		//bundle contains the three inputs from before in an array of Strings
		Bundle coeffs = intent.getExtras();
		String coeffArray = null;
		String[] array = coeffs.getStringArray(coeffArray); //array of ("a", "b", "c")
		long a = Long.parseLong(array[0]);
		long b = Long.parseLong(array[1]);
		long c = Long.parseLong(array[2]);
		long gcd = GetGCD.findGCD(Math.abs(a), Math.abs(b));
		long factor = c/gcd;

		gcdIntroTextView.setText(Html.fromHtml("The <B>GCD</B> of " + array[0] + " and " + array[1] + " is:"));
		gcdTextView.setText("" + gcd);
		
		gcdDisplaySteps(eeaStepsTableLayout, Math.abs(a), Math.abs(b));
		
		TextView emptyTextView = new TextView(this);
		TableRow emptyTableRow = new TableRow(this);
		emptyTextView.setText(" ");
		emptyTableRow.addView(emptyTextView);
		eeaStepsTableLayout.addView(emptyTableRow);
		
		TextView isThereSolution = new TextView (this);
		TableRow isThereSolutionRow = new TableRow (this);
		
		if (c%gcd == 0)
		{
			if (factor == 1)
			{
				isThereSolution.setText(Html.fromHtml("\nThere <B>is</B> a solution to the LDE, " +
						"because " + gcd + " is a factor of " + c+". <B>Find a solution to:</B> "));
			}else{
				isThereSolution.setText(Html.fromHtml("\nThere <B>is</B> a solution to the LDE, " +
						"because " + gcd + " is a factor of " + c+". <B>First, find a solution to:</B> "));
			}
			isThereSolution.setTextSize(18f);
			isThereSolutionRow.addView(isThereSolution);
			eeaStepsTableLayout.addView(isThereSolutionRow);
			
			TableRow simpleTableRow = new TableRow(this);
			TextView simpleEEAProblem = new TextView(this);
			simpleEEAProblem.setText(Html.fromHtml("" + a + "<I>x</I> + " + b + "<I>y</I> = " + gcd));
			simpleEEAProblem.setTypeface(null, Typeface.BOLD);
			simpleEEAProblem.setTextSize(18f);
			simpleEEAProblem.setGravity(Gravity.CENTER_HORIZONTAL);
			simpleTableRow.addView(simpleEEAProblem);
			eeaStepsTableLayout.addView(simpleTableRow);
			
		
			TextView newEmptyView = new TextView(this);
			TableRow newEmptyTableRow = new TableRow(this);
			newEmptyView.setText(" ");
			newEmptyTableRow.addView(newEmptyView);
			eeaStepsTableLayout.addView(newEmptyTableRow);
						
			long[] EEAanswerArray = createEEATable(eeaStepsTableLayout, a, b, c, factor);
			
			TextView oneSolutionTextView = new TextView(this);
			TextView fullSolutionTextView = new TextView(this);
			TextView fullSolutionYTextView = new TextView(this);
			TextView blankTextView = new TextView(this);
			blankTextView.setText(" ");
			TableRow blankTableRow = new TableRow(this);
			blankTableRow.addView(blankTextView);
			eeaStepsTableLayout.addView(blankTableRow);
			oneSolutionTextView.setText("Solution: (" + a + ")(" + EEAanswerArray[0] + ") + (" + b + ")(" + EEAanswerArray[1] + ") = " + gcd);
			oneSolutionTextView.setTextSize(17f);
			fullSolutionTextView.setText("x = " + factor*EEAanswerArray[0] + " + " + b/gcd + "n");
			fullSolutionTextView.setTextSize(17f);
			fullSolutionTextView.setGravity(Gravity.CENTER_HORIZONTAL);
			fullSolutionYTextView.setText("y = " + factor*EEAanswerArray[1] + " - " + a/gcd + "n");
			fullSolutionYTextView.setTextSize(17f);
			fullSolutionYTextView.setGravity(Gravity.CENTER_HORIZONTAL);
			TableRow oneSolutionTableRow = new TableRow(this);
			TableRow fullSolutionTableRow = new TableRow(this);
			TableRow fullSolutionYTableRow = new TableRow(this);
			oneSolutionTableRow.addView(oneSolutionTextView);
			fullSolutionTableRow.addView(fullSolutionTextView);
			fullSolutionYTableRow.addView(fullSolutionYTextView);
			eeaStepsTableLayout.addView(oneSolutionTableRow);
			
			if (factor != 1){
				TableRow ifFactorNotOneTableRow = new TableRow(this);
				TextView ifFactorNotOneTextView = new TextView (this);
				ifFactorNotOneTextView.setText("Now multiply this solution by the factor " + factor + ", because " + c + " / " + gcd + " = " + factor+".");
				ifFactorNotOneTextView.setTextSize(17f);
				ifFactorNotOneTableRow.addView(ifFactorNotOneTextView);
				eeaStepsTableLayout.addView(ifFactorNotOneTableRow);
				
				TextView oneSolutionFactorTextView = new TextView(this);
				TableRow oneSolutionFactorTableRow = new TableRow(this);
				oneSolutionFactorTextView.setText("A Solution: (" + a + ")(" + factor*EEAanswerArray[0] + ") + (" + b + ")(" + factor*EEAanswerArray[1] + ") = " + c);
				oneSolutionFactorTextView.setTypeface(null, Typeface.BOLD);
				oneSolutionFactorTextView.setTextSize(17f);
				oneSolutionFactorTableRow.addView(oneSolutionFactorTextView);
				eeaStepsTableLayout.addView(oneSolutionFactorTableRow);				
			} //no need for an else
			
			TextView blankTV = new TextView(this);
			TableRow blankTR = new TableRow(this);
			blankTV.setText(" ");
			blankTR.addView(blankTV);
			eeaStepsTableLayout.addView(blankTR);
			
			TextView fullSolutionHeader = new TextView(this);
			fullSolutionHeader.setText("Full Solution:");
			fullSolutionHeader.setTypeface(null, Typeface.BOLD);
			fullSolutionHeader.setTextSize(17f);
			TableRow fullSolutionTableRow2 = new TableRow(this);
			fullSolutionTableRow2.addView(fullSolutionHeader);
			eeaStepsTableLayout.addView(fullSolutionTableRow2);
			
			eeaStepsTableLayout.addView(fullSolutionTableRow);
			eeaStepsTableLayout.addView(fullSolutionYTableRow);
			
			TextView conclusionTextView = new TextView(this);
			TableRow conclusionTableRow = new TableRow(this);
			conclusionTextView.setText("for all integers, n.");
			conclusionTextView.setTextSize(17f);
			conclusionTableRow.addView(conclusionTextView);
			eeaStepsTableLayout.addView(conclusionTableRow);
		}
		else
		{
			isThereSolution.setText(Html.fromHtml("\nThere is <B>no solution</B> to the LDE, " +
					"because the GCD is not a factor of " + c +"."));
			isThereSolution.setTextSize(18f);
			isThereSolutionRow.addView(isThereSolution);
			eeaStepsTableLayout.addView(isThereSolutionRow);
		}
		
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	public long[] createEEATable(TableLayout eeaStepsTableLayout, long a, long b, long c, long factor){
		long originalA = a;
		long originalB = b; //these will be more useful if I ever decide to show calculation steps to the user, for now they are mostly redundant
		a = Math.abs(a);
		b = Math.abs(b);
		boolean aNeg;
		boolean bNeg;
		long[] x = new long[3];
		long[] y = new long[3];
		long r = a;
		long q = 0;
		long[] outputArrayIndicatingSign = new long[2]; //int[0] indicates x, int[1] indicates y
		int recursiveStep = 0;
		
		aNeg = originalA < 0 ? true : false;
		bNeg = originalB < 0 ? true : false;
		
		x[0] = 1; //setting initial values for the application of EEA
		y[0] = 0;
		x[1] = 0;
		y[1] = 1;
		
		LayoutParams tvPar = new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT, 1f);
		TableRow FirstTableRow = new TableRow(this);
		TextView FirstTableValue = new TextView(this);
		TextView SecondTableValue = new TextView(this);
		TextView ThirdTableValue = new TextView(this);
		TextView FourthTableValue=  new TextView(this);
		FirstTableValue.setText(Html.fromHtml("<B>x</B>"));
		FirstTableValue.setLayoutParams(tvPar);
		FirstTableValue.setTextSize(17f);
		SecondTableValue.setText(Html.fromHtml("<B>y</B>"));
		SecondTableValue.setLayoutParams(tvPar);
		SecondTableValue.setTextSize(17f);
		ThirdTableValue.setText(Html.fromHtml("<B>q</B>"));
		ThirdTableValue.setLayoutParams(tvPar);
		ThirdTableValue.setTextSize(17f);
		FourthTableValue.setText(Html.fromHtml("<B>r</B>"));
		FourthTableValue.setLayoutParams(tvPar);
		FourthTableValue.setTextSize(17f);
		FirstTableRow.addView(FirstTableValue);
		FirstTableRow.addView(SecondTableValue);
		FirstTableRow.addView(ThirdTableValue);
		FirstTableRow.addView(FourthTableValue);
		
		TableRow SecondTableRow = new TableRow(this);
		
		TextView secondRowFirstValue = new TextView(this);
		TextView secondRowSecondValue = new TextView(this);
		TextView secondRowThirdValue = new TextView(this);
		TextView secondRowFourthValue = new TextView(this);
		secondRowFirstValue.setText("1");
		secondRowFirstValue.setLayoutParams(tvPar);
		secondRowFirstValue.setTextSize(17f);
		secondRowSecondValue.setText("0");
		secondRowSecondValue.setLayoutParams(tvPar);
		secondRowSecondValue.setTextSize(17f);
		secondRowThirdValue.setText("-");
		secondRowThirdValue.setLayoutParams(tvPar);
		secondRowThirdValue.setTextSize(17f);
		secondRowFourthValue.setText("" + a);
		secondRowFourthValue.setLayoutParams(tvPar);
		secondRowFourthValue.setTextSize(17f);
		SecondTableRow.addView(secondRowFirstValue);
		SecondTableRow.addView(secondRowSecondValue);
		SecondTableRow.addView(secondRowThirdValue);
		SecondTableRow.addView(secondRowFourthValue);
		
		TableRow ThirdTableRow = new TableRow(this);
		
		TextView thirdRowFirstValue = new TextView(this);
		thirdRowFirstValue.setText("0");
		thirdRowFirstValue.setLayoutParams(tvPar);
		thirdRowFirstValue.setTextSize(17f);
		TextView thirdRowSecondValue = new TextView(this);
		thirdRowSecondValue.setText("1");
		thirdRowSecondValue.setLayoutParams(tvPar);
		thirdRowSecondValue.setTextSize(17f);
		TextView thirdRowThirdValue = new TextView(this);
		thirdRowThirdValue.setText("-");
		thirdRowThirdValue.setLayoutParams(tvPar);
		thirdRowThirdValue.setTextSize(17f);
		TextView thirdRowFourthValue = new TextView(this);
		thirdRowFourthValue.setText("" + b);
		thirdRowFourthValue.setLayoutParams(tvPar);
		thirdRowFourthValue.setTextSize(17f);
		ThirdTableRow.addView(thirdRowFirstValue);
		ThirdTableRow.addView(thirdRowSecondValue);
		ThirdTableRow.addView(thirdRowThirdValue);
		ThirdTableRow.addView(thirdRowFourthValue);
		
		
		eeaStepsTableLayout.addView(FirstTableRow);
		eeaStepsTableLayout.addView(SecondTableRow);
		eeaStepsTableLayout.addView(ThirdTableRow);
		
		
		while (r != 0){ //this makes sense if you follow along with an extended euclidean algorithm table
		     if (recursiveStep > 0){
		    	 TableRow nextRow = new TableRow (this);
		    	 TextView xValue = new TextView (this);
		    	 TextView yValue = new TextView (this);
		    	 TextView qValue = new TextView (this);
		    	 TextView rValue = new TextView (this);
		    	 
		    	 xValue.setText("" + x[1]);
		    	 xValue.setLayoutParams(tvPar);
		    	 xValue.setTextSize(17f);
		    	 yValue.setText("" + y[1]);
		    	 yValue.setLayoutParams(tvPar);
		    	 yValue.setTextSize(17f);
		    	 qValue.setText("" + q);
		    	 qValue.setLayoutParams(tvPar);
		    	 qValue.setTextSize(17f);
		    	 rValue.setText("" + r);
		    	 rValue.setLayoutParams(tvPar);
		    	 rValue.setTextSize(17f);
		    	 nextRow.addView(xValue);
		    	 nextRow.addView(yValue);
		    	 nextRow.addView(qValue);
		    	 nextRow.addView(rValue);
		    	 eeaStepsTableLayout.addView(nextRow);
		     }
		     q = a/b; //q represents quotient
		     r = a%b; //r represents remainder
		     x[2] = x[1]; //placeholder
		     y[2] = y[1]; //placeholder
		     x[1] = x[0] - x[1] * q;
		     x[0] = x[2];
		     y[1] = y[0] - y[1] * q;
		     y[0] = y[2];
		     a = b; //
		     b = r;
		     recursiveStep++;
		   }
		
		TableRow FinalRow = new TableRow(this);
		TextView xFinal = new TextView(this);
		xFinal.setText(""+x[1]);
		xFinal.setLayoutParams(tvPar);
		xFinal.setTextSize(17f);
		TextView yFinal = new TextView(this);
		yFinal.setText("" + y[1]);
		yFinal.setLayoutParams(tvPar);
		yFinal.setTextSize(17f);
		TextView qFinal = new TextView(this);
		qFinal.setText("" + q);
		qFinal.setLayoutParams(tvPar);
		qFinal.setTextSize(17f);
		TextView rFinal = new TextView(this);
		rFinal.setText(""+r);
		rFinal.setLayoutParams(tvPar);
		rFinal.setTextSize(17f);
		FinalRow.addView(xFinal);
		FinalRow.addView(yFinal);
		FinalRow.addView(qFinal);
		FinalRow.addView(rFinal);
		eeaStepsTableLayout.addView(FinalRow);		
		
		if (aNeg && bNeg){
			   outputArrayIndicatingSign[0] = x[0] * -1;
			   outputArrayIndicatingSign[1] = y[0] * -1;
			   return outputArrayIndicatingSign;
		   }
		   else if (aNeg){
			   outputArrayIndicatingSign[0] = x[0] * -1;
			   outputArrayIndicatingSign[1] = y[0];
			   return outputArrayIndicatingSign;
		   }
		   else if (bNeg){
			  outputArrayIndicatingSign[0] = x[0];
			   outputArrayIndicatingSign[1] = y[0] * -1;
			   return outputArrayIndicatingSign;
		   }
		   else{
			   outputArrayIndicatingSign[0] = x[0];
			   outputArrayIndicatingSign[1] = y[0];
			   return outputArrayIndicatingSign;
		   }
		
	}
	
	public Object gcdDisplaySteps(TableLayout eeaStepsTableLayout, long a, long b){
		if (b==0)
		{
			return null;
		}
		else
		{
			TableRow currentStep = new TableRow (this);
			TextView gcdStep = new TextView (this);
			gcdStep.setText(""+a+" = (" + a/b + ")(" + b + ") + " + a%b);
			gcdStep.setTextSize(17f);
			currentStep.addView(gcdStep);
			eeaStepsTableLayout.addView(currentStep);
			return gcdDisplaySteps(eeaStepsTableLayout, b, a%b);
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
