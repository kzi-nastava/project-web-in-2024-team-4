package com.webshop.service;

import com.webshop.dto.KategorijaDto;
import com.webshop.dto.ProizvodDto;
import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;
import com.webshop.repository.KategorijaRepository;
import com.webshop.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KategorijaService {
    @Autowired
    KategorijaRepository kategorijaRepository;
    @Autowired
    ProizvodRepository proizvodRepository;

    public List<KategorijaDto> getAllCategory(){
        List<Kategorija> kategorija=kategorijaRepository.findAll();
        List<KategorijaDto> kategorijaDtos=new ArrayList<>();
        for(Kategorija k: kategorija){
            kategorijaDtos.add(ConvertToDTO(k));
        }
        return kategorijaDtos;
    }
    public List<ProizvodDto> getAllProductForCategory(String naziv){
        List<Proizvod> proizvod=proizvodRepository.findAll();
        List<ProizvodDto>proizvodDtos=new ArrayList<>();
        for(Proizvod p:proizvod){
            if(p.getKategorija().getNaziv().equals(naziv)){
                proizvodDtos.add(convertToDTO(p));
            }
        }
        return proizvodDtos;
    }

    private KategorijaDto ConvertToDTO(Kategorija kategorija){
        KategorijaDto dtos=new KategorijaDto();
        dtos.setId(kategorija.getId());
        dtos.setNaziv(kategorija.getNaziv());
        //dtos.setProizvodi(kategorija.getProizvodi());
        return  dtos;
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
        return dto;
    }


}
