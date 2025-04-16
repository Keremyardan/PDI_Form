package com.reysas_pdi.backend.business.concretes;

import com.reysas_pdi.backend.business.abstracts.IAdministratorService;
import com.reysas_pdi.backend.core.config.utilities.Msg;
import com.reysas_pdi.backend.core.exceptions.NotFoundException;
import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.entity.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorManager implements IAdministratorService {
    private final AdministratorRepo administratorRepo;

    public AdministratorManager(AdministratorRepo administratorRepo){
        this.administratorRepo = administratorRepo;
    }


    @Override
    public Page<Administrator> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return this.administratorRepo.findAll(pageable);
    }

    @Override
    public Administrator save(Administrator administrator) {

        return this.administratorRepo.save(administrator);
    }

    @Override
    public Administrator update(Long id, Administrator administrator) {
        Administrator existingAdministrator = this.administratorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        existingAdministrator.setName(administrator.getName());

        return this.administratorRepo.save(existingAdministrator);
    }

    @Override
    public Administrator getById(Long id) {
        return this.administratorRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        Administrator administrator = this.administratorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.administratorRepo.delete(administrator);
    }

    @Override
    public List<Administrator> findByNameContainingIgnoreCase(String name) {
       return this.administratorRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Administrator> findByAdministratorId(Long administratorId) {
        return this.administratorRepo.findAllByAdministratorId(administratorId);
    }
}
