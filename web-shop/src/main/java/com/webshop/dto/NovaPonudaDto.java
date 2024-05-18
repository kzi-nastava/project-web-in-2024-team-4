package com.webshop.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.Ponuda}
 */
public class NovaPonudaDto implements Serializable {
    private final double cena;

    public NovaPonudaDto(double cena) {
        this.cena = cena;
    }

    public double getCena() {
        return cena;
    }
}