package com.webshop.model;

import jakarta.persistence.*;
import org.apache.catalina.webresources.AbstractResource;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
@Entity
public class Recenzija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private double ocena;
    private String komentar;
    @Temporal(TemporalType.DATE)
    private LocalDate datum_recenzije;
    @OneToOne
    @MapsId
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;
}

