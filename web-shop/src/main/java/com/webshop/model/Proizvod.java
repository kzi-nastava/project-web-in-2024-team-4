package com.webshop.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Proizvod implements Serializable {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long idProizvoda;
    private String naziv;
    private String opis;
    private String putanja_do_slike; // putanja do slike
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kategorija kategorija;
    private double cena;
    @Enumerated(EnumType.STRING)
    private tipProdaje tip_prodaje;
    @Temporal(TemporalType.DATE)
    private LocalDate datum_obavljivanja;
    @OneToMany(mappedBy = "proizvod",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Ponuda> ponude = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Prodavac prodavac;


    ///////////////////////
    //Diskutabilnooo
    private boolean ostavljenaRecenzija_od_Kupca;
    private boolean ostavljenaRecenzija_od_Prodavca;
    private boolean da_li_je_prodat;


    public enum tipProdaje{Fiksna_Cena,Aukcijska_Cena};

    public Long getIdProizvoda() {
        return idProizvoda;
    }

    public void setIdProizvoda(Long idProizvoda) {
        this.idProizvoda = idProizvoda;
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

    public String getPutanja_do_slike() {
        return putanja_do_slike;
    }

    public void setPutanja_do_slike(String putanja_do_slike) {
        this.putanja_do_slike = putanja_do_slike;
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

    public tipProdaje getTip_prodaje() {
        return tip_prodaje;
    }

    public void setTip_prodaje(tipProdaje tip_prodaje) {
        this.tip_prodaje = tip_prodaje;
    }

    public LocalDate getDatum_obavljivanja() {
        return datum_obavljivanja;
    }

    public void setDatum_obavljivanja(LocalDate datum_obavljivanja) {
        this.datum_obavljivanja = datum_obavljivanja;
    }

    public List<Ponuda> getPonude() {
        return ponude;
    }

    public void setPonude(List<Ponuda> ponude) {
        this.ponude = ponude;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public boolean isOstavljenaRecenzija_od_Kupca() {
        return ostavljenaRecenzija_od_Kupca;
    }

    public void setOstavljenaRecenzija_od_Kupca(boolean ostavljenaRecenzija_od_Kupca) {
        this.ostavljenaRecenzija_od_Kupca = ostavljenaRecenzija_od_Kupca;
    }

    public boolean isOstavljenaRecenzija_od_Prodavca() {
        return ostavljenaRecenzija_od_Prodavca;
    }

    public void setOstavljenaRecenzija_od_Prodavca(boolean ostavljenaRecenzija_od_Prodavca) {
        this.ostavljenaRecenzija_od_Prodavca = ostavljenaRecenzija_od_Prodavca;
    }

    public boolean isDa_li_je_prodat() {
        return da_li_je_prodat;
    }

    public void setDa_li_je_prodat(boolean da_li_je_prodat) {
        this.da_li_je_prodat = da_li_je_prodat;
    }
}
