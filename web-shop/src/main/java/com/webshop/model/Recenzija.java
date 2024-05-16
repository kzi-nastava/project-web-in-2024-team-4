package com.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.apache.catalina.webresources.AbstractResource;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
@Entity
public class Recenzija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private double ocena;
    private String komentar;
    @Temporal(TemporalType.DATE)
    private LocalDate datumRecenzije;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private Korisnik korisnikPrimio;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private Korisnik korisnikDao;
    //Kupac,Prodavac
    //Ko je dao recenziju

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public LocalDate getDatumRecenzije() {
        return datumRecenzije;
    }

    public void setDatumRecenzije(LocalDate datumRecenzije) {
        this.datumRecenzije = datumRecenzije;
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

