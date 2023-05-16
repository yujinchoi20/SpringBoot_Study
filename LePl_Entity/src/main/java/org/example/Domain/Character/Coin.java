package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Coin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COIN_ID")
    private Long id;

    private Long coin_all;

}
