package com.example.techpraktika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "brigade")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "brigade", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BrigadeWorker> workers;
}
