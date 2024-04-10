package com.webshop.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serializable;

@Entity
public class Ponuda implements Serializable{
    @Id
    private long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "kupac_id")
    private Kupac kupac;

    private double cena;

}


