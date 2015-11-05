package com.emrekoca.avalon.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class PerformTimingAdvice {
	public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable {
		long startTime = System.nanoTime();
		try {
			Object returnedValue = method.proceed();
			return returnedValue;
		} finally {
			long endTime = System.nanoTime();
			double timeTaken = (double) (endTime - startTime) / 1000000000.0;
			System.out.println("The method " + method.getSignature().getName() 
					+ " took " + timeTaken + " seconds");
		}
	}

	public void beforeAdviceTesting(JoinPoint jp)
	{
		//BookService b = (BookService)jp.getTarget();
		//System.out.println(b.getEntireCatalogue());
		System.out.println("Now entering a method . . . " + jp.getSignature());
	}
}