package com.example.techpraktika.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "materials")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;
    private int quantity;
    private int costPerUnit;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
