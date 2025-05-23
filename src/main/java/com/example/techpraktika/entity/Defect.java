package com.example.techpraktika.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "Defects")
@NoArgsConstructor
@AllArgsConstructor

public class Defect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Date reportedDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private ConstructionSite constructionSite;

}
