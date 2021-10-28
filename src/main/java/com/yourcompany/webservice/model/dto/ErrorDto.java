package com.yourcompany.webservice.model.dto;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("errors")
public class ErrorDto {

	private Date timestamp;
	private String status;
	private String code;
	private String message;
	private List<String> errorsDetails;
	private String errorSource = "error source has not been set";

	public ErrorDto() {
		this.status = new String();
		this.message = new String();
		this.errorsDetails = new Vector<>();
		this.errorSource = new String();
		this.timestamp = new Date();
	}

	public ErrorDto(String status, String code, String message, List<String> errorsDetails, String errorSource) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.errorsDetails = errorsDetails;
		this.errorSource = errorSource;
		this.timestamp = new Date();
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
