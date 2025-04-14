package com.reysas_pdi.backend.business.abstracts;

import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.entity.Officer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOfficerService {
    ResultData<Officer> save(Officer officer);
    Officer get(Long id);
    void delete(Long id);
    ResultData<Officer> update(Long id, Officer officer);

    Page<Officer> cursor (int page, int size);

    List<Officer> getOfficersByName(String name);
}
