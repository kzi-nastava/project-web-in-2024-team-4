package com.webshop.service;

import com.fasterxml.jackson.databind.node.POJONode;
import com.webshop.Enumeracije.TipProdaje;
import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.KorisnikRegistracijaDto;
import com.webshop.dto.PrijavaKorisnikDto;
import com.webshop.dto.ProdavacDto;
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
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
    public void saveProdavac(Prodavac prodavac){
        korisnikRepository.save(prodavac);
    }
    public void saveKorisnik(Korisnik korisnik){
        korisnikRepository.save(korisnik);
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

    public Korisnik prijavaKorisnika(PrijavaKorisnikDto prijavaKorisnikDto)throws Exception{
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(prijavaKorisnikDto.getKorisnickoIme());

       if(korisnik==null)
            throw new Exception("Ne Postoji Korisnik sa tim Korisnickim Imenom ili Lozinkom");
        if (!korisnik.getLozinka().equals(prijavaKorisnikDto.getLozinka())) {
            throw new Exception("Ne postoji korisnik sa tom  lozinkom.");
        }
        return korisnik;
    }

    public boolean checkPassword(Long id,String unsenPassword) {
        Korisnik korisnik = korisnikRepository.findById(id).orElse(null);
        if(korisnik==null){
            throw new IllegalArgumentException("Korisnik sa zadatim IDjem ne postoji");
        }
        if(unsenPassword==null){
            throw  new RuntimeException("Ako menjate korisnicko ime ili mejl morate ponoviti sifru");
        }
        return unsenPassword.equals(korisnik.getLozinka());
    }

}
