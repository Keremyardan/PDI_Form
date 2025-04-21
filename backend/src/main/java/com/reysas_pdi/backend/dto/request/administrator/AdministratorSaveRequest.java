package com.reysas_pdi.backend.dto.request.administrator;


import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdministratorSaveRequest {
    @NotNull(message = "Name can no be empty")
    @Size(min=2, max=50, message = "The character amount should be between 2 and 50")
    private String name;

    @NotNull(message="Email can not be empty")
    @Size(min=2, max=50, message = "Email character amount should be between 2 and 50 characters")
    private String email;
}
