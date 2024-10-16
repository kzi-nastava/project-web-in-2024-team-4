package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Kategorija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    @OneToMany(mappedBy = "kategorija",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private ArrayList<Proizvod> proizvodi=new ArrayList<>();
}
