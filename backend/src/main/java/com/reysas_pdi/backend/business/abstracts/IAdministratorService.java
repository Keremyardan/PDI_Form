package com.reysas_pdi.backend.business.abstracts;

import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.entity.Administrator;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAdministratorService {
    Page<Administrator> cursor(int page, int size);

    Administrator save(Administrator administratorSaveRequest);

    ResultData<Administrator> update (Long id, Administrator administrator);

    Administrator getById(Long id);

    ResultData<String> delete(Long id);

    List<Administrator> getAll();

    List<Administrator> findByNameContainingIgnoreCase(String name);

    List<Administrator> findByAdministratorId(Long administratorId);
}
