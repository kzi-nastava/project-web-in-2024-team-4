package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class PrijavaProfila implements Serializable {
    private String razlog_prijave;
    @Temporal(TemporalType.DATE)
    private LocalDate datum_Podnosenja_prijave;
    @OneToOne
    private Korisnik podneo_prijavu;
    @OneToOne
    private Korisnik NaKogaSeOdnosiPrijava;
    public enum StatusPrjave{Podneta,Odbijena,Prihvaćena};
    @Enumerated(EnumType.STRING)
    private StatusPrjave status_prijave;

}
