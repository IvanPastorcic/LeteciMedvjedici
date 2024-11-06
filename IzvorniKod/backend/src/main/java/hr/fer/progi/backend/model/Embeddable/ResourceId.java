package hr.fer.progi.backend.model.Embeddable;

import java.io.Serializable;

import hr.fer.progi.backend.model.Enum.ResourceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class ResourceId implements Serializable {
	private static final long serialVersionUID = -2301957031955177912L;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ResourceType resourceType;

	@NotEmpty
	private String address;

	public ResourceId() {
	} 
	
	public ResourceId(ResourceType resourceType, String address) {
		this.resourceType = resourceType;
		this.address = address;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 

}
