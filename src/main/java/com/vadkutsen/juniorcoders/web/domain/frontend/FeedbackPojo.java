package com.vadkutsen.juniorcoders.web.domain.frontend;

import java.io.Serializable;

public class FeedbackPojo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String firstName;
	private String lastName;
	private String feedback;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("FeedbackPojo{");
		sb.append("email='").append(email).append('\'');
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", lastNmae='").append(lastName).append('\'');
		sb.append('}');
		return super.toString();
	}
}