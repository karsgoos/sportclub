package com.realdolmen.sportclub.events.DTO;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AttendEventDTO {
	@NotNull
	private String eventId;
	@Nullable
	private String userId;
	@Nullable
	@Min(0)
	private int nrOfChildren;
	@Nullable
	@Min(0)
	private int nrOfAdults;
	@Nullable
	private String firstName;
	@Nullable
	private String lastName;
	@Nullable
	private String email;
	
	public String getEventId() {
		return eventId;
	}
	
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Nullable
	public int getNrOfChildren() {
		return nrOfChildren;
	}
	
	public void setNrOfChildren(@Nullable int nrOfChildren) {
		this.nrOfChildren = nrOfChildren;
	}
	
	@Nullable
	public int getNrOfAdults() {
		return nrOfAdults;
	}
	
	public void setNrOfAdults(@Nullable int nrOfAdults) {
		this.nrOfAdults = nrOfAdults;
	}
	
	@Nullable
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(@Nullable String firstName) {
		this.firstName = firstName.trim();
	}
	
	@Nullable
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(@Nullable String lastName) {
		this.lastName = lastName.trim();
	}
	
	@Nullable
	public String getEmail() {
		return email;
	}
	
	public void setEmail(@Nullable String email) {
		this.email = email.trim() ;
	}
}
