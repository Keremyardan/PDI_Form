    package com.reysas_pdi.backend.api;

    import com.reysas_pdi.backend.business.abstracts.IAdministratorService;
    import com.reysas_pdi.backend.business.abstracts.IOfficerService;

    import com.reysas_pdi.backend.core.config.result.ResultHelper;
    import com.reysas_pdi.backend.core.modelMapper.IModelMapperService;
    import com.reysas_pdi.backend.dto.request.administrator.AdministratorSaveRequest;
    import com.reysas_pdi.backend.dto.request.administrator.AdministratorUpdateRequest;
    import com.reysas_pdi.backend.dto.response.administrator.AdministratorResponse;
    import com.reysas_pdi.backend.core.config.result.ResultData;
    import com.reysas_pdi.backend.entity.Administrator;
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
        ) {
            this.administratorService = administratorService;
            this.modelMappperService = modelMapperService;
            this.officerService = officerService;
        }

        @PostMapping("/save")
        @ResponseStatus(HttpStatus.CREATED)
        public ResultData<AdministratorResponse> save(@Valid @RequestBody AdministratorSaveRequest administratorSaveRequest) {
            Administrator saveAdministrator = this.modelMappperService.forRequest().map(administratorSaveRequest, Administrator.class);
            ResultData<Administrator> result = this.administratorService.save(saveAdministrator);

            if(result.isSuccess()) {
                return new ResultData<>(true,result.getMessage(), "200", null);
            }
    return ResultHelper.created(this.modelMappperService.forResponse().map(result.getData(), AdministratorResponse.class));
        }

        @PutMapping("/update")
        @ResponseStatus(HttpStatus.OK)
        public ResultData<AdministratorResponse> save(@Valid @RequestBody AdministratorUpdateRequest administratorUpdateRequest) {
            Administrator administrator = this.modelMappperService.forRequest().map(administratorUpdateRequest, Administrator.class);

            ResultData<Administrator> resultData = this.administratorService.update(administratorUpdateRequest.getId(), administrator);

            if(!resultData.isSuccess()) {
                return new ResultData<>(false,resultData.getMessage(), "400", null);
            }
            AdministratorResponse administratorResponse = this.modelMappperService.forResponse().map(resultData.getData(), AdministratorResponse.class);

            return ResultHelper.success(administratorResponse);
        }
    }
