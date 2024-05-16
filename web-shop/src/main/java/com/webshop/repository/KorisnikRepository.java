package com.webshop.repository;

import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import com.webshop.model.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik,Long> {
    boolean existsByKorisnickoIme(String username);
    boolean existsByEmailAdresa(String email);

    Korisnik findByKorisnickoIme(String korisnickoIme);


}
