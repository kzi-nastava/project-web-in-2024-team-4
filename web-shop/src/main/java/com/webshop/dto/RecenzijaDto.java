package com.webshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.model.Korisnik;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.webshop.model.Recenzija}
 */
public class RecenzijaDto implements Serializable {
    private final long id;
    private final double ocena;
    private final String komentar;
    private final LocalDate datumRecenzije;
    private Korisnik korisnikPrimio;
    private Korisnik korisnikDao;

    public RecenzijaDto(long id, double ocena, String komentar, LocalDate datumRecenzije,Korisnik korisnikPrimio,Korisnik korisnikDao) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.datumRecenzije = datumRecenzije;
        this.korisnikPrimio=korisnikPrimio;
        this.korisnikDao=korisnikDao;
    }

    public long getId() {
        return id;
    }

    public double getOcena() {
        return ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public LocalDate getDatumRecenzije() {
        return datumRecenzije;
    }

    public Korisnik getKorisnikPrimio() {
        return korisnikPrimio;
    }

    public void setKorisnikPrimio(Korisnik korisnikPrimio) {
        this.korisnikPrimio = korisnikPrimio;
    }

    public Korisnik getKorisnikDao() {
        return korisnikDao;
    }

    public void setKorisnikDao(Korisnik korisnikDao) {
        this.korisnikDao = korisnikDao;
    }
}