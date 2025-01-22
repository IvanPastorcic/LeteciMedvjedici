package hr.fer.progi.backend.dto;

import java.sql.Date;
import hr.fer.progi.backend.model.Enum.DisasterType;
import hr.fer.progi.backend.model.Enum.ReportStatus;


	public class ReportDTO {
		private String settlementName;
		private DisasterType disasterType;
		private String shortDescription;
		private String photo;
		private String geographicCoordinates; // Jedan atribut za koordinate u formatu "latitude,longitude"
	
		public ReportDTO(String settlementName, DisasterType disasterType, String shortDescription, String photo, String geographicCoordinates) {
			this.settlementName = settlementName;
			this.disasterType = disasterType;
			this.shortDescription = shortDescription;
			this.photo = photo;
			this.geographicCoordinates = geographicCoordinates; // Pohranjujemo koordinate kao string
		}
	
		// Getteri i setteri
		public String getSettlementName() {
			return settlementName;
		}
	
		public void setSettlementName(String settlementName) {
			this.settlementName = settlementName;
		}
	
		public DisasterType getDisasterType() {
			return disasterType;
		}
	
		public void setDisasterType(DisasterType disasterType) {
			this.disasterType = disasterType;
		}
	
		public String getShortDescription() {
			return shortDescription;
		}
	
		public void setShortDescription(String shortDescription) {
			this.shortDescription = shortDescription;
		}
	
		public String getPhoto() {
			return photo;
		}
	
		public void setPhoto(String photo) {
			this.photo = photo;
		}
	
		public String getGeographicCoordinates() {
			return geographicCoordinates;
		}
	
		public void setGeographicCoordinates(String geographicCoordinates) {
			this.geographicCoordinates = geographicCoordinates;
		}
	}
	



