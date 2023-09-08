package com.dev.web.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AopHandler {

	private static Logger logger = LogManager.getLogger(AopHandler.class);

	@AfterThrowing(pointcut = "execution(* com.dev.core.controller.*.*(..))", throwing = "ex")
	private void afterThrowInCore(JoinPoint joinPoint, Exception ex) {
		handleException(ex);
	}
	
	@AfterThrowing(pointcut = "execution(* com.dev.core.oauth.controller.*.*(..))", throwing = "ex")
	private void afterThrowInOauthServices(JoinPoint joinPoint, Exception ex) {
		handleException(ex);
	}
	
	@AfterThrowing(pointcut = "execution(* com.dev.web.*.*(..))", throwing = "ex")
	private void afterThrowInWebapp(JoinPoint joinPoint, Exception ex) {
		handleException(ex);
	}

	private void handleException(Exception ex) {
		logger.error(ex.getMessage(), ex);
	}
}
