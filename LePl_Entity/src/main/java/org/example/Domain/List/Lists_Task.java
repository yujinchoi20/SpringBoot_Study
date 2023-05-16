package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Lists_Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LISTS_TASK_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "LISTS_ID")
    private Lists lists; //FK

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "TASK_ID")
    private Task task; //FK

    private int count;
}
