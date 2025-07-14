package com.reysas_pdi.backend.dto.response.pdiform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdiFormResponse {
    private Date createdAt;
    private String createdByName;
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

    private String damageDescription0;
    private String damageDescription1;
    private String damageDescription2;
    private String damageDescription3;
    private String damageDescription4;
    private String damageDescription5;
    private String damageDescription6;
    private String damageDescription7;
    private String damageDescription8;

}
