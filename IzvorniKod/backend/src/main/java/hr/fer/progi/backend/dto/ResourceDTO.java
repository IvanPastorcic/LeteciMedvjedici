package hr.fer.progi.backend.dto;

import hr.fer.progi.backend.model.Enum.ResourceType;

public class ResourceDTO {

    private ResourceType type;
    private int quantity;
    private String location;


    public ResourceDTO(ResourceType type, int quantity, String location) {
        this.type = type;
        this.quantity = quantity;
        this.location = location;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
