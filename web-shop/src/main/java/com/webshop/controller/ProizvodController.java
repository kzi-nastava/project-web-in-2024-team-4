package com.webshop.controller;

import com.webshop.dto.ProizvodDto;
import com.webshop.model.Proizvod;
import com.webshop.repository.ProizvodRepository;
import com.webshop.service.ProizvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("proizvod")
public class ProizvodController {
    @Autowired
    private ProizvodRepository proizvodRepository;
    @Autowired
    private ProizvodService proizvodService;

    @GetMapping("/lista-proizvoda")
    public ResponseEntity<List<ProizvodDto>> getAllProducts(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue ="10")int size){
        List<ProizvodDto> proizvod=proizvodService.getAllProducts(page,size);
        return  ResponseEntity.ok(proizvod);
    }
    @GetMapping("/proizvodi")
    public ResponseEntity<List<ProizvodDto>> findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(@RequestParam(required = false) String naziv,@RequestParam(required = false) String opis){
        List<ProizvodDto>proizvodDtos=proizvodService.findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(naziv,opis);
        return  ResponseEntity.ok(proizvodDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProizvodDto>getOneProducts(@PathVariable long id){
        ProizvodDto proizvodDto=proizvodService.getOneProducts(id);
        return ResponseEntity.ok(proizvodDto);
    }

}
