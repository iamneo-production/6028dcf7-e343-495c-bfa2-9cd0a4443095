package com.examly.springapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnverifiedUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UnverifiedUserException(String msg) {
		super(msg);
	}
}