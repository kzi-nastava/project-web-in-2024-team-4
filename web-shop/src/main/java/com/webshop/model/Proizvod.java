package com.webshop.model;


import java.util.Date;

public class Proizvod {
    private String naziv;
    private String opis;
    private String putanja_do_slike; // putanja do slike
    private Kategorija kategorija;
    private double cena;
    private String tip_prodaje;
    private Date datum_obavljivanja;
    private Ponuda ponude;
    private Prodavac prodavac;
    private boolean ostavljenaRecenzija_od_Kupca;
    private boolean ostavljenaRecenzija_od_Prodavca;
    private boolean da_li_je_prodat;


}
