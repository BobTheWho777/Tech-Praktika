package com.example.techpraktika.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Defects")
@NoArgsConstructor
@AllArgsConstructor
public class Defect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate reportedDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private ConstructionSite constructionSite;
}
