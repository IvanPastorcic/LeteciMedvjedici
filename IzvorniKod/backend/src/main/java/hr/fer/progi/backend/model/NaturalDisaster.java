package hr.fer.progi.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import hr.fer.progi.backend.model.Enum.DisasterType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table("NATURALDISASTER")
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
