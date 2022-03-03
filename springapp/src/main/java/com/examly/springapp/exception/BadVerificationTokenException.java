package com.examly.springapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class BadVerificationTokenException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public BadVerificationTokenException(String msg) {
		super(msg);
	}

}
