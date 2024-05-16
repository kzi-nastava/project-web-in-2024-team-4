package com.webshop.dto;

import com.webshop.model.Korisnik;

import java.io.Serializable;
import java.nio.file.LinkOption;

/**
 * DTO for {@link com.webshop.model.Korisnik}
 */
public class OsnovneInformacijePregledProfilaDto implements Serializable {
    private final Long id;
    private final String ime;
    private final String prezime;

    public OsnovneInformacijePregledProfilaDto(String ime, String prezime,Long id) {
        this.ime = ime;
        this.prezime = prezime;
        this.id=id;
    }
    public OsnovneInformacijePregledProfilaDto(Korisnik korisnik){
        this.ime=korisnik.getIme();
        this.prezime=korisnik.getPrezime();
        this.id=korisnik.getId();
    }
    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }
    public Long getId(){return id;}
}