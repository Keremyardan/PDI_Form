package com.reysas_pdi.backend.api;

import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.core.config.result.ResultHelper;
import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.dao.OfficerRepo;
import com.reysas_pdi.backend.dto.request.LoginRequest;
import com.reysas_pdi.backend.dto.response.LoginResponse;
import com.reysas_pdi.backend.entity.Administrator;
import com.reysas_pdi.backend.entity.Officer;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public AuthController(AdministratorRepo administratorRepo, OfficerRepo officerRepo, PasswordEncoder passwordEncoder) {
        this.administratorRepo = administratorRepo;
        this.officerRepo = officerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResultData<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional <Administrator> adminOpt = administratorRepo.findAll().stream().filter(u -> u.getEmail().equalsIgnoreCase(loginRequest.getEmail()) && passwordEncoder.matches(loginRequest.getPassword(), u.getPassword())).findFirst();

        if(adminOpt.isPresent()) {
            Administrator admin = adminOpt.get();
            return ResultHelper.success(new LoginResponse(
                    admin.getId(),
                    admin.getName(),
                    admin.getEmail(),
                    admin.getUserRole().name()));
        }

            Optional<Officer> officerOpt = officerRepo.findAll().stream().filter(u -> u.getEmail().equalsIgnoreCase(loginRequest.getEmail()) && passwordEncoder.matches(loginRequest.getPassword(), u.getPassword())).findFirst();

            if(officerOpt.isPresent()) {
                Officer officer = officerOpt.get();
                return  ResultHelper.success(new LoginResponse(
                        officer.getId(),
                        officer.getName(),
                        officer.getEmail(),
                        officer.getUserRole().name()
                ));


            }
        return new ResultData<>(false, "Geçersiz mail ya da şifre", "401", null);
    }

}
