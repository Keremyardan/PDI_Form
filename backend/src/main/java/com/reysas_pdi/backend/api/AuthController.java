package com.reysas_pdi.backend.api;

import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.core.config.result.ResultHelper;
import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.dto.request.LoginRequest;
import com.reysas_pdi.backend.dto.response.LoginResponse;
import com.reysas_pdi.backend.entity.Administrator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AdministratorRepo administratorRepo;

    public AuthController(AdministratorRepo administratorRepo) {
        this.administratorRepo = administratorRepo;
    }

    @PostMapping("/login")
    public ResultData<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<Administrator> adminOpt = administratorRepo.findAll().stream().filter(a -> a.getEmail().equalsIgnoreCase(loginRequest.getEmail()) && a.getPassword().equals(loginRequest.getPassword())).findFirst();

        if(adminOpt.isPresent()){
            Administrator admin = adminOpt.get();
            LoginResponse loginResponse = new LoginResponse(admin.getId(), admin.getName(), admin.getEmail(), admin.getUserRole().name());
            return ResultHelper.success(loginResponse);
        }
        return new ResultData<>(false, "yanlış mail ya da şifre bilgisi", "401", null);
    }
}
