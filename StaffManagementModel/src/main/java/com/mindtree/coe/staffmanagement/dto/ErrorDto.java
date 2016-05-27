package com.mindtree.coe.staffmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"error-message"})
public class ErrorDto {
	@JsonProperty("error-message")
	public String errorMessage;
	public ErrorDto() {
	
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
