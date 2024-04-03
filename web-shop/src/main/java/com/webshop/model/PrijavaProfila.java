package com.webshop.model;

import java.util.Date;

public class PrijavaProfila {
    private String razlog_prijave;
    private Date datum_Podnosenja_prijave;
    private Korisnik korisnik_podneo_prijavu;
    private  Korisnik korisnik_odnosi_prijava;
    public enum StatusPrjave{Podneta,Odbijena,Prihvaćena};
    private StatusPrjave status_prijave;
}
