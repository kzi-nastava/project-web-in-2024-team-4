package com.webshop.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Proizvod implements Serializable {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long idProizvoda;
    private String naziv;
    private String opis;
    private String putanja_do_slike; // putanja do slike
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kategorija kategorija;
    private double cena;
    private tipProdaje tip_prodaje;
    private LocalDate datum_obavljivanja;
    @OneToMany(mappedBy = "proizvod",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Ponuda> ponude = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Prodavac prodavac;


    ///////////////////////
    //Diskutabilnooo
    private boolean ostavljenaRecenzija_od_Kupca;
    private boolean ostavljenaRecenzija_od_Prodavca;
    private boolean da_li_je_prodat;

    public enum tipProdaje{Fiksna_Cena,Aukcijska_Cena};

}
