package com.webshop.controller;

import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.*;
import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import com.webshop.model.Proizvod;
import com.webshop.model.Recenzija;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.ProizvodRepository;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import com.webshop.service.RezenzijaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    KorisnikService korisnikService;
    @Autowired
    ProizvodService proizvodService;
    @Autowired
    ProizvodRepository proizvodRepository;
    @Autowired
    RezenzijaService rezenzijaService;
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

    @PutMapping("/logged/kupacUpdate")
    public ResponseEntity<?> updateKupac(@RequestBody KupacDto kupacDto, HttpSession session){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null){
            return new ResponseEntity<>("Nema prijavljenog kupca",HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.KUPAC){
            return  new ResponseEntity<>("Forbidden",HttpStatus.FORBIDDEN);
        }
        if (kupacDto.getKorisnickoIme()!=null || kupacDto.getEmailAdresa()!=null){
            // Provera da li je stara lozinka tačna
            if (!korisnikService.checkPassword(prijavljeniKorisnik.getId(), kupacDto.getLozinka())) {
                return new ResponseEntity<>("Trenutna lozinka nije tačna.", HttpStatus.BAD_REQUEST);
            }
            if(kupacDto.getKorisnickoIme()!=null)
                prijavljeniKorisnik.setKorisnickoIme(kupacDto.getKorisnickoIme());
            if(kupacDto.getEmailAdresa()!=null)
                prijavljeniKorisnik.setEmailAdresa(kupacDto.getEmailAdresa());
        }
        if(kupacDto.getIme()!=null)
            prijavljeniKorisnik.setIme(kupacDto.getIme());
        if(kupacDto.getPrezime()!=null)
            prijavljeniKorisnik.setPrezime(kupacDto.getPrezime());
        if(kupacDto.getBrojTelefona()!=null)
            prijavljeniKorisnik.setBrojTelefona(kupacDto.getBrojTelefona());
        if(kupacDto.getDatumRodjenja()!=null)
            prijavljeniKorisnik.setDatumRodjenja(kupacDto.getDatumRodjenja());
        if(kupacDto.getProfilnaSlika()!=null)
            prijavljeniKorisnik.setProfilnaSlika(kupacDto.getProfilnaSlika());
        if(kupacDto.getOpis()!=null)
            prijavljeniKorisnik.setOpis(kupacDto.getOpis());
        korisnikService.saveKorisnik(prijavljeniKorisnik);
        return new ResponseEntity<>("Uspesno izmenjeni podaci",HttpStatus.OK);
    }


    @GetMapping("/kupac/pregled")
    public ResponseEntity<?> pregledKorisnika(HttpSession session) {
        Korisnik korisnikPrijavljen = (Korisnik) session.getAttribute("korisnik");
        if (korisnikPrijavljen == null) {
            return new ResponseEntity<>("Nema prijavljenog kupca", HttpStatus.BAD_REQUEST);
        }
        if (korisnikPrijavljen.getUloga() == UlogaKorisnika.Uloga.KUPAC) {
            List<Korisnik> korisnik = korisnikRepository.findAll();
            List<OsnovneInformacijePregledProfilaDto> osnovneInformacijePregledProfilaDtos = new ArrayList<>();
            for (Korisnik k : korisnik) {
                if (k.getUloga() == UlogaKorisnika.Uloga.KUPAC || k.getUloga() == UlogaKorisnika.Uloga.PRODAVAC) {
                    OsnovneInformacijePregledProfilaDto osnovneInformacijePregledProfilaDto=new OsnovneInformacijePregledProfilaDto(k);
                    osnovneInformacijePregledProfilaDtos.add(osnovneInformacijePregledProfilaDto);
                }
            }
            return  ResponseEntity.ok(osnovneInformacijePregledProfilaDtos);
        }else{
            return new ResponseEntity<>("Zabranjen pristup za korisnike koji nisu KUPAC", HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/kupac/pregled/{id}")
    public ResponseEntity<?> pregledIzabranogProfila(@PathVariable Long id,HttpSession session){
        Korisnik korisnikPrijavljen = (Korisnik) session.getAttribute("korisnik");
        if (korisnikPrijavljen == null) {
            return new ResponseEntity<>("Nema prijavljenog kupca", HttpStatus.BAD_REQUEST);
        }
        Optional<Korisnik> trazeniKorisnik = korisnikRepository.findById(id);
        if (!trazeniKorisnik.isPresent()) {
            return new ResponseEntity<>("Korisnik nije pronađen", HttpStatus.NOT_FOUND);
        }

        Korisnik korisnikZaPregled = trazeniKorisnik.get();

        if (korisnikZaPregled.getUloga() == UlogaKorisnika.Uloga.PRODAVAC) {
            // Pronalazak proizvoda prodavatelja

            List<Proizvod> products = proizvodRepository.findProizvodByProdavacId(korisnikZaPregled.getId());
            List<ProizvodDto>proizvodDtos=new ArrayList<>();
            for(Proizvod p:products){
                ProizvodDto proizvodDto= new ProizvodDto(p);
                proizvodDtos.add(proizvodDto);
            }
            List<Proizvod> proizvods=korisnikService.getAllProizvodi(id);
            List<Recenzija>recenzijas=korisnikService.getAllRecenzije(id);
            double prosecnaOcena= korisnikService.getProsecnaOcena(id);
            InformacijeOProdavcuDto dto = new InformacijeOProdavcuDto(
                    korisnikZaPregled.getIme(),
                    korisnikZaPregled.getPrezime(),
                    korisnikZaPregled.getKorisnickoIme(),
                    korisnikZaPregled.getEmailAdresa(),
                    korisnikZaPregled.getBrojTelefona(),
                    korisnikZaPregled.getDatumRodjenja(),
                    korisnikZaPregled.getProfilnaSlika(),
                    korisnikZaPregled.getOpis(),
                    proizvods,
                    recenzijas,
                    prosecnaOcena
            );

            return ResponseEntity.ok(dto);
        } else if (korisnikZaPregled.getUloga()== UlogaKorisnika.Uloga.KUPAC) {
            List<Proizvod> proizvods=korisnikService.getAllProizvodi(id);
            List<Recenzija>recenzijas=korisnikService.getAllRecenzije(id);
            double prosecnaOcena= korisnikService.getProsecnaOcena(id);
            InformacijeOKupcuDto dto=new InformacijeOKupcuDto(korisnikZaPregled.getIme(),
                    korisnikZaPregled.getPrezime(),
                    korisnikZaPregled.getKorisnickoIme(),
                    korisnikZaPregled.getEmailAdresa(),
                    korisnikZaPregled.getBrojTelefona(),
                    korisnikZaPregled.getDatumRodjenja(),
                    korisnikZaPregled.getProfilnaSlika(),
                    korisnikZaPregled.getOpis(),proizvods,recenzijas,prosecnaOcena);
            return ResponseEntity.ok(dto);

        } else {
            return new ResponseEntity<>("Neodgovarajuća uloga korisnika", HttpStatus.FORBIDDEN);
        }
    }

}
