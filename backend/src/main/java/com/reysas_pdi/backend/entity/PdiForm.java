package com.reysas_pdi.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PdiForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pdiYeri;
    private String model;
    private String vin;
    private String kmBilgisi;
    private String kontrolTarihi;


    private String fuelLitres1;
    private String fuelLitres2;
    private Boolean fuelTypeBenzin1;
    private Boolean fuelTypeBenzin2;
    private Boolean gurasyon;
    private Boolean firstAid;
    private String additionalNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officer_id")
    @JsonBackReference
    private Officer officer;

    private Boolean solOnKapi;
    private Boolean sagOnKapi;
    private Boolean onKaput;
    private Boolean arkaTampon;
    private Boolean tavan;
    private Boolean onTampon;
    private Boolean arkaBagaj;
    private Boolean sagOnCamurluk;
    private Boolean solOnCamurluk;
    private Boolean sagArkaCamurluk;
    private Boolean solArkaCamurluk;
    private Boolean sagArkaKapi;
    private Boolean solArkaKapi;

}
