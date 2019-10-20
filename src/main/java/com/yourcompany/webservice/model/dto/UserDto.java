package com.yourcompany.webservice.model.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userId;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	private String status;

	private UserDto createdByUser;

	private UserDto lastModifiedByUser;

	private Date createdDate;

	private Date modifiedDate;

	public UserDto() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDto getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(UserDto createdByUser) {
		this.createdByUser = createdByUser;
	}

	public UserDto getLastModifiedByUser() {
		return lastModifiedByUser;
	}

	public void setLastModifiedByUser(UserDto lastModifiedByUser) {
		this.lastModifiedByUser = lastModifiedByUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}