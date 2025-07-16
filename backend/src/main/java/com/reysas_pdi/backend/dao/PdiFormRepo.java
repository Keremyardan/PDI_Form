package com.reysas_pdi.backend.dao;

import com.reysas_pdi.backend.entity.Officer;
import com.reysas_pdi.backend.entity.PdiForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PdiFormRepo extends JpaRepository<PdiForm, Long> {
    List<PdiForm> findByVinContainingIgnoreCase(String vin);

    List<PdiForm> findByPdiYeriContainingIgnoreCase(String pdiYeri);

    List<PdiForm> findByModelContainingIgnoreCase(String model);

    boolean existsByVin(String vin);

    List<PdiForm> findByOfficer(Officer officer);


    List<PdiForm> findByCreatedByEmail(String email);

    List<PdiForm> findByCreatedByEmailAndCreatedByRole(String email, String role);

}
