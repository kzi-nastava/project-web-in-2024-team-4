package com.webshop.repository;

import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik,Long> {
    boolean existsByKorisnickoIme(String username);
    boolean existsByEmailAdresa(String email);

    Korisnik findByKorisnickoIme(String korisnickoIme);


}
