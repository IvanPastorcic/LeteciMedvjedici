package hr.fer.progi.backend.model;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "SETTLEMENT")
public class Settlement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // or AUTO or SEQUENCE or TABLE
	private Long settlementId;
	
	@Column
	private String settlementName;

		
	@OneToMany(mappedBy = "settlement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<NaturalDisaster> naturalDisasters;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTY_ID", nullable = false)
	private County county;


	public Long getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Long settlementId) {
		this.settlementId = settlementId;
	}

	public String getSettlementName() {
		return settlementName;
	}

	public void setSettlementName(String settlementName) {
		this.settlementName = settlementName;
	}
	
}
