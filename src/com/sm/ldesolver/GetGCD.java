package com.sm.ldesolver;

public class GetGCD {
	public static String main(String[] args){
		long a=Integer.parseInt(args[0]);
		long b=Integer.parseInt(args[1]);
		
		long gcd = findGCD(Math.abs(a), Math.abs(b));
		return "" + gcd;
	}
	
	public static long findGCD(long first, long second){ //uses generative recursion to find GCD
		   if (second == 0)
		   {
		     return first;
		   }
		   else{
		     return findGCD(second, (first % second));
		   }
		 }
}
