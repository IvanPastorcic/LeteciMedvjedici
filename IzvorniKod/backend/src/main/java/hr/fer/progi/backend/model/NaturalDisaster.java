package hr.fer.progi.backend.model;

import hr.fer.progi.backend.model.Enum.DisasterType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "NATURAL_DISASTER")
public class NaturalDisaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int disasterId;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	private DisasterType disasterType;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SETTLEMENT_ID", nullable = false)
	private Settlement settlement;

	public NaturalDisaster() {
	}

	// without relationships
	public NaturalDisaster(DisasterType disasterType) {
		this.disasterType = disasterType;
	}
	
	// with relationships
	public NaturalDisaster(DisasterType disasterType, Settlement settlement) {
		this.disasterType = disasterType;
		this.settlement = settlement;
	}
	
	public Settlement getSettlement() {
		return settlement;
	}

	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}

	public int getDisasterId() {
		return disasterId;
	}

	public DisasterType getDisasterType() {
		return disasterType;
	}

	public void setDisasterType(DisasterType disasterType) {
		this.disasterType = disasterType;
	}

}
