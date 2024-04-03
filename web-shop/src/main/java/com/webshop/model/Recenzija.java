package com.webshop.model;

import org.apache.catalina.webresources.AbstractResource;

import java.util.ArrayList;
import java.util.Date;

public class Recenzija {
    private double ocena;
    private  String komentar;
    private Date datum_recenzije;
    private Korisnik daoRecenziju;
}
