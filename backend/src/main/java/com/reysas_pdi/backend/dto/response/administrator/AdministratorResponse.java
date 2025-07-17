package com.reysas_pdi.backend.dto.response.administrator;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdministratorResponse {
    private Long id;
    private String name;
    private String email;
}

