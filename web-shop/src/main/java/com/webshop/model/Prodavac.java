package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Entity
public class Prodavac implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProdavca;

    @OneToMany(mappedBy = "prodavac")
    private Set<Proizvod> proizvodi = new HashSet<>();
    @OneToMany(mappedBy = "recenzijeProdavca")
    private ArrayList<Recenzija>recenzijeKorisnika_Prodavac=new ArrayList<>();
    private  double prosecnaOcenaProdavac;

    public Long getIdProdavca() {
        return idProdavca;
    }

    public void setIdProdavca(Long idProdavca) {
        this.idProdavca = idProdavca;
    }

    public Set<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(Set<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }

    public ArrayList<Recenzija> getRecenzijeKorisnika_Prodavac() {
        return recenzijeKorisnika_Prodavac;
    }

    public void setRecenzijeKorisnika_Prodavac(ArrayList<Recenzija> recenzijeKorisnika_Prodavac) {
        this.recenzijeKorisnika_Prodavac = recenzijeKorisnika_Prodavac;
    }

    public double getProsecnaOcenaProdavac() {
        return prosecnaOcenaProdavac;
    }

    public void setProsecnaOcenaProdavac(double prosecnaOcenaProdavac) {
        this.prosecnaOcenaProdavac = prosecnaOcenaProdavac;
    }
}
