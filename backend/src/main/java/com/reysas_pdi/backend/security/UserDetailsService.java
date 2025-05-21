package com.reysas_pdi.backend.security;

import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.dao.OfficerRepo;
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
        return administratorRepo.findByEmail(username)
                .map(admin -> User.builder()
                        .username(admin.getEmail())
                        .password(admin.getPassword())
                        .authorities("ROLE_" + admin.getUserRole().name())
                        .build())


                .orElseGet(() -> officerRepo.findByEmail(username)
                        .map(officer -> User.builder()
                                .username(officer.getEmail())
                                .password(officer.getPassword())
                                .authorities("ROLE_" + officer.getUserRole().name())
                                .build())
                        .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username)));
    }

}
