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

	public NaturalDisaster() {
	}

	public NaturalDisaster(DisasterType disasterType) {
		this.disasterType = disasterType;
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
