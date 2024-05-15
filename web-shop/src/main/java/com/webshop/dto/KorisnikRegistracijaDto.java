package com.webshop.dto;

import com.webshop.Enumeracije.UlogaKorisnika;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.webshop.model.Korisnik}
 */
public class KorisnikRegistracijaDto implements Serializable {
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String emailAdresa;
    private String brojTelefona;
    private String lozinka;
    private UlogaKorisnika.Uloga uloga;

    public KorisnikRegistracijaDto(){

    }
    public KorisnikRegistracijaDto(String ime, String prezime, String korisnickoIme, String emailAdresa, String brojTelefona, String lozinka, UlogaKorisnika.Uloga uloga) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.emailAdresa = emailAdresa;
        this.brojTelefona = brojTelefona;
        this.lozinka = lozinka;
        this.uloga = uloga;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getEmailAdresa() {
        return emailAdresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public String getLozinka() {
        return lozinka;
    }

    public UlogaKorisnika.Uloga getUloga() {
        return uloga;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKorisnickoIme(String korisnicko_ime) {
        this.korisnickoIme = korisnicko_ime;
    }

    public void setEmailAdresa(String email_adresa) {
        this.emailAdresa = email_adresa;
    }

    public void setBrojTelefona(String broj_telefona) {
        this.brojTelefona = broj_telefona;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setUloga(UlogaKorisnika.Uloga uloga) {
        this.uloga = uloga;
    }
}