package com.reysas_pdi.backend.dao;

import com.reysas_pdi.backend.entity.Officer;
import com.reysas_pdi.backend.entity.PdiForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficerRepo extends JpaRepository<Officer, Long> {
    @Query("SELECT o FROM Officer o WHERE LOWER(o.name) LIKE LOWER (CONCAT('%', :name, '%'))")
    List<Officer> findByNameContainingIgnoreCase(@Param("name") String name);

    Optional<Officer> findByEmail (String email);

    boolean existsByEmail(String email);

}
