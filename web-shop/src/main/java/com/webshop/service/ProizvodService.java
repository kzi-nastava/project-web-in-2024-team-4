package com.webshop.service;

import com.webshop.Enumeracije.TipProdaje;
import com.webshop.dto.ProizvodDto;
import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;
import com.webshop.repository.KategorijaRepository;
import com.webshop.repository.ProizvodRepository;
import org.apache.logging.log4j.message.ExitMessage;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Service
public class ProizvodService {
    @Autowired
    private ProizvodRepository proizvodRepository;
    @Autowired
    private KategorijaRepository kategorijaRepository;
    public List<ProizvodDto> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Proizvod> products = proizvodRepository.findAll(pageable);
        return products.map(this::convertToDTO).getContent();
    }
    public ProizvodDto getOneProducts(long id){
        Optional<Proizvod> proizvod;
        ProizvodDto proizvodDto = new ProizvodDto();
        proizvod=proizvodRepository.findById(id);
        if(proizvod.isPresent()){
            proizvodDto=proizvod.map(this::convertToDTO).get();
            return proizvodDto;
        }else{
            //Ili return Null
            throw new RuntimeException("Proizvod sa ID-em "+id+" ne postoji");
        }
    }
    public  List<ProizvodDto> findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(String naziv,String opis){
        Optional<List<Proizvod>> proizvod=proizvodRepository.findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(naziv,opis);
        List<ProizvodDto> proizvodDtos=new ArrayList<>();
        if(proizvod.isPresent()) {
            for (Proizvod p : proizvod.get()) {
                proizvodDtos.add(new ProizvodDto(p));
            }
        }else{
            throw new RuntimeException("Ne Postoji proizvod sa tom filtracijom");
        }
        return  proizvodDtos;
    }

    public List<ProizvodDto> findFilteredProducts(Double minPrice, Double maxPrice, TipProdaje.tipProdaje tipProdaje, String category) {
        List<Proizvod> proizvods=proizvodRepository.findProductsByFilters(minPrice, maxPrice, tipProdaje, category);
        List<ProizvodDto>proizvodDtos=new ArrayList<>();
        for(Proizvod p: proizvods){
            proizvodDtos.add(convertToDTO(p));
        }
        return proizvodDtos;
    }

        private ProizvodDto convertToDTO(Proizvod product) {
        ProizvodDto dto = new ProizvodDto();
        dto.setId(product.getId());
        dto.setIme(product.getNaziv());
        dto.setOpis(product.getOpis());
        dto.setKategorija(product.getKategorija());
        dto.setCena(product.getCena());
        dto.setSlika(product.getSlika());
        dto.setTipProdaje(product.getTipProdaje());
        dto.setProdavac(product.getProdavac());
        return dto;
    }
}
