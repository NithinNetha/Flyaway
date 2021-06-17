package com.flyaway.model;

public class Place {
	private int placeId;
	private String placeName;
	private String placeType;
	
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceType() {
		return placeType;
	}
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}
	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", placeName=" + placeName + ", placeType=" + placeType + "]";
	}
	
}
