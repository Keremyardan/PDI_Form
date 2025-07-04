package com.reysas_pdi.backend.business.abstracts;

import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.dto.request.pdiform.PdiFormSaveRequest;
import com.reysas_pdi.backend.entity.PdiForm;

import java.security.Principal;
import java.util.List;

public interface IPdiFormService {

    ResultData<PdiForm> save(PdiFormSaveRequest pdiFormSaveRequest, Principal principal);

    PdiForm getById(Long id);

    void delete(Long id);

    List<PdiForm> findByVin(String vin);

    List<PdiForm> findByPdiYeri(String pdiYeri);

    List<PdiForm> findByModel(String model);
    List<PdiForm> findAll();

    List<PdiForm> findByOfficer(Long officerId);



}
