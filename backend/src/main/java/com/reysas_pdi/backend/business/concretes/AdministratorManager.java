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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorManager implements IAdministratorService {

    private final AdministratorRepo administratorRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AdministratorManager(AdministratorRepo administratorRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.administratorRepo = administratorRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Page<Administrator> cursor(int page, int size) {
        return null;
    }

    @Override
    public Administrator save(Administrator administrator) {
       administrator.setUserRole(UserRole.ADMIN);
       administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
       return this.administratorRepo.save(administrator);
    }

    @Override
    public ResultData<Administrator> update(Long id, Administrator administrator) {
        Administrator existingAdministrator = this.administratorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        if (administrator.getName() != null && !administrator.getName().isBlank()) {
            existingAdministrator.setName(administrator.getName());
        }

        if (administrator.getPassword() != null && !administrator.getPassword().isBlank()) {
            existingAdministrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        }

        return ResultHelper.success(this.administratorRepo.save(existingAdministrator));
    }



    @Override
    public Administrator getById(Long id) {
        return null;
    }

    @Override
    public ResultData<String> delete(Long id) {
       Administrator administrator = this.administratorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.administratorRepo.delete(administrator);

        return ResultHelper.success("Admin başarıyla silindi.");
    }

    @Override
    public List<Administrator> getAll() {
        return administratorRepo.findAll();
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
