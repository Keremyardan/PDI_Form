package com.reysas_pdi.backend.api;

import com.reysas_pdi.backend.business.abstracts.IAdministratorService;
import com.reysas_pdi.backend.business.abstracts.IOfficerService;
import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.core.config.result.ResultHelper;
import com.reysas_pdi.backend.core.config.utilities.Msg;
import com.reysas_pdi.backend.core.modelMapper.IModelMapperService;
import com.reysas_pdi.backend.dto.request.OfficerSaveRequest;
import com.reysas_pdi.backend.dto.response.OfficerResponse;
import com.reysas_pdi.backend.entity.Officer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/officers")
public class OfficerController {
    private final IAdministratorService administratorService;
    private final IOfficerService officerService;
    private final IModelMapperService modelMapperService;

    public OfficerController(IAdministratorService administratorService, IOfficerService iOfficerService, IModelMapperService modelMapperService) {
        this.administratorService = administratorService;
        this.officerService = iOfficerService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<OfficerResponse> save(@Valid @RequestBody OfficerSaveRequest officerSaveRequest) {
        Officer saveOfficer = this.modelMapperService.forRequest().map(officerSaveRequest, Officer.class);
        ResultData<Officer> result = this.officerService.save(saveOfficer);

        if(!result.isSuccess()) {
            return new ResultData<>(false, result.getMessage(),"400",null);
        }
        return ResultHelper.created(this.modelMapperService.forResponse().map(result.getData(),OfficerResponse.class));
    }

}
