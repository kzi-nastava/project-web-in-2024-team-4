package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Prodavac extends Korisnik{

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private ArrayList<Proizvod>proizvodi_na_prodaju=new ArrayList<>();
    @Column
    private double prosecna_ocena;
}
