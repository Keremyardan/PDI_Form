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
    public ResultData<PdiForm> save(PdiFormSaveRequest pdiFormSaveRequest) {
        PdiForm form = PdiForm.builder().
                pdiYeri(pdiFormSaveRequest.getPdiYeri())
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
                .additionalNotes(pdiFormSaveRequest.getAdditionalNotes()).selectedParts(pdiFormSaveRequest.getSelectedParts())
                .build();




        PdiForm saved = pdiFormRepo.save(form);
        return ResultHelper.created(saved);
    }

    @Override
    public PdiForm getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

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
