package com.reysas_pdi.backend.business.concretes;

import com.reysas_pdi.backend.business.abstracts.IAdministratorService;
import com.reysas_pdi.backend.core.config.result.ResultData;
import com.reysas_pdi.backend.core.config.result.ResultHelper;
import com.reysas_pdi.backend.core.config.utilities.Msg;
import com.reysas_pdi.backend.core.exceptions.NotFoundException;
import com.reysas_pdi.backend.dao.AdministratorRepo;
import com.reysas_pdi.backend.entity.Administrator;
import com.reysas_pdi.backend.entity.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.beans.Customizer;
import java.util.List;

@Service
public class AdministratorManager implements IAdministratorService {

    private final AdministratorRepo administratorRepo;
    private final ModelMapper modelMapper;

    public AdministratorManager(AdministratorRepo administratorRepo, ModelMapper modelMapper) {
        this.administratorRepo = administratorRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<Administrator> cursor(int page, int size) {
        return null;
    }

    @Override
    public ResultData<Administrator> save(Administrator administrator) {
        if(administratorRepo.existsByEmail(administrator.getEmail())) {
            return ResultHelper.EmailExists();
        }

        administrator.setUserRole(UserRole.ADMIN);
        Administrator savedAdministrator = administratorRepo.save(administrator);
        return ResultHelper.created(savedAdministrator);
    }

    @Override
    public ResultData<Administrator> update(Long id, Administrator administrator) {

        Administrator existingAdministrator = this.administratorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

existingAdministrator.setName(administrator.getName());
existingAdministrator.setEmail(administrator.getEmail());

Administrator updatedAdministrator = this.administratorRepo.save(existingAdministrator);

return ResultHelper.success(updatedAdministrator);
    }


    @Override
    public Administrator getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
       Administrator administrator = this.administratorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.administratorRepo.delete(administrator);
    }

    @Override
    public List<Administrator> findByNameContainingIgnoreCase(String name) {
        return administratorRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Administrator> findByAdministratorId(Long administratorId) {
        return List.of();
    }
}
