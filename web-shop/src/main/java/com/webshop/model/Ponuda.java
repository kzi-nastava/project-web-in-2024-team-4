package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Ponuda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cena;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

}

