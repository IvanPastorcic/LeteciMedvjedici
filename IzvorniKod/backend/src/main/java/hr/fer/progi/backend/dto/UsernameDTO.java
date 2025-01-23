package hr.fer.progi.backend.dto;

public class UsernameDTO {

    String username;

    public UsernameDTO(){};

    public UsernameDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
