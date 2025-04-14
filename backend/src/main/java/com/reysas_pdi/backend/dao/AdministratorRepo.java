package com.reysas_pdi.backend.dao;


import com.reysas_pdi.backend.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdministratorRepo extends JpaRepository<Administrator, Long> {
    @Query("SELECT a FROM Administrator a WHERE a.id = :AdministratorId")
    List<Administrator> findAllByAdministratorId(@Param("administratorId") Long AdministratorId);
}
