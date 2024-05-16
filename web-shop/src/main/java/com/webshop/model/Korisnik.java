package com.webshop.model;

import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.InformacijeOProdavcuDto;
import com.webshop.dto.KorisnikRegistracijaDto;
import com.webshop.dto.ProdavacDto;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="uloga",discriminatorType = DiscriminatorType.STRING)
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ime;
    private String prezime;

    @Column(unique = true)
    private String korisnickoIme;

    @Column(unique = true)
    private String emailAdresa;
    private String brojTelefona;

    private String lozinka;
    private String datumRodjenja;
    private  String profilnaSlika;
    private String opis;
    @Enumerated(EnumType.STRING)
    @Column(insertable = false,updatable = false)
    private UlogaKorisnika.Uloga uloga;
    private  boolean blokiran;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnicko_ime) {
        this.korisnickoIme = korisnicko_ime;
    }

    public String getEmailAdresa() {
        return emailAdresa;
    }

    public void setEmailAdresa(String email_adresa) {
        this.emailAdresa = email_adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String broj_telefona) {
        this.brojTelefona = broj_telefona;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datum_rodjenja) {
        this.datumRodjenja = datum_rodjenja;
    }

    public String getProfilnaSlika() {
        return profilnaSlika;
    }

    public void setProfilnaSlika(String profilna_slika) {
        this.profilnaSlika = profilna_slika;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public UlogaKorisnika.Uloga getUloga() {
        return uloga;
    }

    public void setUloga(UlogaKorisnika.Uloga uloga) {
        this.uloga = uloga;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }
/////
    public Korisnik(long id, String ime, String prezime, String korisnickoIme, String emailAdresa, String brojTelefona, String lozinka, String datumRodjenja, String profilnaSlika, String opis, boolean blokiran) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.emailAdresa = emailAdresa;
        this.brojTelefona = brojTelefona;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.profilnaSlika = profilnaSlika;
        this.opis = opis;
        this.blokiran = blokiran;
    }
    public Korisnik(){}

    public Korisnik(KorisnikRegistracijaDto korisnikRegistracijaDto){
        this.ime=korisnikRegistracijaDto.getIme();
        this.ime = korisnikRegistracijaDto.getIme();
        this.prezime = korisnikRegistracijaDto.getPrezime();
        this.korisnickoIme = korisnikRegistracijaDto.getKorisnickoIme();
        this.lozinka = korisnikRegistracijaDto.getLozinka();
        this.emailAdresa = korisnikRegistracijaDto.getEmailAdresa();
        this.brojTelefona = korisnikRegistracijaDto.getBrojTelefona();
    }
    public Korisnik(ProdavacDto prodavacDto){
        this.ime=prodavacDto.getIme();
        this.prezime=prodavacDto.getPrezime();
        this.korisnickoIme=prodavacDto.getKorisnickoIme();
        this.emailAdresa=prodavacDto.getEmailAdresa();
        this.brojTelefona=prodavacDto.getBrojTelefona();
        this.lozinka=prodavacDto.getLozinka();
        this.datumRodjenja=prodavacDto.getDatumRodjenja();
        this.profilnaSlika=prodavacDto.getProfilnaSlika();
        this.opis= prodavacDto.getOpis();
    }

    public Korisnik(InformacijeOProdavcuDto informacijeOProdavcuDto){
        this.ime=informacijeOProdavcuDto.getIme();
        this.prezime=informacijeOProdavcuDto.getPrezime();
        this.korisnickoIme= informacijeOProdavcuDto.getKorisnickoIme();
        this.emailAdresa=informacijeOProdavcuDto.getEmailAdresa();
        this.brojTelefona= informacijeOProdavcuDto.getBrojTelefona();
        this.datumRodjenja=informacijeOProdavcuDto.getDatumRodjenja();
        this.profilnaSlika= informacijeOProdavcuDto.getProfilnaSlika();
        this.opis=informacijeOProdavcuDto.getOpis();
    }
}

