package com.emrekoca.avalon.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class SimpleLoggingAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

	@Override
	public void before(Method method, Object[] args, Object targetObject) throws Throwable {
		System.out.println("Now we are about to call the " + method.getName() + " method");
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] arg2, Object targetObject) throws Throwable {
		System.out.println("Now finished calling the " + method.getName() + " method");
		/*
		if(returnValue instanceof java.util.List){
			List list = (List)returnValue;
			list.clear();
		}
		*/
		System.out.println("Now this will be returned " + returnValue + " value");
	}
}
