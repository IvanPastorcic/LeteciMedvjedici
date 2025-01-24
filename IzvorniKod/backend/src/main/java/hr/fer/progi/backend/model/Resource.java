package hr.fer.progi.backend.model;

import hr.fer.progi.backend.model.Embeddable.ResourceId;
import hr.fer.progi.backend.model.Enum.ResourceType;
import hr.fer.progi.backend.model.Enum.Role;
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "HUMANITARIAN_ORGANIZATION_ID", nullable = false)
	private HumanitarianOrganization humanitarianOrganization;


	public Resource( int quantity, HumanitarianOrganization humanitarianOrganization) {
		this.quantity = quantity;
		this.humanitarianOrganization = humanitarianOrganization;


	}

	public Resource() {

	}

	public ResourceId getId() {
		return id;
	}

	public void setId(ResourceId id) {
		this.id = id;
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




}
