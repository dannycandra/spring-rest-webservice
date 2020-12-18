package com.yourcompany.webservice.error;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public enum ErrorCode {

	UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "unknown error", "unknown error has been occured, check server log for details"),
	WRONG_USERNAME_OR_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "wrong username or password",
			"wrong username or password, please check if you have the right access data"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "unauthorized", "you are not authorized to access this resource, please authorize first and try again");

	@JsonIgnore
	private HttpStatus httpStatus;

	private String errorMessage;

	private String errorMessageDetails;

	private ErrorCode(HttpStatus httpStatus, String errorMessage, String errorMessageDetails) {
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.errorMessageDetails = errorMessageDetails;
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

	public String getErrorMessageDetails() {
		return errorMessageDetails;
	}

}
