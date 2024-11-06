package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "HUMANITARIAN_ORGANIZATION")
public class HumanitarianOrganization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotEmpty
	private String organizationName;

	public HumanitarianOrganization() {
	}

	public HumanitarianOrganization(String organizationName) {
		this.organizationName = organizationName;
	}

	public Long getId() {
		return id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

}
