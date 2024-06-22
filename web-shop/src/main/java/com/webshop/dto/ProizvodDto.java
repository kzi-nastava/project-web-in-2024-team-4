package com.webshop.dto;

import com.webshop.Enumeracije.TipProdaje;
import com.webshop.model.Kategorija;
import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import com.webshop.model.Proizvod;

public class ProizvodDto {
    private long id;
    private String ime;
    private String opis;
    private Kategorija kategorija;
    private double cena;
    private String slika;
    private TipProdaje.tipProdaje tipProdaje;
    private Korisnik prodavac;

    public ProizvodDto(){}
    public ProizvodDto(long id,String ime,String opis,Kategorija kategorija,double cena,String slika,TipProdaje.tipProdaje tipProdaje,Korisnik prodavac){
        this.id=id;
        this.ime=ime;
        this.opis=opis;
        this.kategorija=kategorija;
        this.cena=cena;
        this.slika=slika;
        this.tipProdaje=tipProdaje;
        this.prodavac=prodavac;
    }
    public ProizvodDto(Proizvod proizvod){
        this.id=proizvod.getId();
        this.ime=proizvod.getNaziv();
        this.opis=proizvod.getOpis();
        this.kategorija=proizvod.getKategorija();
        this.cena=proizvod.getCena();
        this.slika=proizvod.getSlika();
        this.tipProdaje=proizvod.getTipProdaje();
        this.prodavac=(Prodavac)proizvod.getProdavac();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
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

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public TipProdaje.tipProdaje getTipProdaje() {
        return tipProdaje;
    }

    public void setTipProdaje(TipProdaje.tipProdaje tipProdaje) {
        this.tipProdaje = tipProdaje;
    }

    public Korisnik getProdavac() {
        return prodavac;
    }

    public void setProdavac(Korisnik prodavac) {
        this.prodavac = prodavac;
    }
}
