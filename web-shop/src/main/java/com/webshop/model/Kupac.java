package com.webshop.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Set;

public class Kupac extends  Korisnik{
    private Set<Proizvod> kupljeniProizvodi;
    private ArrayList<Recenzija> recenzijeKupca;
    private  double prosecnaOcenaKupac;
}
