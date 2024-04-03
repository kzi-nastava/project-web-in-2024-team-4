package com.webshop.model;

import jakarta.persistence.*;
import org.apache.catalina.webresources.AbstractResource;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
@Entity
public class Recenzija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecenzije;
    private double ocena;
    private  String komentar;
    private LocalDate datum_recenzije;
    @ManyToOne
    private Korisnik daoRecenziju;
}
