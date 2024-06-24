package com.webshop.service;

import com.fasterxml.jackson.databind.node.POJONode;
import com.webshop.Enumeracije.StatusPrijave;
import com.webshop.Enumeracije.TipProdaje;
import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.InformacijeOProdavcuDto;
import com.webshop.dto.KorisnikRegistracijaDto;
import com.webshop.dto.PrijavaKorisnikDto;
import com.webshop.dto.ProdavacDto;
import com.webshop.model.*;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.PrijavaRepository;
import com.webshop.repository.ProizvodRepository;
import com.webshop.repository.RecenzijaRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class KorisnikService {
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    ProizvodRepository proizvodRepository;
    @Autowired
    RecenzijaRepository recenzijaRepository;
    @Autowired
    PrijavaRepository prijavaRepository;
    @Autowired
    EmailService emailService;

    public void createKupac(KorisnikRegistracijaDto korisnikRegistracijaDto) {
        Kupac kupac = new Kupac(korisnikRegistracijaDto);
        korisnikRepository.save(kupac);
    }

    public void createProdavac(KorisnikRegistracijaDto korisnikRegistracijaDto) {
        Prodavac prodavac = new Prodavac(korisnikRegistracijaDto);
        korisnikRepository.save(prodavac);
    }

    public void saveProdavac(Prodavac prodavac) {
        korisnikRepository.save(prodavac);
    }

    public void saveKorisnik(Korisnik korisnik) {
        korisnikRepository.save(korisnik);
    }

    public ResponseEntity<?> registracijaKorisnika(KorisnikRegistracijaDto korisnikRegistracijaDto) {
        if (!korisnikRepository.existsByKorisnickoIme(korisnikRegistracijaDto.getKorisnickoIme()) && !korisnikRepository.existsByEmailAdresa(korisnikRegistracijaDto.getEmailAdresa())) {
            Korisnik korisnik = new Korisnik();
            if (korisnikRegistracijaDto.getUloga() == UlogaKorisnika.Uloga.ADMINISTRATOR) {
                return new ResponseEntity<>("Uloga 'ADMINISTRATOR' nije dozvoljena za registraciju.", HttpStatus.BAD_REQUEST);
            }
            if (korisnikRegistracijaDto.getUloga() == UlogaKorisnika.Uloga.KUPAC) {
                /*
                kupac.setIme(korisnikRegistracijaDto.getIme());
                kupac.setPrezime(korisnikRegistracijaDto.getPrezime());
                kupac.setKorisnickoIme(korisnikRegistracijaDto.getKorisnickoIme());
                kupac.setEmailAdresa(korisnikRegistracijaDto.getEmailAdresa());
                kupac.setBrojTelefona(korisnikRegistracijaDto.getBrojTelefona());
                kupac.setLozinka(korisnikRegistracijaDto.getLozinka());
                kupac.setUloga(UlogaKorisnika.Uloga.KUPAC);
                */
                Kupac kupac = new Kupac(korisnikRegistracijaDto);
                korisnikRepository.save(kupac);
            } else if (korisnikRegistracijaDto.getUloga() == UlogaKorisnika.Uloga.PRODAVAC) {
                /*
                prodavac.setIme(korisnikRegistracijaDto.getIme());
                prodavac.setPrezime(korisnikRegistracijaDto.getPrezime());
                prodavac.setKorisnickoIme(korisnikRegistracijaDto.getKorisnickoIme());
                prodavac.setEmailAdresa(korisnikRegistracijaDto.getEmailAdresa());
                prodavac.setBrojTelefona(korisnikRegistracijaDto.getBrojTelefona());
                prodavac.setLozinka(korisnikRegistracijaDto.getLozinka());
                prodavac.setUloga(UlogaKorisnika.Uloga.PRODAVAC);
                */
                Prodavac prodavac = new Prodavac(korisnikRegistracijaDto);
                korisnikRepository.save(prodavac);
            }

            // korisnikRepository.save(korisnik);
            return new ResponseEntity<>("Korisnik se uspesno registrovao", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Postoji korisnik sa tim Korisnickim imenom ili emailom", HttpStatus.CONFLICT);
        }
    }

    public Korisnik prijavaKorisnika(PrijavaKorisnikDto prijavaKorisnikDto) throws Exception {
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(prijavaKorisnikDto.getKorisnickoIme());

        if (korisnik == null)
            throw new Exception("Ne Postoji Korisnik sa tim Korisnickim Imenom ili Lozinkom");
        if (!korisnik.getLozinka().equals(prijavaKorisnikDto.getLozinka())) {
            throw new Exception("Ne postoji korisnik sa tom  lozinkom.");
        }
        return korisnik;
    }

    public boolean checkPassword(Long id, String unsenPassword) {
        Korisnik korisnik = korisnikRepository.findById(id).orElse(null);
        if (korisnik == null) {
            throw new IllegalArgumentException("Korisnik sa zadatim IDjem ne postoji");
        }
        if (unsenPassword == null) {
            throw new RuntimeException("Ako menjate korisnicko ime ili mejl morate ponoviti sifru");
        }
        return unsenPassword.equals(korisnik.getLozinka());
    }

    public List<Proizvod> getAllProizvodi(Long id) {
        List<Proizvod> proizvods = proizvodRepository.findProizvodByProdavacId(id);
        return proizvods;
    }

    public List<Recenzija> getAllRecenzije(Long id) {
        List<Recenzija> recenzijas = recenzijaRepository.findRecenzijaByKorisnikPrimioId(id);
        return recenzijas;
    }

    public double getProsecnaOcena(Long id) {
        Optional<Korisnik> korisnik = korisnikRepository.findById(id);
        if (korisnik.isPresent()) {
            Korisnik korisnik1 = korisnik.get();
            if (korisnik1 instanceof Prodavac) {
                Prodavac prodavac = (Prodavac) korisnik1;
                return prodavac.getProsecna_ocena();
            } else if (korisnik1 instanceof Kupac) {
                Kupac kupac = (Kupac) korisnik1;
                return kupac.getProsecna_ocena();
            } else {
                throw new IllegalArgumentException("Objekat nije instnca Prodavca");
            }
        } else {
            throw new NoSuchElementException("Korisnik sa tim ID ne postoji");
        }
    }

    public ResponseEntity<?> administratorPregled() {
        List<Recenzija> sveRecenzije = recenzijaRepository.findAll();
        return ResponseEntity.ok(sveRecenzije);
    }

    public ResponseEntity<?> administratorIzmena(Long id, String komentar) {
        Recenzija recenzija = recenzijaRepository.findRecenzijaById(id);
        if (recenzija == null) {
            return new ResponseEntity<>("Recenzija sa datim id-em ne postoji", HttpStatus.NOT_FOUND);
        }
        recenzija.setKomentar(komentar);
        recenzijaRepository.save(recenzija);
        return ResponseEntity.ok("Komentar recenzije sa id = " + id + " je izmenjena");
    }

    public ResponseEntity<?> administratorObrisi(Long id) {
        Recenzija recenzija = recenzijaRepository.findRecenzijaById(id);
        if (recenzija == null) {
            return new ResponseEntity<>("Recenzija sa datim id-em ne postoji", HttpStatus.NOT_FOUND);
        }
        recenzijaRepository.delete(recenzija);
        return ResponseEntity.ok("Recenzija " + recenzija + " je uspeno obrisana");
    }

    public ResponseEntity<?> administratorGetPrijave() {
        List<PrijavaProfila> prijavaProfilas = prijavaRepository.findAll();
        return ResponseEntity.ok(prijavaProfilas);
    }

    public ResponseEntity<?> administratorObradiPrijavu(Long id, Boolean odluka, @Nullable String razlogOdbijanja) {
        PrijavaProfila prijavaProfila = prijavaRepository.findPrijavaProfilaById(id);
        if (!odluka) {
            Korisnik korisnik = prijavaProfila.getKorisnikPodnositelj();
            String email = korisnik.getEmailAdresa();
            StatusPrijave.faza statusPrijave;
            statusPrijave=StatusPrijave.faza.Odbijena;
            prijavaProfila.setStatusPrijave(statusPrijave);
            prijavaRepository.save(prijavaProfila);
            emailService.sendEmail(email, "Odbijena prijava", razlogOdbijanja);
            return new ResponseEntity<>("Vase prijava je odbijenja razlog odbijanja " + razlogOdbijanja, HttpStatus.OK);
        } else if (odluka) {
            Korisnik korisnik = prijavaProfila.getKorisnikOdnosiSe();
            if (korisnik.getUloga() == UlogaKorisnika.Uloga.PRODAVAC) {
                korisnik.setBlokiran(true);
                korisnikRepository.save(korisnik);
                StatusPrijave.faza statusPrijave;
                statusPrijave=StatusPrijave.faza.Prihvacena;
                prijavaProfila.setStatusPrijave(statusPrijave);
                prijavaRepository.save(prijavaProfila);
                List<Proizvod> proizvods = proizvodRepository.findAllByProdavac(korisnik);
                for (Proizvod p : proizvods) {
                    if (!p.isProdat()) {
                        proizvodRepository.delete(p);
                    }
                }
//                List<PrijavaProfila> prijavaProfilas=prijavaRepository.findAllByKorisnikOdnosiSe(korisnik);
//                prijavaRepository.deleteAll(prijavaProfilas);
              //  prijavaRepository.delete(prijavaProfila);
                String email = korisnik.getEmailAdresa();
                emailService.sendEmail(email, "Prijava", "Proizvodi koje ste postavili su obrisani usled prijave");
            } else {
                return new ResponseEntity<>("Korisnik na kog se odnosi prijava nije prodavac", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return ResponseEntity.ok("Obrada prijave uspesno izvrsena");
    }
}
