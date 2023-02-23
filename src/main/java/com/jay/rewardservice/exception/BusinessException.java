package com.jay.rewardservice.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1787878786666L;
	private String errorMessage;
	private String errorCode;
	private HttpStatus httpStatus;

	public BusinessException(String errorMessage, String message, HttpStatus httpStatus) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = message;
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return errorCode;
	}

	public void setMessage(String message) {
		this.errorCode = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}