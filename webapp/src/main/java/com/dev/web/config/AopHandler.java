package com.dev.web.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.dev.core.dto.ResponseDTO;
import com.dev.core.enums.StatusEnum;
import com.dev.core.exception.handler.FormValidationException;

@Aspect
@Configuration
public class AopHandler {

	Logger logger = LoggerFactory.getLogger(AopHandler.class);

	@AfterThrowing(pointcut = "execution(* com.dev.core.services.*.*(..))", throwing = "ex")
	private ResponseDTO afterThrowInCore(JoinPoint joinPoint, Exception ex) {
		return handleException(ex);
	}

	private ResponseDTO handleException(Exception ex) {
		logger.error(ex.getMessage(), ex);
		StatusEnum statusEnum = StatusEnum.EXCEPTION_OCCIRED;
		String message = null;
		if (ex instanceof FormValidationException) {
			statusEnum = StatusEnum.VALIDATION_FAILURE;
			message = ex.getMessage();
		}
		return new ResponseDTO().status(statusEnum).message(message);
	}
}
