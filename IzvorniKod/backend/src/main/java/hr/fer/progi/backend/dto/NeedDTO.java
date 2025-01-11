package hr.fer.progi.backend.dto;

import hr.fer.progi.backend.model.Enum.NeedType;

public class NeedDTO {
    NeedType type;
    String location; //potencijalna promjena u koordinate

    public NeedDTO(NeedType type, String location) {
        this.type = type;
        this.location = location;
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
}
