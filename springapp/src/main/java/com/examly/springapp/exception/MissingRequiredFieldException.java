package com.examly.springapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingRequiredFieldException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MissingRequiredFieldException(String model, String field) {
		super("Missing required field "+field+" for table: "+model);
	}
}
