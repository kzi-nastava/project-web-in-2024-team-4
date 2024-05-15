package com.webshop.dto;

import com.webshop.model.Korisnik;

import java.io.File;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.webshop.model.Korisnik}
 */
public class PrijavaKorisnikDto implements Serializable {
    private final String korisnickoIme;
    private final String lozinka;

    public PrijavaKorisnikDto(String korisnickoIme, String lozinka){
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }
    public PrijavaKorisnikDto(Korisnik korisnik){
        this.korisnickoIme=korisnik.getKorisnickoIme();
        this.lozinka=korisnik.getLozinka();
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }
}