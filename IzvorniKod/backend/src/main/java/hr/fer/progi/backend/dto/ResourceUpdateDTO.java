package hr.fer.progi.backend.dto;

public class ResourceUpdateDTO {
    int quantity;

    public ResourceUpdateDTO(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
