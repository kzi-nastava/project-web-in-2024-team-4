package com.webshop.model;


import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
@Entity
public class Kupac implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKupca;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private ArrayList<Proizvod>kupljeniProizvodi=new ArrayList<>();
    private  double prosecnaOcenaKupac;
    @OneToMany(mappedBy = "recenzijaKupca")
    private ArrayList<Recenzija>recenzijeKorisnika_Kupac=new ArrayList<>();

    public Long getIdKupca() {
        return idKupca;
    }

    public void setIdKupca(Long idKupca) {
        this.idKupca = idKupca;
    }

    public ArrayList<Proizvod> getKupljeniProizvodi() {
        return kupljeniProizvodi;
    }

    public void setKupljeniProizvodi(ArrayList<Proizvod> kupljeniProizvodi) {
        this.kupljeniProizvodi = kupljeniProizvodi;
    }

    public double getProsecnaOcenaKupac() {
        return prosecnaOcenaKupac;
    }

    public void setProsecnaOcenaKupac(double prosecnaOcenaKupac) {
        this.prosecnaOcenaKupac = prosecnaOcenaKupac;
    }

    public ArrayList<Recenzija> getRecenzijeKorisnika_Kupac() {
        return recenzijeKorisnika_Kupac;
    }

    public void setRecenzijeKorisnika_Kupac(ArrayList<Recenzija> recenzijeKorisnika_Kupac) {
        this.recenzijeKorisnika_Kupac = recenzijeKorisnika_Kupac;
    }
}
