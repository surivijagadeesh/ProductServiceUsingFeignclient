package com.sj.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
	private String errorCode;
	private String errorMessage;
	private int statusCode;
	
	public CustomException() {}
	public CustomException(String errorCode, String errorMessage, int statusCode) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}
}
