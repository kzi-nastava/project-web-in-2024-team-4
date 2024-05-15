package com.webshop.controller;

import com.webshop.dto.KorisnikRegistracijaDto;
import com.webshop.dto.PrijavaKorisnikDto;
import com.webshop.model.Korisnik;
import com.webshop.repository.KorisnikRepository;
import com.webshop.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
