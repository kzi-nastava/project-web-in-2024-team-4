package com.webshop.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.webshop.model.Recenzija}
 */
public class RecenzijaDto implements Serializable {
    private final long id;
    private final double ocena;
    private final String komentar;
    private final LocalDate datumRecenzije;

    public RecenzijaDto(long id, double ocena, String komentar, LocalDate datumRecenzije) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.datumRecenzije = datumRecenzije;
    }

    public long getId() {
        return id;
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