    package com.reysas_pdi.backend.business.concretes;

    import com.reysas_pdi.backend.business.abstracts.IPdiFormService;
    import com.reysas_pdi.backend.core.config.result.ResultData;
    import com.reysas_pdi.backend.core.config.result.ResultHelper;
    import com.reysas_pdi.backend.dao.AdministratorRepo;
    import com.reysas_pdi.backend.dao.OfficerRepo;
    import com.reysas_pdi.backend.dao.PdiFormRepo;
    import com.reysas_pdi.backend.dto.request.pdiform.PdiFormSaveRequest;
    import com.reysas_pdi.backend.entity.Administrator;
    import com.reysas_pdi.backend.entity.Officer;
    import com.reysas_pdi.backend.entity.PdiForm;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.security.Principal;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class PdiFormManager implements IPdiFormService {

        @Autowired
        private OfficerRepo officerRepo;
        @Autowired
        private AdministratorRepo administratorRepo;

        private final PdiFormRepo pdiFormRepo;

        public PdiFormManager(PdiFormRepo pdiFormRepo) {
            this.pdiFormRepo = pdiFormRepo;
        }

        @Override
        public ResultData<PdiForm> save(PdiFormSaveRequest pdiFormSaveRequest, Principal principal) {
            String email = principal.getName();
            System.out.println("Giriş yapan e-posta: " + email);

            PdiForm form = buildFormFromRequest(pdiFormSaveRequest); // her iki rol için ortak form
            form.setCreatedByEmail(email);

            Optional<Administrator> adminOpt = administratorRepo.findByEmail(email);
            System.out.println("Admin bulundu mu? " + adminOpt.isPresent());

            if (adminOpt.isPresent()) {
                form.setCreatedByRole("ADMIN");
                form.setOfficer(null); // ❗ Admin formuna officer iliştirme!
                return ResultHelper.created(pdiFormRepo.save(form));
            }

            Officer officer = officerRepo.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Officer bulunamadı"));

            form.setCreatedByRole("OFFICER");
            form.setOfficer(officer);
            return ResultHelper.created(pdiFormRepo.save(form));
        }

        private PdiForm buildFormFromRequest(PdiFormSaveRequest req) {
            return PdiForm.builder()
                    .pdiYeri(req.getPdiYeri())
                    .model(req.getModel())
                    .vin(req.getVin())
                    .kmBilgisi(req.getKmBilgisi())
                    .kontrolTarihi(req.getKontrolTarihi())
                    .fuelLitres1(req.getFuelLitres1())
                    .fuelLitres2(req.getFuelLitres2())
                    .fuelTypeBenzin1(req.getFuelTypeBenzin1())
                    .fuelTypeBenzin2(req.getFuelTypeBenzin2())
                    .gurasyon(req.getGurasyon())
                    .firstAid(req.getFirstaid())
                    .additionalNotes(req.getAdditionalNotes())
                    .solOnKapi(req.getSolOnKapi())
                    .sagOnKapi(req.getSagOnKapi())
                    .onKaput(req.getOnKaput())
                    .arkaTampon(req.getArkaTampon())
                    .tavan(req.getTavan())
                    .onTampon(req.getOnTampon())
                    .arkaBagaj(req.getArkaBagaj())
                    .sagOnCamurluk(req.getSagOnCamurluk())
                    .solOnCamurluk(req.getSolOnCamurluk())
                    .sagArkaCamurluk(req.getSagArkaCamurluk())
                    .solArkaCamurluk(req.getSolArkaCamurluk())
                    .sagArkaKapi(req.getSagArkaKapi())
                    .solArkaKapi(req.getSolArkaKapi())
                    .functionalCheck0(req.getFunctionalCheck0())
                    .functionalCheck1(req.getFunctionalCheck1())
                    .functionalCheck2(req.getFunctionalCheck2())
                    .functionalCheck3(req.getFunctionalCheck3())
                    .functionalCheck4(req.getFunctionalCheck4())
                    .functionalCheck5(req.getFunctionalCheck5())
                    .functionalCheck6(req.getFunctionalCheck6())
                    .functionalCheck7(req.getFunctionalCheck7())
                    .functionalCheck8(req.getFunctionalCheck8())
                    .functionalCheck9(req.getFunctionalCheck9())
                    .functionalCheck10(req.getFunctionalCheck10())
                    .functionalCheck11(req.getFunctionalCheck11())
                    .functionalCheck12(req.getFunctionalCheck12())
                    .functionalCheck13(req.getFunctionalCheck13())
                    .functionalCheck14(req.getFunctionalCheck14())
                    .functionalCheck15(req.getFunctionalCheck15())
                    .functionalCheck16(req.getFunctionalCheck16())
                    .functionalCheck17(req.getFunctionalCheck17())
                    .functionalCheck18(req.getFunctionalCheck18())
                    .functionalCheck19(req.getFunctionalCheck19())
                    .functionalCheck20(req.getFunctionalCheck20())
                    .functionalCheck21(req.getFunctionalCheck21())
                    .functionalCheck22(req.getFunctionalCheck22())
                    .functionalCheck23(req.getFunctionalCheck23())
                    .functionalCheck24(req.getFunctionalCheck24())
                    .functionalCheck25(req.getFunctionalCheck25())
                    .functionalCheck26(req.getFunctionalCheck26())
                    .functionalCheck27(req.getFunctionalCheck27())
                    .functionalCheck28(req.getFunctionalCheck28())
                    .damageDescription0(req.getDamageDescription0())
                    .damageDescription1(req.getDamageDescription1())
                    .damageDescription2(req.getDamageDescription2())
                    .damageDescription3(req.getDamageDescription3())
                    .damageDescription4(req.getDamageDescription4())
                    .damageDescription5(req.getDamageDescription5())
                    .damageDescription6(req.getDamageDescription6())
                    .damageDescription7(req.getDamageDescription7())
                    .damageDescription8(req.getDamageDescription8())
                    .build();
        }


        @Override
        public PdiForm getById(Long id) {
            return pdiFormRepo.findById(id).orElseThrow(() -> new RuntimeException("Form Bulunamadı"));
        }

        @Override
        public void delete(Long id) {
            // Silme işlemi burada olacak
        }

        @Override
        public List<PdiForm> findByVin(String vin) {
            return List.of();
        }

        @Override
        public List<PdiForm> findByPdiYeri(String pdiYeri) {
            return List.of();
        }

        @Override
        public List<PdiForm> findByModel(String model) {
            return List.of();
        }

        @Override
        public List<PdiForm> findAll() {
            return pdiFormRepo.findAll();
        }

        @Override
        public List<PdiForm> findByOfficer(Long officerId) {
            Officer officer = officerRepo.findById(officerId)
                    .orElseThrow(() -> new RuntimeException("Officer not found"));

            return pdiFormRepo.findByOfficer(officer);
        }
    }
