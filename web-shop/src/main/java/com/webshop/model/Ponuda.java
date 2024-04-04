package com.webshop.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serializable;

@Entity
public class Ponuda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPonuda;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private  Proizvod proizvod;
    @ManyToOne
    private Kupac kupac;
    private double cena;

    public Long getIdPonuda() {
        return idPonuda;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public double getCena() {
        return cena;
    }

    public void setIdPonuda(Long idPonuda) {
        this.idPonuda = idPonuda;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}

