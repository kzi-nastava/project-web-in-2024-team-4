package com.webshop.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

@Entity
public class Ponuda implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Kupac kupac;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Proizvod proizvod;

    //LcalDateTime ponuda postavljena
    LocalDateTime ponuda_postavljena;
    private double cena;

}


