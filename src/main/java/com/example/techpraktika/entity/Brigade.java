package com.example.techpraktika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
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

    @ManyToMany(mappedBy = "brigades", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ConstructionSite> constructionSites;
}