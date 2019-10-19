package com.yourcompany.webservice.error.exception;

import java.util.List;
import java.util.Vector;

import com.yourcompany.webservice.error.ErrorCode;
import com.yourcompany.webservice.error.ErrorSource;

/**
 * Apps Exception for webservices
 * 
 * @author Danny Candra
 */
public class AppBaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4135450097965900640L;

	private ErrorCode error;

	private ErrorSource errorSource;

	private List<String> errorDetails = new Vector<>();

	/**
	 * AppsException<br>
	 * 
	 * @param errorCode {@link ErrorCode}
	 */
	public AppBaseRuntimeException(ErrorCode errorCode) {
		super(errorCode.getErrorMessage());
		this.error = errorCode;
	}

	/**
	 * AppsException<br>
	 * 
	 * @param errorCode {@link ErrorCode}
	 * @param throwable {@link Throwable}
	 */
	public AppBaseRuntimeException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode.getErrorMessage(), throwable);
		this.error = errorCode;
	}

	/**
	 * AppsException<br>
	 * 
	 * @param errorCode    {@link ErrorCode}
	 * @param errorDetails error details
	 */
	public AppBaseRuntimeException(ErrorCode errorCode, List<String> errorDetails) {
		this(errorCode);
		this.errorDetails = errorDetails;
	}

	/**
	 * AppsException<br>
	 * 
	 * @param errorCode {@link ErrorCode}
	 * @param throwable {@link Throwable}
	 */
	public AppBaseRuntimeException(ErrorCode errorCode, List<String> errorDetails, Throwable throwable) {
		this(errorCode, throwable);
		this.errorDetails = errorDetails;
	}

	/**
	 * AppsException<br>
	 * 
	 * @param errorCode    {@link ErrorCode}
	 * @param errorDetails {@link List} of String that describe the error
	 * 
	 * @param errorSouce   {@link ErrorSource}
	 */
	public AppBaseRuntimeException(ErrorCode errorCode, List<String> errorDetails, ErrorSource errorSouce) {
		this(errorCode);
		this.errorDetails = errorDetails;
		this.errorSource = errorSouce;
	}

	/**
	 * AppsException<br>
	 * 
	 * @param errorCode    {@link ErrorCode}
	 * @param errorDetails {@link List} of String that describe the error
	 * 
	 * @param errorSouce   {@link ErrorSource}
	 * @param throwable    {@link Throwable}
	 */
	public AppBaseRuntimeException(ErrorCode errorCode, List<String> errorDetails, ErrorSource errorSouce,
			Throwable throwable) {
		this(errorCode);
		this.errorDetails = errorDetails;
		this.errorSource = errorSouce;
	}

	public ErrorCode getError() {
		return error;
	}

	public List<String> getErrorDetails() {
		return errorDetails;
	}

	public ErrorSource getErrorSource() {
		return errorSource;
	}

	public void setErrorDetails(List<String> errorDetails) {
		this.errorDetails = errorDetails;
	}

	public void setErrorSource(ErrorSource errorSource) {
		this.errorSource = errorSource;
	}

}
