package com.reysas_pdi.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="officer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="officer_id")
    private long id;

    @Column(name="officer_name", nullable = false)
    private String name;

    @Column(name = "officer_email", nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="officer_admin_id", referencedColumnName = "administrator_id", nullable = false)
    private Administrator administrator;
}
