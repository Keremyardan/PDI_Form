package com.reysas_pdi.backend.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="administrator")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" )
    private long id;


    @Column(name="name", nullable = false)
    private String name;
}
