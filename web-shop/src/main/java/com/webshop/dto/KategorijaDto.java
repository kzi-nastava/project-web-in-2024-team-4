package com.webshop.dto;

import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.webshop.model.Kategorija}
 */
public class KategorijaDto implements Serializable {
    private long id;
    private String naziv;
    //private List<Proizvod> proizvodi;
    public KategorijaDto(){}
    public KategorijaDto(long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
       // this.proizvodi = proizvodi;
    }
    public KategorijaDto(Kategorija kategorija){
        this.id=kategorija.getId();
        this.naziv=kategorija.getNaziv();
      //  this.proizvodi=kategorija.getProizvodi();
    }

    public long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

   // public List<Proizvod> getProizvodi() {
   //     return proizvodi;
  //  }

    public void setId(long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

   // public void setProizvodi(List<Proizvod> proizvodi) {
   //     this.proizvodi = proizvodi;
  //  }
}