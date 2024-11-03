package hr.fer.progi.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HumanitarianOrganization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // or AUTO or SEQUENCE or TABLE
	private Long id;
	private String organizationName;

	public HumanitarianOrganization(Long id, String organizationName) {
		super();
		this.id = id;
		this.organizationName = organizationName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Override
	public String toString() {
		return "HumanitarianOrganization{" + "id=" + id + ", organizationName='" + organizationName + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		HumanitarianOrganization that = (HumanitarianOrganization) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

}
