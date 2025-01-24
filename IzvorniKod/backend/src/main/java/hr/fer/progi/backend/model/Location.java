//package hr.fer.progi.backend.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "LOCATION")
//public class Location {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generirani ID
//    private Long id;
//
//    @NotNull
//    private Double latitude;  // Geografska širina
//
//    @NotNull
//    private Double longitude; // Geografska dužina
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "SETTLEMENT_ID", nullable = true) // Može biti null
//    private Settlement settlement;
//
//    public Location() {
//    }
//
//    public Location(Double latitude, Double longitude, Settlement settlement) {
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.settlement = settlement;
//    }
//
//    // GETTERI I SETTERI
//    public Long getId() { return id; }
//
//    public Double getLatitude() { return latitude; }
//    public void setLatitude(Double latitude) { this.latitude = latitude; }
//
//    public Double getLongitude() { return longitude; }
//    public void setLongitude(Double longitude) { this.longitude = longitude; }
//
//    public Settlement getSettlement() { return settlement; }
//    public void setSettlement(Settlement settlement) { this.settlement = settlement; }
//}
