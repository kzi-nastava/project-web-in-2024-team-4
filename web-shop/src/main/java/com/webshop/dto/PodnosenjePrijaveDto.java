package com.webshop.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.PrijavaProfila}
 */
public class PodnosenjePrijaveDto implements Serializable {
    @JsonProperty("razlogPrijave")
    private final String razlogPrijave;
    @JsonCreator
    public PodnosenjePrijaveDto(String razlogPrijave) {
        this.razlogPrijave = razlogPrijave;
    }

    public String getRazlogPrijave() {
        return razlogPrijave;
    }
}