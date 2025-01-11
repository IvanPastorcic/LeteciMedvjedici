package hr.fer.progi.backend.dto;

import hr.fer.progi.backend.model.Embeddable.ResourceId;

public class ResourceUpdateDTO {
    int quantity;
    private ResourceId id;

    public ResourceUpdateDTO(int quantity, ResourceId id) {
        this.quantity = quantity;
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ResourceId getId() {
        return id;
    }

    public void setId(ResourceId id) {
        this.id = id;
    }
}
