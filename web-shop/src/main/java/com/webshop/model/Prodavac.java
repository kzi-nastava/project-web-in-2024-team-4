package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Entity
public class Prodavac implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProdavca;

    @OneToMany(mappedBy = "prodavac")
    private Set<Proizvod> proizvodi = new HashSet<>();
    @OneToMany(mappedBy = "recenzijeProdavca")
    private ArrayList<Recenzija>recenzijeKorisnika_Prodavac=new ArrayList<>();
    private  double prosecnaOcenaProdavac;
}
