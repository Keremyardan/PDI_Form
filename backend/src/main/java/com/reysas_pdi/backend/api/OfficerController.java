package com.reysas_pdi.backend.api;

import com.reysas_pdi.backend.business.abstracts.IAdministratorService;
import com.reysas_pdi.backend.business.abstracts.IOfficerService;
import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.core.config.result.ResultHelper;
import com.reysas_pdi.backend.core.modelMapper.IModelMapperService;
import com.reysas_pdi.backend.dao.PdiFormRepo;
import com.reysas_pdi.backend.dto.request.officer.OfficerSaveRequest;
import com.reysas_pdi.backend.dto.response.officer.OfficerResponse;
import com.reysas_pdi.backend.dto.response.pdiform.PdiFormResponse;
import com.reysas_pdi.backend.entity.Administrator;
import com.reysas_pdi.backend.entity.Officer;
import com.reysas_pdi.backend.entity.PdiForm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/officers")
public class OfficerController {
    private final IAdministratorService administratorService;
    private final IOfficerService officerService;
    private final IModelMapperService modelMapperService;
    private final PdiFormRepo pdiFormRepo;

    public OfficerController(IAdministratorService administratorService, IOfficerService iOfficerService, IModelMapperService modelMapperService, PdiFormRepo pdiFormRepo) {
        this.administratorService = administratorService;
        this.officerService = iOfficerService;
        this.modelMapperService = modelMapperService;
        this.pdiFormRepo = pdiFormRepo;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<OfficerResponse> save(@Valid @RequestBody OfficerSaveRequest officerSaveRequest) {
      Officer saveOfficer = this.modelMapperService.forRequest().map(officerSaveRequest, Officer.class);

      Administrator administrator =this.administratorService.getById(officerSaveRequest.getAdministratorId());
      saveOfficer.setAdministrator(administrator);

      this.officerService.saveOfficer(saveOfficer);

      return ResultHelper.created(this.modelMapperService.forResponse().map(saveOfficer, OfficerResponse.class));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<OfficerResponse> update(@PathVariable("id") Long id, @Valid @RequestBody OfficerSaveRequest officerSaveRequest) {
        Officer officer = this.modelMapperService.forRequest().map(officerSaveRequest, Officer.class);

        Officer updatedOfficer = this.officerService.update(id, officer);

        return ResultHelper.success(this.modelMapperService.forRequest().map(updatedOfficer,OfficerResponse.class));
    }

    @GetMapping("/officer/{officerId}/pdiforms")
    public ResultData<List<PdiFormResponse>> getPdiFormsByOfficer(@PathVariable Long officerId) {
        Officer officer = officerService.get(officerId);
        List<PdiForm> forms = pdiFormRepo.findByOfficer(officer);
        List<PdiFormResponse> response = forms.stream()
                .map(form -> modelMapperService.forResponse().map(form, PdiFormResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(response);
    }

    @GetMapping("/all-officers")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<OfficerResponse>> getAllOfficers() {
        List<Officer> officers = this.officerService.getAll();
        List<OfficerResponse> responseList = officers.stream()
                .map(officer -> this.modelMapperService.forResponse().map(officer, OfficerResponse.class))
                .toList();
        return ResultHelper.success(responseList);
    }


}
