package com.webshop.controller;

import com.webshop.dto.KategorijaDto;
import com.webshop.dto.ProizvodDto;
import com.webshop.model.Kategorija;
import com.webshop.model.Korisnik;
import com.webshop.model.Proizvod;
import com.webshop.service.KategorijaService;
import com.webshop.service.ProizvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kategorija")
public class KategorijaController {
    @Autowired
    KategorijaService kategorijaService;
    @Autowired
    ProizvodService proizvodService;
    @GetMapping
    public ResponseEntity<List<KategorijaDto>> getAllCategory(){
        List<KategorijaDto> kategorija=kategorijaService.getAllCategory();
        return ResponseEntity.ok(kategorija);
    }
    @GetMapping("/{naziv}")
    public ResponseEntity<List<ProizvodDto>>getAllProductForCategory(@PathVariable String naziv){
        List<ProizvodDto> proizvod=kategorijaService.getAllProductForCategory(naziv);
        return ResponseEntity.ok(proizvod);
    }
}
