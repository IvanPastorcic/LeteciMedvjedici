package hr.fer.progi.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "HUMANITARIAN_ORGANIZATION")
public class HumanitarianOrganization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // or AUTO or SEQUENCE or TABLE
	private Long idOrg;
	
	@Column
	private String organizationName;

	public HumanitarianOrganization(Long id, String organizationName) {
		super();
		this.idOrg = id;
		this.organizationName = organizationName;
	}

	public HumanitarianOrganization() {

	}

	public Long getId() {
		return idOrg;
	}

	public void setId(Long id) {
		this.idOrg = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Override
	public String toString() {
		return "HumanitarianOrganization{" + "id=" + idOrg + ", organizationName='" + organizationName + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		HumanitarianOrganization that = (HumanitarianOrganization) o;

		return idOrg != null ? idOrg.equals(that.idOrg) : that.idOrg == null;
	}

	@Override
	public int hashCode() {
		return idOrg != null ? idOrg.hashCode() : 0;
	}

}
