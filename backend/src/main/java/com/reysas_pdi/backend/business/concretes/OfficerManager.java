package com.reysas_pdi.backend.business.concretes;

import com.reysas_pdi.backend.business.abstracts.IOfficerService;
import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.core.config.utilities.Msg;
import com.reysas_pdi.backend.core.exceptions.NotFoundException;
import com.reysas_pdi.backend.entity.Officer;
import org.hibernate.query.results.ResultsHelper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficerManager implements IOfficerService {
    private final OfficerRepo officerRepo;
    private final ModelMapper modelMapper;

    public OfficerManager(OfficerRepo officerRepo, ModelMapper modelMapper) {
        this.officerRepo = officerRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResultData<Officer> save(Officer officer) {
        if(officerRepo.existsByEmail(officer.getEmail())){
            return ResultHelper.EmailExists();
        }
        Officer savedOfficer = officerRepo.save(officer);
        return ResultHelper.created(savedOfficer);
    }

    @Override
    public Officer get(Long id) {
        return this.officerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        Officer officer = this.officerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.officerRepo.delete(officer);
    }

    @Override
    public ResultData<Officer> update(Long id, Officer officer) {
        Officer existingOfficer = this.officerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        existingOfficer.setName(officer.getName());

        Officer updatedOfficer = this.officerRepo.save(existingOfficer);

        return ResultHelper.success(updatedOfficer);
    }

    @Override
    public Page<Officer> cursor(int page, int size) {
        return null;
    }

    @Override
    public List<Officer> getOfficersByName(String name) {
        return officerRepo.findByNameContainingIgnoreCase(name);
    }
}
