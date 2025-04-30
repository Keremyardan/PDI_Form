package com.reysas_pdi.backend.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "functional_checks", joinColumns = @JoinColumn(name= "pdi_form_id"))
    @MapKeyColumn(name = "check_name")
    @Column(name = "check_value")
    private Map<String, Boolean> functionalChecks;

    private String fuelLitres1;
    private String fuelLitres2;
    private Boolean fuelTypeBenzin1;
    private Boolean fuelTypeBenzin2;
    private Boolean gurasyon;
    private Boolean firstAid;
    private String additionalNotes;
}
