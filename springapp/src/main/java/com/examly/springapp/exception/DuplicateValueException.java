package com.examly.springapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateValueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateValueException(String model, String field, String value) {
		super(model+" already exists with "+field+": "+value);
	}
}
