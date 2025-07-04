package com.reysas_pdi.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="officer")

public class Officer  extends  SuperUser{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    @JsonBackReference
    private Administrator administrator;

    @OneToMany(mappedBy = "officer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PdiForm> pdiForms;


    public Officer () {
       this.userRole = userRole.OFFICER;
   }
}
