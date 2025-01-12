package hr.fer.progi.backend.dto;

import hr.fer.progi.backend.model.Embeddable.ResourceId;
import hr.fer.progi.backend.model.Enum.ResourceType;
import jakarta.validation.constraints.NotEmpty;

public class ResourceUpdateDTO {
    int quantity;
    private ResourceType resourceType;


    private String address;

    public ResourceUpdateDTO(int quantity, ResourceType resourceType, String address) {
        this.quantity = quantity;
        this.resourceType = resourceType;
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
