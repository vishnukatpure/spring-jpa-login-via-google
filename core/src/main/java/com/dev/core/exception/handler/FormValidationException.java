package com.dev.core.exception.handler;

public class FormValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FormValidationException(String reason) {
		super(reason);
	}
}