package com.webshop.dto;

import com.webshop.model.Korisnik;
import com.webshop.model.Proizvod;
import com.webshop.model.Recenzija;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.webshop.model.Korisnik}
 */
public class InformacijeOProdavcuDto implements Serializable {
    private final String ime;
    private final String prezime;
    private final String korisnickoIme;
    private final String emailAdresa;
    private final String brojTelefona;
    private final String datumRodjenja;
    private final String profilnaSlika;
    private final String opis;
    private List<Proizvod> proizvodiNaProdaju;
    private  List<Recenzija> dobijeneRecenzije;
    private  double prosecnaOcena;

    public InformacijeOProdavcuDto(String ime, String prezime, String korisnickoIme, String emailAdresa, String brojTelefona, String datumRodjenja, String profilnaSlika, String opis,List<Proizvod> proizvodiNaProdaju,List<Recenzija> dobijeneRecenzije,double prosecnaOcena) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.emailAdresa = emailAdresa;
        this.brojTelefona = brojTelefona;
        this.datumRodjenja = datumRodjenja;
        this.profilnaSlika = profilnaSlika;
        this.opis = opis;
        this.proizvodiNaProdaju=proizvodiNaProdaju;
        this.dobijeneRecenzije=dobijeneRecenzije;
        this.prosecnaOcena=prosecnaOcena;
    }
    public InformacijeOProdavcuDto(Korisnik korisnik){
        this.ime=korisnik.getIme();
        this.prezime=korisnik.getPrezime();
        this.korisnickoIme=korisnik.getKorisnickoIme();
        this.emailAdresa=korisnik.getEmailAdresa();
        this.brojTelefona=korisnik.getBrojTelefona();
        this.datumRodjenja=korisnik.getDatumRodjenja();
        this.profilnaSlika=korisnik.getProfilnaSlika();
        this.opis=korisnik.getOpis();
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

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getProfilnaSlika() {
        return profilnaSlika;
    }

    public String getOpis() {
        return opis;
    }

    public List<Proizvod> getProizvodiNaProdaju() {
        return proizvodiNaProdaju;
    }

    public List<Recenzija> getDobijeneRecenzije() {
        return dobijeneRecenzije;
    }

    public double getProsecnaOcena() {
        return prosecnaOcena;
    }
}