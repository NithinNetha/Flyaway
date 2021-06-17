package com.flyaway.model;

public class Airline {
	private int airlineId;
	private String airlineName;
	private String contactName;
	private String contactNumber;
	
	public int getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Override
	public String toString() {
		return "Airline [airlineId=" + airlineId + ", airlineName=" + airlineName + ", contactName=" + contactName
				+ ", contactNumber=" + contactNumber + "]";
	}
	
	
}
