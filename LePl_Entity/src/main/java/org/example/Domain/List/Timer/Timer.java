package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Timer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIMER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "TASK_ID")
    private Task task; //FK

    @Column(name = "start_time")
    private LocalDateTime start;
    @Column(name = "end_time")
    private LocalDateTime end;

    @Enumerated(EnumType.STRING)
    private TimeStatus timeStatus; //ALLOW, FOCUS

}
