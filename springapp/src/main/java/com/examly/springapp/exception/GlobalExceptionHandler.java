package com.examly.springapp.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

	private static final long serialVersionUID = 1L;
	@Value("${environment}")
	String environment;
	
	// BadCredentialsException
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	// Duplicate Value Exception
	@ExceptionHandler(DuplicateValueException.class)
	public ResponseEntity<?> handleDuplicateValueException(DuplicateValueException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.CONFLICT);
	}
	
	// Missing Required Field
	@ExceptionHandler(MissingRequiredFieldException.class)
	public ResponseEntity<?> handleMissingRequiredFieldException(MissingRequiredFieldException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
	
	// TODO: Classify more specific exceptions
	
	// Generic Exception
	@ExceptionHandler(Exception.class)
		public ResponseEntity<?> handleGenericException(Exception exc){
			return environment.equals("development") ? 
					new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)
					: null;
					// : ResponseEntity.internalServerError().body("Something Went Wrong");
	}
}





