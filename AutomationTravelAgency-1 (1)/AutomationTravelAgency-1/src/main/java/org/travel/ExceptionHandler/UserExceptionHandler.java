package org.travel.ExceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.travel.exception.InvalidLoginException;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { UserNotFoundException.class })
	protected ResponseEntity<Object> handleConflict1(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "User with given id is not present";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { UserNameAlreadyExistsException.class })
	protected ResponseEntity<Object> handleConflict2(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "User name is already taken.Try new one";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { InvalidLoginException.class })
	protected ResponseEntity<Object> handleConflict3(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "UserName or Password is invalid";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
