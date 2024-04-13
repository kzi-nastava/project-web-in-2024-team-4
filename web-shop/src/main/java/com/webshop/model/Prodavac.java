package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("Prodavac")
public class Prodavac extends Korisnik{

    @OneToMany(mappedBy ="prodavac" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private ArrayList<Proizvod>proizvodi_na_prodaju=new ArrayList<>();
    @OneToMany(mappedBy = "korisnik",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private ArrayList<Recenzija> dobijeneRecenzije;
    @Column
    private double prosecna_ocena;
}
