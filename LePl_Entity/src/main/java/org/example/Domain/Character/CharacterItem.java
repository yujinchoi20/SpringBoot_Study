package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class CharacterItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "CHARACTER_ID")
    private Character character; //FK

    private Long itemId;

    @Enumerated(EnumType.STRING)
    private WearingStatus wearingStatus; //아이템 착용여부, YES NO
}
