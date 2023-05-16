package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false) //Not Null
    private String uid;

    private String nickname;

    @OneToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "CHARACTER_ID") //FK
    private Character character;

    @OneToOne
    @JoinColumn(name = "PROFILE_ID") //FK
    private Profile profile;

    @OneToMany(mappedBy = "member") //양방향, 연관관계의 주인이 아님
    private List<Lists> lists = new ArrayList<>(); //바로 초기화

    /*
    연관관계 편의 메서드 추가
    */
}
