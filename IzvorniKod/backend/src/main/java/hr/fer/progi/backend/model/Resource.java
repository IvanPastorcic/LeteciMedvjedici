package hr.fer.progi.backend.model;

import hr.fer.progi.backend.model.Embeddable.ResourceId;
import hr.fer.progi.backend.model.Enum.ResourceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "RESOURCE")
public class Resource {

	@EmbeddedId
	private ResourceId id = new ResourceId();

	@NotNull
	@Column
	private int quantity;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HUMANITARIAN_ORGANIZATION_ID", nullable = false)
	private HumanitarianOrganization humanitarianOrganization;

	public Resource() {
	}

	// without relationship
	public Resource(ResourceType resourceType, String address, int quantity) {
		this.id.setResourceType(resourceType);
		this.id.setAddress(address);
		this.quantity = quantity;
	}

	// with relationship
	public Resource(ResourceType resourceType, String address, int quantity,
			HumanitarianOrganization humanitarianOrganization) {
		this.id.setResourceType(resourceType);
		this.id.setAddress(address);
		this.quantity = quantity;
		this.humanitarianOrganization = humanitarianOrganization;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public HumanitarianOrganization getHumanitarianOrganization() {
		return humanitarianOrganization;
	}

	public void setHumanitarianOrganization(HumanitarianOrganization humanitarianOrganization) {
		this.humanitarianOrganization = humanitarianOrganization;
	}

	public ResourceId getId() {
		return id;
	}
	
	// from embedded
	
	public ResourceType getResourceType() {
		return this.id.getResourceType();
	}

	public void setResourceType(ResourceType resourceType) {
		this.id.setResourceType(resourceType);
	}

	public String getAddress() {
		return this.id.getAddress();
	}

	public void setAddress(String address) {
		this.id.setAddress(address);
	}

}
