package com.example.techpraktika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"defects", "siteStages", "brigades"})
@Table(name = "construction_site")
public class ConstructionSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    private LocalDate startDate;
    private LocalDate endDate;

    private int budget;

    @OneToMany(mappedBy = "constructionSite", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Defect> defects;

    @OneToMany(mappedBy = "constructionSite", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SiteStage> siteStages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "construction_site_brigade",
            joinColumns = @JoinColumn(name = "construction_site_id"),
            inverseJoinColumns = @JoinColumn(name = "brigade_id")
    )
    @JsonIgnore
    private List<Brigade> brigades;
}
