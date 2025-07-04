package com.reysas_pdi.backend.api;

import com.reysas_pdi.backend.business.abstracts.IPdiFormService;
import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.dao.OfficerRepo;
import com.reysas_pdi.backend.dao.PdiFormRepo;
import com.reysas_pdi.backend.dto.request.pdiform.PdiFormSaveRequest;
import com.reysas_pdi.backend.dto.response.pdiform.PdiFormResponse;
import com.reysas_pdi.backend.entity.Administrator;
import com.reysas_pdi.backend.entity.Officer;
import com.reysas_pdi.backend.entity.PdiForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pdi-form")
public class PdiFormController {

   private final IPdiFormService iPdiFormService;
   private final OfficerRepo officerRepo;
   private final AdministratorRepo administratorRepo;
   private final PdiFormRepo pdiFormRepo;

    public PdiFormController(IPdiFormService iPdiFormService, OfficerRepo officerRepo, AdministratorRepo administratorRepo, PdiFormRepo pdiFormRepo) {
        this.iPdiFormService = iPdiFormService;
        this.officerRepo = officerRepo;
        this.administratorRepo = administratorRepo;
        this.pdiFormRepo = pdiFormRepo;
    }

    @PostMapping("/submit")
    @PreAuthorize("hasAnyRole('ADMIN','OFFICER')")
    public ResponseEntity<ResultData<PdiForm>> submitForm(@RequestBody PdiFormSaveRequest request, Principal principal) {
        ResultData<PdiForm> result = iPdiFormService.save(request, principal);
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

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN', 'OFFICER')")
    public ResponseEntity<List<PdiForm>> getFormsForUser(Principal principal) {
        String userName = principal.getName();
        System.out.println("Giriş yapan kullanıcı: " + userName);

        Optional<Officer> officerOpt = officerRepo.findByEmail(userName);
        if(officerOpt.isPresent()) {
            Officer officer = officerOpt.get();
            List<PdiForm> officerForms = pdiFormRepo.findByOfficer(officer);
            System.out.println("Officer form sayısı: " + officerForms.size());
            return ResponseEntity.ok(officerForms);
        }

        Optional<Administrator> administratorOpt = administratorRepo.findByEmail(userName);
        if(administratorOpt.isPresent()) {
            List<PdiForm> allForms = pdiFormRepo.findAll();
            System.out.println("Admin total form sayısı: " + allForms.size());
            return ResponseEntity.ok(allForms);
        }

        System.out.println("User yetkisi bulunamadı. 403 dönüyorum.");
        return ResponseEntity.status(403).build();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','OFFICER')")
    public ResponseEntity<PdiFormResponse> getFormById(@PathVariable Long id) {

        PdiForm entity = pdiFormRepo.findById(id).orElseThrow(() -> new RuntimeException("Form Bululamadı"));

        PdiFormResponse dto = PdiFormResponse.builder()
                .pdiYeri(entity.getPdiYeri())
                .model(entity.getModel())
                .vin(entity.getVin())
                .kmBilgisi(entity.getKmBilgisi())
                .kontrolTarihi(entity.getKontrolTarihi())
                .fuelLitres1(entity.getFuelLitres1())
                .fuelLitres2(entity.getFuelLitres2())
                .fuelTypeBenzin1(entity.getFuelTypeBenzin1())
                .fuelTypeBenzin1(entity.getFuelTypeBenzin2())
                .gurasyon(entity.getGurasyon())
                .firstaid(entity.getFirstAid())
                .additionalNotes(entity.getAdditionalNotes())
                .build();

        return ResponseEntity.ok(dto);
    }
}
