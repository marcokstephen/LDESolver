package com.sm.ldesolver;

public class SolveEEA{
 public static long[] main(String[] args){
   long a = Long.parseLong(args[0]);
   long b = Long.parseLong(args[1]);
   long c = Long.parseLong(args[2]);

   long gcd = GetGCD.findGCD(Math.abs(a), Math.abs(b));
   long factor = c / gcd; //for multiplying x,y solutions by after the LDE has been solved
   
   return computeEquation(a, b, c, factor); //solves the LDE
 }
 
 public static long[] computeEquation(long a, long b, long c, long factor){
   long originalA = a;
   long originalB = b; //these will be more useful if I ever decide to show calculation steps to the user, for now they are mostly redundant
   a = Math.abs(a);
   b = Math.abs(b);
   long[] x;
   long[] y;
   long r = a;
   long q = 0;
   Boolean aNegative = false;
   Boolean bNegative = false;
   long[] outputArrayIndicatingSign = new long[2]; //int[0] indicates x, int[1] indicates y
   
   if (originalA < 0){
     aNegative = true;
   }
   if (originalB < 0){
     bNegative = true;
   }
   
   x = new long[3];
   y = new long[3]; //where the last spot in each array is used as a placeholder for swapping variables
   x[0] = 1; //setting initial values for the application of EEA
   y[0] = 0;
   x[1] = 0;
   y[1] = 1;
   
   while (r != 0){ //this makes sense if you follow along with an extended euclidean algorithm table
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
   }
      
   if (aNegative && bNegative){
	   outputArrayIndicatingSign[0] = factor * x[0] * -1;
	   outputArrayIndicatingSign[1] = factor * y[0] * -1;
	   return outputArrayIndicatingSign;
   }
   else if (aNegative){
	   outputArrayIndicatingSign[0] = factor * x[0] * -1;
	   outputArrayIndicatingSign[1] = factor * y[0];
	   return outputArrayIndicatingSign;
   }
   else if (bNegative){
	  outputArrayIndicatingSign[0] = factor * x[0];
	   outputArrayIndicatingSign[1] = factor * y[0] * -1;
	   return outputArrayIndicatingSign;
   }
   else{
	   outputArrayIndicatingSign[0] = factor * x[0];
	   outputArrayIndicatingSign[1] = factor * y[0];
	   return outputArrayIndicatingSign;
   }
 }
}