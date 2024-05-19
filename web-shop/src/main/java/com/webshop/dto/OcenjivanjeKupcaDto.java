package com.webshop.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.Recenzija}
 */
public class OcenjivanjeKupcaDto implements Serializable {
    private final double ocena;
    private final String komentar;

    public OcenjivanjeKupcaDto(double ocena, String komentar) {
        this.ocena = ocena;
        this.komentar = komentar;
    }

    public double getOcena() {
        return ocena;
    }

    public String getKomentar() {
        return komentar;
    }
}