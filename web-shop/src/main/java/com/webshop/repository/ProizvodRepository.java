package com.webshop.repository;

import com.webshop.model.Proizvod;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod,Long> {
    //Page<Proizvod> findAll(Pageable pageable);
    //List<Proizvod> findAllByNaziv(String naziv,Pageable pageable);
   // List<Proizvod> findAllByCenaAndNaziv(double cena,String naziv,Pageable pageable);
}
