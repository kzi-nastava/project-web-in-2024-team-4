package com.webshop.controller;

import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.KorisnikRegistracijaDto;
import com.webshop.dto.PrijavaKorisnikDto;
import com.webshop.dto.ProdavacDto;
import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import com.webshop.repository.KorisnikRepository;
import com.webshop.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    KorisnikService korisnikService;

    @PostMapping("/registracija")
    public ResponseEntity<?> registracijaKorisnika(@RequestBody KorisnikRegistracijaDto korisnikRegistracijaDto){
       ResponseEntity<?> zahtev = korisnikService.registracijaKorisnika(korisnikRegistracijaDto);
        return ResponseEntity.ok(zahtev.getBody());
    }
    @PostMapping("/prijava-korisnika")
    public ResponseEntity<String> prijava(@RequestBody PrijavaKorisnikDto prijavaKorisnikDto,HttpSession session){
        if(prijavaKorisnikDto.getKorisnickoIme().isEmpty()|| prijavaKorisnikDto.getLozinka().isEmpty()){
            return new ResponseEntity<>("Nisu uneseni adekvatni podaci", HttpStatus.BAD_REQUEST);
        }else{
            try {
                Korisnik korisnik = korisnikService.prijavaKorisnika(prijavaKorisnikDto);
                // Ako korisnik postoji i lozinka je tačna, možemo nastaviti sesiju
                session.setAttribute("korisnik", korisnik);
                return new ResponseEntity<>("Uspešna prijava", HttpStatus.OK);
            } catch (Exception e) {
                // Obradi izuzetak ako korisnik ne postoji ili lozinka nije tačna
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> Logout(HttpSession session){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null)
            return new ResponseEntity<>("Forbidden",HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity<>("Uspesno ste se odjavili",HttpStatus.OK);
    }
    //http://localhost:8080/korisnik/logged/prodavacUpdate
    @PutMapping("/logged/prodavacUpdate")
    public ResponseEntity<?> updateProdavac(@RequestBody ProdavacDto prodavacDto,HttpSession session){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null){
            return new ResponseEntity<>("Nema prijavljenog prodavca",HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.PRODAVAC){
            return  new ResponseEntity<>("Forbidden",HttpStatus.FORBIDDEN);
        }
        if (prodavacDto.getKorisnickoIme()!=null || prodavacDto.getEmailAdresa()!=null) {
            // Provera da li je stara lozinka tačna
            if (!korisnikService.checkPassword(prijavljeniKorisnik.getId(), prodavacDto.getLozinka())) {
                return new ResponseEntity<>("Trenutna lozinka nije tačna.", HttpStatus.BAD_REQUEST);
            }
            if(prodavacDto.getKorisnickoIme()!=null)
                prijavljeniKorisnik.setKorisnickoIme(prodavacDto.getKorisnickoIme());
            if(prodavacDto.getEmailAdresa()!=null)
                prijavljeniKorisnik.setEmailAdresa(prodavacDto.getEmailAdresa());
        }
        if(prodavacDto.getIme()!=null)
            prijavljeniKorisnik.setIme(prodavacDto.getIme());
        if(prodavacDto.getPrezime()!=null)
            prijavljeniKorisnik.setPrezime(prodavacDto.getPrezime());
        if(prodavacDto.getBrojTelefona()!=null)
            prijavljeniKorisnik.setBrojTelefona(prodavacDto.getBrojTelefona());
        if(prodavacDto.getDatumRodjenja()!=null)
            prijavljeniKorisnik.setDatumRodjenja(prodavacDto.getDatumRodjenja());
        if(prodavacDto.getProfilnaSlika()!=null)
            prijavljeniKorisnik.setProfilnaSlika(prodavacDto.getProfilnaSlika());
        if(prodavacDto.getOpis()!=null)
            prijavljeniKorisnik.setOpis(prodavacDto.getOpis());
        korisnikService.saveKorisnik(prijavljeniKorisnik);
        return new ResponseEntity<>("Uspesno izmenjeni podaci",HttpStatus.OK);
    }

}
