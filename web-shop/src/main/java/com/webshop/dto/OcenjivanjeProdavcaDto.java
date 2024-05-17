package com.webshop.dto;

import com.webshop.model.Recenzija;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.Recenzija}
 */
public class OcenjivanjeProdavcaDto implements Serializable {
    private final double ocena;
    private final String komentar;

    public OcenjivanjeProdavcaDto(double ocena, String komentar) {
        this.ocena = ocena;
        this.komentar = komentar;
    }
    public OcenjivanjeProdavcaDto(Recenzija recenzija){
        this.ocena=recenzija.getOcena();
        this.komentar=recenzija.getKomentar();
    }
    public double getOcena() {
        return ocena;
    }

    public String getKomentar() {
        return komentar;
    }
}