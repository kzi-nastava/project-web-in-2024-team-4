package com.webshop.model;


import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
@Entity
@DiscriminatorValue("Kupac")
public class Kupac extends Korisnik{
    @OneToMany(mappedBy ="kupac",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private ArrayList<Proizvod>kupljeni_proizvodi;
    @OneToMany(mappedBy = "korisnik",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private ArrayList<Recenzija> dobijeneRecenzije;
    @OneToMany(mappedBy = "kupac",fetch = FetchType.EAGER,orphanRemoval = true)
    private ArrayList<Ponuda>ponude;
    @Column
    private double prosecna_ocena;
}

