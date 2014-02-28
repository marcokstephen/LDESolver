package com.sm.ldesolver;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
		
	public Object solveEq(View view){
		Intent intent = new Intent (this, SolveEquation.class);
		EditText a_coefficient = (EditText) findViewById(R.id.a_coeff);
		EditText b_coefficient = (EditText) findViewById(R.id.b_coeff);
		EditText c_coefficient = (EditText) findViewById(R.id.c_coeff);
		String a_string = a_coefficient.getText().toString();
		String b_string = b_coefficient.getText().toString();
		String c_string = c_coefficient.getText().toString();
		long a,b;
		
		try{
			a = Long.parseLong(a_string);
			b = Long.parseLong(b_string);
		}catch(NumberFormatException e){
			
			new AlertDialog.Builder(this)
		    .setTitle("Input Error")
		    .setMessage("There are not three integers!")
		    .setCancelable(true)
		    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int which) { 
		    		dialog.cancel();
		    	}
		    })
		    .show();
			return null;
		}

		if((a==0)&&(b==0)){
			new AlertDialog.Builder(this)
		    .setTitle("Impossible GCD!")
		    .setMessage("Either the a coefficient or b coefficient must be non-zero.")
		    .setCancelable(true)
		    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int which) { 
		    		dialog.cancel();
		    	}
		    })
		    .show();
			return null;
		}
		else if(b==0){
			new AlertDialog.Builder(this)
		    .setTitle("Division By Zero")
		    .setMessage("Please place the zero in the a-coefficient.")
		    .setCancelable(true)
		    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int which) { 
		    		dialog.cancel();
		    	}
		    })
		    .show();
			return null;
		}
		
		{
		//bundle contains the three coefficients of type String
		Bundle coeffs = new Bundle();
		String coeffArray = null;
		coeffs.putStringArray(coeffArray, new String[]{a_string, b_string, c_string});
		intent.putExtras(coeffs);

		startActivity(intent);
		return null;
		}
	}
	
	
	public Object solveWithSolution(View view){
		Intent intent = new Intent (this, SolveWithSolution.class);
		EditText a_coefficient = (EditText) findViewById(R.id.a_coeff);
		EditText b_coefficient = (EditText) findViewById(R.id.b_coeff);
		EditText c_coefficient = (EditText) findViewById(R.id.c_coeff);
		String a_string = a_coefficient.getText().toString();
		String b_string = b_coefficient.getText().toString();
		String c_string = c_coefficient.getText().toString();
		Long a,b;
		
		try{
			a = Long.parseLong(a_string);
			b = Long.parseLong(b_string);
		}catch(NumberFormatException e){
			
			new AlertDialog.Builder(this)
		    .setTitle("Input Error")
		    .setMessage("There are not three integers!")
		    .setCancelable(true)
		    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int which) { 
		    		dialog.cancel();
		    	}
		    })
		    .show();
			return null;
		}

		if((a==0)&&(b==0)){
			new AlertDialog.Builder(this)
		    .setTitle("Impossible GCD!")
		    .setMessage("Either the a coefficient or b coefficient must be non-zero.")
		    .setCancelable(true)
		    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int which) { 
		    		dialog.cancel();
		    	}
		    })
		    .show();
			return null;
		}
		else if(b==0){
			new AlertDialog.Builder(this)
		    .setTitle("Division By Zero")
		    .setMessage("Please place the zero in the a-coefficient.")
		    .setCancelable(true)
		    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int which) { 
		    		dialog.cancel();
		    	}
		    })
		    .show();
			return null;
		}
		
		{
		//bundle contains the three coefficients of type String
		Bundle coeffs = new Bundle();
		String coeffArray = null;
		coeffs.putStringArray(coeffArray, new String[]{a_string, b_string, c_string});
		intent.putExtras(coeffs);

		startActivity(intent);
		return null;
		}
	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button resetButton = (Button) findViewById(R.id.resetButton);
						
		resetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText a_coefficient = (EditText) findViewById(R.id.a_coeff);
				EditText b_coefficient = (EditText) findViewById(R.id.b_coeff);
				EditText c_coefficient = (EditText) findViewById(R.id.c_coeff);
				a_coefficient.setText("");
				b_coefficient.setText("");
				c_coefficient.setText("");
			}
			
			});		
		}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
