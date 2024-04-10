package com.webshop.model;


import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Kupac extends Korisnik{
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private ArrayList<Proizvod>kupljeni_proizvodi=new ArrayList<>();
    @Column
    private double prosecna_ocena;
}

