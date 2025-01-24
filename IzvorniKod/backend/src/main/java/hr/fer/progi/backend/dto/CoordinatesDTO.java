package hr.fer.progi.backend.dto;

public class CoordinatesDTO {

	private float lat;
	private float lon;
	public CoordinatesDTO(float lat, float lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}

	public String getStringCoordinates(){
		return lat + ", " + lon;
	}
	
	
}
