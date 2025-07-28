package com.reysas_pdi.backend.dto.request.administrator;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorUpdateRequest {

    @Min(value=1,message = "Id should be greater than 1")
    private Long id;

    @NotNull(message="Name can not be empty")
    @Size(min=1, max=50, message = "Name value should be between 1 and 50")
    private String name;

    @NotNull(message = "Officer Id can not be empty")
    @Min(value=1, message = "Customer id should be greater than 1")
    private Long officerId;

    @Size(min=6, max=100, message = "Şifre en az 6 karakter olmalı")
    private String password;

}
