package com.yourcompany.webservice.model.dto;

import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("errors")
public class ErrorDto {

	private String code;
	private String message;
	private List<String> errorsDetails;
	private String errorSource;

	public ErrorDto() {
		code = new String();
		message = new String();
		errorsDetails = new Vector<>();
		errorSource = new String();
	}

	public ErrorDto(String code, String message, List<String> errorsDetails, String errorSource) {
		super();
		this.code = code;
		this.message = message;
		this.errorsDetails = errorsDetails;
		this.errorSource = errorSource;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrorsDetails() {
		return errorsDetails;
	}

	public void setErrorsDetails(List<String> errorsDetails) {
		this.errorsDetails = errorsDetails;
	}

	public String getErrorSource() {
		return errorSource;
	}

	public void setErrorSource(String errorSource) {
		this.errorSource = errorSource;
	}

}
