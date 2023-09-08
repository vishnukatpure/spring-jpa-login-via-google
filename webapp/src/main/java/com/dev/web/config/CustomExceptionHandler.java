package com.dev.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dev.core.custom.exception.BadRequestException;
import com.dev.core.custom.exception.FormValidationException;
import com.dev.core.dto.ResponseDTO;
import com.dev.core.enums.StatusEnum;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDTO> handleException(Exception ex) {
		String msg = null;
		StatusEnum status = StatusEnum.EXCEPTION_OCCIRED;
		if (ex instanceof FormValidationException) {
			status = StatusEnum.VALIDATION_FAILURE;
			msg = ex.getMessage();
		}
		if (ex instanceof BadRequestException) {
			status = StatusEnum.EXCEPTION_OCCIRED;
			msg = ex.getMessage();
		}

		ResponseDTO dto = new ResponseDTO().status(status).message(msg);
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(dto);
	}
}
