package hr.fer.progi.backend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "COUNTY")
public class County {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String countyName;

    /*TODO: dodati regiju kad napravimo model regije*/
}
