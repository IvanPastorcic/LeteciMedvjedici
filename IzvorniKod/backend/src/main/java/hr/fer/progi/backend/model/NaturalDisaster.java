package hr.fer.progi.backend.model;


import hr.fer.progi.backend.model.Enum.DisasterType;
import jakarta.persistence.*;

@Entity
@Table(name = "NATURAL_DISASTER")
public class NaturalDisaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int disasterId;
	
	@Enumerated(EnumType.STRING)
    private DisasterType disasterType;
	

    public NaturalDisaster() {
    	
    }
    
	public NaturalDisaster(int disasterId, DisasterType disasterType) {
		super();
		this.disasterId = disasterId;
		this.disasterType = disasterType;
	}

	@ManyToOne
	@JoinColumn(name = "SETTLEMENT_ID", nullable = false)
	private Settlement settlement;
	
	public int getDisasterId() {
		return disasterId;
	}


	public void setDisasterId(int disasterId) {
		this.disasterId = disasterId;
	}


	public DisasterType getDisasterType() {
		return disasterType;
	}


	public void setDisasterType(DisasterType disasterType) {
		this.disasterType = disasterType;
	}

}
