package com.reysas_pdi.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "administrator")
public class Administrator extends SuperUser {
    @OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Officer> officerList;

    public Administrator() {
        this.userRole = userRole.ADMIN;
    }
}