package com.webshop.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Korisnik implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private  String ime;
    @Column
    private String prezime;
    @Column
    @NaturalId
    private String userName;
    @Column
    @NaturalId
    private String mejlAdresa;
    @Column
    private String brojTelefona;
    @Column
    private String lozinka;
    @Column
    private LocalDate datumRodjenja;
    //@Transient
    private String prof_Slika; //Putanje do slike
    //@Transient
    private String opis;
    @Column
    private Uloga uloga;
    @Column
    private  boolean blokiran;
    public enum Uloga{Kupac,Prodavac,Administrator};

    public long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getUserName() {
        return userName;
    }

    public String getMejlAdresa() {
        return mejlAdresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public String getLozinka() {
        return lozinka;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getProf_Slika() {
        return prof_Slika;
    }

    public String getOpis() {
        return opis;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public boolean getBlokiran() {
        return blokiran;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMejlAdresa(String mejlAdresa) {
        this.mejlAdresa = mejlAdresa;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja=LocalDate.parse(datumRodjenja, DateTimeFormatter.ofPattern("d-MMM-yyyy"));
    }

    public void setProf_Slika(String prof_Slika) {
        this.prof_Slika = prof_Slika;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }
    @OneToMany(mappedBy = "daoRecenziju",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Recenzija>recenzije = new ArrayList<>();

}
