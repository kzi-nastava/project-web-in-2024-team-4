package com.webshop.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

@Entity
public class Ponuda implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Kupac kupac;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Proizvod proizvod;

    //LcalDateTime ponuda postavljena
    LocalDateTime ponudaPostavljena;
    private double cena;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public LocalDateTime getPonuda_postavljena() {
        return ponudaPostavljena;
    }

    public void setPonuda_postavljena(LocalDateTime ponuda_postavljena) {
        this.ponudaPostavljena = ponuda_postavljena;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}


