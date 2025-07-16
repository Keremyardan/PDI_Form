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

    @GetMapping("/all")
    public List<PdiForm> getForms(Principal principal) {
        String email = principal.getName();

        Optional<Officer> officerOpt = officerRepo.findByEmail(email);
        if (officerOpt.isPresent()) {
            return pdiFormRepo.findByCreatedByEmailAndCreatedByRole(email, "OFFICER");
        }

        Optional<Administrator> adminOpt = administratorRepo.findByEmail(email);
        if (adminOpt.isPresent()) {
            return pdiFormRepo.findAll(); // admin tüm formları görür
        }

        throw new RuntimeException("Kullanıcı bulunamadı");
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
                .fuelTypeBenzin2(entity.getFuelTypeBenzin2())
                .gurasyon(entity.getGurasyon())
                .firstaid(entity.getFirstAid())
                .additionalNotes(entity.getAdditionalNotes())
                .solOnKapi(entity.getSolOnKapi())
                .sagOnKapi(entity.getSagOnKapi())
                .onKaput(entity.getOnKaput())
                .arkaTampon(entity.getArkaTampon())
                .tavan(entity.getTavan())
                .onTampon(entity.getOnTampon())
                .arkaBagaj(entity.getArkaBagaj())
                .sagOnCamurluk(entity.getSagOnCamurluk())
                .solOnCamurluk(entity.getSolOnCamurluk())
                .sagArkaCamurluk(entity.getSagArkaCamurluk())
                .solArkaCamurluk(entity.getSolArkaCamurluk())
                .sagArkaKapi(entity.getSagArkaKapi())
                .solArkaKapi(entity.getSolArkaKapi())
                .functionalCheck0(entity.getFunctionalCheck0())
                .functionalCheck1(entity.getFunctionalCheck1())
                .functionalCheck2(entity.getFunctionalCheck2())
                .functionalCheck3(entity.getFunctionalCheck3())
                .functionalCheck4(entity.getFunctionalCheck4())
                .functionalCheck5(entity.getFunctionalCheck5())
                .functionalCheck6(entity.getFunctionalCheck6())
                .functionalCheck7(entity.getFunctionalCheck7())
                .functionalCheck8(entity.getFunctionalCheck8())
                .functionalCheck9(entity.getFunctionalCheck9())
                .functionalCheck10(entity.getFunctionalCheck10())
                .functionalCheck11(entity.getFunctionalCheck11())
                .functionalCheck12(entity.getFunctionalCheck12())
                .functionalCheck13(entity.getFunctionalCheck13())
                .functionalCheck14(entity.getFunctionalCheck14())
                .functionalCheck15(entity.getFunctionalCheck15())
                .functionalCheck16(entity.getFunctionalCheck16())
                .functionalCheck17(entity.getFunctionalCheck17())
                .functionalCheck18(entity.getFunctionalCheck18())
                .functionalCheck19(entity.getFunctionalCheck19())
                .functionalCheck20(entity.getFunctionalCheck20())
                .functionalCheck21(entity.getFunctionalCheck21())
                .functionalCheck22(entity.getFunctionalCheck22())
                .functionalCheck23(entity.getFunctionalCheck23())
                .functionalCheck24(entity.getFunctionalCheck24())
                .functionalCheck25(entity.getFunctionalCheck25())
                .functionalCheck26(entity.getFunctionalCheck26())
                .functionalCheck27(entity.getFunctionalCheck27())
                .functionalCheck28(entity.getFunctionalCheck28())
                .createdAt(entity.getCreatedAt())
                .createdByName(entity.getOfficer() != null ? entity.getOfficer().getName() : null)
                .damageDescription0(entity.getDamageDescription0())
                .damageDescription1(entity.getDamageDescription1())
                .damageDescription2(entity.getDamageDescription2())
                .damageDescription3(entity.getDamageDescription3())
                .damageDescription4(entity.getDamageDescription4())
                .damageDescription5(entity.getDamageDescription5())
                .damageDescription6(entity.getDamageDescription6())
                .damageDescription7(entity.getDamageDescription7())
                .damageDescription8(entity.getDamageDescription8())

                .build();


        return ResponseEntity.ok(dto);
    }
}
