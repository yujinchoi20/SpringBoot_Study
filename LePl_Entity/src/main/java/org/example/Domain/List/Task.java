package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Long id;

    private String content;

    @Column(name = "start_time")
    private String start;
    @Column(name = "end_time")
    private String end;

    @OneToMany(mappedBy = "task") //양방향
    private List<Timer> timer = new ArrayList<>(); //초기화

    @OneToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "TASK_STATUS_ID")
    private Task_Status task_status; //FK

    /*
    연관관계 편의 메서드
    */

}
