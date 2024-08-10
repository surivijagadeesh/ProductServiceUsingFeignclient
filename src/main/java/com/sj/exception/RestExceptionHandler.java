package com.sj.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomException exception){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode(exception.getErrorCode());
		errorResponse.setErrorMessage(exception.getErrorMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatusCode.valueOf(exception.getStatusCode()));
		
	}

}
