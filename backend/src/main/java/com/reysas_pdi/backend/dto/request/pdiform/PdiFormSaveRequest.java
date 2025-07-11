package com.reysas_pdi.backend.dto.request.pdiform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
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

    private Boolean functionalCheck0;
    private Boolean functionalCheck1;
    private Boolean functionalCheck2;
    private Boolean functionalCheck3;
    private Boolean functionalCheck4;
    private Boolean functionalCheck5;
    private Boolean functionalCheck6;
    private Boolean functionalCheck7;
    private Boolean functionalCheck8;
    private Boolean functionalCheck9;
    private Boolean functionalCheck10;
    private Boolean functionalCheck11;
    private Boolean functionalCheck12;
    private Boolean functionalCheck13;
    private Boolean functionalCheck14;
    private Boolean functionalCheck15;
    private Boolean functionalCheck16;
    private Boolean functionalCheck17;
    private Boolean functionalCheck18;
    private Boolean functionalCheck19;
    private Boolean functionalCheck20;
    private Boolean functionalCheck21;
    private Boolean functionalCheck22;
    private Boolean functionalCheck23;
    private Boolean functionalCheck24;
    private Boolean functionalCheck25;
    private Boolean functionalCheck26;
    private Boolean functionalCheck27;
    private Boolean functionalCheck28;
}
