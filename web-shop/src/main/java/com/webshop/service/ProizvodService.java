package com.webshop.service;

import com.webshop.dto.ProizvodDto;
import com.webshop.model.Proizvod;
import com.webshop.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class ProizvodService {
    @Autowired
    private ProizvodRepository proizvodRepository;

    public List<ProizvodDto> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Proizvod> products = proizvodRepository.findAll(pageable);
        return products.map(this::convertToDTO).getContent();
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
