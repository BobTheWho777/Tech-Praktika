package com.example.techpraktika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "construction_site")
public class ConstructionSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private Date startDate;
    private Date endDate;
    private int budget;

    @OneToMany(mappedBy = "constructionSite", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SiteStage> stages;

    @OneToMany(mappedBy = "constructionSite", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Defect> defects;

}
