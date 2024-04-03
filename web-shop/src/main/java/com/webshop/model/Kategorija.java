package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Kategorija implements Serializable {
    @Id
    private Long id_Kategorije;
    @OneToMany(mappedBy = "kategorija",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Proizvod> proizvodi = new HashSet<>();
    private String nazivKategorije;

    public Set<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setProizvodi(Set<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }
}
