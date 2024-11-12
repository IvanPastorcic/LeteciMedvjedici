package hr.fer.progi.backend.dto;

import java.sql.Date;
import hr.fer.progi.backend.model.Enum.DisasterType;
import hr.fer.progi.backend.model.Enum.ReportStatus;

public class ReportDTO {

	private Long settlementId;
	private DisasterType disasterType;;
	private String shortDescription;
	private String photo;

	public ReportDTO(Long settlementId, DisasterType disasterType, String shortDescription, String photo) {
		this.settlementId = settlementId;
		this.disasterType = disasterType;
		this.shortDescription = shortDescription;
		this.photo = photo;
	}

	public Long getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Long settlementId) {
		this.settlementId = settlementId;
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

}
