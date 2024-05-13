package com.webshop.model;

import com.webshop.Enumeracije.UlogaKorisnika;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="uloga",discriminatorType = DiscriminatorType.STRING)
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
    @Enumerated(EnumType.STRING)
    @Column(insertable = false,updatable = false)
    private UlogaKorisnika.Uloga uloga;
    private  boolean blokiran;

}

