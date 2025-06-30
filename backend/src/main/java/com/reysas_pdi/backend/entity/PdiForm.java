package com.reysas_pdi.backend.entity;

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
    private Officer officer;

    @ElementCollection
    @CollectionTable(name = "selected_parts", joinColumns = @JoinColumn(name="pdi_form_id"))
    @Column(name ="part_name")
    private List<String> selectedParts;

}
