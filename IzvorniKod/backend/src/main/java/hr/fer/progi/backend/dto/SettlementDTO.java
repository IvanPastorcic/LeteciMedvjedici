package hr.fer.progi.backend.dto;

public class SettlementDTO {

	private String settlementName;
	private String countyName;
	public SettlementDTO(String settlementName, String countyName) {
		super();
		this.settlementName = settlementName;
		this.countyName = countyName;
	}
	public String getSettlementName() {
		return settlementName;
	}
	public void setSettlementName(String settlementName) {
		this.settlementName = settlementName;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
	
}
