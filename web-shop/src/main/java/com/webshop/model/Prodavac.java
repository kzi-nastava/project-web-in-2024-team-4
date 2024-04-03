package com.webshop.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Prodavac extends Korisnik{
    private Set<Proizvod>proizvodiNaProdaju;
    private ArrayList<Recenzija>recenzijeProdavca;
    private  double prosecnaOcenaProdavac;
}
