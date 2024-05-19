package com.webshop.repository;

import com.webshop.model.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija,Long> {

    Kategorija findKategorijaByNaziv(String naziv);

    boolean existsKategorijaByNaziv(String naziv);
}
