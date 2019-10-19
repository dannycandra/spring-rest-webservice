package com.yourcompany.webservice.error.exception;

import java.util.List;

import com.yourcompany.webservice.error.ErrorCode;

public class WrongUsernameOrPasswordException extends AppBaseRuntimeException{

	private static final long serialVersionUID = 1L;

	public WrongUsernameOrPasswordException() {
		super(ErrorCode.WRONG_USERNAME_OR_PASSWORD_ERROR);
	}

	public WrongUsernameOrPasswordException(Throwable throwable) {
		super(ErrorCode.WRONG_USERNAME_OR_PASSWORD_ERROR, throwable);
	}

	public WrongUsernameOrPasswordException(List<String> errorDetails) {
		super(ErrorCode.WRONG_USERNAME_OR_PASSWORD_ERROR, errorDetails);
	}

	public WrongUsernameOrPasswordException(List<String> errorDetails, Throwable throwable) {
		super(ErrorCode.WRONG_USERNAME_OR_PASSWORD_ERROR, errorDetails, throwable);
	}
}
