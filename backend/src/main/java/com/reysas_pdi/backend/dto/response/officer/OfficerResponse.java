package com.reysas_pdi.backend.dto.response.officer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfficerResponse {
    private Long id;
    private String name;
    private String email;
}
