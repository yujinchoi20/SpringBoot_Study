package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Character {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHARACTER_ID")
    private Long id;

    private Long level;

    @OneToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "COIN_ID")
    private Coin coin; //FK

    @OneToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "EXP_ID")
    private Exp exp; //FK

    @OneToMany(mappedBy = "character")
    private List<CharacterItem> characterItems = new ArrayList<>();

    /*
    연관관계 편의 메서드 추가
    */
}
