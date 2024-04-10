package com.webshop.model;

import java.util.Date;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
public class PrijavaProfila implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String razlog_prijave;
    @Temporal(TemporalType.DATE)
    private LocalDate datum_podnosenja_prijave;
    @OneToOne
    @JoinColumn(name = "korisnik_podnositelj_id", referencedColumnName = "id")
    private Korisnik korisnikPodnositelj;

    @OneToOne
    @JoinColumn(name = "korisnik_odnosi_se_id", referencedColumnName = "id")
    private Korisnik korisnikOdnosiSe;

    public enum StatusPrijave {Podneta, Odbijena, Prihvaćena};
    @Enumerated(EnumType.STRING)
    private StatusPrijave statusPrijave;
}

