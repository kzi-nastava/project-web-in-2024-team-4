package com.webshop.controller;

import com.webshop.Enumeracije.TipProdaje;
import com.webshop.Enumeracije.UlogaKorisnika;
import com.webshop.dto.*;
import com.webshop.model.*;
import com.webshop.repository.*;
import com.webshop.service.EmailService;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import com.webshop.service.RecenzijaService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
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
    RecenzijaService rezenzijaService;
    @Autowired
    RecenzijaRepository recenzijaRepository;
    @Autowired
    KategorijaRepository kategorijaRepository;
    @Autowired
    PonudaRepository ponudaRepository;
    @Autowired
    EmailService emailService;
    @PostMapping("/registracija")
    public ResponseEntity<?> registracijaKorisnika(@RequestBody KorisnikRegistracijaDto korisnikRegistracijaDto){
        if(korisnikRegistracijaDto.getKorisnickoIme().isEmpty()||korisnikRegistracijaDto.getBrojTelefona().isEmpty()||korisnikRegistracijaDto.getLozinka().isEmpty()||korisnikRegistracijaDto.getEmailAdresa().isEmpty()||korisnikRegistracijaDto.getIme().isEmpty()||korisnikRegistracijaDto.getPrezime().isEmpty()){
            return new ResponseEntity<>("Nisu uneseni adekvatni podaci", HttpStatus.BAD_REQUEST);
        }
       ResponseEntity<?> zahtev = korisnikService.registracijaKorisnika(korisnikRegistracijaDto);
        return ResponseEntity.ok(zahtev.getBody());
    }
    @PostMapping("/prijava-korisnika")
    public ResponseEntity<?> prijava(@RequestBody PrijavaKorisnikDto prijavaKorisnikDto,HttpSession session){
        if(prijavaKorisnikDto.getKorisnickoIme().isEmpty()|| prijavaKorisnikDto.getLozinka().isEmpty()){
            return new ResponseEntity<>("Nisu uneseni adekvatni podaci", HttpStatus.BAD_REQUEST);
        }else{
            try {
                Korisnik korisnik = korisnikService.prijavaKorisnika(prijavaKorisnikDto);
                // Ako korisnik postoji i lozinka je tačna, možemo nastaviti sesiju
                session.setAttribute("korisnik", korisnik);
                return new ResponseEntity<>(korisnik, HttpStatus.OK);
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
        if (kupacDto.getKorisnickoIme()!=null || kupacDto.getEmailAdresa()!=null||kupacDto.getLozinka()!=null){
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
        if(!kupacDto.getNovaLozinka().isEmpty()){
            prijavljeniKorisnik.setLozinka(kupacDto.getNovaLozinka());
        }else{
            prijavljeniKorisnik.setLozinka((kupacDto.getLozinka()));
        }
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

    @PostMapping("/kupac/oceniProdavca/{prodavacId}")
    public ResponseEntity<?> oceniProdavca(@PathVariable Long prodavacId, @RequestBody OcenjivanjeProdavcaDto ocenjivanjeProdavcaDto, HttpSession session) {
        Korisnik korisnikPrijavljen = (Korisnik) session.getAttribute("korisnik");
        if (korisnikPrijavljen == null) {
            return new ResponseEntity<>("Nema prijavljenog kupca", HttpStatus.BAD_REQUEST);
        }
        if (korisnikPrijavljen.getUloga() != UlogaKorisnika.Uloga.KUPAC) {
            return new ResponseEntity<>("Korisnik nije kupac, nema pristupa", HttpStatus.FORBIDDEN);
        }
        double ocena = ocenjivanjeProdavcaDto.getOcena();
        if (ocena < 1 || ocena > 5) {
            return new  ResponseEntity("Ocena mora biti u opsegu od 1 do 5.",HttpStatus.BAD_REQUEST);
        }
        boolean kupioProizvod=proizvodRepository.existsProizvodByKupacAndProdavac(korisnikPrijavljen,korisnikRepository.findKorisnikById(prodavacId));
        if (!kupioProizvod) {
            return new  ResponseEntity("Kupac nije kupio proizvod od tog prodavca ne moze mu ostaviti ocenu",HttpStatus.BAD_REQUEST);
        }
        rezenzijaService.oceniProdavca(korisnikPrijavljen,korisnikRepository.findKorisnikById(prodavacId),ocenjivanjeProdavcaDto);
        return  ResponseEntity.ok("Prodavac uspesno ocenjen");

    }
    @GetMapping("/prosecnaOcenaProdavca/{id}")
    public ResponseEntity<?> getProsecnaOcenaProdavca(@PathVariable Long id){
        Korisnik korisnik=korisnikRepository.findKorisnikById(id);
        if(korisnik.getUloga()!= UlogaKorisnika.Uloga.PRODAVAC){
            return ResponseEntity.badRequest().body("Uneti ID nije ID prodavca");
        }
        List<Recenzija> recenzijas=recenzijaRepository.findAllByKorisnikPrimio(korisnik);
        double prosecnaOcena=0;
        for(Recenzija r:recenzijas){
            prosecnaOcena+=r.getOcena();
        }
        prosecnaOcena=prosecnaOcena/recenzijas.size();
        return ResponseEntity.ok(prosecnaOcena);
    }


    @GetMapping("/kupac/pregledRecenzija")
    public  ResponseEntity<?> pregledRecenzijaKupac(HttpSession session){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null){
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.KUPAC){
            return new ResponseEntity<>("Samo kupac ima pristup", HttpStatus.FORBIDDEN);
        }
        List<Recenzija> recenzijeDaoKupac=recenzijaRepository.findAllByKorisnikDao(prijavljeniKorisnik);
        List<PregledRecenzijaDto>pregledRecenzijaDtos=new ArrayList<>();
        for(Recenzija r:recenzijeDaoKupac){
            PregledRecenzijaDto pregledRecenzijaDto=new PregledRecenzijaDto(r);
            pregledRecenzijaDtos.add(pregledRecenzijaDto);
        }
        for(Recenzija r:recenzijeDaoKupac){
            List<Recenzija> recenzijeAkojeProdavacDaoKupcu=recenzijaRepository.findAllByKorisnikDao(r.getKorisnikPrimio());
            for(Recenzija r1: recenzijeAkojeProdavacDaoKupcu){
                if(r1.getKorisnikPrimio().getId()==prijavljeniKorisnik.getId()) {
                    PregledRecenzijaDto pregledRecenzijaDto= new PregledRecenzijaDto(r1);
                    pregledRecenzijaDtos.add(pregledRecenzijaDto);
                }
            }

        }
        return ResponseEntity.ok(pregledRecenzijaDtos);
    }

    //3.2
    @GetMapping("/prodavac/pregled")
    public ResponseEntity<?> pregledKorisnikaProdavac(HttpSession session) {
        Korisnik korisnikPrijavljen = (Korisnik) session.getAttribute("korisnik");
        if (korisnikPrijavljen == null) {
            return new ResponseEntity<>("Nema prijavljenog prodavca", HttpStatus.BAD_REQUEST);
        }
        if (korisnikPrijavljen.getUloga() == UlogaKorisnika.Uloga.PRODAVAC) {
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
            return new ResponseEntity<>("Zabranjen pristup za korisnike koji nisu PRODAVAC", HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/prodavac/pregled/{id}")
    public ResponseEntity<?> pregledIzabranogProfilaProdavac(@PathVariable Long id,HttpSession session){
        Korisnik korisnikPrijavljen = (Korisnik) session.getAttribute("korisnik");
        if (korisnikPrijavljen == null) {
            return new ResponseEntity<>("Nema prijavljenog PRODAVCA", HttpStatus.BAD_REQUEST);
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

    //3.3 Funkcionalnost
    @PostMapping("/prodavac/postaviProdaju")
    public ResponseEntity<?> postavljanjeProdaje(HttpSession session,@RequestBody PostavljanjeProdajeDto postavljanjeProdajeDto) {
        Korisnik korisnikPrijavljen = (Korisnik) session.getAttribute("korisnik");
        if (korisnikPrijavljen == null) {
            return new ResponseEntity<>("Nema prijavljenog prodavca", HttpStatus.BAD_REQUEST);
        }
        if(korisnikPrijavljen.getUloga()!= UlogaKorisnika.Uloga.PRODAVAC) {
            return new ResponseEntity<>("Niste ulogovani kako PRODAVAC pristup odbijem",HttpStatus.FORBIDDEN);
        }

        Proizvod proizvod=new Proizvod();
        Prodavac prodavac=(Prodavac)korisnikPrijavljen;
        proizvod.setNaziv(postavljanjeProdajeDto.getNaziv());
        proizvod.setOpis(postavljanjeProdajeDto.getOpis());
        proizvod.setSlika(postavljanjeProdajeDto.getSlika());
        proizvod.setCena(postavljanjeProdajeDto.getCena());
        proizvod.setTipProdaje(postavljanjeProdajeDto.getTipProdaje());
        proizvod.setProdavac(prodavac);
        proizvod.setDatumObjavljivanja(LocalDate.now());
        Kategorija kategorija=kategorijaRepository.findKategorijaByNaziv(postavljanjeProdajeDto.getKategorija().getNaziv());
        if(kategorija==null){
            Kategorija kategorija1= new Kategorija();
            kategorija1.setNaziv(postavljanjeProdajeDto.getKategorija().getNaziv());
            kategorijaRepository.save(kategorija1);
            proizvod.setKategorija(kategorija1);
        }else{
            proizvod.setKategorija(kategorija);
        }
        //proizvod.getKategorija().setNaziv(postavljanjeProdajeDto.getKategorija().getNaziv());

        proizvodRepository.save(proizvod);
        return ResponseEntity.ok("Prodaja uspeno postavljena");
    }

    //3.4 Funkcionalnost
    @GetMapping("/prodavac/krajaukcije/{id}")
    public ResponseEntity<?> krajAukcije(HttpSession session,@PathVariable Long id){
        Korisnik korisnikPrijavljen = (Korisnik) session.getAttribute("korisnik");
        if (korisnikPrijavljen == null) {
            return new ResponseEntity<>("Nema prijavljenog prodavca", HttpStatus.BAD_REQUEST);
        }
        if(korisnikPrijavljen.getUloga()!= UlogaKorisnika.Uloga.PRODAVAC) {
            return new ResponseEntity<>("Niste ulogovani kako PRODAVAC pristup odbijem",HttpStatus.FORBIDDEN);
        }

        Proizvod proizvod=proizvodRepository.getProizvodsById(id);
        List<Ponuda> ponudas= ponudaRepository.findAllByProizvod(proizvod);
        if(proizvod.isProdat() || ponudas.isEmpty()){
            return new ResponseEntity<>("Ne mozete proglasiti kraj aukcije jer aukcija nije aktivna ili aukcija nije imala niti jednu ponudu",HttpStatus.BAD_REQUEST);
        }

        proizvod.setProdat(true);
        proizvodRepository.save(proizvod);

        Ponuda ponuda= ponudaRepository.findTopByProizvodOrderByPonudaPostavljenaDesc(proizvod);

        Kupac kupac=ponuda.getKupac();
        List<Proizvod> proizvods=kupac.getKupljeni_proizvodi();
        proizvods.add(proizvod);


        //Dodaj proveru da li je taj proizvod pripada toj prodavcu ulogovanom


        Prodavac prodavac=(Prodavac) korisnikPrijavljen;
        List<Proizvod>proizvodiNaProdaju=prodavac.getProizvodi_na_prodaju();
        proizvodiNaProdaju.remove(proizvod);
        String prodavacEmail=prodavac.getEmailAdresa();
        emailService.sendEmail(prodavacEmail,"Zavrsena Aukcija","Proizvod je prodat "+kupac.getIme()+" po ceni od "+ponuda.getCena());
        String kupacEmail;
        for(Ponuda p:ponudas){
            kupacEmail=p.getKupac().getEmailAdresa();
            emailService.sendEmail(kupacEmail,"Zavrsetak Aukcije","Hvala na učešću");
        }

        return ResponseEntity.ok("Aukcija je uspeno zavrsena");
    }

    //3.5 Ažuriranje informacija o proizvodu
    @PutMapping("/prodavac/azuriranjeProizvoda/{id}")
    public  ResponseEntity<?> azuriranjeInformacijaOPRoizvodu(@RequestBody@Nullable ProizvodDto proizvodDto, @PathVariable Long id, HttpSession session){
        Korisnik korisnikPrijavljen = (Korisnik) session.getAttribute("korisnik");
        if (korisnikPrijavljen == null) {
            return new ResponseEntity<>("Nema prijavljenog prodavca", HttpStatus.BAD_REQUEST);
        }
        if(korisnikPrijavljen.getUloga()!= UlogaKorisnika.Uloga.PRODAVAC) {
            return new ResponseEntity<>("Niste ulogovani kako PRODAVAC pristup odbijem",HttpStatus.FORBIDDEN);
        }
        Proizvod proizvod = proizvodRepository.getProizvodsById(id);
        List<Ponuda> ponudeZaProizvod=ponudaRepository.findAllByProizvod(proizvod);
        Prodavac prodavac=(Prodavac) korisnikPrijavljen;
        if(proizvod.getProdavac().getId()!=prodavac.getId()){
            return new ResponseEntity<>("Ovaj prodavac ne prodaje trazeni proizvod",HttpStatus.NOT_FOUND);
        }
        if(proizvod.getTipProdaje()== TipProdaje.tipProdaje.Aukcija){
            if(!ponudeZaProizvod.isEmpty()){
                return new ResponseEntity<>("Nije moguce izmeniti informacije o proizvodu jer za taj proizvod postoje ponude",HttpStatus.BAD_REQUEST);
            }
        }
        if(proizvodDto.getIme()!=null)
            proizvod.setNaziv(proizvodDto.getIme());
        if(proizvodDto.getOpis()!=null)
            proizvod.setOpis(proizvodDto.getOpis());
        if(proizvodDto.getKategorija()!=null) {
            boolean kategorija=kategorijaRepository.existsKategorijaByNaziv(proizvodDto.getKategorija().getNaziv());
            if(!kategorija){
                Kategorija novaKategorija = new Kategorija();
                novaKategorija.setNaziv(proizvodDto.getKategorija().getNaziv());
                proizvod.setKategorija(novaKategorija);
                kategorijaRepository.save(novaKategorija);
            }else {
                Kategorija kategorija1=kategorijaRepository.findKategorijaByNaziv(proizvodDto.getKategorija().getNaziv());
                proizvod.setKategorija(kategorija1);
                }
            }

        proizvod.setCena(proizvodDto.getCena());
        if(proizvodDto.getSlika()!=null)
            proizvod.setSlika(proizvodDto.getSlika());
        if(proizvodDto.getTipProdaje()!=null)
            proizvod.setTipProdaje(proizvodDto.getTipProdaje());

        proizvodRepository.save(proizvod);
        return new ResponseEntity<>("Proizvod je uspeno updatovan",HttpStatus.OK);
    }

    @PostMapping("/prodavac/oceniKupca/{kupacId}")
    public ResponseEntity<?> oceniKupca(@PathVariable Long kupacId, @RequestBody OcenjivanjeKupcaDto ocenjivanjeKupcaDto, HttpSession session) {
        Korisnik korisnikPrijavljen = (Korisnik) session.getAttribute("korisnik");
        if (korisnikPrijavljen == null) {
            return new ResponseEntity<>("Nema prijavljenog prodavca", HttpStatus.BAD_REQUEST);
        }
        if (korisnikPrijavljen.getUloga() != UlogaKorisnika.Uloga.PRODAVAC) {
            return new ResponseEntity<>("Korisnik nije kupac, nema pristupa", HttpStatus.FORBIDDEN);
        }

        rezenzijaService.oceniKupca(korisnikPrijavljen,korisnikRepository.findKorisnikById(kupacId),ocenjivanjeKupcaDto);
        return  ResponseEntity.ok("Prodavac uspesno ocenjen");
    }
    @GetMapping("/prosecnaOcenaKupca/{id}")
    public ResponseEntity<?> getProsecnaOcenaKupca(@PathVariable Long id){
        Korisnik korisnik=korisnikRepository.findKorisnikById(id);
        if(korisnik.getUloga()!= UlogaKorisnika.Uloga.KUPAC){
            return ResponseEntity.badRequest().body("Uneti ID nije ID Kupca");
        }
        List<Recenzija> recenzijas=recenzijaRepository.findAllByKorisnikPrimio(korisnik);
        Kupac kupac=(Kupac) korisnik;
        double prosecnaOcena=kupac.getProsecna_ocena();
        for(Recenzija r:recenzijas){
            prosecnaOcena+=r.getOcena();
        }
        prosecnaOcena=prosecnaOcena/(recenzijas.size()+1);
        return ResponseEntity.ok(prosecnaOcena);
    }

    //3.7Implementacija

    @GetMapping("/prodavac/pregledRecenzija")
    public  ResponseEntity<?> pregledRecenzijaProdavac(HttpSession session){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null){
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.PRODAVAC){
            return new ResponseEntity<>("Samo prodavac ima pristup", HttpStatus.FORBIDDEN);
        }
        List<Recenzija> recenzijeDaoProdavac=recenzijaRepository.findAllByKorisnikDao(prijavljeniKorisnik);
        List<PregledRecenzijaDto>pregledRecenzijaDtos=new ArrayList<>();
        for(Recenzija r:recenzijeDaoProdavac){
            PregledRecenzijaDto pregledRecenzijaDto=new PregledRecenzijaDto(r);
            pregledRecenzijaDtos.add(pregledRecenzijaDto);
        }
        for(Recenzija r:recenzijeDaoProdavac){
            List<Recenzija> recenzijeAkojeKupacDaoProdavcu=recenzijaRepository.findAllByKorisnikDao(r.getKorisnikPrimio());
            for(Recenzija r1: recenzijeAkojeKupacDaoProdavcu){
                if(r1.getKorisnikPrimio().getId()==prijavljeniKorisnik.getId()) {
                    PregledRecenzijaDto pregledRecenzijaDto= new PregledRecenzijaDto(r1);
                    pregledRecenzijaDtos.add(pregledRecenzijaDto);
                }
            }

        }
        return ResponseEntity.ok(pregledRecenzijaDtos);
    }
    //4.1 Funkcionalnost
    @GetMapping("/administrator/recenzije/pregled")
    public ResponseEntity<?> administratorPregledRecenzija(HttpSession session){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null){
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.ADMINISTRATOR){
            return new ResponseEntity<>("Samo administrator ima pristup", HttpStatus.FORBIDDEN);
        }

        return korisnikService.administratorPregled();
    }

    @PutMapping("/administrator/recenzije/izmena/{id}")
    public ResponseEntity<?> administratorIzmenaRecenzija(HttpSession session,@PathVariable Long id,@RequestParam String komentar){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null){
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.ADMINISTRATOR){
            return new ResponseEntity<>("Samo administrator ima pristup", HttpStatus.FORBIDDEN);
        }
        return korisnikService.administratorIzmena(id, komentar);
    }
    @PostMapping("/administrator/recenzije/obrisi")
    public ResponseEntity<?> administratorObrisiRecenzija(HttpSession session,@RequestParam Long id){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null){
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.ADMINISTRATOR){
            return new ResponseEntity<>("Samo administrator ima pristup", HttpStatus.FORBIDDEN);
        }
        return korisnikService.administratorObrisi(id);
    }

    @GetMapping("/administrator/podneseneprijave/pregled")
    public ResponseEntity<?> administratorGetPrijave(HttpSession session){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null){
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.ADMINISTRATOR){
            return new ResponseEntity<>("Samo administrator ima pristup", HttpStatus.FORBIDDEN);
        }
        return korisnikService.administratorGetPrijave();
    }

    //4.2 Funkcionalnost

    @PostMapping("/administrator/podneseneprijave/obrada/{id}")
    public ResponseEntity<?> administratorObarada(HttpSession session,@PathVariable Long id,@RequestParam Boolean odluka,@Nullable @RequestParam String razlogOdbijanja){
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik==null){
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if(prijavljeniKorisnik.getUloga()!= UlogaKorisnika.Uloga.ADMINISTRATOR){
            return new ResponseEntity<>("Samo administrator ima pristup", HttpStatus.FORBIDDEN);
        }
        return korisnikService.administratorObradiPrijavu(id,odluka,razlogOdbijanja);
    }

}
