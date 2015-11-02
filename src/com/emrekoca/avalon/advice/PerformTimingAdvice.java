package com.emrekoca.avalon.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PerformTimingAdvice implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		long startTime = System.nanoTime();
		try {
			Object returnedValue = method.proceed();
			return returnedValue;
		} finally {
			long endTime = System.nanoTime();
			double timeTaken = (double)(endTime - startTime) / 1000000000.0 ;
			System.out.println("The method " 
					+ method.getMethod().getName() 
					+ " took " + timeTaken + " seconds");
		}
	}
}