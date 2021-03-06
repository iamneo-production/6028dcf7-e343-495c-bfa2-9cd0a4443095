package com.examly.springapp.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.mail.AuthenticationFailedException;
import javax.validation.ConstraintViolationException;

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
	
	// DuplicateValueException
	@ExceptionHandler(DuplicateValueException.class)
	public ResponseEntity<?> handleDuplicateValueException(DuplicateValueException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.CONFLICT);
	}
	
	// MissingRequiredFieldException
	@ExceptionHandler(MissingRequiredFieldException.class)
	public ResponseEntity<?> handleMissingRequiredFieldException(MissingRequiredFieldException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

	// BadVerificationTokenException
	@ExceptionHandler(BadVerificationTokenException.class)
	public ResponseEntity<?> handleBadVerificationTokenException(BadVerificationTokenException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
	}

	//UnverifiedUserException
	@ExceptionHandler(UnverifiedUserException.class)
	public ResponseEntity<?> handleUnverifiedUserException(UnverifiedUserException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
	}

	//UnauthorizedAccessException
	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<?> handleUnauthorizedAccessException(UnauthorizedAccessException exc) {
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.FORBIDDEN);
	}

	// ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.NOT_FOUND);
	}

	//javax.mail.AuthenticationFailedException
	@ExceptionHandler(javax.mail.AuthenticationFailedException.class)
	public ResponseEntity<?> handleAuthenticationFailedException(AuthenticationFailedException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

	//ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exc){
		return new ResponseEntity(exc.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
	
	// TODO: Classify more specific exceptions
	
	// Generic Exception
	@ExceptionHandler(Exception.class)
		public ResponseEntity<?> handleGenericException(Exception exc){
			return environment.equals("development") ? 
					new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)
					: new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}