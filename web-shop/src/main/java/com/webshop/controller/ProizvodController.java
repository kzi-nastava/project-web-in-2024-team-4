package com.webshop.controller;

import com.webshop.Enumeracije.TipProdaje;
import com.webshop.dto.ProizvodDto;
import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;
import com.webshop.repository.KategorijaRepository;
import com.webshop.repository.ProizvodRepository;
import com.webshop.service.KategorijaService;
import com.webshop.service.ProizvodService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
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

}
