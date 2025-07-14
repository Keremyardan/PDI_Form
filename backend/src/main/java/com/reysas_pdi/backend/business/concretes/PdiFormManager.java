    package com.reysas_pdi.backend.business.concretes;

    import com.reysas_pdi.backend.business.abstracts.IPdiFormService;
    import com.reysas_pdi.backend.core.config.result.ResultData;
    import com.reysas_pdi.backend.core.config.result.ResultHelper;
    import com.reysas_pdi.backend.dao.OfficerRepo;
    import com.reysas_pdi.backend.dao.PdiFormRepo;
    import com.reysas_pdi.backend.dto.request.pdiform.PdiFormSaveRequest;
    import com.reysas_pdi.backend.entity.Officer;
    import com.reysas_pdi.backend.entity.PdiForm;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.security.Principal;
    import java.util.List;

    @Service
    public class PdiFormManager implements IPdiFormService {

        @Autowired
        private OfficerRepo officerRepo;

        private final PdiFormRepo pdiFormRepo;

        public PdiFormManager(PdiFormRepo pdiFormRepo) {
            this.pdiFormRepo = pdiFormRepo;
        }

        @Override
        public ResultData<PdiForm> save(PdiFormSaveRequest pdiFormSaveRequest, Principal principal) {
            String username = principal.getName();
            Officer officer = officerRepo.findByEmail(username)
                    .orElseThrow(() -> new RuntimeException("Officer bulunamadı"));

            PdiForm form = PdiForm.builder()
                    .pdiYeri(pdiFormSaveRequest.getPdiYeri())
                    .model(pdiFormSaveRequest.getModel())
                    .vin(pdiFormSaveRequest.getVin())
                    .kmBilgisi(pdiFormSaveRequest.getKmBilgisi())
                    .kontrolTarihi(pdiFormSaveRequest.getKontrolTarihi())
                    .fuelLitres1(pdiFormSaveRequest.getFuelLitres1())
                    .fuelLitres2(pdiFormSaveRequest.getFuelLitres2())
                    .fuelTypeBenzin1(pdiFormSaveRequest.getFuelTypeBenzin1())
                    .fuelTypeBenzin2(pdiFormSaveRequest.getFuelTypeBenzin2())
                    .gurasyon(pdiFormSaveRequest.getGurasyon())
                    .firstAid(pdiFormSaveRequest.getFirstaid())
                    .additionalNotes(pdiFormSaveRequest.getAdditionalNotes())
                    .solOnKapi(pdiFormSaveRequest.getSolOnKapi())
                    .sagOnKapi(pdiFormSaveRequest.getSagOnKapi())
                    .onKaput(pdiFormSaveRequest.getOnKaput())
                    .arkaTampon(pdiFormSaveRequest.getArkaTampon())
                    .tavan(pdiFormSaveRequest.getTavan())
                    .onTampon(pdiFormSaveRequest.getOnTampon())
                    .arkaBagaj(pdiFormSaveRequest.getArkaBagaj())
                    .sagOnCamurluk(pdiFormSaveRequest.getSagOnCamurluk())
                    .solOnCamurluk(pdiFormSaveRequest.getSolOnCamurluk())
                    .sagArkaCamurluk(pdiFormSaveRequest.getSagArkaCamurluk())
                    .solArkaCamurluk(pdiFormSaveRequest.getSolArkaCamurluk())
                    .sagArkaKapi(pdiFormSaveRequest.getSagArkaKapi())
                    .solArkaKapi(pdiFormSaveRequest.getSolArkaKapi())
                    .officer(officer)
                    .functionalCheck0(pdiFormSaveRequest.getFunctionalCheck0())
                    .functionalCheck1(pdiFormSaveRequest.getFunctionalCheck1())
                    .functionalCheck2(pdiFormSaveRequest.getFunctionalCheck2())
                    .functionalCheck3(pdiFormSaveRequest.getFunctionalCheck3())
                    .functionalCheck4(pdiFormSaveRequest.getFunctionalCheck4())
                    .functionalCheck5(pdiFormSaveRequest.getFunctionalCheck5())
                    .functionalCheck6(pdiFormSaveRequest.getFunctionalCheck6())
                    .functionalCheck7(pdiFormSaveRequest.getFunctionalCheck7())
                    .functionalCheck8(pdiFormSaveRequest.getFunctionalCheck8())
                    .functionalCheck9(pdiFormSaveRequest.getFunctionalCheck9())
                    .functionalCheck10(pdiFormSaveRequest.getFunctionalCheck10())
                    .functionalCheck11(pdiFormSaveRequest.getFunctionalCheck11())
                    .functionalCheck12(pdiFormSaveRequest.getFunctionalCheck12())
                    .functionalCheck13(pdiFormSaveRequest.getFunctionalCheck13())
                    .functionalCheck14(pdiFormSaveRequest.getFunctionalCheck14())
                    .functionalCheck15(pdiFormSaveRequest.getFunctionalCheck15())
                    .functionalCheck16(pdiFormSaveRequest.getFunctionalCheck16())
                    .functionalCheck17(pdiFormSaveRequest.getFunctionalCheck17())
                    .functionalCheck18(pdiFormSaveRequest.getFunctionalCheck18())
                    .functionalCheck19(pdiFormSaveRequest.getFunctionalCheck19())
                    .functionalCheck20(pdiFormSaveRequest.getFunctionalCheck20())
                    .functionalCheck21(pdiFormSaveRequest.getFunctionalCheck21())
                    .functionalCheck22(pdiFormSaveRequest.getFunctionalCheck22())
                    .functionalCheck23(pdiFormSaveRequest.getFunctionalCheck23())
                    .functionalCheck24(pdiFormSaveRequest.getFunctionalCheck24())
                    .functionalCheck25(pdiFormSaveRequest.getFunctionalCheck25())
                    .functionalCheck26(pdiFormSaveRequest.getFunctionalCheck26())
                    .functionalCheck27(pdiFormSaveRequest.getFunctionalCheck27())
                    .functionalCheck28(pdiFormSaveRequest.getFunctionalCheck28())
                    .damageDescription0(pdiFormSaveRequest.getDamageDescription0())
                    .damageDescription1(pdiFormSaveRequest.getDamageDescription1())
                    .damageDescription2(pdiFormSaveRequest.getDamageDescription2())
                    .damageDescription3(pdiFormSaveRequest.getDamageDescription3())
                    .damageDescription4(pdiFormSaveRequest.getDamageDescription4())
                    .damageDescription5(pdiFormSaveRequest.getDamageDescription5())
                    .damageDescription6(pdiFormSaveRequest.getDamageDescription6())
                    .damageDescription7(pdiFormSaveRequest.getDamageDescription7())
                    .damageDescription8(pdiFormSaveRequest.getDamageDescription8())
                    .build();

            PdiForm saved = pdiFormRepo.save(form);
            return ResultHelper.created(saved);
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
