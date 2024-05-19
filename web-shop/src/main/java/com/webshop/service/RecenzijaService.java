package com.webshop.service;

import com.webshop.dto.OcenjivanjeKupcaDto;
import com.webshop.dto.OcenjivanjeProdavcaDto;
import com.webshop.model.*;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.ProizvodRepository;
import com.webshop.repository.RecenzijaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecenzijaService {

    @Autowired
    RecenzijaRepository recenzijaRepository;
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    ProizvodRepository proizvodRepository;

    public void oceniProdavca(Korisnik kupac, Korisnik prodavac, OcenjivanjeProdavcaDto ocenjivanjeProdavcaDto) {

        boolean kupioProizvod=proizvodRepository.existsProizvodByKupacAndProdavac(kupac,prodavac);
        if (!kupioProizvod) {
            throw new IllegalArgumentException("Kupac nije kupio proizvod od datog prodavca.");
        }

        // Provera da li je ocena validna (u opsegu od 1 do 5)
        double ocena = ocenjivanjeProdavcaDto.getOcena();
        if (ocena < 1 || ocena > 5) {
            throw new IllegalArgumentException("Ocena mora biti u opsegu od 1 do 5.");
        }

        Recenzija recenzija = new Recenzija();
        recenzija.setOcena(ocenjivanjeProdavcaDto.getOcena());
        recenzija.setKomentar(ocenjivanjeProdavcaDto.getKomentar());
        recenzija.setKorisnikDao(kupac);
        recenzija.setKorisnikPrimio(prodavac);
        recenzija.setDatumRecenzije(LocalDate.now());

        recenzijaRepository.save(recenzija);
    }

    public void oceniKupca(Korisnik prodavac, Korisnik kupac, OcenjivanjeKupcaDto ocenjivanjeKupcaDto) {

        boolean prodaoProizvod=proizvodRepository.existsProizvodByProdavacAndKupac(prodavac,kupac);
        if (!prodaoProizvod) {
            throw new IllegalArgumentException("Prodavac nije prodao proizvod datom kupcu");
        }

        // Provera da li je ocena validna (u opsegu od 1 do 5)
        double ocena = ocenjivanjeKupcaDto.getOcena();
        if (ocena < 1 || ocena > 5) {
            throw new IllegalArgumentException("Ocena mora biti u opsegu od 1 do 5.");
        }

        Recenzija recenzija = new Recenzija();
        recenzija.setOcena(ocenjivanjeKupcaDto.getOcena());
        recenzija.setKomentar(ocenjivanjeKupcaDto.getKomentar());
        recenzija.setKorisnikDao(prodavac);
        recenzija.setKorisnikPrimio(kupac);
        recenzija.setDatumRecenzije(LocalDate.now());

        recenzijaRepository.save(recenzija);
    }

}
