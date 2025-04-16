package com.reysas_pdi.backend.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdministratorSaveRequest {
    @NotNull(message = "Name can no be empty")
    @Size(min=2, max=50, message = "The character amount should be between 2 and 50")
    private String name;

    @NotNull(message="Customer ID can not be empty")
    @Min(value=1,message = "Administrator ID value should be greater than 1")
    private Long administratorId;

    @NotNull(message="Email can not be empty")
    @Size(min=2, max=50, message = "Email character amount should be between 2 and 50 characters")
    private String email;
}
