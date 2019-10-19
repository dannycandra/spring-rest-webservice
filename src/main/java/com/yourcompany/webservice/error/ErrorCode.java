package com.yourcompany.webservice.error;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public enum ErrorCode {

	UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "unknown error", "unknown error"),
	WRONG_USERNAME_OR_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "wrong username or password", "wrong username or password"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "unauthorized", "unauthorized");

	@JsonIgnore
	private HttpStatus httpStatus;

	private String errorMessage;

	private String errorDetailsTemplate;

	private ErrorCode(HttpStatus httpStatus, String errorMessage, String errorDetailsTemplate) {
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.errorDetailsTemplate = errorDetailsTemplate;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getErrorCode() {
		return this.toString();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorDetailsTemplate() {
		return errorDetailsTemplate;
	}

}
