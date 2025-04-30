package com.reysas_pdi.backend.api;

import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.core.config.result.ResultHelper;
import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.dao.OfficerRepo;
import com.reysas_pdi.backend.dto.request.LoginRequest;
import com.reysas_pdi.backend.dto.response.LoginResponse;
import com.reysas_pdi.backend.entity.Administrator;
import com.reysas_pdi.backend.entity.Officer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AdministratorRepo administratorRepo;
    private final OfficerRepo officerRepo;

    public AuthController(AdministratorRepo administratorRepo, OfficerRepo officerRepo) {
        this.administratorRepo = administratorRepo;
        this.officerRepo = officerRepo;
    }

    @PostMapping("/login")
    public ResultData<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<Administrator> adminOpt = administratorRepo.findAll().stream().filter(a -> a.getEmail().equalsIgnoreCase(loginRequest.getEmail()) && a.getPassword().equals(loginRequest.getPassword())).findFirst();
        Optional<Officer> officerOpt = officerRepo.findAll().stream().filter(o -> o.getEmail().equalsIgnoreCase(loginRequest.getEmail()) && o.getPassword().equals(loginRequest.getPassword())).findFirst();
        if(adminOpt.isPresent()){
            Administrator admin = adminOpt.get();
            LoginResponse loginResponse = new LoginResponse(admin.getId(), admin.getName(), admin.getEmail(), admin.getUserRole().name());
            return ResultHelper.success(loginResponse);
        } else if (officerOpt.isPresent()) {
            Officer officer = officerOpt.get();
            LoginResponse loginResponse = new LoginResponse(officer.getId(),officer.getName(),officer.getEmail(), officer.getUserRole().name());
            return  ResultHelper.success(loginResponse);
        }
        return new ResultData<>(false, "Geçersiz mail ya da şifre bilgisi", "401", null);
    }
}
