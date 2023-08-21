package com.dev.core.exception.handler;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BadRequestException(String reason) {
		super(reason);
	}
}
