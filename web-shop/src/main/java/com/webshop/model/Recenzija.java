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
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Korisnik korisnik_primio;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Korisnik korisnik_dao;
    //Kupac,Prodavac
    //Ko je dao recenziju
}

