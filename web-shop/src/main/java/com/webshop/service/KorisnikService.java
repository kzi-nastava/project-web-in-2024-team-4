package com.webshop.service;

import com.fasterxml.jackson.databind.node.POJONode;
import com.webshop.Enumeracije.TipProdaje;
import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.KorisnikRegistracijaDto;
import com.webshop.model.Korisnik;
import com.webshop.model.Kupac;
import com.webshop.model.Prodavac;
import com.webshop.repository.KorisnikRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class KorisnikService {
    @Autowired
    KorisnikRepository korisnikRepository;

    public void createKupac(KorisnikRegistracijaDto korisnikRegistracijaDto){
        Kupac kupac= new Kupac(korisnikRegistracijaDto);
        korisnikRepository.save(kupac);
    }

    public void createProdavac(KorisnikRegistracijaDto korisnikRegistracijaDto){
        Prodavac prodavac= new Prodavac(korisnikRegistracijaDto);
        korisnikRepository.save(prodavac);
    }
    public ResponseEntity<?> registracijaKorisnika(KorisnikRegistracijaDto korisnikRegistracijaDto){
        if(!korisnikRepository.existsByKorisnickoIme(korisnikRegistracijaDto.getKorisnickoIme())&&!korisnikRepository.existsByEmailAdresa(korisnikRegistracijaDto.getEmailAdresa())){
            Korisnik korisnik = new Korisnik();
            if (korisnikRegistracijaDto.getUloga()== UlogaKorisnika.Uloga.ADMINISTRATOR) {
                return new ResponseEntity<>("Uloga 'ADMINISTRATOR' nije dozvoljena za registraciju.", HttpStatus.BAD_REQUEST);
            }
            if(korisnikRegistracijaDto.getUloga()== UlogaKorisnika.Uloga.KUPAC){
                /*
                kupac.setIme(korisnikRegistracijaDto.getIme());
                kupac.setPrezime(korisnikRegistracijaDto.getPrezime());
                kupac.setKorisnickoIme(korisnikRegistracijaDto.getKorisnickoIme());
                kupac.setEmailAdresa(korisnikRegistracijaDto.getEmailAdresa());
                kupac.setBrojTelefona(korisnikRegistracijaDto.getBrojTelefona());
                kupac.setLozinka(korisnikRegistracijaDto.getLozinka());
                kupac.setUloga(UlogaKorisnika.Uloga.KUPAC);
                */
                Kupac kupac=new Kupac(korisnikRegistracijaDto);
                korisnikRepository.save(kupac);
            } else if (korisnikRegistracijaDto.getUloga()== UlogaKorisnika.Uloga.PRODAVAC) {
                /*
                prodavac.setIme(korisnikRegistracijaDto.getIme());
                prodavac.setPrezime(korisnikRegistracijaDto.getPrezime());
                prodavac.setKorisnickoIme(korisnikRegistracijaDto.getKorisnickoIme());
                prodavac.setEmailAdresa(korisnikRegistracijaDto.getEmailAdresa());
                prodavac.setBrojTelefona(korisnikRegistracijaDto.getBrojTelefona());
                prodavac.setLozinka(korisnikRegistracijaDto.getLozinka());
                prodavac.setUloga(UlogaKorisnika.Uloga.PRODAVAC);
                */
                Prodavac prodavac= new Prodavac(korisnikRegistracijaDto);
                korisnikRepository.save(prodavac);
            }

           // korisnikRepository.save(korisnik);
           return  new ResponseEntity<>("Korisnik se uspesno registrovao",HttpStatus.OK);
        }else{
           return  new ResponseEntity<>("Postoji korisnik sa tim Korisnickim imenom ili emailom",HttpStatus.CONFLICT);
        }
    }
}
