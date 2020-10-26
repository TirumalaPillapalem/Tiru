package com.example.bean;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long userId;
	
	private String firstName;
	
	private String lastName;
	
	private Set<AddressRequest> address;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<AddressRequest> getAddress() {
		return address;
	}

	public void setAddress(Set<AddressRequest> address) {
		this.address = address;
	}

}
