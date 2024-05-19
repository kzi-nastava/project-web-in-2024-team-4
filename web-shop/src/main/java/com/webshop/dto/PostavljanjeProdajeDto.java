package com.webshop.dto;

import com.webshop.Enumeracije.TipProdaje;
import com.webshop.model.Kategorija;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.Proizvod}
 */
public class PostavljanjeProdajeDto implements Serializable {
    private final String naziv;
    private final String opis;
    private final String slika;
    private final Kategorija kategorija;
    private final double cena;
    private final TipProdaje.tipProdaje tipProdaje;

    public PostavljanjeProdajeDto(String naziv, String opis, String slika, Kategorija kategorija, double cena, TipProdaje.tipProdaje tipProdaje) {
        this.naziv = naziv;
        this.opis = opis;
        this.slika = slika;
        this.kategorija = kategorija;
        this.cena = cena;
        this.tipProdaje = tipProdaje;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public String getSlika() {
        return slika;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public double getCena() {
        return cena;
    }

    public TipProdaje.tipProdaje getTipProdaje() {
        return tipProdaje;
    }
}