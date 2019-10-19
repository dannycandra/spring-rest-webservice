package com.yourcompany.webservice.error.exception;

import java.util.List;

import com.yourcompany.webservice.error.ErrorCode;

public class UnauthorizedException extends AppBaseRuntimeException{

	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
		super(ErrorCode.UNAUTHORIZED);
	}

	public UnauthorizedException(Throwable throwable) {
		super(ErrorCode.UNAUTHORIZED, throwable);
	}

	public UnauthorizedException(List<String> errorDetails) {
		super(ErrorCode.UNAUTHORIZED, errorDetails);
	}

	public UnauthorizedException(List<String> errorDetails, Throwable throwable) {
		super(ErrorCode.UNAUTHORIZED, errorDetails, throwable);
	}
}
