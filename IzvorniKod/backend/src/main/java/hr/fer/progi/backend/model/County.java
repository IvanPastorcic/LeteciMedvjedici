package hr.fer.progi.backend.model;

import hr.fer.progi.backend.model.Enum.Region;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "COUNTY")
public class County {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotEmpty
	private String countyName;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	private Region region;

	public County() {
	}

	public County(String countyName, Region region) {
		this.countyName = countyName;
		this.region = region;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Long getId() {
		return id;
	}

}
