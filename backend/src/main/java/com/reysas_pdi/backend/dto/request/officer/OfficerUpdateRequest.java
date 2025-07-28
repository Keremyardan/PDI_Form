package com.reysas_pdi.backend.dto.request.officer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficerUpdateRequest {

    @Min(value=1,message = "Officer id should be greater than 1")
    private Long id;

    @NotNull(message = "Name can not be empty")
    @Size(min=1, max=50, message = "name must have characters between 1 and 50")
    private String name;

    @NotNull(message = "Email can not be empty")
    @Size(min=1, max=50, message = "name must have characters between 1 and 50")
    private String email;

    @Size(min=6, max=100, message = "Şifre en az 6 karakter olmalıdır")
    private String password;

}
