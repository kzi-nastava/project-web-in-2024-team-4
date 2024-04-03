package com.webshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class Korisnik {
    private  String Ime;
    private String Prezime;
    private String userName;
    private String mejlAdresa;
    private String brojTelefona;
    private String Lozinka;
    private String datumRodjenja;
    private String prof_Slika; //Putanje do slike
    private String opis;
    private Uloga uloga;
    private  boolean blokiran;
    public enum Uloga{Kupac,Prodavac,Administrator};

}
