package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "LOCATION")
public class Location {
	
	@Id
	private String geographicalCoordinates;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SETTLEMENT_ID", nullable = false)
	private Settlement settlement;

	public Location() {
	}
	
	public Location(String geographicalCoordinates, Settlement settlement) {
		this.geographicalCoordinates = geographicalCoordinates;
		this.settlement = settlement;
	}

	public String getGeographicalCoordinates() {
		return geographicalCoordinates;
	}

	public void setGeographicalCoordinates(String geographicalCoordinates) {
		this.geographicalCoordinates = geographicalCoordinates;
	}

	public Settlement getSettlement() {
		return settlement;
	}

	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}
	
}
