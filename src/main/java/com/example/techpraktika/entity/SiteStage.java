package com.example.techpraktika.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "site_stage")
public class SiteStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stageName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private ConstructionSite constructionSite;


}
