package com.webshop.model;

import java.util.Date;

import com.webshop.Enumeracije.StatusPrijave;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
public class PrijavaProfila implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String razlogPrijave;
    @Temporal(TemporalType.DATE)
    private LocalDate datumPodnosenjaPrijave;
    @ManyToOne
    @JoinColumn(name = "podneoPrijavu_ID", referencedColumnName = "id")
    private Korisnik korisnikPodnositelj;

    @ManyToOne
    @JoinColumn(name = "odnosiSePrijava_ID", referencedColumnName = "id")
    private Korisnik korisnikOdnosiSe;


    @Enumerated(EnumType.STRING)
    private StatusPrijave.faza statusPrijave;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRazlog_prijave() {
        return razlogPrijave;
    }

    public void setRazlog_prijave(String razlog_prijave) {
        this.razlogPrijave = razlog_prijave;
    }

    public LocalDate getDatum_podnosenja_prijave() {
        return datumPodnosenjaPrijave;
    }

    public void setDatum_podnosenja_prijave(LocalDate datum_podnosenja_prijave) {
        this.datumPodnosenjaPrijave = datum_podnosenja_prijave;
    }

    public Korisnik getKorisnikPodnositelj() {
        return korisnikPodnositelj;
    }

    public void setKorisnikPodnositelj(Korisnik korisnikPodnositelj) {
        this.korisnikPodnositelj = korisnikPodnositelj;
    }

    public Korisnik getKorisnikOdnosiSe() {
        return korisnikOdnosiSe;
    }

    public void setKorisnikOdnosiSe(Korisnik korisnikOdnosiSe) {
        this.korisnikOdnosiSe = korisnikOdnosiSe;
    }

    public StatusPrijave.faza getStatusPrijave() {
        return statusPrijave;
    }

    public void setStatusPrijave(StatusPrijave.faza statusPrijave) {
        this.statusPrijave = statusPrijave;
    }
}

