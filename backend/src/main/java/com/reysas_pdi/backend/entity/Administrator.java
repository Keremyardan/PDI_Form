package com.reysas_pdi.backend.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="administrator")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="administrator_id" )
    private long id;

    @Column (name="administrator_email")
    private String email;

    @Column(name="administrator_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "administrator",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List <Officer> officer;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole userRole = UserRole.ADMIN;

}
