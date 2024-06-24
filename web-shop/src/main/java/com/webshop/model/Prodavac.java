package com.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.InformacijeOProdavcuDto;
import com.webshop.dto.KorisnikRegistracijaDto;
import com.webshop.dto.ProdavacDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("PRODAVAC")
public class Prodavac extends Korisnik{

    @OneToMany(mappedBy ="prodavac" ,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Proizvod>proizvodiNaProdaju=new ArrayList<>();
    @OneToMany(mappedBy = "korisnikPrimio",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Recenzija> dobijeneRecenzije;
    @OneToMany(mappedBy = "korisnikDao",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Recenzija> dateRecenzije;
    @Column
    private double prosecnaOcena;

    public List<Proizvod> getProizvodi_na_prodaju() {
        return proizvodiNaProdaju;
    }

    public void setProizvodi_na_prodaju(List<Proizvod> proizvodi_na_prodaju) {
        this.proizvodiNaProdaju = proizvodi_na_prodaju;
    }

    public List<Recenzija> getDobijeneRecenzije() {
        return dobijeneRecenzije;
    }

    public void setDobijeneRecenzije(List<Recenzija> dobijeneRecenzije) {
        this.dobijeneRecenzije = dobijeneRecenzije;
    }

    public List<Recenzija> getDateRecenzije() {
        return dateRecenzije;
    }

    public void setDateRecenzije(List<Recenzija> dateRecenzije) {
        this.dateRecenzije = dateRecenzije;
    }

    public double getProsecna_ocena() {
        return prosecnaOcena;
    }

    public void setProsecna_ocena(double prosecna_ocena) {
        this.prosecnaOcena = prosecna_ocena;
    }
    public  Prodavac(KorisnikRegistracijaDto korisnikRegistracijaDto){
        super(korisnikRegistracijaDto);
        setUloga(UlogaKorisnika.Uloga.PRODAVAC);
    }
    public Prodavac(){}
    public Prodavac(ProdavacDto prodavacDto){
        super(prodavacDto);
    }
    public Prodavac(InformacijeOProdavcuDto informacijeOProdavcuDto){
        super(informacijeOProdavcuDto);
        this.proizvodiNaProdaju=informacijeOProdavcuDto.getProizvodiNaProdaju();
        this.dobijeneRecenzije=informacijeOProdavcuDto.getDobijeneRecenzije();
        this.prosecnaOcena=informacijeOProdavcuDto.getProsecnaOcena();
    }
    public Prodavac(Korisnik korisnik){
        this.prosecnaOcena=getProsecna_ocena();
    }
}
