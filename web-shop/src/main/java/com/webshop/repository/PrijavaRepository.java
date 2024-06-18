package com.webshop.repository;

import com.webshop.model.PrijavaProfila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrijavaRepository extends JpaRepository<PrijavaProfila,Long> {
    PrijavaProfila findPrijavaProfilaById(Long id);
}
