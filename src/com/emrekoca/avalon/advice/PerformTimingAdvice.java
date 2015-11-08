package com.emrekoca.avalon.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PerformTimingAdvice {
	@Pointcut("execution ( * com.emrekoca.avalon.services.*.* (..) )")
	public void allServiceMethodThatReturnAnything(){}

	@Around("allServiceMethodThatReturnAnything()")
	public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable {
		long startTime = System.nanoTime();
		try {
			Object returnedValue = method.proceed();
			return returnedValue;
		} finally {
			long endTime = System.nanoTime();
			double timeTaken = (double) (endTime - startTime) / 1000000000.0;
			System.out.println("The method " + method.getSignature().getName() + " took " + timeTaken + " seconds");
		}
	}

	@Before(value = "execution ( java.util.List com.emrekoca.avalon.services.*.* (..) )")
	public void beforeAdviceTesting(JoinPoint jp) {
		// BookService b = (BookService)jp.getTarget();
		// System.out.println(b.getEntireCatalogue());
		System.out.println("Now entering a method . . . " + jp.getSignature());
	}
}