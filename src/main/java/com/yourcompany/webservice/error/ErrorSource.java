package com.yourcompany.webservice.error;

public enum ErrorSource {

	VALIDATION_METADATA_TABLE("METADATA_TABLE"), 
	VALIDATION_CHANGES_DESCRIPTION("CHANGES_DESCRIPTION");

	private String source;

	ErrorSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}
}
