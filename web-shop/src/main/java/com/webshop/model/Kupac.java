package com.webshop.model;


import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.KorisnikRegistracijaDto;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
@DiscriminatorValue("KUPAC")
public class Kupac extends Korisnik{
    @OneToMany(mappedBy ="kupac",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Proizvod> kupljeni_proizvodi;
    @OneToMany(mappedBy = "korisnik_primio",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Recenzija> dobijeneRecenzije;
    @OneToMany(mappedBy = "korisnik_dao",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Recenzija> dateRecenzije;
    @OneToMany(mappedBy = "kupac",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Ponuda>ponude;
    @Column
    private double prosecna_ocena;

    public List<Proizvod> getKupljeni_proizvodi() {
        return kupljeni_proizvodi;
    }

    public void setKupljeni_proizvodi(ArrayList<Proizvod> kupljeni_proizvodi) {
        this.kupljeni_proizvodi = kupljeni_proizvodi;
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
        return prosecna_ocena;
    }

    public void setProsecna_ocena(double prosecna_ocena) {
        this.prosecna_ocena = prosecna_ocena;
    }

    public  Kupac(KorisnikRegistracijaDto korisnikRegistracijaDto){
        super(korisnikRegistracijaDto);
        setUloga(UlogaKorisnika.Uloga.KUPAC);
    }
    public Kupac(){}
}

