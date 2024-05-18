package com.webshop.controller;

import com.webshop.Enumeracije.StatusPrijave;
import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.PodnosenjePrijaveDto;
import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.PrijavaRepository;
import com.webshop.repository.ProizvodRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/prijavaprofila")
public class PrijavaProfilaController {
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    ProizvodRepository proizvodRepository;
    @Autowired
    PrijavaRepository prijavaRepository;

    @PostMapping("/podnosiprijavu/kupac")
    public ResponseEntity<?> podnesiPrijavu(@RequestBody PodnosenjePrijaveDto podnosenjePrijaveDto, @RequestParam Long id,HttpSession session){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);

        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.KUPAC)
            return new ResponseEntity<>("Niste ulogovani kao KUPAC pristup odbijen", HttpStatus.FORBIDDEN);
        Korisnik korisnikNaKogSeOdnosiPrijava=korisnikRepository.findKorisnikById(id);
        if(korisnikNaKogSeOdnosiPrijava.getUloga()!= UlogaKorisnika.Uloga.PRODAVAC){
            return new ResponseEntity<>("Mozete samo prijavti prodavca", HttpStatus.BAD_REQUEST);
        }
        boolean kupio=proizvodRepository.existsProizvodByKupacAndProdavac(prijavljeniKorisnik,korisnikNaKogSeOdnosiPrijava);
        if(!kupio)
            return new ResponseEntity<>("Kupac nije kupio proizvod od tog prodavca", HttpStatus.NOT_FOUND);

        PrijavaProfila prijavaProfila = new PrijavaProfila();

        prijavaProfila.setDatum_podnosenja_prijave(LocalDate.now());
        prijavaProfila.setRazlog_prijave(podnosenjePrijaveDto.getRazlogPrijave());
        prijavaProfila.setKorisnikPodnositelj(prijavljeniKorisnik);
        prijavaProfila.setKorisnikOdnosiSe(korisnikNaKogSeOdnosiPrijava);
        prijavaProfila.setStatusPrijave(StatusPrijave.faza.Podneta);
        //Uslov da li mogu da se unesu dve iste prijave?
        prijavaRepository.save(prijavaProfila);
        return ResponseEntity.ok("Uspeno podneta prijava");
    }
}
