package com.reysas_pdi.backend.api;

import com.reysas_pdi.backend.business.abstracts.IPdiFormService;
import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.dto.request.pdiform.PdiFormSaveRequest;
import com.reysas_pdi.backend.entity.PdiForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pdi-form")
public class PdiFormController {

   private final IPdiFormService iPdiFormService;

    public PdiFormController(IPdiFormService iPdiFormService) {
        this.iPdiFormService = iPdiFormService;
    }

    @PostMapping("/submit")
    @PreAuthorize("hasAnyRole('ADMIN','OFFICER')")
    public ResponseEntity<ResultData<PdiForm>> submitForm(@RequestBody PdiFormSaveRequest request) {
        ResultData<PdiForm> result = iPdiFormService.save(request);
        return ResponseEntity.status(201).body(result);
    }






    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','OFFICER')")
    public ResponseEntity<List<PdiForm>> findAll() {
        return ResponseEntity.ok(this.iPdiFormService.findAll());
    }

    @GetMapping("/by-officer/{officerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER')")
    public ResponseEntity<List<PdiForm>> getByOfficer(@PathVariable Long officerId) {
        return ResponseEntity.ok(iPdiFormService.findByOfficer(officerId));
    }



}
