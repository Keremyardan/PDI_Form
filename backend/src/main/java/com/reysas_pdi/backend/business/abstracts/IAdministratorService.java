package com.reysas_pdi.backend.business.abstracts;

import com.reysas_pdi.backend.entity.Administrator;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAdministratorService {
    Page<Administrator> cursor(int page, int size);

    Administrator save(Administrator administratorSaveRequest);

    Administrator update (Long id, Administrator administrator);

    Administrator getById(Long id);

    void delete(Long id);

    List<Administrator> findByNameContainingIgnoreCase(String name);

    List<Administrator> findByAdministratorId(Long administratorId);
}
