package com.webshop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.KorisnikRegistracijaDto;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("KUPAC")
public class Kupac extends Korisnik{
    @OneToMany(mappedBy ="kupac",fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonIgnore
    private List<Proizvod> kupljeniProizvodi;
    @OneToMany(mappedBy = "korisnikPrimio",fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonIgnore
    private List<Recenzija> dobijeneRecenzije;
    @OneToMany(mappedBy = "korisnikDao",fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonIgnore
    private List<Recenzija> dateRecenzije;
    @OneToMany(mappedBy = "kupac",fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonIgnore
    private List<Ponuda>ponude;
    @Column
    private double prosecnaOcena;

    public List<Proizvod> getKupljeni_proizvodi() {
        return kupljeniProizvodi;
    }

    public void setKupljeni_proizvodi(List<Proizvod> kupljeni_proizvodi) {
        this.kupljeniProizvodi = kupljeni_proizvodi;
    }

    public List<Recenzija> getDobijeneRecenzije() {
        return dobijeneRecenzije;
    }

    public void setDobijeneRecenzije(ArrayList<Recenzija> dobijeneRecenzije) {
        this.dobijeneRecenzije = dobijeneRecenzije;
    }

    public List<Recenzija> getDateRecenzije() {
        return dateRecenzije;
    }

    public void setDateRecenzije(ArrayList<Recenzija> dateRecenzije) {
        this.dateRecenzije = dateRecenzije;
    }

    public List<Ponuda> getPonude() {
        return ponude;
    }

    public void setPonude(ArrayList<Ponuda> ponude) {
        this.ponude = ponude;
    }

    public double getProsecna_ocena() {
        return prosecnaOcena;
    }

    public void setProsecna_ocena(double prosecna_ocena) {
        this.prosecnaOcena = prosecna_ocena;
    }

    public  Kupac(KorisnikRegistracijaDto korisnikRegistracijaDto){
        super(korisnikRegistracijaDto);
        setUloga(UlogaKorisnika.Uloga.KUPAC);
    }
    public Kupac(){}
}

