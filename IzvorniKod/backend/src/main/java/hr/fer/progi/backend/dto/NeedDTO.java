package hr.fer.progi.backend.dto;

import hr.fer.progi.backend.model.Enum.NeedType;

public class NeedDTO {
    NeedType type;
    String location; //potencijalna promjena u koordinate
    int quantity;

    public NeedDTO(NeedType type, String location, int quantity) {
        this.type = type;
        this.location = location;
        this.quantity = quantity;
    }

    public NeedType getType() {
        return type;
    }

    public void setType(NeedType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
