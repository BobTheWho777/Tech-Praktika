package com.example.techpraktika.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "materials")
@NoArgsConstructor
@AllArgsConstructor

public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private int costPerUnit;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
