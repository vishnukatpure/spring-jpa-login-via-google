package com.dev.web.config;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.dev.core.dto.ResponseDTO;
import com.dev.core.enums.StatusEnum;

@Aspect
@Configuration
public class AopHandler {

	@AfterThrowing(pointcut = "execution(* com.dev.core.services.*.*(..))", throwing = "ex")
	private ResponseDTO afterThrowInCore(Exception ex) {
		return new ResponseDTO().status(StatusEnum.EXCEPTION_OCCIRED);
	}

	@AfterThrowing(pointcut = "execution(* com.dev.oauth.services.*.*(..))", throwing = "ex")
	private ResponseDTO afterThrowInoAuth(Exception ex) {
		return new ResponseDTO().status(StatusEnum.EXCEPTION_OCCIRED);
	}

	@AfterThrowing(pointcut = "execution(* com.dev.web.services.*.*(..))", throwing = "ex")
	private ResponseDTO afterThrow(Exception ex) {
		return new ResponseDTO().status(StatusEnum.EXCEPTION_OCCIRED);
	}
}
