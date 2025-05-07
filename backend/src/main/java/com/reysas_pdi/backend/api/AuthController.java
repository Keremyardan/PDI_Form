package com.reysas_pdi.backend.api;

import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.core.config.result.ResultHelper;
import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.dao.OfficerRepo;
import com.reysas_pdi.backend.dto.request.LoginRequest;
import com.reysas_pdi.backend.dto.response.LoginResponse;
import com.reysas_pdi.backend.entity.Administrator;
import com.reysas_pdi.backend.entity.Officer;
import com.reysas_pdi.backend.entity.SuperUser;
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
        Optional<? extends SuperUser> userOpt =
                administratorRepo.findAll().stream()
                        .filter(u -> u.getEmail().equalsIgnoreCase(loginRequest.getEmail()) && u.getPassword().equals(loginRequest.getPassword()))
                        .findFirst()
                        .map(SuperUser.class::cast);

        if (userOpt.isEmpty()) {
            userOpt = officerRepo.findAll().stream()
                    .filter(u -> u.getEmail().equalsIgnoreCase(loginRequest.getEmail()) && u.getPassword().equals(loginRequest.getPassword()))
                    .findFirst()
                    .map(SuperUser.class::cast);
        }

        if (userOpt.isPresent()) {
            SuperUser user = userOpt.get();
            return ResultHelper.success(new LoginResponse(user.getId(), user.getName(), user.getEmail(), user.getUserRole().name()));
        }

        return new ResultData<>(false, "Geçersiz mail ya da şifre bilgisi", "401", null);
    }

}
