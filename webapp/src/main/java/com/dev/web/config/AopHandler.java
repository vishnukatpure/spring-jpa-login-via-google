package com.dev.web.config;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AopHandler {

	@AfterThrowing(pointcut = "execution(* com.dev.core.services.*.*(..))"
			+ "|| execution(* com.dev.web.services.*.*(..))"
			+ "|| execution(* com.dev.oauth.services.*.*(..))", throwing = "ex")
	private String afterThrow(Exception ex) {
		return "EXCEPTION_OCCIRED";
	}
}
