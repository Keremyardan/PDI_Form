package com.reysas_pdi.backend.dto.request.pdiform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PdiFormSaveRequest {
    @NotNull(message = "PDI yeri boş olamaz")
    @Size(min = 2, max = 100, message = "PDI yeri 2 ile 100 karakter arasında olmalı")
    private String pdiYeri;

    @NotNull(message = "Model bilgisi boş olamaz")
    @Size(min = 1, max = 100, message = "Model 1 ile 100 karakter arasında olmalı")
    private String model;

    @NotNull(message = "VIN bilgisi boş olamaz")
    @Size(min = 2, max = 50, message = "VIN 2 ile 50 karakter arasında olmalı")
    private String vin;

    @NotNull(message = "Kilometre bilgisi boş olamaz")
    private String kmBilgisi;

    @NotNull(message = "Kontrol tarihi boş olamaz")
    private String kontrolTarihi;

    @NotNull(message = "Fonksiyonel kontroller boş olamaz")
    private Map<String, Boolean> functionalChecks;

    private String fuelLitres1;
    private String fuelLitres2;

    private Boolean fuelTypeBenzin1;
    private Boolean fuelTypeBenzin2;

    private Boolean gurasyon;
    private Boolean firstaid;

    private String additionalNotes;
}
