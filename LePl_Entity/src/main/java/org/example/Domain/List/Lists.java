package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.Domain.Member.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Lists {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LISTS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "MEMBER_ID")
    private Member member; //FK

    private LocalDate lists_date; //Date -> LocalDateTime -> LocalDate 으로 변경

    @OneToMany(mappedBy = "lists") //양방향
    private List<Lists_Task> lists_tasks = new ArrayList<>(); //List 초기화, null 값 대비

    /*
    연관관계 편의 메서드
    */
}
