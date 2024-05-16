package com.webshop.dto;

import com.webshop.model.Korisnik;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.Korisnik}
 */
public class ProdavacDto implements Serializable {
   // private final long id;
    private final String ime;
    private final String prezime;
    private final String korisnickoIme;
    private final String emailAdresa;
    private final String brojTelefona;
    private final String lozinka;
    private final String datumRodjenja;
    private final String profilnaSlika;
    private final String opis;
    public ProdavacDto(String ime, String prezime, String korisnickoIme, String emailAdresa, String brojTelefona, String lozinka, String datumRodjenja, String profilnaSlika, String opis) {
      //  this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.emailAdresa = emailAdresa;
        this.brojTelefona = brojTelefona;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.profilnaSlika = profilnaSlika;
        this.opis = opis;
    }

    public ProdavacDto(Korisnik k){
        this.ime=k.getIme();
        this.prezime=k.getPrezime();
        this.korisnickoIme=k.getKorisnickoIme();
        this.emailAdresa=k.getEmailAdresa();
        this.brojTelefona=k.getBrojTelefona();
        this.lozinka=k.getLozinka();
        this.datumRodjenja=k.getDatumRodjenja();
        this.profilnaSlika=k.getProfilnaSlika();
        this.opis=k.getOpis();
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

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getProfilnaSlika() {
        return profilnaSlika;
    }

    public String getOpis() {
        return opis;
    }
}