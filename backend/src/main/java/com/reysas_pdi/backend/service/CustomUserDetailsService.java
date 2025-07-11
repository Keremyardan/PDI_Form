package com.reysas_pdi.backend.service;

import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.dao.OfficerRepo;
import com.reysas_pdi.backend.entity.Administrator;
import com.reysas_pdi.backend.entity.Officer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdministratorRepo administratorRepo;
    private final OfficerRepo officerRepo;

    public CustomUserDetailsService(AdministratorRepo administratorRepo, OfficerRepo officerRepo) {
        this.administratorRepo = administratorRepo;
        this.officerRepo = officerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Administrator> adminOpt = administratorRepo.findByEmail(username);
        if (adminOpt.isPresent()) {
            Administrator admin = adminOpt.get();
            return User.builder()
                    .username(admin.getEmail())
                    .password(admin.getPassword())
                    .roles("ADMIN")
                    .build();
        }

        Optional<Officer> officerOpt = officerRepo.findByEmail(username);
        if (officerOpt.isPresent()) {
            Officer officer = officerOpt.get();
            return User.builder()
                    .username(officer.getEmail())
                    .password(officer.getPassword())
                    .roles("OFFICER")
                    .build();
        }

        throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + username);
    }
}
