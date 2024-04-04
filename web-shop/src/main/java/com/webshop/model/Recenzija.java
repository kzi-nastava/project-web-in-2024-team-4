package com.webshop.model;

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
    private Long idRecenzije;
    private double ocena;
    private  String komentar;
    @Temporal(TemporalType.DATE)
    private LocalDate datum_recenzije;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Korisnik daoRecenziju;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac recenzijaKupca;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Prodavac recenzijeProdavca;

    public Long getIdRecenzije() {
        return idRecenzije;
    }

    public void setIdRecenzije(Long idRecenzije) {
        this.idRecenzije = idRecenzije;
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

    public LocalDate getDatum_recenzije() {
        return datum_recenzije;
    }

    public void setDatum_recenzije(LocalDate datum_recenzije) {
        this.datum_recenzije = datum_recenzije;
    }

    public Korisnik getDaoRecenziju() {
        return daoRecenziju;
    }

    public void setDaoRecenziju(Korisnik daoRecenziju) {
        this.daoRecenziju = daoRecenziju;
    }

    public Kupac getRecenzijaKupca() {
        return recenzijaKupca;
    }

    public void setRecenzijaKupca(Kupac recenzijaKupca) {
        this.recenzijaKupca = recenzijaKupca;
    }

    public Prodavac getRecenzijeProdavca() {
        return recenzijeProdavca;
    }

    public void setRecenzijeProdavca(Prodavac recenzijeProdavca) {
        this.recenzijeProdavca = recenzijeProdavca;
    }
}
