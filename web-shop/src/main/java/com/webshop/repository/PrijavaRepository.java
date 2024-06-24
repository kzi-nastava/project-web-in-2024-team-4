package com.webshop.repository;

import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrijavaRepository extends JpaRepository<PrijavaProfila,Long> {
    PrijavaProfila findPrijavaProfilaById(Long id);
    List<PrijavaProfila>findAllByKorisnikOdnosiSe(Korisnik korisnik);
}
