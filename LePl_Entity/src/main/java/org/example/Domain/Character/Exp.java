package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Exp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXP_ID")
    private Long id;

    private Long exp_all;

}
