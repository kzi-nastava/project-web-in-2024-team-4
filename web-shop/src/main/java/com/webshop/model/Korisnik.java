package com.webshop.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;

@Entity
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ime;
    private String prezime;
    @NaturalId
    @Column(unique = true)
    private String korisnicko_ime;
    @NaturalId
    @Column(unique = true)
    private String email_adresa;
    private String broj_telefona;

    private String lozinka;
    private String datum_rodjenja;
    private  String profilna_slika;
    private String opis;
    public enum Uloga {Kupac, Prodavac, Administrator};
    @Enumerated(EnumType.STRING)
    private Uloga uloga;
    private  boolean blokiran;

}

