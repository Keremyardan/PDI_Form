package com.reysas_pdi.backend.dto.response.pdiform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdiFormResponse {
    private String pdiYeri;
    private String model;
    private String vin;
    private String kmBilgisi;
    private String kontrolTarihi;
    private Map<String,Boolean> functionalCheck;
    private String fuelLitres1;
    private String fuelLitres2;
    private Boolean fuelTypeBenzin1;
    private Boolean fuelTypeBenzin2;
    private Boolean gurasyon;
    private Boolean firstaid;
    private String additionalNotes;
}
