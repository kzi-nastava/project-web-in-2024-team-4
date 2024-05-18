package com.webshop.dto;

import com.webshop.model.Recenzija;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.webshop.model.Recenzija}
 */
public class PregledRecenzijaDto implements Serializable {
    private final double ocena;
    private final String komentar;
    private final LocalDate datumRecenzije;

    public PregledRecenzijaDto(double ocena, String komentar, LocalDate datumRecenzije) {
        this.ocena = ocena;
        this.komentar = komentar;
        this.datumRecenzije = datumRecenzije;
    }
    public PregledRecenzijaDto(Recenzija recenzija){
        this.ocena=recenzija.getOcena();
        this.komentar=recenzija.getKomentar();
        this.datumRecenzije=recenzija.getDatumRecenzije();
    }

    public double getOcena() {
        return ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public LocalDate getDatumRecenzije() {
        return datumRecenzije;
    }
}