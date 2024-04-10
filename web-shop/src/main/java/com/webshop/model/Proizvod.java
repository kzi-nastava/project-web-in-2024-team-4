package com.webshop.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Proizvod implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String naziv;
    private String opis;
    private String slika;//Putanja do slike
    //Kategorija
    @ManyToOne
    private Kategorija kategorija;
    private double cena;
    public enum Tip_Prodaje{Fiksna_Cena,Aukcija};
    @Enumerated(EnumType.STRING)
    private Tip_Prodaje tip_prodaje;
    @Temporal(TemporalType.DATE)
    private LocalDate datumObjavljivanja;

    //Punuda
    @OneToMany
    private ArrayList<Ponuda>ponude=new ArrayList<>();

    //Prodavac
    @ManyToOne
    private Prodavac prodavac;
    private boolean ostavljenaRecenzijaKupac;
    private boolean ostavljenaRecenzijaProdavac;

    private boolean prodat;

}

