package com.flyaway.model;

public class Flight {
	private int flightId;
	private String source;
	private String destination;
	private String airline;
	private double ecPrice;
	private double fcPrice;
	private double bcPrice;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public double getEcPrice() {
		return ecPrice;
	}

	public void setEcPrice(double ecPrice) {
		this.ecPrice = ecPrice;
	}

	public double getFcPrice() {
		return fcPrice;
	}

	public void setFcPrice(double fcPrice) {
		this.fcPrice = fcPrice;
	}

	public double getBcPrice() {
		return bcPrice;
	}

	public void setBcPrice(double bcPrice) {
		this.bcPrice = bcPrice;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", source=" + source + ", destination=" + destination + ", airline="
				+ airline + ", ecPrice=" + ecPrice + ", fcPrice=" + fcPrice + ", bcPrice=" + bcPrice + "]";
	}

	

}
