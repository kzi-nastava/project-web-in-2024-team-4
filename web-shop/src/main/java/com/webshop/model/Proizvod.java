package com.webshop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.Enumeracije.TipProdaje;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Proizvod implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String naziv;
    private String opis;
    private String slika;//Putanja do slike
    //Kategorija
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Kategorija kategorija;
    private double cena;
    @Enumerated(EnumType.STRING)
    private TipProdaje.tipProdaje tipProdaje;
    @Temporal(TemporalType.DATE)
    private LocalDate datumObjavljivanja;

    //Ponuda
    @OneToMany(mappedBy = "proizvod",fetch = FetchType.EAGER)
    private List<Ponuda>ponude=new ArrayList<>();

    //Prodavac
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Korisnik prodavac;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Korisnik kupac;
    private boolean ostavljenaRecenzijaKupac;
    private boolean ostavljenaRecenzijaProdavac;

    private boolean prodat;

        //Getters and Setters


    public LocalDate getDatumObjavljivanja() {
        return datumObjavljivanja;
    }

    public List<Ponuda> getPonude() {
        return ponude;
    }

    public void setPonude(List<Ponuda> ponude) {
        this.ponude = ponude;
    }

    public void setProdavac(Korisnik prodavac) {
        this.prodavac = prodavac;
    }

    public void setKupac(Korisnik kupac) {
        this.kupac = kupac;
    }

    public boolean isOstavljenaRecenzijaKupac() {
        return ostavljenaRecenzijaKupac;
    }

    public void setOstavljenaRecenzijaKupac(boolean ostavljenaRecenzijaKupac) {
        this.ostavljenaRecenzijaKupac = ostavljenaRecenzijaKupac;
    }

    public boolean isOstavljenaRecenzijaProdavac() {
        return ostavljenaRecenzijaProdavac;
    }

    public void setOstavljenaRecenzijaProdavac(boolean ostavljenaRecenzijaProdavac) {
        this.ostavljenaRecenzijaProdavac = ostavljenaRecenzijaProdavac;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public TipProdaje.tipProdaje getTipProdaje() {
        return tipProdaje;
    }

    public void setTipProdaje(TipProdaje.tipProdaje tipProdaje) {
        this.tipProdaje = tipProdaje;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Korisnik getProdavac() {
        return prodavac;
    }

    public Korisnik getKupac() {
        return kupac;
    }

    public boolean isProdat() {
        return prodat;
    }

    public void setProdat(boolean prodat) {
        this.prodat = prodat;
    }

    public void setDatumObjavljivanja(LocalDate datumObjavljivanja) {
        this.datumObjavljivanja = datumObjavljivanja;
    }
}

