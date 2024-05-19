package com.webshop.controller;

import com.webshop.Enumeracije.TipProdaje;
import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.ProizvodDto;
import com.webshop.model.*;
import com.webshop.repository.KategorijaRepository;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.PonudaRepository;
import com.webshop.repository.ProizvodRepository;
import com.webshop.service.EmailService;
import com.webshop.service.KategorijaService;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("proizvod")
public class ProizvodController {
    @Autowired
    private ProizvodRepository proizvodRepository;
    @Autowired
    private ProizvodService proizvodService;
    @Autowired
    private KategorijaService kategorijaService;
    @Autowired
    private KategorijaRepository kategorijaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PonudaRepository ponudaRepository;
    @Autowired
    EmailService emailService;
    @GetMapping("/lista-proizvoda")
    public ResponseEntity<List<ProizvodDto>> getAllProducts(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue ="10")int size){
        List<ProizvodDto> proizvod=proizvodService.getAllProducts(page,size);
        return  ResponseEntity.ok(proizvod);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProizvodDto>> findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(@RequestParam(required = false) String naziv,@RequestParam(required = false) String opis){
        List<ProizvodDto>proizvodDtos=proizvodService.findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(naziv,opis);
        return  ResponseEntity.ok(proizvodDtos);
    }

    @GetMapping("/search/filter")
    public ResponseEntity<List<ProizvodDto>> findProductsByFilters(
            @RequestParam(required = false, defaultValue = "0") Double minCena,
            @RequestParam(required = false, defaultValue = "99999999999999") Double maxCena,
            @RequestParam(required = false) TipProdaje.tipProdaje tipProdaje,
            @RequestParam(required = false) String nazivKategorije) {

        // Pretraga proizvoda
        List<ProizvodDto> proizvodDtos=proizvodService.findFilteredProducts(minCena, maxCena, tipProdaje, nazivKategorije);

        return ResponseEntity.ok(proizvodDtos);
    }

    @GetMapping("/lista-proizvoda/{id}")
    public ResponseEntity<ProizvodDto>getOneProducts(@PathVariable long id){
        ProizvodDto proizvodDto=proizvodService.getOneProducts(id);
        return ResponseEntity.ok(proizvodDto);
    }
    @GetMapping("/kupiproizvode")
    public ResponseEntity<?>kupiProizvod(HttpSession session, @RequestParam long id,@RequestParam @Nullable double novaCena){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
          if(prijavljeniKorisnik==null){
                return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.KUPAC){
            return new ResponseEntity<>("Samo kupac ima pristup", HttpStatus.FORBIDDEN);
        }
        Proizvod proizvod = proizvodRepository.getProizvodsById(id);
        Korisnik korisnik =proizvod.getProdavac();

            Kupac kupac = (Kupac) prijavljeniKorisnik;
            List<Proizvod> kupljeniProizvodi = kupac.getKupljeni_proizvodi();
            Prodavac prodavac = (Prodavac) korisnik;
            List<Proizvod> proizvodiNaProdaju = prodavac.getProizvodi_na_prodaju();
            if (proizvod.getTipProdaje() == TipProdaje.tipProdaje.FiksnaCena) {
                if (!proizvod.isProdat()) {
                    kupljeniProizvodi.add(proizvod);
                    proizvod.setProdat(true);
                    proizvodRepository.save(proizvod);
                    proizvodiNaProdaju.remove(proizvod);
                    String prodavacEmail=prodavac.getEmailAdresa();
                    String proizvodNaziv=proizvod.getNaziv();
                    String kupacEmail=kupac.getEmailAdresa();
                    emailService.sendEmail(kupacEmail,"Purches Confirmation","You have successfully purchased the product: "+proizvodNaziv);
                    emailService.sendEmail(prodavacEmail,"Sold Confirmation","You have successfully sold the product "+proizvodNaziv);
                }else {
                    return new ResponseEntity<>("Proizvod je prodat",HttpStatus.BAD_REQUEST);
                }
            } else if (proizvod.getTipProdaje() == TipProdaje.tipProdaje.Aukcija) {
                    if(!proizvod.isProdat()){
                        Ponuda ponuda=ponudaRepository.findTopByProizvodOrderByPonudaPostavljenaDesc(proizvod);
                        if(ponuda==null){
                            String prodavacEmail=prodavac.getEmailAdresa();
                            Ponuda novaPonuda=new Ponuda();
                            novaPonuda.setCena(novaCena);
                            novaPonuda.setProizvod(proizvod);
                            novaPonuda.setKupac(kupac);
                            novaPonuda.setPonuda_postavljena(LocalDateTime.now());
                            emailService.sendEmail(prodavacEmail,"Informacije o novoj ceni","Nova cena proizvoda je "+novaCena);
                            ponudaRepository.save(novaPonuda);
                            return ResponseEntity.ok(novaPonuda);
                        }
                        if(ponuda.getCena()<novaCena){
                            //ponuda.setCena(novaCena);
                            String prodavacEmail=prodavac.getEmailAdresa();
                            Ponuda novaPonuda=new Ponuda();
                            novaPonuda.setCena(novaCena);
                            novaPonuda.setProizvod(proizvod);
                            novaPonuda.setKupac(kupac);
                            novaPonuda.setPonuda_postavljena(LocalDateTime.now());
                            emailService.sendEmail(prodavacEmail,"Informacije o novoj ceni","Nova cena proizvoda je "+novaCena);
                            ponudaRepository.save(novaPonuda);
                            return ResponseEntity.ok(novaPonuda);
                        }else {
                            return ResponseEntity.badRequest().body("Nova ponuda mora biti veća od trenutne ponude.");
                        }
                    }
            } else {
                return new ResponseEntity<>("Ne postojeci Tip Prodaje", HttpStatus.BAD_REQUEST);
            }
        return  new ResponseEntity<>("Proizvod je uspesno prodat", HttpStatus.OK);

    }

}
