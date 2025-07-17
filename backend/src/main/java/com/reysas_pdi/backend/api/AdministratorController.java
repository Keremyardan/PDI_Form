    package com.reysas_pdi.backend.api;

    import com.reysas_pdi.backend.business.abstracts.IAdministratorService;
    import com.reysas_pdi.backend.business.abstracts.IOfficerService;

    import com.reysas_pdi.backend.core.config.result.ResultHelper;
    import com.reysas_pdi.backend.core.modelMapper.IModelMapperService;
    import com.reysas_pdi.backend.dto.request.administrator.AdministratorSaveRequest;
    import com.reysas_pdi.backend.dto.request.administrator.AdministratorUpdateRequest;
    import com.reysas_pdi.backend.dto.request.officer.OfficerSaveRequest;
    import com.reysas_pdi.backend.dto.response.administrator.AdministratorResponse;
    import com.reysas_pdi.backend.core.config.result.ResultData;
    import com.reysas_pdi.backend.dto.response.officer.OfficerResponse;
    import com.reysas_pdi.backend.entity.Administrator;
    import com.reysas_pdi.backend.entity.Officer;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;

    import javax.validation.Valid;
    import java.util.List;

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
        ) {
            this.administratorService = administratorService;
            this.modelMappperService = modelMapperService;
            this.officerService = officerService;
        }


        @PostMapping("/save")
        @ResponseStatus(HttpStatus.CREATED)
        public ResultData<AdministratorResponse> save(@Valid @RequestBody AdministratorSaveRequest administratorSaveRequest) {
            Administrator administrator = this.modelMappperService.forRequest().map(administratorSaveRequest, Administrator.class);
            Administrator saveAdmin = this.administratorService.save(administrator);
            return ResultHelper.created(this.modelMappperService.forResponse().map(saveAdmin, AdministratorResponse.class));
        }


        @PutMapping("/update")
        @ResponseStatus(HttpStatus.OK)
        public ResultData<AdministratorResponse> update(@Valid @RequestBody AdministratorUpdateRequest administratorUpdateRequest) {
            Administrator administrator = this.modelMappperService.forRequest().map(administratorUpdateRequest, Administrator.class);
            ResultData<Administrator> resultData = this.administratorService.update(administratorUpdateRequest.getId(), administrator);
            if(!resultData.isSuccess()) {
                return new ResultData<>(false, resultData.getMessage(), "400", null);
            }
            AdministratorResponse response = this.modelMappperService.forResponse().map(resultData.getData(), AdministratorResponse.class);
            return ResultHelper.success(response);
        }


        @DeleteMapping("/delete-admin/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResultData<String> deleteAdmin(@PathVariable Long id) {
            return this.administratorService.delete(id);
        }


        @PostMapping("/create-officer")
        @ResponseStatus(HttpStatus.CREATED)
        public ResultData<OfficerResponse> createOfficer(@Valid @RequestBody OfficerSaveRequest officerSaveRequest) {
            Officer officer = this.modelMappperService.forRequest().map(officerSaveRequest, Officer.class);
            Officer savedOfficer = this.officerService.saveOfficer(officer);
            OfficerResponse response = this.modelMappperService.forResponse().map(savedOfficer, OfficerResponse.class);
            return ResultHelper.created(response);
        }


        @DeleteMapping("/delete-officer/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResultData<String> deleteOfficer(@PathVariable Long id) {
            return this.officerService.delete(id);
        }

        @GetMapping("/all-officers")
        @ResponseStatus(HttpStatus.OK)
        public ResultData<List<OfficerResponse>> getAllOfficers() {
            List<Officer> officers = this.officerService.getAll();
            List<OfficerResponse> responseList = officers.stream()
                    .map(officer -> this.modelMappperService.forResponse().map(officer, OfficerResponse.class))
                    .toList();
            return ResultHelper.success(responseList);
        }


        @GetMapping("/all-admins")
        @ResponseStatus(HttpStatus.OK)
        public ResultData<List<AdministratorResponse>> getAllAdmins() {
            List<Administrator> admins = this.administratorService.getAll();
            List<AdministratorResponse> responseList = admins.stream()
                    .map(admin -> this.modelMappperService.forResponse().map(admin, AdministratorResponse.class))
                    .toList();
            return ResultHelper.success(responseList);
        }

    }
