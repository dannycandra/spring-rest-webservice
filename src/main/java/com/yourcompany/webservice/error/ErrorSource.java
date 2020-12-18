package com.yourcompany.webservice.error;

public enum ErrorSource {

	VALIDATION_USER_DATA, VALIDATION_CHANGES_DESCRIPTION;

	public String getSource() {
		return this.name();
	}
}
