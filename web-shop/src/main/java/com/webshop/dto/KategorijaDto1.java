package com.webshop.dto;

import com.webshop.model.Proizvod;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.webshop.model.Kategorija}
 */
public class KategorijaDto1 implements Serializable {
    private final String naziv;
    private final List<Proizvod> proizvodi;

    public KategorijaDto1(String naziv, List<Proizvod> proizvodi) {
        this.naziv = naziv;
        this.proizvodi = proizvodi;
    }
    /*
    public KategorijaDto1(PostavljanjeProdajeDto postavljanjeProdajeDto) {
        this.naziv = postavljanjeProdajeDto.getKategorija().getNaziv();
        this.proizvodi = postavljanjeProdajeDto.getKategorija().getProizvodi();
    }
    */
    public String getNaziv() {
        return naziv;
    }

    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }
}