package com.example.techpraktika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "brigade")
@NoArgsConstructor
@AllArgsConstructor
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "brigade", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BrigadeWorker> workers;
}
