package com.example.techpraktika.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brigade_worker")
@NoArgsConstructor
@AllArgsConstructor
public class BrigadeWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    private String number;

    @ManyToOne
    @JoinColumn(name = "beidage_id")
    private Brigade brigade;

}
