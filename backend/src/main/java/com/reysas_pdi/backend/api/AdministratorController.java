package com.reysas_pdi.backend.api;

import com.reysas_pdi.backend.business.abstracts.IAdministratorService;
import com.reysas_pdi.backend.business.abstracts.IOfficerService;
import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.core.config.result.ResultHelper;
import com.reysas_pdi.backend.core.modelMapper.IModelMapperService;
import com.reysas_pdi.backend.dto.request.AdministratorSaveRequest;
import com.reysas_pdi.backend.dto.response.AdministratorResponse;

import com.reysas_pdi.backend.entity.Administrator;
import com.reysas_pdi.backend.entity.Officer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {

    private final IAdministratorService administratorService;
    private final IModelMapperService modelMappperService;
    private final IOfficerService officerService;

    public AdministratorController(
            IAdministratorService administratorService,
                                   IModelMapperService modelMapperService,
                                   IOfficerService officerService
    )
    {
        this.administratorService = administratorService;
        this.modelMappperService = modelMapperService;
        this.officerService = officerService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AdministratorResponse> save(@Valid @RequestBody AdministratorSaveRequest administratorSaveRequest) {
        Administrator saveAdministrator =this.modelMappperService.forRequest().map(administratorSaveRequest, Administrator.class);

        Officer officer = this.officerService.get(administratorSaveRequest.getAdministratorId());
        saveAdministrator.setOfficer(officer);

        this.administratorService.save(saveAdministrator);

        return ResultHelper.created(this.modelMappperService.forResponse().map(saveAdministrator, AdministratorResponse.class));
    }
}
