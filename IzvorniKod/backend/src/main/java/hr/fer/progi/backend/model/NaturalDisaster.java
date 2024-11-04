package hr.fer.progi.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table("NATURALDISASTER")
public class NaturalDisaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNepogoda;
	
	@NotEmpty
	private String vrstaNepogoda;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id", nullable = false)
	private Settlement settlement;


	public int getIdNepogoda() {
		return idNepogoda;
	}


	public void setIdNepogoda(int idNepogoda) {
		this.idNepogoda = idNepogoda;
	}


	public String getVrstaNepogoda() {
		return vrstaNepogoda;
	}


	public void setVrstaNepogoda(String vrstaNepogoda) {
		this.vrstaNepogoda = vrstaNepogoda;
	}


	public Settlement getSettlement() {
		return settlement;
	}


	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}
	
	
	
}
