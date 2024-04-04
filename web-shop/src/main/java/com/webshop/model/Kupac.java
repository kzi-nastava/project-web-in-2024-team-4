package com.webshop.model;


import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
@Entity
public class Kupac implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKupca;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private ArrayList<Proizvod>kupljeniProizvodi=new ArrayList<>();
    private  double prosecnaOcenaKupac;
    @OneToMany(mappedBy = "recenzijaKupca")
    private ArrayList<Recenzija>recenzijeKorisnika_Kupac=new ArrayList<>();
}
