package com.webshop.controller;

import com.webshop.dto.KorisnikRegistracijaDto;
import com.webshop.repository.KorisnikRepository;
import com.webshop.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

}
