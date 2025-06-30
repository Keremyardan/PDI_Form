package com.reysas_pdi.backend.security;

import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.dao.OfficerRepo;
import com.reysas_pdi.backend.entity.Administrator;
import com.reysas_pdi.backend.entity.Officer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AdministratorRepo administratorRepo;
    private final OfficerRepo officerRepo;

    public UserDetailsService(AdministratorRepo administratorRepo, OfficerRepo officerRepo) {
        this.administratorRepo = administratorRepo;
        this.officerRepo = officerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator admin = administratorRepo.findByEmail(username).orElse(null);
        if (admin != null) {
            return User.builder()
                    .username(admin.getEmail())
                    .password(admin.getPassword())
                    .roles(admin.getUserRole().name()) // Örn: ADMIN
                    .build();
        }

        Officer officer = officerRepo.findByEmail(username).orElse(null);

        if (officer != null) {

            return User.builder()
                    .username(officer.getEmail())
                    .password(officer.getPassword())
                    .roles(officer.getUserRole().name()) // Örn: OFFICER
                    .build();

        }
        System.out.println("Gelen Username: " + username);


        throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + username);
    }
}
